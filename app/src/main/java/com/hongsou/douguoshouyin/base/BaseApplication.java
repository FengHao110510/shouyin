package com.hongsou.douguoshouyin.base;

import android.bluetooth.BluetoothSocket;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.gprinter.aidl.GpService;
import com.gprinter.service.GpPrintService;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.greendao.gen.DaoMaster;
import com.hongsou.greendao.gen.DaoSession;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.litesuits.orm.LiteOrm;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * 文件描述：new！！com.example.administrator.myapplication.app.base
 * 作者：fh
 * 创建时间：2018/6/13
 * 更改时间：2018/6/13
 * 版本号：1
 * 用途：
 */


public class BaseApplication extends MultiDexApplication {

    public static BaseApplication app;


    /**
     * 蓝牙对象
     */
    public BluetoothSocket socket = null;
    public List<BluetoothSocket> socketArray = new ArrayList<>();

    /***寄存整个应用Activity**/
    private final Stack<AppCompatActivity> activitys = new Stack<AppCompatActivity>();

    public static LiteOrm liteOrm;
    private DaoMaster.DevOpenHelper devOpenHelper;
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
   
    public static GpService mGpService;
    
    @Override
    public void onCreate() {
        super.onCreate();

        initApp();
        settingOkHttp();
        settingOrm();
        initGreenDao();
        initKedaxunfei();
        connection();
        //初始化全局异常捕获
//        CrashHandler.getInstance().init(this);

    }

    /**
     * 科大讯飞
     */
    private void initKedaxunfei() {
        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=" + ApiConfig.KEDAXUNFEI_APPID);
    }

    /**
     * @desc 初始化数据库
     * @anthor lpc
     * @date: 2018/7/17
     */
    private void settingOrm() {
        if (liteOrm == null) {
            liteOrm = LiteOrm.newSingleInstance(this, "shouyin.db");
        }
        // open the log
        liteOrm.setDebugged(true);
    }


    private void initApp() {
        app = this;
    }

    public static synchronized BaseApplication getInstance() {
        return app;
    }

    public static BaseApplication getApplication() {
        return app;
    }

    /**
     * @desc 初始化GreenDao
     * @anthor lpc
     * @date: 2018/7/19
     */
    private void initGreenDao() {
        devOpenHelper = new DaoMaster.DevOpenHelper(this, "shouyinapp.db", null);
        daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    /**
     * @desc 绑定佳博打印机服务
     * @anthor lpc
     * @date: 2018/8/10
     */
    private void connection() {
        PrinterServiceConnection conn = new PrinterServiceConnection();
        Intent intent = new Intent(this, GpPrintService.class);
        // bindService
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    /**
     * @desc 打印机服务连接监听
     * @anthor lpc
     * @date: 2018/8/11
     */
    class PrinterServiceConnection implements ServiceConnection {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("lpc", "onServiceDisconnected() called");
            mGpService = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("lpc", "onServiceConnected 打开");
            mGpService = GpService.Stub.asInterface(service);
        }
    }
    
    /**
     * 将Activity压入Application栈
     *
     * @param task 将要压入栈的Activity对象
     */
    public void pushTask(AppCompatActivity task) {
        activitys.push(task);
    }

    /**
     * 将传入的Activity对象从栈中移除
     *
     * @param task
     */
    public void removeTask(AppCompatActivity task) {
        if (task != null) {
            activitys.remove(task);
        }
    }

//    //设置 Header 为 MaterialHeader普通    BezierCircleHeader水滴  DropBoxHeader盒子
//        smTurnoverOrder.setRefreshHeader(new BezierCircleHeader(getActivity()));
//    //设置 Footer 为 经典样式  BallPulseFooter三个红点  丑   FalsifyFooter没效果
//        smTurnoverOrder.setRefreshFooter(new ClassicsFooter(getActivity()));

    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.color_base_yellow, android.R.color.white);//全局设置主题颜色
                return new MaterialHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(16);
            }
        });
    }

    /**
     * 根据指定位置从栈中移除Activity
     *
     * @param taskIndex Activity栈索引
     */
    public void removeTask(int taskIndex) {
        if (activitys.size() > taskIndex) {
            activitys.remove(taskIndex);
        }

    }

    /**
     * 将栈中Activity移除至栈顶
     */
    public void removeToTop() {
        int end = activitys.size();
        int start = 1;
        for (int i = end - 1; i >= start; i--) {
            if (!activitys.get(i).isFinishing()) {
                activitys.get(i).finish();
            }
        }
    }

    /**
     * 移除全部（用于整个应用退出）
     */
    public void removeAll() {
        //finish所有的Activity
        Iterator<AppCompatActivity> iterator = activitys.iterator();
        while (iterator.hasNext()) {
            AppCompatActivity task = iterator.next();
            if (!task.isFinishing()) {
                task.finish();
            }
        }
//        for (AppCompatActivity task : activitys) {
//            if (!task.isFinishing()) {
//                task.finish();
//            }
//        }
    }


    public static Context getAppContext() {
        return app.getApplicationContext();
    }

    private void settingOkHttp() {
        CookieJarImpl cookieJar = new CookieJarImpl(new PersistentCookieStore(getApplicationContext()));
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor("HsFood", false))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .writeTimeout(10000L, TimeUnit.MILLISECONDS)
                .cookieJar(cookieJar).build();
        OkHttpUtils.initClient(okHttpClient);
    }
}