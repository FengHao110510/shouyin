package com.hongsou.douguoshouyin.activity.payfor.createorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.activity.payfor.createorder.presenter.CreateOrderPresenter;
import com.hongsou.douguoshouyin.adapter.CreateOrderSelectGroupAdapter;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.db.FoodZuheTaocanXQ;
import com.hongsou.douguoshouyin.db.SelectMealEntity;
import com.hongsou.douguoshouyin.javabean.FoodBean;
import com.hongsou.douguoshouyin.tool.JavaBeanUtils;
import com.hongsou.douguoshouyin.tool.ToastUtil;
import com.hongsou.douguoshouyin.views.CommonTopBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @copyright: 鸿搜网络公司 版权所有
 * <p>
 * @author: lpc
 * <p>
 * @date：2018/7/16 <p>
 * @desc：开单流程，选择组合套餐页面
 */
public class CreateOrderSelectGroupActivity extends BaseActivity {

    @BindView(R.id.top_bar)
    CommonTopBar mTopBar;
    @BindView(R.id.tv_group_price)
    TextView mTvGroupPrice;
    @BindView(R.id.tv_add_meal)
    TextView mTvAddMeal;
    @BindView(R.id.ll_bottom)
    LinearLayout mLlBottom;
    @BindView(R.id.tv_group_food_name)
    TextView mTvGroupFoodName;
    @BindView(R.id.tv_group_name1)
    TextView mTvGroupName1;
    @BindView(R.id.tv_mandatory_count1)
    TextView mTvMandatoryCount1;
    @BindView(R.id.tv_selected_count1)
    TextView mTvSelectedCount1;
    @BindView(R.id.recycler_group1)
    RecyclerView mRecyclerGroup1;
    @BindView(R.id.tv_group_name2)
    TextView mTvGroupName2;
    @BindView(R.id.tv_mandatory_count2)
    TextView mTvMandatoryCount2;
    @BindView(R.id.tv_selected_count2)
    TextView mTvSelectedCount2;
    @BindView(R.id.recycler_group2)
    RecyclerView mRecyclerGroup2;
    @BindView(R.id.ll_group2)
    LinearLayout mLlGroup2;

    private LayoutInflater mInflater;
    /**
     * 餐品数据
     */
    private FoodBean.DataBean mDataBean;
    /**
     * 分组A的餐品
     */
    private List<List<FoodZuheTaocanXQ>> mListThree1;
    /**
     * 分组B的餐品
     */
    private List<List<FoodZuheTaocanXQ>> mListThree2;
    /**
     * 已选中的分组A餐品
     */
    private List<FoodZuheTaocanXQ> mListThreeBeans1;
    private List<List<FoodZuheTaocanXQ>> mListThreeBeans11;
    /**
     * 已选中的分组B餐品
     */
    private List<FoodZuheTaocanXQ> mListThreeBeans2;
    private List<List<FoodZuheTaocanXQ>> mListThreeBeans22;


    @Override
    public int initLayout() {
        return R.layout.module_activity_create_order_select_group;
    }

    @Override
    protected void init() {
        mInflater = LayoutInflater.from(CreateOrderSelectGroupActivity.this);
        if (getIntent().hasExtra("data")) {
            mDataBean = new Gson().fromJson(getIntent().getStringExtra("data"), FoodBean.DataBean.class);
        }else {
            finish();
        }

        mListThreeBeans1 = new ArrayList<>();
        mListThreeBeans2 = new ArrayList<>();
        mListThreeBeans11 = new ArrayList<>();
        mListThreeBeans22 = new ArrayList<>();

        mTvGroupFoodName.setText(mDataBean.getGroupPackageName());
        mTvGroupPrice.setText("￥" + mDataBean.getGroupPackagePrice());
        mRecyclerGroup1.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerGroup2.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerGroup1.setHasFixedSize(true);
        mRecyclerGroup1.setNestedScrollingEnabled(false);
        mRecyclerGroup2.setHasFixedSize(true);
        mRecyclerGroup2.setNestedScrollingEnabled(false);

        setGroupLists();
    }

    /**
     * @desc 设置组合内餐品显示
     * @anthor lpc
     * @date: 2018/7/20
     */
    private void setGroupLists() {
        // 分组A
        if (mDataBean.getListTwo().size() > 0) {
            mListThree1 = mDataBean .getListTwo().get(0).getListThree();
            mTvGroupName1.setText(mListThree1.get(0).get(0).getGroupName());
            final String groupCount = mListThree1.get(0).get(0).getGroupCount();
            mTvMandatoryCount1.setText(groupCount);
            final CreateOrderSelectGroupAdapter adapter1 = new CreateOrderSelectGroupAdapter(mListThree1);
            mRecyclerGroup1.setAdapter(adapter1);
            adapter1.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                        if(!adapter1.isSelected.get(position)){
                            // 修改map的值保存状态
                            if (mListThreeBeans11.size() < Integer.valueOf(groupCount)){
                                adapter1.isSelected.put(position, true);
                                adapter1.notifyItemChanged(position);
                                mListThreeBeans11.add(mListThree1.get(position));
                            }else {
                                ToastUtil.showToast("已超出可选数量");
                            }
                        }else {
                            // 修改map的值保存状态
                            adapter1.isSelected.put(position, false);
                            adapter1.notifyItemChanged(position);
                            mListThreeBeans11.remove(mListThree1.get(position));
                        }
                        mTvSelectedCount1.setText(mListThreeBeans11.size() + "");
                }
            });
        }
        // 分组B
        if (mDataBean.getListTwo().size() > 1) {
            mListThree2 = mDataBean.getListTwo().get(1).getListThree();
            mLlGroup2.setVisibility(View.VISIBLE);
            mTvGroupName2.setText(mListThree2.get(0).get(0).getGroupName());
            final String groupCount = mListThree2.get(0).get(0).getGroupCount();
            mTvMandatoryCount2.setText(groupCount);
            final CreateOrderSelectGroupAdapter adapter2 = new CreateOrderSelectGroupAdapter(mListThree2);
            mRecyclerGroup2.setAdapter(adapter2);

            adapter2.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        if(!adapter2.isSelected.get(position)){
                            // 修改map的值保存状态
                            if (mListThreeBeans22.size() < Integer.valueOf(groupCount)){
                                adapter2.isSelected.put(position, true);
                                adapter2.notifyItemChanged(position);
                                mListThreeBeans22.add(mListThree2.get(position));
                            }else {
                                ToastUtil.showToast("已超出可选数量");
                            }
                        }else {
                            // 修改map的值保存状态
                            adapter2.isSelected.put(position, false);
                            adapter2.notifyItemChanged(position);
                            mListThreeBeans22.remove(mListThree2.get(position));
                        }
                        mTvSelectedCount2.setText(mListThreeBeans22.size() + "");
                }
            });
        } else {
            mLlGroup2.setVisibility(View.GONE);
        }
    }

    @Override
    public void initData() {
    }

    @OnClick(R.id.tv_add_meal)
    public void onViewClicked() {
        int count1 = Integer.valueOf(mListThree1.get(0).get(0).getGroupCount());
        if (mDataBean.getListTwo().size() > 1){
            int count2 = Integer.valueOf(mListThree2.get(0).get(0).getGroupCount());
            if (mListThreeBeans11.size() != count1 && mListThreeBeans22.size() != count2) {
                ToastUtil.showToast("请正确选择组合餐品，再操作");
                return;
            }
        }else {
            if (mListThreeBeans11.size() != count1) {
                ToastUtil.showToast("请正确选择组合餐品，再操作");
                return;
            }
        }

        SelectMealEntity mMealListEntity = new SelectMealEntity();
        mMealListEntity.setFoodName(mDataBean.getGroupPackageName());
        mMealListEntity.setFoodProductsNumber(mDataBean.getGroupPackageNumber());
        mMealListEntity.setFoodPrice(mDataBean.getGroupPackagePrice());
        mMealListEntity.setFoodProductsCount(1);
        mMealListEntity.setFoodProductsType(mDataBean.getFoodType());
        mMealListEntity.setIncreasePrice("0");
        mMealListEntity.setMemberPreferences(mDataBean.getVipPrice());
        mMealListEntity.setId( ++CreateOrderPresenter.DAO_ID);

        mDataBean.getList();

        List<FoodZuheTaocanXQ> groupFoodList = new ArrayList<>();
        List<FoodBean.DataBean.ListBean> source = new ArrayList<>(); // 选中的餐品组合
        for (List<FoodZuheTaocanXQ> foodZuheTaocanXQS : mListThreeBeans11) {
            groupFoodList.addAll(foodZuheTaocanXQS);
            for (FoodZuheTaocanXQ listThreeBean : foodZuheTaocanXQS) {
                FoodBean.DataBean.ListBean bean1 = JavaBeanUtils.copyData(listThreeBean, FoodBean.DataBean.ListBean.class);
                bean1.setSingleProductNumber(listThreeBean.getFoodProductsNumber());
                bean1.setSingleQuantity(listThreeBean.getFoodProductsCount());
                source.add(bean1);
            }
        }
        if (mDataBean.getListTwo().size() > 1) {
            for (List<FoodZuheTaocanXQ> foodZuheTaocanXQS : mListThreeBeans22) {
                groupFoodList.addAll(foodZuheTaocanXQS);
                for (FoodZuheTaocanXQ listThreeBean : foodZuheTaocanXQS) {
                    FoodBean.DataBean.ListBean bean1 = JavaBeanUtils.copyData(listThreeBean, FoodBean.DataBean.ListBean.class);
                    bean1.setSingleProductNumber(listThreeBean.getFoodProductsNumber());
                    bean1.setSingleQuantity(listThreeBean.getFoodProductsCount());
                    source.add(bean1);
                }
            }
        }
        mMealListEntity.setGroupFood(groupFoodList);
        mDataBean.setList(source);
        Intent intent = new Intent();
        intent.putExtra("bean", new Gson().toJson(mDataBean));
        intent.putExtra("meal", new Gson().toJson(mMealListEntity));
        setResult(101, intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }


}
