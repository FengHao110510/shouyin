package com.hongsou.douguoshouyin.activity.payfor.createorder.view;

import com.hongsou.douguoshouyin.db.SelectMealEntity;

import java.util.List;

/**
 * @author lpc
 *         <p>
 * @copyright 鸿搜网络公司 版权所有
 * <p>
 * @date 2018/7/17
 * <p>
 * @desc 数据 接口回调
 */

public interface ICreateOrderView<T> {
    void onSuccess(T response);

//    void onError();

    void changeSelectFoodCallBack(List<SelectMealEntity> entities, String totalMoney, int totalCount);

}
