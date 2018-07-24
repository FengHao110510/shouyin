package com.hongsou.douguoshouyin.javabean;

/**
 * @author lpc
 *         <p>
 * @copyright 鸿搜网络公司 版权所有
 * <p>
 * @date 2018/7/23
 * <p>
 * @desc 筛选条件实体类
 */

public class OrderConditionBean {


    /**
     * orderSourcePayment : 开单
     * orderSourceType : 1
     * orderSourceFlag : FLASTE1
     * orderSourceTypeName : 订单来源
     */

    private String orderSourcePayment;
    private String orderSourceType;
    private String orderSourceFlag;
    private String orderSourceTypeName;

    public String getOrderSourcePayment() {
        return orderSourcePayment;
    }

    public void setOrderSourcePayment(String orderSourcePayment) {
        this.orderSourcePayment = orderSourcePayment;
    }

    public String getOrderSourceType() {
        return orderSourceType;
    }

    public void setOrderSourceType(String orderSourceType) {
        this.orderSourceType = orderSourceType;
    }

    public String getOrderSourceFlag() {
        return orderSourceFlag;
    }

    public void setOrderSourceFlag(String orderSourceFlag) {
        this.orderSourceFlag = orderSourceFlag;
    }

    public String getOrderSourceTypeName() {
        return orderSourceTypeName;
    }

    public void setOrderSourceTypeName(String orderSourceTypeName) {
        this.orderSourceTypeName = orderSourceTypeName;
    }
}
