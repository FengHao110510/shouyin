package com.hongsou.douguoshouyin.javabean;

/**
 * Created by zlq on 2018/1/10.
 */

public class BluetoothBean {

    private static final int PRINT_TYPE = 1664;
    private static final int PHONE_TYPE = 524;

    private String name;
    private String status;
    private int type;
    private String typeStr;

    public String getTypeStr() {
        return typeStr;
    }


    /**
     * 260-电脑
     * 1664-打印机
     * 524-智能手机
     *
     * @return
     */
    public void setType(int type) {
        if (type==PRINT_TYPE){
            typeStr="打印设备";
        }
        if (type==PHONE_TYPE){
            typeStr="手机设备";
        }
        if (type!=PRINT_TYPE && type!=PHONE_TYPE){
            typeStr="其他设备";
        }

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
}
