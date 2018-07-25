package com.hongsou.douguoshouyin.activity.payfor.goodsmanage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.adapter.AddTaocanCategoryAdapter;
import com.hongsou.douguoshouyin.adapter.AddTaocanFoodsAdapter;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.http.ResponseCallback;
import com.hongsou.douguoshouyin.javabean.FoodBean;
import com.hongsou.douguoshouyin.javabean.FoodCategoryBean;
import com.hongsou.douguoshouyin.javabean.SingleFoodsBean;
import com.hongsou.douguoshouyin.tool.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 添加分组套餐中餐品   添加单人套餐中的商品
 */
public class AddTaocanFoodsActivity extends BaseActivity {

    @BindView(R.id.rv_payfor_addtaocan_food_category)
    RecyclerView rvPayforAddtaocanFoodCategory;
    @BindView(R.id.rv_payfor_addtaocan_food_foods)
    RecyclerView rvPayforAddtaocanFoodFoods;
    @BindView(R.id.tv_payfor_addtaocan_food_wancheng)
    TextView tvPayforAddtaocanFoodWancheng;


    //分类适配器
    AddTaocanCategoryAdapter addTaocanCategoryAdapter;
    //分类数据源
    List<FoodCategoryBean.DataBean> dataBeanList;
    //food数据源 修改后的
    List<SingleFoodsBean> singleFoodsBeanList = new ArrayList<>();
    //分类下的餐品 数据
    List<SingleFoodsBean> singleFoodsBeanList2 = new ArrayList<>();

    //适配器
    AddTaocanFoodsAdapter addTaocanFoodsAdapter;
    //分类编号
    private String categoryNumber;

    @Override
    public int initLayout() {
        return R.layout.module_activity_payfor_addtaocan_food;
    }

    @Override
    protected void init() {
        initView();
        initData();
        initBack();
    }

    @Override
    public void initView() {
        initTitle("添加套餐商品");
    }

    @Override
    public void initData() {
        getCategoryList();
        getFoodsList();
    }


    /**
     * @author fenghao
     * @date 2018/7/20 0020 下午 15:05
     * @desc 获取分类列表
     */
    private void getCategoryList() {
        HttpFactory.get().url(ApiConfig.GET_CATEGORY)
                .addParams("shopNumber", getShopNumber())
                .build().execute(new ResponseCallback<FoodCategoryBean>(this) {
            @Override
            public void onResponse(FoodCategoryBean response, int id) {
                if (response.isSuccess()) {
                    dataBeanList = response.getData();
                    if (dataBeanList.size() > 0) {
                        showCategoryList(dataBeanList);
                    } else {
                        ToastUtil.showToast("暂无分类，请先添加分类");
                    }
                } else {
                    ToastUtil.showToast(response.getMsg());
                }
            }
        });
    }

    /**
     * @param dataBeanList 分类数据源
     * @author fenghao
     * @date 2018/7/20 0020 下午 15:07
     * @desc 展示分类列表
     */
    private void showCategoryList(final List<FoodCategoryBean.DataBean> dataBeanList) {
        for (int k = 0; k < dataBeanList.size(); k++) {
            //为了展示单品
            if (!"0".equals(dataBeanList.get(k).getCategoryType())) {
                dataBeanList.remove(k);
                k--;
            }
        }
        if (dataBeanList.size() < 1) {
            ToastUtil.showToast("暂无商品，请先添加商品");
            finishActivity();
        }
        addTaocanCategoryAdapter = new AddTaocanCategoryAdapter(R.layout.module_item_addfoods_category, dataBeanList);
        rvPayforAddtaocanFoodCategory.setAdapter(addTaocanCategoryAdapter);
        rvPayforAddtaocanFoodCategory.setLayoutManager(new LinearLayoutManager(this));

        addTaocanCategoryAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TextView tvItemAddfoodsCategory;

                //获取其他子控件 变色
                for (int i = 0; i < dataBeanList.size(); i++) {
                    if (i == position) {
                        tvItemAddfoodsCategory = (TextView) adapter.getViewByPosition(rvPayforAddtaocanFoodCategory, position
                                , R.id.tv_item_addfoods_category);
                        tvItemAddfoodsCategory.setBackgroundColor(getResources().getColor(R.color.white));

                    } else {
                        tvItemAddfoodsCategory = (TextView) adapter.getViewByPosition(rvPayforAddtaocanFoodCategory, i
                                , R.id.tv_item_addfoods_category);
                        tvItemAddfoodsCategory.setBackgroundColor(getResources().getColor(R.color.bg_gray));
                    }
                }
                singleFoodsBeanList2.clear();
                //取出同类型的商品放入集合2 显示
                for (int n = 0; n < singleFoodsBeanList.size(); n++) {
                    if (singleFoodsBeanList.get(n).getCategoryNumber().equals(dataBeanList.get(position).getCategoryNumber())) {
                        singleFoodsBeanList2.add(singleFoodsBeanList.get(n));
                    }
                }
                addTaocanFoodsAdapter.notifyDataSetChanged();
            }
        });

    }

    /**
     * @author fenghao
     * @date 2018/7/20 0020 下午 15:06
     * @desc 获取food列表
     */
    private void getFoodsList() {
        HttpFactory.get().url(ApiConfig.GET_FOOD)
                .addParams("shopNumber", getShopNumber())
                .build().execute(new ResponseCallback<FoodBean>(this) {
            @Override
            public void onResponse(FoodBean response, int id) {
                if (response.isSuccess()) {
                    List<FoodBean.DataBean> data = response.getData();
                    setSingleFoodsBean(data);
                } else {
                    ToastUtil.showToast(response.getMsg());
                }
            }
        });
    }

    /**
     * @param data 原始数据
     * @author fenghao
     * @date 2018/7/20 0020 下午 16:36
     * @desc 设置数据
     */
    private void setSingleFoodsBean(List<FoodBean.DataBean> data) {
        FoodBean.DataBean foodDataBean;
        for (int k = 0; k < data.size(); k++) {
            //为了展示单品 1  0 固定套餐 2 分组套餐
            if (!"1".equals(data.get(k).getFoodType())) {
                data.remove(k);
                k--;
            }
        }
        for (int i = 0; i < data.size(); i++) {
            //循环商品规格
            foodDataBean = data.get(i);
            for (int k = 0; k < data.get(i).getShopStandarList().size(); k++) {
                SingleFoodsBean singleFoodsBean = new SingleFoodsBean(foodDataBean.getSingleProductNumber()
                        , foodDataBean.getShopStandarList().get(k).getStandardNumber()
                        , foodDataBean.getSingleProductName()
                        , foodDataBean.getShopStandarList().get(k).getStandardName()
                        , foodDataBean.getShopStandarList().get(k).getSell()
                        , foodDataBean.getFoodProductsPicture()
                        , foodDataBean.getSingleProductType()
                        , 0
                );
                singleFoodsBeanList.add(singleFoodsBean);
            }
        }
        //取出同类型的商品放入集合2 显示  默认显示第一个分类的数据
        for (int n = 0; n < singleFoodsBeanList.size(); n++) {
            if (singleFoodsBeanList.get(n).getCategoryNumber().equals(dataBeanList.get(0).getCategoryNumber())) {
                singleFoodsBeanList2.add(singleFoodsBeanList.get(n));
            }
        }
        showFoodsList();
    }

    /**
     * @author fenghao
     * @date 2018/7/20 0020 下午 16:55
     * @desc 展示商品列表
     */
    private void showFoodsList() {
        addTaocanFoodsAdapter = new AddTaocanFoodsAdapter(R.layout.module_recycle_item_create_order_food, singleFoodsBeanList2,0);
        rvPayforAddtaocanFoodFoods.setAdapter(addTaocanFoodsAdapter);
        rvPayforAddtaocanFoodFoods.setLayoutManager(new LinearLayoutManager(this));

        //给加减设置点击事件
        addTaocanFoodsAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                TextView tvFoodCount = (TextView) addTaocanFoodsAdapter.getViewByPosition(rvPayforAddtaocanFoodFoods, position, R.id.tv_food_count);
                switch (view.getId()) {
                    case R.id.tv_subtract:
                        //减
                        //获取点击的商品编号 去找singleFoodsBeanList中的对应商品-1
                        for (int i = 0; i < singleFoodsBeanList.size(); i++) {
                            if (singleFoodsBeanList.get(i).getStandardNumber().
                                    equals(singleFoodsBeanList2.get(position).getStandardNumber())) {
                                if (singleFoodsBeanList.get(i).getSingleQuantity() < 1) {
                                    ToastUtil.showToast("商品数量不得小于0");
                                } else {
                                    tvFoodCount.setText((singleFoodsBeanList.get(i).getSingleQuantity() - 1) + "");
                                    singleFoodsBeanList.get(i).setSingleQuantity(singleFoodsBeanList.get(i).getSingleQuantity() - 1);
                                }

                            }
                        }
                        break;
                    case R.id.tv_add:
                        //加
                        //获取点击的商品编号 去找singleFoodsBeanList中的对应商品+1
                        for (int i = 0; i < singleFoodsBeanList.size(); i++) {
                            if (singleFoodsBeanList.get(i).getStandardNumber().
                                    equals(singleFoodsBeanList2.get(position).getStandardNumber())) {
                                tvFoodCount.setText((singleFoodsBeanList.get(i).getSingleQuantity() + 1) + "");
                                singleFoodsBeanList.get(i).setSingleQuantity(singleFoodsBeanList.get(i).getSingleQuantity() + 1);
                                Log.e(TAG, "onItemChildClick: " + tvFoodCount.getText().toString());
                            }
                        }
                        break;
                    default:
                        break;

                }
            }
        });
    }


    @Override
    public void initBack() {
        super.initBack();
        startForTaocanResult();

    }

    private void startForTaocanResult() {
        Intent intentPut = new Intent();
        intentPut.putExtra("singleFoodsBeanList", (Serializable) singleFoodsBeanList);
        setResult(2, intentPut);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startForTaocanResult();


    }

    @OnClick({R.id.tv_payfor_addtaocan_food_wancheng})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.tv_payfor_addtaocan_food_wancheng:
                //将选择的菜品返回到上个界面显示
                startForTaocanResult();
                finishActivity();
                break;
            default:
                break;
        }
    }


    //=============================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}