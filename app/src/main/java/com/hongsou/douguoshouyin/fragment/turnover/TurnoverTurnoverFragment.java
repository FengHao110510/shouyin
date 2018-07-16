package com.hongsou.douguoshouyin.fragment.turnover;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.adapter.TurnoverMultipleItem;
import com.hongsou.douguoshouyin.base.BaseFragment;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.http.ResponseCallback;
import com.hongsou.douguoshouyin.javabean.TurnoverBean;
import com.hongsou.douguoshouyin.tool.ToastUtil;

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
 * 描述：流水fragment
 * <p>
 * 修订历史：
 */


public class TurnoverTurnoverFragment extends BaseFragment {
    @BindView(R.id.rv_turnover_turnover_list)
    RecyclerView rvTurnoverTurnoverList;
    Unbinder unbinder;


    private int page;
    List<TurnoverBean.DataBean.ResultBean> result;
    List<TurnoverMultipleItem> turnoverMultipleItemList;

    @Override
    public int getLayoutId() {
        return R.layout.module_fragment_turnover_turnover;
    }

    @Override
    public void init() {
        initData();
        page = 1;
    }

    /**
     * @param
     * @return
     * @author fenghao
     * @date 2018/7/16 0016 上午 9:30
     * @desc 初始化列表数据
     */
    private void initData() {
//        showTurnoverList();
    }

    /**
     * @param
     * @return
     * @author fenghao
     * @date 2018/7/16 0016 上午 9:30
     * @desc 展示列表数据
     */
    private void showTurnoverList() {
        HttpFactory.get().url(ApiConfig.GET_PAY_ORDER_LIST)
                .addParams("shopNumber", "1000180614300325544")
                .addParams("tradingTime", "")
                .addParams("endTime", "")
                .addParams("paymentType", "")
                .addParams("orderState", "")
                .addParams("pageString", page + "")
                .addParams("rowsString", "10")
                .build().execute(new ResponseCallback<TurnoverBean>(getActivity()) {
            @Override
            public void onResponse(TurnoverBean response, int id) {
                if (response.getCode() == 1000) {
                    result = response.getData().getResult();
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
    private void setTurnoverAdapter(List<TurnoverBean.DataBean.ResultBean> result) {

        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).getBatch().equals("1")) {
                turnoverMultipleItemList.add(new TurnoverMultipleItem(TurnoverMultipleItem.FIRST_TYPE, result.get(i)));
            }else {
                turnoverMultipleItemList.add(new TurnoverMultipleItem(TurnoverMultipleItem.SECOND_TYPE, result.get(i)));

            }


        }
    }


    //====================================================================================================
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
