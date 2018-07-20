package com.hongsou.douguoshouyin.myinterface;

/**
 * @author lpc
 *         <p>
 * @copyright 鸿搜网络公司 版权所有
 * <p>
 * @date 2018/7/17
 * <p>
 * @desc 加减号的监听
 */

public interface OnAddSubtractClickLinstenter {
    /**
     * @desc 加号的点击回调
     * @anthor lpc
     * @date: 2018/7/17
     * @param o 数据对象
     * @param position 点击位置
     */
    void onAddLinstenter(Object o, int position);
    /**
     * @desc 减号的点击回调
     * @anthor lpc
     * @date: 2018/7/17
     * @param o 数据对象
     * @param position 点击位置
     */
    void onSubtractLinstenter(Object o, int position);
}
