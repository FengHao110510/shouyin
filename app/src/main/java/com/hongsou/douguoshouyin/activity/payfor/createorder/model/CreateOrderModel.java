package com.hongsou.douguoshouyin.activity.payfor.createorder.model;

import android.content.Context;
import android.text.TextUtils;

import com.hongsou.douguoshouyin.activity.payfor.createorder.view.ICreateOrderView;
import com.hongsou.douguoshouyin.base.Constant;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.http.ResponseCallback;
import com.hongsou.douguoshouyin.javabean.FoodBean;
import com.hongsou.douguoshouyin.javabean.FoodCategoryBean;
import com.hongsou.douguoshouyin.tool.Global;
import com.hongsou.douguoshouyin.tool.ToastUtil;

import java.util.ArrayList;
import java.util.List;

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
                        FoodBean resultBean = new FoodBean();
                        resultBean.setData(new ArrayList<FoodBean.DataBean>());
                        if (response.isSuccess()) {
                            List<FoodBean.DataBean> data = response.getData();
                            for (FoodBean.DataBean datum : data) {
                                if ("1".equals(datum.getFoodType())) {
                                    // 单品
                                    List<FoodBean.DataBean.ShopStandarListBean> shopStandarList = datum.getShopStandarList();
                                    if (shopStandarList != null) {
                                        datum.setFoodPrice(shopStandarList.get(0).getSell());
                                        datum.setBtnStr("选规格");
                                        if (shopStandarList.size() == 1) {
                                            datum.setFoodName(datum.getSingleProductName() + "(" + shopStandarList.get(0).getStandardName() + ")");
                                        } else {
                                            datum.setFoodName(datum.getSingleProductName());
                                        }
                                        if (shopStandarList.size() == 1) {
                                            datum.setVisible(false);
                                        } else {
                                            datum.setVisible(true);
                                        }
                                    }
                                    if (!TextUtils.isEmpty(datum.getFoodProductsPicture())) {
                                        String img_url = datum.getFoodProductsPicture();
                                        if (datum.getFoodProductsPicture().contains("--")) {
                                            String[] split = datum.getFoodProductsPicture().split("--");
                                            img_url = split[0];
                                        }
                                        if (img_url.contains(Constant.IMG_REPLACE1) || img_url.contains(Constant.IMG_REPLACE2)) {
                                            img_url = img_url.substring(img_url.indexOf("shopPic/"), img_url.length());
                                        }
                                        datum.setFoodImg(img_url);
                                    }
                                } else if ("0".equals(datum.getFoodType())) {
                                    // 固定套餐
                                    datum.setBtnStr("选规格");
                                    datum.setVisible(false);
                                    datum.setFoodName(datum.getPackageName());
                                    datum.setFoodPrice(datum.getPackagePrice());
                                    if (!TextUtils.isEmpty(datum.getPackagePicture())) {
                                        String img_url = datum.getPackagePicture();
                                        if (datum.getPackagePicture().contains("--")) {
                                            String[] split = datum.getPackagePicture().split("--");
                                            img_url = split[0];
                                        }
                                        if (img_url.contains(Constant.IMG_REPLACE1) || img_url.contains(Constant.IMG_REPLACE2)) {
                                            img_url = img_url.substring(img_url.indexOf("shopPic/"), img_url.length());
                                        }
                                        datum.setFoodImg(img_url);
                                    }
                                } else if ("2".equals(datum.getFoodType())) {
                                    // 组合套餐
                                    datum.setBtnStr("选餐品");
                                    datum.setVisible(true);
                                    datum.setFoodName(datum.getGroupPackageName());
                                    datum.setFoodPrice(datum.getGroupPackagePrice());
                                    // 图片路径
                                    if (!TextUtils.isEmpty(datum.getPackagePicture())) {
                                        String img_url = datum.getPackagePicture();
                                        // 是否有分隔符’-’
                                        if (datum.getPackagePicture().contains("--")) {
                                            String[] split = datum.getPackagePicture().split("--");
                                            img_url = split[0];
                                        }
                                        // 是否有旧数据中无用的字符链接
                                        if (img_url.contains(Constant.IMG_REPLACE1) || img_url.contains(Constant.IMG_REPLACE2)) {
                                            img_url = img_url.substring(img_url.indexOf("shopPic/"), img_url.length());
                                        }
                                        datum.setFoodImg(img_url);
                                    }
                                }

                                if (datum.getSellingStatus().equals("0")) {
                                    resultBean.getData().add(datum);
                                }
                            }

                            callback.onSuccess(resultBean);
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
