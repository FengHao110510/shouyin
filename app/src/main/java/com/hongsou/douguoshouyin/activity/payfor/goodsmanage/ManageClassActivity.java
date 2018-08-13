package com.hongsou.douguoshouyin.activity.payfor.goodsmanage;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.adapter.CategoryListAdapter;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.http.ResponseCallback;
import com.hongsou.douguoshouyin.javabean.BaseBean;
import com.hongsou.douguoshouyin.javabean.FoodCategoryBean;
import com.hongsou.douguoshouyin.tool.Global;
import com.hongsou.douguoshouyin.tool.ToastUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//管理分类页面
public class ManageClassActivity extends BaseActivity {


    @BindView(R.id.tv_titlebar_right)
    TextView tvTitlebarRight;
    @BindView(R.id.rv_payfor_manageclass_list)
    RecyclerView rvPayforManageclassList;

    //添加弹框
    private Dialog dialog;
    //分类列表数据源
    private List<FoodCategoryBean.DataBean> dataBeanList;
    //分类列表适配器
    CategoryListAdapter categoryListAdapter;

    //区分添加还是编辑 0 add  1 update
    private int addFlag;

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
        //查询商品分类接口
        getCategory();
    }

    /**
     * @author fenghao
     * @date 2018/7/18 0018 下午 15:51
     * @desc 查询菜品分类接口
     */
    private void getCategory() {
        if (dataBeanList != null) {
            dataBeanList.clear();
        }
        HttpFactory.get().url(ApiConfig.GET_CATEGORY)
                .addParams("shopNumber", getShopNumber())
                .build().execute(new ResponseCallback<FoodCategoryBean>(this) {
            @Override
            public void onResponse(FoodCategoryBean response, int id) {
                if (response.isSuccess()) {
                    dataBeanList = response.getData();
                    if (dataBeanList.size() > 0) {
                        showCategoryList(dataBeanList);
                    } else {
                        ToastUtil.showToast("暂无分类，请添加分类");
                    }
                } else {
                    ToastUtil.showToast(response.getMsg());
                }
            }
        });

    }

    /**
     * @param dataBeanList 分类列表数据源
     * @author fenghao
     * @date 2018/7/18 0018 下午 16:09
     * @desc 展示分类列表
     */
    private void showCategoryList(final List<FoodCategoryBean.DataBean> dataBeanList) {
        categoryListAdapter = new CategoryListAdapter(R.layout.module_item_region, dataBeanList);
        rvPayforManageclassList.setAdapter(categoryListAdapter);
        rvPayforManageclassList.setLayoutManager(new LinearLayoutManager(this));

        categoryListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                //获取其他子控件
                TextView tvItemPayforRegionAddDel = (TextView) adapter.getViewByPosition(rvPayforManageclassList, position
                        , R.id.tv_item_payfor_region_add_del);
                ImageView ivItemPayforRegionAddDelIcon = (ImageView) adapter.getViewByPosition(rvPayforManageclassList
                        , position, R.id.iv_item_payfor_region_add_del_icon);
                if (view.getId() == R.id.iv_item_payfor_region_add_icon) {
                    //编辑区域  type分类类型
                    addFlag = 1;
                    showAddCatagoryDialog(addFlag, dataBeanList.get(position).getCategoryNumber());
                } else if (view.getId() == R.id.iv_item_payfor_region_add_del_icon) {
                    //删除icon 点击时隐藏   显示删除汉字
                    tvItemPayforRegionAddDel.setVisibility(View.VISIBLE);
                    ivItemPayforRegionAddDelIcon.setVisibility(View.GONE);
                } else if (view.getId() == R.id.tv_item_payfor_region_add_del) {
                    //走删除接口
                    deleteCategory(dataBeanList.get(position).getCategoryNumber());
                    tvItemPayforRegionAddDel.setVisibility(View.GONE);
                    ivItemPayforRegionAddDelIcon.setVisibility(View.VISIBLE);
                }


            }
        });
    }


    /**
     * @param categoryNumber logGid 唯一标识
     * @author fenghao
     * @date 2018/7/18 0018 下午 16:59
     * @desc 编辑分类接口分类类型 0 单品 1固定套餐  2分组套餐
     */
    private void updateCategory(String categoryNumber, String categoryName) {
        Map<String, Object> updateCategoryMap = new HashMap<>();
        updateCategoryMap.put("categoryNumber", categoryNumber);
        updateCategoryMap.put("categoryName", categoryName);
        updateCategoryMap.put("shopNumber", getShopNumber());
        Gson gson = new Gson();

        HttpFactory.postString(ApiConfig.UPDATE_CATEGORY, gson.toJson(updateCategoryMap)
                , new ResponseCallback<BaseBean>(this) {
                    @Override
                    public void onResponse(BaseBean response, int id) {
                        if (response.isSuccess()) {
                            ToastUtil.showToast("修改成功");
                            getCategory();
                            //强制刷新页面
                            Global.getSpGlobalUtil().setForceState("0");
                            dialog.dismiss();
                        } else {
                            ToastUtil.showToast(response.getMsg());
                        }
                    }
                });
    }

    /**
     * @param categoryNumber
     * @author fenghao
     * @date 2018/7/18 0018 下午 16:49
     * @desc 删除分类
     */
    private void deleteCategory(String categoryNumber) {
        Map<String, Object> deleteCategoryMap = new HashMap<>();
        deleteCategoryMap.put("categoryNumber", categoryNumber);
        deleteCategoryMap.put("shopNumber", getShopNumber());
        Gson gson = new Gson();
        HttpFactory.postString(ApiConfig.DELETE_CATEGORY, gson.toJson(deleteCategoryMap)
                , new ResponseCallback<BaseBean>(this) {
                    @Override
                    public void onResponse(BaseBean response, int id) {
                        if (response.isSuccess()) {
                            ToastUtil.showToast("删除成功");
                            getCategory();
                        } else {
                            ToastUtil.showToast(response.getMsg());
                        }

                    }
                });
    }

    @OnClick(R.id.tv_titlebar_right)
    public void onViewClicked() {
        addFlag = 0;
        //点击添加后弹框
        showAddCatagoryDialog(addFlag, "");
    }

    /**
     * @param addFlag
     * @param categoryNumber
     * @author fenghao
     * @date 2018/7/18 0018 下午 16:04
     * @desc 弹出添加或编辑类别弹框
     */
    private void showAddCatagoryDialog(final int addFlag, final String categoryNumber) {
        dialog = new Dialog(this, R.style.CommonDialog);

        View view = LayoutInflater.from(this).inflate(R.layout.module_dialog_fenlei_text, null);
        TextView tvDialogFenleiCancle = view.findViewById(R.id.tv_dialog_fenlei_cancle);
        TextView tvDialogFenleiYes = view.findViewById(R.id.tv_dialog_fenlei_yes);
        final EditText etDialogFenleiContent = view.findViewById(R.id.et_dialog_fenlei_content);
        TextView tvDialogFenleiContentIcon = view.findViewById(R.id.tv_dialog_fenlei_content_icon);
        Spinner spDialogFenleiDantao = view.findViewById(R.id.sp_dialog_fenlei_dantao);
        Display display = this.getWindowManager().getDefaultDisplay();
        int w = display.getWidth();
        int h = display.getHeight();
        LinearLayout.LayoutParams params;
        //编辑的时候 隐藏选择分类
        if (addFlag==1){
            spDialogFenleiDantao.setVisibility(View.GONE);
            params = new LinearLayout.LayoutParams(w * 4 / 5, h * 1 / 5);
        }else {
            params = new LinearLayout.LayoutParams(w * 4 / 5, h * 2 / 7);
        }
        final int[] type = new int[1];
        //看用户选择的是单品还是套餐
        spDialogFenleiDantao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                type[0] = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                type[0] = 0;
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
                if (!TextUtils.isEmpty(etDialogFenleiContent.getText().toString().trim())) {
                    if (addFlag == 0) {
                        //走添加接口
                        addCategory(etDialogFenleiContent.getText().toString(), type[0]);
                    } else {
                        updateCategory(categoryNumber, etDialogFenleiContent.getText().toString());
                    }

                } else {
                    ToastUtil.showToast("请填写分类名称");
                }

            }
        });

        dialog.addContentView(view, params);

        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();

    }

    /**
     * @param categoryName 分类名称
     * @param categoryType 分类类型
     * @author fenghao
     * @date 2018/7/18 0018 下午 17:10
     * @desc
     */
    private void addCategory(String categoryName, int categoryType) {
        Map<String, Object> addCategoryMap = new HashMap<>();
        addCategoryMap.put("shopNumber", getShopNumber());
        addCategoryMap.put("categoryName", categoryName);
        addCategoryMap.put("categoryType", categoryType);
        Gson gson = new Gson();
        HttpFactory.postString(ApiConfig.ADD_CATEGORY, gson.toJson(addCategoryMap)
                , new ResponseCallback<BaseBean>(this) {
                    @Override
                    public void onResponse(BaseBean response, int id) {
                        if (response.isSuccess()) {
                            ToastUtil.showToast("添加成功");
                            getCategory();
                            dialog.dismiss();
                        } else {
                            ToastUtil.showToast(response.getMsg());
                        }
                    }
                });
    }

    //============================================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
