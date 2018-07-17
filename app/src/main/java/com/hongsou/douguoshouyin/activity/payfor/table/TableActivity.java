package com.hongsou.douguoshouyin.activity.payfor.table;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.adapter.TableAdapter;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.http.ResponseCallback;
import com.hongsou.douguoshouyin.javabean.TableBean;
import com.hongsou.douguoshouyin.javabean.TableListContentBean;
import com.hongsou.douguoshouyin.javabean.TableRegionTitleBean;
import com.hongsou.douguoshouyin.tool.ToastUtil;

import java.util.ArrayList;
import java.util.List;

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
    @BindView(R.id.rv_payfor_table_list)
    RecyclerView rvPayforTableList;
    //桌台数据
    List<TableBean.DataBean> dataBeanList;

    //适配器
    TableAdapter tableAdapter;

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
        getTableList();
    }

    /**
     * @author fenghao
     * @date 2018/7/17 0017 上午 10:21
     * @desc 获取桌台列表
     */
    private void getTableList() {
        HttpFactory.get().url(ApiConfig.GET_TABLE_LIST)
                .addParams("shopNumber", getShopNumber())
                .build().execute(new ResponseCallback<TableBean>(this) {

            @Override
            public void onResponse(TableBean response, int id) {
                if (response.isSuccess()) {
                    dataBeanList = response.getData();
                    showTableList(dataBeanList);
                } else {
                    ToastUtil.showToast("查询失败");
                }
            }
        });
    }

    /**
     * @param dataBeanList
     * @author fenghao
     * @date 2018/7/17 0017 上午 10:28
     * @desc 展示桌台列表
     */
    private void showTableList(List<TableBean.DataBean> dataBeanList) {
        final ArrayList<MultiItemEntity> res = new ArrayList<>();
        for (int i = 0; i < dataBeanList.size(); i++) {
            TableBean.DataBean dataBean = dataBeanList.get(i);
            TableRegionTitleBean tableRegionTitleBean = new TableRegionTitleBean(dataBean.getRegionName()
                    , dataBean.getPedestal(), dataBean.getRegionNumber());
            List<TableBean.DataBean.TableListBean> tableList = dataBean.getTableList();


            for (int k = 0; k < tableList.size(); k++) {
                TableListContentBean tableListContentBean = new TableListContentBean(tableList.get(k).getShopNumber(),
                        tableList.get(k).getTabletableNumber(), tableList.get(k).getPedestal(), tableList.get(k).getNumber());
                tableRegionTitleBean.addSubItem(i, tableListContentBean);
            }
            res.add(tableRegionTitleBean);
        }
        //设置适配器
        tableAdapter = new TableAdapter(res);
        //设置子菜单显示
        tableAdapter.expandAll();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        //判断显示列数 头显示一列
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return setSpanSize(position, res);
            }
        });
        rvPayforTableList.setAdapter(tableAdapter);
        rvPayforTableList.setLayoutManager(gridLayoutManager);


    }


    private int setSpanSize(int position, ArrayList<MultiItemEntity> res) {
        int count;
//        TableRegionTitleBean tableRegionTitleBean = (TableRegionTitleBean) res.get(position);
//        if (!TextUtils.isEmpty(tableRegionTitleBean.getRegionName())) {
        if (res.get(position).getItemType()==0){
            count = 3;
        } else {
            count = 1;
        }

        return count;
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
