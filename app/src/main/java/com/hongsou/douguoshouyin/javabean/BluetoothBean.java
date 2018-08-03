package com.hongsou.douguoshouyin.javabean;

import java.io.Serializable;

/**
 * Created by zlq on 2018/1/10.
 */

public class BluetoothBean implements Serializable {

    private static final int PRINT_TYPE = 1664;
    private static final int PHONE_TYPE = 524;

    private String name;
    private String address;
    private String status;
    private String typeStr;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

    public String getTypeStr() {
        return typeStr;
    }


    /**
     * 260-电脑
     * 1664-打印机
     * 524-智能手机
     */
    public void setType(int type) {
        if (type==PRINT_TYPE){
            setTypeStr("打印设备");
        }
        if (type==PHONE_TYPE){
            setTypeStr("手机设备");
        }
        if (type!=PRINT_TYPE && type!=PHONE_TYPE){
            setTypeStr("其他设备");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BluetoothBean)) {
            return false;
        }
        BluetoothBean that = (BluetoothBean) o;

        return address != null ? address.equals(that.address) : that.address == null;
    }

    @Override
    public int hashCode() {
        return address != null ? address.hashCode() : 0;
    }
}
