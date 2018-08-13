package com.hongsou.douguoshouyin.tool.bluetooth;

import android.bluetooth.BluetoothSocket;

import com.hongsou.douguoshouyin.base.BaseApplication;
import com.hongsou.douguoshouyin.tool.ToastUtil;

import java.io.IOException;

/**
 * Created by zlq on 2018/1/11.
 * 打印付款成功的小票
 */

public class BluetoothPrinterUtil {

    private static BluetoothPrinterUtil bluetoothPrinterUtil;
    /**
     * 小票打印份数
     */
    private int count;

    /**
     * 数据源
     */
    private Object mObject;
    private Print mPrint;

    /**
     * 打印类型
     */
    public enum Print {
        // 开单打印
        ORDER,
        // 收款打印
        PAY,
        // 交班打印
        HANDOVER,
        // 退单打印
        BACK_MONEY,
        // 统计打印
        STATISTICS,
        // 测试
        TEST
    }

    private BluetoothPrinterUtil() {
    }

    public static BluetoothPrinterUtil getInstance() {
        if (bluetoothPrinterUtil == null) {
            bluetoothPrinterUtil = new BluetoothPrinterUtil();
        }
        return bluetoothPrinterUtil;
    }

    /**
     * @param count 小票份数
     * @desc 设置每次打印的小票份数
     * @anthor lpc
     * @date: 2018/7/27
     */
    public void setPrintCount(int count) {
        this.count = count;
    }

    /**
     * @param object 数据源
     * @desc 设置打印的数据
     * @anthor lpc
     * @date: 2018/7/27
     */
    public void setPrintObject(Object object) {
        this.mObject = object;
    }

    /**
     * @param printType 打印类型
     * @desc 设置打印类型
     * @anthor lpc
     * @date: 2018/7/27
     */
    public void setPrintType(Print printType) {
        this.mPrint = printType;
    }

    /**
     * @desc 开始打印
     * @anthor lpc
     * @date: 2018/7/27
     */
    public void startPrint() {
        for (BluetoothSocket bluetoothSocket : BaseApplication.getApplication().socketArray) {
            print(bluetoothSocket);
        }
    }

    private void print(BluetoothSocket socket){
//        BluetoothSocket socket = BaseApplication.getInstance().socket;
        if (socket == null) {
            ToastUtil.showToast("蓝牙未连接");
            return;
        }
        if (mObject == null) {
            ToastUtil.showToast("数据异常");
            return;
        }
        if (!socket.isConnected()) {
            try {
                socket.connect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            BluetoothFormatUtils.setOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 判断打印什么模板
        for (int i = 0; i < count; i++) {
            switch (mPrint) {
                case ORDER:
                    BluetoothPrinterTemplate.printOrder(mObject);
                    break;
                case PAY:
                    BluetoothPrinterTemplate.printPay(mObject);
                    break;
                case HANDOVER:
                    BluetoothPrinterTemplate.printHandover(mObject);
                    break;
                case BACK_MONEY:
                    BluetoothPrinterTemplate.printBackOrder(mObject);
                    break;
                case STATISTICS:
                    BluetoothPrinterTemplate.printStatistics(mObject);
                    break;
                case TEST:
                    BluetoothPrinterTemplate.printTest();
                    break;
                default:
                    break;
            }
        }
    }

    public static class Builder {
        private int count;
        private Object obj;
        private Print mPrint;

        public Builder setCount(int count) {
            this.count = count;
            return this;
        }

        public Builder setContent(Object content) {
            this.obj = content;
            return this;
        }

        public Builder setType(Print print) {
            this.mPrint = print;
            return this;
        }

        public BluetoothPrinterUtil build() {
            BluetoothPrinterUtil util = BluetoothPrinterUtil.getInstance();
            util.setPrintCount(count);
            util.setPrintObject(obj);
            util.setPrintType(mPrint);
            return util;
        }
    }
}
