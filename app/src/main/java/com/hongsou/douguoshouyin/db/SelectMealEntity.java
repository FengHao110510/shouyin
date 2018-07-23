package com.hongsou.douguoshouyin.db;

import android.util.Log;

import com.hongsou.douguoshouyin.db.helper.GroupFoodConverter;
import com.hongsou.douguoshouyin.db.helper.PackageFoodConverter;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

import java.util.List;

/**
 * @author lpc
 *         <p>
 * @copyright 鸿搜网络公司 版权所有
 * <p>
 * @date 2018/7/17
 * <p>
 * @desc 购物车中的餐品
 */
@Entity
public class SelectMealEntity {
    @Id(autoincrement = true)
    private long id;

    private String foodName;
    private String foodPrice;
    private String standardName;

    /**
     * deletionFlag : 0
     * discountAmount : 1
     * foodProductsCount : 2
     * foodProductsNumber : TCBDCAB4
     * foodProductsType : 0
     * increasePrice : 1
     * memberPreferences : 1
     * remarks : 好
     * replace : 0
     * serialNumber : 2
     * shopNumber : 11111
     * standard : 中
     */

    private double VIPPrice;
    private double amount;
    private String mealCode;
    private String sell;
    private int sellingStatus;
    private String singleProductName;
    private String standardNumber;
    private int PHR;
    private long endTime;
    private String packageInitials;
    private String packageName;
    private String packagePrice;
    private String packageType;
    private String packageUnit;
    private int selling;
    private long startTime;
    private int takeout;
    private int theKitchenPrint;
    private int antiNodeCount;
    private String deletionFlag;
    private String discountAmount;
    private int foodProductsCount;

    private String foodProductsNumber;
    @Unique
    private String packageNumber;
    private String foodProductsType;
    private String increasePrice;
    private String memberPreferences;
    private String remarks;
    private String replace;
    private String serialNumber;
    private String shopNumber;
    private String standard;

    @Convert(columnType = String.class, converter = PackageFoodConverter.class)
    private List<PackageFoodEntity> packageFood;

    @Convert(columnType = String.class, converter = GroupFoodConverter.class)
    private List<FoodZuheTaocanXQ> groupFood;

    @Generated(hash = 817945036)
    public SelectMealEntity(long id, String foodName, String foodPrice, String standardName, double VIPPrice, double amount, String mealCode,
            String sell, int sellingStatus, String singleProductName, String standardNumber, int PHR, long endTime, String packageInitials,
            String packageName, String packagePrice, String packageType, String packageUnit, int selling, long startTime, int takeout,
            int theKitchenPrint, int antiNodeCount, String deletionFlag, String discountAmount, int foodProductsCount,
            String foodProductsNumber, String packageNumber, String foodProductsType, String increasePrice, String memberPreferences,
            String remarks, String replace, String serialNumber, String shopNumber, String standard, List<PackageFoodEntity> packageFood,
            List<FoodZuheTaocanXQ> groupFood) {
        this.id = id;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.standardName = standardName;
        this.VIPPrice = VIPPrice;
        this.amount = amount;
        this.mealCode = mealCode;
        this.sell = sell;
        this.sellingStatus = sellingStatus;
        this.singleProductName = singleProductName;
        this.standardNumber = standardNumber;
        this.PHR = PHR;
        this.endTime = endTime;
        this.packageInitials = packageInitials;
        this.packageName = packageName;
        this.packagePrice = packagePrice;
        this.packageType = packageType;
        this.packageUnit = packageUnit;
        this.selling = selling;
        this.startTime = startTime;
        this.takeout = takeout;
        this.theKitchenPrint = theKitchenPrint;
        this.antiNodeCount = antiNodeCount;
        this.deletionFlag = deletionFlag;
        this.discountAmount = discountAmount;
        this.foodProductsCount = foodProductsCount;
        this.foodProductsNumber = foodProductsNumber;
        this.packageNumber = packageNumber;
        this.foodProductsType = foodProductsType;
        this.increasePrice = increasePrice;
        this.memberPreferences = memberPreferences;
        this.remarks = remarks;
        this.replace = replace;
        this.serialNumber = serialNumber;
        this.shopNumber = shopNumber;
        this.standard = standard;
        this.packageFood = packageFood;
        this.groupFood = groupFood;
    }

    @Generated(hash = 999102229)
    public SelectMealEntity() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFoodName() {
        return this.foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodPrice() {
        return this.foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getStandardName() {
        return this.standardName;
    }

    public void setStandardName(String standardName) {
        this.standardName = standardName;
    }

    public double getVIPPrice() {
        return this.VIPPrice;
    }

    public void setVIPPrice(double VIPPrice) {
        this.VIPPrice = VIPPrice;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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

    public int getSellingStatus() {
        return this.sellingStatus;
    }

    public void setSellingStatus(int sellingStatus) {
        this.sellingStatus = sellingStatus;
    }

    public String getSingleProductName() {
        return this.singleProductName;
    }

    public void setSingleProductName(String singleProductName) {
        this.singleProductName = singleProductName;
    }

    public String getStandardNumber() {
        return this.standardNumber;
    }

    public void setStandardNumber(String standardNumber) {
        this.standardNumber = standardNumber;
    }

    public int getPHR() {
        return this.PHR;
    }

    public void setPHR(int PHR) {
        this.PHR = PHR;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getPackageInitials() {
        return this.packageInitials;
    }

    public void setPackageInitials(String packageInitials) {
        this.packageInitials = packageInitials;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPackagePrice() {
        return this.packagePrice;
    }

    public void setPackagePrice(String packagePrice) {
        this.packagePrice = packagePrice;
    }

    public String getPackageType() {
        return this.packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getPackageUnit() {
        return this.packageUnit;
    }

    public void setPackageUnit(String packageUnit) {
        this.packageUnit = packageUnit;
    }

    public int getSelling() {
        return this.selling;
    }

    public void setSelling(int selling) {
        this.selling = selling;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public int getTakeout() {
        return this.takeout;
    }

    public void setTakeout(int takeout) {
        this.takeout = takeout;
    }

    public int getTheKitchenPrint() {
        return this.theKitchenPrint;
    }

    public void setTheKitchenPrint(int theKitchenPrint) {
        this.theKitchenPrint = theKitchenPrint;
    }

    public int getAntiNodeCount() {
        return this.antiNodeCount;
    }

    public void setAntiNodeCount(int antiNodeCount) {
        this.antiNodeCount = antiNodeCount;
    }

    public String getDeletionFlag() {
        return this.deletionFlag;
    }

    public void setDeletionFlag(String deletionFlag) {
        this.deletionFlag = deletionFlag;
    }

    public String getDiscountAmount() {
        return this.discountAmount;
    }

    public void setDiscountAmount(String discountAmount) {
        this.discountAmount = discountAmount;
    }

    public int getFoodProductsCount() {
        return this.foodProductsCount;
    }

    public void setFoodProductsCount(int foodProductsCount) {
        this.foodProductsCount = foodProductsCount;
    }

    public String getFoodProductsNumber() {
        return this.foodProductsNumber;
    }

    public void setFoodProductsNumber(String foodProductsNumber) {
        this.foodProductsNumber = foodProductsNumber;
    }

    public String getPackageNumber() {
        return this.packageNumber;
    }

    public void setPackageNumber(String packageNumber) {
        this.packageNumber = packageNumber;
    }

    public String getFoodProductsType() {
        return this.foodProductsType;
    }

    public void setFoodProductsType(String foodProductsType) {
        this.foodProductsType = foodProductsType;
    }

    public String getIncreasePrice() {
        return this.increasePrice;
    }

    public void setIncreasePrice(String increasePrice) {
        this.increasePrice = increasePrice;
    }

    public String getMemberPreferences() {
        return this.memberPreferences;
    }

    public void setMemberPreferences(String memberPreferences) {
        this.memberPreferences = memberPreferences;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getReplace() {
        return this.replace;
    }

    public void setReplace(String replace) {
        this.replace = replace;
    }

    public String getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getShopNumber() {
        return this.shopNumber;
    }

    public void setShopNumber(String shopNumber) {
        this.shopNumber = shopNumber;
    }

    public String getStandard() {
        return this.standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public List<PackageFoodEntity> getPackageFood() {
        return this.packageFood;
    }

    public void setPackageFood(List<PackageFoodEntity> packageFood) {
        this.packageFood = packageFood;
    }

    public List<FoodZuheTaocanXQ> getGroupFood() {
        return this.groupFood;
    }

    public void setGroupFood(List<FoodZuheTaocanXQ> groupFood) {
        this.groupFood = groupFood;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SelectMealEntity)) return false;

        SelectMealEntity entity = (SelectMealEntity) o;

        if ("1".equals(entity.getFoodProductsType())){
            if (standardNumber != null ? !standardNumber.equals(entity.standardNumber) : entity.standardNumber != null){
                Log.e("aaaasdfdsf", "equals: 1111111111111111111111");
                return false;
            }
            if (foodProductsNumber != null ? !foodProductsNumber.equals(entity.foodProductsNumber) : entity.foodProductsNumber != null){
                Log.e("aaaasdfdsf", "equals: 2222222222222");
                return false;
            }
        }else if ("0".equals(entity.getFoodProductsType())){
            if (packageNumber != null ? !packageNumber.equals(entity.packageNumber) : entity.packageNumber != null){
                Log.e("aaaasdfdsf", "equals: 444444444444444");
                return false;
            }
        }else if ("2".equals(entity.getFoodProductsType())){
            Log.e("aaaasdfdsf", "equals: 8888888888888888");
            if (foodProductsNumber != null ? !foodProductsNumber.equals(entity.foodProductsNumber) : entity.getFoodProductsNumber() != null){
                Log.e("aaaasdfdsf", "equals: 99999999999999");
                return false;
            }
            Log.e("aaaasdfdsf", "equals: zzzzzzzzzzzzzzzzzz" + isListEqual(groupFood, entity.groupFood));
            return  isListEqual(groupFood, entity.groupFood);
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = standardNumber != null ? standardNumber.hashCode() : 0;
        result = 31 * result + (foodProductsNumber != null ? foodProductsNumber.hashCode() : 0);
        result = 31 * result + (packageNumber != null ? packageNumber.hashCode() : 0);
        result = 31 * result + (foodProductsType != null ? foodProductsType.hashCode() : 0);
        return result;
    }

    public static boolean isListEqual(List<FoodZuheTaocanXQ> l0, List<FoodZuheTaocanXQ> l1){
        if (l0 == l1) {
            return true;
        }
        if (l0 == null || l1 == null) {
            return false;
        }
        if (l0.size() != l1.size()) {
            return false;
        }
        for (FoodZuheTaocanXQ o : l0) {
            if (!l1.contains(o)) {
                return false;
            }
        }
        for (FoodZuheTaocanXQ o : l1) {
            if (!l0.contains(o)) {
                return false;
            }
        }
        return true;
    }


}
