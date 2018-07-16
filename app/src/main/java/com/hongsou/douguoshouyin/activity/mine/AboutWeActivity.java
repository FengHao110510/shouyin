package com.hongsou.douguoshouyin.activity.mine;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.hongsou.douguoshouyin.http.ApiConfig;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.tool.Global;
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
        showLoadingDialog();
        HttpFactory.post().url(ApiConfig.checkVersion).addParams("", "").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoadingDialog();
            }

            @Override
            public void onResponse(String response, int id) {

            }
        });

    }

    //=================================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
