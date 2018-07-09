package com.hongsou.douguoshouyin.activity.payfor.payfor;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.http.Apiconfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import okhttp3.Call;

/**
 * 收款失败页面
 */
public class FailActivity extends BaseActivity {


    @BindView(R.id.tv_titlebar_tongbu_icon)
    TextView tvTitlebarTongbuIcon;
    @BindView(R.id.ll_titlebar_tongbu)
    LinearLayout llTitlebarTongbu;
    @BindView(R.id.tv_payfor_fail_shoukuanjine)
    TextView tvPayforFailShoukuanjine;
    @BindView(R.id.tv_payfor_fail_dingdanhao)
    TextView tvPayforFailDingdanhao;
    @BindView(R.id.tv_payfor_fail_jiaoyishijian)
    TextView tvPayforFailJiaoyishijian;
    @BindView(R.id.tv_payfor_fail_jiaoyijine)
    TextView tvPayforFailJiaoyijine;
    @BindView(R.id.tv_payfor_fail_zhifufangshi)
    TextView tvPayforFailZhifufangshi;
    @BindView(R.id.tv_payfor_fail_zhifuzhuangtai)
    TextView tvPayforFailZhifuzhuangtai;
    @BindView(R.id.bt_payfor_fail_jixu)
    Button btPayforFailJixu;

    @Override
    public int initLayout() {
        return R.layout.module_activity_payfor_fail;
    }

    @Override
    protected void init() {
        initView();
        initData();
        initBack();
        initTitle("收款失败");
    }

    @Override
    public void initView() {
        setIconFont(new TextView[]{tvTitlebarTongbuIcon});
        llTitlebarTongbu.setVisibility(View.VISIBLE);
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.ll_titlebar_tongbu, R.id.bt_payfor_fail_jixu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_titlebar_tongbu:
                tongbu();
                break;
            case R.id.bt_payfor_fail_jixu:
                finishActivity();
                break;
        }
    }

    /**
     * 同步接口
     */
    private void tongbu() {
        showLoadingDialog();
        HttpFactory.post().url(Apiconfig.tongbu).addParams("", "").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoadingDialog();

            }

            @Override
            public void onResponse(String response, int id) {
                dismissLoadingDialog();
                //TODO 成功后跳转到收款成功？？？
            }
        });
    }

    //===================================================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
