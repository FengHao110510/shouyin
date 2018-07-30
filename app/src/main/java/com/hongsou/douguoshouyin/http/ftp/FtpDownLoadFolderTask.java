package com.hongsou.douguoshouyin.http.ftp;

import android.os.AsyncTask;

/**
 * @author lpc
 *         <p>
 * @copyright 鸿搜网络公司 版权所有
 * <p>
 * @date 2018/7/30
 * <p>
 * @desc ftp下载文件夹
 */

public class FtpDownLoadFolderTask extends AsyncTask<String, Integer, Boolean> {

    //ftp工具类
    private FtpHelper ftpHelper;
    //回调
    private FtpNetCallBack callBack;
    //ftp目录路径
    private String ftpFolder;
    //本地文件夹路径
    private String localFilePath;

    public FtpDownLoadFolderTask(FtpHelper ftpHelper, FtpNetCallBack callBack,
                                 String ftpFolder, String localFilePath) {
        this.ftpHelper = ftpHelper;
        this.callBack = callBack;
        this.ftpFolder = ftpFolder;
        this.localFilePath = localFilePath;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        boolean result = false;
        try {
            if (ftpHelper != null && ftpHelper.isConnect()) {
                int count = ftpHelper.downloadFolder(ftpFolder, localFilePath);
                if (count > 0) {
                    result = true;
                }
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
