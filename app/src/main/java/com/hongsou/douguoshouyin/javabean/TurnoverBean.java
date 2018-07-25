package com.hongsou.douguoshouyin.javabean;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/7/16 0016
 * 描述：流水列表bean
 * 修订历史：
 * ┌─┐       ┌─┐
 * ┌──┘ ┴───────┘ ┴──┐
 * │                 │
 * │       ───       │
 * │  ─┬┘       └┬─  │
 * │                 │
 * │       ─┴─       │
 * │                 │
 * └───┐         ┌───┘
 * │         │
 * │         │
 * │         │
 * │         └──────────────┐
 * │                        │
 * │                        ├─┐
 * │                        ┌─┘
 * │                        │
 * └─┐  ┐  ┌───────┬──┐  ┌──┘
 * │ ─┤ ─┤       │ ─┤ ─┤
 * └──┴──┘       └──┴──┘
 * 神兽保佑
 * 代码无BUG!
 */


public class TurnoverBean {


    /**
     * item : 1
     * money : 0.00
     * count : 9
     * time : 2018-07-25
     * page : 0
     * rows : 0
     * batch : 201807254495963784
     * paymentBatch :
     * tradingTime : 2018-07-25 10:06:02.0
     * paymentType : 现金支付
     * orderState : 成功
     * payAmount : 30.0000
     * shopNumber : 1000180614300325544
     * recharge : 0
     * amountCollected : 0.0000
     * orderType : 1
     */

    private int item;
    private String money;
    private int count;
    private String time;
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

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
}
