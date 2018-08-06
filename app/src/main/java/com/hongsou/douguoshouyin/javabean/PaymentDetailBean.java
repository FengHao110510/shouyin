package com.hongsou.douguoshouyin.javabean;

/**
 * @author lpc
 *         <p>
 * @copyright 鸿搜网络公司 版权所有
 * <p>
 * @date 2018/7/24
 * <p>
 * @desc 支付流水详情
 */

public class PaymentDetailBean {


    /**
     * page : 0
     * rows : 0
     * batch : 20180706105910834813
     * paymentBatch : 201807061059114027926
     * tradingTime : 2018-07-06 10:59:22.0
     * paymentType : 支付宝支付
     * payAmount : 0.01
     * shopNumber : 1000180614300325544
     * recharge : 0
     * amountCollected : 0.00
     * orderType : 1
     * clerkUserName : 17732152792
     */

    private int page;
    private int rows;
    private String batch;
    private String paymentBatch;
    private String tradingTime;
    private String paymentType;
    private String orderState;
    private String payAmount;
    private String shopNumber;
    private String recharge;
    private String amountCollected;
    private String orderType;
    private String clerkUserName;

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

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getPaymentBatch() {
        return paymentBatch;
    }

    public void setPaymentBatch(String paymentBatch) {
        this.paymentBatch = paymentBatch;
    }

    public String getTradingTime() {
        return tradingTime;
    }

    public void setTradingTime(String tradingTime) {
        this.tradingTime = tradingTime;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public String getShopNumber() {
        return shopNumber;
    }

    public void setShopNumber(String shopNumber) {
        this.shopNumber = shopNumber;
    }

    public String getRecharge() {
        return recharge;
    }

    public void setRecharge(String recharge) {
        this.recharge = recharge;
    }

    public String getAmountCollected() {
        return amountCollected;
    }

    public void setAmountCollected(String amountCollected) {
        this.amountCollected = amountCollected;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getClerkUserName() {
        return clerkUserName;
    }

    public void setClerkUserName(String clerkUserName) {
        this.clerkUserName = clerkUserName;
    }
}
