package com.hongsou.douguoshouyin.http.ftp;

import org.apache.commons.net.ftp.FTPFile;

import java.util.List;

/**
 * @author lpc
 *         <p>
 * @copyright 鸿搜网络公司 版权所有
 * <p>
 * @date 2018/7/30
 * <p>
 * @desc 用于ftp操作回调
 */

public interface FtpNetCallBack {

    //获取文件夹下文件列表
    void getFtpFileList(List<FTPFile> ftpFileList);

    //下载
    void downLoadFinish(boolean result);

    //上传
    void uploadFinish(boolean result);
}
