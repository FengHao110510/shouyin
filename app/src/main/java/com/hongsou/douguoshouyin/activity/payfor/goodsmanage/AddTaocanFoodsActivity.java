package com.hongsou.douguoshouyin.activity.payfor.goodsmanage;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.adapter.AddTaocanCategoryAdapter;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.http.ResponseCallback;
import com.hongsou.douguoshouyin.javabean.FoodCategoryBean;
import com.hongsou.douguoshouyin.tool.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 添加分组套餐中餐品（这个还没写）   添加单人套餐中的商品
 */
public class AddTaocanFoodsActivity extends BaseActivity {

    @BindView(R.id.tv_titlebar_right)
    TextView tvTitlebarRight;
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
        tvTitlebarRight.setVisibility(View.VISIBLE);
        tvTitlebarRight.setText("全选");
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
     * @param
     * @return
     * @author fenghao
     * @date 2018/7/20 0020 下午 15:07
     * @desc
     */
    private void showCategoryList(List<FoodCategoryBean.DataBean> dataBeanList) {
        addTaocanCategoryAdapter = new AddTaocanCategoryAdapter(R.layout.module_item_addfoods_category,dataBeanList);
        rvPayforAddtaocanFoodCategory.setAdapter(addTaocanCategoryAdapter);
        rvPayforAddtaocanFoodCategory.setLayoutManager(new LinearLayoutManager(this));


    }

    /**
     * @author fenghao
     * @date 2018/7/20 0020 下午 15:06
     * @desc 获取food列表
     */
    private void getFoodsList() {

    }


    @OnClick({R.id.tv_titlebar_right, R.id.tv_payfor_addtaocan_food_wancheng})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_titlebar_right:
                //全选
                break;
            case R.id.tv_payfor_addtaocan_food_wancheng:
                //将选择的菜品返回到上个界面显示   走接口？
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