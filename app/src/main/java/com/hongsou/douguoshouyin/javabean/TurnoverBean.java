package com.hongsou.douguoshouyin.javabean;

import java.util.List;

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
     * code : 1000
     * msg : 服务成功
     * extInfo : null
     * data : {"page":1,"limit":10,"result":[{"deleteFlag":0,"page":0,"rows":0,"batch":"20180712173653183351","paymentBatch":"201807121736534943973","tradingTime":"2018-07-12 17:37:07.003","paymentType":"微信支付","orderState":"成功","payAmount":"0.0100","shopNumber":"1000180614300325544","recharge":"0","amountCollected":"0.0000"},{"deleteFlag":0,"page":0,"rows":0,"batch":"20180706110032602582","paymentBatch":"201807061100340973570","tradingTime":"2018-07-06 11:00:35.0","paymentType":"支付宝支付","orderState":"成功","payAmount":"0.0100","shopNumber":"1000180614300325544","recharge":"0"},{"deleteFlag":0,"page":0,"rows":0,"batch":"2018070611000517773","paymentBatch":"201807061100101919464","tradingTime":"2018-07-06 11:00:11.0","paymentType":"微信支付","orderState":"成功","payAmount":"0.0100","shopNumber":"1000180614300325544","recharge":"0"},{"deleteFlag":0,"page":0,"rows":0,"batch":"2018070610593719464","paymentBatch":"201807061059382727434","tradingTime":"2018-07-06 10:59:55.0","paymentType":"微信支付","orderState":"成功","payAmount":"0.0100","shopNumber":"1000180614300325544","recharge":"0"},{"deleteFlag":0,"page":0,"rows":0,"batch":"20180706105910834813","paymentBatch":"201807061059114027926","tradingTime":"2018-07-06 10:59:22.0","paymentType":"支付宝支付","orderState":"成功","payAmount":"0.0100","shopNumber":"1000180614300325544","recharge":"0"}],"totalCount":5,"totalPage":1,"orderProperty":null,"orderDirection":null,"filters":[],"orders":[],"remark":null}
     * success : true
     */

    private int code;
    private String msg;
    private Object extInfo;
    private DataBean data;
    private boolean success;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Object extInfo) {
        this.extInfo = extInfo;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class DataBean {
        /**
         * page : 1
         * limit : 10
         * result : [{"deleteFlag":0,"page":0,"rows":0,"batch":"20180712173653183351","paymentBatch":"201807121736534943973","tradingTime":"2018-07-12 17:37:07.003","paymentType":"微信支付","orderState":"成功","payAmount":"0.0100","shopNumber":"1000180614300325544","recharge":"0","amountCollected":"0.0000"},{"deleteFlag":0,"page":0,"rows":0,"batch":"20180706110032602582","paymentBatch":"201807061100340973570","tradingTime":"2018-07-06 11:00:35.0","paymentType":"支付宝支付","orderState":"成功","payAmount":"0.0100","shopNumber":"1000180614300325544","recharge":"0"},{"deleteFlag":0,"page":0,"rows":0,"batch":"2018070611000517773","paymentBatch":"201807061100101919464","tradingTime":"2018-07-06 11:00:11.0","paymentType":"微信支付","orderState":"成功","payAmount":"0.0100","shopNumber":"1000180614300325544","recharge":"0"},{"deleteFlag":0,"page":0,"rows":0,"batch":"2018070610593719464","paymentBatch":"201807061059382727434","tradingTime":"2018-07-06 10:59:55.0","paymentType":"微信支付","orderState":"成功","payAmount":"0.0100","shopNumber":"1000180614300325544","recharge":"0"},{"deleteFlag":0,"page":0,"rows":0,"batch":"20180706105910834813","paymentBatch":"201807061059114027926","tradingTime":"2018-07-06 10:59:22.0","paymentType":"支付宝支付","orderState":"成功","payAmount":"0.0100","shopNumber":"1000180614300325544","recharge":"0"}]
         * totalCount : 5
         * totalPage : 1
         * orderProperty : null
         * orderDirection : null
         * filters : []
         * orders : []
         * remark : null
         */

        private int page;
        private int limit;
        private int totalCount;
        private int totalPage;
        private Object orderProperty;
        private Object orderDirection;
        private Object remark;
        private List<ResultBean> result;
        private List<?> filters;
        private List<?> orders;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public Object getOrderProperty() {
            return orderProperty;
        }

        public void setOrderProperty(Object orderProperty) {
            this.orderProperty = orderProperty;
        }

        public Object getOrderDirection() {
            return orderDirection;
        }

        public void setOrderDirection(Object orderDirection) {
            this.orderDirection = orderDirection;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public List<ResultBean> getResult() {
            return result;
        }

        public void setResult(List<ResultBean> result) {
            this.result = result;
        }

        public List<?> getFilters() {
            return filters;
        }

        public void setFilters(List<?> filters) {
            this.filters = filters;
        }

        public List<?> getOrders() {
            return orders;
        }

        public void setOrders(List<?> orders) {
            this.orders = orders;
        }

        public static class ResultBean {
            /**
             * deleteFlag : 0
             * page : 0
             * rows : 0
             * batch : 20180712173653183351
             * paymentBatch : 201807121736534943973
             * tradingTime : 2018-07-12 17:37:07.003
             * paymentType : 微信支付
             * orderState : 成功
             * payAmount : 0.0100
             * shopNumber : 1000180614300325544
             * recharge : 0
             * amountCollected : 0.0000
             */

            private int deleteFlag;
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

            public int getDeleteFlag() {
                return deleteFlag;
            }

            public void setDeleteFlag(int deleteFlag) {
                this.deleteFlag = deleteFlag;
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
        }
    }
}
