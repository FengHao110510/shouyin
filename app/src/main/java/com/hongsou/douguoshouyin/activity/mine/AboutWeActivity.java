package com.hongsou.douguoshouyin.activity.mine;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.Layout;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.hongsou.douguoshouyin.activity.login.LoginActivity;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.http.ResponseCallback;
import com.hongsou.douguoshouyin.javabean.SystemSetupBean;
import com.hongsou.douguoshouyin.tool.APKVersionCodeUtils;
import com.hongsou.douguoshouyin.tool.DateUtils;
import com.hongsou.douguoshouyin.tool.ToastUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.tool.Global;

import java.io.File;

import okhttp3.Call;

/**
 * 关于我们
 */
public class AboutWeActivity extends BaseActivity {


    @BindView(R.id.tv_mine_aboutwe_version)
    TextView tvMineAboutweVersion;
    @BindView(R.id.tv_mine_aboutwe_check)
    Button tvMineAboutweCheck;


    @Override
    public int initLayout() {
        return R.layout.module_activity_mine_about_we;
    }

    @Override
    protected void init() {
        initView();
        initData();
        initBack();
        initTitle("关于我们");
    }

    @Override
    public void initView() {
        tvMineAboutweVersion.setText("版本号V " + Global.getVersionName());
    }

    @Override
    public void initData() {
    }


    @OnClick(R.id.tv_mine_aboutwe_check)
    public void onViewClicked() {
        //检查更新  走接口 TODO
        HttpFactory.get().url(ApiConfig.GET_SYSTEM_SETUP)
                .addParams("diffVersion", "qingshouyin")
                .build().execute(new ResponseCallback<SystemSetupBean>(this) {
            @Override
            public void onResponse(SystemSetupBean response, int id) {
                if (response.isSuccess()) {
                    //123瞎写的
                    //判断当前版本号与后台是否相同
                    String versionCode = APKVersionCodeUtils.getVersionCode(AboutWeActivity.this) + "";

                    if (!versionCode.equals(response.getData().getNewVersionNumber())) {
                        showAskDialog(response.getData().getDownloadAddress());
                    } else {
                        ToastUtil.showToast("最新版本无需更新");
                    }
                } else {
                    ToastUtil.showToast(response.getMsg());
                }
            }
        });


    }

    /**
     * @param downloadAddress
     * @author fenghao
     * @date 2018/8/1 0001 下午 17:19
     * @desc 弹框询问用户是否更新
     */
    Dialog askDialog;

    private void showAskDialog(final String downloadAddress) {
        View view = LayoutInflater.from(this).inflate(R.layout.module_dialog_text, null);
        Display display = getWindowManager().getDefaultDisplay();
        int w = display.getWidth();
        int h = display.getHeight();

        TextView tvDialogTextTitle = view.findViewById(R.id.tv_dialog_text_title);
        TextView tvDialogTextContent = view.findViewById(R.id.tv_dialog_text_content);
        TextView tvLogoutDialogCancle = view.findViewById(R.id.tv_logout_dialog_cancle);
        TextView tvLogoutDialogYes = view.findViewById(R.id.tv_logout_dialog_yes);

        tvDialogTextTitle.setText("提示");
        tvDialogTextContent.setText("有新版本，确定更新吗？");
        tvLogoutDialogCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                askDialog.dismiss();
            }
        });

        tvLogoutDialogYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downLoadApk(downloadAddress);
                showDonwLoadDialog();
                askDialog.dismiss();
            }
        });
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(w * 4 / 5, h * 2 / 7);
        askDialog = new Dialog(this, R.style.CommonDialog);
        askDialog.addContentView(view, params);
        askDialog.setCancelable(false);
        askDialog.setCanceledOnTouchOutside(false);
        askDialog.show();
    }

    /**
     * @author fenghao
     * @date 2018/8/1 0001 下午 16:46
     * @desc
     */
    Dialog downLoadDialog;
    ProgressBar progressBar;
    TextView tvDialogCheckContent;

    private void showDonwLoadDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.module_dialog_check, null);

        progressBar = view.findViewById(R.id.pro_dialog_check_pro);
        tvDialogCheckContent = view.findViewById(R.id.tv_dialog_check_content);

        Display display = getWindowManager().getDefaultDisplay();
        int w = display.getWidth();
        int h = display.getHeight();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(w * 4 / 5, h * 2 / 7);
        downLoadDialog = new Dialog(this, R.style.CommonDialog);
        downLoadDialog.addContentView(view, params);
        downLoadDialog.setCanceledOnTouchOutside(false);
        downLoadDialog.setCancelable(false);
        downLoadDialog.show();
    }

    private void downLoadApk(String downloadAddress) {
        String path = Environment.getExternalStorageDirectory() + "/douguo/apk/";
        //测试
        String download = "https://pro-app-qn.fir.im/9d72c9b9c0be3b8e08a56c58aa0e5a757d8e83df.apk?attname=dgcy-release-1.0.4-hualaishi.apk_1.0.4.apk&e=1533121474&token=LOvmia8oXF4xnLh0IdH05XMYpH6ENHNpARlmPc-T:LwI-IGsriaLH5fJ-hkkYQOQo7-0=";

        OkHttpUtils.get().url(downloadAddress).build().execute(
                new FileCallBack(path, "qingshouyin" + DateUtils.getNowDateLong()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtil.showToast("网络连接失败");
                        downLoadDialog.dismiss();
                    }

                    @Override
                    public void onResponse(File response, int id) {
                        if (downLoadDialog != null) {
                            downLoadDialog.dismiss();
                        }
                        ToastUtil.showToast("下载完成");

                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addCategory(Intent.CATEGORY_DEFAULT);
                        intent.setDataAndType(Uri.fromFile(response),
                                "application/vnd.android.package-archive");
                        startActivity(intent);
                    }

                    @Override
                    public void inProgress(float progress, long total, int id) {
                        super.inProgress(progress, total, id);
                        progressBar.setProgress((int) (progress * 100));
                        Message message = new Message();
                        message.what = 0;
                        message.obj = (int) (progress * 100);
                        mHandler.sendMessage(message);
                    }
                });
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    //正在下载
                    tvDialogCheckContent.setText("已完成" + msg.obj + "%");
                    break;
                case 1:
                    //下载完成
                    downLoadDialog.dismiss();
                    break;
                case 3:
                    //？？？？
                    break;
                default:
                    break;
            }
        }

    };

    //=================================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
