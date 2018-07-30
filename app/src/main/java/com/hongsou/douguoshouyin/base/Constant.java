package com.hongsou.douguoshouyin.base;

import java.text.DecimalFormat;

/**
 * @author lpc
 *         <p>
 * @copyright 鸿搜网络公司 版权所有
 * <p>
 * @date 2018/7/18
 * <p>
 * @desc 常量类
 */

public class Constant {

    /**
     * Double数据格式化两位小数
     */
    public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");
    /**
     * 传给后台的极光推送 Secret
     */
    public static final String MASTER_SECRET = "280ff86ecd27f6a02a060f2a";
    /**
     * 传给后台的极光推送 APP_KEY
     */
    public static final String APP_KEY = "58655fc8bb336cc07d639b35";
    /**
     * 餐品图片链接中多余的字符,第一种情况
     */
    public static final String IMG_REPLACE1 = "/www/wwwroot/h5.hongxiaosou.cn/";
    /**
     * 餐品图片链接中多余的字符,第二种情况
     */
    public static final String IMG_REPLACE2 = "/www/wwwroot/images.hongxiaosou.cn/";
    /**
     * 支付成功后推送httpclient的url
     */
//    public static final String HTTP_URL = "http://39.106.220.143:61616/hs-app-server";
    public static final String HTTP_URL = "http://47.93.0.117:5003/hs-app-server";
    /**
     * FTP路径
     */
    public static final String FTP_PATH = "47.95.247.16";
    /**
     * FTP根目录
     */
    public static final String FTP_FILE_PATH = "/hsAppServer/crash/douguoshouyin";
    /**
     * FTP 账号
     */
    public static final String FTP_NAME = "images_hongxiao";
    /**
     * FTP 密码
     */
    public static final String FTP_PAW = "N3P6xE5ZTsbthXEa";
    /**
     * FTP 端口号
     */
    public static final int FTP_PORT = 21;
}
