package com.hongsou.douguoshouyin.activity.payfor.table;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 桌台管理页面
 */
public class TableActivity extends BaseActivity {


    Dialog dialog;
    @BindView(R.id.tv_payfor_table_add)
    TextView tvPayforTableAdd;
    @BindView(R.id.tv_payfor_table_delet)
    TextView tvPayforTableDelet;
    @BindView(R.id.tv_payfor_table_region)
    TextView tvPayforTableRegion;

    @Override
    public int initLayout() {
        return R.layout.module_activity_payfor_table;
    }

    @Override
    protected void init() {
        initView();
        initData();
        initBack();
        initTitle("桌台管理");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.tv_payfor_table_region)
    public void onViewClicked() {
    }

    @OnClick({R.id.tv_payfor_table_add, R.id.tv_payfor_table_delet, R.id.tv_payfor_table_region})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_payfor_table_add:
                //弹框批量添加
                showAddTableDialog();
                break;
            case R.id.tv_payfor_table_delet:
                //批量删除
                break;
            case R.id.tv_payfor_table_region:
                //区域管理
                startActivity(new Intent(this, RegionActivity.class));
                break;
            default:
                break;
        }
    }

    //弹框批量添加

    private void showAddTableDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.module_dialog_add_zhuotai, null);
        //规格
        EditText etDialogZhuotaiguige = view.findViewById(R.id.et_dialog_zhuotaiguige);
        //数量
        EditText etDialogZhuotaishuliang = view.findViewById(R.id.et_dialog_zhuotaishuliang);
        //取消
        TextView tvDialogAddzhuotaiCancle = view.findViewById(R.id.tv_dialog_addzhuotai_cancle);
        //确定
        TextView tvDialogAddzhuotaiYes = view.findViewById(R.id.tv_dialog_addzhuotai_yes);

        tvDialogAddzhuotaiCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });
        tvDialogAddzhuotaiYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //成功后走接口上传
                dialog.dismiss();
            }
        });

        dialog = new Dialog(this, R.style.CommonDialog);

        Display display = getWindowManager().getDefaultDisplay();
        int w = display.getWidth();
        int h = display.getHeight();

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(w * 4 / 5, h / 4);
        dialog.addContentView(view, params);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }

    //===============================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
