package com.hongsou.douguoshouyin.activity.payfor.createorder.model;

import android.content.Context;

import com.hongsou.douguoshouyin.activity.payfor.createorder.view.ICreateOrderView;

/**
 * @author lpc
 *         <p>
 * @copyright 鸿搜网络公司 版权所有
 * <p>
 * @date 2018/7/17
 * <p>
 * @desc 获取餐品数据的接口
 */

public interface ICreateOrderModel<T> {

    /**
     * @desc 获取餐品数据
     * @anthor lpc
     * @date: 2018/7/17
     * @param callback 数据回调
     */
    void getFood(Context context, ICreateOrderView<T> callback);

    /**
     * @desc 获取分类信息
     * @anthor lpc
     * @date: 2018/7/17
     * @param
     */
    void getCategory(Context context, ICreateOrderView<T> callback);
}
