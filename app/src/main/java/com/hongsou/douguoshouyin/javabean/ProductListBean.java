package com.hongsou.douguoshouyin.javabean;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/7/21 0021
 * 描述：上传分组的
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


public class ProductListBean {
    private String foodProductsNumber;
    private int foodProductsCount;
    private String standard;
    private String minGroup;

    public ProductListBean(String foodProductsNumber, int foodProductsCount, String standard, String minGroup) {
        this.foodProductsNumber = foodProductsNumber;
        this.foodProductsCount = foodProductsCount;
        this.standard = standard;
        this.minGroup = minGroup;
    }

    public String getFoodProductsNumber() {
        return foodProductsNumber;
    }

    public void setFoodProductsNumber(String foodProductsNumber) {
        this.foodProductsNumber = foodProductsNumber;
    }

    public int getFoodProductsCount() {
        return foodProductsCount;
    }

    public void setFoodProductsCount(int foodProductsCount) {
        this.foodProductsCount = foodProductsCount;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getMinGroup() {
        return minGroup;
    }

    public void setMinGroup(String minGroup) {
        this.minGroup = minGroup;
    }
}
