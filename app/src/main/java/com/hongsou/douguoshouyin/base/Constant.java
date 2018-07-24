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
}
