package com.hongsou.douguoshouyin.http.ftp;

import android.os.AsyncTask;

/**
 * @author lpc
 *         <p>
 * @copyright 鸿搜网络公司 版权所有
 * <p>
 * @date 2018/7/30
 * <p>
 * @desc ftp下载单个文件
 */

public class FtpDownLoadFileTask extends AsyncTask<String, Integer, Boolean> {

    //ftp工具类
    private FtpHelper ftpHelper;
    //回调
    private FtpNetCallBack callBack;
    //ftp目录
    private String ftpFolder;
    //本地文件夹
    private String localFilePath;
    //需要下载的文件
    private String fileName;

    public FtpDownLoadFileTask(FtpHelper ftpHelper, FtpNetCallBack callBack,
                               String ftpFolder, String fileName, String localFilePath) {
        this.ftpHelper = ftpHelper;
        this.callBack = callBack;
        this.ftpFolder = ftpFolder;
        this.localFilePath = localFilePath;
        this.fileName = fileName;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        boolean result = false;
        try {
            if (ftpHelper != null && ftpHelper.isConnect()) {
                result = ftpHelper.downloadFile(ftpFolder, fileName, localFilePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        callBack.downLoadFinish(result);
    }
}
