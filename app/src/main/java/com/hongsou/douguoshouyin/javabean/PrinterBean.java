package com.hongsou.douguoshouyin.javabean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Unique;

/**
 * @author lpc
 *         <p>
 * @copyright 鸿搜网络公司 版权所有
 * <p>
 * @date 2018/8/3
 * <p>
 * @desc 打印机信息bean
 */
@Entity
public class PrinterBean {

    @Id(autoincrement = true)
    private long id;

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
    @Unique
    private String printAddress;
    private boolean connectStatus;



    @Generated(hash = 1299197321)
    public PrinterBean(long id, int page, int rows, String shopNumber, String printName, String printClassifyName,
            String printBrand, String printWidth, String printCount, String printAddress, boolean connectStatus) {
        this.id = id;
        this.page = page;
        this.rows = rows;
        this.shopNumber = shopNumber;
        this.printName = printName;
        this.printClassifyName = printClassifyName;
        this.printBrand = printBrand;
        this.printWidth = printWidth;
        this.printCount = printCount;
        this.printAddress = printAddress;
        this.connectStatus = connectStatus;
    }

    @Generated(hash = 1585630953)
    public PrinterBean() {
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

        return printAddress != null ? printAddress.equals(that.printAddress) : that.printAddress == null;
    }

    @Override
    public int hashCode() {
        return printAddress != null ? printAddress.hashCode() : 0;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return this.rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getShopNumber() {
        return this.shopNumber;
    }

    public void setShopNumber(String shopNumber) {
        this.shopNumber = shopNumber;
    }

    public String getPrintName() {
        return this.printName;
    }

    public void setPrintName(String printName) {
        this.printName = printName;
    }

    public String getPrintClassifyName() {
        return this.printClassifyName;
    }

    public void setPrintClassifyName(String printClassifyName) {
        this.printClassifyName = printClassifyName;
    }

    public String getPrintBrand() {
        return this.printBrand;
    }

    public void setPrintBrand(String printBrand) {
        this.printBrand = printBrand;
    }

    public String getPrintWidth() {
        return this.printWidth;
    }

    public void setPrintWidth(String printWidth) {
        this.printWidth = printWidth;
    }

    public String getPrintCount() {
        return this.printCount;
    }

    public void setPrintCount(String printCount) {
        this.printCount = printCount;
    }

    public String getPrintAddress() {
        return this.printAddress;
    }

    public void setPrintAddress(String printAddress) {
        this.printAddress = printAddress;
    }

    public boolean getConnectStatus() {
        return this.connectStatus;
    }

    public void setConnectStatus(boolean connectStatus) {
        this.connectStatus = connectStatus;
    }

}
