package com.hongsou.douguoshouyin.activity;


import android.Manifest;
import android.bluetooth.BluetoothSocket;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
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
import com.hongsou.douguoshouyin.base.Constant;
import com.hongsou.douguoshouyin.fragment.MineFragment;
import com.hongsou.douguoshouyin.fragment.MoreFragment;
import com.hongsou.douguoshouyin.fragment.PayForFragment;
import com.hongsou.douguoshouyin.fragment.TurnoverFragment;
import com.hongsou.douguoshouyin.http.ftp.FtpNetCallBack;
import com.hongsou.douguoshouyin.http.ftp.FtpUploadTask;
import com.hongsou.douguoshouyin.tool.BlueToothManeger;
import com.hongsou.douguoshouyin.tool.Global;
import com.hongsou.douguoshouyin.tool.LogUtil;
import com.hongsou.douguoshouyin.tool.ToastUtil;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.apache.commons.net.ftp.FTPFile;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import io.reactivex.functions.Consumer;

/**
 * 主页面
 */
public class MainActivity extends BaseActivity implements FtpNetCallBack {


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
    private AsyncTask asyncTask;


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
        getSkuNum();
        initPermission();
        autoConnetBlueTooth();
        sendCrashReportsToServer();
    }


    /**
     * 如果蓝牙是开启的自动连接
     */
    private void autoConnetBlueTooth() {
        BluetoothSocket socket = BaseApplication.getInstance().socket;
        if (socket == null) {
            asyncTask = BlueToothManeger.instance().autoNonnectBlue(this);
        }
    }

    /**
     *  @author  fenghao
     *  @date    2018/7/24 0024 下午 21:00
     *  @desc   初始化权限
     */
    private void initPermission() {
        RxPermissions rxPermission = new RxPermissions(this);
        rxPermission
                .requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                        ,Manifest.permission.CAMERA
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

    //====================================================================================================
    /**
     * 错误报告文件保存路径
     */
    private String mDirPath;
    /**
     * 错误报告文件的扩展名
     */
    private static final String CRASH_REPORTER_EXTENSION = ".cr";


    private void initFile() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            mDirPath = Environment.getExternalStorageDirectory() + "/douguo/sy/log/";
            File dir = new File(mDirPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
        }
    }

    /**
     * 把错误报告发送给服务器,包含新产生的和以前没发送的.
     */
    private void sendCrashReportsToServer() {
        initFile();
        String[] crFiles = getCrashReportFiles();
        if (crFiles != null && crFiles.length > 0) {
            TreeSet<String> sortedFiles = new TreeSet<String>();
            sortedFiles.addAll(Arrays.asList(crFiles));
            for (String fileName : sortedFiles) {
                File cr = new File(mDirPath, fileName);
                // 发送错误报告到服务器
                upload(cr.getPath());
                cr.delete();// 删除已发送的报告
            }
        }
    }

    /**
     * @param localFilePath 本地文件路径
     * @desc 上传到FTP服务器
     * @anthor lpc
     * @date: 2018/7/30
     */
    private void upload(String localFilePath) {
        if (!TextUtils.isEmpty(localFilePath)) {
            new FtpUploadTask(BaseApplication.ftp, this, localFilePath, Constant.FTP_FILE_PATH).execute();
        }
    }

    @Override
    public void getFtpFileList(List<FTPFile> ftpFileList) {
    }

    @Override
    public void downLoadFinish(boolean result) {
    }

    @Override
    public void uploadFinish(boolean result) {
        if (result) {
            LogUtil.e(TAG, "uploadFinish: 提交成功");
        } else {
            LogUtil.e(TAG, "uploadFinish: 提交失败");
        }
    }

    /**
     * 获取错误报告文件名
     * @return
     */
    private String[] getCrashReportFiles() {
        if (TextUtils.isEmpty(mDirPath)){
            return null;
        }
        File filesDir = new File(mDirPath);
        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(CRASH_REPORTER_EXTENSION);
            }
        };
        return filesDir.list(filter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
