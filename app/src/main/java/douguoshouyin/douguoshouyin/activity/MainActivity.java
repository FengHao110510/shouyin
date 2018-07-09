package douguoshouyin.douguoshouyin.activity;


import android.os.Bundle;
import android.os.Handler;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import douguoshouyin.douguoshouyin.R;
import douguoshouyin.douguoshouyin.base.BaseActivity;
import douguoshouyin.douguoshouyin.base.BaseApplication;
import douguoshouyin.douguoshouyin.base.BaseFragment;
import douguoshouyin.douguoshouyin.fragment.MineFragment;
import douguoshouyin.douguoshouyin.fragment.MoreFragment;
import douguoshouyin.douguoshouyin.fragment.PayForFragment;
import douguoshouyin.douguoshouyin.fragment.TurnoverFragment;
import douguoshouyin.douguoshouyin.tool.ToastUtil;

/**
 * 主页面
 */
public class MainActivity extends BaseActivity {


    @BindView(R.id.tv_titlebar_finish_back)
    TextView tvTitlebarFinishBack;
    @BindView(R.id.tv_titlebar_title)
    TextView tvTitlebarTitle;
    @BindView(R.id.fl_titlebar)
    FrameLayout flTitlebar;
    @BindView(R.id.fl_main_fragment)
    FrameLayout flMainFragment;
    @BindView(R.id.tv_tag_receivable)
    TextView tvTagReceivable;
    @BindView(R.id.tv_tag_turnover)
    TextView tvTagTurnover;
    @BindView(R.id.tv_tag_more)
    TextView tvTagMore;
    @BindView(R.id.tv_tag_mine)
    TextView tvTagMine;
    @BindView(R.id.ll_tag)
    LinearLayout llTag;
    @BindView(R.id.tv_tag_receivable_icon)
    TextView tvTagReceivableIcon;
    @BindView(R.id.tv_tag_turnover_icon)
    TextView tvTagTurnoverIcon;
    @BindView(R.id.tv_tag_more_icon)
    TextView tvTagMoreIcon;
    @BindView(R.id.tv_tag_mine_icon)
    TextView tvTagMineIcon;
    @BindView(R.id.ll_tag_receivable)
    LinearLayout llTagReceivable;
    @BindView(R.id.ll_tag_turnover)
    LinearLayout llTagTurnover;
    @BindView(R.id.ll_tag_more)
    LinearLayout llTagMore;
    @BindView(R.id.ll_tag_mine)
    LinearLayout llTagMine;
    private ArrayList<BaseFragment> fragmentList;
    private BaseFragment tempFragment;//当前fragment
    //选中fragment对应的位置
    private int position = 0;
    private long exitTime = 0;//计算点击时间
    private static Handler mHandler = null;


    /*
     * 初始化fragment
     */
    private void initFragment() {

        fragmentList = new ArrayList<>();

        fragmentList.add(new PayForFragment());//收款
        fragmentList.add(new TurnoverFragment());//流水
        fragmentList.add(new MoreFragment());//更多
        fragmentList.add(new MineFragment());//我的


    }


    @Override
    protected void init() {
        initView();
        initData();
        initBack();
    }


    @Override
    public int intiLayout() {
        return R.layout.module_activity_main;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        initFragment();

        setIconFont(new TextView[]{tvTagMineIcon, tvTagMoreIcon, tvTagTurnoverIcon, tvTagReceivableIcon});

        llTagReceivable.performClick();
        tvTitlebarFinishBack.setVisibility(View.INVISIBLE);
    }


    /**
     * @param fromFragment 上一个fragment
     * @param nextFragment 当前要显示的fragment
     */
    private void switchFragment(Fragment fromFragment, BaseFragment nextFragment) {
        if (tempFragment != nextFragment) {
            tempFragment = nextFragment;

            if (nextFragment != null) {

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加
                if (!nextFragment.isAdded()) {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.add(R.id.fl_main_fragment, nextFragment).commit();
                } else {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }


            }
        }

    }

    //获取fragment
    private BaseFragment getFragment(int position) {
        if (fragmentList != null && fragmentList.size() > 0) {
            BaseFragment baseFragment = fragmentList.get(position);
            return baseFragment;
        }
        return null;
    }


    /**
     * 点击两次退出app
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            ToastUtil.showToast(
                    "再按一次退出程序");
            exitTime = System.currentTimeMillis();
        } else {
            BaseApplication.getApplication().removeAll();
            System.exit(0);
        }
    }

    @OnClick({R.id.ll_tag_receivable, R.id.ll_tag_turnover, R.id.ll_tag_more, R.id.ll_tag_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_tag_receivable://收款
                position = 0;
                flTitlebar.setVisibility(View.GONE);
                //文字变色
                tvTagReceivable.setTextColor(getResources().getColor(R.color.color_base_yellow));
                tvTagTurnover.setTextColor(getResources().getColor(R.color.black));
                tvTagMore.setTextColor(getResources().getColor(R.color.black));
                tvTagMine.setTextColor(getResources().getColor(R.color.black));
                //图片变色
                tvTagReceivableIcon.setTextColor(getResources().getColor(R.color.color_base_yellow));
                tvTagTurnoverIcon.setTextColor(getResources().getColor(R.color.black));
                tvTagMoreIcon.setTextColor(getResources().getColor(R.color.black));
                tvTagMineIcon.setTextColor(getResources().getColor(R.color.black));
                break;
            case R.id.ll_tag_turnover://流水
                position = 1;
                flTitlebar.setVisibility(View.GONE);
                tvTitlebarTitle.setText("流水");

                tvTagReceivable.setTextColor(getResources().getColor(R.color.black));
                tvTagTurnover.setTextColor(getResources().getColor(R.color.color_base_yellow));
                tvTagMore.setTextColor(getResources().getColor(R.color.black));
                tvTagMine.setTextColor(getResources().getColor(R.color.black));
                //图片变色
                tvTagReceivableIcon.setTextColor(getResources().getColor(R.color.black));
                tvTagTurnoverIcon.setTextColor(getResources().getColor(R.color.color_base_yellow));
                tvTagMoreIcon.setTextColor(getResources().getColor(R.color.black));
                tvTagMineIcon.setTextColor(getResources().getColor(R.color.black));
                break;
            case R.id.ll_tag_more://更多
                position = 2;
                flTitlebar.setVisibility(View.VISIBLE);
                tvTitlebarTitle.setText("更多");
                tvTagReceivable.setTextColor(getResources().getColor(R.color.black));
                tvTagTurnover.setTextColor(getResources().getColor(R.color.black));
                tvTagMore.setTextColor(getResources().getColor(R.color.color_base_yellow));
                tvTagMine.setTextColor(getResources().getColor(R.color.black));
                //图片变色
                tvTagReceivableIcon.setTextColor(getResources().getColor(R.color.black));
                tvTagTurnoverIcon.setTextColor(getResources().getColor(R.color.black));
                tvTagMoreIcon.setTextColor(getResources().getColor(R.color.color_base_yellow));
                tvTagMineIcon.setTextColor(getResources().getColor(R.color.black));
                break;
            case R.id.ll_tag_mine://我的
                position = 3;
                flTitlebar.setVisibility(View.GONE);
                tvTagReceivable.setTextColor(getResources().getColor(R.color.black));
                tvTagTurnover.setTextColor(getResources().getColor(R.color.black));
                tvTagMore.setTextColor(getResources().getColor(R.color.black));
                tvTagMine.setTextColor(getResources().getColor(R.color.color_base_yellow));
                //图片变色
                tvTagReceivableIcon.setTextColor(getResources().getColor(R.color.black));
                tvTagTurnoverIcon.setTextColor(getResources().getColor(R.color.black));
                tvTagMoreIcon.setTextColor(getResources().getColor(R.color.black));
                tvTagMineIcon.setTextColor(getResources().getColor(R.color.color_base_yellow));
                break;

        }
        BaseFragment baseFragment = getFragment(position);

        switchFragment(tempFragment, baseFragment);
    }

    //====================================================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
