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
     * data : {"page":1,"limit":10,"result":[{"page":0,"rows":0,"endTime":"2018-08-13 19:16:01","orderSource":"开单","orderType":"已结帐","orderState":1,"orderAmount":"33.00","insertTime":"2018-08-13 19:16:01","batch":"201808137827194774"},{"page":0,"rows":0,"endTime":"2018-08-13 10:15:03","orderSource":"开单","orderType":"已退单","orderState":1,"orderAmount":"0.01","insertTime":"2018-08-13 10:15:03","batch":"201808133368617940"},{"page":0,"rows":0,"endTime":"2018-08-13 10:14:40","orderSource":"开单","orderType":"已退单","orderState":1,"orderAmount":"0.01","insertTime":"2018-08-13 10:14:40","batch":"201808130180811588"},{"page":0,"rows":0,"endTime":"2018-08-11 16:31:05","orderSource":"开单","orderType":"已退单","orderState":1,"orderAmount":"0.01","insertTime":"2018-08-11 16:31:05","batch":"201808111059182756"},{"page":0,"rows":0,"endTime":"2018-08-11 16:26:26","orderSource":"开单","orderType":"已退单","orderState":1,"orderAmount":"33.00","insertTime":"2018-08-11 16:26:26","batch":"201808116269813676"},{"page":0,"rows":0,"endTime":"2018-08-11 16:25:44","orderSource":"开单","orderType":"已退单","orderState":1,"orderAmount":"33.00","insertTime":"2018-08-11 16:25:44","batch":"201808114354754258"},{"page":0,"rows":0,"endTime":"2018-08-11 16:25:12","orderSource":"开单","orderType":"已退单","orderState":1,"orderAmount":"1.00","insertTime":"2018-08-11 16:25:12","batch":"201808112194355215"},{"page":0,"rows":0,"endTime":"2018-08-11 16:24:34","orderSource":"开单","orderType":"已退单","orderState":1,"orderAmount":"1998.00","insertTime":"2018-08-11 16:24:34","batch":"201808111116684095"},{"page":0,"rows":0,"endTime":"2018-08-11 15:54:01","orderSource":"开单","orderType":"已退单","orderState":1,"orderAmount":"2.00","insertTime":"2018-08-11 15:54:01","batch":"201808111008861205"},{"page":0,"rows":0,"endTime":"2018-08-11 13:30:42","orderSource":"开单","orderType":"已退单","orderState":1,"orderAmount":"0.50","insertTime":"2018-08-11 13:30:42","batch":"201808111810158460"}],"totalCount":216,"totalPage":22,"orderProperty":null,"orderDirection":null,"filters":[],"orders":[],"remark":null}
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
         * result : [{"page":0,"rows":0,"endTime":"2018-08-13 19:16:01","orderSource":"开单","orderType":"已结帐","orderState":1,"orderAmount":"33.00","insertTime":"2018-08-13 19:16:01","batch":"201808137827194774"},{"page":0,"rows":0,"endTime":"2018-08-13 10:15:03","orderSource":"开单","orderType":"已退单","orderState":1,"orderAmount":"0.01","insertTime":"2018-08-13 10:15:03","batch":"201808133368617940"},{"page":0,"rows":0,"endTime":"2018-08-13 10:14:40","orderSource":"开单","orderType":"已退单","orderState":1,"orderAmount":"0.01","insertTime":"2018-08-13 10:14:40","batch":"201808130180811588"},{"page":0,"rows":0,"endTime":"2018-08-11 16:31:05","orderSource":"开单","orderType":"已退单","orderState":1,"orderAmount":"0.01","insertTime":"2018-08-11 16:31:05","batch":"201808111059182756"},{"page":0,"rows":0,"endTime":"2018-08-11 16:26:26","orderSource":"开单","orderType":"已退单","orderState":1,"orderAmount":"33.00","insertTime":"2018-08-11 16:26:26","batch":"201808116269813676"},{"page":0,"rows":0,"endTime":"2018-08-11 16:25:44","orderSource":"开单","orderType":"已退单","orderState":1,"orderAmount":"33.00","insertTime":"2018-08-11 16:25:44","batch":"201808114354754258"},{"page":0,"rows":0,"endTime":"2018-08-11 16:25:12","orderSource":"开单","orderType":"已退单","orderState":1,"orderAmount":"1.00","insertTime":"2018-08-11 16:25:12","batch":"201808112194355215"},{"page":0,"rows":0,"endTime":"2018-08-11 16:24:34","orderSource":"开单","orderType":"已退单","orderState":1,"orderAmount":"1998.00","insertTime":"2018-08-11 16:24:34","batch":"201808111116684095"},{"page":0,"rows":0,"endTime":"2018-08-11 15:54:01","orderSource":"开单","orderType":"已退单","orderState":1,"orderAmount":"2.00","insertTime":"2018-08-11 15:54:01","batch":"201808111008861205"},{"page":0,"rows":0,"endTime":"2018-08-11 13:30:42","orderSource":"开单","orderType":"已退单","orderState":1,"orderAmount":"0.50","insertTime":"2018-08-11 13:30:42","batch":"201808111810158460"}]
         * totalCount : 216
         * totalPage : 22
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
             * page : 0
             * rows : 0
             * endTime : 2018-08-13 19:16:01
             * orderSource : 开单
             * orderType : 已结帐
             * orderState : 1
             * orderAmount : 33.00
             * insertTime : 2018-08-13 19:16:01
             * batch : 201808137827194774
             */

            private int page;
            private int rows;
            private String endTime;
            private String orderSource;
            private String orderType;
            private int orderState;
            private String orderAmount;
            private String insertTime;
            private String batch;

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

            public int getOrderState() {
                return orderState;
            }

            public void setOrderState(int orderState) {
                this.orderState = orderState;
            }

            public String getOrderAmount() {
                return orderAmount;
            }

            public void setOrderAmount(String orderAmount) {
                this.orderAmount = orderAmount;
            }

            public String getInsertTime() {
                return insertTime;
            }

            public void setInsertTime(String insertTime) {
                this.insertTime = insertTime;
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
