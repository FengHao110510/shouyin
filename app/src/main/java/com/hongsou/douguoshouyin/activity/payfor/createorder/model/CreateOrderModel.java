package com.hongsou.douguoshouyin.activity.payfor.createorder.model;

import android.content.Context;

import com.hongsou.douguoshouyin.activity.payfor.createorder.view.ICreateOrderView;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.http.ResponseCallback;
import com.hongsou.douguoshouyin.javabean.FoodBean;
import com.hongsou.douguoshouyin.javabean.FoodCategoryBean;
import com.hongsou.douguoshouyin.tool.Global;
import com.hongsou.douguoshouyin.tool.ToastUtil;

/**
 * @author lpc
 *         <p>
 * @copyright 鸿搜网络公司 版权所有
 * <p>
 * @date 2018/7/17
 * <p>
 * @desc 获取餐品数据的接口实现
 */

public class CreateOrderModel implements ICreateOrderModel {


    @Override
    public void getFood(Context context, String state, final ICreateOrderView callback) {
        HttpFactory.get()
                .url(ApiConfig.GET_FOOD)
                .addParams("shopNumber", Global.getSpGlobalUtil().getShopNumber())
                .addParams("state", state)
                .build()
                .execute(new ResponseCallback<FoodBean>(context) {
                    @Override
                    public void onResponse(FoodBean response, int id) {
                        if (response.isSuccess()) {
                            callback.onSuccess(response);
                        } else {
                            ToastUtil.showToast(response.getMsg());
                        }
                    }
                });
    }

    @Override
    public void getCategory(Context context, final ICreateOrderView callback) {
        HttpFactory.get()
                .url(ApiConfig.GET_CATEGORY)
                .addParams("shopNumber", Global.getSpGlobalUtil().getShopNumber())
                .build()
                .execute(new ResponseCallback<FoodCategoryBean>(context) {
                    @Override
                    public void onResponse(FoodCategoryBean response, int id) {
                        if (response.isSuccess()) {
                            callback.onSuccess(response);
                        } else {
                            ToastUtil.showToast(response.getMsg());
                        }
                    }
                });
    }
}
