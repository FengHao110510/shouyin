package com.hongsou.douguoshouyin.javabean;

/**
 * 版权：鸿搜网络公司 版权所有
 * <p>
 * 作者：冯大鱼
 * <p>
 * 版本：1.0
 * <p>
 * 创建日期：2018/7/10 0010
 * <p>
 * 描述：
 * <p>
 * 修订历史：
 */


public class SendMsgBean extends RootBean {


    /**
     * VerificationCode : 707166
     * phone : 17603271217
     * Content : 【豆果点餐】验证码:707166,您正在进行身份验证,打死不要到时别人哦!
     */

    private String VerificationCode;
    private String phone;
    private String Content;

    public String getVerificationCode() {
        return VerificationCode;
    }

    public void setVerificationCode(String VerificationCode) {
        this.VerificationCode = VerificationCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }
}
