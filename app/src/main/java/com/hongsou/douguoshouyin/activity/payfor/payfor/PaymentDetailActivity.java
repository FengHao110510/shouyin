package com.hongsou.douguoshouyin.activity.payfor.payfor;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.http.ResponseCallback;
import com.hongsou.douguoshouyin.javabean.PaymentDetailBean;
import com.hongsou.douguoshouyin.javabean.RootBean;
import com.hongsou.douguoshouyin.tool.ToastUtil;
import com.hongsou.douguoshouyin.views.CommonTopBar;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * @author lpc
 *         <p>
 * @copyright 鸿搜网络公司 版权所有
 * <p>
 * @date 2018/7/23
 * <p>
 * @desc 支付订单详情页
 */
public class PaymentDetailActivity extends BaseActivity {


    @BindView(R.id.top_bar)
    CommonTopBar mTopBar;
    @BindView(R.id.iv_order_status)
    ImageView mIvOrderStatus;
    @BindView(R.id.tv_order_title)
    TextView mTvOrderTitle;
    @BindView(R.id.tv_order_money)
    TextView mTvOrderMoney;
    @BindView(R.id.tv_order_batch)
    TextView mTvOrderBatch;
    @BindView(R.id.tv_order_pay_time)
    TextView mTvOrderPayTime;
    @BindView(R.id.tv_order_pay_money)
    TextView mTvOrderPayMoney;
    @BindView(R.id.tv_order_pay_type)
    TextView mTvOrderPayType;
    @BindView(R.id.tv_order_pay_status)
    TextView mTvOrderPayStatus;
    @BindView(R.id.btn_order_back_money)
    Button mBtnOrderBackMoney;
    @BindView(R.id.btn_order_print)
    Button mBtnOrderPrint;
    @BindView(R.id.btn_order_again)
    Button mBtnOrderAgain;
    private Dialog dialog;
    private String mBatch;

    @Override
    public int initLayout() {
        return R.layout.module_activity_order_detail;
    }

    @Override
    protected void init() {
        if (getIntent().hasExtra("batch")){
            mBatch = getIntent().getStringExtra("batch");
        }
        mTopBar.setRightViewClickListener(new CommonTopBar.ClickCallBack() {
            @Override
            public void onClick(View v) {
                // 右上角同步按钮

            }
        });
        // 点击返回按钮，跳转到首页
//        mTopBar.setLeftViewClickListener(new CommonTopBar.ClickCallBack() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(PaymentDetailActivity.this, MainActivity.class));
//                finish();
//            }
//        });
        initData();
    }

    @Override
    public void initData() {
        //TODO 需要初始化数据
        HttpFactory.get().url(ApiConfig.GET_PAYMENT_ORDER_BY_BATCH)
                .addParams("shopNumber", getShopNumber())
                .addParams("batch", mBatch)
                .build()
                .execute(new ResponseCallback<RootBean<PaymentDetailBean>>(this) {
                    @Override
                    public void onResponse(RootBean<PaymentDetailBean> response, int id) {
                        if (response.isSuccess()) {
                            PaymentDetailBean data = response.getData();
                            renderView(data);
                        }else {
                            ToastUtil.showToast(response.getMsg());
                        }
                    }
                });
    }

    /**
     * @desc 数据显示
     * @anthor lpc
     * @date: 2018/7/24
     * @param data 数据源
     */
    private void renderView(PaymentDetailBean data) {
        // OrderState 0 '支付中'  1'收款成功'  -2 '支付中' -3'已退款'
        if ("0".equals(data.getOrderState())){
            setViewInfo("收款中", R.drawable.icon_paying, View.VISIBLE);
        }else if ("-2".equals(data.getOrderState())){
            setViewInfo("收款中", R.drawable.icon_paying, View.GONE);
        }else if ("1".equals(data.getOrderState())){
            setViewInfo("收款成功", R.drawable.icon_pay_success, View.GONE);
        }else if ("-3".equals(data.getOrderState())){
            setViewInfo("退款成功", R.drawable.icon_pay_back_money, View.GONE);
        }
        mTvOrderBatch.setText(data.getBatch());
        mTvOrderPayTime.setText(data.getTradingTime());
        mTvOrderPayMoney.setText(data.getPayAmount() + "元");
        mTvOrderMoney.setText(data.getPayAmount());
        if (data.getPaymentType().contains("支付宝")){
            mTvOrderPayType.setText("支付宝");
        }else if (data.getPaymentType().contains("微信")){
            mTvOrderPayType.setText("微信");
        }else if (data.getPaymentType().contains("现金")){
            mTvOrderPayType.setText("现金");
        }
    }

    /**
     * @desc 设置标题图片等内容
     * @anthor lpc
     * @date: 2018/7/24
     * @param title 标题
     * @param imgRes 图标的图片id
     * @param visible 右上角同步按钮是否显示
     */
    private void setViewInfo(String title, int imgRes, int visible) {
        mTopBar.setCenterText(title);
        mIvOrderStatus.setImageResource(imgRes);
        mTvOrderTitle.setText(title);
        mTopBar.setRightVisibility(visible);
        mTvOrderPayStatus.setText(title);
    }


    @OnClick({R.id.btn_order_back_money, R.id.btn_order_print, R.id.btn_order_again})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_order_back_money:
                //走退款接口
                tuikuanDialog();
                break;
            case R.id.btn_order_print:
                // 打印小票

                break;
            case R.id.btn_order_again:
                // 继续收款

                break;
            default:
                break;
        }
    }

    //输入退款密码
    private void tuikuanDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.module_dialog_tuikuan, null);
        final EditText et_dialog_tuikuan_content = view.findViewById(R.id.et_dialog_tuikuan_content);
        TextView tv_dialog_tuikuan_yes = view.findViewById(R.id.tv_dialog_tuikuan_yes);
        TextView tv_dialog_tuikuan_cancle = view.findViewById(R.id.tv_dialog_tuikuan_cancle);

        Display display = this.getWindowManager().getDefaultDisplay();
        int w = display.getWidth();
        int h = display.getHeight();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(w * 4 / 5, h * 2 / 7);
        dialog = new Dialog(this, R.style.CommonDialog);
        dialog.addContentView(view, params);
        dialog.show();


        tv_dialog_tuikuan_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!et_dialog_tuikuan_content.getText().toString().equals("")) {
                    //TODO 判断密码是否正确
                    tuikuan();
                    dialog.dismiss();
                } else {
                    ToastUtil.showToast("请输入密码");
                }

            }
        });
        tv_dialog_tuikuan_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }

    /**
     * 退款接口`
     */
    private void tuikuan() {
        showLoadingDialog();
        HttpFactory.post().url(ApiConfig.TUIKUAN).addParams("", "").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoadingDialog();

            }

            @Override
            public void onResponse(String response, int id) {
                dismissLoadingDialog();
                //TODO 成功之后跳转到退款成功页面 此页面关闭
                Intent backIntent = new Intent(PaymentDetailActivity.this, BackPayActivity.class);
                startActivity(backIntent);
                finishActivity();
            }
        });
    }


    //=======================================================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

}
