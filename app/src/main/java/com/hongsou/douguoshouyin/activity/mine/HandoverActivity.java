package com.hongsou.douguoshouyin.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.activity.login.LoginActivity;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.http.ResponseCallback;
import com.hongsou.douguoshouyin.javabean.HandoverDetailBean;
import com.hongsou.douguoshouyin.javabean.RootBean;
import com.hongsou.douguoshouyin.tool.DateUtils;
import com.hongsou.douguoshouyin.tool.Global;
import com.hongsou.douguoshouyin.tool.ToastUtil;
import com.hongsou.douguoshouyin.tool.bluetooth.BluetoothPrinterUtil;
import com.hongsou.douguoshouyin.views.CustomDatePicker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author lpc
 *         <p>
 * @copyright 鸿搜网络公司 版权所有
 * <p>
 * @date 2018/7/24
 * <p>
 * @desc 交班页面
 */
public class HandoverActivity extends BaseActivity {


    @BindView(R.id.tv_handover_collect_money)
    TextView mTvHandoverCollectMoney;
    @BindView(R.id.tv_hanover_order_count)
    TextView mTvHanoverOrderCount;
    @BindView(R.id.tv_handover_back_money)
    TextView mTvHandoverBackMoney;
    @BindView(R.id.tv_handover_back_count)
    TextView mTvHandoverBackCount;
    @BindView(R.id.tv_handover_alipay)
    TextView mTvHandoverAlipay;
    @BindView(R.id.tv_handover_wechat)
    TextView mTvHandoverWechat;
    @BindView(R.id.tv_handover_money)
    TextView mTvHandoverMoney;
    @BindView(R.id.tv_handover_bank_card)
    TextView mTvHandoverBankCard;
    @BindView(R.id.tv_handover_start_time)
    TextView mTvHandoverStartTime;
    @BindView(R.id.tv_handover_end_time)
    TextView mTvHandoverEndTime;
    @BindView(R.id.btn_hanover_submit)
    Button mBtnHanoverSubmit;

    /**
     * 现在时间
     */
    private String now;
    /**
     * 开始结束时间控制器
     */
    private CustomDatePicker customDatePickerStart, customDatePickerEnd;
    private String tradingTime;
    private String endTime;

    @Override
    public int initLayout() {
        return R.layout.module_activity_handover;
    }

    @Override
    protected void init() {
        initView();
        initData();
    }

    @Override
    public void initView() {
        //获取当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        now = sdf.format(new Date());
        //初始化起始时间
//        initDateStartPicker(now);
        //初始化结束时间
        initDateEndPicker("2010-01-01 00:00", now);
    }

    /**
     * @desc 初始化开始时间
     * @anthor lpc
     * @date: 2018/7/27
     * @param
     * @return
     */

    /* 交班开始时间不能选择
    private void initDateStartPicker(String endTime) {
        mTvHandoverStartTime.setText(endTime);
        customDatePickerStart = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                mTvHandoverStartTime.setText(time);
            }
        }, "2010-01-01 00:00", endTime);
        customDatePickerStart.showSpecificTime(true);
        customDatePickerStart.setIsLoop(true);
    }*/

    /**
     * @desc 初始化结束时间选择器
     * @anthor lpc
     * @date: 2018/7/27
     * @param starTime 开始期限
     * @param endTime 最后期限 现在
     * @return 
     */
    private void initDateEndPicker(String starTime, String endTime) {
        customDatePickerEnd = null;
        mTvHandoverEndTime.setText(endTime);
        customDatePickerEnd = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                mTvHandoverEndTime.setText(time);
            }
        }, starTime, endTime);
        customDatePickerEnd.showSpecificTime(true);
        customDatePickerEnd.setIsLoop(true);
    }

    @Override
    public void initData() {
        HttpFactory.get().url(ApiConfig.GET_SHIFT_DETAILS)
                .addParams("shopNumber", getShopNumber())
                .addParams("clerkNumber", Global.getSpGlobalUtil().getClerkNumber())
                .addParams("endTime", DateUtils.getStringDateNotSS())
                .build()
                .execute(new ResponseCallback<RootBean<HandoverDetailBean>>(this) {
                    @Override
                    public void onResponse(RootBean<HandoverDetailBean> response, int id) {
                        if (response.isSuccess()) {
                            HandoverDetailBean data = response.getData();
                            renderView(data);
                        }else {
                            ToastUtil.showToast(response.getMsg());
                        }
                    }
                });
    }

    /**
     * @desc 初始化页面数据
     * @anthor lpc
     * @date: 2018/7/24
     * @param data 数据源
     */
    private void renderView(HandoverDetailBean data) {
        tradingTime = DateUtils.formatToString(data.getTradingTime());
        endTime = DateUtils.formatToStringNotSS(data.getEndTime());
        // 实收金额
        mTvHandoverCollectMoney.setText(data.getAmountCollected());
        // 订单笔数
        mTvHanoverOrderCount.setText(data.getCollectedCount());
        // 退款金额
        mTvHandoverBackMoney.setText(data.getRefoundAmount());
        // 退款笔数
        mTvHandoverBackCount.setText(data.getRefoundCount());
        // 支付宝支付金额
        mTvHandoverAlipay.setText(data.getAliAmount());
        // 微信支付金额
        mTvHandoverWechat.setText(data.getWeChatAmount());
        // 现金支付金额
        mTvHandoverMoney.setText(data.getCashAmount());
        // 银行卡支付金额
        mTvHandoverBankCard.setText(data.getBankAmount());
        // 交班开始时间
        mTvHandoverStartTime.setText(tradingTime);
    }


    @OnClick({R.id.tv_handover_start_time, R.id.tv_handover_end_time, R.id.btn_hanover_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_handover_start_time:
//                // 日期格式为yyyy-MM-dd HH:mm
//                customDatePickerStart.show(mTvHandoverStartTime.getText().toString());
                break;
            case R.id.tv_handover_end_time:
                // 日期格式为yyyy-MM-dd HH:mm
                customDatePickerEnd.show(mTvHandoverEndTime.getText().toString());
                break;
            case R.id.btn_hanover_submit:
                //交班打印小票接口  TODO  只打印？  包括查询？
                //判断起始时间不得大于结束时间
                if (mTvHandoverStartTime.getText().toString().compareTo(mTvHandoverEndTime.getText().toString()) > 0) {
                    ToastUtil.showToast("起始时间不得大于结束时间");
                } else {
                    handoverSubmit();
                }
                break;
            default:
                break;
        }
    }

    /**
     * @param
     * @return
     * @desc 交班提交接口
     * @anthor lpc
     * @date: 2018/7/24
     */
    private void handoverSubmit() {
        HttpFactory.post().url(ApiConfig.INSERT_SHIFT)
                .addParams("shopNumber", getShopNumber())
                .addParams("clerkNumber", Global.getSpGlobalUtil().getClerkNumber())
                .addParams("endTime", mTvHandoverEndTime.getText().toString())
                .build()
                .execute(new ResponseCallback<RootBean>(this) {
                    @Override
                    public void onResponse(RootBean response, int id) {
                        if (response.isSuccess()) {
                            ToastUtil.showToast("交班成功");
                            getHandoverInfo();
                        }else {
                            ToastUtil.showToast(response.getMsg());
                        }
                    }
                });
    }

    /**
     * @desc 查询已交班数据
     * @anthor lpc
     * @date: 2018/8/4
     */
    public void getHandoverInfo() {
        HttpFactory.get().url(ApiConfig.GET_SHIFT_DETAILS)
                .addParams("shopNumber", getShopNumber())
                .addParams("clerkNumber", Global.getSpGlobalUtil().getClerkNumber())
                .addParams("tradingTime", tradingTime)
                .addParams("endTime", endTime)
                .build()
                .execute(new ResponseCallback<RootBean<HandoverDetailBean>>(this) {
                    @Override
                    public void onResponse(RootBean<HandoverDetailBean> response, int id) {
                        if (response.isSuccess()) {
                            HandoverDetailBean data = response.getData();
                            if (Global.getSpGlobalUtil().getHandoverPrintSwitch()){
                                BluetoothPrinterUtil util = new BluetoothPrinterUtil.Builder()
                                        .setContent(data)
                                        .setCount(Global.getSpGlobalUtil().getHandoverPrintCount())
                                        .setType(BluetoothPrinterUtil.Print.HANDOVER)
                                        .build();
                                util.startPrint();
                            }
                            startActivity(new Intent(HandoverActivity.this, LoginActivity.class));
                            Global.logout();
                        }else {
                            ToastUtil.showToast(response.getMsg());
                        }
                    }
                });
    }

    //===============================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick(R.id.btn_hanover_submit)
    public void onViewClicked() {
    }
}
