package douguoshouyin.douguoshouyin.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import douguoshouyin.douguoshouyin.R;
import douguoshouyin.douguoshouyin.activity.turnover.OrderShaixuanActivity;
import douguoshouyin.douguoshouyin.activity.turnover.TurnoverShaixuanActivity;
import douguoshouyin.douguoshouyin.base.BaseFragment;
import douguoshouyin.douguoshouyin.fragment.turnover.TurnoverOrderFragment;
import douguoshouyin.douguoshouyin.fragment.turnover.TurnoverTurnoverFragment;
import douguoshouyin.douguoshouyin.tool.Global;
import douguoshouyin.douguoshouyin.tool.ToastUtil;

/**
 * 文件描述：new！！com.example.administrator.myapplication.app.fragment
 * 作者：fh
 * 创建时间：2018/6/13
 * 更改时间：2018/6/13
 * 版本号：1
 * 用途：流水fragment
 */


public class TurnoverFragment extends BaseFragment {


    @BindView(R.id.tab_titlebar_turnover_tab)
    TabLayout tabTitlebarTurnoverTab;
    @BindView(R.id.tv_titlebar_turnover_shaixuan_icon)
    TextView tvTitlebarTurnoverShaixuanIcon;
    @BindView(R.id.ll_titlebar_turnover_shaixuan)
    LinearLayout llTitlebarTurnoverShaixuan;

    Unbinder unbinder;
    @BindView(R.id.vp_titlebar_turnover_viewpager)
    ViewPager vpTitlebarTurnoverViewpager;

    CustomViewPagerAdapter customViewPagerAdapter;
    private List<Fragment> list;

    @Override
    public int getLayoutId() {
        return R.layout.module_fragment_turnover;
    }

    @Override
    public void init() {
        initView();
        initData();
    }


    /**
     * /初始化界面
     */
    private void initView() {
        setIconFont(new TextView[]{tvTitlebarTurnoverShaixuanIcon});
        //限定预加载个数
        vpTitlebarTurnoverViewpager.setOffscreenPageLimit(2);
        customViewPagerAdapter = new CustomViewPagerAdapter(this.getFragmentManager());
        customViewPagerAdapter.addFragment(new TurnoverOrderFragment(), "  订单  ");
        customViewPagerAdapter.addFragment(new TurnoverTurnoverFragment(), "  流水  ");
        vpTitlebarTurnoverViewpager.setAdapter(customViewPagerAdapter);
        //制定初始化页面
        vpTitlebarTurnoverViewpager.setCurrentItem(0);
        //tab与vp联动
        tabTitlebarTurnoverTab.setupWithViewPager(vpTitlebarTurnoverViewpager);


    }

    /**
     * 初始化数据
     */
    private void initData() {

    }

    //ViewPager 的 adapter
    static class CustomViewPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragmentList = new ArrayList<>();
        private List<String> titles = new ArrayList<>();


        public CustomViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
            titles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }

    @OnClick(R.id.ll_titlebar_turnover_shaixuan)
    public void onViewClicked() {
        //筛选 判断是流水还是订单

        if (tabTitlebarTurnoverTab.getSelectedTabPosition() == 0) {
            Intent orderSXIntent = new Intent(getActivity(), OrderShaixuanActivity.class);
            startActivity(orderSXIntent);
        } else {
            Intent turnoverSXIntent = new Intent(getActivity(), TurnoverShaixuanActivity.class);
            startActivity(turnoverSXIntent);

        }

    }


    //==========================================================================================================================

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
