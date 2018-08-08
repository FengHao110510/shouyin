package com.hongsou.douguoshouyin.activity.payfor.table;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.gson.Gson;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.adapter.TableAdapter;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.http.ResponseCallback;
import com.hongsou.douguoshouyin.javabean.BaseBean;
import com.hongsou.douguoshouyin.javabean.DeletTableBean;
import com.hongsou.douguoshouyin.javabean.RegionListBean;
import com.hongsou.douguoshouyin.javabean.TableBean;
import com.hongsou.douguoshouyin.javabean.TableListContentBean;
import com.hongsou.douguoshouyin.javabean.TableRegionTitleBean;
import com.hongsou.douguoshouyin.tool.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 桌台管理页面
 */
public class TableActivity extends BaseActivity {


    @BindView(R.id.tv_payfor_table_add)
    TextView tvPayforTableAdd;
    @BindView(R.id.tv_payfor_table_delet)
    TextView tvPayforTableDelet;
    @BindView(R.id.tv_payfor_table_region)
    TextView tvPayforTableRegion;
    @BindView(R.id.rv_payfor_table_list)
    RecyclerView rvPayforTableList;
    @BindView(R.id.tv_titlebar_right)
    TextView tvTitlebarRight;
    //桌台数据
    private List<TableBean.DataBean> dataBeanList;
    //区域数据
    private List<RegionListBean.DataBean> regionDataBeanList;

    //适配器
    private TableAdapter tableAdapter;
    //批量添加的弹框
    private Dialog dialog;
    //添加单个的弹框
    private Dialog addOneDialog;
    //添加单个的弹框
    private Dialog deletDialog;
    //区域编号
    private String regionNumber;
    //数据源
    ArrayList<MultiItemEntity> res = new ArrayList<>();
    ;

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
        tvTitlebarRight.setText("打包下载");
        tvTitlebarRight.setVisibility(View.VISIBLE);
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
        res.clear();
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

        for (int i = 0; i < dataBeanList.size(); i++) {
            //添加头部
            TableBean.DataBean dataBean = dataBeanList.get(i);
            TableRegionTitleBean tableRegionTitleBean = new TableRegionTitleBean(dataBean.getRegionName()
                    , dataBean.getPedestal(), dataBean.getRegionNumber());
            List<TableBean.DataBean.TableListBean> tableList = dataBean.getTableList();

            for (int k = 0; k < tableList.size(); k++) {
                //添加子级
                TableListContentBean tableListContentBean = new TableListContentBean(tableList.get(k).getShopNumber()
                        , tableList.get(k).getLogGid()
                        , tableList.get(k).getTabletableNumber()
                        , tableList.get(k).getPedestal()
                        , tableList.get(k).getNumber()
                        , tableList.get(k).getRegionNumber()
                        , false
                        , tableList.get(k).getQrCodeLink());
                Log.e(TAG, "showTableList: " + tableList.get(k).getNumber());
                tableRegionTitleBean.addSubItem(tableListContentBean);
            }
//            }

            //添加一个空白的 + 按钮  110510瞎写的用作区分  dataBean.getPedestal() 规格 dataBean.getRegionNumber()区域编号  添加单个桌台有用
            TableListContentBean tableListContentBean2 = new TableListContentBean("",
                    "", "", dataBean.getPedestal(), 110510,
                    dataBean.getRegionNumber(), false
                    , "");
            tableRegionTitleBean.addSubItem(tableListContentBean2);
            res.add(tableRegionTitleBean);
        }
        //设置适配器
        tableAdapter = new TableAdapter(res);
        //设置子菜单显示
        tableAdapter.expandAll();

        //显示
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        //判断显示列数 头显示一列
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return setSpanSize(position, res);
            }
        });
        //先添加adapter 在添加manager setSpanSizeLookup才会被调用
        rvPayforTableList.setAdapter(tableAdapter);
        rvPayforTableList.setLayoutManager(gridLayoutManager);

        //设置点击事件
        setChildClick(tableAdapter, res);

    }

    /**
     * @param tableAdapter 桌台适配器
     * @param res          数据源
     * @author fenghao
     * @date 2018/7/17 0017 下午 17:54
     * @desc 设置点击事件
     */
    private void setChildClick(TableAdapter tableAdapter, final ArrayList<MultiItemEntity> res) {
        tableAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                TableListContentBean tableListContentBean = (TableListContentBean) res.get(position);
                String regionName = "";
                for (int i = 0; i < res.size(); i++) {
                    if (res.get(i).getItemType() == TableAdapter.TYPE_TABLE_TITLE) {
                        if (tableListContentBean.getRegionNumber().equals(((TableRegionTitleBean) res.get(i)).getRegionNumber())) {
                            regionName = ((TableRegionTitleBean) res.get(i)).getRegionName()
                                    + "(" + ((TableRegionTitleBean) res.get(i)).getPedestal() + "人桌)";
                        }
                    }
                }
                if (view.getId() == R.id.tv_item_table_content_pedestal) {
                    //点击选中状态改变
                    if (tableListContentBean.isSelectFlag()) {
                        view.setBackground(getResources().getDrawable(R.drawable.btn_logout));
                        tableListContentBean.setSelectFlag(false);
                    } else {
                        tableListContentBean.setSelectFlag(true);
                        view.setBackground(getResources().getDrawable(R.drawable.btn_stroke_red));
                    }
                } else if (view.getId() == R.id.iv_item_table_content_add) {
                    //点击的最后一个添加图片时
                    //走单个添加接口
                    showAddOneTableDialog(tableListContentBean, regionName);
                }
            }
        });
    }

    /**
     * @param tableListContentBean
     * @param regionName
     * @author fenghao
     * @date 2018/7/17 0017 下午 18:41
     * @desc 弹框让用户输入 桌台编号
     */
    private void showAddOneTableDialog(final TableListContentBean tableListContentBean, String regionName) {
        addOneDialog = new Dialog(this, R.style.CommonDialog);
        View view = LayoutInflater.from(this).inflate(R.layout.module_dialog_edit, null);
        Display display = this.getWindowManager().getDefaultDisplay();
        int w = display.getWidth();
        int h = display.getHeight();
        TextView tvDialogEditTitle = view.findViewById(R.id.tv_dialog_edit_title);
        TextView tvDialogEditIcon = view.findViewById(R.id.tv_dialog_edit_icon);
        TextView tvDialogEditYes = view.findViewById(R.id.tv_dialog_edit_yes);
        TextView tvDialogEditCancle = view.findViewById(R.id.tv_dialog_edit_cancle);
        final EditText etDialogEditContent = view.findViewById(R.id.et_dialog_edit_content);
        tvDialogEditIcon.setVisibility(View.VISIBLE);
        setIconFont(new TextView[]{tvDialogEditIcon});
        tvDialogEditTitle.setText(regionName);
        etDialogEditContent.setHint("输入桌台号");
        etDialogEditContent.setInputType(InputType.TYPE_CLASS_NUMBER);
        tvDialogEditCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addOneDialog.dismiss();
            }
        });
        tvDialogEditYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //走添加单个桌台的接口
                if (!TextUtils.isEmpty(etDialogEditContent.getText().toString())) {
                    addOneTable(tableListContentBean, etDialogEditContent.getText().toString());

                } else {
                    ToastUtil.showToast("请输入桌台号");
                }
            }
        });
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(w * 4 / 5, h * 1 / 4);
        addOneDialog.addContentView(view, params);
        addOneDialog.setCancelable(false);
        addOneDialog.setCanceledOnTouchOutside(false);
        addOneDialog.show();
    }

    /**
     * @param tableListContentBean 上传的数据
     * @param number               桌台号
     * @author fenghao
     * @date 2018/7/17 0017 下午 18:54
     * @desc 添加单个接口
     */
    private void addOneTable(TableListContentBean tableListContentBean, String number) {

        Map<String, String> addOneMap = new HashMap<String, String>();
        addOneMap.put("shopNumber", getShopNumber());
        addOneMap.put("pedestal", tableListContentBean.getPedestal() + "");
        addOneMap.put("regionNumber", tableListContentBean.getRegionNumber());
        addOneMap.put("number", number);
        Gson gson = new Gson();
        HttpFactory.postString(ApiConfig.ADD_TABLE, gson.toJson(addOneMap), new ResponseCallback<BaseBean>(this) {
            @Override
            public void onResponse(BaseBean response, int id) {
                if (response.isSuccess()) {
                    ToastUtil.showToast("添加成功");
                    addOneDialog.dismiss();
                    getTableList();
                } else {
                    ToastUtil.showToast(response.getMsg());
                }
            }
        });
    }


    /**
     * @param position item位置
     * @param res      数据源 用于判断显示格数
     * @author fenghao
     * @date 2018/7/17 0017 下午 17:05
     * @desc 判断item所占格数
     */
    private int setSpanSize(int position, ArrayList<MultiItemEntity> res) {
        int count;
        //一级菜单 TYPE为TYPE_TABLE_TITLE
        if (res.get(position).getItemType() == TableAdapter.TYPE_TABLE_TITLE) {
            //占3格 因为总数是3格  所以显示效果为macthparent
            count = 3;
        } else {
            count = 1;
        }
        return count;
    }


    @OnClick({R.id.tv_payfor_table_add, R.id.tv_payfor_table_delet, R.id.tv_payfor_table_region, R.id.tv_titlebar_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_payfor_table_add:
                //弹框批量添加
                showAddTableDialog();
                break;
            case R.id.tv_payfor_table_delet:
                //批量删除
                showDeletListTableDialog();
                break;
            case R.id.tv_payfor_table_region:
                //区域管理
                startActivity(new Intent(this, RegionActivity.class));
                break;
            case R.id.tv_titlebar_right:
                //打包下载
                showDownLoadDialog();
                break;
            default:
                break;
        }
    }

    /**
     * @author fenghao
     * @date 2018/7/30 0030 下午 16:36
     * @desc 打包下载dialog
     */
    Dialog downLoadDialog;

    private void showDownLoadDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.module_dialog_edit2, null);
        Display display = getWindowManager().getDefaultDisplay();
        int w = display.getWidth();
        int h = display.getHeight();
        TextView tvDialogEditYes = view.findViewById(R.id.tv_dialog_edit_yes);
        TextView tvDialogEditCancle = view.findViewById(R.id.tv_dialog_edit_cancle);
        TextView tvDialogEditIcon = view.findViewById(R.id.tv_dialog_edit_icon);
        final EditText etDialogEditContent = view.findViewById(R.id.et_dialog_edit_content);
        tvDialogEditIcon.setVisibility(View.VISIBLE);
        setIconFont(new TextView[]{tvDialogEditIcon});
        etDialogEditContent.setHint("请输入要发送的邮箱地址！");
        tvDialogEditYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(etDialogEditContent.getText().toString())) {
                    ToastUtil.showToast("请输入要发送的邮箱地址！");
                } else {
                    final ArrayList<DeletTableBean> emailTableBeanArrayList = new ArrayList<>();
                    for (int w = 0; w < res.size(); w++) {
                        if (res.get(w).getItemType() == TableAdapter.TYPE_TABLE_CONTENT) {
                            TableListContentBean tableListContentBean = (TableListContentBean) res.get(w);
                            //根据条目数形判断有没有被选中
                            if (tableListContentBean.isSelectFlag()) {
                                DeletTableBean deletTableBean = new DeletTableBean(tableListContentBean.getLogGid());
                                for (int n = 0; n < dataBeanList.size(); n++) {
                                    if (dataBeanList.get(n).getRegionNumber().equals(tableListContentBean.getRegionNumber())) {
                                        deletTableBean.setRemarks(dataBeanList.get(n).getRegionName());
                                    }
                                }
                                deletTableBean.setNumber(tableListContentBean.getNumber() + "");
                                deletTableBean.setQrCodeLink(tableListContentBean.getQrCodeLink() + "");
                                emailTableBeanArrayList.add(deletTableBean);
                            }
                        }
                        if (emailTableBeanArrayList.size() < 1) {
                            ToastUtil.showToast("请先选择选中的桌台");
                            downLoadDialog.dismiss();
                        } else {
                            if (etDialogEditContent.getText().toString().matches("^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$")) {
                                downLoad(etDialogEditContent.getText().toString(), emailTableBeanArrayList);
                                downLoadDialog.dismiss();
                            } else {
                                ToastUtil.showToast("请输入正确的邮箱格式");
                            }

                        }

                    }

                }

            }
        });

        tvDialogEditCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downLoadDialog.dismiss();
            }
        });
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(w * 4 / 5, h * 4 / 25);

        downLoadDialog = new Dialog(this, R.style.CommonDialog);
        downLoadDialog.addContentView(view, params);
        downLoadDialog.setCanceledOnTouchOutside(false);
        downLoadDialog.setCancelable(false);
        downLoadDialog.show();


    }

    /**
     * @param email                   邮箱
     * @param emailTableBeanArrayList 上传的桌台list
     * @author fenghao
     * @date 2018/7/30 0030 下午 16:55
     * @desc 打包下载接口
     */
    private void downLoad(String email, ArrayList<DeletTableBean> emailTableBeanArrayList) {
        Map<String, Object> emailMap = new HashMap<>();
        emailMap.put("toUser", email);
        emailMap.put("title", "桌台二维码打包下载");
        emailMap.put("msg", "桌台二维码打包下载");
        emailMap.put("shopTableJson", emailTableBeanArrayList);
        Gson gson = new Gson();

        //TODO 打包下载接口
        HttpFactory.postString(ApiConfig.DOWN_SHOP_TABLE, gson.toJson(emailMap)
                , new ResponseCallback<BaseBean>(this) {
                    @Override
                    public void onResponse(BaseBean response, int id) {
                        if (response.isSuccess()) {
                            ToastUtil.showToast("发送成功");
                        } else {
                            ToastUtil.showToast(response.getMsg());
                        }
                    }
                });
    }

    /**
     * @author fenghao
     * @date 2018/7/18 0018 下午 12:30
     * @desc 询问用户是否删除
     */
    private void showDeletListTableDialog() {
        final ArrayList<DeletTableBean> deletTableBeanArrayList = new ArrayList<>();

        deletDialog = new Dialog(this, R.style.CommonDialog);
        View view = LayoutInflater.from(this).inflate(R.layout.module_alert_dialog, null);
        TextView tvAlertDialogContent = view.findViewById(R.id.tv_alert_dialog_content);
        TextView tvAlertDialogCancle = view.findViewById(R.id.tv_alert_dialog_cancle);
        TextView tvAlertDialogYes = view.findViewById(R.id.tv_alert_dialog_yes);
        for (int w = 0; w < res.size(); w++) {
            if (res.get(w).getItemType() == TableAdapter.TYPE_TABLE_CONTENT) {
                TableListContentBean tableListContentBean = (TableListContentBean) res.get(w);
                //根据条目数形判断有没有被选中
                if (tableListContentBean.isSelectFlag()) {
                    DeletTableBean deletTableBean = new DeletTableBean(tableListContentBean.getLogGid());
                    deletTableBeanArrayList.add(deletTableBean);
                }
            }

        }
        tvAlertDialogContent.setText("您确定要删除这" + deletTableBeanArrayList.size() + "个桌台吗");
        tvAlertDialogCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletDialog.dismiss();
                deletTableBeanArrayList.clear();
            }
        });
        tvAlertDialogYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gson gson = new Gson();
                //走删除接口
                toDeletTable(gson.toJson(deletTableBeanArrayList));
            }
        });

        Display display = this.getWindowManager().getDefaultDisplay();
        int w = display.getWidth();
        int h = display.getHeight();

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(w * 4 / 5, h / 6);
        deletDialog.addContentView(view, params);
        deletDialog.setCanceledOnTouchOutside(false);
        deletDialog.setCancelable(false);
        if (deletTableBeanArrayList.size() > 0) {
            deletDialog.show();
        } else {
            ToastUtil.showToast("请先选择要删除的桌台");
        }

    }

    /**
     * @param json
     * @author fenghao
     * @date 2018/7/18 0018 下午 14:48
     * @desc 删除桌台接口
     */
    private void toDeletTable(String json) {
        HttpFactory.postString(ApiConfig.BATCH_DELETE_TABLE
                , json, new ResponseCallback<BaseBean>(this) {
                    @Override
                    public void onResponse(BaseBean response, int id) {
                        if (response.isSuccess()) {
                            ToastUtil.showToast("删除成功");
                            getTableList();
                            deletDialog.dismiss();
                        } else {
                            ToastUtil.showToast(response.getMsg());
                        }
                    }
                });
    }

    /**
     * @author fenghao
     * @date 2018/7/18 0018 下午 15:21
     * @desc //弹框批量添加
     */
    private void showAddTableDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.module_dialog_add_zhuotai, null);
        //规格
        final EditText etDialogZhuotaiguige = view.findViewById(R.id.et_dialog_zhuotaiguige);
        //数量
        final EditText etDialogZhuotaishuliang = view.findViewById(R.id.et_dialog_zhuotaishuliang);
        //取消
        TextView tvDialogAddzhuotaiCancle = view.findViewById(R.id.tv_dialog_addzhuotai_cancle);
        //确定
        TextView tvDialogAddzhuotaiYes = view.findViewById(R.id.tv_dialog_addzhuotai_yes);
        //下拉框选择区域
        Spinner spDialogZhuotairegion = view.findViewById(R.id.sp_dialog_zhuotairegion);
        //给下拉框添加数据  并绑定点击事件
        getRegionToSp(spDialogZhuotairegion);

        //设置点击事件
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
                addListTable(regionNumber, etDialogZhuotaiguige.getText().toString(), etDialogZhuotaishuliang.getText().toString());
            }
        });

        dialog = new Dialog(this, R.style.CommonDialog);

        Display display = getWindowManager().getDefaultDisplay();
        int w = display.getWidth();
        int h = display.getHeight();

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(w * 4 / 5, h * 5 / 16);
        dialog.addContentView(view, params);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }

    /**
     * @param regionNumber 区域编号
     * @param pedestal     规格
     * @param count        数量
     * @author fenghao
     * @date 2018/7/18 0018 上午 10:19
     * @desc 走批量添加接口
     */
    private void addListTable(String regionNumber, String pedestal, String count) {
        if (!TextUtils.isEmpty(pedestal)) {
            if (!TextUtils.isEmpty(count)) {
                Map<String, Object> addListTableMap = new HashMap<>();
                addListTableMap.put("shopNumber", getShopNumber());
                addListTableMap.put("regionNumber", regionNumber);
                addListTableMap.put("pedestal", Integer.valueOf(pedestal));
                addListTableMap.put("count", Integer.valueOf(count));
                Gson gson = new Gson();
                HttpFactory.postString(ApiConfig.BATCH_ADD_TABLE
                        , gson.toJson(addListTableMap)
                        , new ResponseCallback<BaseBean>(this) {
                            @Override
                            public void onResponse(BaseBean response, int id) {
                                if (response.isSuccess()) {
                                    getTableList();
                                    dialog.dismiss();
                                    ToastUtil.showToast("批量添加成功");
                                } else {
                                    ToastUtil.showToast(response.getMsg());
                                }
                            }
                        });
            } else {
                ToastUtil.showToast("请添加桌台数量");
            }
        } else {
            ToastUtil.showToast("请添加桌台规格");
        }
    }

    /**
     * @author fenghao
     * @date 2018/7/18 0018 上午 9:53
     * @param spDialogZhuotairegion 下拉框对象
     * @desc 给下拉框添加数据 并绑定点击事件
     */

    //下拉框数据
    private List<String> spList;
    //下拉框适配器
    private ArrayAdapter<String> arrAdapter;

    private void getRegionToSp(final Spinner spDialogZhuotairegion) {
        //查询区域接口
        HttpFactory.get().url(ApiConfig.GET_REGION_LIST)
                .addParams("shopNumber", getShopNumber())
                .build().execute(new ResponseCallback<RegionListBean>(this) {
            @Override
            public void onResponse(RegionListBean response, int id) {
                if (response.isSuccess()) {
                    regionDataBeanList = response.getData();
                    if (regionDataBeanList.size() > 0) {
                        regionDataBeanList = response.getData();
                        //默认区域
                        regionNumber = regionDataBeanList.get(0).getRegionNumber();

                        //数据
                        spList = new ArrayList<String>();
                        for (int i = 0; i < regionDataBeanList.size(); i++) {
                            spList.add(regionDataBeanList.get(i).getRegionName());
                        }
                        //适配器
                        arrAdapter = new ArrayAdapter<String>(TableActivity.this, android.R.layout.simple_spinner_item, spList);
                        //设置样式
                        arrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //加载适配器
                        spDialogZhuotairegion.setAdapter(arrAdapter);

                        spDialogZhuotairegion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                regionNumber = regionDataBeanList.get(i).getRegionNumber();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {
                                regionNumber = regionDataBeanList.get(0).getRegionNumber();
                            }
                        });
                    } else {
                        ToastUtil.showToast("请先添加区域");
                        dialog.dismiss();
                    }

                } else {
                    ToastUtil.showToast("查询失败");
                }
            }

        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getTableList();
    }

    //===============================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }


}
