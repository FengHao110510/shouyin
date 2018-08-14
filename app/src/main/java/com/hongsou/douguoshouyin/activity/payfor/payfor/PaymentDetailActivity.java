package com.hongsou.douguoshouyin.activity.payfor.payfor;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.activity.MainActivity;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.broadcastreceiver.PayOnLineSuccessBean;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.http.ResponseCallback;
import com.hongsou.douguoshouyin.javabean.BaseBean;
import com.hongsou.douguoshouyin.javabean.PaymentDetailBean;
import com.hongsou.douguoshouyin.javabean.RootBean;
import com.hongsou.douguoshouyin.tool.Global;
import com.hongsou.douguoshouyin.tool.MscSpeechUtils;
import com.hongsou.douguoshouyin.tool.ToastUtil;
import com.hongsou.douguoshouyin.tool.bluetooth.BluetoothPrinterUtil;
import com.hongsou.douguoshouyin.views.CommonTopBar;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
    private String paymentBatch = "";
    String now;//现在时间
    private PaymentDetailBean mPaymentDetailBean;

    //判断是不是从流水fragment过来的  是的话直接关闭页面 不用跳转main  0是 1不是
    private int turnover;

    @Override
    public int initLayout() {
        return R.layout.module_activity_order_detail;
    }

    @Override
    protected void init() {

        if (getIntent().hasExtra("turnover")) {
            turnover = 0;
        } else {
            turnover = 1;
        }

        //获取当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        now = sdf.format(new Date());
        if (getIntent().hasExtra("paymentBatch")) {
            paymentBatch = getIntent().getStringExtra("paymentBatch");
        }
        if (getIntent().hasExtra("batch")) {
            mBatch = getIntent().getStringExtra("batch");
            initData();
        } else if (getIntent().hasExtra("payOnLineSuccessBean")) {
            PayOnLineSuccessBean payOnLineSuccessBean = (PayOnLineSuccessBean) getIntent().getSerializableExtra("payOnLineSuccessBean");
            mTvOrderMoney.setText(payOnLineSuccessBean.getMoney());
            mTvOrderPayTime.setText(payOnLineSuccessBean.getDate());
            mTvOrderBatch.setText(payOnLineSuccessBean.getOutTradeNo());
            mTvOrderPayType.setText(payOnLineSuccessBean.getTradeType());
            mTvOrderPayMoney.setText(payOnLineSuccessBean.getMoney());
            mTvOrderPayStatus.setText("支付成功");
            mBatch = payOnLineSuccessBean.getBatch();
            paymentBatch = payOnLineSuccessBean.getOutTradeNo();
            if (Global.getSpUserUtil().getSpeechVoice()) {
                MscSpeechUtils.speech(payOnLineSuccessBean.getTradeType() + "收款到账"
                        + payOnLineSuccessBean.getMoney() + "元", this);
            }

        } else if (getIntent().hasExtra("xianjin")) {
            paymentBatch = getIntent().getStringExtra("paymentBatch");
            mTvOrderMoney.setText(getIntent().getStringExtra("money"));
            mTvOrderPayTime.setText(now);
            mTvOrderBatch.setText(paymentBatch);
            mTvOrderPayType.setText("现金");
            mTvOrderPayMoney.setText(getIntent().getStringExtra("money"));
            mTvOrderPayStatus.setText("支付成功");
            mBatch = "00000000000000000000";

        }else if (getIntent().hasExtra("scanH5")){
            ToastUtil.showToast(getIntent().getStringExtra("scanH5"));
        }
        mTopBar.setRightViewClickListener(new CommonTopBar.ClickCallBack() {
            @Override
            public void onClick(View v) {
                // 右上角同步按钮

            }
        });
        // 点击返回按钮，跳转到首页
        mTopBar.setLeftViewClickListener(new CommonTopBar.ClickCallBack() {
            @Override
            public void onClick(View v) {
                if (turnover==1){
                    startActivity(new Intent(PaymentDetailActivity.this, MainActivity.class));
                }
                finish();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (turnover==1){
                startActivity(new Intent(PaymentDetailActivity.this, MainActivity.class));
            }
            finish();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }

    @Override
    public void initData() {
        //TODO 需要初始化数据
        HttpFactory.get().url(ApiConfig.GET_PAYMENT_ORDER_BY_BATCH)
                .addParams("shopNumber", getShopNumber())
                .addParams("batch", mBatch)
                .addParams("paymentBatch", paymentBatch)
                .build()
                .execute(new ResponseCallback<RootBean<PaymentDetailBean>>(this) {
                    @Override
                    public void onResponse(RootBean<PaymentDetailBean> response, int id) {
                        if (response.isSuccess()) {
                            mPaymentDetailBean = response.getData();
                            renderView(mPaymentDetailBean);
                        } else {
                            ToastUtil.showToast(response.getMsg());
                        }
                    }
                });
    }

    /**
     * @param data 数据源
     * @desc 数据显示
     * @anthor lpc
     * @date: 2018/7/24
     */
    private void renderView(PaymentDetailBean data) {
        // OrderType 0 '支付中'  1'收款成功'  -2 '支付中' -3'已退款'
        if ("0".equals(data.getOrderType())) {
            setViewInfo("收款中", R.drawable.icon_paying, View.VISIBLE, View.VISIBLE);
        } else if ("-2".equals(data.getOrderType())) {
            setViewInfo("收款中", R.drawable.icon_paying, View.GONE, View.VISIBLE);
        } else if ("1".equals(data.getOrderType())) {
            setViewInfo("收款成功", R.drawable.icon_pay_success, View.GONE, View.VISIBLE);
        } else if ("-3".equals(data.getOrderType())) {
            setViewInfo("退款成功", R.drawable.icon_pay_back_money, View.GONE, View.GONE);
        }
        mTvOrderBatch.setText(TextUtils.isEmpty(data.getPaymentBatch()) ? data.getBatch() : data.getPaymentBatch());
        mTvOrderPayTime.setText(data.getTradingTime());
        mTvOrderPayMoney.setText(data.getPayAmount() + "元");
        mTvOrderMoney.setText(data.getPayAmount());
        if (data.getPaymentType().contains("支付宝")) {
            mTvOrderPayType.setText("支付宝");
        } else if (data.getPaymentType().contains("微信")) {
            mTvOrderPayType.setText("微信");
        } else if (data.getPaymentType().contains("现金")) {
            mTvOrderPayType.setText("现金");
        }
    }

    /**
     * @param title   标题
     * @param imgRes  图标的图片id
     * @param visible 右上角同步按钮是否显示
     * @desc 设置标题图片等内容
     * @anthor lpc
     * @date: 2018/7/24
     */
    private void setViewInfo(String title, int imgRes, int visible, int visible2) {
        mTopBar.setCenterText(title);
        mTvOrderTitle.setText(title);
        mTvOrderPayStatus.setText(title);
        mIvOrderStatus.setImageResource(imgRes);
        mTopBar.setRightVisibility(visible);
        mBtnOrderBackMoney.setVisibility(visible2);
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
                if (!"00000000000000000000".equals(mBatch)) {
                    // 不是纯收款，开单
                    if (Global.getSpUserUtil().getOrderPrintSwitch()) {
                        BluetoothPrinterUtil util = new BluetoothPrinterUtil.Builder()
                                .setType(BluetoothPrinterUtil.Print.ORDER)
                                .setCount(Global.getSpUserUtil().getOrderPrintCount())
                                .setContent(mPaymentDetailBean)
                                .build();
                        util.startPrint();
                    }
                } else {
                    // 纯收款
                    if (Global.getSpUserUtil().getOrderPrintSwitch()) {
                        BluetoothPrinterUtil util = new BluetoothPrinterUtil.Builder()
                                .setType(BluetoothPrinterUtil.Print.ORDER)
                                .setCount(Global.getSpUserUtil().getOrderPrintCount())
                                .setContent(mPaymentDetailBean)
                                .build();
                        util.startPrint();
                    }
                }

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
                if (et_dialog_tuikuan_content.getText().toString().equals(Global.getSpGlobalUtil().getPassword())) {
                    //TODO 判断密码是否正确
                    String type = "";
                    if (mTvOrderPayType.getText().toString().contains("微信")) {
                        type = "wechar";
                        tuikuan(type);

                    } else if (mTvOrderPayType.getText().toString().contains("支付宝")) {
                        type = "ali";
                        tuikuan(type);
                    } else {
                        //现金退款
                        cashTuikuan();
                    }
                    dialog.dismiss();
                } else {
                    ToastUtil.showToast("请输入正确密码");
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
     * @author fenghao
     * @date 2018/7/27 0027 下午 17:42
     * @desc
     */
    private void cashTuikuan() {
        HttpFactory.post().url(ApiConfig.REFOUND_BY_CASH)
                .addParams("paymentBatch", paymentBatch)
                .addParams("batch", mBatch)
                .build().execute(new ResponseCallback<BaseBean>(this) {
            @Override
            public void onResponse(BaseBean response, int id) {
                if (response.isSuccess()) {
                    if (!"00000000000000000000".equals(mBatch)) {
                        //不是纯收款走个退单接口
                        tuidan();
                    } else {
                        Intent backIntent = new Intent(PaymentDetailActivity.this, BackPayActivity.class);
                        backIntent.putExtra("refundAmount", mTvOrderMoney.getText().toString());
                        backIntent.putExtra("outTradeNo", mTvOrderBatch.getText().toString());
                        backIntent.putExtra("type", mTvOrderPayType.getText().toString());
                        startActivity(backIntent);
                        finishActivity();
                        ToastUtil.showToast("退款成功");
                    }
                } else {
                    ToastUtil.showToast(response.getMsg());
                }
            }
        });
    }

    /**
     * 退款接口`
     *
     * @param type
     */
    private void tuikuan(String type) {
        showLoadingDialog();

        HttpFactory.post().url(ApiConfig.REFUND)
                .addParams("outTradeNo", mTvOrderBatch.getText().toString())
                .addParams("type", type)
                .addParams("appAuthToken", Global.getSpGlobalUtil().getAliCode())
                .addParams("subMchId", Global.getSpGlobalUtil().getWecharCode())
                .addParams("refundAmount", mTvOrderMoney.getText().toString())

                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoadingDialog();

            }

            @Override
            public void onResponse(String response, int id) {
                dismissLoadingDialog();
                Log.e(TAG, "onResponse: " + response);
                //TODO 成功之后跳转到退款成功页面 此页面关闭
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = (int) jsonObject.get("code");
                    if (code == 0) {
                        if (!"00000000000000000000".equals(mBatch)) {
                            //不是纯收款走个退单接口
                            tuidan();
                        } else {
                            Intent backIntent = new Intent(PaymentDetailActivity.this, BackPayActivity.class);
                            backIntent.putExtra("refundAmount", mTvOrderMoney.getText().toString());
                            backIntent.putExtra("outTradeNo", mTvOrderBatch.getText().toString());
                            backIntent.putExtra("type", mTvOrderPayType.getText().toString());
                            startActivity(backIntent);
                            finishActivity();
                        }

                    } else {
                        ToastUtil.showToast("退款失败");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    /**
     * @author fenghao
     * @date 2018/7/26 0026 下午 16:20
     * @desc 退单接口
     */
    private void tuidan() {
        String type = "";
        if (mTvOrderPayType.getText().toString().contains("微信")) {
            type = "微信";
        } else if (mTvOrderPayType.getText().toString().contains("支付宝")) {
            type = "支付宝";
        }
        HttpFactory.post()
                .addParams("shopNumber", getShopNumber())
                .addParams("batch", mBatch)
                .addParams("orderAmount", mTvOrderMoney.getText().toString())
                .addParams("reason", "")
                .addParams("amount", mTvOrderMoney.getText().toString())
                .addParams("paymentType", type)
                .url(ApiConfig.REFOUND_ORDER).build().execute(new ResponseCallback<BaseBean>(this) {

            @Override
            public void onResponse(BaseBean response, int id) {
                if (response.isSuccess()) {
                    ToastUtil.showToast("退款成功");
                    Intent backIntent = new Intent(PaymentDetailActivity.this, BackPayActivity.class);
                    backIntent.putExtra("refundAmount", mTvOrderMoney.getText().toString());
                    backIntent.putExtra("outTradeNo", mTvOrderBatch.getText().toString());
                    backIntent.putExtra("type", mTvOrderPayType.getText().toString());
                    startActivity(backIntent);
                    finishActivity();
                } else {
                    ToastUtil.showToast("退单失败");
                }
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
