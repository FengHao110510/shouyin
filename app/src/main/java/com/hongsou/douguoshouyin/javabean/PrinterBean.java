package com.hongsou.douguoshouyin.javabean;

/**
 * @author lpc
 *         <p>
 * @copyright 鸿搜网络公司 版权所有
 * <p>
 * @date 2018/8/3
 * <p>
 * @desc 打印机信息bean
 */

public class PrinterBean {

    /**
     * page : 0
     * rows : 0
     * shopNumber : 1000180427300326890
     * printName : 惠普打印机
     * printClassifyName : 收银打印
     * printBrand : 惠普
     * printWidth : 58mm
     * printCount : 1
     */

    private int page;
    private int rows;
    private String shopNumber;
    private String printName;
    private String printClassifyName;
    private String printBrand;
    private String printWidth;
    private String printCount;
    private String address;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getShopNumber() {
        return shopNumber;
    }

    public void setShopNumber(String shopNumber) {
        this.shopNumber = shopNumber;
    }

    public String getPrintName() {
        return printName;
    }

    public void setPrintName(String printName) {
        this.printName = printName;
    }

    public String getPrintClassifyName() {
        return printClassifyName;
    }

    public void setPrintClassifyName(String printClassifyName) {
        this.printClassifyName = printClassifyName;
    }

    public String getPrintBrand() {
        return printBrand;
    }

    public void setPrintBrand(String printBrand) {
        this.printBrand = printBrand;
    }

    public String getPrintWidth() {
        return printWidth;
    }

    public void setPrintWidth(String printWidth) {
        this.printWidth = printWidth;
    }

    public String getPrintCount() {
        return printCount;
    }

    public void setPrintCount(String printCount) {
        this.printCount = printCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PrinterBean)) {
            return false;
        }

        PrinterBean that = (PrinterBean) o;

        return address != null ? address.equals(that.address) : that.address == null;
    }

    @Override
    public int hashCode() {
        return address != null ? address.hashCode() : 0;
    }
}
