package com.hongsou.douguoshouyin.activity.payfor.goodsmanage;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
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

//管理分类页面
public class ManageClassActivity extends BaseActivity {


    @BindView(R.id.tv_titlebar_right)
    TextView tvTitlebarRight;

    Dialog dialog;

    @Override
    public int initLayout() {
        return R.layout.module_activity_payfor_manageclass;
    }

    @Override
    protected void init() {
        initView();
        initData();
        initBack();
        initTitle("管理分类");
    }

    @Override
    public void initView() {
        tvTitlebarRight.setText("添加");
        tvTitlebarRight.setVisibility(View.VISIBLE);
    }

    @Override
    public void initData() {
        //查询商品分类接口 TODO
        caipinfenlei();
    }

    /**
     * 查询菜品分类
     */
    private void caipinfenlei() {
        showLoadingDialog();

    }

    @OnClick(R.id.tv_titlebar_right)
    public void onViewClicked() {
        //点击添加后弹框
        showTianjiaDialog();
    }

    /**
     * 弹出添加弹框
     */
    private void showTianjiaDialog() {
        dialog = new Dialog(this, R.style.CommonDialog);

        View view = LayoutInflater.from(this).inflate(R.layout.module_dialog_fenlei_text, null);
        TextView tvDialogFenleiCancle = view.findViewById(R.id.tv_dialog_fenlei_cancle);
        TextView tvDialogFenleiYes = view.findViewById(R.id.tv_dialog_fenlei_yes);
        EditText etDialogFenleiContent = view.findViewById(R.id.et_dialog_fenlei_content);
        TextView tvDialogFenleiContentIcon = view.findViewById(R.id.tv_dialog_fenlei_content_icon);
        Spinner spDialogFenleiDantao = view.findViewById(R.id.sp_dialog_fenlei_dantao);


//        final String[] dantao = new String[1];//看用户选择的是单品还是套餐
        spDialogFenleiDantao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    ToastUtil.showToast("单品");
                } else {
                    ToastUtil.showToast("套餐");


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                ToastUtil.showToast("单品");
            }
        });
        setIconFont(new TextView[]{tvDialogFenleiContentIcon});

        tvDialogFenleiCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        tvDialogFenleiYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //走添加接口
                managetTianjia();
            }
        });
        Display display = this.getWindowManager().getDefaultDisplay();
        int w = display.getWidth();
        int h = display.getHeight();

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(w * 4 / 5, h * 2 / 7);
        dialog.addContentView(view, params);

        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();

    }

    /**
     * 走添加接口 然后刷新ui TODO
     */
    private void managetTianjia() {

    }

    //============================================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
