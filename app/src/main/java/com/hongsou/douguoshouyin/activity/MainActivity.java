package com.hongsou.douguoshouyin.activity;


import android.Manifest;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.base.BaseApplication;
import com.hongsou.douguoshouyin.base.BaseFragment;
import com.hongsou.douguoshouyin.fragment.MineFragment;
import com.hongsou.douguoshouyin.fragment.MoreFragment;
import com.hongsou.douguoshouyin.fragment.PayForFragment;
import com.hongsou.douguoshouyin.fragment.TurnoverFragment;
import com.hongsou.douguoshouyin.javabean.PrinterBean;
import com.hongsou.douguoshouyin.tool.Global;
import com.hongsou.douguoshouyin.tool.NotificationsUtils;
import com.hongsou.douguoshouyin.tool.ToastUtil;
import com.hongsou.douguoshouyin.tool.bluetooth.BlueToothManager;
import com.hongsou.greendao.gen.PrinterBeanDao;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import io.reactivex.functions.Consumer;

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
    private AsyncTask asyncTask;

    private PrinterBeanDao mPrinterBeanDao;

    /**
     * 初始化fragment
     */
    private void initFragment() {
        fragmentList = new ArrayList<>();
        //收款
        fragmentList.add(new PayForFragment());
        //流水
        fragmentList.add(new TurnoverFragment());
        //更多
        fragmentList.add(new MoreFragment());
        //我的
        fragmentList.add(new MineFragment());
    }


    @Override
    protected void init() {

        initView();
        initData();
        initBack();
        getSkuNum();
        initPermission();
        autoConnectBlueTooth();

        NotificationsUtils.setNotificationPermission(this);
    }


    /**
     * 如果蓝牙是开启的自动连接
     */
    private void autoConnectBlueTooth() {
        mPrinterBeanDao = BaseApplication.getApplication().getDaoSession().getPrinterBeanDao();
        List<PrinterBean> printerBeans = mPrinterBeanDao.loadAll();
        for (PrinterBean printerBean : printerBeans) {
            if (printerBean.getConnectStatus()) {
                asyncTask = BlueToothManager.getInstance().autoConnectBlue(printerBean.getPrintAddress(), printerBean.getPrintName());
            }
        }
    }

    /**
     * @author fenghao
     * @date 2018/7/24 0024 下午 21:00
     * @desc 初始化权限
     */
    private void initPermission() {
        RxPermissions rxPermission = new RxPermissions(this);
        rxPermission
                .requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                )
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {

                        } else {
                            // 用户拒绝了该权限，并且选中『不再询问』
                            ToastUtil.showToast("请打开相关权限，否则将影响使用");
                        }
                    }
                });
    }

    //获取设备唯一标识
    private void getSkuNum() {
        String code = Global.getSpGlobalUtil().getCode();
        setAlias(code);
    }

    //设置别名
    private void setAlias(String name) {
        // 调用 Handler 来异步设置别名
        JPushInterface.setAlias(this, 1, name);
        Log.i("JPushInterface", "----->" + name);
    }


    @Override
    public int initLayout() {
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
            default:
                break;

        }
        BaseFragment baseFragment = getFragment(position);

        switchFragment(tempFragment, baseFragment);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
