package com.hongsou.douguoshouyin.activity.payfor.createorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.activity.payfor.createorder.presenter.CreateOrderPresenter;
import com.hongsou.douguoshouyin.adapter.CreateOrderSelectGroupAdapter2;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.db.FoodZuheTaocanXQ;
import com.hongsou.douguoshouyin.db.SelectMealEntity;
import com.hongsou.douguoshouyin.javabean.FoodBean;
import com.hongsou.douguoshouyin.tool.JavaBeanUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateOrderSelectGroup2Activity extends BaseActivity {

    @BindView(R.id.tv_create_order_select_group_name)
    TextView tvCreateOrderSelectGroupName;
    @BindView(R.id.rv_create_order_select_group_list)
    RecyclerView rvCreateOrderSelectGroupList;

    //数据源
    List<FoodBean.DataBean.ListTwoBean> listTwoBeanList;

    CreateOrderSelectGroupAdapter2 createOrderSelectGroupAdapter2;
    @BindView(R.id.tv_create_order_select_group_jine)
    TextView tvCreateOrderSelectGroupJine;
    @BindView(R.id.tv_create_order_select_group_addmeal)
    TextView tvCreateOrderSelectGroupAddmeal;
    /**
     * 餐品数据
     */
    private FoodBean.DataBean mDataBean;
    private int mFoodPosition;

    @Override
    public int initLayout() {
        return R.layout.module_activity_create_order_select_group2;
    }

    @Override
    protected void init() {
        initData();
        initBack();
        initTitle("组合套餐");
    }

    @Override
    public void initData() {
        if (getIntent().hasExtra("foodPosition")){
            mFoodPosition = getIntent().getIntExtra("foodPosition", -1);
        }
        if (getIntent().hasExtra("data")) {
            mDataBean = new Gson().fromJson(getIntent().getStringExtra("data"), FoodBean.DataBean.class);
            Log.e(TAG, "init: " + getIntent().getStringExtra("data"));
            tvCreateOrderSelectGroupName.setText(mDataBean.getGroupPackageName());
            tvCreateOrderSelectGroupJine.setText("¥ " + mDataBean.getGroupPackagePrice());
            showGroupList();
        } else {
            finish();
        }


    }

    /**
     * @author fenghao
     * @date 2018/8/17 0017 上午 11:18
     * @desc 展示选择组合套餐的列表
     */
    private void showGroupList() {
        listTwoBeanList = mDataBean.getListTwo();
        createOrderSelectGroupAdapter2 = new CreateOrderSelectGroupAdapter2
                (R.layout.module_item_create_order_select_group_item2, listTwoBeanList, this);
        rvCreateOrderSelectGroupList.setAdapter(createOrderSelectGroupAdapter2);
        rvCreateOrderSelectGroupList.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_create_order_select_group_addmeal)
    public void onViewClicked() {
        List<FoodZuheTaocanXQ> foodZuheTaocanXQS = createOrderSelectGroupAdapter2.getList();
        if (createOrderSelectGroupAdapter2.getList() != null) {
            SelectMealEntity mMealListEntity = new SelectMealEntity();
            mMealListEntity.setFoodName(mDataBean.getGroupPackageName());
            mMealListEntity.setFoodProductsNumber(mDataBean.getGroupPackageNumber());
            mMealListEntity.setFoodPrice(mDataBean.getGroupPackagePrice());
            mMealListEntity.setFoodProductsCount(1);
            mMealListEntity.setFoodProductsType(mDataBean.getFoodType());
            mMealListEntity.setIncreasePrice("0");
            mMealListEntity.setMemberPreferences(mDataBean.getVipPrice());
            mMealListEntity.setId(++CreateOrderPresenter.DAO_ID);
            mMealListEntity.setFoodPosition(mFoodPosition);

            List<FoodBean.DataBean.ListBean> source = new ArrayList<>(); // 选中的餐品组合
            List<FoodZuheTaocanXQ> groupFoodList = new ArrayList<>();
            groupFoodList.addAll(foodZuheTaocanXQS);
            for (FoodZuheTaocanXQ listThreeBean : foodZuheTaocanXQS) {
                FoodBean.DataBean.ListBean bean1 = JavaBeanUtils.copyData(listThreeBean, FoodBean.DataBean.ListBean.class);
                bean1.setSingleProductNumber(listThreeBean.getFoodProductsNumber());
                bean1.setSingleQuantity(listThreeBean.getFoodProductsCount());
                source.add(bean1);
            }
            mMealListEntity.setGroupFood(groupFoodList);
            mDataBean.setList(source);
            Intent intent = new Intent();
            intent.putExtra("bean", new Gson().toJson(mDataBean));
            intent.putExtra("meal", new Gson().toJson(mMealListEntity));
            setResult(101, intent);
            finish();
        }

    }
}
