package com.hongsou.douguoshouyin.javabean;

import com.hongsou.douguoshouyin.db.SelectMealEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @author lpc
 *         <p>
 * @copyright 鸿搜网络公司 版权所有
 * <p>
 * @date 2018/7/19
 * <p>
 * @desc 提交餐品订单
 */

public class SubmitOrderBean implements Serializable{

    private Long id;
    private String batch;
    private String amountCollected;
    private String amountReceivable;
    private String antiNodeReason;
    private String cashAmount;
    private String clerkName;
    private String clerkNumber;
    private String dateOrderNumber;
    private String endTime;
    private String equipmentNumber;
    private String equipmentSource;
    private String orderAmount;
    private String orderRemarks;
    private String orderSource;
    private String orderType;
    private String paymentType;
    private String shopNumber;
    private String tradingTime;
    private String paymentOrder;
    /**
     * 订单来源
     */
    private String orderSourcePayment;
    /**
     * 会员编号/手机号
     */
    private String memberNumber;
    /**
     * 点餐列表数据
     */
    private List<SelectMealEntity> foodProductsResult;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getAmountCollected() {
        return amountCollected;
    }

    public void setAmountCollected(String amountCollected) {
        this.amountCollected = amountCollected;
    }

    public String getAmountReceivable() {
        return amountReceivable;
    }

    public void setAmountReceivable(String amountReceivable) {
        this.amountReceivable = amountReceivable;
    }

    public String getAntiNodeReason() {
        return antiNodeReason;
    }

    public void setAntiNodeReason(String antiNodeReason) {
        this.antiNodeReason = antiNodeReason;
    }

    public String getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(String cashAmount) {
        this.cashAmount = cashAmount;
    }

    public String getClerkName() {
        return clerkName;
    }

    public void setClerkName(String clerkName) {
        this.clerkName = clerkName;
    }

    public String getClerkNumber() {
        return clerkNumber;
    }

    public void setClerkNumber(String clerkNumber) {
        this.clerkNumber = clerkNumber;
    }

    public String getDateOrderNumber() {
        return dateOrderNumber;
    }

    public void setDateOrderNumber(String dateOrderNumber) {
        this.dateOrderNumber = dateOrderNumber;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEquipmentNumber() {
        return equipmentNumber;
    }

    public void setEquipmentNumber(String equipmentNumber) {
        this.equipmentNumber = equipmentNumber;
    }

    public String getEquipmentSource() {
        return equipmentSource;
    }

    public void setEquipmentSource(String equipmentSource) {
        this.equipmentSource = equipmentSource;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderRemarks() {
        return orderRemarks;
    }

    public void setOrderRemarks(String orderRemarks) {
        this.orderRemarks = orderRemarks;
    }

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getShopNumber() {
        return shopNumber;
    }

    public void setShopNumber(String shopNumber) {
        this.shopNumber = shopNumber;
    }

    public String getTradingTime() {
        return tradingTime;
    }

    public void setTradingTime(String tradingTime) {
        this.tradingTime = tradingTime;
    }

    public String getPaymentOrder() {
        return paymentOrder;
    }

    public void setPaymentOrder(String paymentOrder) {
        this.paymentOrder = paymentOrder;
    }

    public String getOrderSourcePayment() {
        return orderSourcePayment;
    }

    public void setOrderSourcePayment(String orderSourcePayment) {
        this.orderSourcePayment = orderSourcePayment;
    }

    public String getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(String memberNumber) {
        this.memberNumber = memberNumber;
    }

    public List<SelectMealEntity> getFoodProductsResult() {
        return foodProductsResult;
    }

    public void setFoodProductsResult(List<SelectMealEntity> foodProductsResult) {
        this.foodProductsResult = foodProductsResult;
    }
}
