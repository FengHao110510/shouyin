package com.hongsou.douguoshouyin.javabean;

import java.util.List;

/**
 * 版权：鸿搜网络公司 版权所有
 * <p>
 * 作者：冯大鱼
 * <p>
 * 版本：1.0
 * <p>
 * 创建日期：2018/7/11 0011
 * <p>
 * 描述：订单列表Bean
 * <p>
 * 修订历史：
 */


public class OrderBean {


    /**
     * code : 1000
     * msg : 服务成功
     * extInfo : null
     * data : {"page":1,"limit":10,"result":[{"deleteFlag":0,"page":0,"rows":0,"endTime":"2018-07-09 12:10:56.003","orderType":"已结帐","orderAmount":"8.50","batch":"20180709121632288327"},{"deleteFlag":0,"page":0,"rows":0,"endTime":"2018-07-08 19:50:25.003","orderType":"已结帐","orderAmount":"30.00","batch":"20180708195559677349"},{"deleteFlag":0,"page":0,"rows":0,"endTime":"2018-07-06 10:43:46.0","orderType":"已结帐","orderAmount":"0.02","batch":"20180706104333985853"},{"deleteFlag":0,"page":0,"rows":0,"endTime":"2018-07-05 15:19:48.0","orderType":"已结帐","orderAmount":"71.00","batch":"20180705151947179334"},{"deleteFlag":0,"page":0,"rows":0,"endTime":"2018-07-05 14:44:47.0","orderType":"已结帐","orderAmount":"180.00","batch":"20180705144447471823"},{"deleteFlag":0,"page":0,"rows":0,"endTime":"2018-07-05 14:44:19.0","orderType":"已结帐","orderAmount":"16.00","batch":"2018070514441981632"},{"deleteFlag":0,"page":0,"rows":0,"endTime":"2018-07-03 12:23:04.0","orderType":"已结帐","orderAmount":"0.00","batch":"20180703122406925318"},{"deleteFlag":0,"page":0,"rows":0,"endTime":"2018-07-03 12:20:45.0","orderType":"已结帐","orderAmount":"16.00","batch":"20180703122145855173"},{"deleteFlag":0,"page":0,"rows":0,"endTime":"2018-07-03 11:09:17.0","orderType":"已结帐","orderAmount":"5.00","batch":"20180703111019758400"},{"deleteFlag":0,"page":0,"rows":0,"endTime":"2018-07-03 11:09:09.0","orderType":"已结帐","orderAmount":"10.00","batch":"20180703111011527712"}],"totalCount":146,"totalPage":15,"orderProperty":null,"orderDirection":null,"filters":[],"orders":[],"remark":null}
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
         * result : [{"deleteFlag":0,"page":0,"rows":0,"endTime":"2018-07-09 12:10:56.003","orderType":"已结帐","orderAmount":"8.50","batch":"20180709121632288327"},{"deleteFlag":0,"page":0,"rows":0,"endTime":"2018-07-08 19:50:25.003","orderType":"已结帐","orderAmount":"30.00","batch":"20180708195559677349"},{"deleteFlag":0,"page":0,"rows":0,"endTime":"2018-07-06 10:43:46.0","orderType":"已结帐","orderAmount":"0.02","batch":"20180706104333985853"},{"deleteFlag":0,"page":0,"rows":0,"endTime":"2018-07-05 15:19:48.0","orderType":"已结帐","orderAmount":"71.00","batch":"20180705151947179334"},{"deleteFlag":0,"page":0,"rows":0,"endTime":"2018-07-05 14:44:47.0","orderType":"已结帐","orderAmount":"180.00","batch":"20180705144447471823"},{"deleteFlag":0,"page":0,"rows":0,"endTime":"2018-07-05 14:44:19.0","orderType":"已结帐","orderAmount":"16.00","batch":"2018070514441981632"},{"deleteFlag":0,"page":0,"rows":0,"endTime":"2018-07-03 12:23:04.0","orderType":"已结帐","orderAmount":"0.00","batch":"20180703122406925318"},{"deleteFlag":0,"page":0,"rows":0,"endTime":"2018-07-03 12:20:45.0","orderType":"已结帐","orderAmount":"16.00","batch":"20180703122145855173"},{"deleteFlag":0,"page":0,"rows":0,"endTime":"2018-07-03 11:09:17.0","orderType":"已结帐","orderAmount":"5.00","batch":"20180703111019758400"},{"deleteFlag":0,"page":0,"rows":0,"endTime":"2018-07-03 11:09:09.0","orderType":"已结帐","orderAmount":"10.00","batch":"20180703111011527712"}]
         * totalCount : 146
         * totalPage : 15
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
             * endTime : 2018-07-09 12:10:56.003
             * orderType : 已结帐
             * orderAmount : 8.50
             * batch : 20180709121632288327
             */

            private int deleteFlag;
            private int page;
            private int rows;
            private String endTime;
            private String orderType;
            private String orderAmount;
            private String batch;

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

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public String getOrderType() {
                return orderType;
            }

            public void setOrderType(String orderType) {
                this.orderType = orderType;
            }

            public String getOrderAmount() {
                return orderAmount;
            }

            public void setOrderAmount(String orderAmount) {
                this.orderAmount = orderAmount;
            }

            public String getBatch() {
                return batch;
            }

            public void setBatch(String batch) {
                this.batch = batch;
            }
        }
    }
}
