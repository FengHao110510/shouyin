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
     * data : {"page":1,"limit":10,"result":[{"page":0,"rows":0,"shopNumber":"1000180614300325544","singleProductNumber":"feb769cc-7cd1-4a97-b97c-508c64e3bbe0","standardNumber":"035af0a1-222a-4b87-8ed8-3132a3be0379","singleProductName":"充电器(条)","foodType":"1","price":"1.00","picture":"hsAppServer/20180727/6a91ef0a7b49456886f5bb61e1effdf4.png"},{"page":0,"rows":0,"shopNumber":"1000180614300325544","singleProductNumber":"f4f192e1-a58f-420d-990a-0b89c9efa521","standardNumber":"bb0f4a16-1f43-4866-b36c-62e68c0bbcd7","singleProductName":"痒痒鼠(小)","foodType":"1","price":"998.00","picture":"hsAppServer\\20180726\\2ad0066dd0f8490c94dd81492bcebc8b.png"},{"page":0,"rows":0,"shopNumber":"1000180614300325544","singleProductNumber":"ea5ca914-7dca-4269-b425-dda513d9fbba","standardNumber":"f8306237-ae6e-4410-90c2-f900b40427a1","singleProductName":"8成新苹果7(小)","foodType":"1","price":"666.00","picture":"hsAppServer/20180727/db65c2af657945179164b4bec90ca964.png"},{"page":0,"rows":0,"shopNumber":"1000180614300325544","singleProductNumber":"d42c5f04-e4eb-42d3-9a27-1531c280ed8d","standardNumber":"6571a10e-da4f-4991-8f9e-f5782fc71221","singleProductName":"测试单亲(大)","foodType":"1","price":"3.00","picture":"hsAppServer\\20180726\\c0c9089107c04b16a9c4990bae1e9773.png"},{"page":0,"rows":0,"shopNumber":"1000180614300325544","singleProductNumber":"d2e6bcf6-8bf8-4e70-ac7f-0b1079d7d95a","standardNumber":"0a56ede2-8630-4289-a441-043412f66443","singleProductName":"喵(喵一只)","foodType":"1","price":"666.00","picture":"hsAppServer/20180725/1ee2785b9a934e60800a3a055a583006.png"},{"page":0,"rows":0,"shopNumber":"1000180614300325544","singleProductNumber":"d0ccc711-3d41-47bb-9d4f-6782cb7ecc81","standardNumber":"961493e9-17a4-46b9-be24-4d42477a8ee9","singleProductName":"测试单品002(大)","foodType":"1","price":"0.01","picture":"hsAppServer/20180725/ca45e857530b425f8d295e2e08093f7c.png"},{"page":0,"rows":0,"shopNumber":"1000180614300325544","singleProductNumber":"CPFCAA61","standardNumber":"180625111111304","singleProductName":"可乐(大杯)","foodType":"1","price":"5.00","picture":""},{"page":0,"rows":0,"shopNumber":"1000180614300325544","singleProductNumber":"CP85ED31","standardNumber":"180626111117901","singleProductName":"鸡翅(大份)","foodType":"1","price":"15.00","picture":""},{"page":0,"rows":0,"shopNumber":"1000180614300325544","singleProductNumber":"CP824796","standardNumber":"180706111113019","singleProductName":"测试餐品(大个)","foodType":"1","price":"0.01","picture":""},{"page":0,"rows":0,"shopNumber":"1000180614300325544","singleProductNumber":"CP5E8CE1","standardNumber":"180625111111790","singleProductName":"德国鸡腿堡(大个)","foodType":"1","price":"5.00","picture":""}],"totalCount":44,"totalPage":5,"orderProperty":null,"orderDirection":null,"filters":[],"orders":[],"remark":null}
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
         * result : [{"page":0,"rows":0,"shopNumber":"1000180614300325544","singleProductNumber":"feb769cc-7cd1-4a97-b97c-508c64e3bbe0","standardNumber":"035af0a1-222a-4b87-8ed8-3132a3be0379","singleProductName":"充电器(条)","foodType":"1","price":"1.00","picture":"hsAppServer/20180727/6a91ef0a7b49456886f5bb61e1effdf4.png"},{"page":0,"rows":0,"shopNumber":"1000180614300325544","singleProductNumber":"f4f192e1-a58f-420d-990a-0b89c9efa521","standardNumber":"bb0f4a16-1f43-4866-b36c-62e68c0bbcd7","singleProductName":"痒痒鼠(小)","foodType":"1","price":"998.00","picture":"hsAppServer\\20180726\\2ad0066dd0f8490c94dd81492bcebc8b.png"},{"page":0,"rows":0,"shopNumber":"1000180614300325544","singleProductNumber":"ea5ca914-7dca-4269-b425-dda513d9fbba","standardNumber":"f8306237-ae6e-4410-90c2-f900b40427a1","singleProductName":"8成新苹果7(小)","foodType":"1","price":"666.00","picture":"hsAppServer/20180727/db65c2af657945179164b4bec90ca964.png"},{"page":0,"rows":0,"shopNumber":"1000180614300325544","singleProductNumber":"d42c5f04-e4eb-42d3-9a27-1531c280ed8d","standardNumber":"6571a10e-da4f-4991-8f9e-f5782fc71221","singleProductName":"测试单亲(大)","foodType":"1","price":"3.00","picture":"hsAppServer\\20180726\\c0c9089107c04b16a9c4990bae1e9773.png"},{"page":0,"rows":0,"shopNumber":"1000180614300325544","singleProductNumber":"d2e6bcf6-8bf8-4e70-ac7f-0b1079d7d95a","standardNumber":"0a56ede2-8630-4289-a441-043412f66443","singleProductName":"喵(喵一只)","foodType":"1","price":"666.00","picture":"hsAppServer/20180725/1ee2785b9a934e60800a3a055a583006.png"},{"page":0,"rows":0,"shopNumber":"1000180614300325544","singleProductNumber":"d0ccc711-3d41-47bb-9d4f-6782cb7ecc81","standardNumber":"961493e9-17a4-46b9-be24-4d42477a8ee9","singleProductName":"测试单品002(大)","foodType":"1","price":"0.01","picture":"hsAppServer/20180725/ca45e857530b425f8d295e2e08093f7c.png"},{"page":0,"rows":0,"shopNumber":"1000180614300325544","singleProductNumber":"CPFCAA61","standardNumber":"180625111111304","singleProductName":"可乐(大杯)","foodType":"1","price":"5.00","picture":""},{"page":0,"rows":0,"shopNumber":"1000180614300325544","singleProductNumber":"CP85ED31","standardNumber":"180626111117901","singleProductName":"鸡翅(大份)","foodType":"1","price":"15.00","picture":""},{"page":0,"rows":0,"shopNumber":"1000180614300325544","singleProductNumber":"CP824796","standardNumber":"180706111113019","singleProductName":"测试餐品(大个)","foodType":"1","price":"0.01","picture":""},{"page":0,"rows":0,"shopNumber":"1000180614300325544","singleProductNumber":"CP5E8CE1","standardNumber":"180625111111790","singleProductName":"德国鸡腿堡(大个)","foodType":"1","price":"5.00","picture":""}]
         * totalCount : 44
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
             * singleProductNumber : feb769cc-7cd1-4a97-b97c-508c64e3bbe0
             * standardNumber : 035af0a1-222a-4b87-8ed8-3132a3be0379
             * singleProductName : 充电器(条)
             * foodType : 1
             * price : 1.00
             * picture : hsAppServer/20180727/6a91ef0a7b49456886f5bb61e1effdf4.png
             */

            private int page;
            private int rows;
            private String shopNumber;
            private String singleProductNumber;
            private String standardNumber;
            private String singleProductName;
            private String foodType;
            private String price;
            private String picture;
            private String isScan;

            public String getIsScan() {
                return isScan;
            }

            public void setIsScan(String isScan) {
                this.isScan = isScan;
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

            public String getStandardNumber() {
                return standardNumber;
            }

            public void setStandardNumber(String standardNumber) {
                this.standardNumber = standardNumber;
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

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }
        }
    }
}
