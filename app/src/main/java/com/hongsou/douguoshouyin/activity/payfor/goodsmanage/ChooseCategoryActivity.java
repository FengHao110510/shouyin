package com.hongsou.douguoshouyin.activity.payfor.goodsmanage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.adapter.ChooseCategoryAdapter;
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
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/7/19 0019
 * 描述：选择分类页面
 * 修订历史：
 * ┌─┐       ┌─┐
 * ┌──┘ ┴───────┘ ┴──┐
 * │                 │
 * │       ───       │
 * │  ─┬┘       └┬─  │
 * │                 │
 * │       ─┴─       │
 * │                 │
 * └───┐         ┌───┘
 * │         │
 * │         │
 * │         │
 * │         └──────────────┐
 * │                        │
 * │                        ├─┐
 * │                        ┌─┘
 * │                        │
 * └─┐  ┐  ┌───────┬──┐  ┌──┘
 * │ ─┤ ─┤       │ ─┤ ─┤
 * └──┴──┘       └──┴──┘
 * 神兽保佑
 * 代码无BUG!
 */


public class ChooseCategoryActivity extends BaseActivity {

    //数据源
    List<FoodCategoryBean.DataBean> dataBeanList;

    //适配器
    ChooseCategoryAdapter chooseCategoryAdapter;
    @BindView(R.id.rv_payfor_choose_category_list)
    RecyclerView rvPayforChooseCategoryList;
    @BindView(R.id.tv_payfor_choose_category_yes)
    TextView tvPayforChooseCategoryYes;

    //类型编号
    private String singleProductType;
    //类型名称
    private String categoryName;
    private Intent intent;

    @Override
    public int initLayout() {
        return R.layout.module_activity_payfor_choose_category;
    }

    @Override
    protected void init() {
        initData();
        initBack();
        initTitle("选择分类");

    }

    @Override
    public void initData() {
        getCategoryList();
    }

    /**
     * @author fenghao
     * @date 2018/7/19 0019 上午 10:14
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
     * @param dataBeanList 数据源
     * @author fenghao
     * @date 2018/7/19 0019 上午 10:18
     * @desc 展示列表
     */
    private void showCategoryList(final List<FoodCategoryBean.DataBean> dataBeanList) {
        for (int k =0;k<dataBeanList.size();k++){
            //为了展示单品
            if (!dataBeanList.get(k).getCategoryType().equals("0")){
                dataBeanList.remove(k);
                k--;
            }
        }
        chooseCategoryAdapter = new ChooseCategoryAdapter(R.layout.module_item_choose_category, dataBeanList);
        rvPayforChooseCategoryList.setLayoutManager(new LinearLayoutManager(this));
        rvPayforChooseCategoryList.setAdapter(chooseCategoryAdapter);

        chooseCategoryAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //获取其他子控件
                TextView tvItemChooseCategoryYes = null;
                for (int i = 0; i < dataBeanList.size(); i++) {
                    tvItemChooseCategoryYes = (TextView) adapter.getViewByPosition(rvPayforChooseCategoryList, i, R.id.tv_item_choose_category_yes);
                    setIconFont(new TextView[]{tvItemChooseCategoryYes});
                    if (i == position) {
                        tvItemChooseCategoryYes.setVisibility(View.VISIBLE);
                        singleProductType = dataBeanList.get(i).getCategoryNumber();
                        categoryName = dataBeanList.get(i).getCategoryName();
                    } else {
                        tvItemChooseCategoryYes.setVisibility(View.GONE);
                    }
                    Log.e(TAG, "onItemClick: " + i);
                }

            }
        });
    }
    @OnClick(R.id.tv_payfor_choose_category_yes)
    public void onViewClicked() {
        //确定
        intent = new Intent();
        intent.putExtra("categoryName",categoryName);
        intent.putExtra("singleProductType",singleProductType);
        Log.e(TAG, "onActivityResult: "+categoryName.toString() );
        setResult(1, intent);
        finishActivity();
    }
    //==============================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
