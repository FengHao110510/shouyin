package com.hongsou.douguoshouyin.javabean;

/**
 * @author lpc
 *         <p>
 * @copyright 鸿搜网络公司 版权所有
 * <p>
 * @date 2018/7/27
 * <p>
 * @desc
 */

public class HandoverDetailBean {


    /**
     * page : 0
     * rows : 0
     * aliAmount : 0.00
     * weChatAmount : 0.00
     * cashAmount : 0.00
     * bankAmount : 0.00
     * refoundCount : 0
     * refoundAmount : 0.00
     * amountCollected : 0.00
     * collectedCount : 0
     */

    private int page;
    private int rows;
    private String aliAmount;
    private String weChatAmount;
    private String cashAmount;
    private String bankAmount;
    private String refoundCount;
    private String refoundAmount;
    private String amountCollected;
    private String collectedCount;
    private String tradingTime;

    public String getTradingTime() {
        return tradingTime;
    }

    public void setTradingTime(String tradingTime) {
        this.tradingTime = tradingTime;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getAliAmount() {
        return aliAmount;
    }

    public void setAliAmount(String aliAmount) {
        this.aliAmount = aliAmount;
    }

    public String getWeChatAmount() {
        return weChatAmount;
    }

    public void setWeChatAmount(String weChatAmount) {
        this.weChatAmount = weChatAmount;
    }

    public String getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(String cashAmount) {
        this.cashAmount = cashAmount;
    }

    public String getBankAmount() {
        return bankAmount;
    }

    public void setBankAmount(String bankAmount) {
        this.bankAmount = bankAmount;
    }

    public String getRefoundCount() {
        return refoundCount;
    }

    public void setRefoundCount(String refoundCount) {
        this.refoundCount = refoundCount;
    }

    public String getRefoundAmount() {
        return refoundAmount;
    }

    public void setRefoundAmount(String refoundAmount) {
        this.refoundAmount = refoundAmount;
    }

    public String getAmountCollected() {
        return amountCollected;
    }

    public void setAmountCollected(String amountCollected) {
        this.amountCollected = amountCollected;
    }

    public String getCollectedCount() {
        return collectedCount;
    }

    public void setCollectedCount(String collectedCount) {
        this.collectedCount = collectedCount;
    }
}