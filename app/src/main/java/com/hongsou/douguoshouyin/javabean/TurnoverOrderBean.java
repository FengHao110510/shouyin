package com.hongsou.douguoshouyin.javabean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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


public class TurnoverOrderBean extends RootBean{


    /**
     * page : 1
     * limit : 10
     * data : [{"id":null,"createUser":null,"createUserName":null,"createTime":null,"updateUser":null,"updateTime":null,"deleteFlag":0,"queryString":null,"delIds":null,"delIdsList":null,"page":0,"rows":0,"orderProperty":null,"orderDirection":null,"supplyStoreList":null,"token":null,"pageString":null,"rowsString":null,"tradingTime":null,"endTime":"2018-07-12 17:35:28.003","orderSource":null,"equipmentSource":null,"orderType":"已结帐","paymentType":null,"clerkNumber":null,"orderRemarks":null,"orderAmount":"0.01","amountReceivable":null,"amountCollected":null,"cashAmount":null,"memberPreferences":null,"rfundAmount":null,"rfundReason":null,"freeSingleReason":null,"antiNodeReason":null,"shopNumber":null,"equipmentNumber":null,"memberNumber":null,"meiTuanCouponCode":null,"dianPingCouponCode":null,"takeoutNumber":null,"foodProductsDiscount":null,"orderDiscount":null,"malingSystem":null,"insertTime":null,"antiNode":null,"order":null,"freeSingleType":null,"cash":null,"paymentOrder":null,"orderSourcePayment":null,"regionNumber":null,"tabletableNumber":null,"discountPercentage":null,"customDiscount":null,"memberMoney":null,"clerkName":null,"registeredCell":null,"orderSourceFlag":null,"batch":"20180712173528498360"},{"id":null,"createUser":null,"createUserName":null,"createTime":null,"updateUser":null,"updateTime":null,"deleteFlag":0,"queryString":null,"delIds":null,"delIdsList":null,"page":0,"rows":0,"orderProperty":null,"orderDirection":null,"supplyStoreList":null,"token":null,"pageString":null,"rowsString":null,"tradingTime":null,"endTime":"2018-07-11 16:56:32.003","orderSource":null,"equipmentSource":null,"orderType":"已结帐","paymentType":null,"clerkNumber":null,"orderRemarks":null,"orderAmount":"4.00","amountReceivable":null,"amountCollected":null,"cashAmount":null,"memberPreferences":null,"rfundAmount":null,"rfundReason":null,"freeSingleReason":null,"antiNodeReason":null,"shopNumber":null,"equipmentNumber":null,"memberNumber":null,"meiTuanCouponCode":null,"dianPingCouponCode":null,"takeoutNumber":null,"foodProductsDiscount":null,"orderDiscount":null,"malingSystem":null,"insertTime":null,"antiNode":null,"order":null,"freeSingleType":null,"cash":null,"paymentOrder":null,"orderSourcePayment":null,"regionNumber":null,"tabletableNumber":null,"discountPercentage":null,"customDiscount":null,"memberMoney":null,"clerkName":null,"registeredCell":null,"orderSourceFlag":null,"batch":"20180711165632272454"},{"id":null,"createUser":null,"createUserName":null,"createTime":null,"updateUser":null,"updateTime":null,"deleteFlag":0,"queryString":null,"delIds":null,"delIdsList":null,"page":0,"rows":0,"orderProperty":null,"orderDirection":null,"supplyStoreList":null,"token":null,"pageString":null,"rowsString":null,"tradingTime":null,"endTime":"2018-07-10 19:37:45.003","orderSource":null,"equipmentSource":null,"orderType":"已结帐","paymentType":null,"clerkNumber":null,"orderRemarks":null,"orderAmount":"24.00","amountReceivable":null,"amountCollected":null,"cashAmount":null,"memberPreferences":null,"rfundAmount":null,"rfundReason":null,"freeSingleReason":null,"antiNodeReason":null,"shopNumber":null,"equipmentNumber":null,"memberNumber":null,"meiTuanCouponCode":null,"dianPingCouponCode":null,"takeoutNumber":null,"foodProductsDiscount":null,"orderDiscount":null,"malingSystem":null,"insertTime":null,"antiNode":null,"order":null,"freeSingleType":null,"cash":null,"paymentOrder":null,"orderSourcePayment":null,"regionNumber":null,"tabletableNumber":null,"discountPercentage":null,"customDiscount":null,"memberMoney":null,"clerkName":null,"registeredCell":null,"orderSourceFlag":null,"batch":"20180710193853992497"},{"id":null,"createUser":null,"createUserName":null,"createTime":null,"updateUser":null,"updateTime":null,"deleteFlag":0,"queryString":null,"delIds":null,"delIdsList":null,"page":0,"rows":0,"orderProperty":null,"orderDirection":null,"supplyStoreList":null,"token":null,"pageString":null,"rowsString":null,"tradingTime":null,"endTime":"2018-07-10 19:37:29.003","orderSource":null,"equipmentSource":null,"orderType":"已结帐","paymentType":null,"clerkNumber":null,"orderRemarks":null,"orderAmount":"101.00","amountReceivable":null,"amountCollected":null,"cashAmount":null,"memberPreferences":null,"rfundAmount":null,"rfundReason":null,"freeSingleReason":null,"antiNodeReason":null,"shopNumber":null,"equipmentNumber":null,"memberNumber":null,"meiTuanCouponCode":null,"dianPingCouponCode":null,"takeoutNumber":null,"foodProductsDiscount":null,"orderDiscount":null,"malingSystem":null,"insertTime":null,"antiNode":null,"order":null,"freeSingleType":null,"cash":null,"paymentOrder":null,"orderSourcePayment":null,"regionNumber":null,"tabletableNumber":null,"discountPercentage":null,"customDiscount":null,"memberMoney":null,"clerkName":null,"registeredCell":null,"orderSourceFlag":null,"batch":"20180710193838657672"},{"id":null,"createUser":null,"createUserName":null,"createTime":null,"updateUser":null,"updateTime":null,"deleteFlag":0,"queryString":null,"delIds":null,"delIdsList":null,"page":0,"rows":0,"orderProperty":null,"orderDirection":null,"supplyStoreList":null,"token":null,"pageString":null,"rowsString":null,"tradingTime":null,"endTime":"2018-07-10 19:35:15.003","orderSource":null,"equipmentSource":null,"orderType":"已结帐","paymentType":null,"clerkNumber":null,"orderRemarks":null,"orderAmount":"104.00","amountReceivable":null,"amountCollected":null,"cashAmount":null,"memberPreferences":null,"rfundAmount":null,"rfundReason":null,"freeSingleReason":null,"antiNodeReason":null,"shopNumber":null,"equipmentNumber":null,"memberNumber":null,"meiTuanCouponCode":null,"dianPingCouponCode":null,"takeoutNumber":null,"foodProductsDiscount":null,"orderDiscount":null,"malingSystem":null,"insertTime":null,"antiNode":null,"order":null,"freeSingleType":null,"cash":null,"paymentOrder":null,"orderSourcePayment":null,"regionNumber":null,"tabletableNumber":null,"discountPercentage":null,"customDiscount":null,"memberMoney":null,"clerkName":null,"registeredCell":null,"orderSourceFlag":null,"batch":"2018071019362470460"},{"id":null,"createUser":null,"createUserName":null,"createTime":null,"updateUser":null,"updateTime":null,"deleteFlag":0,"queryString":null,"delIds":null,"delIdsList":null,"page":0,"rows":0,"orderProperty":null,"orderDirection":null,"supplyStoreList":null,"token":null,"pageString":null,"rowsString":null,"tradingTime":null,"endTime":"2018-07-10 19:34:37.003","orderSource":null,"equipmentSource":null,"orderType":"已结帐","paymentType":null,"clerkNumber":null,"orderRemarks":null,"orderAmount":"101.00","amountReceivable":null,"amountCollected":null,"cashAmount":null,"memberPreferences":null,"rfundAmount":null,"rfundReason":null,"freeSingleReason":null,"antiNodeReason":null,"shopNumber":null,"equipmentNumber":null,"memberNumber":null,"meiTuanCouponCode":null,"dianPingCouponCode":null,"takeoutNumber":null,"foodProductsDiscount":null,"orderDiscount":null,"malingSystem":null,"insertTime":null,"antiNode":null,"order":null,"freeSingleType":null,"cash":null,"paymentOrder":null,"orderSourcePayment":null,"regionNumber":null,"tabletableNumber":null,"discountPercentage":null,"customDiscount":null,"memberMoney":null,"clerkName":null,"registeredCell":null,"orderSourceFlag":null,"batch":"20180710193546694231"},{"id":null,"createUser":null,"createUserName":null,"createTime":null,"updateUser":null,"updateTime":null,"deleteFlag":0,"queryString":null,"delIds":null,"delIdsList":null,"page":0,"rows":0,"orderProperty":null,"orderDirection":null,"supplyStoreList":null,"token":null,"pageString":null,"rowsString":null,"tradingTime":null,"endTime":"2018-07-10 19:30:00.003","orderSource":null,"equipmentSource":null,"orderType":"已结帐","paymentType":null,"clerkNumber":null,"orderRemarks":null,"orderAmount":"48.00","amountReceivable":null,"amountCollected":null,"cashAmount":null,"memberPreferences":null,"rfundAmount":null,"rfundReason":null,"freeSingleReason":null,"antiNodeReason":null,"shopNumber":null,"equipmentNumber":null,"memberNumber":null,"meiTuanCouponCode":null,"dianPingCouponCode":null,"takeoutNumber":null,"foodProductsDiscount":null,"orderDiscount":null,"malingSystem":null,"insertTime":null,"antiNode":null,"order":null,"freeSingleType":null,"cash":null,"paymentOrder":null,"orderSourcePayment":null,"regionNumber":null,"tabletableNumber":null,"discountPercentage":null,"customDiscount":null,"memberMoney":null,"clerkName":null,"registeredCell":null,"orderSourceFlag":null,"batch":"20180710193108666695"},{"id":null,"createUser":null,"createUserName":null,"createTime":null,"updateUser":null,"updateTime":null,"deleteFlag":0,"queryString":null,"delIds":null,"delIdsList":null,"page":0,"rows":0,"orderProperty":null,"orderDirection":null,"supplyStoreList":null,"token":null,"pageString":null,"rowsString":null,"tradingTime":null,"endTime":"2018-07-10 19:27:34.003","orderSource":null,"equipmentSource":null,"orderType":"已结帐","paymentType":null,"clerkNumber":null,"orderRemarks":null,"orderAmount":"200.00","amountReceivable":null,"amountCollected":null,"cashAmount":null,"memberPreferences":null,"rfundAmount":null,"rfundReason":null,"freeSingleReason":null,"antiNodeReason":null,"shopNumber":null,"equipmentNumber":null,"memberNumber":null,"meiTuanCouponCode":null,"dianPingCouponCode":null,"takeoutNumber":null,"foodProductsDiscount":null,"orderDiscount":null,"malingSystem":null,"insertTime":null,"antiNode":null,"order":null,"freeSingleType":null,"cash":null,"paymentOrder":null,"orderSourcePayment":null,"regionNumber":null,"tabletableNumber":null,"discountPercentage":null,"customDiscount":null,"memberMoney":null,"clerkName":null,"registeredCell":null,"orderSourceFlag":null,"batch":"20180710192843241879"},{"id":null,"createUser":null,"createUserName":null,"createTime":null,"updateUser":null,"updateTime":null,"deleteFlag":0,"queryString":null,"delIds":null,"delIdsList":null,"page":0,"rows":0,"orderProperty":null,"orderDirection":null,"supplyStoreList":null,"token":null,"pageString":null,"rowsString":null,"tradingTime":null,"endTime":"2018-07-10 19:16:28.003","orderSource":null,"equipmentSource":null,"orderType":"已结帐","paymentType":null,"clerkNumber":null,"orderRemarks":null,"orderAmount":"200.00","amountReceivable":null,"amountCollected":null,"cashAmount":null,"memberPreferences":null,"rfundAmount":null,"rfundReason":null,"freeSingleReason":null,"antiNodeReason":null,"shopNumber":null,"equipmentNumber":null,"memberNumber":null,"meiTuanCouponCode":null,"dianPingCouponCode":null,"takeoutNumber":null,"foodProductsDiscount":null,"orderDiscount":null,"malingSystem":null,"insertTime":null,"antiNode":null,"order":null,"freeSingleType":null,"cash":null,"paymentOrder":null,"orderSourcePayment":null,"regionNumber":null,"tabletableNumber":null,"discountPercentage":null,"customDiscount":null,"memberMoney":null,"clerkName":null,"registeredCell":null,"orderSourceFlag":null,"batch":"20180710191737544285"},{"id":null,"createUser":null,"createUserName":null,"createTime":null,"updateUser":null,"updateTime":null,"deleteFlag":0,"queryString":null,"delIds":null,"delIdsList":null,"page":0,"rows":0,"orderProperty":null,"orderDirection":null,"supplyStoreList":null,"token":null,"pageString":null,"rowsString":null,"tradingTime":null,"endTime":"2018-07-10 19:02:48.003","orderSource":null,"equipmentSource":null,"orderType":"已结帐","paymentType":null,"clerkNumber":null,"orderRemarks":null,"orderAmount":"30.00","amountReceivable":null,"amountCollected":null,"cashAmount":null,"memberPreferences":null,"rfundAmount":null,"rfundReason":null,"freeSingleReason":null,"antiNodeReason":null,"shopNumber":null,"equipmentNumber":null,"memberNumber":null,"meiTuanCouponCode":null,"dianPingCouponCode":null,"takeoutNumber":null,"foodProductsDiscount":null,"orderDiscount":null,"malingSystem":null,"insertTime":null,"antiNode":null,"order":null,"freeSingleType":null,"cash":null,"paymentOrder":null,"orderSourcePayment":null,"regionNumber":null,"tabletableNumber":null,"discountPercentage":null,"customDiscount":null,"memberMoney":null,"clerkName":null,"registeredCell":null,"orderSourceFlag":null,"batch":"20180710190357305190"}]
     * totalCount : 708
     * totalPage : 71
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
    @Expose
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
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
         * endTime : 2018-07-12 17:35:28.003
         * orderSource : null
         * equipmentSource : null
         * orderType : 已结帐
         * paymentType : null
         * clerkNumber : null
         * orderRemarks : null
         * orderAmount : 0.01
         * amountReceivable : null
         * amountCollected : null
         * cashAmount : null
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
         * orderDiscount : null
         * malingSystem : null
         * insertTime : null
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
         * clerkName : null
         * registeredCell : null
         * orderSourceFlag : null
         * batch : 20180712173528498360
         */

        private Object id;
        private Object createUser;
        private Object createUserName;
        private Object createTime;
        private Object updateUser;
        private Object updateTime;
        private int deleteFlag;
        private Object queryString;
        private Object delIds;
        private Object delIdsList;
        private int page;
        private int rows;
        private Object orderProperty;
        private Object orderDirection;
        private Object supplyStoreList;
        private Object token;
        private Object pageString;
        private Object rowsString;
        private Object tradingTime;
        private String endTime;
        private Object orderSource;
        private Object equipmentSource;
        private String orderType;
        private Object paymentType;
        private Object clerkNumber;
        private Object orderRemarks;
        private String orderAmount;
        private Object amountReceivable;
        private Object amountCollected;
        private Object cashAmount;
        private Object memberPreferences;
        private Object rfundAmount;
        private Object rfundReason;
        private Object freeSingleReason;
        private Object antiNodeReason;
        private Object shopNumber;
        private Object equipmentNumber;
        private Object memberNumber;
        private Object meiTuanCouponCode;
        private Object dianPingCouponCode;
        private Object takeoutNumber;
        private Object foodProductsDiscount;
        private Object orderDiscount;
        private Object malingSystem;
        private Object insertTime;
        private Object antiNode;
        private Object order;
        private Object freeSingleType;
        private Object cash;
        private Object paymentOrder;
        private Object orderSourcePayment;
        private Object regionNumber;
        private Object tabletableNumber;
        private Object discountPercentage;
        private Object customDiscount;
        private Object memberMoney;
        private Object clerkName;
        private Object registeredCell;
        private Object orderSourceFlag;
        private String batch;

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
            this.id = id;
        }

        public Object getCreateUser() {
            return createUser;
        }

        public void setCreateUser(Object createUser) {
            this.createUser = createUser;
        }

        public Object getCreateUserName() {
            return createUserName;
        }

        public void setCreateUserName(Object createUserName) {
            this.createUserName = createUserName;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateUser() {
            return updateUser;
        }

        public void setUpdateUser(Object updateUser) {
            this.updateUser = updateUser;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public int getDeleteFlag() {
            return deleteFlag;
        }

        public void setDeleteFlag(int deleteFlag) {
            this.deleteFlag = deleteFlag;
        }

        public Object getQueryString() {
            return queryString;
        }

        public void setQueryString(Object queryString) {
            this.queryString = queryString;
        }

        public Object getDelIds() {
            return delIds;
        }

        public void setDelIds(Object delIds) {
            this.delIds = delIds;
        }

        public Object getDelIdsList() {
            return delIdsList;
        }

        public void setDelIdsList(Object delIdsList) {
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

        public Object getSupplyStoreList() {
            return supplyStoreList;
        }

        public void setSupplyStoreList(Object supplyStoreList) {
            this.supplyStoreList = supplyStoreList;
        }

        public Object getToken() {
            return token;
        }

        public void setToken(Object token) {
            this.token = token;
        }

        public Object getPageString() {
            return pageString;
        }

        public void setPageString(Object pageString) {
            this.pageString = pageString;
        }

        public Object getRowsString() {
            return rowsString;
        }

        public void setRowsString(Object rowsString) {
            this.rowsString = rowsString;
        }

        public Object getTradingTime() {
            return tradingTime;
        }

        public void setTradingTime(Object tradingTime) {
            this.tradingTime = tradingTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public Object getOrderSource() {
            return orderSource;
        }

        public void setOrderSource(Object orderSource) {
            this.orderSource = orderSource;
        }

        public Object getEquipmentSource() {
            return equipmentSource;
        }

        public void setEquipmentSource(Object equipmentSource) {
            this.equipmentSource = equipmentSource;
        }

        public String getOrderType() {
            return orderType;
        }

        public void setOrderType(String orderType) {
            this.orderType = orderType;
        }

        public Object getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(Object paymentType) {
            this.paymentType = paymentType;
        }

        public Object getClerkNumber() {
            return clerkNumber;
        }

        public void setClerkNumber(Object clerkNumber) {
            this.clerkNumber = clerkNumber;
        }

        public Object getOrderRemarks() {
            return orderRemarks;
        }

        public void setOrderRemarks(Object orderRemarks) {
            this.orderRemarks = orderRemarks;
        }

        public String getOrderAmount() {
            return orderAmount;
        }

        public void setOrderAmount(String orderAmount) {
            this.orderAmount = orderAmount;
        }

        public Object getAmountReceivable() {
            return amountReceivable;
        }

        public void setAmountReceivable(Object amountReceivable) {
            this.amountReceivable = amountReceivable;
        }

        public Object getAmountCollected() {
            return amountCollected;
        }

        public void setAmountCollected(Object amountCollected) {
            this.amountCollected = amountCollected;
        }

        public Object getCashAmount() {
            return cashAmount;
        }

        public void setCashAmount(Object cashAmount) {
            this.cashAmount = cashAmount;
        }

        public Object getMemberPreferences() {
            return memberPreferences;
        }

        public void setMemberPreferences(Object memberPreferences) {
            this.memberPreferences = memberPreferences;
        }

        public Object getRfundAmount() {
            return rfundAmount;
        }

        public void setRfundAmount(Object rfundAmount) {
            this.rfundAmount = rfundAmount;
        }

        public Object getRfundReason() {
            return rfundReason;
        }

        public void setRfundReason(Object rfundReason) {
            this.rfundReason = rfundReason;
        }

        public Object getFreeSingleReason() {
            return freeSingleReason;
        }

        public void setFreeSingleReason(Object freeSingleReason) {
            this.freeSingleReason = freeSingleReason;
        }

        public Object getAntiNodeReason() {
            return antiNodeReason;
        }

        public void setAntiNodeReason(Object antiNodeReason) {
            this.antiNodeReason = antiNodeReason;
        }

        public Object getShopNumber() {
            return shopNumber;
        }

        public void setShopNumber(Object shopNumber) {
            this.shopNumber = shopNumber;
        }

        public Object getEquipmentNumber() {
            return equipmentNumber;
        }

        public void setEquipmentNumber(Object equipmentNumber) {
            this.equipmentNumber = equipmentNumber;
        }

        public Object getMemberNumber() {
            return memberNumber;
        }

        public void setMemberNumber(Object memberNumber) {
            this.memberNumber = memberNumber;
        }

        public Object getMeiTuanCouponCode() {
            return meiTuanCouponCode;
        }

        public void setMeiTuanCouponCode(Object meiTuanCouponCode) {
            this.meiTuanCouponCode = meiTuanCouponCode;
        }

        public Object getDianPingCouponCode() {
            return dianPingCouponCode;
        }

        public void setDianPingCouponCode(Object dianPingCouponCode) {
            this.dianPingCouponCode = dianPingCouponCode;
        }

        public Object getTakeoutNumber() {
            return takeoutNumber;
        }

        public void setTakeoutNumber(Object takeoutNumber) {
            this.takeoutNumber = takeoutNumber;
        }

        public Object getFoodProductsDiscount() {
            return foodProductsDiscount;
        }

        public void setFoodProductsDiscount(Object foodProductsDiscount) {
            this.foodProductsDiscount = foodProductsDiscount;
        }

        public Object getOrderDiscount() {
            return orderDiscount;
        }

        public void setOrderDiscount(Object orderDiscount) {
            this.orderDiscount = orderDiscount;
        }

        public Object getMalingSystem() {
            return malingSystem;
        }

        public void setMalingSystem(Object malingSystem) {
            this.malingSystem = malingSystem;
        }

        public Object getInsertTime() {
            return insertTime;
        }

        public void setInsertTime(Object insertTime) {
            this.insertTime = insertTime;
        }

        public Object getAntiNode() {
            return antiNode;
        }

        public void setAntiNode(Object antiNode) {
            this.antiNode = antiNode;
        }

        public Object getOrder() {
            return order;
        }

        public void setOrder(Object order) {
            this.order = order;
        }

        public Object getFreeSingleType() {
            return freeSingleType;
        }

        public void setFreeSingleType(Object freeSingleType) {
            this.freeSingleType = freeSingleType;
        }

        public Object getCash() {
            return cash;
        }

        public void setCash(Object cash) {
            this.cash = cash;
        }

        public Object getPaymentOrder() {
            return paymentOrder;
        }

        public void setPaymentOrder(Object paymentOrder) {
            this.paymentOrder = paymentOrder;
        }

        public Object getOrderSourcePayment() {
            return orderSourcePayment;
        }

        public void setOrderSourcePayment(Object orderSourcePayment) {
            this.orderSourcePayment = orderSourcePayment;
        }

        public Object getRegionNumber() {
            return regionNumber;
        }

        public void setRegionNumber(Object regionNumber) {
            this.regionNumber = regionNumber;
        }

        public Object getTabletableNumber() {
            return tabletableNumber;
        }

        public void setTabletableNumber(Object tabletableNumber) {
            this.tabletableNumber = tabletableNumber;
        }

        public Object getDiscountPercentage() {
            return discountPercentage;
        }

        public void setDiscountPercentage(Object discountPercentage) {
            this.discountPercentage = discountPercentage;
        }

        public Object getCustomDiscount() {
            return customDiscount;
        }

        public void setCustomDiscount(Object customDiscount) {
            this.customDiscount = customDiscount;
        }

        public Object getMemberMoney() {
            return memberMoney;
        }

        public void setMemberMoney(Object memberMoney) {
            this.memberMoney = memberMoney;
        }

        public Object getClerkName() {
            return clerkName;
        }

        public void setClerkName(Object clerkName) {
            this.clerkName = clerkName;
        }

        public Object getRegisteredCell() {
            return registeredCell;
        }

        public void setRegisteredCell(Object registeredCell) {
            this.registeredCell = registeredCell;
        }

        public Object getOrderSourceFlag() {
            return orderSourceFlag;
        }

        public void setOrderSourceFlag(Object orderSourceFlag) {
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
