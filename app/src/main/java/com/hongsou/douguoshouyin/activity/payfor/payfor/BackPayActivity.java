package com.hongsou.douguoshouyin.activity.payfor.payfor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.activity.MainActivity;
import com.hongsou.douguoshouyin.base.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @BindView(R.id.tv_titlebar_finish_back)
    TextView tvTitlebarFinishBack;

    @Override
    public int initLayout() {
        return R.layout.module_activity_payfor_back_pay;
    }

    @Override
    protected void init() {
        initView();
        initData();
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
        setIconFont(new TextView[]{tvTitlebarFinishBack});
        tvTitlebarFinishBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toMain();
            }
        });


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
