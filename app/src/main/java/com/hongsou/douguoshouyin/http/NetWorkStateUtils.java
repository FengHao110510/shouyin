package com.hongsou.douguoshouyin.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.hongsou.douguoshouyin.base.BaseApplication;

/**
 * Created by Administrator on 2018/7/2.
 */

public class NetWorkStateUtils {

    public static boolean isNetConnected() {
        boolean isNetConnected;
        ConnectivityManager connManager = (ConnectivityManager) BaseApplication.getAppContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connManager.getActiveNetworkInfo();
        isNetConnected = info != null && info.isAvailable();
        return isNetConnected;
    }
}
