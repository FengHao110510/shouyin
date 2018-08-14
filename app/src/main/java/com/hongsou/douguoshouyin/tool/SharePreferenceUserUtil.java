package com.hongsou.douguoshouyin.tool;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferenceUserUtil {

	private SharedPreferences sp;
	private SharedPreferences.Editor editor;


	public SharePreferenceUserUtil(Context context, String file) {
		sp = context.getSharedPreferences(file, Context.MODE_PRIVATE);
		editor = sp.edit();
	}


	public void put(String key, String value) {
		editor.putString("put_" + key, value);
		editor.commit();
	}

	public String get(String key) {
		return sp.getString("put_" + key, "");
	}

	// UserID
	public String getUserId() {
		return sp.getString("_user_id", "");

	}

	public void setUserId(String uid) {
		editor.putString("_user_id", uid);
		editor.commit();

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
}