package com.hongsou.douguoshouyin.activity.mine;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.adapter.AddTaocanCategoryAdapter;
import com.hongsou.douguoshouyin.adapter.ChooseIsScanAdapter;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.http.ResponseCallback;
import com.hongsou.douguoshouyin.javabean.BaseBean;
import com.hongsou.douguoshouyin.javabean.FoodBean;
import com.hongsou.douguoshouyin.javabean.FoodCategoryBean;
import com.hongsou.douguoshouyin.javabean.SingleFoodsBean;
import com.hongsou.douguoshouyin.tool.ToastUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseIsScanActivity extends BaseActivity {

    @BindView(R.id.rv_mine_choose_isscan_category)
    RecyclerView rvMineChooseIsscanCategory;
    @BindView(R.id.rv_mine_choose_isscan_foods)
    RecyclerView rvMineChooseIsscanFoods;
    @BindView(R.id.tv_mine_choose_isscan_wancheng)
    TextView tvMineChooseIsscanWancheng;


    //分类适配器
    AddTaocanCategoryAdapter addTaocanCategoryAdapter;
    //分类数据源
    List<FoodCategoryBean.DataBean> dataBeanList;
    //food数据源 修改后的
    List<SingleFoodsBean> singleFoodsBeanList = new ArrayList<>();
    //分类下的餐品 数据
    List<SingleFoodsBean> singleFoodsBeanList2 = new ArrayList<>();
    //适配器
    ChooseIsScanAdapter chooseIsScanAdapter;
    @BindView(R.id.tv_titlebar_right)
    TextView tvTitlebarRight;

    @Override
    public int initLayout() {
        return R.layout.module_activity_mine_choose_is_scan;
    }

    @Override
    protected void init() {
        initData();
        initBack();
        initTitle("选择不参与扫码点餐的商品");
        tvTitlebarRight.setText("全选");
        tvTitlebarRight.setVisibility(View.VISIBLE);
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

        if (dataBeanList.size() < 1) {
            ToastUtil.showToast("暂无商品，请先添加商品");
            finishActivity();
        }
        addTaocanCategoryAdapter = new AddTaocanCategoryAdapter(R.layout.module_item_addfoods_category, dataBeanList);
        rvMineChooseIsscanCategory.setAdapter(addTaocanCategoryAdapter);
        rvMineChooseIsscanCategory.setLayoutManager(new LinearLayoutManager(this));

        addTaocanCategoryAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TextView tvItemAddfoodsCategory;

                //获取其他子控件 变色
                for (int i = 0; i < dataBeanList.size(); i++) {
                    if (i == position) {
                        tvItemAddfoodsCategory = (TextView) adapter.getViewByPosition(rvMineChooseIsscanCategory, position
                                , R.id.tv_item_addfoods_category);
                        tvItemAddfoodsCategory.setBackgroundColor(getResources().getColor(R.color.white));

                    } else {
                        tvItemAddfoodsCategory = (TextView) adapter.getViewByPosition(rvMineChooseIsscanCategory, i
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

                chooseIsScanAdapter.notifyDataSetChanged();
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
                .addParams("state", "1")
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

        for (int i = 0; i < data.size(); i++) {
            //循环商品规格
            foodDataBean = data.get(i);
            if ("1".equals(data.get(i).getFoodType())) {
                for (int k = 0; k < data.get(i).getShopStandarList().size(); k++) {
                    SingleFoodsBean singleFoodsBean = null;
                    if (data.get(i).getShopStandarList().size() > 1) {
                        if (k == 0) {
                            singleFoodsBean = new SingleFoodsBean(foodDataBean.getSingleProductNumber()
                                    , foodDataBean.getShopStandarList().get(k).getStandardNumber()
                                    , foodDataBean.getSingleProductName()
                                    , "repeat"
                                    , foodDataBean.getShopStandarList().get(k).getSell()
                                    , foodDataBean.getFoodProductsPicture()
                                    , foodDataBean.getSingleProductType()
                                    , "1"
                                    , "0"
                                    , 0
                            );
                            singleFoodsBeanList.add(singleFoodsBean);
                        }
                    } else {
                        singleFoodsBean = new SingleFoodsBean(foodDataBean.getSingleProductNumber()
                                , foodDataBean.getShopStandarList().get(k).getStandardNumber()
                                , foodDataBean.getSingleProductName()
                                , foodDataBean.getShopStandarList().get(k).getStandardName()
                                , foodDataBean.getShopStandarList().get(k).getSell()
                                , foodDataBean.getFoodProductsPicture()
                                , foodDataBean.getSingleProductType()
                                , "1"
                                , "0"
                                , 0
                        );
                        singleFoodsBeanList.add(singleFoodsBean);
                    }
                }
            } else if ("0".equals(data.get(i).getFoodType())) {
                //固定套餐
                singleFoodsBeanList.add(new SingleFoodsBean(foodDataBean.getPackageNumber()
                        , "", foodDataBean.getPackageName()
                        , "", foodDataBean.getPackagePrice()
                        , foodDataBean.getPackagePicture()
                        , foodDataBean.getPackageType()
                        , "0"
                        , "0"
                        , 0));
            } else if ("2".equals(data.get(i).getFoodType())) {
                //组合套餐
                singleFoodsBeanList.add(new SingleFoodsBean(foodDataBean.getGroupPackageNumber()
                        , "", foodDataBean.getGroupPackageName()
                        , "", foodDataBean.getGroupPackagePrice()
                        , foodDataBean.getPackagePicture()
                        , foodDataBean.getCateGoryType()
                        , "2"
                        , "0"
                        , 0));
            }

        }

        showFoodsList(data);
    }

    /**
     * @param data
     * @author fenghao
     * @date 2018/7/20 0020 下午 16:55
     * @desc 展示商品列表
     */
    private void showFoodsList(final List<FoodBean.DataBean> data) {
        //取出同类型的商品放入集合2 显示  默认显示第一个分类的数据
        for (int n = 0; n < singleFoodsBeanList.size(); n++) {
            if (singleFoodsBeanList.get(n).getCategoryNumber().equals(dataBeanList.get(0).getCategoryNumber())) {
                singleFoodsBeanList2.add(singleFoodsBeanList.get(n));
            }
        }

        chooseIsScanAdapter = new ChooseIsScanAdapter(R.layout.module_item_choose_isscan, singleFoodsBeanList2);
        rvMineChooseIsscanFoods.setAdapter(chooseIsScanAdapter);
        rvMineChooseIsscanFoods.setLayoutManager(new LinearLayoutManager(this));

        chooseIsScanAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ImageView ivItemChooseIsscanChoose = view.findViewById(R.id.iv_item_choose_isscan_choose);
                if ("0".equals(singleFoodsBeanList2.get(position).getIsScan())) {
                    for (int i = 0; i < singleFoodsBeanList.size(); i++) {
                        if (singleFoodsBeanList2.get(position).getSingleProductNumber().equals(singleFoodsBeanList.get(i).getSingleProductNumber())) {
                            singleFoodsBeanList.get(i).setIsScan("1");
                        }
                    }
                    ivItemChooseIsscanChoose.setImageResource(R.drawable.checkbox);
                } else {
                    for (int i = 0; i < singleFoodsBeanList.size(); i++) {
                        if (singleFoodsBeanList2.get(position).getSingleProductNumber().equals(singleFoodsBeanList.get(i).getSingleProductNumber())) {
                            singleFoodsBeanList.get(i).setIsScan("0");
                        }
                    }
                    ivItemChooseIsscanChoose.setImageResource(R.drawable.checkboxn);
                }
            }
        });

    }

    @OnClick({R.id.tv_titlebar_right, R.id.tv_mine_choose_isscan_wancheng})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_titlebar_right:
                chooseAll();
                break;
            case R.id.tv_mine_choose_isscan_wancheng:
                //上传
                upLoad();
                break;
            default:
                break;
        }
    }

    /**
     * @author fenghao
     * @date 2018/8/1 0001 下午 14:42
     * @desc 上传
     */
    private void upLoad() {
        for (int i = 0 ;i<singleFoodsBeanList.size();i++){
            if ("0".equals(singleFoodsBeanList.get(i).getIsScan())){
                singleFoodsBeanList.remove(i);
                i--;
            }
        }
        Gson gson = new Gson();
//        OkHttpUtils.postString().content(gson.toJson(singleFoodsBeanList)).url(ApiConfig.UPDATE_SINGLE_FOOD_IS_SCAN)
//                .build().execute(new ResponseCallback<BaseBean>(this) {
//            @Override
//            public void onResponse(BaseBean response, int id) {
//                if (response.isSuccess()) {
//                    ToastUtil.showToast("添加成功");
//                    finishActivity();
//                } else {
//                    ToastUtil.showToast(response.getMsg());
//                }
//            }
//        });
        HttpFactory.postString(ApiConfig.UPDATE_SINGLE_FOOD_IS_SCAN, gson.toJson(singleFoodsBeanList), new ResponseCallback<BaseBean>(this) {
            @Override
            public void onResponse(BaseBean response, int id) {
                if (response.isSuccess()) {
                    ToastUtil.showToast("添加成功");
                    finishActivity();
                } else {
                    ToastUtil.showToast(response.getMsg());
                }
            }
        });
    }

    /**
     * @author fenghao
     * @date 2018/8/1 0001 上午 11:37
     * @desc 选择全部
     */
    private void chooseAll() {
        for (int i = 0; i < singleFoodsBeanList2.size(); i++) {
            for (int k = 0; k < singleFoodsBeanList.size(); k++) {
                if (singleFoodsBeanList2.get(i).getSingleProductNumber().equals(singleFoodsBeanList.get(k).getSingleProductNumber())) {
                    singleFoodsBeanList.get(k).setIsScan("1");
                }
            }
            ImageView ivItemChooseIsscanImg = (ImageView) chooseIsScanAdapter.getViewByPosition(rvMineChooseIsscanFoods, i, R.id.iv_item_choose_isscan_choose);
            ivItemChooseIsscanImg.setImageResource(R.drawable.checkbox);
        }
    }

    //=================================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
