package douguoshouyin.douguoshouyin.base;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;
import com.zhy.http.okhttp.log.LoggerInterceptor;

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


public class BaseApplication extends Application {
    public static BaseApplication app;
    /***寄存整个应用Activity**/
    private final Stack<AppCompatActivity> activitys = new Stack<AppCompatActivity>();
    @Override
    public void onCreate() {
        super.onCreate();

        initApp();
        settingOkHttp();

//        //初始化全局异常捕获
//        CrashHandler.getInstance().init(this);
    }


    private void initApp() {
        app = this;
    }

    public static synchronized Application getInstance() {
        return app;
    }

    public static BaseApplication getApplication() {
        return app;
    }


    //用来放置内存溢出 oom
    /**
     * 将Activity压入Application栈
     * @param task 将要压入栈的Activity对象
     */
    public  void pushTask(AppCompatActivity task) {
        activitys.push(task);
    }
    /**
     * 将传入的Activity对象从栈中移除
     * @param task
     */
    public  void removeTask(AppCompatActivity task) {
        if (task!=null){
            activitys.remove(task);
        }
    }

    /**
     * 根据指定位置从栈中移除Activity
     * @param taskIndex Activity栈索引
     */
    public  void removeTask(int taskIndex) {
        if (activitys.size() > taskIndex){
            activitys.remove(taskIndex);
        }

    }

    /**
     * 将栈中Activity移除至栈顶
     */
    public  void removeToTop() {
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
    public  void removeAll() {
        //finish所有的Activity
        for (AppCompatActivity task : activitys) {
            if (!task.isFinishing()) {
                task.finish();
            }
        }
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