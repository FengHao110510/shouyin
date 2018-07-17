package com.hongsou.douguoshouyin.activity.payfor.table;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.adapter.OrderAdapter;
import com.hongsou.douguoshouyin.adapter.RegionAdapter;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.http.ResponseCallback;
import com.hongsou.douguoshouyin.javabean.BaseBean;
import com.hongsou.douguoshouyin.javabean.RegionListBean;
import com.hongsou.douguoshouyin.tool.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegionActivity extends BaseActivity {


    @BindView(R.id.tv_titlebar_right)
    TextView tvTitlebarRight;
    @BindView(R.id.rv_payfor_table_region_list)
    RecyclerView rvPayforTableRegionList;

    List<RegionListBean.DataBean> dataBeanList;
    private Dialog dialog;
    private RegionAdapter regionAdapter;

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
        getRegionList();
    }

    /**
     * @author fenghao
     * @date 2018/7/16 0016 下午 15:01
     * @desc 走接口请求区域数据
     */
    private void getRegionList() {
        HttpFactory.get().url(ApiConfig.GET_REGION_LIST)
                .addParams("shopNumber", getShopNumber())
                .build().execute(new ResponseCallback<RegionListBean>(this) {
            @Override
            public void onResponse(RegionListBean response, int id) {
                if (response.isSuccess()) {
                    dataBeanList = response.getData();
                    showRegionList(dataBeanList);
                } else {
                    ToastUtil.showToast("查询失败");
                }
            }

        });
    }

    /**
     * @param dataBeanList
     * @author fenghao
     * @date 2018/7/16 0016 下午 17:55
     * @desc 展示区域列表
     */
    private void showRegionList(List<RegionListBean.DataBean> dataBeanList) {

        regionAdapter = new RegionAdapter(R.layout.module_item_region, dataBeanList);
        //创建适配器
        rvPayforTableRegionList.setAdapter(regionAdapter);
        //创建布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvPayforTableRegionList.setLayoutManager(layoutManager);

        regionAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                TextView tvItemPayforRegionAddDel = view.findViewById(R.id.tv_item_payfor_region_add_del);
//                ImageView ivItemPayforRegionAddDelIcon = view.findViewById(R.id.iv_item_payfor_region_add_del_icon);
                TextView tvItemPayforRegionAddDel = (TextView) adapter.getViewByPosition(rvPayforTableRegionList, position, R.id.tv_item_payfor_region_add_del);
                ImageView ivItemPayforRegionAddDelIcon = (ImageView) adapter.getViewByPosition(rvPayforTableRegionList, position, R.id.iv_item_payfor_region_add_del_icon);

                if (view.getId() == R.id.iv_item_payfor_region_add_icon) {
                    //编辑区域
                    ToastUtil.showToast("编辑");

                } else if (view.getId() == R.id.iv_item_payfor_region_add_del_icon) {
                    tvItemPayforRegionAddDel.setVisibility(View.VISIBLE);
                    ivItemPayforRegionAddDelIcon.setVisibility(View.GONE);
                } else if (view.getId() == R.id.tv_item_payfor_region_add_del) {


                    tvItemPayforRegionAddDel.setVisibility(View.GONE);
                    ivItemPayforRegionAddDelIcon.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @OnClick(R.id.tv_titlebar_right)
    public void onViewClicked() {
        //点击添加
        showAddDialog();
    }

    /**
     * @author fenghao
     * @date 2018/7/16 0016 下午 15:04
     * @desc 弹框添加区域的操作
     */
    private void showAddDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.module_dialog_add, null);
        Display display = this.getWindowManager().getDefaultDisplay();
        int w = display.getWidth();
        int h = display.getHeight();

        TextView tvDialogRegionContentIcon = view.findViewById(R.id.tv_dialog_region_content_icon);
        TextView tvDialogRegionCancle = view.findViewById(R.id.tv_dialog_region_cancle);
        TextView tvDialogRegionYes = view.findViewById(R.id.tv_dialog_region_yes);
        final EditText etDialogRegionContent = view.findViewById(R.id.et_dialog_region_content);

        tvDialogRegionContentIcon.setTypeface(typeface);
        tvDialogRegionCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        //点击确定后走接口 添加
        tvDialogRegionYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(etDialogRegionContent.getText().toString())) {
                    ToastUtil.showToast("请填写区域名称");
                } else {
                    addRegion(etDialogRegionContent.getText().toString());
                }
            }
        });

        dialog = new Dialog(this, R.style.CommonDialog);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(w * 4 / 5, h * 1 / 6);
        dialog.addContentView(view, params);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();


    }

    /**
     * @author fenghao
     * @date 2018/7/16 0016 下午 15:18
     * @desc 添加区域接口 ADD_REGION
     */
    private void addRegion(String s) {
        HttpFactory.post().url(ApiConfig.ADD_REGION)
                .addParams("shopNumber", getShopNumber())
                .addParams("regionName", s)
                .build().execute(new ResponseCallback<BaseBean>(this) {
            @Override
            public void onResponse(BaseBean response, int id) {
                if (response.isSuccess()) {
                    ToastUtil.showToast("添加成功");
                } else {
                    ToastUtil.showToast("添加失败");
                }
                dialog.dismiss();
            }

        });
    }

    //=============================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
