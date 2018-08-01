package com.hongsou.douguoshouyin.javabean;

import java.util.List;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/8/1 0001
 * 描述：不参与扫码点餐的商品
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


public class NoScanBean {

    /**
     * code : 1000
     * msg : 服务成功
     * extInfo : null
     * data : {"page":1,"limit":10,"result":[{"page":0,"rows":0,"shopNumber":"1000180614300325544","singleProductNumber":"ZH180626111116030","singleProductName":"经典A+B","foodType":"2","price":"18.00"},{"page":0,"rows":0,"shopNumber":"1000180614300325544","singleProductNumber":"feb769cc-7cd1-4a97-b97c-508c64e3bbe0","singleProductName":"充电器(条)","foodType":"1","price":"1.00"},{"page":0,"rows":0,"shopNumber":"1000180614300325544","singleProductNumber":"f4f192e1-a58f-420d-990a-0b89c9efa521","singleProductName":"痒痒鼠(小)","foodType":"1","price":"998.00"},{"page":0,"rows":0,"shopNumber":"1000180614300325544","singleProductNumber":"ea5ca914-7dca-4269-b425-dda513d9fbba","singleProductName":"8成新苹果7(小)","foodType":"1","price":"666.00"},{"page":0,"rows":0,"shopNumber":"1000180614300325544","singleProductNumber":"de0ba0e3-bb9a-4dbf-875d-6b05bf0a65e2","singleProductName":"组合套餐","foodType":"2","price":"0.01"},{"page":0,"rows":0,"shopNumber":"1000180614300325544","singleProductNumber":"d42c5f04-e4eb-42d3-9a27-1531c280ed8d","singleProductName":"测试单亲(大)","foodType":"1","price":"3.00"},{"page":0,"rows":0,"shopNumber":"1000180614300325544","singleProductNumber":"d2e6bcf6-8bf8-4e70-ac7f-0b1079d7d95a","singleProductName":"喵(喵一只)","foodType":"1","price":"666.00"},{"page":0,"rows":0,"shopNumber":"1000180614300325544","singleProductNumber":"d0ccc711-3d41-47bb-9d4f-6782cb7ecc81","singleProductName":"测试单品002(大)","foodType":"1","price":"0.01"},{"page":0,"rows":0,"shopNumber":"1000180614300325544","singleProductNumber":"CPFCAA61","singleProductName":"可乐(大杯)","foodType":"1","price":"5.00"},{"page":0,"rows":0,"shopNumber":"1000180614300325544","singleProductNumber":"CP85ED31","singleProductName":"鸡翅(大份)","foodType":"1","price":"15.00"}],"totalCount":48,"totalPage":5,"orderProperty":null,"orderDirection":null,"filters":[],"orders":[],"remark":null}
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
         * result : [{"page":0,"rows":0,"shopNumber":"1000180614300325544","singleProductNumber":"ZH180626111116030","singleProductName":"经典A+B","foodType":"2","price":"18.00"},{"page":0,"rows":0,"shopNumber":"1000180614300325544","singleProductNumber":"feb769cc-7cd1-4a97-b97c-508c64e3bbe0","singleProductName":"充电器(条)","foodType":"1","price":"1.00"},{"page":0,"rows":0,"shopNumber":"1000180614300325544","singleProductNumber":"f4f192e1-a58f-420d-990a-0b89c9efa521","singleProductName":"痒痒鼠(小)","foodType":"1","price":"998.00"},{"page":0,"rows":0,"shopNumber":"1000180614300325544","singleProductNumber":"ea5ca914-7dca-4269-b425-dda513d9fbba","singleProductName":"8成新苹果7(小)","foodType":"1","price":"666.00"},{"page":0,"rows":0,"shopNumber":"1000180614300325544","singleProductNumber":"de0ba0e3-bb9a-4dbf-875d-6b05bf0a65e2","singleProductName":"组合套餐","foodType":"2","price":"0.01"},{"page":0,"rows":0,"shopNumber":"1000180614300325544","singleProductNumber":"d42c5f04-e4eb-42d3-9a27-1531c280ed8d","singleProductName":"测试单亲(大)","foodType":"1","price":"3.00"},{"page":0,"rows":0,"shopNumber":"1000180614300325544","singleProductNumber":"d2e6bcf6-8bf8-4e70-ac7f-0b1079d7d95a","singleProductName":"喵(喵一只)","foodType":"1","price":"666.00"},{"page":0,"rows":0,"shopNumber":"1000180614300325544","singleProductNumber":"d0ccc711-3d41-47bb-9d4f-6782cb7ecc81","singleProductName":"测试单品002(大)","foodType":"1","price":"0.01"},{"page":0,"rows":0,"shopNumber":"1000180614300325544","singleProductNumber":"CPFCAA61","singleProductName":"可乐(大杯)","foodType":"1","price":"5.00"},{"page":0,"rows":0,"shopNumber":"1000180614300325544","singleProductNumber":"CP85ED31","singleProductName":"鸡翅(大份)","foodType":"1","price":"15.00"}]
         * totalCount : 48
         * totalPage : 5
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
             * shopNumber : 1000180614300325544
             * singleProductNumber : ZH180626111116030
             * singleProductName : 经典A+B
             * foodType : 2
             * price : 18.00
             */

            private int page;
            private int rows;
            private String shopNumber;
            private String singleProductNumber;
            private String singleProductName;
            private String foodType;
            private String price;

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

            public String getShopNumber() {
                return shopNumber;
            }

            public void setShopNumber(String shopNumber) {
                this.shopNumber = shopNumber;
            }

            public String getSingleProductNumber() {
                return singleProductNumber;
            }

            public void setSingleProductNumber(String singleProductNumber) {
                this.singleProductNumber = singleProductNumber;
            }

            public String getSingleProductName() {
                return singleProductName;
            }

            public void setSingleProductName(String singleProductName) {
                this.singleProductName = singleProductName;
            }

            public String getFoodType() {
                return foodType;
            }

            public void setFoodType(String foodType) {
                this.foodType = foodType;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }
        }
    }
}
