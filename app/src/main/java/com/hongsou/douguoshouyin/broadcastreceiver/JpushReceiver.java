package com.hongsou.douguoshouyin.broadcastreceiver;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.hongsou.douguoshouyin.activity.turnover.OrderDetailActivity;
import com.hongsou.douguoshouyin.base.BaseApplication;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.javabean.OrderDetailBean;
import com.hongsou.douguoshouyin.tool.Global;
import com.hongsou.douguoshouyin.tool.GsonUtil;
import com.hongsou.douguoshouyin.tool.MscSpeechUtils;
import com.hongsou.douguoshouyin.tool.ToastUtil;
import com.hongsou.douguoshouyin.tool.bluetooth.BluetoothPrinterUtil;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import cn.jpush.android.api.JPushInterface;
import okhttp3.Call;


/**
 * Created by zlq on 2017/12/20.
 * <p>
 * 用于接受极光推送的广播
 */

public class JpushReceiver extends BroadcastReceiver {
    private static final String TAG = "MyReceiver";

    private NotificationManager nm;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (null == nm) {
            nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        }

        Bundle bundle = intent.getExtras();
        Log.i(TAG, "onReceive - " + intent.getAction() + ", extras: " + bundle);
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        Log.i(TAG, "onReceive - " + intent.getAction() + ", extras: " + extras);

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {

            Log.d(TAG, "JPush用户注册成功");

        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "接受到推送下来的自定义消息");
            Log.e(TAG, "onReceive: aaaaaaaa" + extras);

            //extras的数据   支付
            // {
            //	"money": "0.01",
            //	"tradeType": "微信支付",
            //	"outTradeNo": "201805121256356710576",
            //	"storeName": "",
            //	"date": "2018-05-12 13:03:38",
            //	"clientName": ""
            //}


        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "接受到推送下来的通知");
            //处理支付推送的消息  TODO 区分开单还是扫码点餐  扫码点餐的话不跳转
            if (TextUtils.isEmpty(extras)) {
                ToastUtil.showToast("数据异常");
            } else {
                PayOnLineSuccessBean paySuccessBean = GsonUtil.GsonToBean(extras, PayOnLineSuccessBean.class);
                if ("scan".equals(paySuccessBean.getFlag())){
                    //扫码点餐来的
                    if (Global.getSpUserUtil().getSpeechVoice()) {
                        if (Global.getSpUserUtil().getSpeechVoice()) {
                            MscSpeechUtils.speech("您有一条新的订单", BaseApplication.getAppContext());
                        }
                    }
                    getOrderInfo(paySuccessBean.getBatch());
                }else {
                    // app内的支付
                    if (Global.getSpUserUtil().getSpeechVoice()) {
                        MscSpeechUtils.speech(paySuccessBean.getTradeType() + "收款到账"
                                + paySuccessBean.getMoney() + "元", BaseApplication.getAppContext());
                    }
                    successAct(context, paySuccessBean);
                }
            }
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Log.d(TAG, "用户点击打开了通知");
            PayOnLineSuccessBean paySuccessBean = GsonUtil.GsonToBean(extras, PayOnLineSuccessBean.class);

            if ("scan".equals(paySuccessBean.getFlag())){
                //点击后跳转支付成功页面
                Intent intent1 = new Intent(context, OrderDetailActivity.class);
                intent1.putExtra("batch", paySuccessBean.getBatch());
                intent1.putExtra("print", "print");
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent1);
            }
        }

    }

    /**
     * 启动收款成功的界面
     */
    private void successAct(Context context, PayOnLineSuccessBean paySuccessBean) {
        EventBus.getDefault().post(paySuccessBean);
    }

    /**
     * @desc 获取订单详情并打印
     * @anthor lpc
     * @date: 2018/8/14
     */
    private void getOrderInfo(String batch) {
        HttpFactory.get().url(ApiConfig.GET_ORDER_DETAILS)
                .addParams("shopNumber", Global.getSpGlobalUtil().getShopNumber())
                .addParams("batch", batch)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtil.showError();
            }

            @Override
            public void onResponse(String response, int id) {
                OrderDetailBean mOrderDetailBean = new Gson().fromJson(response, OrderDetailBean.class);
                if (mOrderDetailBean.isSuccess()) {
                    if (Global.getSpUserUtil().getOrderPrintSwitch()) {
                        BluetoothPrinterUtil printerUtil = new BluetoothPrinterUtil.Builder()
                                .setContent(mOrderDetailBean)
                                .setCount(Global.getSpUserUtil().getOrderPrintCount())
                                .setType(BluetoothPrinterUtil.Print.ORDER)
                                .build();
                        printerUtil.startPrint();
                    }
                }
            }
        });
    }
}
