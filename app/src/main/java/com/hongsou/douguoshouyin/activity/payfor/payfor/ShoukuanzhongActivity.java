package com.hongsou.douguoshouyin.activity.payfor.payfor;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hongsou.douguoshouyin.http.ApiConfig;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.tool.ToastUtil;
import okhttp3.Call;
/**
 * 收款中页面
 */
public class ShoukuanzhongActivity extends BaseActivity {


    @BindView(R.id.tv_titlebar_tongbu_icon)
    TextView tvTitlebarTongbuIcon;
    @BindView(R.id.ll_titlebar_tongbu)
    LinearLayout llTitlebarTongbu;
    @BindView(R.id.tv_payfor_zhifuzhong_shoukuanjine)
    TextView tvPayforZhifuzhongShoukuanjine;
    @BindView(R.id.tv_payfor_zhifuzhong_dingdanhao)
    TextView tvPayforZhifuzhongDingdanhao;
    @BindView(R.id.tv_payfor_zhifuzhong_jiaoyishijian)
    TextView tvPayforZhifuzhongJiaoyishijian;
    @BindView(R.id.tv_payfor_zhifuzhong_jiaoyijine)
    TextView tvPayforZhifuzhongJiaoyijine;
    @BindView(R.id.tv_payfor_zhifuzhong_zhifufangshi)
    TextView tvPayforZhifuzhongZhifufangshi;
    @BindView(R.id.tv_payfor_zhifuzhong_zhifuzhuangtai)
    TextView tvPayforZhifuzhongZhifuzhuangtai;


    @Override
    public int initLayout() {
        return R.layout.module_activity_payfor_zhifuzhong;
    }

    @Override
    protected void init() {
        initView();
        initData();
        initBack();
        initTitle("收款中");
    }

    @Override
    public void initView() {
        setIconFont(new TextView[]{tvTitlebarTongbuIcon});
        llTitlebarTongbu.setVisibility(View.VISIBLE);
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.ll_titlebar_tongbu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_titlebar_tongbu:
                tongbu();
                break;
        }
    }


    /**
     * 同步接口
     */
    private void tongbu() {
        showLoadingDialog();
        HttpFactory.post().url(ApiConfig.TONGBU).addParams("", "").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoadingDialog();

            }

            @Override
            public void onResponse(String response, int id) {
                dismissLoadingDialog();
                //TODO 成功后跳转到收款成功？？？
                ToastUtil.showToast("同步成功");
                ToastUtil.showToast("没有该订单请稍后重试");

            }
        });
    }

    //======================================================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
