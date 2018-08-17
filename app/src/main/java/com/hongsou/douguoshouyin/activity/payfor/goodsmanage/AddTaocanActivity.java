package com.hongsou.douguoshouyin.activity.payfor.goodsmanage;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.adapter.AddTaocanSinglefoodAdapter;
import com.hongsou.douguoshouyin.adapter.ChooseCategoryAdapter;
import com.hongsou.douguoshouyin.adapter.GroupAdapter;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.http.ResponseCallback;
import com.hongsou.douguoshouyin.javabean.BaseBean;
import com.hongsou.douguoshouyin.javabean.FoodCategoryBean;
import com.hongsou.douguoshouyin.javabean.GroupBean;
import com.hongsou.douguoshouyin.javabean.GroupContentBean;
import com.hongsou.douguoshouyin.javabean.GroupListUpBean;
import com.hongsou.douguoshouyin.javabean.GroupMultiItemEntity;
import com.hongsou.douguoshouyin.javabean.GroupTitleBean;
import com.hongsou.douguoshouyin.javabean.SaomahaoBean;
import com.hongsou.douguoshouyin.javabean.SingleFoodsBean;
import com.hongsou.douguoshouyin.javabean.UpImgBean;
import com.hongsou.douguoshouyin.tool.BitmapUtil;
import com.hongsou.douguoshouyin.tool.Global;
import com.hongsou.douguoshouyin.tool.ToastUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 添加套餐页面
 */
public class AddTaocanActivity extends BaseActivity {

    @BindView(R.id.tv_payfor_addtaocan_icon)
    TextView tvPayforAddtaocanIcon;
    @BindView(R.id.et_payfor_addtaocan_mingcheng)
    EditText etPayforAddtaocanMingcheng;
    @BindView(R.id.et_payfor_addtaocan_jine)
    EditText etPayforAddtaocanJine;
    @BindView(R.id.rl_payfor_addtaocan_fenlei)
    RelativeLayout rlPayforAddtaocanFenlei;

    @BindView(R.id.et_payfor_addtaocan_danwei)
    EditText etPayforAddtaocanDanwei;
    @BindView(R.id.tv_payfor_addtaocan_shifouzaishou)
    TextView tvPayforAddtaocanShifouzaishou;
    @BindView(R.id.tv_payfor_addtaocan_fenlei)
    TextView tvPayforAddtaocanFenlei;
    @BindView(R.id.tv_payfor_addtaocan_leixing)
    TextView tvPayforAddtaocanLeixing;
    @BindView(R.id.rl_payfor_addtaocan_taocanleixing)
    RelativeLayout rlPayforAddtaocanTaocanleixing;
    @BindView(R.id.rv_payfor_addtaocan_zuhe_fenzu)
    RecyclerView rvPayforAddtaocanZuheFenzu;
    @BindView(R.id.tv_payfor_addtaocan_zuhe_addfenzu)
    TextView tvPayforAddtaocanZuheAddfenzu;
    @BindView(R.id.ll_payfor_addtaocan_zuhe)
    LinearLayout llPayforAddtaocanZuhe;
    @BindView(R.id.rv_payfor_addtaocan_danpin_shangpin)
    RecyclerView rvPayforAddtaocanDanpinShangpin;
    @BindView(R.id.tv_payfor_addtaocan_danpin_addshangpin)
    TextView tvPayforAddtaocanDanpinAddshangpin;
    @BindView(R.id.ll_payfor_addtaocan_danpin_taocan)
    LinearLayout llPayforAddtaocanDanpinTaocan;
    @BindView(R.id.ll_payfor_addtaocan)
    LinearLayout llPayforAddtaocan;
    @BindView(R.id.iv_payfor_addtaocan_icon)
    ImageView ivPayforAddtaocanIcon;
    @BindView(R.id.tv_titlebar_right)
    TextView tvTitlebarRight;
    //在售的状态 默认true
    private boolean zaishouFlag;
    //套餐类型 1 单品套餐  2 组合套餐
    private String taocanleixingFlag;
    //选择套餐pop
    PopupWindow mPopupWindow;
    //选择图片img pop
    PopupWindow imgPopupWindow;
    //选择套餐分类 pop
    PopupWindow categoryPopupWindow;
    //img号
    String foodProductsPicture;
    //img 文件
    private File file1;
    //分类数据源
    List<FoodCategoryBean.DataBean> dataBeanList;
    //适配器
    ChooseCategoryAdapter chooseCategoryAdapter;
    //分类编号
    String packageType;
    //分类名称
    String packageName;
    //从选择商品页面返回的数据
    List<SingleFoodsBean> singleFoodsBeanList;
    //从分组页面返回的数据 处理后
    List<GroupMultiItemEntity> groupMultiItemEntities = new ArrayList<>();
    //从分组页面返回的数据 处理前
    List<GroupBean.DataBean> dataBeanList2;
    /**
     * @return
     */
    @Override
    public int initLayout() {
        return R.layout.module_activity_payfor_addtaocan;
    }

    @Override
    protected void init() {
        initView();
        initData();
        initBack();
        initTitle("添加套餐");
    }

    @Override
    public void initView() {
        setIconFont(new TextView[]{tvPayforAddtaocanIcon});
        zaishouFlag = true;
        tvTitlebarRight.setText("保存");
        tvTitlebarRight.setVisibility(View.VISIBLE);
    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.tv_titlebar_right, R.id.iv_payfor_addtaocan_icon, R.id.tv_payfor_addtaocan_icon,
            R.id.rl_payfor_addtaocan_fenlei,
            R.id.tv_payfor_addtaocan_shifouzaishou,
            R.id.rl_payfor_addtaocan_taocanleixing,
            R.id.tv_payfor_addtaocan_zuhe_addfenzu,
            R.id.tv_payfor_addtaocan_danpin_addshangpin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_titlebar_right:
                //检查该填的数据填写了么  然后保存操作
                if ("1".equals(taocanleixingFlag)) {
                    //固定套餐
                    checkSave();
                } else {
                    //分组套餐
                    checkFenzuSave();
                }
                break;
            case R.id.iv_payfor_addtaocan_icon:
                showImg();
                break;
            case R.id.tv_payfor_addtaocan_icon:
                showImg();
                break;
            case R.id.rl_payfor_addtaocan_fenlei:

                if (TextUtils.isEmpty(taocanleixingFlag)) {
                    ToastUtil.showToast("请先选择套餐类型");
                } else {
                    showCategoryPop();
                }
                break;
            case R.id.rl_payfor_addtaocan_taocanleixing:
                //选择套餐类型  如果是单品套餐 显示添加单品  组合套餐 显示添加分组

                showPopWindow();
                break;
            case R.id.tv_payfor_addtaocan_zuhe_addfenzu:
                //添加分组按钮  跳转添加分组页面
                Intent taocanfenzuIntent = new Intent(this, TaocanfenzuActivity.class);
                taocanfenzuIntent.putExtra("choose", "choose");
                startActivityForResult(taocanfenzuIntent, 5);
                break;
            case R.id.tv_payfor_addtaocan_danpin_addshangpin:
                //添加单品按钮  跳转添加单品页面
                Intent danpinIntent = new Intent(this, AddTaocanFoodsActivity.class);
                startActivityForResult(danpinIntent, 2);
                break;
            case R.id.tv_payfor_addtaocan_shifouzaishou:
                //是否在售状态
                if (zaishouFlag) {
                    zaishouFlag = false;
                    tvPayforAddtaocanShifouzaishou.setBackground(getResources().getDrawable(R.drawable.imgbt_nomal));

                } else {
                    zaishouFlag = true;
                    tvPayforAddtaocanShifouzaishou.setBackground(getResources().getDrawable(R.drawable.imgbt_selector));
                }
                break;
            default:
                break;
        }
    }

    /**
     *  @author  fenghao
     *  @date    2018/7/23 0023 下午 15:40
     *  @desc   检查该填的数据填写了么  然后保存操作
     */
    private void checkFenzuSave() {
        if (file1 == null) {
            ToastUtil.showToast("请上传照片");
            return;
        }
        if (TextUtils.isEmpty(etPayforAddtaocanMingcheng.getText().toString().trim())) {
            ToastUtil.showToast("请填写套餐名称");
            return;
        }
        if (TextUtils.isEmpty(taocanleixingFlag.trim())) {
            ToastUtil.showToast("请先选择套餐类型");
            return;
        }
        if (TextUtils.isEmpty(etPayforAddtaocanJine.getText().toString().trim())) {
            ToastUtil.showToast("请先填写套餐金额");
            return;
        }
        if (TextUtils.isEmpty(packageType)) {
            ToastUtil.showToast("请先选择套餐分类");
            return;
        }
        if (groupMultiItemEntities.size() < 1) {
            ToastUtil.showToast("请先选择套餐中的餐品");
            return;
        }
        if (TextUtils.isEmpty(etPayforAddtaocanDanwei.getText().toString().trim())) {
            ToastUtil.showToast("请先选择套餐单位");
            return;
        }
        if (dataBeanList2==null||dataBeanList2.size()<1){
            ToastUtil.showToast("请选择分组商品");
            return;
        }

        Map<String, Object> upAddGoodsMap = new HashMap<>();
        //店铺编号
        upAddGoodsMap.put("shopNumber", getShopNumber());
        //套餐名称
        upAddGoodsMap.put("groupPackageName", etPayforAddtaocanMingcheng.getText().toString());
        //套餐分类
        upAddGoodsMap.put("groupPackageType", packageType);
        //套餐价格
        upAddGoodsMap.put("groupPackagePrice", etPayforAddtaocanJine.getText().toString());
        //售卖状态
        int sellingStatus;
        if (zaishouFlag){
            sellingStatus=0;
        }else {
            sellingStatus=1;
        }
        upAddGoodsMap.put("sellingStatus", sellingStatus);


        //分组列表编号
        List<GroupListUpBean> s = new ArrayList<>();
        for (int t=0;t<dataBeanList2.size();t++){
            s.add(new GroupListUpBean(dataBeanList2.get(t).getGroupNumber()));
        }
        upAddGoodsMap.put("groupList", s);


        postImg(upAddGoodsMap);
    }

    /**
     * @author fenghao
     * @date 2018/7/21 0021 上午 9:59
     * @desc 检查该填的数据填写了么  然后保存操作
     */
    private void checkSave() {
        if (file1 == null) {
            ToastUtil.showToast("请上传照片");
            return;
        }
        if (TextUtils.isEmpty(etPayforAddtaocanMingcheng.getText().toString().trim())) {
            ToastUtil.showToast("请填写套餐名称");
            return;
        }
        if (TextUtils.isEmpty(taocanleixingFlag)) {
            ToastUtil.showToast("请先选择套餐类型");
            return;
        }
        if (TextUtils.isEmpty(etPayforAddtaocanJine.getText().toString().trim())) {
            ToastUtil.showToast("请先填写套餐金额");
            return;
        }
        if (TextUtils.isEmpty(packageType)) {
            ToastUtil.showToast("请先选择套餐分类");
            return;
        }
        if (singleFoodsBeanList==null||singleFoodsBeanList.size() < 1) {
            ToastUtil.showToast("请先选择套餐中的餐品");
            return;
        }
        if (TextUtils.isEmpty(etPayforAddtaocanDanwei.getText().toString().trim())) {
            ToastUtil.showToast("请先选择套餐单位");
            return;
        }


        Map<String, Object> upAddGoodsMap = new HashMap<>();
        //店铺编号
        upAddGoodsMap.put("shopNumber", getShopNumber());
        //套餐名称
        upAddGoodsMap.put("packageName", etPayforAddtaocanMingcheng.getText().toString());
        //套餐分类
        upAddGoodsMap.put("packageType", packageType);
        //套餐单位
        upAddGoodsMap.put("packageUnit", etPayforAddtaocanDanwei.getText().toString());
        //套餐价格
        upAddGoodsMap.put("packagePrice", etPayforAddtaocanJine.getText().toString());
        //售卖状态
        int sellingStatus;
        if (zaishouFlag){
            sellingStatus=0;
        }else {
            sellingStatus=1;
        }
        upAddGoodsMap.put("sellingStatus", sellingStatus);

        //套餐描述
        upAddGoodsMap.put("foodProductsDescribe", "");
        //备注
        upAddGoodsMap.put("remarks", "");
        //商品列表
        upAddGoodsMap.put("productList", singleFoodsBeanList);


        postImg(upAddGoodsMap);

    }

    /**
     * @param upAddGoodsMap
     * @author fenghao
     * @date 2018/7/19 0019 下午 18:31
     * @desc 上传图片
     */
    private void postImg(final Map<String, Object> upAddGoodsMap) {
        if (file1 == null) {
            ToastUtil.showToast("请先选择商品图片");
        } else {
            showLoadingDialog();
            Log.e(TAG, "postImg: " + file1.getName());

            OkHttpUtils.post().addFile("foodImg", file1.getName(), file1)
                    .url(ApiConfig.UPLOAD_IMG).addHeader("Content-Type", "multipart/form-data").build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    dismissLoadingDialog();
                    ToastUtil.showToast("网络连接出错");
                }

                @Override
                public void onResponse(String response, int id) {
                    dismissLoadingDialog();
                    UpImgBean upImgBean = new Gson().fromJson(response, UpImgBean.class);
                    if (upImgBean.isSuccess()) {
                        foodProductsPicture = upImgBean.getData().getFoodProductsPicture();
                        //图片编号
                        upAddGoodsMap.put("packagePicture", foodProductsPicture);
                        if ("1".equals(taocanleixingFlag)){
                            //单品套餐
                            upAdd(upAddGoodsMap);
                        }else {
                            //组合套餐
                            upZuhe(upAddGoodsMap);
                        }

                    } else {
                        ToastUtil.showToast("上传图片失败");

                    }
                }
            });
        }

    }
    /**
     * @param upAddGoodsMap
     * @author fenghao
     * @date 2018/7/20 0020 上午 9:58
     * @desc 添加组合套餐接口
     */
    private void upZuhe(Map<String, Object> upAddGoodsMap) {
        Gson gson = new Gson();
        HttpFactory.postString(ApiConfig.ADD_GROUP_PACKAGE, gson.toJson(upAddGoodsMap), new ResponseCallback<BaseBean>(this) {
            @Override
            public void onResponse(BaseBean response, int id) {
                if (response.isSuccess()) {
                    ToastUtil.showToast("添加成功");
                    Global.getSpGlobalUtil().setForceState("0");
                    finishActivity();
                } else {
                    ToastUtil.showToast(response.getMsg());
                }
            }
        });
    }

    /**
     * @param upAddGoodsMap
     * @author fenghao
     * @date 2018/7/20 0020 上午 9:58
     * @desc 添加单品套餐接口
     */
    private void upAdd(Map<String, Object> upAddGoodsMap) {
        Gson gson = new Gson();

        HttpFactory.postString(ApiConfig.ADD_PACKAGE, gson.toJson(upAddGoodsMap), new ResponseCallback<BaseBean>(this) {
            @Override
            public void onResponse(BaseBean response, int id) {
                if (response.isSuccess()) {
                    ToastUtil.showToast("添加成功");
                    Global.getSpGlobalUtil().setForceState("0");
                    finishActivity();
                } else {
                    ToastUtil.showToast(response.getMsg());
                }
            }
        });
    }

    /**
     * @author fenghao
     * @date 2018/7/20 0020 上午 11:36
     * @desc 展示分类列表
     */
    private void showCategoryPop() {
        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.module_pop_recyclerview, null);
        RecyclerView rvPopRecyclerviewChooseList = view.findViewById(R.id.rv_pop_recyclerview_choose_list);
        //分类列表数据
        getCategoryList(rvPopRecyclerviewChooseList);
        //h popwindow的高度
        int h = this.getWindowManager().getDefaultDisplay().getHeight();
        categoryPopupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, h / 3);
        // 设置SelectPicPopupWindow弹出窗体动画效果，设置动画，一会会讲解
        categoryPopupWindow.setAnimationStyle(R.style.et_style);
        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        categoryPopupWindow.setFocusable(true);
        categoryPopupWindow.setOutsideTouchable(true);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(getResources().getColor(R.color.black));
        // 设置pop弹出窗体的背景
        categoryPopupWindow.setBackgroundDrawable(dw);
        backgroundAlpha(this, 0.5f);//0.0-1.0
        categoryPopupWindow.update();
        categoryPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        categoryPopupWindow.showAtLocation(llPayforAddtaocan, Gravity.BOTTOM, 0, 0);

        categoryPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(AddTaocanActivity.this, 1f);//0.0-1.0
            }
        });

    }


    /**
     * @param rvPopRecyclerviewChooseList
     * @author fenghao
     * @date 2018/7/19 0019 上午 10:14
     * @desc 获取分类列表
     */
    private void getCategoryList(final RecyclerView rvPopRecyclerviewChooseList) {
        HttpFactory.get().url(ApiConfig.GET_CATEGORY)
                .addParams("shopNumber", getShopNumber())
                .build().execute(new ResponseCallback<FoodCategoryBean>(this) {
            @Override
            public void onResponse(FoodCategoryBean response, int id) {
                if (response.isSuccess()) {
                    dataBeanList = response.getData();
                    if (dataBeanList.size() > 0) {
                        showCategoryList(dataBeanList, rvPopRecyclerviewChooseList);
                    } else {
                        ToastUtil.showToast("暂无分类，请先添加分类");
                    }
                } else {
                    ToastUtil.showToast(response.getMsg());
                }
            }
        });
    }

    /**
     * @param dataBeanList                数据源
     * @param rvPopRecyclerviewChooseList
     * @author fenghao
     * @date 2018/7/19 0019 上午 10:18
     * @desc 展示列表
     */
    private void showCategoryList(final List<FoodCategoryBean.DataBean> dataBeanList, final RecyclerView rvPopRecyclerviewChooseList) {
        for (int k = 0; k < dataBeanList.size(); k++) {
            //为了展示单品套餐 或者组合套餐
            if (!taocanleixingFlag.equals(dataBeanList.get(k).getCategoryType())) {
                dataBeanList.remove(k);
                k--;
            }
        }
        if (dataBeanList.size() < 1) {
            ToastUtil.showToast("暂无套餐分类请添加");
            categoryPopupWindow.dismiss();
            return;
        }
        chooseCategoryAdapter = new ChooseCategoryAdapter(R.layout.module_item_choose_category, dataBeanList);
        rvPopRecyclerviewChooseList.setLayoutManager(new LinearLayoutManager(this));
        rvPopRecyclerviewChooseList.setAdapter(chooseCategoryAdapter);

        chooseCategoryAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //获取其他子控件
                TextView tvItemChooseCategoryYes = null;
                for (int i = 0; i < dataBeanList.size(); i++) {
                    tvItemChooseCategoryYes = (TextView) adapter.getViewByPosition(rvPopRecyclerviewChooseList, i, R.id.tv_item_choose_category_yes);
                    setIconFont(new TextView[]{tvItemChooseCategoryYes});
                    if (i == position) {
                        tvItemChooseCategoryYes.setVisibility(View.VISIBLE);
                        packageType = dataBeanList.get(i).getCategoryNumber();
                        packageName = dataBeanList.get(i).getCategoryName();
                        tvPayforAddtaocanFenlei.setText(packageName.toString());
                        categoryPopupWindow.dismiss();
                        if ("1".equals(taocanleixingFlag)) {
                            llPayforAddtaocanDanpinTaocan.setVisibility(View.VISIBLE);
                            llPayforAddtaocanZuhe.setVisibility(View.GONE);
                        } else {
                            llPayforAddtaocanDanpinTaocan.setVisibility(View.GONE);
                            llPayforAddtaocanZuhe.setVisibility(View.VISIBLE);
                        }

                    } else {
                        tvItemChooseCategoryYes.setVisibility(View.GONE);
                    }
                    Log.e(TAG, "onItemClick: " + i);
                }

            }
        });
    }

    /**
     * @author fenghao
     * @date 2018/7/19 0019 下午 17:54
     * @desc 展示图片弹框
     */
    private void showImg() {
        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.module_pop_camera, null);

        TextView tvPopCameraPaizhaoIcon = view.findViewById(R.id.tv_pop_camera_paizhao_icon);
        TextView tvPopCameraXiangceIcon = view.findViewById(R.id.tv_pop_camera_xiangce_icon);

        setIconFont(new TextView[]{tvPopCameraPaizhaoIcon, tvPopCameraXiangceIcon});

        LinearLayout llPopCameraPaizhao = view.findViewById(R.id.ll_pop_camera_paizhao);
        LinearLayout llPopCameraXiangce = view.findViewById(R.id.ll_pop_camera_xiangce);

        llPopCameraPaizhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgPopupWindow.dismiss();
                openCamera();
            }
        });

        llPopCameraXiangce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgPopupWindow.dismiss();
                openPhoto();
            }
        });

        //h popwindow的高度
        int h = this.getWindowManager().getDefaultDisplay().getHeight();
        imgPopupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, h / 4);
        // 设置SelectPicPopupWindow弹出窗体动画效果，设置动画，一会会讲解
        imgPopupWindow.setAnimationStyle(R.style.et_style);
        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        imgPopupWindow.setFocusable(true);
        imgPopupWindow.setOutsideTouchable(true);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(getResources().getColor(R.color.black));
        // 设置pop弹出窗体的背景
        imgPopupWindow.setBackgroundDrawable(dw);
        backgroundAlpha(this, 0.5f);//0.0-1.0
        imgPopupWindow.update();
        imgPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        imgPopupWindow.showAtLocation(llPayforAddtaocan, Gravity.BOTTOM, 0, 0);

        imgPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(AddTaocanActivity.this, 1f);//0.0-1.0
            }
        });
    }

    /**
     * 打开摄像头拍照
     */
    private void openCamera() {
        //跳转到相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 10);
    }

    /**
     * 打开相册
     */
    private void openPhoto() {
        //在这里跳转到手机系统相册里面
        Intent intent = new Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 20);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 2:
                //从选择商品页面返回的数据singleFoodsBeanList
                if (singleFoodsBeanList != null) {
                    singleFoodsBeanList.clear();
                }
                singleFoodsBeanList = (List<SingleFoodsBean>) data.getSerializableExtra("singleFoodsBeanList");
                Log.e(TAG, "onActivityResult: " + singleFoodsBeanList.size());
                setSingleFoodsBeanList(singleFoodsBeanList);
                break;
            case 5:
                //从选择选择分组页面返回的数据
                if (groupMultiItemEntities != null) {
                    groupMultiItemEntities.clear();
                }
//                groupMultiItemEntities = (List<GroupMultiItemEntity>) data.getSerializableExtra
//                        ("groupMultiItemEntities");
                dataBeanList2 = (List<GroupBean.DataBean>) data.getSerializableExtra
                        ("dataBeanList2");
                showGroupList(setData(dataBeanList2));
                Log.e(TAG, "onActivityResult: " + dataBeanList2.size());
                break;
            case 10:
                if (resultCode == Activity.RESULT_OK) {
                    String sdStatus = Environment.getExternalStorageState();
                    if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
                        System.out.println(" ------------- sd card is not avaiable ---------------");
                        return;
                    }
                    String name = "photo.jpg";
                    Bundle bundle = data.getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");

                    File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/");
                    file.mkdirs(); //创建文件夹

                    String fileName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + name;

                    FileOutputStream b = null;

                    try {
                        b = new FileOutputStream(fileName);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);
                        Bitmap comp = BitmapUtil.comp(bitmap);
                        file1 = BitmapUtil.saveBitmapFile(comp,fileName);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            b.flush();
                            b.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    showImage(bitmap);
                }
                break;
            case 20:
                //这里的requestCode是我自己设置的，就是确定返回到那个Activity的标志
                if (resultCode == RESULT_OK) {//resultcode是setResult里面设置的code值
                    try {
                        Uri selectedImage = data.getData(); //获取系统返回的照片的Uri
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        Cursor cursor = getContentResolver().query(selectedImage,
                                filePathColumn, null, null, null);//从系统表中查询指定Uri对应的照片
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        String path = cursor.getString(columnIndex);  //获取照片路径
                        cursor.close();
                        String fileName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "www.jpg";

                        Bitmap bitmap = BitmapFactory.decodeFile(path);
                        Bitmap comp = BitmapUtil.comp(bitmap);
                        file1 = BitmapUtil.saveBitmapFile(comp,fileName);
                        showImage(bitmap);
                    } catch (Exception e) {
                        // TODO Auto-generatedcatch block
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }
    /**
     * @param dataBeanList
     * @author fenghao
     * @date 2018/7/21 0021 下午 16:11
     * @desc 设置数据
     */
    private List<GroupMultiItemEntity> setData(List<GroupBean.DataBean> dataBeanList) {
        if (groupMultiItemEntities!=null){
            groupMultiItemEntities.clear();
        }

        List<GroupBean.DataBean.ProductListBean> productListBeanList;
        for (int i = 0; i < dataBeanList.size(); i++) {
            groupMultiItemEntities.add(new GroupMultiItemEntity(GroupMultiItemEntity.TITLE,
                    new GroupTitleBean(dataBeanList.get(i).getGroupName()
                            , dataBeanList.get(i).getGroupNumber()
                            , dataBeanList.get(i).getProductList().size() + "选" + dataBeanList.get(i).getGroupCount()
                            , false)));
            productListBeanList = dataBeanList.get(i).getProductList();
            for (int k = 0; k < productListBeanList.size(); k++) {
                groupMultiItemEntities.add(new GroupMultiItemEntity(GroupMultiItemEntity.CONTENT,
                        new GroupContentBean(productListBeanList.get(k).getFoodFullName()
                                , productListBeanList.get(k).getFoodProductsCount()
                                , productListBeanList.get(k).getStandard()
                                , productListBeanList.get(k).getMinGroup()
                                , productListBeanList.get(k).getFoodProductsNumber())));
            }
        }
        return groupMultiItemEntities;
    }
    /**
     * @param groupMultiItemEntityList
     * @author fenghao
     * @date 2018/7/21 0021 下午 16:41
     * @desc 展示
     */
    //适配器
    GroupAdapter groupAdapter;
    private void showGroupList(final List<GroupMultiItemEntity> groupMultiItemEntityList) {
        //2 显示删除图标
        groupAdapter = new GroupAdapter(groupMultiItemEntityList, 2);
        rvPayforAddtaocanZuheFenzu.setLayoutManager(new LinearLayoutManager(this));
        rvPayforAddtaocanZuheFenzu.setAdapter(groupAdapter);
        LinearLayout llItemTaocanfenzuShoulistTitleDel;
        ImageView ivItemTaocanfenzuShoulistTitleCheck;

        groupAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    //删除
                    case R.id.ll_item_taocanfenzu_shoulist_title_del:
                        delGroup(groupMultiItemEntityList.get(position).getGroupTitleBean().getGroupNumber());

                        break;
                    case R.id.iv_item_taocanfenzu_shoulist_title_check:
                        ImageView ivItemTaocanfenzuShoulistTitleCheck = (ImageView) groupAdapter.getViewByPosition(rvPayforAddtaocanZuheFenzu, position
                                , R.id.iv_item_taocanfenzu_shoulist_title_check);
                        if (groupMultiItemEntityList.get(position).getGroupTitleBean().isCheck()) {
                            ivItemTaocanfenzuShoulistTitleCheck.setBackground(getResources().getDrawable(R.drawable.weixuan));
                            groupMultiItemEntityList.get(position).getGroupTitleBean().setCheck(false);
                        } else {
                            ivItemTaocanfenzuShoulistTitleCheck.setBackground(getResources().getDrawable(R.drawable.xuanzhong));
                            groupMultiItemEntityList.get(position).getGroupTitleBean().setCheck(true);
                        }
                        break;
                    default:
                        break;
                }

            }
        });
    }
    /**
     * @param groupNumber
     * @author fenghao
     * @date 2018/7/21 0021 下午 16:53
     * @desc 删除分组
     */
    private void delGroup(String groupNumber) {
        for (int i=0;i<dataBeanList2.size();i++){
            if (dataBeanList2.get(i).getGroupNumber().equals(groupNumber)){
                dataBeanList2.remove(i);
            }
        }
        groupMultiItemEntities.clear();
        setData(dataBeanList2);
        groupAdapter.notifyDataSetChanged();
    }

    /**
     * @param singleFoodsBeanList 从选择商品页面传回的数据元
     * @author fenghao
     * @date 2018/7/21 0021 上午 9:20
     * @desc 设置数据
     */
    private void setSingleFoodsBeanList(List<SingleFoodsBean> singleFoodsBeanList) {
        for (int i = 0; i < singleFoodsBeanList.size(); i++) {
            if (singleFoodsBeanList.get(i).getSingleQuantity() == 0) {
                singleFoodsBeanList.remove(i);
                i--;
            }
        }

        showSingleFoods(singleFoodsBeanList);
    }

    AddTaocanSinglefoodAdapter addTaocanSinglefoodAdapter;

    /**
     * @param singleFoodsBeanList 处理后的数据
     * @author fenghao
     * @date 2018/7/21 0021 上午 9:22
     * @desc 展示返回的商品
     */
    private void showSingleFoods(final List<SingleFoodsBean> singleFoodsBeanList) {
        Log.e(TAG, "showSingleFoods: " + singleFoodsBeanList.size());

        addTaocanSinglefoodAdapter = new AddTaocanSinglefoodAdapter(R.layout.module_item_addtaocan_singfoods, singleFoodsBeanList);
        rvPayforAddtaocanDanpinShangpin.setAdapter(addTaocanSinglefoodAdapter);
        rvPayforAddtaocanDanpinShangpin.setLayoutManager(new LinearLayoutManager(this));
        addTaocanSinglefoodAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.tv_item_addtaocan_singlefoods_del) {
                    singleFoodsBeanList.remove(position);
                    addTaocanSinglefoodAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    /**
     * @param bitmap
     * @author fenghao
     * @date 2018/7/20 0020 上午 11:16
     * @desc 展示图片
     */
    private void showImage(Bitmap bitmap) {
        ivPayforAddtaocanIcon.setImageBitmap(bitmap);
        ivPayforAddtaocanIcon.setVisibility(View.VISIBLE);
        tvPayforAddtaocanIcon.setVisibility(View.GONE);
        //将要保存图片的路径
        File file = new File("/mnt/sdcard/pic/01.jpg");
        Bitmap comp = BitmapUtil.comp(bitmap);
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            comp.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 弹框选择套餐类型
     */
    private void showPopWindow() {
        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.module_pop_taocanleixing, null);

        RelativeLayout rlPopAddTaocanleixingDanpin = view.findViewById(R.id.rl_pop_add_taocanleixing_danpin);
        RelativeLayout rlPopAddTaocanleixingZuhe = view.findViewById(R.id.rl_pop_add_taocanleixing_zuhe);

        final TextView tvPopAddTaocanleixingDanpinIcon = view.findViewById(R.id.tv_pop_add_taocanleixing_danpin_icon);
        final TextView tvPopAddTaocanleixingZuheIcon = view.findViewById(R.id.tv_pop_add_taocanleixing_zuhe_icon);

        setIconFont(new TextView[]{tvPopAddTaocanleixingDanpinIcon, tvPopAddTaocanleixingZuheIcon});

        //套餐模式回显
        if (!TextUtils.isEmpty(taocanleixingFlag)) {
            if (taocanleixingFlag.equals("1")) {
                tvPopAddTaocanleixingDanpinIcon.setVisibility(View.VISIBLE);
                tvPopAddTaocanleixingZuheIcon.setVisibility(View.GONE);
            } else {
                tvPopAddTaocanleixingDanpinIcon.setVisibility(View.GONE);
                tvPopAddTaocanleixingZuheIcon.setVisibility(View.VISIBLE);
            }
        }

        rlPopAddTaocanleixingDanpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tvPayforAddtaocanLeixing.setText("单品套餐");
                taocanleixingFlag = "1";
                llPayforAddtaocanZuhe.setVisibility(View.GONE);
                mPopupWindow.dismiss();
            }
        });
        rlPopAddTaocanleixingZuhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvPayforAddtaocanLeixing.setText("组合套餐");
                taocanleixingFlag = "2";
                llPayforAddtaocanDanpinTaocan.setVisibility(View.GONE);
                mPopupWindow.dismiss();

            }
        });
        //h popwindow的高度
        int h = this.getWindowManager().getDefaultDisplay().getHeight();
        mPopupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, h / 3);
        // 设置SelectPicPopupWindow弹出窗体动画效果，设置动画，一会会讲解
        mPopupWindow.setAnimationStyle(R.style.et_style);
        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(getResources().getColor(R.color.black));
        // 设置pop弹出窗体的背景
        mPopupWindow.setBackgroundDrawable(dw);
        backgroundAlpha(this, 0.5f);//0.0-1.0
        mPopupWindow.update();
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.showAtLocation(llPayforAddtaocan, Gravity.BOTTOM, 0, 0);

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(AddTaocanActivity.this, 1f);//0.0-1.0
            }
        });
    }


    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }


    //===============================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
