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
import com.google.gson.Gson;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.adapter.RegionAdapter;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.http.ResponseCallback;
import com.hongsou.douguoshouyin.javabean.BaseBean;
import com.hongsou.douguoshouyin.javabean.RegionListBean;
import com.hongsou.douguoshouyin.tool.ToastUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private final int ADD_REGION = 1;//添加区域接口
    private final int UPDATE_REGION = 2;//更新区域接口

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
    private void showRegionList(final List<RegionListBean.DataBean> dataBeanList) {
        //创建适配器
        regionAdapter = new RegionAdapter(R.layout.module_item_region, dataBeanList);

        rvPayforTableRegionList.setAdapter(regionAdapter);
        //创建布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvPayforTableRegionList.setLayoutManager(layoutManager);

        regionAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                //获取其他子控件
                TextView tvItemPayforRegionAddDel = (TextView) adapter.getViewByPosition(rvPayforTableRegionList, position, R.id.tv_item_payfor_region_add_del);
                ImageView ivItemPayforRegionAddDelIcon  = (ImageView) adapter.getViewByPosition(rvPayforTableRegionList, position, R.id.iv_item_payfor_region_add_del_icon);

                if (view.getId() == R.id.iv_item_payfor_region_add_icon) {
                    //编辑区域
//                    ToastUtil.showToast("编辑");
                    showRegionDialog(UPDATE_REGION, dataBeanList.get(position).getLogGid(), regionAdapter, position);
                } else if (view.getId() == R.id.iv_item_payfor_region_add_del_icon) {
                    //删除icon 点击时隐藏   显示删除汉字
                    tvItemPayforRegionAddDel.setVisibility(View.VISIBLE);
                    ivItemPayforRegionAddDelIcon.setVisibility(View.GONE);
                } else if (view.getId() == R.id.tv_item_payfor_region_add_del) {
                    //走删除接口
                    itemDelete(dataBeanList.get(position).getLogGid(), adapter, position);

                    tvItemPayforRegionAddDel.setVisibility(View.GONE);
                    ivItemPayforRegionAddDelIcon.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    /**
     * @param logGid
     * @param adapter
     * @param position
     * @author fenghao
     * @date 2018/7/17 0017 上午 9:35
     * @desc 删除接口
     */
    private void itemDelete(String logGid, final BaseQuickAdapter adapter, final int position) {
        Map<String, String> itemDeleteMap = new HashMap<>();
        itemDeleteMap.put("logGid", logGid);
        Gson gson = new Gson();
        HttpFactory.postString(ApiConfig.DEL_REGION, gson.toJson(itemDeleteMap), new ResponseCallback<BaseBean>(this) {

            @Override
            public void onResponse(BaseBean response, int id) {
                if (response.isSuccess()) {
                    ToastUtil.showToast("删除成功");
                    //删除 删除这条item
                    adapter.remove(position);
                    // 更新视图
                    adapter.notifyDataSetChanged();
                } else {
                    ToastUtil.showToast(response.getMsg());
                }
            }
        });
    }


    @OnClick(R.id.tv_titlebar_right)
    public void onViewClicked() {
        //点击添加
        showRegionDialog(ADD_REGION, "", null, 0);
    }

    /**
     * @param regionFlag    判断是添加 还是编辑
     * @param logGid        区域id
     * @param regionAdapter
     * @param position      更改位置
     * @author fenghao
     * @date 2018/7/16 0016 下午 15:04
     * @desc 弹框添加区域的操作
     */
    private void showRegionDialog(final int regionFlag, final String logGid,
                                  final RegionAdapter regionAdapter, final int position) {

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
                if (TextUtils.isEmpty(etDialogRegionContent.getText().toString().trim())) {
                    ToastUtil.showToast("请填写区域名称");
                } else {
                    if (regionFlag == 1) {
                        //添加接口
                        addRegion(etDialogRegionContent.getText().toString());
                    } else {
                        //编辑接口
                        updateRegion(etDialogRegionContent.getText().toString(), logGid, regionAdapter, position);
                    }
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
     * @param regionName    区域名称
     * @param logGid        区域id
     * @param regionAdapter adapter
     * @param position      位置
     * @author fenghao
     * @date 2018/7/17 0017 上午 9:58
     * @desc 更新区域名称接口
     */
    private void updateRegion(final String regionName, String logGid, final RegionAdapter regionAdapter, final int position) {
        Map<String, String> updateRegionMap = new HashMap<>();
        updateRegionMap.put("logGid", logGid);
        updateRegionMap.put("regionName", regionName);
        Gson gson = new Gson();
        HttpFactory.postString(ApiConfig.UPDATE_REGION, gson.toJson(updateRegionMap), new ResponseCallback<BaseBean>(this) {

            @Override
            public void onResponse(BaseBean response, int id) {
                if (response.isSuccess()) {
                    ToastUtil.showToast("更改成功");
                    dataBeanList.get(position).setRegionName(regionName);
                    regionAdapter.notifyDataSetChanged();
                    dialog.dismiss();

                } else {

                    ToastUtil.showToast("更改失败");
                }
            }
        });
    }

    /**
     * @param regionName 区域名称
     * @author fenghao
     * @date 2018/7/16 0016 下午 15:18
     * @desc 添加区域接口 ADD_REGION
     */
    private void addRegion(String regionName) {
        Map<String, String> addRegionMap = new HashMap<>();
        addRegionMap.put("shopNumber", getShopNumber());
        addRegionMap.put("regionName", regionName);
        Gson gson = new Gson();
        HttpFactory.postString(ApiConfig.ADD_REGION, gson.toJson(addRegionMap), new ResponseCallback<BaseBean>(this) {
            @Override
            public void onResponse(BaseBean response, int id) {
                if (response.isSuccess()) {
                    ToastUtil.showToast("添加成功");
                    getRegionList();
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
