package com.hongsou.douguoshouyin.tool;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.hongsou.douguoshouyin.activity.WelcomeActivity;
import com.hongsou.douguoshouyin.base.BaseApplication;
import com.hongsou.douguoshouyin.base.Constant;
import com.hongsou.douguoshouyin.http.ThreadPoolUtils;
import com.hongsou.douguoshouyin.http.ftp.FtpNetCallBack;
import com.hongsou.douguoshouyin.http.ftp.FtpUploadTask;

import org.apache.commons.net.ftp.FTPFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;


/**
 * Created by Administrator on 2018/5/22.
 * TODO 异常捕获工具类
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler, FtpNetCallBack {

    public static final String TAG = "CrashHandler";
    private Context mContext;
    private static Object syncRoot = new Object();

    /**
     * 系统默认的UncaughtException处理类
     */
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    /**
     * CrashHandler实例
     */
    private static CrashHandler INSTANCE;
    /**
     * 用来存储设备信息和异常信息
     */
    private Map<String, String> infos = new HashMap<String, String>();
    /**
     * 用于格式化日期,作为日志文件名的一部分
     */
    private DateFormat formatter = new SimpleDateFormat("yyyyMMdd-HHmmss-SSS");
    /**
     * 错误报告文件的扩展名
     */
    private static final String CRASH_REPORTER_EXTENSION = ".cr";
    /**
     * 错误报告文件保存路径
     */
    private String mDirPath;

    private CrashHandler() {
    }

    /**
     * 获取CrashHandler实例 ,单例模式
     */
    public static CrashHandler getInstance() {
        // 防止多线程访问安全，这里使用了双重锁
        if (INSTANCE == null) {
            synchronized (syncRoot) {
                if (INSTANCE == null) {
                    INSTANCE = new CrashHandler();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 初始化
     *
     * @param context
     */
    public void init(Context context) {
        mContext = context;
        initFile();
        // 获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        // 设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    private void initFile() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            mDirPath = Environment.getExternalStorageDirectory() + "/douguo/sy/log/";
            File dir = new File(mDirPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
        }
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            // 如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                Log.e(TAG, "error : ", e);
            }
            // 退出程序
//            android.os.Process.killProcess(android.os.Process.myPid());
//            System.exit(1);
            // 重启程序
            Intent intent = new Intent();
            intent.setClass(mContext, WelcomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false.
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        // 使用Toast来显示异常信息
        ThreadPoolUtils.execute(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext, "很抱歉,程序出现异常即将重启.", Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        });
        // 收集设备参数信息
        collectDeviceInfo(mContext);
        // 保存日志文件
        saveCrashInfo2File(ex);
        // 把错误报告发送给服务器
//        sendCrashReportsToServer();
        return true;
    }

    /**
     * 收集设备参数信息
     *
     * @param ctx
     */
    public void collectDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "an error occured when collect package info", e);
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
                LogUtil.d(TAG, field.getName() + " : " + field.get(null));
            } catch (Exception e) {
                Log.e(TAG, "an error occured when collect crash info", e);
            }
        }
    }

    /**
     * 保存错误信息到文件中
     *
     * @param ex
     * @return 返回文件名称, 便于将文件传送到服务器
     */
    private String saveCrashInfo2File(Throwable ex) {

        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
        }

        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);
        try {
            String time = formatter.format(new Date());
            String fileName =  "crash-" + time + CRASH_REPORTER_EXTENSION;
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                mDirPath = Environment.getExternalStorageDirectory() + "/douguo/sy/log/";
                File dir = new File(mDirPath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(mDirPath + fileName);
                fos.write(sb.toString().getBytes("UTF-8"));
                fos.close();
            }
            return fileName;
        } catch (Exception e) {
            Log.e(TAG, "an error occured while writing file...", e);
        }
        return null;
    }

    /**
     * @param localFilePath 本地文件路径
     * @desc 上传到FTP服务器
     * @anthor lpc
     * @date: 2018/7/30
     */
    private void upload(String localFilePath) {
        if (!TextUtils.isEmpty(localFilePath)) {
            new FtpUploadTask(BaseApplication.ftp, this, localFilePath, Constant.FTP_FILE_PATH).execute();
        }
    }

    @Override
    public void getFtpFileList(List<FTPFile> ftpFileList) {

    }

    @Override
    public void downLoadFinish(boolean result) {

    }

    @Override
    public void uploadFinish(boolean result) {
        if (result) {
            LogUtil.e(TAG, "uploadFinish: 提交成功");
        } else {
            LogUtil.e(TAG, "uploadFinish: 提交失败");
        }

    }

    /**
     * 把错误报告发送给服务器,包含新产生的和以前没发送的.
     */
    private void sendCrashReportsToServer() {
        String[] crFiles = getCrashReportFiles();
        if (crFiles != null && crFiles.length > 0) {
            TreeSet<String> sortedFiles = new TreeSet<String>();
            sortedFiles.addAll(Arrays.asList(crFiles));
            for (String fileName : sortedFiles) {
                File cr = new File(mDirPath, fileName);
                postReport(cr);
//                cr.delete();// 删除已发送的报告
            }
        }
    }

    private void postReport(File file) {
        // TODO 发送错误报告到服务器
        LogUtil.e(file.getPath());
        upload(file.getPath());
    }

    /**
     * 获取错误报告文件名
     * @return
     */
    private String[] getCrashReportFiles() {
        if (TextUtils.isEmpty(mDirPath)){
            return null;
        }
        File filesDir = new File(mDirPath);
        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(CRASH_REPORTER_EXTENSION);
            }
        };
        return filesDir.list(filter);
    }
}
