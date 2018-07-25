package com.hongsou.douguoshouyin.fragment.turnover;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.adapter.TurnoverAdapter;
import com.hongsou.douguoshouyin.adapter.TurnoverMultipleItem;
import com.hongsou.douguoshouyin.base.BaseFragment;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.http.ResponseCallback;
import com.hongsou.douguoshouyin.javabean.RootBean;
import com.hongsou.douguoshouyin.javabean.TurnoverBean;
import com.hongsou.douguoshouyin.tool.ToastUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
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
 * 描述：流水fragment
 * <p>
 * 修订历史：
 */
public class TurnoverTurnoverFragment extends BaseFragment {

    @BindView(R.id.rv_turnover_turnover_list)
    RecyclerView rvTurnoverTurnoverList;
    Unbinder unbinder;
    @BindView(R.id.srl_turnover)
    SmartRefreshLayout mSrlTurnover;

    private int page = 1;
    private List<TurnoverBean> result;
    private List<TurnoverMultipleItem> turnoverMultipleItemList;
    private TurnoverAdapter mTurnoverAdapter;
    private HashMap<String, String> mParam = new HashMap<>();
    private String mTradingTime = "";

    @Override
    public int getLayoutId() {
        return R.layout.module_fragment_turnover_turnover;
    }

    @Override
    public void init() {
        result = new ArrayList<>();
        rvTurnoverTurnoverList.setLayoutManager(new LinearLayoutManager(getActivity()));
        initPullRefresher();
        initData();
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
        mSrlTurnover.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                mTradingTime = "";
                showTurnoverList(new HashMap<String, String>());
                mSrlTurnover.finishRefresh();

            }
        });
        //上拉加载
        mSrlTurnover.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                showTurnoverList(mParam);
                mSrlTurnover.finishLoadMore();//不传时间则立即停止刷新    传入false表示加载失败

            }
        });

    }

    /**
     * @param
     * @return
     * @author fenghao
     * @date 2018/7/16 0016 上午 9:30
     * @desc 初始化列表数据
     */
    private void initData() {
        showTurnoverList(mParam);
    }

    /**
     * @param
     * @return
     * @author fenghao
     * @date 2018/7/16 0016 上午 9:30
     * @desc 展示列表数据
     */
    public void showTurnoverList(HashMap<String, String> param) {
        param.put("shopNumber", getShopNumber());
        param.put("pageString", page + "");
        // todo 参数需要和订单列表的统一一下
        HttpFactory.get().url(ApiConfig.GET_PAY_ORDER_LIST)
                .params(param)
                .build().execute(new ResponseCallback<RootBean<List<TurnoverBean>>>(getActivity()) {
            @Override
            public void onResponse(RootBean<List<TurnoverBean>> response, int id) {
                if (response.isSuccess()) {
                    if (page == 1){
                        result.clear();
                    }
                    result.addAll(response.getData());
                    mTradingTime = result.get(result.size() - 1).getTradingTime();
                    setTurnoverAdapter(result);
                } else {
                    ToastUtil.showToast(response.getMsg());
                }
            }
        });
    }

    /**
     * @param result
     * @return
     * @author fenghao
     * @date 2018/7/16 0016 上午 11:26
     * @desc 设置适配器
     */
    private void setTurnoverAdapter(List<TurnoverBean> result) {
        if (turnoverMultipleItemList == null){
            turnoverMultipleItemList = new ArrayList<>();
            mTurnoverAdapter = new TurnoverAdapter(turnoverMultipleItemList);
            rvTurnoverTurnoverList.setAdapter(mTurnoverAdapter);
        }
        turnoverMultipleItemList.clear();
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).getItem() == 1) {
                turnoverMultipleItemList.add(new TurnoverMultipleItem(TurnoverMultipleItem.TYPE_DATE, result.get(i)));
            } else {
                turnoverMultipleItemList.add(new TurnoverMultipleItem(TurnoverMultipleItem.TYPE_INFO, result.get(i)));
            }
        }
        mTurnoverAdapter.notifyDataSetChanged();
    }
}
