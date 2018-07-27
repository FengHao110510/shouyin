package com.hongsou.douguoshouyin.tool;

/**
 * @author lpc
 *         <p>
 * @copyright 鸿搜网络公司 版权所有
 * <p>
 * @date 2018/7/27
 * <p>
 * @desc 蓝牙打印模板
 */

public class BluetoothPrinterTemplate {

    /**
     * @param
     * @desc 订单打印模板
     * @anthor lpc
     * @date: 2018/7/27
     */
    public static void printOrder(Object object) {
        BluetoothFormatUtils.selectCommand(BluetoothFormatUtils.DOUBLE_HEIGHT_WIDTH);
        BluetoothFormatUtils.selectCommand(BluetoothFormatUtils.ALIGN_CENTER);
        BluetoothFormatUtils.printText("豆果智慧收银\n");
        BluetoothFormatUtils.selectCommand(BluetoothFormatUtils.NORMAL);
        BluetoothFormatUtils.selectCommand(BluetoothFormatUtils.ALIGN_LEFT);
        BluetoothFormatUtils.printText("--------------------------------\n");
        BluetoothFormatUtils.printText(BluetoothFormatUtils.printTwoData("交易订单号", /*printerModel.getOrderNum() +*/ "\n"));
        BluetoothFormatUtils.printText(BluetoothFormatUtils.printTwoData("交易时间", /*printerModel.getOrderTime() +*/ "\n"));
        BluetoothFormatUtils.printText(BluetoothFormatUtils.printTwoData("交易方式", /*printerModel.getPaytype() + */"\n"));
        BluetoothFormatUtils.printText(BluetoothFormatUtils.printTwoData("交易金额", /*printerModel.getMoneyStr() +*/ "\n"));
        BluetoothFormatUtils.printText(BluetoothFormatUtils.printTwoData("收银员", /*printerModel.getUserName() +*/ "\n"));
        BluetoothFormatUtils.printText(BluetoothFormatUtils.printTwoData("收银门店", /*printerModel.getStoraName() +*/ "\n"));
        BluetoothFormatUtils.printText("--------------------------------\n\n");
        BluetoothFormatUtils.printText("签名：");
        BluetoothFormatUtils.printText("\n\n\n\n\n");
    }

    /**
     * @param obj
     * @desc 交班打印模板
     * @anthor lpc
     * @date: 2018/7/27
     */
    public static void printHandover(Object obj) {
        BluetoothFormatUtils.selectCommand(BluetoothFormatUtils.DOUBLE_HEIGHT_WIDTH);
        BluetoothFormatUtils.selectCommand(BluetoothFormatUtils.ALIGN_CENTER);
        BluetoothFormatUtils.printText("豆果智慧收银\n");
        BluetoothFormatUtils.selectCommand(BluetoothFormatUtils.NORMAL);
        BluetoothFormatUtils.selectCommand(BluetoothFormatUtils.ALIGN_LEFT);
        BluetoothFormatUtils.printText("--------------------------------\n");
        BluetoothFormatUtils.printText(BluetoothFormatUtils.printTwoData("交易订单号", /*printerModel.getOrderNum() +*/ "\n"));
        BluetoothFormatUtils.printText(BluetoothFormatUtils.printTwoData("交易时间", /*printerModel.getOrderTime() +*/ "\n"));
        BluetoothFormatUtils.printText(BluetoothFormatUtils.printTwoData("交易方式", /*printerModel.getPaytype() + */"\n"));
        BluetoothFormatUtils.printText(BluetoothFormatUtils.printTwoData("交易金额", /*printerModel.getMoneyStr() +*/ "\n"));
        BluetoothFormatUtils.printText(BluetoothFormatUtils.printTwoData("收银员", /*printerModel.getUserName() +*/ "\n"));
        BluetoothFormatUtils.printText(BluetoothFormatUtils.printTwoData("收银门店", /*printerModel.getStoraName() +*/ "\n"));
        BluetoothFormatUtils.printText("--------------------------------\n\n");
        BluetoothFormatUtils.printText("签名：");
        BluetoothFormatUtils.printText("\n\n\n\n\n");
    }

    /**
     * @param obj
     * @desc 统计打印模板
     * @anthor lpc
     * @date: 2018/7/27
     */
    public static void printStatistics(Object obj) {
        BluetoothFormatUtils.selectCommand(BluetoothFormatUtils.DOUBLE_HEIGHT_WIDTH);
        BluetoothFormatUtils.selectCommand(BluetoothFormatUtils.ALIGN_CENTER);
        BluetoothFormatUtils.printText("豆果智慧收银\n");
        BluetoothFormatUtils.selectCommand(BluetoothFormatUtils.NORMAL);
        BluetoothFormatUtils.selectCommand(BluetoothFormatUtils.ALIGN_LEFT);
        BluetoothFormatUtils.printText("--------------------------------\n");
        BluetoothFormatUtils.printText(BluetoothFormatUtils.printTwoData("交易订单号", /*printerModel.getOrderNum() +*/ "\n"));
        BluetoothFormatUtils.printText(BluetoothFormatUtils.printTwoData("交易时间", /*printerModel.getOrderTime() +*/ "\n"));
        BluetoothFormatUtils.printText(BluetoothFormatUtils.printTwoData("交易方式", /*printerModel.getPaytype() + */"\n"));
        BluetoothFormatUtils.printText(BluetoothFormatUtils.printTwoData("交易金额", /*printerModel.getMoneyStr() +*/ "\n"));
        BluetoothFormatUtils.printText(BluetoothFormatUtils.printTwoData("收银员", /*printerModel.getUserName() +*/ "\n"));
        BluetoothFormatUtils.printText(BluetoothFormatUtils.printTwoData("收银门店", /*printerModel.getStoraName() +*/ "\n"));
        BluetoothFormatUtils.printText("--------------------------------\n\n");
        BluetoothFormatUtils.printText("签名：");
        BluetoothFormatUtils.printText("\n\n\n\n\n");
    }

    /**
     * @param obj
     * @desc 退单打印模板
     * @anthor lpc
     * @date: 2018/7/27
     */
    public static void printBackOrder(Object obj) {
        BluetoothFormatUtils.selectCommand(BluetoothFormatUtils.DOUBLE_HEIGHT_WIDTH);
        BluetoothFormatUtils.selectCommand(BluetoothFormatUtils.ALIGN_CENTER);
        BluetoothFormatUtils.printText("豆果智慧收银\n");
        BluetoothFormatUtils.selectCommand(BluetoothFormatUtils.NORMAL);
        BluetoothFormatUtils.selectCommand(BluetoothFormatUtils.ALIGN_LEFT);
        BluetoothFormatUtils.printText("--------------------------------\n");
        BluetoothFormatUtils.printText(BluetoothFormatUtils.printTwoData("交易订单号", /*printerModel.getOrderNum() +*/ "\n"));
        BluetoothFormatUtils.printText(BluetoothFormatUtils.printTwoData("交易时间", /*printerModel.getOrderTime() +*/ "\n"));
        BluetoothFormatUtils.printText(BluetoothFormatUtils.printTwoData("交易方式", /*printerModel.getPaytype() + */"\n"));
        BluetoothFormatUtils.printText(BluetoothFormatUtils.printTwoData("交易金额", /*printerModel.getMoneyStr() +*/ "\n"));
        BluetoothFormatUtils.printText(BluetoothFormatUtils.printTwoData("收银员", /*printerModel.getUserName() +*/ "\n"));
        BluetoothFormatUtils.printText(BluetoothFormatUtils.printTwoData("收银门店", /*printerModel.getStoraName() +*/ "\n"));
        BluetoothFormatUtils.printText("--------------------------------\n\n");
        BluetoothFormatUtils.printText("签名：");
        BluetoothFormatUtils.printText("\n\n\n\n\n");
    }

}
