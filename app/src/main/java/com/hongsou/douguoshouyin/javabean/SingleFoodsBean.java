package com.hongsou.douguoshouyin.javabean;

import java.io.Serializable;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/7/20 0020
 * 描述：
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


public class SingleFoodsBean implements Serializable {

    private String singleProductNumber;
    private int singleQuantity;
    private String standardNumber;
    private String singleProductName;
    private String standardName;
    private String singleProductPrice;
    private String foodProductsPicture;
    private String categoryNumber;
    private String minGroup;

    public String getMinGroup() {
        return minGroup;
    }

    public void setMinGroup(String minGroup) {
        this.minGroup = minGroup;
    }

    public String getSingleProductNumber() {
        return singleProductNumber;
    }

    public void setSingleProductNumber(String singleProductNumber) {
        this.singleProductNumber = singleProductNumber;
    }

    public int getSingleQuantity() {
        return singleQuantity;
    }

    public void setSingleQuantity(int singleQuantity) {
        this.singleQuantity = singleQuantity;
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

    public String getStandardName() {
        return standardName;
    }

    public void setStandardName(String standardName) {
        this.standardName = standardName;
    }

    public String getSingleProductPrice() {
        return singleProductPrice;
    }

    public void setSingleProductPrice(String singleProductPrice) {
        this.singleProductPrice = singleProductPrice;
    }

    public String getFoodProductsPicture() {
        return foodProductsPicture;
    }

    public void setFoodProductsPicture(String foodProductsPicture) {
        this.foodProductsPicture = foodProductsPicture;
    }

    public String getCategoryNumber() {
        return categoryNumber;
    }

    public void setCategoryNumber(String categoryNumber) {
        this.categoryNumber = categoryNumber;
    }

    public SingleFoodsBean(String singleProductNumber,
                           String standardNumber,
                           String singleProductName,
                           String standardName,
                           String singleProductPrice,
                           String foodProductsPicture,
                           String categoryNumber
            , int singleQuantity) {
        this.singleProductNumber = singleProductNumber;
        this.standardNumber = standardNumber;
        this.singleProductName = singleProductName;
        this.standardName = standardName;
        this.singleProductPrice = singleProductPrice;
        this.foodProductsPicture = foodProductsPicture;
        this.categoryNumber = categoryNumber;
        this.singleQuantity = singleQuantity;

    }
}
