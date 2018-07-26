package com.hongsou.douguoshouyin.javabean;

/**
 * @author lpc
 *         <p>
 * @copyright 鸿搜网络公司 版权所有
 * <p>
 * @date 2018/7/26
 * <p>
 * @desc 统计页面销售排行的实体类
 */

public class StatisticsRankingsBean {


    /**
     * page : 0
     * rows : 0
     * foodProductsNumber : ZH180530111118025
     * foodProductsName : 测试组合TYPE是否正常
     * standardNumber :
     * standardName :
     * foodProductsCount : 4
     */

    private int page;
    private int rows;
    private String foodProductsNumber;
    private String foodProductsName;
    private String standardNumber;
    private String standardName;
    private String foodProductsCount;

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

    public String getFoodProductsNumber() {
        return foodProductsNumber;
    }

    public void setFoodProductsNumber(String foodProductsNumber) {
        this.foodProductsNumber = foodProductsNumber;
    }

    public String getFoodProductsName() {
        return foodProductsName;
    }

    public void setFoodProductsName(String foodProductsName) {
        this.foodProductsName = foodProductsName;
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

    public String getFoodProductsCount() {
        return foodProductsCount;
    }

    public void setFoodProductsCount(String foodProductsCount) {
        this.foodProductsCount = foodProductsCount;
    }
}
