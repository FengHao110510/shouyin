package com.hongsou.douguoshouyin.fragment;


import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.activity.turnover.OrderConditionActivity;
import com.hongsou.douguoshouyin.base.BaseFragment;
import com.hongsou.douguoshouyin.fragment.turnover.TurnoverOrderFragment;
import com.hongsou.douguoshouyin.fragment.turnover.TurnoverTurnoverFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @copyright 鸿搜网络公司 版权所有
 * @author fh
 * @date 2018/6/13
 * @desc 流水fragment
 */

public class TurnoverFragment extends BaseFragment {

    private static final String TAG = "TurnoverFragment";
    public static final int REQUEST_CODE = 11;

    @BindView(R.id.tab_titlebar_turnover_tab)
    TabLayout tabTitlebarTurnoverTab;
    @BindView(R.id.tv_titlebar_turnover_shaixuan_icon)
    TextView tvTitlebarTurnoverShaixuanIcon;
    @BindView(R.id.ll_titlebar_turnover_shaixuan)
    LinearLayout llTitlebarTurnoverShaixuan;

    Unbinder unbinder;
    @BindView(R.id.vp_titlebar_turnover_viewpager)
    ViewPager vpTitlebarTurnoverViewpager;

    private CustomViewPagerAdapter customViewPagerAdapter;
    private TurnoverOrderFragment mTurnoverOrderFragment;
    private TurnoverTurnoverFragment mTurnoverTurnoverFragment;

    private HashMap<String, String> mParam;

    @Override
    public int getLayoutId() {
        return R.layout.module_fragment_turnover;
    }

    @Override
    public void init() {
        initView();
    }

    /**
     * /初始化界面
     */
    private void initView() {
        setIconFont(new TextView[]{tvTitlebarTurnoverShaixuanIcon});
        mTurnoverOrderFragment = new TurnoverOrderFragment();
        mTurnoverTurnoverFragment = new TurnoverTurnoverFragment();
        //限定预加载个数
        vpTitlebarTurnoverViewpager.setOffscreenPageLimit(2);
        customViewPagerAdapter = new CustomViewPagerAdapter(this.getChildFragmentManager());
        customViewPagerAdapter.addFragment(mTurnoverOrderFragment, "  订单  ");
        customViewPagerAdapter.addFragment(mTurnoverTurnoverFragment, "  流水  ");
        vpTitlebarTurnoverViewpager.setAdapter(customViewPagerAdapter);
        //制定初始化页面
        vpTitlebarTurnoverViewpager.setCurrentItem(0);
        //tab与vp联动
        tabTitlebarTurnoverTab.setupWithViewPager(vpTitlebarTurnoverViewpager);

    }


    @OnClick(R.id.ll_titlebar_turnover_shaixuan)
    public void onViewClicked() {
        //筛选 判断是流水还是订单

        Intent intent = new Intent(getActivity(), OrderConditionActivity.class);
        if (tabTitlebarTurnoverTab.getSelectedTabPosition() == 0) {
            intent.putExtra("type", 0);
        }else {
            intent.putExtra("type", 1);
        }
        startActivityForResult(intent, REQUEST_CODE);
    }

    private void initData() {
    }

    /**
     * ViewPager 的 adapter
     */
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TurnoverFragment.REQUEST_CODE && data != null){
            mParam = (HashMap<String, String>) data.getSerializableExtra("data");
            if (resultCode == 0){
                mTurnoverOrderFragment.mParam = mParam;
                mTurnoverOrderFragment.getOrderList(mParam, 1);
            }else {
                mTurnoverTurnoverFragment.mParam = mParam;
                mTurnoverTurnoverFragment.beginTime = "";
                mTurnoverTurnoverFragment.showTurnoverList(mParam, 1);
            }
        }
    }

}
