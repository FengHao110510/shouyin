package com.hongsou.douguoshouyin.tool;

import android.content.Context;
import android.content.SharedPreferences;


public class SharePreferenceGlobalUtil {

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    public SharePreferenceGlobalUtil(Context context, String file) {
        sp = context.getSharedPreferences(file, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    /**
     * 版本号
     *
     * @param lastVersion
     */
    public void setLastVersion(String lastVersion) {
        editor.putString("Last_version", lastVersion);
        editor.commit();
    }

    public String getLastVersion() {
        return sp.getString("Last_version", "");
    }

    /**
     * 语音提醒开关
     *
     * @param speechVoice
     */
    public void setSpeechVoice(boolean speechVoice) {
        editor.putBoolean("speechVoice", speechVoice);
        editor.commit();
    }

    public boolean getSpeechVoice() {
        return sp.getBoolean("speechVoice", true);
    }


    /**
     * TODO =================================小票打印========================================================
     */
    /**
     * 收款小票打印开关开关
     *
     * @param payPrintSwitch
     */
    public void setPayPrintSwitch(boolean payPrintSwitch) {
        editor.putBoolean("payPrintSwitch", payPrintSwitch);
        editor.commit();
    }

    public boolean getPayPrintSwitch() {
        return sp.getBoolean("payPrintSwitch", true);
    }

    /**
     * 收款小票打印份数
     *
     * @param payPrintCount
     */
    public void setPayPrintCount(int payPrintCount) {
        editor.putInt("payPrintCount", payPrintCount);
        editor.commit();
    }

    public int getPayPrintCount() {
        return sp.getInt("payPrintCount", 1);
    }

    /**
     * 订单小票打印开关开关
     *
     * @param orderPrintSwitch
     */
    public void setOrderPrintSwitch(boolean orderPrintSwitch) {
        editor.putBoolean("orderPrintSwitch", orderPrintSwitch);
        editor.commit();
    }

    public boolean getOrderPrintSwitch() {
        return sp.getBoolean("orderPrintSwitch", true);
    }

    /**
     * 订单小票打印份数
     *
     * @param orderPrintCount
     */
    public void setOrderPrintCount(int orderPrintCount) {
        editor.putInt("OrderPrintCount", orderPrintCount);
        editor.commit();
    }

    public int getOrderPrintCount() {
        return sp.getInt("OrderPrintCount", 1);
    }

    /**
     * 退款小票打印开关开关
     *
     * @param refundSwitch
     */
    public void setRefundPrintSwitch(boolean refundSwitch) {
        editor.putBoolean("refundSwitch", refundSwitch);
        editor.commit();
    }

    public boolean getRefundPrintSwitch() {
        return sp.getBoolean("refundSwitch", true);
    }

    /**
     * 退款小票打印份数
     *
     * @param refundPrintCount
     */
    public void setRefundPrintCount(int refundPrintCount) {
        editor.putInt("refundPrintCount", refundPrintCount);
        editor.commit();
    }

    public int getRefundPrintCount() {
        return sp.getInt("refundPrintCount", 1);
    }

    /**
     * 交班小票打印开关开关
     *
     * @param handoverPrintSwitch
     */
    public void setHandoverPrintSwitch(boolean handoverPrintSwitch) {
        editor.putBoolean("handoverPrintSwitch", handoverPrintSwitch);
        editor.commit();
    }

    public boolean getHandoverPrintSwitch() {
        return sp.getBoolean("handoverPrintSwitch", true);
    }

    /**
     * 交班小票打印份数
     *
     * @param handoverPrintCount
     */
    public void setHandoverPrintCount(int handoverPrintCount) {
        editor.putInt("handoverPrintCount", handoverPrintCount);
        editor.commit();
    }

    public int getHandoverPrintCount() {
        return sp.getInt("handoverPrintCount", 1);
    }

    /**
     * 后厨小票打印开关开关
     *
     * @param kitchenPrintSwitch
     */
    public void setKitchenPrintSwitch(boolean kitchenPrintSwitch) {
        editor.putBoolean("kitchenPrintSwitch", kitchenPrintSwitch);
        editor.commit();
    }

    public boolean getKitchenPrintSwitch() {
        return sp.getBoolean("kitchenPrintSwitch", true);
    }

    /**
     * 后厨小票打印份数
     *
     * @param kitchenPrintCount
     */
    public void setKitchenPrintCount(int kitchenPrintCount) {
        editor.putInt("kitchenPrintCount", kitchenPrintCount);
        editor.commit();
    }

    public int getKitchenPrintCount() {
        return sp.getInt("kitchenPrintCount", 1);
    }

    /**
     * TODO ================================= 小票打印 END ========================================================
     */


    /**
     * 应收金额
     *
     * @param receivableMoney
     */
    public void setReceivableMoney(String receivableMoney) {
        editor.putString("receivableMoney", receivableMoney);
        editor.commit();
    }

    public String getReceivableMoney() {
        return sp.getString("receivableMoney", "");
    }

    /**
     * 订单号
     *
     * @param batch
     */
    public void setBatch(String batch) {
        editor.putString("batch", batch);
        editor.commit();
    }

    public String getBatch() {
        return sp.getString("batch", "");
    }

    /**
     * 折扣还是减钱
     *
     * @param discountType
     */
    public void setDiscountType(String discountType) {
        editor.putString("discountType", discountType);
        editor.commit();
    }

    public String getDiscountType() {
        return sp.getString("discountType", "");
    }

    /**
     * 折扣金额
     *
     * @param discountMoney
     */
    public void setDiscountMoney(String discountMoney) {
        editor.putString("discountMoney", discountMoney);
        editor.commit();
    }

    public String getDiscountMoney() {
        return sp.getString("discountMoney", "");
    }


/**
 * TODO =================================LOGIN========================================================
 */

    /**
     * 是否记住密码
     *
     * @param checkPassword
     */
    public void setCheckPassword(boolean checkPassword) {
        editor.putBoolean("checkPassword", checkPassword);
        editor.commit();
    }

    public boolean getCheckPassword() {
        return sp.getBoolean("checkPassword", true);
    }

    /**
     * wecharCode
     *
     * @param wecharCode
     */
    public void setWecharCode(String wecharCode) {
        editor.putString("wecharCode", wecharCode);
        editor.commit();
    }

    public String getWecharCode() {
        return sp.getString("wecharCode", "");
    }

    /**
     * aliCode
     *
     * @param aliCode
     */
    public void setAliCode(String aliCode) {
        editor.putString("aliCode", aliCode);
        editor.commit();
    }

    public String getAliCode() {
        return sp.getString("aliCode", "");
    }

    /**
     * 蓝牙地址
     *
     * @param blueAddress
     */
    public void setHaveBlueAddress(String blueAddress) {
        editor.putString("blueAddress", blueAddress);
        editor.commit();
    }

    public String getHaveBlueAddress() {
        return sp.getString("blueAddress", "");
    }

    /**
     * 蓝牙名称
     *
     * @param blueName
     */
    public void setHaveBlueName(String blueName) {
        editor.putString("blueName", blueName);
        editor.commit();
    }

    public String getHaveBlueName() {
        return sp.getString("blueName", "");
    }

    /**
     * 账号
     *
     * @param userName
     */
    public void setUserName(String userName) {
        editor.putString("userName", userName);
        editor.commit();
    }

    public String getUserName() {
        return sp.getString("userName", "");
    }

    /**
     * 密码
     *
     * @param password
     */
    public void setPassword(String password) {
        editor.putString("password", password);
        editor.commit();
    }

    public String getPassword() {
        return sp.getString("password", "");
    }

    /**
     * 保存手机设备编号
     *
     * @param code
     */
    public void setCode(String code) {
        editor.putString("code", code);
        editor.commit();
    }

    public String getCode() {
        return sp.getString("code", "");
    }

    /**
     * 保存店员编号
     *
     * @param clerkNumber
     */
    public void setClerkNumber(String clerkNumber) {
        editor.putString("clerkNumber", clerkNumber);
        editor.commit();
    }

    public String getClerkNumber() {
        return sp.getString("clerkNumber", "");
    }

    /**
     * 保存店员名字
     *
     * @param clerkName
     */
    public void setClerkName(String clerkName) {
        editor.putString("clerkName", clerkName);
        editor.commit();
    }

    public String getClerkName() {
        return sp.getString("clerkName", "");
    }

    /**
     * 保存店铺编号
     *
     * @param shopNumber
     */
    public void setShopNumber(String shopNumber) {
        editor.putString("shopNumber", shopNumber);
        editor.commit();
    }

    public String getShopNumber() {
        return sp.getString("shopNumber", "");
    }

    /**
     * 保存店铺名称
     *
     * @param shopName
     */
    public void setShopName(String shopName) {
        editor.putString("shopName", shopName);
        editor.commit();
    }

    public String getShopName() {
        return sp.getString("shopName", "");
    }

    /**
     * 店铺电话
     */
    public void setShopPhone(String shopPhone) {
        editor.putString("shopPhone", shopPhone);
        editor.commit();
    }

    public String getShopPhone() {
        return sp.getString("shopPhone", "");
    }

    /**
     * 店铺地址
     */
    public void setShopAddress(String shopAddress) {
        editor.putString("shopAddress", shopAddress);
        editor.commit();
    }

    public String getShopAddress() {
        return sp.getString("shopAddress", "");
    }

    /**
     * 保存商户编号
     *
     * @param paymentUser
     */
    public void setPaymentUser(String paymentUser) {
        editor.putString("paymentUser", paymentUser);
        editor.commit();
    }

    public String getPaymentUser() {
        return sp.getString("paymentUser", "");
    }

    /**
     * 存储每日订单序号
     */
    public void setDateOrderNumber(String date, String number) {
        editor.putString("dateOrderNumber", date + "=_=" + number);
        editor.commit();
    }

    public String getDateOrderNumber() {
        return sp.getString("dateOrderNumber", null);
    }

    /**
     * 存储是否强制更新//强制刷新状态 0强制刷新 1不强制刷新
     */
    public void setForceState(String forcestate) {
        editor.putString("forcestate", forcestate);
        editor.commit();
    }

    public String getForceState() {
        return sp.getString("forcestate", "1");
    }

    /**
     * 蓝牙地址
     *
     * @param address
     */
    public void setBluetoothAddress(String address) {
        editor.putString("BluetoothAddress", address);
        editor.commit();
    }

    public String getBluetoothAddress() {
        return sp.getString("BluetoothAddress", "");
    }

    /**
     * 蓝牙名称
     *
     * @param name
     */
    public void setBluetoothName(String name) {
        editor.putString("BluetoothName", name);
        editor.commit();
    }

    public String getBluetoothName() {
        return sp.getString("BluetoothName", "");
    }

}