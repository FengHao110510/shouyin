package com.hongsou.douguoshouyin.activity.payfor.createorder.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.hongsou.douguoshouyin.activity.payfor.createorder.model.ICreateOrderModel;
import com.hongsou.douguoshouyin.activity.payfor.createorder.view.ICreateOrderView;
import com.hongsou.douguoshouyin.base.Constant;
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

    public int DAO_ID = 0;

    private List<SelectMealEntity> mealForSelects;

    public CreateOrderPresenter(Context context, ICreateOrderModel icreateordermodel, ICreateOrderView icreateorderview) {
        mContext = context;
        mICreateOrderModel = icreateordermodel;
        mICreateOrderView = icreateorderview;
        mealForSelects = new ArrayList<SelectMealEntity>();
    }

    /**
     * @desc 根据分类切换数据显示
     * @anthor lpc
     * @date: 2018/7/18
     * @param foodBeanList 选择后的数据
     * @param finalFoodBeanList 全部餐品数据
     */
    public void getFoodByCategory(List<FoodBean.DataBean> foodBeanList, List<FoodBean.DataBean> finalFoodBeanList, String type){
        foodBeanList.clear();
        if ("-1".equals(type)){
            // 全部
            foodBeanList.addAll(finalFoodBeanList);
        }else {
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
    public void getFoodList() {
        mICreateOrderModel.getFood(mContext, mICreateOrderView);
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
        if (obj instanceof FoodBean.DataBean){
            saveSelectFoods(TAG_ADD, beanList, beanConverter(((FoodBean.DataBean) obj), standardPosition), standardPosition);
        }else if (obj instanceof SelectMealEntity){
            saveSelectFoods(TAG_ADD, beanList, (SelectMealEntity) obj, standardPosition);
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
        if (obj instanceof FoodBean.DataBean){
            saveSelectFoods(TAG_SUBTRACT, beanList, beanConverter(((FoodBean.DataBean) obj), standardPosition), standardPosition);
        }else if (obj instanceof SelectMealEntity){
            saveSelectFoods(TAG_SUBTRACT, beanList, (SelectMealEntity) obj, standardPosition);
        }
    }

    /**
     * @param
     * @return
     * @desc 清空购物车
     * @anthor lpc
     * @date: 2018/7/17
     */
    public void clearFoods() {
        saveSelectFoods(TAG_CLEAR, null, null, -1);
    }

    /**
     * @desc 将餐品转换为点餐后的餐品对象
     * @anthor lpc
     * @date: 2018/7/18
     * @param bean 转换前的餐品对象
     * @param standardPosition 单品规格的下标
     * @return 转换后的餐品对象
     */
    private SelectMealEntity beanConverter(FoodBean.DataBean bean, int standardPosition){
        SelectMealEntity entity = new SelectMealEntity();
        entity.setId(++DAO_ID);
        if ("1".equals(bean.getFoodType()) ) {
            // 单品
            entity.setFoodName(bean.getSingleProductName());
            entity.setFoodProductsNumber(bean.getSingleProductNumber());
            entity.setFoodProductsCount(1);
            entity.setFoodProductsType(bean.getFoodType());
            entity.setIncreasePrice("0");
            if ( standardPosition >= 0){
                FoodBean.DataBean.ShopStandarListBean shopStandarListBean = bean.getShopStandarList().get(standardPosition);
                entity.setStandardName(shopStandarListBean.getStandardName());
                entity.setStandard(shopStandarListBean.getStandardNumber());
                entity.setStandardNumber(shopStandarListBean.getStandardNumber());
                entity.setFoodPrice(shopStandarListBean.getSell());
                entity.setMemberPreferences(shopStandarListBean.getVipPrice());
            }
        }else if ("0".equals(bean.getFoodType())){
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
        }else if ("2".equals(bean.getFoodType())){
            // 组合套餐

//            entity.setFoodName(bean.getSingleProductName());
//            entity.setFoodProductsNumber(bean.getSingleProductNumber());
//            entity.setFoodProductsCount(1);
//            entity.setFoodProductsType(bean.getFoodType());
//            entity.setIncreasePrice("0");
//            entity.setFoodPrice(bean.getGroupPackagePrice());
//            entity.setMemberPreferences(bean.getVipprice());
        }
        return entity;
    }

    /**
     * @param
     * @param tag      操作类型
     * @param entity   操作对象
     * @param position
     * @desc 保存选择的餐品数据
     * @anthor lpc
     * @date: 2018/7/17
     */
    private void saveSelectFoods(int tag, List<FoodBean.DataBean> beanList, SelectMealEntity entity, int position) {
        int indexOf = mealForSelects.indexOf(entity);
        if (tag == TAG_ADD) {
            if (indexOf < 0) {
                mealForSelects.add(entity);
            } else {
                int count = mealForSelects.get(indexOf).getFoodProductsCount() + 1;
                setSelectCount(beanList, entity, indexOf, count);
            }
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
            if (listEntity.getFoodProductsType().equals("1")) {
                priceStr = listEntity.getFoodPrice() != null ? listEntity.getFoodPrice() : listEntity.getSell();
            } else if (listEntity.getFoodProductsType().equals("2")) {
                priceStr =listEntity.getFoodPrice();
            } else {
                priceStr =listEntity.getFoodPrice() != null ? listEntity.getFoodPrice() : listEntity.getPackagePrice();
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
     * @desc 设置选择的餐品数量
     * @anthor lpc
     * @date: 2018/7/19
     * @param beanList 全部餐品列表数据
     * @param entity 操作对应的餐品对象
     * @param indexOf 购物车列表对应的下标位置
     * @param count 剩下的选中数量
     */
    private void setSelectCount(List<FoodBean.DataBean> beanList, SelectMealEntity entity, int indexOf, int count) {
        entity.setFoodProductsCount(count);
        for (FoodBean.DataBean dataBean : beanList) {
            if ("0".equals(dataBean.getFoodType())){
                if (dataBean.getPackageNumber().equals(entity.getPackageNumber())){
                    dataBean.setFoodProductsCount(count);
                    break;
                }
            }
        }
        mealForSelects.set(indexOf, entity);
    }
}
