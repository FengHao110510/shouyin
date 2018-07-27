package com.hongsou.douguoshouyin.activity.payfor.payfor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.activity.MainActivity;
import com.hongsou.douguoshouyin.base.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 退款成功页面
 */
public class BackPayActivity extends BaseActivity {


    @BindView(R.id.tv_payfor_backpay_tuikuanjine)
    TextView tvPayforBackpayTuikuanjine;
    @BindView(R.id.tv_payfor_backpay_dingdanhao)
    TextView tvPayforBackpayDingdanhao;
    @BindView(R.id.tv_payfor_backpay_jiaoyishijian)
    TextView tvPayforBackpayJiaoyishijian;
    @BindView(R.id.tv_payfor_backpay_jiaoyijine)
    TextView tvPayforBackpayJiaoyijine;
    @BindView(R.id.tv_payfor_backpay_zhifufangshi)
    TextView tvPayforBackpayZhifufangshi;
    @BindView(R.id.tv_payfor_backpay_zhifuzhuangtai)
    TextView tvPayforBackpayZhifuzhuangtai;
    @BindView(R.id.bt_payfor_backpay_tuikuan)
    Button btPayforBackpayTuikuan;

    @Override
    public int initLayout() {
        return R.layout.module_activity_payfor_back_pay;
    }

    @Override
    protected void init() {
        initView();
        initData();
        initBack();
        initTitle("退款成功");
    }


    @Override
    public void initView() {
    //TODO 初始化数据之前退款接口传的？
        //获取当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String now = sdf.format(new Date());

        String amount = getIntent().getStringExtra("refundAmount");
        String batch = getIntent().getStringExtra("outTradeNo");
        String type = getIntent().getStringExtra("type");
        tvPayforBackpayDingdanhao.setText(batch);
        tvPayforBackpayJiaoyijine.setText(amount);
        tvPayforBackpayJiaoyishijian.setText(now);
        tvPayforBackpayTuikuanjine.setText(amount);
        tvPayforBackpayZhifufangshi.setText(type);
        tvPayforBackpayZhifuzhuangtai.setText("退款成功");
    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.bt_payfor_backpay_tuikuan)
    public void onViewClicked() {
        toMain();

    }

    private void toMain() {
        startActivity(new Intent(BackPayActivity.this, MainActivity.class));
        finishActivity();
    }

    @Override
    public void initBack() {
       toMain();
    }

    @Override
    public void onBackPressed() {
        toMain();

    }

    //---=================================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
