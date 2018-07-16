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
     * data : {"package":[{"shopNumber":"1000180427300326890","foodProductsCount":"2","packageNumber":"TC810317","packageName":"豪华总统套餐","packagePrice":"98.00","packageList":[{"singleProductName":"芬达","standardName":"大杯"},{"singleProductName":"鸡翅","standardName":"一对"},{"singleProductName":"沙拉酱","standardName":"大包"},{"singleProductName":"特大份汉堡","standardName":"个"},{"singleProductName":"特大份薯条","standardName":"份"}]}],"food":[{"foodProductsCount":"2","shopNumber":"1000180427300326890","singleProductName":"可乐","standardNumber":"180427111118129","standardName":"小杯","sell":"5.00"}],"group":[{"foodProductsNumber":"ZH180608111114582","groupFood":[{"foodProductsNumber":"ZH180608111114582","foodProductsType":"2","foodProductsCount":"2","increasePrice":"0.0000","memberPreferences":"0.0000","discountAmount":"0.0000","costPrice":"0.0000","serialNumber":"1","antiNodeCount":"0","antiNode":"0","singleProductName":"江小白","standardName":"瓶（中）","groupNumber":"FZ180608111112751","minGroup":"2"},{"foodProductsNumber":"ZH180608111114582","foodProductsType":"2","foodProductsCount":"2","increasePrice":"0.0000","memberPreferences":"0.0000","discountAmount":"0.0000","costPrice":"0.0000","serialNumber":"1","antiNodeCount":"0","antiNode":"0","singleProductName":"热牛奶","standardName":"中杯","groupNumber":"FZ180608111112751","minGroup":"2"},{"foodProductsNumber":"ZH180608111114582","foodProductsType":"2","foodProductsCount":"2","increasePrice":"4.0000","memberPreferences":"0.0000","discountAmount":"0.0000","costPrice":"0.0000","serialNumber":"1","antiNodeCount":"0","antiNode":"0","singleProductName":"薯条","standardName":"小份","groupNumber":"FZ180608111113341","minGroup":"11"}],"foodProductsType":"2","foodProductsCount":"2","increasePrice":"0.0000","memberPreferences":"0.0000","discountAmount":"0.0000","costPrice":"0.0000","serialNumber":"1","antiNodeCount":"0","antiNode":"0","groupPackageName":"6.8高考组合","groupNumber":"FZ180608111112751","minGroup":"2","groupPackagePrice":"22.00"}],"order":{"deleteFlag":0,"page":0,"rows":0,"orderType":"已结帐","orderAmount":"24.00","amountReceivable":"24.00","amountCollected":"24.00","cashAmount":"0.00","orderDiscount":"0.00","insertTime":"2018-07-10 19:38:52.923","clerkName":"李晓鑫","batch":"20180710193853992497"}}
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
         * package : [{"shopNumber":"1000180427300326890","foodProductsCount":"2","packageNumber":"TC810317","packageName":"豪华总统套餐","packagePrice":"98.00","packageList":[{"singleProductName":"芬达","standardName":"大杯"},{"singleProductName":"鸡翅","standardName":"一对"},{"singleProductName":"沙拉酱","standardName":"大包"},{"singleProductName":"特大份汉堡","standardName":"个"},{"singleProductName":"特大份薯条","standardName":"份"}]}]
         * food : [{"foodProductsCount":"2","shopNumber":"1000180427300326890","singleProductName":"可乐","standardNumber":"180427111118129","standardName":"小杯","sell":"5.00"}]
         * group : [{"foodProductsNumber":"ZH180608111114582","groupFood":[{"foodProductsNumber":"ZH180608111114582","foodProductsType":"2","foodProductsCount":"2","increasePrice":"0.0000","memberPreferences":"0.0000","discountAmount":"0.0000","costPrice":"0.0000","serialNumber":"1","antiNodeCount":"0","antiNode":"0","singleProductName":"江小白","standardName":"瓶（中）","groupNumber":"FZ180608111112751","minGroup":"2"},{"foodProductsNumber":"ZH180608111114582","foodProductsType":"2","foodProductsCount":"2","increasePrice":"0.0000","memberPreferences":"0.0000","discountAmount":"0.0000","costPrice":"0.0000","serialNumber":"1","antiNodeCount":"0","antiNode":"0","singleProductName":"热牛奶","standardName":"中杯","groupNumber":"FZ180608111112751","minGroup":"2"},{"foodProductsNumber":"ZH180608111114582","foodProductsType":"2","foodProductsCount":"2","increasePrice":"4.0000","memberPreferences":"0.0000","discountAmount":"0.0000","costPrice":"0.0000","serialNumber":"1","antiNodeCount":"0","antiNode":"0","singleProductName":"薯条","standardName":"小份","groupNumber":"FZ180608111113341","minGroup":"11"}],"foodProductsType":"2","foodProductsCount":"2","increasePrice":"0.0000","memberPreferences":"0.0000","discountAmount":"0.0000","costPrice":"0.0000","serialNumber":"1","antiNodeCount":"0","antiNode":"0","groupPackageName":"6.8高考组合","groupNumber":"FZ180608111112751","minGroup":"2","groupPackagePrice":"22.00"}]
         * order : {"deleteFlag":0,"page":0,"rows":0,"orderType":"已结帐","orderAmount":"24.00","amountReceivable":"24.00","amountCollected":"24.00","cashAmount":"0.00","orderDiscount":"0.00","insertTime":"2018-07-10 19:38:52.923","clerkName":"李晓鑫","batch":"20180710193853992497"}
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
             * deleteFlag : 0
             * page : 0
             * rows : 0
             * orderType : 已结帐
             * orderAmount : 24.00
             * amountReceivable : 24.00
             * amountCollected : 24.00
             * cashAmount : 0.00
             * orderDiscount : 0.00
             * insertTime : 2018-07-10 19:38:52.923
             * clerkName : 李晓鑫
             * batch : 20180710193853992497
             */

            private int deleteFlag;
            private int page;
            private int rows;
            private String orderType;
            private String orderAmount;
            private String amountReceivable;
            private String amountCollected;
            private String cashAmount;
            private String orderDiscount;
            private String insertTime;
            private String clerkName;
            private String batch;
            private String orderSourcePayment;

            public String getOrderSourcePayment() {
                return orderSourcePayment;
            }

            public void setOrderSourcePayment(String orderSourcePayment) {
                this.orderSourcePayment = orderSourcePayment;
            }


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

            public String getClerkName() {
                return clerkName;
            }

            public void setClerkName(String clerkName) {
                this.clerkName = clerkName;
            }

            public String getBatch() {
                return batch;
            }

            public void setBatch(String batch) {
                this.batch = batch;
            }
        }

        public static class PackageBean {
            /**
             * shopNumber : 1000180427300326890
             * foodProductsCount : 2
             * packageNumber : TC810317
             * packageName : 豪华总统套餐
             * packagePrice : 98.00
             * packageList : [{"singleProductName":"芬达","standardName":"大杯"},{"singleProductName":"鸡翅","standardName":"一对"},{"singleProductName":"沙拉酱","standardName":"大包"},{"singleProductName":"特大份汉堡","standardName":"个"},{"singleProductName":"特大份薯条","standardName":"份"}]
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
                 * singleProductName : 芬达
                 * standardName : 大杯
                 */

                private String singleProductName;
                private String standardName;
                private String foodProductsCount;

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
             * foodProductsCount : 2
             * shopNumber : 1000180427300326890
             * singleProductName : 可乐
             * standardNumber : 180427111118129
             * standardName : 小杯
             * sell : 5.00
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
             * foodProductsNumber : ZH180608111114582
             * groupFood : [{"foodProductsNumber":"ZH180608111114582","foodProductsType":"2","foodProductsCount":"2","increasePrice":"0.0000","memberPreferences":"0.0000","discountAmount":"0.0000","costPrice":"0.0000","serialNumber":"1","antiNodeCount":"0","antiNode":"0","singleProductName":"江小白","standardName":"瓶（中）","groupNumber":"FZ180608111112751","minGroup":"2"},{"foodProductsNumber":"ZH180608111114582","foodProductsType":"2","foodProductsCount":"2","increasePrice":"0.0000","memberPreferences":"0.0000","discountAmount":"0.0000","costPrice":"0.0000","serialNumber":"1","antiNodeCount":"0","antiNode":"0","singleProductName":"热牛奶","standardName":"中杯","groupNumber":"FZ180608111112751","minGroup":"2"},{"foodProductsNumber":"ZH180608111114582","foodProductsType":"2","foodProductsCount":"2","increasePrice":"4.0000","memberPreferences":"0.0000","discountAmount":"0.0000","costPrice":"0.0000","serialNumber":"1","antiNodeCount":"0","antiNode":"0","singleProductName":"薯条","standardName":"小份","groupNumber":"FZ180608111113341","minGroup":"11"}]
             * foodProductsType : 2
             * foodProductsCount : 2
             * increasePrice : 0.0000
             * memberPreferences : 0.0000
             * discountAmount : 0.0000
             * costPrice : 0.0000
             * serialNumber : 1
             * antiNodeCount : 0
             * antiNode : 0
             * groupPackageName : 6.8高考组合
             * groupNumber : FZ180608111112751
             * minGroup : 2
             * groupPackagePrice : 22.00
             */

            private String foodProductsNumber;
            private String foodProductsType;
            private String foodProductsCount;
            private String increasePrice;
            private String memberPreferences;
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
                 * foodProductsNumber : ZH180608111114582
                 * foodProductsType : 2
                 * foodProductsCount : 2
                 * increasePrice : 0.0000
                 * memberPreferences : 0.0000
                 * discountAmount : 0.0000
                 * costPrice : 0.0000
                 * serialNumber : 1
                 * antiNodeCount : 0
                 * antiNode : 0
                 * singleProductName : 江小白
                 * standardName : 瓶（中）
                 * groupNumber : FZ180608111112751
                 * minGroup : 2
                 */

                private String foodProductsNumber;
                private String foodProductsType;
                private String foodProductsCount;
                private String increasePrice;
                private String memberPreferences;
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
