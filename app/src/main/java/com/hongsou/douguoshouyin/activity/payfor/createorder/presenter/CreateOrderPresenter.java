package com.hongsou.douguoshouyin.activity.payfor.createorder.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.hongsou.douguoshouyin.activity.payfor.createorder.model.ICreateOrderModel;
import com.hongsou.douguoshouyin.activity.payfor.createorder.view.ICreateOrderView;
import com.hongsou.douguoshouyin.base.Constant;
import com.hongsou.douguoshouyin.db.FoodZuheTaocanXQ;
import com.hongsou.douguoshouyin.db.PackageFoodEntity;
import com.hongsou.douguoshouyin.db.SelectMealEntity;
import com.hongsou.douguoshouyin.javabean.FoodBean;
import com.hongsou.douguoshouyin.tool.JavaBeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lpc
 *         <p>
 * @copyright 鸿搜网络公司 版权所有
 * <p>
 * @date 2018/7/17
 * <p>
 * @desc 开单主页面的presenter
 */

public class CreateOrderPresenter {

    private Context mContext;
    private ICreateOrderModel mICreateOrderModel;
    private ICreateOrderView mICreateOrderView;

    public static final int TAG_ADD = 1;
    public static final int TAG_SUBTRACT = 2;
    public static final int TAG_CLEAR = 3;

    public static int DAO_ID = 0;

    private List<SelectMealEntity> mealForSelects;

    public CreateOrderPresenter(Context context, ICreateOrderModel icreateordermodel, ICreateOrderView icreateorderview) {
        mContext = context;
        mICreateOrderModel = icreateordermodel;
        mICreateOrderView = icreateorderview;
        mealForSelects = new ArrayList<SelectMealEntity>();
    }

    /**
     * @param foodBeanList      选择后的数据
     * @param finalFoodBeanList 全部餐品数据
     * @desc 根据分类切换数据显示
     * @anthor lpc
     * @date: 2018/7/18
     */
    public void getFoodByCategory(List<FoodBean.DataBean> foodBeanList, List<FoodBean.DataBean> finalFoodBeanList, String type) {
        foodBeanList.clear();
        if ("-1".equals(type)) {
            // 全部
            foodBeanList.addAll(finalFoodBeanList);
        } else {
            for (FoodBean.DataBean entity : finalFoodBeanList) {
                if (type.equals(entity.getCateGoryType())) {
                    foodBeanList.add(entity);
                }
            }
        }
    }

    /**
     * @param
     * @desc 获取餐品数据
     * @anthor lpc
     * @date: 2018/7/17
     */
    public void getFoodList(String state) {
        mICreateOrderModel.getFood(mContext, state, mICreateOrderView);
    }

    /**
     * @param
     * @return
     * @desc 获取餐品分类信息
     * @anthor lpc
     * @date: 2018/7/17
     */
    public void getCategory() {
        mICreateOrderModel.getCategory(mContext, mICreateOrderView);
    }


    /**
     * @param
     * @desc 餐品数量+1
     * @anthor lpc
     * @date: 2018/7/17
     */
    public void addFood(List<FoodBean.DataBean> beanList, Object obj, int standardPosition) {
        if (obj instanceof FoodBean.DataBean) {
            saveSelectFoods(TAG_ADD, beanList, beanConverter(((FoodBean.DataBean) obj), standardPosition));
        } else if (obj instanceof SelectMealEntity) {
            saveSelectFoods(TAG_ADD, beanList, (SelectMealEntity) obj);
        }
    }

    /**
     * @param
     * @return
     * @desc 减少一条餐品
     * @anthor lpc
     * @date: 2018/7/17
     */
    public void subtractFood(List<FoodBean.DataBean> beanList, Object obj, int standardPosition) {
        if (obj instanceof FoodBean.DataBean) {
            saveSelectFoods(TAG_SUBTRACT, beanList, beanConverter(((FoodBean.DataBean) obj), standardPosition));
        } else if (obj instanceof SelectMealEntity) {
            saveSelectFoods(TAG_SUBTRACT, beanList, (SelectMealEntity) obj);
        }
    }

    /**
     * @param
     * @return
     * @desc 清空购物车
     * @anthor lpc
     * @date: 2018/7/17
     */
    public void clearFoods(List<FoodBean.DataBean> beanList) {
        saveSelectFoods(TAG_CLEAR, beanList, null);
    }

    /**
     * @param bean             转换前的餐品对象
     * @param standardPosition 单品规格的下标
     * @return 转换后的餐品对象
     * @desc 将餐品转换为点餐后的餐品对象
     * @anthor lpc
     * @date: 2018/7/18
     */
    private SelectMealEntity beanConverter(FoodBean.DataBean bean, int standardPosition) {
        SelectMealEntity entity = new SelectMealEntity();
        entity.setId(++DAO_ID);
        if ("1".equals(bean.getFoodType())) {
            // 单品
            entity.setFoodName(bean.getSingleProductName());
            entity.setFoodProductsNumber(bean.getSingleProductNumber());
            entity.setFoodProductsCount(1);
            entity.setFoodProductsType(bean.getFoodType());
            entity.setIncreasePrice("0");
            entity.setFoodProductPicture(bean.getFoodProductsPicture());
            if (standardPosition >= 0) {
                FoodBean.DataBean.ShopStandarListBean shopStandarListBean = bean.getShopStandarList().get(standardPosition);
                entity.setStandardName(shopStandarListBean.getStandardName());
                entity.setStandard(shopStandarListBean.getStandardNumber());
                entity.setStandardNumber(shopStandarListBean.getStandardNumber());
                entity.setFoodPrice(shopStandarListBean.getSell());
                entity.setMemberPreferences(shopStandarListBean.getVipPrice());
            }
        } else if ("0".equals(bean.getFoodType())) {
            // 固定套餐
            entity.setFoodName(bean.getPackageName());
            entity.setFoodProductsType(bean.getFoodType());
            entity.setFoodPrice(bean.getPackagePrice());
            entity.setFoodProductsNumber(bean.getPackageNumber());
            entity.setPackageNumber(bean.getPackageNumber());
            entity.setFoodProductsCount(1);
            entity.setMemberPreferences(bean.getVipprice());
            entity.setIncreasePrice("0");
            List<PackageFoodEntity> entityList = new ArrayList<>();
            for (FoodBean.DataBean.ListBean listBean : bean.getList()) {
                PackageFoodEntity packageFoodEntity = JavaBeanUtils.copyData(listBean, PackageFoodEntity.class);
                entityList.add(packageFoodEntity);
            }
            entity.setPackageFood(entityList);
            entity.setFoodProductPicture(bean.getPackagePicture());
        } else if ("2".equals(bean.getFoodType())) {
            // 组合套餐
            entity.setFoodName(bean.getGroupPackageName());
            entity.setFoodProductsNumber(bean.getGroupPackageNumber());
            entity.setFoodPrice(bean.getGroupPackagePrice());
            entity.setFoodProductsCount(1);
            entity.setFoodProductsType(bean.getFoodType());
            entity.setIncreasePrice("0");
            entity.setMemberPreferences(bean.getVipprice());
            List<PackageFoodEntity> entityList = new ArrayList<>();
            List<FoodZuheTaocanXQ> groups = new ArrayList<>();
            for (FoodBean.DataBean.ListBean listBean : bean.getList()) {
                PackageFoodEntity packageFoodEntity = JavaBeanUtils.copyData(listBean, PackageFoodEntity.class);
                entityList.add(packageFoodEntity);
            }
            entity.setPackageFood(entityList);
            entity.setFoodProductPicture(bean.getPackagePicture());
        }
        return entity;
    }

    /**
     * @param
     * @param tag    操作类型
     * @param entity 操作对象
     * @desc 保存选择的餐品数据
     * @anthor lpc
     * @date: 2018/7/17
     */
    private void saveSelectFoods(int tag, List<FoodBean.DataBean> beanList, SelectMealEntity entity) {
        int indexOf = mealForSelects.indexOf(entity);
        Log.e("saveSelectFoods:", "小标 ：： " + indexOf);
        if (tag == TAG_ADD) {
            int count = 0;
            if (indexOf < 0) {
                mealForSelects.add(entity);
                count = 1;
            } else {
                // 如果是组合套餐，那么每次增加都新增一个对象，其他的需要判断是否需要新增
                count = mealForSelects.get(indexOf).getFoodProductsCount() + 1;
            }
            setSelectCount(beanList, entity, indexOf, count);
        } else if (tag == TAG_SUBTRACT) {
            if (mealForSelects.size() <= 0 || indexOf < 0) {
                return;
            }
            int count = mealForSelects.get(indexOf).getFoodProductsCount() - 1;
            setSelectCount(beanList, entity, indexOf, count);
            if (count < 1) {
                mealForSelects.remove(indexOf);
            }
        } else if (tag == TAG_CLEAR) {
            mealForSelects.clear();
            setSelectCount(beanList, null, -1, 0);
        }

        setTotalResult();
    }

    /**
     * @desc 设置操作结束的数量和金额和购物车列表数据
     * @anthor lpc
     * @date: 2018/7/19
     */
    private void setTotalResult() {
        double allPrice = 0;
        int totalCount = 0;
        for (SelectMealEntity listEntity : mealForSelects) {
            String priceStr;
            if ("1".equals(listEntity.getFoodProductsType())) {
                priceStr = listEntity.getFoodPrice() != null ? listEntity.getFoodPrice() : listEntity.getSell();
            } else if ("2".equals(listEntity.getFoodProductsType())) {
                priceStr = listEntity.getFoodPrice();
            } else {
                priceStr = listEntity.getFoodPrice() != null ? listEntity.getFoodPrice() : listEntity.getPackagePrice();
            }
            int currentNum = listEntity.getFoodProductsCount();
            String in = listEntity.getIncreasePrice();
            if (TextUtils.isEmpty(in)) {
                in = "0";
            }
            allPrice += ((Double.valueOf(priceStr)) * currentNum) + Double.valueOf(in);
            totalCount += listEntity.getFoodProductsCount();
        }
        String totalMoney = Constant.DECIMAL_FORMAT.format(allPrice);
        mICreateOrderView.changeSelectFoodCallBack(mealForSelects, totalMoney, totalCount);
    }

    /**
     * @param beanList 全部餐品列表数据
     * @param entity   操作对应的餐品对象
     * @param indexOf  购物车列表对应的下标位置
     * @param count    剩下的选中数量
     * @desc 设置选择的餐品数量
     * @anthor lpc
     * @date: 2018/7/19
     */
    private void setSelectCount(List<FoodBean.DataBean> beanList, SelectMealEntity entity, int indexOf, int count) {
        // 是否是清空操作
        boolean clear = false;
        if (entity != null){
            entity.setFoodProductsCount(count);
            clear = false;
        }else {
            clear = true;
        }
        for (FoodBean.DataBean dataBean : beanList) {
            if ("0".equals(dataBean.getFoodType())) {
                if (clear){
                    // 清空购物车，将选择的数量清零
                    dataBean.setFoodProductsCount(0);
                    continue;
                }
                if (dataBean.getPackageNumber().equals(entity.getPackageNumber())) {
                    dataBean.setFoodProductsCount(count);
                    break;
                }
            } else if ("1".equals(dataBean.getFoodType())) {
                // 判断单品
                if (clear){
                    // 清空购物车，将选择的数量清零
                    dataBean.setFoodProductsCount(0);
                    for (FoodBean.DataBean.ShopStandarListBean shopStandarListBean : dataBean.getShopStandarList()) {
                        shopStandarListBean.setSelectCount(0);
                    }
                    continue;
                }
                if (dataBean.getSingleProductNumber().equals(entity.getFoodProductsNumber())) {
                    int c = 0;
                    // 再循环判断单品对应的规格
                    for (FoodBean.DataBean.ShopStandarListBean shopStandarListBean : dataBean.getShopStandarList()) {
                        if (shopStandarListBean.getStandardNumber().equals(entity.getStandardNumber())) {
                            shopStandarListBean.setSelectCount(count);
                            c += count;
                        } else {
                            c += shopStandarListBean.getSelectCount();
                        }
                    }
                    dataBean.setFoodProductsCount(c);
                }
            } else if ("2".equals(dataBean.getFoodType())) {
                if (clear){
                    // 清空购物车，将选择的数量清零
                    dataBean.setFoodProductsCount(0);
                    continue;
                }
                // 先找到对应的组合套餐
                if (dataBean.getGroupPackageNumber().equals(entity.getFoodProductsNumber())) {
                    int c = 0;
                    // 循环购物车
                    for (int i = 0; i < mealForSelects.size(); i++) {
                        // 判断操作的是否是当前下标
                        if (i != indexOf) {
                            // 判断是否是同一个组合套餐
                            if (mealForSelects.get(i).getFoodProductsNumber().equals(entity.getFoodProductsNumber())) {
                                // 同一个组合套餐，只是单品内容不同，需要累加所有数量
                                c += mealForSelects.get(i).getFoodProductsCount();
                            }
                        } else {
                            // 当前操作的组合套餐，直接累加count
                            c += count;
                        }
                    }
                    dataBean.setFoodProductsCount(c);
                    break;
                }
            }
        }
        if (indexOf >= 0) {
            mealForSelects.set(indexOf, entity);
        }
    }
}
