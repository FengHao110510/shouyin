package com.hongsou.douguoshouyin.fragment.turnover;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.activity.turnover.OrderDetailActivity;
import com.hongsou.douguoshouyin.adapter.OrderAdapter;
import com.hongsou.douguoshouyin.base.BaseFragment;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.http.ResponseCallback;
import com.hongsou.douguoshouyin.javabean.OrderBean;
import com.hongsou.douguoshouyin.tool.Global;
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
import butterknife.Unbinder;

/**
 * 版权：鸿搜网络公司 版权所有
 * <p>
 * 作者：冯大鱼
 * <p>
 * 版本：1.0
 * <p>
 * 创建日期：2018/6/26 0026
 * <p>
 * 描述： 订单fragment
 * <p>
 * 修订历史：
 */


public class TurnoverOrderFragment extends BaseFragment {

    private static final String TAG = "TurnoverOrderFragment";

    @BindView(R.id.tv_turnover_order_order_icon)
    TextView tvTurnoverOrderOrderIcon;
    @BindView(R.id.et_turnover_order_order)
    EditText etTurnoverOrderOrder;
    Unbinder unbinder;
    @BindView(R.id.rv_turnover_order)
    RecyclerView rvTurnoverOrder;

    @BindView(R.id.sm_turnover_order)
    SmartRefreshLayout smTurnoverOrder;

    OrderAdapter orderAdapter;
    public int page;
    ArrayList<OrderBean.DataBean.ResultBean> listBeans = new ArrayList<>();
    public HashMap<String, String> mParam = new HashMap<>();

    @Override
    public int getLayoutId() {
        return R.layout.module_fragment_turnover_order;
    }


    @Override
    public void init() {
        setIconFont(new TextView[]{tvTurnoverOrderOrderIcon});
        initData();
        initPullRefresher();
    }

    /**
     * @param
     * @return
     * @author fenghao
     * @date 2018/7/12 0012 上午 9:20
     * @desc 初始化下拉刷新  上拉加载
     */
    private void initPullRefresher() {
        //下拉刷新
        smTurnoverOrder.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                getOrderList(new HashMap<String, String>(), page);
                smTurnoverOrder.finishRefresh();
            }
        });
        //上拉加载
        smTurnoverOrder.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getOrderList(mParam, page);
                smTurnoverOrder.finishLoadMore();//不传时间则立即停止刷新    传入false表示加载失败
            }
        });
    }

    /**
     * 初始化数据
     */
    private void initData() {
        page = 1;
        getOrderList(mParam, page);
    }

    /**
     * @param
     * @return
     * @author fenghao
     * @date 2018/7/11 0011 下午 12:23
     * @desc 获取数据
     */
    public void getOrderList(HashMap<String, String> param, final int page) {
        param.put("shopNumber", Global.getSpGlobalUtil().getShopNumber());
        param.put("pageString", page + "");
        HttpFactory.get().url(ApiConfig.GET_ORDER_LIST)
                //店铺编号
                .params(param)
                .build().execute(new ResponseCallback<OrderBean>(getActivity()) {
            @Override
            public void onResponse(OrderBean orderBean, int id) {
                if (orderBean.isSuccess()) {
                    showOrderList(orderBean.getData().getResult(), page);
                } else {
                    ToastUtil.showToast(orderBean.getMsg());
                }
            }
        });
    }

    /**
     * @param data
     * @return
     * @author fenghao
     * @date 2018/7/12 0012 下午 15:04
     * @desc //展示订单列表
     */
    private void showOrderList(List<OrderBean.DataBean.ResultBean> data, int page) {
        if (page > 1) {
            if (data.size() > 0) {
                listBeans.addAll(data);
                orderAdapter.notifyItemInserted(data.size());
            } else {
                ToastUtil.showToast("暂无更多数据");
            }

        } else {
            listBeans.clear();
            if (orderAdapter == null) {
                //创建适配器
                orderAdapter = new OrderAdapter(R.layout.module_item_turnover_order, listBeans);
                rvTurnoverOrder.setAdapter(orderAdapter);
                //创建布局管理器
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                rvTurnoverOrder.setLayoutManager(linearLayoutManager);
                //条目点击事件
                orderAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Intent itemIntent = new Intent(getActivity(), OrderDetailActivity.class);
                        itemIntent.putExtra("batch", listBeans.get(position).getBatch());
                        startActivity(itemIntent);
                    }
                });
            }
            //获取数据
            listBeans.addAll(data);
            orderAdapter.notifyDataSetChanged();
        }
    }

    //==============================================================================================================
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
