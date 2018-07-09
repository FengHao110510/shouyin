package douguoshouyin.douguoshouyin.fragment.turnover;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import douguoshouyin.douguoshouyin.R;
import douguoshouyin.douguoshouyin.base.BaseFragment;
import douguoshouyin.douguoshouyin.tool.Global;

/**
 * 版权：鸿搜网络公司 版权所有
 * <p>
 * 作者：冯大鱼
 * <p>
 * 版本：1.0
 * <p>
 * 创建日期：2018/6/26 0026
 * <p>
 * 描述：
 * <p>
 * 修订历史：
 */


public class TurnoverOrderFragment extends BaseFragment {
    @BindView(R.id.tv_turnover_order_order_icon)
    TextView tvTurnoverOrderOrderIcon;
    @BindView(R.id.et_turnover_order_order)
    EditText etTurnoverOrderOrder;
    Unbinder unbinder;

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
