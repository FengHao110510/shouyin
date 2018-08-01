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
     * @param shoukuanXPKG
     */
    public void setShoukuanXPKG(boolean shoukuanXPKG) {
        editor.putBoolean("shoukuanXPKG", shoukuanXPKG);
        editor.commit();
    }

    public boolean getShoukuanXPKG() {
        return sp.getBoolean("shoukuanXPKG", true);
    }

    /**
     * 收款小票打印份数
     *
     * @param shoukuanXPFS
     */
    public void setShoukuanXPFS(String shoukuanXPFS) {
        editor.putString("shoukuanXPFS", shoukuanXPFS);
        editor.commit();
    }

    public String getShoukuanXPFS() {
        return sp.getString("shoukuanXPFS", "1");
    }

    /**
     * 订单小票打印开关开关
     *
     * @param dingdanXPKG
     */
    public void setDingdanXPKG(boolean dingdanXPKG) {
        editor.putBoolean("dingdanXPKG", dingdanXPKG);
        editor.commit();
    }

    public boolean getDingdanXPKG() {
        return sp.getBoolean("dingdanXPKG", true);
    }

    /**
     * 订单小票打印份数
     *
     * @param dingdanXPFS
     */
    public void setDingdanXPFS(String dingdanXPFS) {
        editor.putString("dingdanXPFS", dingdanXPFS);
        editor.commit();
    }

    public String getDingdanXPFS() {
        return sp.getString("dingdanXPFS", "1");
    }

    /**
     * 退款小票打印开关开关
     *
     * @param tuikuanXPKG
     */
    public void setTuikuanXPKG(boolean tuikuanXPKG) {
        editor.putBoolean("tuikuanXPKG", tuikuanXPKG);
        editor.commit();
    }

    public boolean getTuikuanXPKG() {
        return sp.getBoolean("tuikuanXPKG", true);
    }

    /**
     * 退款小票打印份数
     *
     * @param tuikuanXPFS
     */
    public void setTuikuanXPFS(String tuikuanXPFS) {
        editor.putString("tuikuanXPFS", tuikuanXPFS);
        editor.commit();
    }

    public String getTuikuanXPFS() {
        return sp.getString("tuikuanXPFS", "1");
    }

    /**
     * 交班小票打印开关开关
     *
     * @param jiaobanXPKG
     */
    public void setJiaobanXPKG(boolean jiaobanXPKG) {
        editor.putBoolean("jiaobanXPKG", jiaobanXPKG);
        editor.commit();
    }

    public boolean getJiaobanXPKG() {
        return sp.getBoolean("jiaobanXPKG", true);
    }

    /**
     * 交班小票打印份数
     *
     * @param jiaobanXPFS
     */
    public void setJiaobanXPFS(String jiaobanXPFS) {
        editor.putString("jiaobanXPFS", jiaobanXPFS);
        editor.commit();
    }

    public String getJiaobanXPFS() {
        return sp.getString("jiaobanXPFS", "1");
    }

    /**
     * 后厨小票打印开关开关
     *
     * @param houchuXPKG
     */
    public void setHouchuXPKG(boolean houchuXPKG) {
        editor.putBoolean("houchuXPKG", houchuXPKG);
        editor.commit();
    }

    public boolean getHouchuXPKG() {
        return sp.getBoolean("houchuXPKG", true);
    }

    /**
     * 后厨小票打印份数
     *
     * @param houchuXPFS
     */
    public void setHouchuXPFS(String houchuXPFS) {
        editor.putString("houchuXPFS", houchuXPFS);
        editor.commit();
    }

    public String getHouchuXPFS() {
        return sp.getString("houchuXPFS", "1");
    }

    /**
     * TODO =================================小票打印========================================================
     */

    /**
     * 扫码点餐开关
     *
     * @param scanCreateMeal
     */
    public void setScanCreateMeal(boolean scanCreateMeal) {
        editor.putBoolean("scanCreateMeal", scanCreateMeal);
        editor.commit();
    }

    public boolean getScanCreateMeal() {
        return sp.getBoolean("scanCreateMeal", true);
    }

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
     * 扫码模式  点餐 or 点餐+支付
     *
     * @param saomamoshi
     */
    public void setSaomamoshi(String saomamoshi) {
        editor.putString("saomamoshi", saomamoshi);
        editor.commit();
    }

    public String getSaomamoshi() {
        return sp.getString("saomamoshi", "点餐");
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

}