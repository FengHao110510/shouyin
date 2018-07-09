package com.hongsou.douguoshouyin.activity.turnover;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.http.Apiconfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.tool.ToastUtil;
import okhttp3.Call;
/**
 * 订单详情页面
 */
public class OrderDetailActivity extends BaseActivity {


    @BindView(R.id.tv_turnover_orderdetail_xianshijine)
    TextView tvTurnoverOrderdetailXianshijine;
    @BindView(R.id.tv_turnover_orderdetail_dingdanhao)
    TextView tvTurnoverOrderdetailDingdanhao;
    @BindView(R.id.tv_turnover_orderdetail_leixing)
    TextView tvTurnoverOrderdetailLeixing;
    @BindView(R.id.tv_turnover_orderdetail_dingdanzhuangtai)
    TextView tvTurnoverOrderdetailDingdanzhuangtai;
    @BindView(R.id.tv_turnover_orderdetail_caozuoren)
    TextView tvTurnoverOrderdetailCaozuoren;
    @BindView(R.id.tv_turnover_orderdetail_xiadanshijian)
    TextView tvTurnoverOrderdetailXiadanshijian;
    @BindView(R.id.tv_turnover_orderdetail_yingshou)
    TextView tvTurnoverOrderdetailYingshou;
    @BindView(R.id.tv_turnover_orderdetail_zhaoling)
    TextView tvTurnoverOrderdetailZhaoling;
    @BindView(R.id.tv_turnover_orderdetail_shishoujine)
    TextView tvTurnoverOrderdetailShishoujine;
    @BindView(R.id.tv_turnover_orderdetail_tuikuan)
    TextView tvTurnoverOrderdetailTuikuan;
    @BindView(R.id.tv_turnover_orderdetail_dayinxiaopiao)
    TextView tvTurnoverOrderdetailDayinxiaopiao;
    @BindView(R.id.tv_turnover_orderdetail_dingdanjine)
    TextView tvTurnoverOrderdetailDingdanjine;
    @BindView(R.id.tv_turnover_orderdetail_youhuijine)
    TextView tvTurnoverOrderdetailYouhuijine;
    @BindView(R.id.tv_turnover_orderdetail_shoukuanjine)
    TextView tvTurnoverOrderdetailShoukuanjine;


    Dialog dialog;


    @Override
    public int initLayout() {
        return R.layout.module_activity_turnover_order_detail;
    }

    @Override
    protected void init() {
        initView();
        initData();
        initBack();
        initTitle("订单详情页");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        detailes();
    }

    /**
     * 查询订单详情接口
     */
    private void detailes() {
        showLoadingDialog();
        HttpFactory.post().url(Apiconfig.orderDetails).addParams("", "").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoadingDialog();
            }

            @Override
            public void onResponse(String response, int id) {
                dismissLoadingDialog();

                //TODO 判断是不是已退款的 是的话隐藏退款按钮
                tvTurnoverOrderdetailTuikuan.setVisibility(View.GONE);

                tvTurnoverOrderdetailXianshijine.setText("");
                tvTurnoverOrderdetailDingdanhao.setText("");
                tvTurnoverOrderdetailLeixing.setText("");
                tvTurnoverOrderdetailDingdanzhuangtai.setText("");
                tvTurnoverOrderdetailCaozuoren.setText("");
                tvTurnoverOrderdetailXiadanshijian.setText("");
                tvTurnoverOrderdetailYingshou.setText("");
                tvTurnoverOrderdetailZhaoling.setText("");
                tvTurnoverOrderdetailShishoujine.setText("");
                tvTurnoverOrderdetailDingdanjine.setText("");
                tvTurnoverOrderdetailYouhuijine.setText("");
                tvTurnoverOrderdetailShoukuanjine.setText("");


            }
        });
    }

    //弹框输入密码
    private void showTuikuanDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.module_dialog_tuikuan, null);
        TextView tv_dialog_tuikuan_title = view.findViewById(R.id.tv_dialog_tuikuan_title);
        EditText et_dialog_tuikuan_content = view.findViewById(R.id.et_dialog_tuikuan_content);
        TextView tv_dialog_tuikuan_yes = view.findViewById(R.id.tv_dialog_tuikuan_yes);
        TextView tv_dialog_tuikuan_cancle = view.findViewById(R.id.tv_dialog_tuikuan_cancle);
        tv_dialog_tuikuan_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //判断密码是否正确 走退款接口   TODO
                tuikuan();
            }
        });
        tv_dialog_tuikuan_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        Display display = this.getWindowManager().getDefaultDisplay();
        int w = display.getWidth();
        int h = display.getHeight();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(w * 4 / 5, h * 2 / 7);
        dialog = new Dialog(this, R.style.CommonDialog);
        dialog.addContentView(view, params);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();


    }

    //退款接口
    private void tuikuan() {
        showLoadingDialog();
        HttpFactory.post().url(Apiconfig.tuikuan).addParams("", "").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoadingDialog();
                ToastUtil.showToast();
            }

            @Override
            public void onResponse(String response, int id) {
                dismissLoadingDialog();

            }
        });
    }


    @OnClick({R.id.tv_turnover_orderdetail_tuikuan, R.id.tv_turnover_orderdetail_dayinxiaopiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_turnover_orderdetail_tuikuan:
                showTuikuanDialog();
                break;
            case R.id.tv_turnover_orderdetail_dayinxiaopiao:
                break;
        }
    }


    //======================================================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
