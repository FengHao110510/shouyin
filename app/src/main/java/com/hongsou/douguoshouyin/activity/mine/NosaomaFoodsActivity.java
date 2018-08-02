package com.hongsou.douguoshouyin.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.adapter.IsScanAdapter;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.http.ResponseCallback;
import com.hongsou.douguoshouyin.javabean.BaseBean;
import com.hongsou.douguoshouyin.javabean.NoScanBean;
import com.hongsou.douguoshouyin.tool.ToastUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 不参与扫码点餐的商品页面
 */
public class NosaomaFoodsActivity extends BaseActivity {


    @BindView(R.id.rv_mine_nosaoma_foods)
    RecyclerView rvMineNosaomaFoods;
    @BindView(R.id.tv_mine_nosaoma_addnosaoma)
    TextView tvMineNosaomaAddnosaoma;
    @BindView(R.id.sm_mine_nosaoma_foods)
    SmartRefreshLayout smMineNosaomaFoods;


    //数据源
    List<NoScanBean.DataBean.ResultBean> resultBeanList = new ArrayList<>();
    //适配器
    IsScanAdapter isScanAdapter;

    private int page;
    //总页数
    private int totalPage;


    @Override
    public int initLayout() {
        return R.layout.module_activity_mine_nosaoma_foods;
    }

    @Override
    protected void init() {
        initData();
        initBack();
        initTitle("不参与扫码的商品");
        initPullRefresher();

    }

    @Override
    public void initData() {
        //获取数据
        page = 1;
        totalPage = 1;
        getData();
    }

    /**
     * @author fenghao
     * @date 2018/8/1 0001 下午 16:00
     * @desc 下拉刷新上拉加载
     */
    private void initPullRefresher() {
        //下拉刷新
        smMineNosaomaFoods.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                getData();
                smMineNosaomaFoods.finishRefresh();
            }
        });
        //上拉加载
        smMineNosaomaFoods.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                if (page > totalPage) {
                    ToastUtil.showToast("已经是最后一页");
                    smMineNosaomaFoods.finishLoadMore(false);
                } else {
                    getData();
                    //不传时间则立即停止刷新    传入false表示加载失败
                    smMineNosaomaFoods.finishLoadMore();
                }

            }
        });
    }

    /**
     * @author fenghao
     * @date 2018/8/1 0001 下午 15:24
     * @desc 获取数据
     */
    private void getData() {
        if (page == 1) {
            if (resultBeanList != null) {
                resultBeanList.clear();
            }
        }
        HttpFactory.get().url(ApiConfig.GET_SCAN_FOOD)
                .addParams("shopNumber", getShopNumber())
                .addParams("pageString", page + "")
                .addParams("rowsString", "10")
                .build().execute(new ResponseCallback<NoScanBean>(this) {
            @Override
            public void onResponse(NoScanBean response, int id) {
                if (response.isSuccess()) {
                    totalPage = response.getData().getTotalPage();
                    resultBeanList.addAll(response.getData().getResult());
                    showList();
                } else {
                    ToastUtil.showToast(response.getMsg());
                }
            }
        });
    }

    /**
     * @author fenghao
     * @date 2018/8/1 0001 下午 15:42
     * @desc 展示列表
     */
    private void showList() {
        isScanAdapter = new IsScanAdapter(R.layout.module_item_choose_isscan, resultBeanList);
        rvMineNosaomaFoods.setAdapter(isScanAdapter);
        rvMineNosaomaFoods.setLayoutManager(new LinearLayoutManager(this));
        isScanAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.tv_item_choose_isscan_del) {
                    resultBeanList.get(position).setIsScan("0");

                    //走删除接口
                    delFood(resultBeanList.get(position), position);
                }
            }
        });
    }

    /**
     * @param resultBean 要删除的数据
     * @param position   要删除的数据的位置
     * @author fenghao
     * @date 2018/8/2 0002 下午 15:39
     * @desc 删除接口
     */
    //最后提交的
    List<NoScanBean.DataBean.ResultBean> resultBeanList2 = new ArrayList<>();
    private void delFood(NoScanBean.DataBean.ResultBean resultBean, final int position) {
        Gson gson = new Gson();
        if (resultBeanList2.size()>0){
            resultBeanList2.clear();
        }
        resultBeanList2.add(resultBean);
        HttpFactory.postString(ApiConfig.UPDATE_SINGLE_FOOD_IS_SCAN, gson.toJson(resultBeanList2)
                , new ResponseCallback<BaseBean>(this) {
                    @Override
                    public void onResponse(BaseBean response, int id) {
                        if (response.isSuccess()) {
                            ToastUtil.showToast("删除成功");

//                            resultBeanList.remove(position);
//                            isScanAdapter.notifyDataSetChanged();//这种方法存在bug
                            page=1;
                            getData();
                        } else {
                            ToastUtil.showToast(response.getMsg());
                        }
                    }
                });
    }

    @OnClick(R.id.tv_mine_nosaoma_addnosaoma)
    public void onViewClicked() {
        //跳转到添加不参与扫码商品的页面
        Intent addnoIntent = new Intent(this, ChooseIsScanActivity.class);

        startActivityForResult(addnoIntent, 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
            //添加了不参与扫码点餐的商品 刷新界面
            page = 1;
            getData();
        }

    }

    //===============================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
