package com.hongsou.douguoshouyin.broadcastreceiver;

import java.io.Serializable;

/**
 * @copyright 鸿搜网络公司 版权所有
 * <p>
 * @author lpc
 * <p>
 * @date 2018/7/24
 * <p>
 * @desc 在线支付后，极光推送推送过来的数据bean
 */
public class PayOnLineSuccessBean implements Serializable {

    /**
     * 支付类型
     */
    private String tradeType;
    /**
     * 支付金额
     */
    private String money;
    private String outTradeNo;
    private String date;
    private String batch;

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }


    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

}
