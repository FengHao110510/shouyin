package com.hongsou.douguoshouyin.fragment.turnover;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.adapter.OrderAdapter;
import com.hongsou.douguoshouyin.base.BaseFragment;
import com.hongsou.douguoshouyin.http.Apiconfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.javabean.OrderBean;
import com.hongsou.douguoshouyin.javabean.RootBean;
import com.hongsou.douguoshouyin.tool.ToastUtil;
import com.zhy.http.okhttp.callback.StringCallback;

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

    OrderAdapter orderAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.module_fragment_turnover_order;
    }

    @Override
    public void init() {
        initView();
        initData();
    }


    /**
     * 初始化视图
     */
    private void initView() {
        setIconFont(new TextView[]{tvTurnoverOrderOrderIcon});
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
        HttpFactory.post().url(Apiconfig.sendMsg).addParams("", "").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoadingDialog();
            }

            @Override
            public void onResponse(String response, int id) {
                dismissLoadingDialog();
                RootBean<OrderBean> orderBean = new Gson().fromJson(response, new TypeToken<RootBean<OrderBean>>() {
                }.getType());
                if (orderBean.getCode() == 1000) {
                    //获取数据
                }
                ToastUtil.showToast(orderBean.getMsg());
            }
        });
    }


    //==============================================================================================================
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
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
