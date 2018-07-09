package com.hongsou.douguoshouyin.activity.payfor.payfor;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.hongsou.douguoshouyin.tool.ToastUtil;
import okhttp3.Call;

/**
 * 收款成功页面
 */
public class SuccessActivity extends BaseActivity {


    @BindView(R.id.tv_payfor_success_shoukuanjine)
    TextView tvPayforSuccessShoukuanjine;
    @BindView(R.id.tv_payfor_success_dingdanhao)
    TextView tvPayforSuccessDingdanhao;
    @BindView(R.id.tv_payfor_success_jiaoyishijian)
    TextView tvPayforSuccessJiaoyishijian;
    @BindView(R.id.tv_payfor_success_jiaoyijine)
    TextView tvPayforSuccessJiaoyijine;
    @BindView(R.id.tv_payfor_success_zhifufangshi)
    TextView tvPayforSuccessZhifufangshi;
    @BindView(R.id.tv_payfor_success_zhifuzhuangtai)
    TextView tvPayforSuccessZhifuzhuangtai;
    @BindView(R.id.bt_payfor_success_tuikuan)
    Button btPayforSuccessTuikuan;
    @BindView(R.id.bt_payfor_success_dayin)
    Button btPayforSuccessDayin;

    private Dialog dialog;

    @Override
    public int initLayout() {
        return R.layout.module_activity_payfor_success;
    }

    @Override
    protected void init() {
        initView();
        initData();
        initBack();
        initTitle("收款成功");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        //TODO 需要初始化数据 订单号啥的

    }

    @OnClick({R.id.bt_payfor_success_tuikuan, R.id.bt_payfor_success_dayin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_payfor_success_tuikuan:
                //走退款接口
                tuikuanDialog();
                break;
            case R.id.bt_payfor_success_dayin:
                break;
        }
    }

    //输入退款密码
    private void tuikuanDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.module_dialog_tuikuan, null);
        final EditText et_dialog_tuikuan_content = view.findViewById(R.id.et_dialog_tuikuan_content);
        TextView tv_dialog_tuikuan_yes = view.findViewById(R.id.tv_dialog_tuikuan_yes);
        TextView tv_dialog_tuikuan_cancle = view.findViewById(R.id.tv_dialog_tuikuan_cancle);

        Display display = this.getWindowManager().getDefaultDisplay();
        int w = display.getWidth();
        int h = display.getHeight();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(w * 4 / 5, h * 2 / 7);
        dialog = new Dialog(this, R.style.CommonDialog);
        dialog.addContentView(view, params);
        dialog.show();


        tv_dialog_tuikuan_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!et_dialog_tuikuan_content.getText().toString().equals("")) {
                    //TODO 判断密码是否正确
                    tuikuan();
                    dialog.dismiss();
                } else {
                    ToastUtil.showToast("请输入密码");
                }

            }
        });
        tv_dialog_tuikuan_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }

    /**
     * 退款接口`
     */
    private void tuikuan() {
        showLoadingDialog();
        HttpFactory.post().url(Apiconfig.tuikuan).addParams("", "").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoadingDialog();

            }

            @Override
            public void onResponse(String response, int id) {
                dismissLoadingDialog();
                //TODO 成功之后跳转到退款成功页面 此页面关闭
                Intent backIntent = new Intent(SuccessActivity.this, BackPayActivity.class);
                startActivity(backIntent);
                finishActivity();
            }
        });
    }


    //=======================================================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
