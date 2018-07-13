package com.hongsou.douguoshouyin.fragment.turnover;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.activity.turnover.OrderDetailActivity;
import com.hongsou.douguoshouyin.adapter.OrderAdapter;
import com.hongsou.douguoshouyin.base.BaseFragment;
import com.hongsou.douguoshouyin.http.Apiconfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.javabean.OrderBean;
import com.hongsou.douguoshouyin.javabean.RootBean;
import com.hongsou.douguoshouyin.tool.Global;
import com.hongsou.douguoshouyin.tool.ToastUtil;
import com.scwang.smartrefresh.header.BezierCircleHeader;
import com.scwang.smartrefresh.header.DeliveryHeader;
import com.scwang.smartrefresh.header.DropBoxHeader;
import com.scwang.smartrefresh.header.FunGameHitBlockHeader;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.footer.FalsifyFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;

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
    private int page;
    ArrayList<OrderBean.DataBeanX.DataBean> listBeans = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.module_fragment_turnover_order;
    }


    @Override
    public void init() {
        initView();
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
                getOrderList();
                smTurnoverOrder.finishRefresh();

            }
        });
        //上拉加载
        smTurnoverOrder.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getOrderList();
                smTurnoverOrder.finishLoadMore();//不传时间则立即停止刷新    传入false表示加载失败

            }
        });

    }


    /**
     * 初始化视图
     */
    private void initView() {
        setIconFont(new TextView[]{tvTurnoverOrderOrderIcon});
        page = 1;
    }

    /**
     * 初始化数据
     */
    private void initData() {
        getOrderList();
    }

    /**
     * @param
     * @return
     * @author fenghao
     * @date 2018/7/11 0011 下午 12:23
     * @desc 获取数据
     */
    private void getOrderList() {
        showLoadingDialog();
        Log.e("2333", "getOrderList: " + Global.getSpGlobalUtil().getShopNumber());
        HttpFactory.get().url(Apiconfig.getOrderList)
                .addParams("shopNumber", Global.getSpGlobalUtil().getShopNumber())//店铺编号
                .addParams("orderSourcePayment", "")//订单来源
                .addParams("orderType", "")//订单类型
                .addParams("paymentType", "")//支付方式
                .addParams("tradingTime", "")//开始时间
                .addParams("endTime", "")//结束时间
                .addParams("pageString", page + "")//当前页
                .addParams("rowsString", "10")//行数
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoadingDialog();

            }

            @Override
            public void onResponse(String response, int id) {
                dismissLoadingDialog();
                OrderBean orderBean = new Gson().fromJson(response, OrderBean.class);

                if (orderBean.getCode() == 1000) {
                    showOrderList(orderBean.getData().getData());
                }else {
                    ToastUtil.showToast(orderBean.getMsg());
                }

            }
        });
    }


    /**
     *  @author  fenghao
     *  @date    2018/7/12 0012 下午 15:04
     *  @param   data
     *  @return
     *  @desc   //展示订单列表
     */
    private void showOrderList(List<OrderBean.DataBeanX.DataBean> data) {
        if (page > 1) {
            listBeans.addAll(data);
            orderAdapter.notifyItemInserted(data.size());

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
                        itemIntent.putExtra("batch",listBeans.get(position).getBatch());
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
