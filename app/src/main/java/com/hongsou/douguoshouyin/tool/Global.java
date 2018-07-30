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
import android.util.Log;
import android.view.View;

import com.hongsou.douguoshouyin.base.BaseApplication;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

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
                    BaseApplication.getAppContext(), "hong_sou_global");
        return mSpGlobalUtil;
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





    public static final String PREFERENCES_FILENAME = "com.lvrenyang.drawer.PREFERENCES_FILENAME";

    public static final String PREFERENCES_IPADDRESS = "com.lvrenyang.drawer.PREFERENCES_IPADDRESS";
    public static final String PREFERENCES_PORTNUMBER = "com.lvrenyang.drawer.PREFERENCES_PORTNUMBER";
    public static final String PREFERENCES_BTADDRESS = "com.lvrenyang.drawer.PREFERENCES_BTADDRESS";

    public static final int MSG_WORKTHREAD_HANDLER_CONNECTNET = 100000;
    public static final int MSG_WORKTHREAD_SEND_CONNECTNETRESULT = 100001;
    public static final int MSG_WORKTHREAD_HANDLER_OPENDRAWERNET = 100002;
    public static final int MSG_WORKTHREAD_SEND_OPENDRAWERNETRESULT = 100003;
    public static final int MSG_WORKTHREAD_HANDLER_CONNECTBT = 100004;
    public static final int MSG_WORKTHREAD_SEND_CONNECTBTRESULT = 100005;
    public static final int MSG_WORKTHREAD_HANDLER_OPENDRAWERBT = 100006;
    public static final int MSG_WORKTHREAD_SEND_OPENDRAWERBTRESULT = 100007;
    public static final int MSG_WORKTHREAD_HANDLER_STRINGINFOBT = 100008;
    public static final int MSG_WORKTHREAD_SEND_STRINGINFOBTRESULT = 100009;
    public static final int MSG_WORKTHREAD_HANDLER_STRINGINFONET = 100010;
    public static final int MSG_WORKTHREAD_SEND_STRINGINFONETRESULT = 100011;
    public static final int MSG_WORKTHREAD_HANDLER_SETKEYBT = 100012;
    public static final int MSG_WORKTHREAD_SEND_SETKEYBTRESULT = 100013;
    public static final int MSG_WORKTHREAD_HANDLER_SETKEYNET = 100014;
    public static final int MSG_WORKTHREAD_SEND_SETKEYNETRESULT = 100015;
    public static final int MSG_WORKTHREAD_HANDLER_SETBTPARABT = 100016;
    public static final int MSG_WORKTHREAD_SEND_SETBTPARABTRESULT = 100017;
    public static final int MSG_WORKTHREAD_HANDLER_SETBTPARANET = 100018;
    public static final int MSG_WORKTHREAD_SEND_SETBTPARANETRESULT = 100019;
    public static final int MSG_WORKTHREAD_HANDLER_SETIPPARABT = 100020;
    public static final int MSG_WORKTHREAD_SEND_SETIPPARABTRESULT = 100021;
    public static final int MSG_WORKTHREAD_HANDLER_SETIPPARANET = 100022;
    public static final int MSG_WORKTHREAD_SEND_SETIPPARANETRESULT = 100023;
    public static final int MSG_WORKTHREAD_HANDLER_SETWIFIPARABT = 100024;
    public static final int MSG_WORKTHREAD_SEND_SETWIFIPARABTRESULT = 100025;
    public static final int MSG_WORKTHREAD_HANDLER_SETWIFIPARANET = 100026;
    public static final int MSG_WORKTHREAD_SEND_SETWIFIPARANETRESULT = 100027;
    public static final int MSG_WORKTHREAD_HANDLER_CONNECTUSB = 100028;
    public static final int MSG_WORKTHREAD_SEND_CONNECTUSBRESULT = 100029;

    // Bundle data使用
    public static final String BYTESPARA1 = "bytespara1";
    public static final String BYTESPARA2 = "bytespara2";
    public static final String BYTESPARA3 = "bytespara3";
    public static final String BYTESPARA4 = "bytespara4";
    public static final String INTPARA1 = "intpara1";
    public static final String INTPARA2 = "intpara2";
    public static final String INTPARA3 = "intpara3";
    public static final String INTPARA4 = "intpara4";
    public static final String INTPARA5 = "intpara5";
    public static final String INTPARA6 = "intpara6";
    public static final String STRPARA1 = "strpara1";
    public static final String STRPARA2 = "strpara2";
    public static final String STRPARA3 = "strpara3";
    public static final String STRPARA4 = "strpara4";
    public static final String OBJECT1 = "object1";
    public static final String OBJECT2 = "object2";
    public static final String OBJECT3 = "object3";
    public static final String OBJECT4 = "object4";
    public static final String PARCE1 = "parce1";
    public static final String PARCE2 = "parce2";
    public static final String SERIAL1 = "serial1";
    public static final String SERIAL2 = "serial2";

    public static final int CMD_POS_WRITE = 100100;
    public static final int CMD_POS_WRITERESULT = 100101;
    public static final int CMD_POS_READ = 100102;
    public static final int CMD_POS_READRESULT = 100103;
    public static final int CMD_POS_SETKEY = 100104;
    public static final int CMD_POS_SETKEYRESULT = 100105;
    public static final int CMD_POS_CHECKKEY = 100106;
    public static final int CMD_POS_CHECKKEYRESULT = 100107;
    public static final int CMD_POS_PRINTPICTURE = 100108;
    public static final int CMD_POS_PRINTPICTURERESULT = 100109;
    public static final int CMD_POS_STEXTOUT = 100110;
    public static final int CMD_POS_STEXTOUTRESULT = 100111;
    public static final int CMD_POS_SALIGN = 100112;
    public static final int CMD_POS_SALIGNRESULT = 100113;
    public static final int CMD_POS_SETLINEHEIGHT = 100114;
    public static final int CMD_POS_SETLINEHEIGHTRESULT = 100115;
    public static final int CMD_POS_SETRIGHTSPACE = 100116;
    public static final int CMD_POS_SETRIGHTSPACERESULT = 100117;
    public static final int CMD_POS_SETCHARSETANDCODEPAGE = 100118;
    public static final int CMD_POS_SETCHARSETANDCODEPAGERESULT = 100119;
    public static final int CMD_POS_SETBARCODE = 100120;
    public static final int CMD_POS_SETBARCODERESULT = 100121;
    public static final int CMD_POS_SETQRCODE = 100122;
    public static final int CMD_POS_SETQRCODERESULT = 100123;
    public static final int CMD_EPSON_SETQRCODE = 100123;
    public static final int CMD_EPSON_SETQRCODERESULT = 100124;
    public static final int MSG_ALLTHREAD_READY = 100300;
    public static final int MSG_PAUSE_HEARTBEAT = 100301;
    public static final int MSG_RESUME_HEARTBEAT = 100302;
    public static final int MSG_ON_RECIVE = 100303;
    public static final int CMD_WRITE = 100304;
    public static final int CMD_WRITERESULT = 100305;

}