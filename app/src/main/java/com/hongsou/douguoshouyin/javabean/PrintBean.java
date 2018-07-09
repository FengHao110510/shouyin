package com.hongsou.douguoshouyin.javabean;

import android.bluetooth.BluetoothDevice;
import android.text.TextUtils;
import android.view.View;

/**
 * 版权：鸿搜网络公司 版权所有
 * <p>
 * 作者：冯大鱼
 * <p>
 * 版本：1.0
 * <p>
 * 创建日期：2018/6/30 0030
 * <p>
 * 描述：打印机 item        蓝牙设备的实体类
 * <p>
 * 修订历史：
 */


public class PrintBean {
    public static final int PRINT_TYPE = 1664;
    //蓝牙-名称
    public String name;
    //蓝牙-地址
    public String address;
    //蓝牙-设备类型
    public int type;
    //蓝牙-是否已经匹配
    public boolean isConnect;

    BluetoothDevice device;

    /**
     * @param devicee 蓝牙设备对象
     */
    public PrintBean(BluetoothDevice devicee) {
        this.name = TextUtils.isEmpty(devicee.getName()) ? "未知" : devicee.getName();
        this.address = devicee.getAddress();
        this.isConnect = devicee.getBondState() == BluetoothDevice.BOND_BONDED;
        this.type = devicee.getBluetoothClass().getDeviceClass();
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }



    public String getDeviceType(View view) {
        if (type == PRINT_TYPE) {
            view.setSelected(true);
            return isConnect ? "已连接" : "点击连接";
        } else {
            view.setSelected(false);
            return "非打印设备";
        }
    }

    public int getType() {
        return type;
    }

    public boolean isConnect() {
        return isConnect;
    }

    public BluetoothDevice getDevice() {

        return device;
    }

    public void setConnect(boolean connect) {
        isConnect = connect;
    }

}
