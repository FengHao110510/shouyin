package com.hongsou.douguoshouyin.javabean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 版权：鸿搜网络公司 版权所有
 * <p>
 * 作者：冯大鱼
 * <p>
 * 版本：1.0
 * <p>
 * 创建日期：2018/7/13 0013
 * <p>
 * 描述：订单详情bean
 * <p>
 * 修订历史：
 */


public class OrderDetailBean {


    /**
     * code : 1000
     * msg : 服务成功
     * extInfo : null
     * data : {"package":[{"shopNumber":"1000180614300325544","foodProductsCount":"1","packageNumber":"40e8115a-896e-40bc-bb6b-dfc4d606231a","packageName":"这是个固定套餐","packagePrice":"0.01","packageList":[{"foodProductsCount":"1","singleProductName":"这是个单品","standardName":"大大"},{"foodProductsCount":"1","singleProductName":"这是个单品","standardName":"小小"}]}],"food":[{"foodProductsCount":"1","shopNumber":"1000180614300325544","singleProductName":"饮料 01","standardNumber":"6b5a00b0-8a38-4609-85f2-55fa16af49fc","standardName":"大","sell":"0.01"}],"group":[{"foodProductsNumber":"de0ba0e3-bb9a-4dbf-875d-6b05bf0a65e2","groupFood":[{"foodProductsNumber":"de0ba0e3-bb9a-4dbf-875d-6b05bf0a65e2","foodProductsType":"2","foodProductsCount":"1","increasePrice":"0.0000","memberPreferences":"0.0000","remarks":"","discountAmount":"0.0000","costPrice":"0.0000","serialNumber":"2","antiNodeCount":"0","antiNode":"0","singleProductName":"测试单品001","standardName":"大","groupNumber":"4322ef14-cade-4012-8599-f2d2cad6f491","minGroup":"1"},{"foodProductsNumber":"de0ba0e3-bb9a-4dbf-875d-6b05bf0a65e2","foodProductsType":"2","foodProductsCount":"1","increasePrice":"0.0000","memberPreferences":"0.0000","remarks":"","discountAmount":"0.0000","costPrice":"0.0000","serialNumber":"2","antiNodeCount":"0","antiNode":"0","singleProductName":"测试单品001","standardName":"中","groupNumber":"a2ae9435-8bb1-4a34-a1d1-b9613572fe03","minGroup":"1"}],"foodProductsType":"2","foodProductsCount":"1","increasePrice":"0.0000","memberPreferences":"0.0000","remarks":"","discountAmount":"0.0000","costPrice":"0.0000","serialNumber":"2","antiNodeCount":"0","antiNode":"0","groupPackageName":"组合套餐","groupNumber":"4322ef14-cade-4012-8599-f2d2cad6f491","minGroup":"1","groupPackagePrice":"0.01"}],"order":{"page":0,"rows":0,"orderType":"已结帐","orderState":1,"orderDetailsType":"2","paymentType":"微信支付","orderAmount":"0.01","amountReceivable":"0.01","amountCollected":"0.01","cashAmount":"0.00","orderDiscount":"0.00","insertTime":"2018-07-27","orderSourcePayment":"开单","orderSourceFlag":"FLASTE1","batch":"201807273711758371"}}
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
         * package : [{"shopNumber":"1000180614300325544","foodProductsCount":"1","packageNumber":"40e8115a-896e-40bc-bb6b-dfc4d606231a","packageName":"这是个固定套餐","packagePrice":"0.01","packageList":[{"foodProductsCount":"1","singleProductName":"这是个单品","standardName":"大大"},{"foodProductsCount":"1","singleProductName":"这是个单品","standardName":"小小"}]}]
         * food : [{"foodProductsCount":"1","shopNumber":"1000180614300325544","singleProductName":"饮料 01","standardNumber":"6b5a00b0-8a38-4609-85f2-55fa16af49fc","standardName":"大","sell":"0.01"}]
         * group : [{"foodProductsNumber":"de0ba0e3-bb9a-4dbf-875d-6b05bf0a65e2","groupFood":[{"foodProductsNumber":"de0ba0e3-bb9a-4dbf-875d-6b05bf0a65e2","foodProductsType":"2","foodProductsCount":"1","increasePrice":"0.0000","memberPreferences":"0.0000","remarks":"","discountAmount":"0.0000","costPrice":"0.0000","serialNumber":"2","antiNodeCount":"0","antiNode":"0","singleProductName":"测试单品001","standardName":"大","groupNumber":"4322ef14-cade-4012-8599-f2d2cad6f491","minGroup":"1"},{"foodProductsNumber":"de0ba0e3-bb9a-4dbf-875d-6b05bf0a65e2","foodProductsType":"2","foodProductsCount":"1","increasePrice":"0.0000","memberPreferences":"0.0000","remarks":"","discountAmount":"0.0000","costPrice":"0.0000","serialNumber":"2","antiNodeCount":"0","antiNode":"0","singleProductName":"测试单品001","standardName":"中","groupNumber":"a2ae9435-8bb1-4a34-a1d1-b9613572fe03","minGroup":"1"}],"foodProductsType":"2","foodProductsCount":"1","increasePrice":"0.0000","memberPreferences":"0.0000","remarks":"","discountAmount":"0.0000","costPrice":"0.0000","serialNumber":"2","antiNodeCount":"0","antiNode":"0","groupPackageName":"组合套餐","groupNumber":"4322ef14-cade-4012-8599-f2d2cad6f491","minGroup":"1","groupPackagePrice":"0.01"}]
         * order : {"page":0,"rows":0,"orderType":"已结帐","orderState":1,"orderDetailsType":"2","paymentType":"微信支付","orderAmount":"0.01","amountReceivable":"0.01","amountCollected":"0.01","cashAmount":"0.00","orderDiscount":"0.00","insertTime":"2018-07-27","orderSourcePayment":"开单","orderSourceFlag":"FLASTE1","batch":"201807273711758371"}
         */

        private OrderBean order;
        @SerializedName("package")
        private List<PackageBean> packageX;
        private List<FoodBean> food;
        private List<GroupBean> group;

        public OrderBean getOrder() {
            return order;
        }

        public void setOrder(OrderBean order) {
            this.order = order;
        }

        public List<PackageBean> getPackageX() {
            return packageX;
        }

        public void setPackageX(List<PackageBean> packageX) {
            this.packageX = packageX;
        }

        public List<FoodBean> getFood() {
            return food;
        }

        public void setFood(List<FoodBean> food) {
            this.food = food;
        }

        public List<GroupBean> getGroup() {
            return group;
        }

        public void setGroup(List<GroupBean> group) {
            this.group = group;
        }

        public static class OrderBean {
            /**
             * page : 0
             * rows : 0
             * orderType : 已结帐
             * orderState : 1
             * orderDetailsType : 2
             * paymentType : 微信支付
             * orderAmount : 0.01
             * amountReceivable : 0.01
             * amountCollected : 0.01
             * cashAmount : 0.00
             * orderDiscount : 0.00
             * insertTime : 2018-07-27
             * orderSourcePayment : 开单
             * orderSourceFlag : FLASTE1
             * batch : 201807273711758371
             * tableNumber : 0
             */

            private int page;
            private int rows;
            private String orderType;
            private int orderState;
            private String orderDetailsType;
            private String paymentType;
            private String orderAmount;
            private String amountReceivable;
            private String amountCollected;
            private String cashAmount;
            private String orderDiscount;
            private String insertTime;
            private String orderSourcePayment;
            private String orderSourceFlag;
            private String batch;
            private String dateOrderNumber;
            private String clerkName;
            private String tableNumber;
            private String registeredCell;

            public String getRegisteredCell() {
                return registeredCell;
            }

            public void setRegisteredCell(String registeredCell) {
                this.registeredCell = registeredCell;
            }

            public String getDateOrderNumber() {
                return dateOrderNumber;
            }

            public void setDateOrderNumber(String dateOrderNumber) {
                this.dateOrderNumber = dateOrderNumber;
            }

            public String getClerkName() {
                return clerkName;
            }

            public void setClerkName(String clerkName) {
                this.clerkName = clerkName;
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

            public String getOrderDetailsType() {
                return orderDetailsType;
            }

            public void setOrderDetailsType(String orderDetailsType) {
                this.orderDetailsType = orderDetailsType;
            }

            public String getPaymentType() {
                return paymentType;
            }

            public void setPaymentType(String paymentType) {
                this.paymentType = paymentType;
            }

            public String getOrderAmount() {
                return orderAmount;
            }

            public void setOrderAmount(String orderAmount) {
                this.orderAmount = orderAmount;
            }

            public String getAmountReceivable() {
                return amountReceivable;
            }

            public void setAmountReceivable(String amountReceivable) {
                this.amountReceivable = amountReceivable;
            }

            public String getAmountCollected() {
                return amountCollected;
            }

            public void setAmountCollected(String amountCollected) {
                this.amountCollected = amountCollected;
            }

            public String getCashAmount() {
                return cashAmount;
            }

            public void setCashAmount(String cashAmount) {
                this.cashAmount = cashAmount;
            }

            public String getOrderDiscount() {
                return orderDiscount;
            }

            public void setOrderDiscount(String orderDiscount) {
                this.orderDiscount = orderDiscount;
            }

            public String getInsertTime() {
                return insertTime;
            }

            public void setInsertTime(String insertTime) {
                this.insertTime = insertTime;
            }

            public String getOrderSourcePayment() {
                return orderSourcePayment;
            }

            public void setOrderSourcePayment(String orderSourcePayment) {
                this.orderSourcePayment = orderSourcePayment;
            }

            public String getOrderSourceFlag() {
                return orderSourceFlag;
            }

            public void setOrderSourceFlag(String orderSourceFlag) {
                this.orderSourceFlag = orderSourceFlag;
            }

            public String getBatch() {
                return batch;
            }

            public void setBatch(String batch) {
                this.batch = batch;
            }

            public String getTableNumber() {
                return tableNumber;
            }

            public void setTableNumber(String tableNumber) {
                this.tableNumber = tableNumber;
            }
        }

        public static class PackageBean {
            /**
             * shopNumber : 1000180614300325544
             * foodProductsCount : 1
             * packageNumber : 40e8115a-896e-40bc-bb6b-dfc4d606231a
             * packageName : 这是个固定套餐
             * packagePrice : 0.01
             * packageList : [{"foodProductsCount":"1","singleProductName":"这是个单品","standardName":"大大"},{"foodProductsCount":"1","singleProductName":"这是个单品","standardName":"小小"}]
             */

            private String shopNumber;
            private String foodProductsCount;
            private String packageNumber;
            private String packageName;
            private String packagePrice;
            private List<PackageListBean> packageList;

            public String getShopNumber() {
                return shopNumber;
            }

            public void setShopNumber(String shopNumber) {
                this.shopNumber = shopNumber;
            }

            public String getFoodProductsCount() {
                return foodProductsCount;
            }

            public void setFoodProductsCount(String foodProductsCount) {
                this.foodProductsCount = foodProductsCount;
            }

            public String getPackageNumber() {
                return packageNumber;
            }

            public void setPackageNumber(String packageNumber) {
                this.packageNumber = packageNumber;
            }

            public String getPackageName() {
                return packageName;
            }

            public void setPackageName(String packageName) {
                this.packageName = packageName;
            }

            public String getPackagePrice() {
                return packagePrice;
            }

            public void setPackagePrice(String packagePrice) {
                this.packagePrice = packagePrice;
            }

            public List<PackageListBean> getPackageList() {
                return packageList;
            }

            public void setPackageList(List<PackageListBean> packageList) {
                this.packageList = packageList;
            }

            public static class PackageListBean {
                /**
                 * foodProductsCount : 1
                 * singleProductName : 这是个单品
                 * standardName : 大大
                 */

                private String foodProductsCount;
                private String singleProductName;
                private String standardName;

                public String getFoodProductsCount() {
                    return foodProductsCount;
                }

                public void setFoodProductsCount(String foodProductsCount) {
                    this.foodProductsCount = foodProductsCount;
                }

                public String getSingleProductName() {
                    return singleProductName;
                }

                public void setSingleProductName(String singleProductName) {
                    this.singleProductName = singleProductName;
                }

                public String getStandardName() {
                    return standardName;
                }

                public void setStandardName(String standardName) {
                    this.standardName = standardName;
                }
            }
        }

        public static class FoodBean {
            /**
             * foodProductsCount : 1
             * shopNumber : 1000180614300325544
             * singleProductName : 饮料 01
             * standardNumber : 6b5a00b0-8a38-4609-85f2-55fa16af49fc
             * standardName : 大
             * sell : 0.01
             */

            private String foodProductsCount;
            private String shopNumber;
            private String singleProductName;
            private String standardNumber;
            private String standardName;
            private String sell;

            public String getFoodProductsCount() {
                return foodProductsCount;
            }

            public void setFoodProductsCount(String foodProductsCount) {
                this.foodProductsCount = foodProductsCount;
            }

            public String getShopNumber() {
                return shopNumber;
            }

            public void setShopNumber(String shopNumber) {
                this.shopNumber = shopNumber;
            }

            public String getSingleProductName() {
                return singleProductName;
            }

            public void setSingleProductName(String singleProductName) {
                this.singleProductName = singleProductName;
            }

            public String getStandardNumber() {
                return standardNumber;
            }

            public void setStandardNumber(String standardNumber) {
                this.standardNumber = standardNumber;
            }

            public String getStandardName() {
                return standardName;
            }

            public void setStandardName(String standardName) {
                this.standardName = standardName;
            }

            public String getSell() {
                return sell;
            }

            public void setSell(String sell) {
                this.sell = sell;
            }
        }

        public static class GroupBean {
            /**
             * foodProductsNumber : de0ba0e3-bb9a-4dbf-875d-6b05bf0a65e2
             * groupFood : [{"foodProductsNumber":"de0ba0e3-bb9a-4dbf-875d-6b05bf0a65e2","foodProductsType":"2","foodProductsCount":"1","increasePrice":"0.0000","memberPreferences":"0.0000","remarks":"","discountAmount":"0.0000","costPrice":"0.0000","serialNumber":"2","antiNodeCount":"0","antiNode":"0","singleProductName":"测试单品001","standardName":"大","groupNumber":"4322ef14-cade-4012-8599-f2d2cad6f491","minGroup":"1"},{"foodProductsNumber":"de0ba0e3-bb9a-4dbf-875d-6b05bf0a65e2","foodProductsType":"2","foodProductsCount":"1","increasePrice":"0.0000","memberPreferences":"0.0000","remarks":"","discountAmount":"0.0000","costPrice":"0.0000","serialNumber":"2","antiNodeCount":"0","antiNode":"0","singleProductName":"测试单品001","standardName":"中","groupNumber":"a2ae9435-8bb1-4a34-a1d1-b9613572fe03","minGroup":"1"}]
             * foodProductsType : 2
             * foodProductsCount : 1
             * increasePrice : 0.0000
             * memberPreferences : 0.0000
             * remarks :
             * discountAmount : 0.0000
             * costPrice : 0.0000
             * serialNumber : 2
             * antiNodeCount : 0
             * antiNode : 0
             * groupPackageName : 组合套餐
             * groupNumber : 4322ef14-cade-4012-8599-f2d2cad6f491
             * minGroup : 1
             * groupPackagePrice : 0.01
             */

            private String foodProductsNumber;
            private String foodProductsType;
            private String foodProductsCount;
            private String increasePrice;
            private String memberPreferences;
            private String remarks;
            private String discountAmount;
            private String costPrice;
            private String serialNumber;
            private String antiNodeCount;
            private String antiNode;
            private String groupPackageName;
            private String groupNumber;
            private String minGroup;
            private String groupPackagePrice;
            private List<GroupFoodBean> groupFood;

            public String getFoodProductsNumber() {
                return foodProductsNumber;
            }

            public void setFoodProductsNumber(String foodProductsNumber) {
                this.foodProductsNumber = foodProductsNumber;
            }

            public String getFoodProductsType() {
                return foodProductsType;
            }

            public void setFoodProductsType(String foodProductsType) {
                this.foodProductsType = foodProductsType;
            }

            public String getFoodProductsCount() {
                return foodProductsCount;
            }

            public void setFoodProductsCount(String foodProductsCount) {
                this.foodProductsCount = foodProductsCount;
            }

            public String getIncreasePrice() {
                return increasePrice;
            }

            public void setIncreasePrice(String increasePrice) {
                this.increasePrice = increasePrice;
            }

            public String getMemberPreferences() {
                return memberPreferences;
            }

            public void setMemberPreferences(String memberPreferences) {
                this.memberPreferences = memberPreferences;
            }

            public String getRemarks() {
                return remarks;
            }

            public void setRemarks(String remarks) {
                this.remarks = remarks;
            }

            public String getDiscountAmount() {
                return discountAmount;
            }

            public void setDiscountAmount(String discountAmount) {
                this.discountAmount = discountAmount;
            }

            public String getCostPrice() {
                return costPrice;
            }

            public void setCostPrice(String costPrice) {
                this.costPrice = costPrice;
            }

            public String getSerialNumber() {
                return serialNumber;
            }

            public void setSerialNumber(String serialNumber) {
                this.serialNumber = serialNumber;
            }

            public String getAntiNodeCount() {
                return antiNodeCount;
            }

            public void setAntiNodeCount(String antiNodeCount) {
                this.antiNodeCount = antiNodeCount;
            }

            public String getAntiNode() {
                return antiNode;
            }

            public void setAntiNode(String antiNode) {
                this.antiNode = antiNode;
            }

            public String getGroupPackageName() {
                return groupPackageName;
            }

            public void setGroupPackageName(String groupPackageName) {
                this.groupPackageName = groupPackageName;
            }

            public String getGroupNumber() {
                return groupNumber;
            }

            public void setGroupNumber(String groupNumber) {
                this.groupNumber = groupNumber;
            }

            public String getMinGroup() {
                return minGroup;
            }

            public void setMinGroup(String minGroup) {
                this.minGroup = minGroup;
            }

            public String getGroupPackagePrice() {
                return groupPackagePrice;
            }

            public void setGroupPackagePrice(String groupPackagePrice) {
                this.groupPackagePrice = groupPackagePrice;
            }

            public List<GroupFoodBean> getGroupFood() {
                return groupFood;
            }

            public void setGroupFood(List<GroupFoodBean> groupFood) {
                this.groupFood = groupFood;
            }

            public static class GroupFoodBean {
                /**
                 * foodProductsNumber : de0ba0e3-bb9a-4dbf-875d-6b05bf0a65e2
                 * foodProductsType : 2
                 * foodProductsCount : 1
                 * increasePrice : 0.0000
                 * memberPreferences : 0.0000
                 * remarks :
                 * discountAmount : 0.0000
                 * costPrice : 0.0000
                 * serialNumber : 2
                 * antiNodeCount : 0
                 * antiNode : 0
                 * singleProductName : 测试单品001
                 * standardName : 大
                 * groupNumber : 4322ef14-cade-4012-8599-f2d2cad6f491
                 * minGroup : 1
                 */

                private String foodProductsNumber;
                private String foodProductsType;
                private String foodProductsCount;
                private String increasePrice;
                private String memberPreferences;
                private String remarks;
                private String discountAmount;
                private String costPrice;
                private String serialNumber;
                private String antiNodeCount;
                private String antiNode;
                private String singleProductName;
                private String standardName;
                private String groupNumber;
                private String minGroup;

                public String getFoodProductsNumber() {
                    return foodProductsNumber;
                }

                public void setFoodProductsNumber(String foodProductsNumber) {
                    this.foodProductsNumber = foodProductsNumber;
                }

                public String getFoodProductsType() {
                    return foodProductsType;
                }

                public void setFoodProductsType(String foodProductsType) {
                    this.foodProductsType = foodProductsType;
                }

                public String getFoodProductsCount() {
                    return foodProductsCount;
                }

                public void setFoodProductsCount(String foodProductsCount) {
                    this.foodProductsCount = foodProductsCount;
                }

                public String getIncreasePrice() {
                    return increasePrice;
                }

                public void setIncreasePrice(String increasePrice) {
                    this.increasePrice = increasePrice;
                }

                public String getMemberPreferences() {
                    return memberPreferences;
                }

                public void setMemberPreferences(String memberPreferences) {
                    this.memberPreferences = memberPreferences;
                }

                public String getRemarks() {
                    return remarks;
                }

                public void setRemarks(String remarks) {
                    this.remarks = remarks;
                }

                public String getDiscountAmount() {
                    return discountAmount;
                }

                public void setDiscountAmount(String discountAmount) {
                    this.discountAmount = discountAmount;
                }

                public String getCostPrice() {
                    return costPrice;
                }

                public void setCostPrice(String costPrice) {
                    this.costPrice = costPrice;
                }

                public String getSerialNumber() {
                    return serialNumber;
                }

                public void setSerialNumber(String serialNumber) {
                    this.serialNumber = serialNumber;
                }

                public String getAntiNodeCount() {
                    return antiNodeCount;
                }

                public void setAntiNodeCount(String antiNodeCount) {
                    this.antiNodeCount = antiNodeCount;
                }

                public String getAntiNode() {
                    return antiNode;
                }

                public void setAntiNode(String antiNode) {
                    this.antiNode = antiNode;
                }

                public String getSingleProductName() {
                    return singleProductName;
                }

                public void setSingleProductName(String singleProductName) {
                    this.singleProductName = singleProductName;
                }

                public String getStandardName() {
                    return standardName;
                }

                public void setStandardName(String standardName) {
                    this.standardName = standardName;
                }

                public String getGroupNumber() {
                    return groupNumber;
                }

                public void setGroupNumber(String groupNumber) {
                    this.groupNumber = groupNumber;
                }

                public String getMinGroup() {
                    return minGroup;
                }

                public void setMinGroup(String minGroup) {
                    this.minGroup = minGroup;
                }
            }
        }
    }
}
