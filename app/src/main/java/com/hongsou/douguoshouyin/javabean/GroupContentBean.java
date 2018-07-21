package com.hongsou.douguoshouyin.javabean;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/7/21 0021
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


public class GroupContentBean {

    /**
     * foodFullName : ex eu veniam culpa eiusmod
     * foodProductsCount : 8978270
     * standard : enim reprehenderit amet sint
     * minGroup : labore tempor
     * foodProductsNumber : tempor ea officia sunt
     */

    private String foodFullName;
    private int foodProductsCount;
    private String standard;
    private String minGroup;
    private String foodProductsNumber;

    public GroupContentBean(String foodFullName, int foodProductsCount, String standard, String minGroup, String foodProductsNumber) {
        this.foodFullName = foodFullName;
        this.foodProductsCount = foodProductsCount;
        this.standard = standard;
        this.minGroup = minGroup;
        this.foodProductsNumber = foodProductsNumber;
    }

    public String getFoodFullName() {
        return foodFullName;
    }

    public void setFoodFullName(String foodFullName) {
        this.foodFullName = foodFullName;
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

    public String getFoodProductsNumber() {
        return foodProductsNumber;
    }

    public void setFoodProductsNumber(String foodProductsNumber) {
        this.foodProductsNumber = foodProductsNumber;
    }
}
