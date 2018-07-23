package com.hongsou.douguoshouyin.db;

/**
 * Created by Insist_L on 2018/4/17.
 * TODO:    点餐订单列表中套餐内的单品列表
 */
public class PackageFoodEntity {


    private String foodProductsNumber;
    private String mealCode; // 餐品码
    private String sell;
    private String standardName; // 规格名称
    private String standardNumber;  // 规格编号
    private String vipprice; // 会员价
    private String cateGoryName;
    private String cateGoryType;
    private String foodProductsPicture;
    private String packageName;
    private String packageNumber;
    private String replace;
    private String remind;
    private String replaceStatus;       // 是否是替换后的
    private String singleProductName;
    private String singleProductNumber;
    private String singleQuantity;      // 原套餐内单品数量（不会变）
    private String singleCount;         // 替换后的单品数量（实时变化）
    private String serialNumber;
    private String repId;               // 关联替换前后的两种单品的标识
    private String increasePrice;

    public String getRemind() {
        return remind;
    }

    public void setRemind(String remind) {
        this.remind = remind;
    }

    public String getSingleCount() {
        return singleCount;
    }

    public void setSingleCount(String singleCount) {
        this.singleCount = singleCount;
    }

    public String getFoodProductsNumber() {
        return this.foodProductsNumber;
    }


    public void setFoodProductsNumber(String foodProductsNumber) {
        this.foodProductsNumber = foodProductsNumber;
    }


    public String getMealCode() {
        return this.mealCode;
    }


    public void setMealCode(String mealCode) {
        this.mealCode = mealCode;
    }


    public String getSell() {
        return this.sell;
    }


    public void setSell(String sell) {
        this.sell = sell;
    }


    public String getStandardName() {
        return this.standardName;
    }


    public void setStandardName(String standardName) {
        this.standardName = standardName;
    }


    public String getStandardNumber() {
        return this.standardNumber;
    }


    public void setStandardNumber(String standardNumber) {
        this.standardNumber = standardNumber;
    }


    public String getVipprice() {
        return this.vipprice;
    }


    public void setVipprice(String vipprice) {
        this.vipprice = vipprice;
    }


    public String getCateGoryName() {
        return this.cateGoryName;
    }


    public void setCateGoryName(String cateGoryName) {
        this.cateGoryName = cateGoryName;
    }


    public String getCateGoryType() {
        return this.cateGoryType;
    }


    public void setCateGoryType(String cateGoryType) {
        this.cateGoryType = cateGoryType;
    }


    public String getFoodProductsPicture() {
        return this.foodProductsPicture;
    }


    public void setFoodProductsPicture(String foodProductsPicture) {
        this.foodProductsPicture = foodProductsPicture;
    }


    public String getPackageName() {
        return this.packageName;
    }


    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }


    public String getPackageNumber() {
        return this.packageNumber;
    }


    public void setPackageNumber(String packageNumber) {
        this.packageNumber = packageNumber;
    }


    public String getReplace() {
        return this.replace;
    }


    public void setReplace(String replace) {
        this.replace = replace;
    }


    public String getSingleProductName() {
        return this.singleProductName;
    }


    public void setSingleProductName(String singleProductName) {
        this.singleProductName = singleProductName;
    }


    public String getSingleProductNumber() {
        return this.singleProductNumber;
    }


    public void setSingleProductNumber(String singleProductNumber) {
        this.singleProductNumber = singleProductNumber;
    }


    public String getSingleQuantity() {
        return this.singleQuantity;
    }


    public void setSingleQuantity(String singleQuantity) {
        this.singleQuantity = singleQuantity;
    }



    public String getReplaceStatus() {
        return this.replaceStatus;
    }


    public void setReplaceStatus(String replaceStatus) {
        this.replaceStatus = replaceStatus;
    }


    public String getSerialNumber() {
        return this.serialNumber;
    }


    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }


    public String getRepId() {
        return this.repId;
    }


    public void setRepId(String repId) {
        this.repId = repId;
    }


    public String getIncreasePrice() {
        return this.increasePrice;
    }


    public void setIncreasePrice(String increasePrice) {
        this.increasePrice = increasePrice;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof PackageFoodEntity)) {
            return false;
        }
        PackageFoodEntity entity = (PackageFoodEntity) obj;
        return entity.singleProductName.equals(singleProductName) &&
                entity.standardName == standardName;
    }

    @Override
    public int hashCode() {

        return singleProductName.hashCode();
    }

}
