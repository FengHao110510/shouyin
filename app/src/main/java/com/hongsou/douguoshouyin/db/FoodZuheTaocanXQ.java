package com.hongsou.douguoshouyin.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;


/**
 * Created by Administrator on 2018/5/20.
 */
@Entity
public class FoodZuheTaocanXQ {

    @Id(autoincrement = true)
    private Long id;

    /**
     * shopNumber : 1000180427300326890
     * groupNumber : FZ180518111114135
     * groupName : 1
     * groupCount : 1
     * foodProductsNumber : CP8C1D19
     * <p>
     * foodProductsCount : 1
     * standard : 180502111115650
     * minGroup : 3
     * priceMarkup : 0.0000
     * defaults : null
     * singleProductName : 薯条
     * standardName : 大份
     * packagePicture : null
     */

    private String shopNumber;
    @Unique
    private String groupNumber;
    private String groupName;
    private String groupCount;
    @Unique
    private String foodProductsNumber;
    private String foodProductsCount;
    private String standard;
    private String minGroup;
    private String priceMarkup;
    private String defaults;
    private String singleProductName;
    private String standardName;
    private String packagePicture;
    private String foodProductsType;
    private String replace;
    private String remind;
    private String foodProductsPicture;
    private boolean alreadyCount;







    @Generated(hash = 1464852940)
    public FoodZuheTaocanXQ(Long id, String shopNumber, String groupNumber,
            String groupName, String groupCount, String foodProductsNumber,
            String foodProductsCount, String standard, String minGroup,
            String priceMarkup, String defaults, String singleProductName,
            String standardName, String packagePicture, String foodProductsType,
            String replace, String remind, String foodProductsPicture,
            boolean alreadyCount) {
        this.id = id;
        this.shopNumber = shopNumber;
        this.groupNumber = groupNumber;
        this.groupName = groupName;
        this.groupCount = groupCount;
        this.foodProductsNumber = foodProductsNumber;
        this.foodProductsCount = foodProductsCount;
        this.standard = standard;
        this.minGroup = minGroup;
        this.priceMarkup = priceMarkup;
        this.defaults = defaults;
        this.singleProductName = singleProductName;
        this.standardName = standardName;
        this.packagePicture = packagePicture;
        this.foodProductsType = foodProductsType;
        this.replace = replace;
        this.remind = remind;
        this.foodProductsPicture = foodProductsPicture;
        this.alreadyCount = alreadyCount;
    }

    @Generated(hash = 613214578)
    public FoodZuheTaocanXQ() {
    }





  

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof FoodZuheTaocanXQ)) {
            return false;
        }
        FoodZuheTaocanXQ entity = (FoodZuheTaocanXQ) obj;
        return entity.singleProductName.equals(singleProductName) &&
                entity.standardName.equals(standardName) &&
                entity.foodProductsCount.equals(foodProductsCount);
    }

    @Override
    public int hashCode() {

        return singleProductName.hashCode();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShopNumber() {
        return this.shopNumber;
    }

    public void setShopNumber(String shopNumber) {
        this.shopNumber = shopNumber;
    }

    public String getGroupNumber() {
        return this.groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupCount() {
        return this.groupCount;
    }

    public void setGroupCount(String groupCount) {
        this.groupCount = groupCount;
    }

    public String getFoodProductsNumber() {
        return this.foodProductsNumber;
    }

    public void setFoodProductsNumber(String foodProductsNumber) {
        this.foodProductsNumber = foodProductsNumber;
    }

    public String getFoodProductsCount() {
        return this.foodProductsCount;
    }

    public void setFoodProductsCount(String foodProductsCount) {
        this.foodProductsCount = foodProductsCount;
    }

    public String getStandard() {
        return this.standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getMinGroup() {
        return this.minGroup;
    }

    public void setMinGroup(String minGroup) {
        this.minGroup = minGroup;
    }

    public String getPriceMarkup() {
        return this.priceMarkup;
    }

    public void setPriceMarkup(String priceMarkup) {
        this.priceMarkup = priceMarkup;
    }

    public String getDefaults() {
        return this.defaults;
    }

    public void setDefaults(String defaults) {
        this.defaults = defaults;
    }

    public String getSingleProductName() {
        return this.singleProductName;
    }

    public void setSingleProductName(String singleProductName) {
        this.singleProductName = singleProductName;
    }

    public String getStandardName() {
        return this.standardName;
    }

    public void setStandardName(String standardName) {
        this.standardName = standardName;
    }

    public String getPackagePicture() {
        return this.packagePicture;
    }

    public void setPackagePicture(String packagePicture) {
        this.packagePicture = packagePicture;
    }

    public String getFoodProductsType() {
        return this.foodProductsType;
    }

    public void setFoodProductsType(String foodProductsType) {
        this.foodProductsType = foodProductsType;
    }

    public String getReplace() {
        return this.replace;
    }

    public void setReplace(String replace) {
        this.replace = replace;
    }

    public String getRemind() {
        return this.remind;
    }

    public void setRemind(String remind) {
        this.remind = remind;
    }

    public String getFoodProductsPicture() {
        return this.foodProductsPicture;
    }

    public void setFoodProductsPicture(String foodProductsPicture) {
        this.foodProductsPicture = foodProductsPicture;
    }

    public boolean getAlreadyCount() {
        return this.alreadyCount;
    }

    public void setAlreadyCount(boolean alreadyCount) {
        this.alreadyCount = alreadyCount;
    }

  

}
