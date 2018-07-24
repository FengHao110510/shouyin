package com.hongsou.douguoshouyin.broadcastreceiver;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.hongsou.douguoshouyin.tool.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import cn.jpush.android.api.JPushInterface;



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

            //处理支付推送的消息
//            successAct(context, extras);

        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "接受到推送下来的通知");

        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Log.d(TAG, "用户点击打开了通知");

        }

    }

//    /**
//     * 启动收款成功的界面
//     */
//    private QrPayModel qrPayModel;
//    private QrPayModel.ContentBean contentBean;
//
//    private void successAct(Context context, String jsonStr) {
//        Log.e(TAG, "successAct: chengasd");
//        if (TextUtils.isEmpty(jsonStr)) {
//            ToastUtil.showToast("数据异常");
//            return;
//
//        }else if(jsonStr.contains("takeawayChanne")){
//            WaimaiOrderTuisongBean waimaiOrderTuisongBean = GsonTools.GsonToBean(jsonStr,WaimaiOrderTuisongBean.class);
//            if (waimaiOrderTuisongBean == null) {
//                waimaiOrderTuisongBean = new WaimaiOrderTuisongBean();
//            }
//            Log.e(TAG, "successAct: waimai"+jsonStr );
//            EventBus.getDefault().post(waimaiOrderTuisongBean);
//
//        }else {
//            Log.e(TAG, "successAct:支付成功？？？？？？ ");
//            PaySuccessBean paySuccessBean = GsonTools.GsonToBean(jsonStr, PaySuccessBean.class);
//
//            if (paySuccessBean == null) {
//                paySuccessBean = new PaySuccessBean();
//            }
//
//            EventBus.getDefault().post(paySuccessBean);
//
//        }
//
//
//    }


}
