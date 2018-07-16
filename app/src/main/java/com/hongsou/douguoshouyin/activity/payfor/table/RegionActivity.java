package com.hongsou.douguoshouyin.activity.payfor.table;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.tool.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegionActivity extends BaseActivity {


    @BindView(R.id.tv_titlebar_right)
    TextView tvTitlebarRight;
    @BindView(R.id.rv_payfor_table_region_list)
    RecyclerView rvPayforTableRegionList;

    private Dialog dialog;

    @Override
    public int initLayout() {
        return R.layout.module_activity_payfor_region;
    }

    @Override
    protected void init() {
        initView();
        initData();
        initBack();
        initTitle("区域管理");
    }

    @Override
    public void initView() {
        tvTitlebarRight.setVisibility(View.VISIBLE);
        tvTitlebarRight.setText("添加");
    }

    @Override
    public void initData() {
        showRegionList();
    }

    /**
     * @param
     * @return
     * @author fenghao
     * @date 2018/7/16 0016 下午 15:01
     * @desc 走接口请求区域数据
     */
    private void showRegionList() {

    }

    @OnClick(R.id.tv_titlebar_right)
    public void onViewClicked() {
        //点击添加
        showAddDialog();
    }

    /**
     * @param
     * @return
     * @author fenghao
     * @date 2018/7/16 0016 下午 15:04
     * @desc 弹框添加区域的操作
     */
    private void showAddDialog() {
        View view = LinearLayout.inflate(this, R.layout.module_dialog_add, null);
        Display display = getWindowManager().getDefaultDisplay();
        int w = display.getWidth();
        int h = display.getHeight();

        TextView tv_dialog_region_content_icon = view.findViewById(R.id.tv_dialog_region_content_icon);
        TextView tv_dialog_region_cancle = view.findViewById(R.id.tv_dialog_region_cancle);
        TextView tv_dialog_region_yes = view.findViewById(R.id.tv_dialog_region_yes);
        final EditText et_dialog_region_content = view.findViewById(R.id.et_dialog_region_content);

        tv_dialog_region_content_icon.setTypeface(typeface);
        tv_dialog_region_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        //点击确定后走接口 添加
        tv_dialog_region_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(et_dialog_region_content.getText().toString())){
                    ToastUtil.showToast("请填写区域名称");
                }else {
                    addRegion(et_dialog_region_content.getText().toString());
                }
            }
        });

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(w * 4 / 5, h * 2 / 7);
        dialog.addContentView(view, params);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();


    }

    /**
     * @param
     * @params
     * @return
     * @author fenghao
     * @date 2018/7/16 0016 下午 15:18
     * @desc 添加区域接口 addRegion
     */
    private void addRegion(String s) {

    }

    //=============================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
