package com.hongsou.douguoshouyin.javabean;

import com.google.gson.annotations.SerializedName;

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


public class OrderDetailBean extends RootBean {


    /**
     * package : {"batch":"20180711165632272454","shopNumber":"1000180427300326890","packageNumber":null,"packageName":null,"packagePrice":null,"packageList":null}
     * food : {"batch":null,"shopNumber":"1000180427300326890","singleProductName":"泰国","standardNumber":"180621111113174","standardName":"小杯","sell":"4.0000"}
     * order : {"id":null,"createUser":null,"createUserName":null,"createTime":null,"updateUser":null,"updateTime":null,"deleteFlag":0,"queryString":null,"delIds":null,"delIdsList":null,"page":0,"rows":0,"orderProperty":null,"orderDirection":null,"supplyStoreList":null,"token":null,"pageString":null,"rowsString":null,"tradingTime":null,"endTime":null,"orderSource":null,"equipmentSource":null,"orderType":"已结帐","paymentType":null,"clerkNumber":null,"orderRemarks":null,"orderAmount":"4.00","amountReceivable":"4.00","amountCollected":"5.00","cashAmount":"1.00","memberPreferences":null,"rfundAmount":null,"rfundReason":null,"freeSingleReason":null,"antiNodeReason":null,"shopNumber":null,"equipmentNumber":null,"memberNumber":null,"meiTuanCouponCode":null,"dianPingCouponCode":null,"takeoutNumber":null,"foodProductsDiscount":null,"orderDiscount":"0.00","malingSystem":null,"insertTime":"2018-07-11 16:56:32.297","antiNode":null,"order":null,"freeSingleType":null,"cash":null,"paymentOrder":null,"orderSourcePayment":null,"regionNumber":null,"tabletableNumber":null,"discountPercentage":null,"customDiscount":null,"memberMoney":null,"clerkName":"李晓鑫","registeredCell":null,"orderSourceFlag":null,"batch":"20180711165632272454"}
     */

    @SerializedName("package")
    private PackageBean packageX;
    private FoodBean food;
    private OrderBean order;

    public PackageBean getPackageX() {
        return packageX;
    }

    public void setPackageX(PackageBean packageX) {
        this.packageX = packageX;
    }

    public FoodBean getFood() {
        return food;
    }

    public void setFood(FoodBean food) {
        this.food = food;
    }

    public OrderBean getOrder() {
        return order;
    }

    public void setOrder(OrderBean order) {
        this.order = order;
    }

    public static class PackageBean {
        /**
         * batch : 20180711165632272454
         * shopNumber : 1000180427300326890
         * packageNumber : null
         * packageName : null
         * packagePrice : null
         * packageList : null
         */

        private String batch;
        private String shopNumber;
        private String packageNumber;
        private String packageName;
        private String packagePrice;
        private String packageList;

        public String getBatch() {
            return batch;
        }

        public void setBatch(String batch) {
            this.batch = batch;
        }

        public String getShopNumber() {
            return shopNumber;
        }

        public void setShopNumber(String shopNumber) {
            this.shopNumber = shopNumber;
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

        public String getPackageList() {
            return packageList;
        }

        public void setPackageList(String packageList) {
            this.packageList = packageList;
        }
    }

    public static class FoodBean {
        /**
         * batch : null
         * shopNumber : 1000180427300326890
         * singleProductName : 泰国
         * standardNumber : 180621111113174
         * standardName : 小杯
         * sell : 4.0000
         */

        private String batch;
        private String shopNumber;
        private String singleProductName;
        private String standardNumber;
        private String standardName;
        private String sell;

        public String getBatch() {
            return batch;
        }

        public void setBatch(String batch) {
            this.batch = batch;
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

    public static class OrderBean {
        /**
         * id : null
         * createUser : null
         * createUserName : null
         * createTime : null
         * updateUser : null
         * updateTime : null
         * deleteFlag : 0
         * queryString : null
         * delIds : null
         * delIdsList : null
         * page : 0
         * rows : 0
         * orderProperty : null
         * orderDirection : null
         * supplyStoreList : null
         * token : null
         * pageString : null
         * rowsString : null
         * tradingTime : null
         * endTime : null
         * orderSource : null
         * equipmentSource : null
         * orderType : 已结帐
         * paymentType : null
         * clerkNumber : null
         * orderRemarks : null
         * orderAmount : 4.00
         * amountReceivable : 4.00
         * amountCollected : 5.00
         * cashAmount : 1.00
         * memberPreferences : null
         * rfundAmount : null
         * rfundReason : null
         * freeSingleReason : null
         * antiNodeReason : null
         * shopNumber : null
         * equipmentNumber : null
         * memberNumber : null
         * meiTuanCouponCode : null
         * dianPingCouponCode : null
         * takeoutNumber : null
         * foodProductsDiscount : null
         * orderDiscount : 0.00
         * malingSystem : null
         * insertTime : 2018-07-11 16:56:32.297
         * antiNode : null
         * order : null
         * freeSingleType : null
         * cash : null
         * paymentOrder : null
         * orderSourcePayment : null
         * regionNumber : null
         * tabletableNumber : null
         * discountPercentage : null
         * customDiscount : null
         * memberMoney : null
         * clerkName : 李晓鑫
         * registeredCell : null
         * orderSourceFlag : null
         * batch : 20180711165632272454
         */

        private String id;
        private String createUser;
        private String createUserName;
        private String createTime;
        private String updateUser;
        private String updateTime;
        private int deleteFlag;
        private String queryString;
        private String delIds;
        private String delIdsList;
        private int page;
        private int rows;
        private String orderProperty;
        private String orderDirection;
        private String supplyStoreList;
        private String token;
        private String pageString;
        private String rowsString;
        private String tradingTime;
        private String endTime;
        private String orderSource;
        private String equipmentSource;
        private String orderType;
        private String paymentType;
        private String clerkNumber;
        private String orderRemarks;
        private String orderAmount;
        private String amountReceivable;
        private String amountCollected;
        private String cashAmount;
        private String memberPreferences;
        private String rfundAmount;
        private String rfundReason;
        private String freeSingleReason;
        private String antiNodeReason;
        private String shopNumber;
        private String equipmentNumber;
        private String memberNumber;
        private String meiTuanCouponCode;
        private String dianPingCouponCode;
        private String takeoutNumber;
        private String foodProductsDiscount;
        private String orderDiscount;
        private String malingSystem;
        private String insertTime;
        private String antiNode;
        private String order;
        private String freeSingleType;
        private String cash;
        private String paymentOrder;
        private String orderSourcePayment;
        private String regionNumber;
        private String tabletableNumber;
        private String discountPercentage;
        private String customDiscount;
        private String memberMoney;
        private String clerkName;
        private String registeredCell;
        private String orderSourceFlag;
        private String batch;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreateUser() {
            return createUser;
        }

        public void setCreateUser(String createUser) {
            this.createUser = createUser;
        }

        public String getCreateUserName() {
            return createUserName;
        }

        public void setCreateUserName(String createUserName) {
            this.createUserName = createUserName;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateUser() {
            return updateUser;
        }

        public void setUpdateUser(String updateUser) {
            this.updateUser = updateUser;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getDeleteFlag() {
            return deleteFlag;
        }

        public void setDeleteFlag(int deleteFlag) {
            this.deleteFlag = deleteFlag;
        }

        public String getQueryString() {
            return queryString;
        }

        public void setQueryString(String queryString) {
            this.queryString = queryString;
        }

        public String getDelIds() {
            return delIds;
        }

        public void setDelIds(String delIds) {
            this.delIds = delIds;
        }

        public String getDelIdsList() {
            return delIdsList;
        }

        public void setDelIdsList(String delIdsList) {
            this.delIdsList = delIdsList;
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

        public String getOrderProperty() {
            return orderProperty;
        }

        public void setOrderProperty(String orderProperty) {
            this.orderProperty = orderProperty;
        }

        public String getOrderDirection() {
            return orderDirection;
        }

        public void setOrderDirection(String orderDirection) {
            this.orderDirection = orderDirection;
        }

        public String getSupplyStoreList() {
            return supplyStoreList;
        }

        public void setSupplyStoreList(String supplyStoreList) {
            this.supplyStoreList = supplyStoreList;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getPageString() {
            return pageString;
        }

        public void setPageString(String pageString) {
            this.pageString = pageString;
        }

        public String getRowsString() {
            return rowsString;
        }

        public void setRowsString(String rowsString) {
            this.rowsString = rowsString;
        }

        public String getTradingTime() {
            return tradingTime;
        }

        public void setTradingTime(String tradingTime) {
            this.tradingTime = tradingTime;
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

        public String getEquipmentSource() {
            return equipmentSource;
        }

        public void setEquipmentSource(String equipmentSource) {
            this.equipmentSource = equipmentSource;
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

        public String getClerkNumber() {
            return clerkNumber;
        }

        public void setClerkNumber(String clerkNumber) {
            this.clerkNumber = clerkNumber;
        }

        public String getOrderRemarks() {
            return orderRemarks;
        }

        public void setOrderRemarks(String orderRemarks) {
            this.orderRemarks = orderRemarks;
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

        public String getMemberPreferences() {
            return memberPreferences;
        }

        public void setMemberPreferences(String memberPreferences) {
            this.memberPreferences = memberPreferences;
        }

        public String getRfundAmount() {
            return rfundAmount;
        }

        public void setRfundAmount(String rfundAmount) {
            this.rfundAmount = rfundAmount;
        }

        public String getRfundReason() {
            return rfundReason;
        }

        public void setRfundReason(String rfundReason) {
            this.rfundReason = rfundReason;
        }

        public String getFreeSingleReason() {
            return freeSingleReason;
        }

        public void setFreeSingleReason(String freeSingleReason) {
            this.freeSingleReason = freeSingleReason;
        }

        public String getAntiNodeReason() {
            return antiNodeReason;
        }

        public void setAntiNodeReason(String antiNodeReason) {
            this.antiNodeReason = antiNodeReason;
        }

        public String getShopNumber() {
            return shopNumber;
        }

        public void setShopNumber(String shopNumber) {
            this.shopNumber = shopNumber;
        }

        public String getEquipmentNumber() {
            return equipmentNumber;
        }

        public void setEquipmentNumber(String equipmentNumber) {
            this.equipmentNumber = equipmentNumber;
        }

        public String getMemberNumber() {
            return memberNumber;
        }

        public void setMemberNumber(String memberNumber) {
            this.memberNumber = memberNumber;
        }

        public String getMeiTuanCouponCode() {
            return meiTuanCouponCode;
        }

        public void setMeiTuanCouponCode(String meiTuanCouponCode) {
            this.meiTuanCouponCode = meiTuanCouponCode;
        }

        public String getDianPingCouponCode() {
            return dianPingCouponCode;
        }

        public void setDianPingCouponCode(String dianPingCouponCode) {
            this.dianPingCouponCode = dianPingCouponCode;
        }

        public String getTakeoutNumber() {
            return takeoutNumber;
        }

        public void setTakeoutNumber(String takeoutNumber) {
            this.takeoutNumber = takeoutNumber;
        }

        public String getFoodProductsDiscount() {
            return foodProductsDiscount;
        }

        public void setFoodProductsDiscount(String foodProductsDiscount) {
            this.foodProductsDiscount = foodProductsDiscount;
        }

        public String getOrderDiscount() {
            return orderDiscount;
        }

        public void setOrderDiscount(String orderDiscount) {
            this.orderDiscount = orderDiscount;
        }

        public String getMalingSystem() {
            return malingSystem;
        }

        public void setMalingSystem(String malingSystem) {
            this.malingSystem = malingSystem;
        }

        public String getInsertTime() {
            return insertTime;
        }

        public void setInsertTime(String insertTime) {
            this.insertTime = insertTime;
        }

        public String getAntiNode() {
            return antiNode;
        }

        public void setAntiNode(String antiNode) {
            this.antiNode = antiNode;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }

        public String getFreeSingleType() {
            return freeSingleType;
        }

        public void setFreeSingleType(String freeSingleType) {
            this.freeSingleType = freeSingleType;
        }

        public String getCash() {
            return cash;
        }

        public void setCash(String cash) {
            this.cash = cash;
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

        public String getRegionNumber() {
            return regionNumber;
        }

        public void setRegionNumber(String regionNumber) {
            this.regionNumber = regionNumber;
        }

        public String getTabletableNumber() {
            return tabletableNumber;
        }

        public void setTabletableNumber(String tabletableNumber) {
            this.tabletableNumber = tabletableNumber;
        }

        public String getDiscountPercentage() {
            return discountPercentage;
        }

        public void setDiscountPercentage(String discountPercentage) {
            this.discountPercentage = discountPercentage;
        }

        public String getCustomDiscount() {
            return customDiscount;
        }

        public void setCustomDiscount(String customDiscount) {
            this.customDiscount = customDiscount;
        }

        public String getMemberMoney() {
            return memberMoney;
        }

        public void setMemberMoney(String memberMoney) {
            this.memberMoney = memberMoney;
        }

        public String getClerkName() {
            return clerkName;
        }

        public void setClerkName(String clerkName) {
            this.clerkName = clerkName;
        }

        public String getRegisteredCell() {
            return registeredCell;
        }

        public void setRegisteredCell(String registeredCell) {
            this.registeredCell = registeredCell;
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
    }
}
