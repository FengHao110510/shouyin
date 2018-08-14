package com.hongsou.douguoshouyin.tool;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.hongsou.douguoshouyin.base.BaseApplication;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import cn.jpush.android.api.JPushInterface;

/**
 * 全局
 */

public class Global {
    private static final String TAG = "Global";

    private static SharePreferenceUserUtil mSpUtil;
    private static SharePreferenceGlobalUtil mSpGlobalUtil;
    public static boolean DEBUG = true;
    private static Boolean isLogin = null;
    private static Activity at;

    public static void init(Context context) {

    }

    public static String getLanguage(Context context) {
        return context.getResources().getConfiguration().locale.toString();
    }

    public static synchronized SharePreferenceGlobalUtil getSpGlobalUtil() {
        if (mSpGlobalUtil == null)
            mSpGlobalUtil = new SharePreferenceGlobalUtil(
                    BaseApplication.getAppContext(), "hong_sou_sy_global");
        return mSpGlobalUtil;
    }

    public static synchronized SharePreferenceUserUtil getSpUserUtil() {
        if (mSpUtil == null) {
            SharePreferenceGlobalUtil spc = getSpGlobalUtil();
            if (!TextUtils.isEmpty(spc.getClerkNumber())) {
                mSpUtil = new SharePreferenceUserUtil(
                        BaseApplication.getAppContext(), "hsou_shouyin_user_"
                        + spc.getClerkNumber());
            } else {
                mSpUtil = new SharePreferenceUserUtil(
                        BaseApplication.getAppContext(), "hsou_shouyin_user");
            }
        }
        return mSpUtil;
    }

    /**
     * 清除登录信息
     */
    public static void logout() {
        Global.getSpGlobalUtil().setAliCode("");
        Global.getSpGlobalUtil().setWecharCode("");
        Global.getSpGlobalUtil().setClerkName("");
        Global.getSpGlobalUtil().setClerkNumber("");
        Global.getSpGlobalUtil().setPaymentUser("");
        Global.getSpGlobalUtil().setShopNumber("");
        Global.getSpGlobalUtil().setCode("");
        //取消别名
        JPushInterface.setAlias(BaseApplication.getAppContext(), 1, "");
        mSpUtil = null;
    }

    /**
     * 获取当前版本号
     *
     * @return
     */
    public static int getVersionCode() {
        int versionCode = 0;
        try {

            versionCode = BaseApplication
                    .getAppContext()
                    .getPackageManager()
                    .getPackageInfo(
                            BaseApplication.getAppContext().getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 获取当前版本名
     *
     * @return
     */
    public static String getVersionName() {
        String versionName = "0";
        try {

            versionName = BaseApplication
                    .getAppContext()
                    .getPackageManager()
                    .getPackageInfo(
                            BaseApplication.getAppContext().getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }


    /**
     * 拨打电话
     *
     * @param context
     * @param phonenumber
     */
    public static void takePhone(Context context, String phonenumber) {
        context.startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
                + phonenumber)));
    }

    /**
     * 获取view的高度/宽度
     *
     * @param view
     * @param isHeight
     * @return
     */
    public static int getViewHeight(View view, boolean isHeight) {
        int result;
        if (view == null) return 0;
        if (isHeight) {
            int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            view.measure(h, 0);
            result = view.getMeasuredHeight();
        } else {
            int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            view.measure(0, w);
            result = view.getMeasuredWidth();
        }
        return result;
    }

    public static String getIPAddress(Context context) {
        NetworkInfo info = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {//当前使用2G/3G/4G网络
                try {
                    //Enumeration<NetworkInterface> en=NetworkInterface.getNetworkInterfaces();
                    for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                        NetworkInterface intf = en.nextElement();
                        for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                            InetAddress inetAddress = enumIpAddr.nextElement();
                            if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                                Log.e(TAG, "getIPAddress: " + inetAddress.getHostAddress());
                                return inetAddress.getHostAddress();
                            }
                        }
                    }
                } catch (SocketException e) {
                    e.printStackTrace();
                }

            } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {//当前使用无线网络
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                String ipAddress = intIP2StringIP(wifiInfo.getIpAddress());//得到IPV4地址
                Log.e(TAG, "getIPAddress: " + ipAddress);
                return ipAddress;
            }
        } else {
            //当前无网络连接,请在设置中打开网络
            ToastUtil.showToast("当前无网络连接,请在设置中打开网络");
        }
        return null;
    }

    /**
     * 将得到的int类型的IP转换为String类型
     *
     * @param ip
     * @return
     */
    public static String intIP2StringIP(int ip) {
        return (ip & 0xFF) + "." +
                ((ip >> 8) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                (ip >> 24 & 0xFF);
    }

}