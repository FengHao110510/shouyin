package com.hongsou.douguoshouyin.activity.payfor.goodsmanage;

import android.app.Activity;
import android.app.Dialog;
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
import android.view.Display;
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
import com.hongsou.douguoshouyin.adapter.ShopStandarAdapter;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.http.ResponseCallback;
import com.hongsou.douguoshouyin.javabean.BaseBean;
import com.hongsou.douguoshouyin.javabean.ShopStandarList;
import com.hongsou.douguoshouyin.javabean.UpImgBean;
import com.hongsou.douguoshouyin.tool.BitmapUtil;
import com.hongsou.douguoshouyin.tool.ToastUtil;
import com.hongsou.douguoshouyin.views.CustomDatePicker;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * 添加商品
 */
public class AddGoodsActivity extends BaseActivity {


    @BindView(R.id.tv_payfor_addgoods_icon)
    TextView tvPayforAddgoodsIcon;
    @BindView(R.id.et_payfor_addgoods_mingcheng)
    EditText etPayforAddgoodsMingcheng;

    @BindView(R.id.rl_payfor_addgoods_fenlei)
    RelativeLayout rlPayforAddgoodsFenlei;
    @BindView(R.id.rv_payfor_addgoods_guige)
    RecyclerView rvPayforAddgoodsGuige;
    @BindView(R.id.et_payfor_addgoods_danwei)
    EditText etPayforAddgoodsDanwei;
    @BindView(R.id.tv_payfor_addgoods_shifouzaishou)
    TextView tvPayforAddgoodsShifouzaishou;
    @BindView(R.id.tv_titlebar_right)
    TextView tvTitlebarRight;
    @BindView(R.id.tv_payfor_addgoods_fenlei)
    TextView tvPayforAddgoodsFenlei;
    @BindView(R.id.ll_payfor_addgoods_addguige)
    LinearLayout llPayforAddgoodsAddguige;

    //分类类型
    String singleProductType;
    @BindView(R.id.tv_payfor_addgoods_addguige)
    TextView tvPayforAddgoodsAddguige;
    @BindView(R.id.tv_payfor_addgoods_time)
    TextView tvPayforAddgoodsTime;
    @BindView(R.id.rl_payfor_addgoods_time)
    RelativeLayout rlPayforAddgoodsTime;
    @BindView(R.id.ll_payfor_addgoods)
    LinearLayout llPayforAddgoods;
    @BindView(R.id.iv_payfor_addgoods_icon)
    ImageView ivPayforAddgoodsIcon;

    //类型名称
    private String categoryName;
    //在售的状态 默认true
    private boolean zaishouFlag;

    //规格列表
    List<ShopStandarList> shopStandarLists = new ArrayList<>();
    //规格适配器
    ShopStandarAdapter shopStandarAdapter;
    //开始结束时间控制器
    private CustomDatePicker customDatePickerStart, customDatePickerEnd;
    private Dialog dialog;
    //现在时间
    private String now;
    //选择的起始时间
    private String startTime;
    //选择的结束时间
    private String endTime;
    //时间状态 0 自定义  1 不限时间
    private int selling;

    //售卖时间pop
    PopupWindow timePopupWindow;
    //选择图片img pop
    PopupWindow imgPopupWindow;
    //售卖时间类型
    boolean sellingFlag;

    //img号
    String foodProductsPicture;
    //img 文件
    private File file1;

    @Override
    public int initLayout() {
        return R.layout.module_activity_payfor_addgoods;
    }

    @Override
    protected void init() {
        initView();
        initData();
        initBack();
        initTitle("添加商品");
    }

    @Override
    public void initView() {
        setIconFont(new TextView[]{tvPayforAddgoodsIcon, tvPayforAddgoodsAddguige});
        zaishouFlag = true;
        sellingFlag = true;
        selling = 1;
        tvPayforAddgoodsShifouzaishou.setBackground(getResources().getDrawable(R.drawable.imgbt_selector));
        tvTitlebarRight.setVisibility(View.VISIBLE);
        startTime = "";
        endTime = "";
    }

    @Override
    public void initData() {
        shopStandarLists.clear();
        shopStandarLists.add(new ShopStandarList("", ""));
        showStandarList(shopStandarLists);
    }

    /**
     * @param shopStandarLists 数据源
     * @author fenghao
     * @date 2018/7/19 0019 下午 14:30
     * @desc 展示一个添加规格的页面
     */
    private void showStandarList(final List<ShopStandarList> shopStandarLists) {
        shopStandarAdapter = new ShopStandarAdapter(R.layout.module_item_standar, shopStandarLists);
        rvPayforAddgoodsGuige.setAdapter(shopStandarAdapter);
        rvPayforAddgoodsGuige.setLayoutManager(new LinearLayoutManager(this));

        shopStandarAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                //点击删除按钮
                if (view.getId() == R.id.tv_item_standar_del) {
                    if (shopStandarLists.size() > 1) {
                        shopStandarLists.remove(position);
                        shopStandarAdapter.notifyDataSetChanged();
                    } else {
                        ToastUtil.showToast("最少填写一项规格");
                    }

                }
            }
        });
        shopStandarAdapter.setOnTextChangeListener(new ShopStandarAdapter.onTextChangeListener() {
            @Override
            public void onTextChanged(int pos, String standardName, String sell) {
                if (!TextUtils.isEmpty(standardName)) {
                    shopStandarLists.get(pos).setStandardName(standardName);
                }
                if (!TextUtils.isEmpty(sell)) {
                    shopStandarLists.get(pos).setSell(sell);
                }
            }


        });

    }


    @OnClick({R.id.tv_titlebar_right, R.id.tv_payfor_addgoods_icon, R.id.rl_payfor_addgoods_fenlei
            , R.id.tv_payfor_addgoods_shifouzaishou, R.id.ll_payfor_addgoods_addguige,
            R.id.rl_payfor_addgoods_time, R.id.iv_payfor_addgoods_icon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_payfor_addgoods_icon:
                //显示图片并保存图片
                showImg();
                break;
            case R.id.iv_payfor_addgoods_icon:
                //显示图片并保存图片
                showImg();
                break;
            case R.id.rl_payfor_addgoods_fenlei:
                //跳转到选择分类页面
                Intent classIntent = new Intent(AddGoodsActivity.this, ChooseCategoryActivity.class);
                //1表示在本页面跳转到分类页面的  只显示单品分类
                startActivityForResult(classIntent, 1);
                break;
            case R.id.tv_payfor_addgoods_shifouzaishou:
                //在售状态
                if (zaishouFlag) {
                    tvPayforAddgoodsShifouzaishou.setBackground(getResources().getDrawable(R.drawable.imgbt_nomal));
                    zaishouFlag = false;
                } else {
                    tvPayforAddgoodsShifouzaishou.setBackground(getResources().getDrawable(R.drawable.imgbt_selector));
                    zaishouFlag = true;
                }
                break;
            case R.id.tv_titlebar_right:
                //检查数据是否填写 走上传图片接口  然后走上传数据接口
                upAddGoods();
                break;
            case R.id.ll_payfor_addgoods_addguige:
                //点一下添加一个规格
                if (shopStandarLists.size() < 12) {
                    shopStandarLists.add(new ShopStandarList("", ""));
                    shopStandarAdapter.notifyDataSetChanged();
                }

                break;
            case R.id.rl_payfor_addgoods_time:
                //在售时间
                showPopWindow();
                break;
            default:
                break;
        }
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
                        upAddGoodsMap.put("foodProductsPicture", foodProductsPicture);
                        upAdd(upAddGoodsMap);

                    } else {
                        ToastUtil.showToast("上传图片失败");

                    }
                }
            });
        }

    }

    /**
     * @author fenghao
     * @date 2018/7/20 0020 上午 9:36
     * @desc 整理上传所需数据
     * //走上传接口 把所需信息上传
     * upAddGoods();
     */
    private void upAddGoods() {
        Map<String, Object> upAddGoodsMap = new HashMap<>();
        //店铺编号
        upAddGoodsMap.put("shopNumber", getShopNumber());
        //商品名称
        if (TextUtils.isEmpty(etPayforAddgoodsMingcheng.getText().toString())) {
            ToastUtil.showToast("请输入商品名称");
            return;
        }
        upAddGoodsMap.put("singleProductName", etPayforAddgoodsMingcheng.getText().toString());
        //商品类型编号
        if (TextUtils.isEmpty(singleProductType)) {
            ToastUtil.showToast("请选择商品类型");
            return;
        }
        upAddGoodsMap.put("singleProductType", singleProductType);
        //在售状态0在售 1不在售
        int sellingStatus;
        if (zaishouFlag) {
            sellingStatus = 0;
        } else {
            sellingStatus = 1;
        }
        upAddGoodsMap.put("sellingStatus", sellingStatus);
        //可售卖时间状态 0有时间 1不限时间
        upAddGoodsMap.put("selling", selling);

        //商品单位
        if (TextUtils.isEmpty(etPayforAddgoodsDanwei.getText().toString())) {
            ToastUtil.showToast("请填写商品单位");
            return;
        }
        upAddGoodsMap.put("singleProductUnit", etPayforAddgoodsDanwei.getText().toString());
        //起售份数
        upAddGoodsMap.put("phr", "");
        //起始时间
        upAddGoodsMap.put("startTime", startTime);
        //结束时间
        upAddGoodsMap.put("endTime", endTime);
        //规格列表 判断规格中有没有空值
        boolean isEmpty = false;
        for (int i = 0; i < shopStandarLists.size(); i++) {
            if (TextUtils.isEmpty(shopStandarLists.get(i).getStandardName())) {
                ToastUtil.showToast("规格" + (i + 1) + "的名称没有填写");
                isEmpty = true;
                break;
            }
            if (TextUtils.isEmpty(shopStandarLists.get(i).getSell())) {
                ToastUtil.showToast("规格" + (i + 1) + "的金额没有填写");
                isEmpty = true;
                break;
            }
        }
        if (isEmpty) {
            return;
        }
        upAddGoodsMap.put("shopStandarList", shopStandarLists);

        postImg(upAddGoodsMap);
    }

    /**
     * @param upAddGoodsMap
     * @author fenghao
     * @date 2018/7/20 0020 上午 9:58
     * @desc 添加商品接口
     */
    private void upAdd(Map<String, Object> upAddGoodsMap) {
        Gson gson = new Gson();

        HttpFactory.postString(ApiConfig.ADD_SINGLE_FOOD, gson.toJson(upAddGoodsMap), new ResponseCallback<BaseBean>(this) {
            @Override
            public void onResponse(BaseBean response, int id) {
                if (response.isSuccess()) {
                    ToastUtil.showToast("添加成功");
                    finishActivity();
                } else {
                    ToastUtil.showToast(response.getMsg());

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
                ToastUtil.showToast("拍照");
                openCamera();
            }
        });

        llPopCameraXiangce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgPopupWindow.dismiss();
                openPhoto();
                ToastUtil.showToast("相册");
            }
        });

        int h = this.getWindowManager().getDefaultDisplay().getHeight();
        imgPopupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, h / 5);
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
        imgPopupWindow.showAtLocation(llPayforAddgoods, Gravity.BOTTOM, 0, 0);

        imgPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(AddGoodsActivity.this, 1f);//0.0-1.0
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


    //相机回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 1:
                //获取返回的分类信息
                if (data == null) {
                    break;
                }
                if (TextUtils.isEmpty(data.getStringExtra("singleProductType"))) {
                    break;
                }
                if (TextUtils.isEmpty(data.getStringExtra("categoryName"))) {
                    break;
                }
                singleProductType = data.getStringExtra("singleProductType");
                categoryName = data.getStringExtra("categoryName");
                tvPayforAddgoodsFenlei.setText(categoryName);
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
                        file1 = new File(fileName);
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
                        Bitmap bitmap = BitmapFactory.decodeFile(path);
                        file1 = new File(path);
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
     * @param bitmap
     * @author fenghao
     * @date 2018/7/20 0020 上午 11:16
     * @desc 展示图片
     */
    private void showImage(Bitmap bitmap) {
        ivPayforAddgoodsIcon.setImageBitmap(bitmap);
        ivPayforAddgoodsIcon.setVisibility(View.VISIBLE);
        tvPayforAddgoodsIcon.setVisibility(View.GONE);
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
     * 保存
     */
    private void baocun() {
        showLoadingDialog();
        HttpFactory.post().url(ApiConfig.ADD_SINGLE_FOOD).addParams("", "").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoadingDialog();
            }

            @Override
            public void onResponse(String response, int id) {
                dismissLoadingDialog();
            }
        });
    }

    /**
     * 展示时间筛选dialog
     */
    private void showTimeDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.module_dialog_time_shaixuan, null);
        final TextView tvDialogTimeShaixuanStart = view.findViewById(R.id.tv_dialog_time_shaixuan_start);
        final TextView tvDialogTimeShaixuanEnd = view.findViewById(R.id.tv_dialog_time_shaixuan_end);
        TextView tvDialogTimeShaixuanBt = view.findViewById(R.id.tv_dialog_time_shaixuan_bt);
        TextView tvDialogTimeShaixuanTitle = view.findViewById(R.id.tv_dialog_time_shaixuan_title);
        tvDialogTimeShaixuanTitle.setText("售卖时间");
        tvDialogTimeShaixuanBt.setText("选择");
        tvDialogTimeShaixuanBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //判断起始时间不得大于结束时间
                if (tvDialogTimeShaixuanStart.getText().toString().compareTo(tvDialogTimeShaixuanEnd.getText().toString()) > 0) {
                    ToastUtil.showToast("起始时间不得大于结束时间");
                } else {
                    startTime = tvDialogTimeShaixuanStart.getText().toString();
                    endTime = tvDialogTimeShaixuanEnd.getText().toString();
                    selling = 0;
                    tvPayforAddgoodsTime.setText(startTime + "---" + endTime);

                    dialog.dismiss();
                }

            }
        });
        //设置开始时间
        tvDialogTimeShaixuanStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 日期格式为yyyy-MM-dd HH:mm
                customDatePickerStart.show(tvDialogTimeShaixuanStart.getText().toString());
            }
        });
        //设置结束时间
        tvDialogTimeShaixuanEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 日期格式为yyyy-MM-dd HH:mm
                customDatePickerEnd.show(tvDialogTimeShaixuanEnd.getText().toString());
            }
        });
        //获取当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        now = sdf.format(new Date());
        //初始化起始时间
        initDateStartPicker(now, tvDialogTimeShaixuanStart);
        //初始化结束时间
        initDateEndPicker("2010-01-01 00:00", now, tvDialogTimeShaixuanEnd);

        Display display = this.getWindowManager().getDefaultDisplay();
        int w = display.getWidth();
        int h = display.getHeight();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(w * 7 / 8, h * 5 / 21);

        dialog = new Dialog(this, R.style.TRCommonDialog);
        dialog.setContentView(view, params);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    //endtime 最后期限  初始化开始时间
    private void initDateStartPicker(String endtime, final TextView tv_dialog_time_shaixuan_start) {

        tv_dialog_time_shaixuan_start.setText(endtime);
        customDatePickerStart = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                tv_dialog_time_shaixuan_start.setText(time);
            }
        }, "2010-01-01 00:00", endtime); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePickerStart.showSpecificTime(true); // 显示时和分
        customDatePickerStart.setIsLoop(true); // 允许循环滚动
    }

    //starttime 开始期限  初始化开始时间  endtime 最后期限 现在
    private void initDateEndPicker(String starttime, String endtime, final TextView tv_dialog_time_shaixuan_end) {
        customDatePickerEnd = null;
        tv_dialog_time_shaixuan_end.setText(endtime);
        customDatePickerEnd = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                tv_dialog_time_shaixuan_end.setText(time);
            }
        }, starttime, endtime); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePickerEnd.showSpecificTime(true); // 显示时和分
        customDatePickerEnd.setIsLoop(true); // 允许循环滚动
    }

    /**
     * 弹框选择售卖时间
     */
    private void showPopWindow() {
        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.module_pop_taocanleixing, null);

        RelativeLayout rlPopAddTaocanleixingDanpin = view.findViewById(R.id.rl_pop_add_taocanleixing_danpin);
        RelativeLayout rlPopAddTaocanleixingZuhe = view.findViewById(R.id.rl_pop_add_taocanleixing_zuhe);
        TextView tvPopAddTaocanleixingDanpin = view.findViewById(R.id.tv_pop_add_taocanleixing_danpin);
        TextView tvPopAddTaocanleixingZuhe = view.findViewById(R.id.tv_pop_add_taocanleixing_zuhe);
        TextView tvPopAddTaocanleixingTitle = view.findViewById(R.id.tv_pop_add_taocanleixing_title);
        tvPopAddTaocanleixingTitle.setText("售卖时间");
        tvPopAddTaocanleixingDanpin.setText("不限制售卖时间");
        tvPopAddTaocanleixingZuhe.setText("选择售卖时间");

        TextView tvPopAddTaocanleixingDanpinIcon = view.findViewById(R.id.tv_pop_add_taocanleixing_danpin_icon);
        TextView tvPopAddTaocanleixingZuheIcon = view.findViewById(R.id.tv_pop_add_taocanleixing_zuhe_icon);

        setIconFont(new TextView[]{tvPopAddTaocanleixingDanpinIcon, tvPopAddTaocanleixingZuheIcon});
        //默认是不限时间
        if (sellingFlag) {
            tvPopAddTaocanleixingDanpinIcon.setVisibility(View.VISIBLE);
            tvPopAddTaocanleixingZuheIcon.setVisibility(View.GONE);
        } else {
            tvPopAddTaocanleixingDanpinIcon.setVisibility(View.GONE);
            tvPopAddTaocanleixingZuheIcon.setVisibility(View.VISIBLE);
        }


        rlPopAddTaocanleixingDanpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvPayforAddgoodsTime.setText("不限制售卖时间");
                selling = 1;
                startTime = "";
                endTime = "";
                sellingFlag = true;
                timePopupWindow.dismiss();
            }
        });
        rlPopAddTaocanleixingZuhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimeDialog();
                sellingFlag = false;
                timePopupWindow.dismiss();

            }
        });

        //h popwindow的高度
        int h = this.getWindowManager().getDefaultDisplay().getHeight();
        timePopupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, h / 3);
        // 设置SelectPicPopupWindow弹出窗体动画效果，设置动画，一会会讲解
        timePopupWindow.setAnimationStyle(R.style.et_style);
        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        timePopupWindow.setFocusable(true);
        timePopupWindow.setOutsideTouchable(true);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(getResources().getColor(R.color.black));
        // 设置pop弹出窗体的背景
        timePopupWindow.setBackgroundDrawable(dw);
        backgroundAlpha(this, 0.5f);//0.0-1.0
        timePopupWindow.update();
        timePopupWindow.setBackgroundDrawable(new BitmapDrawable());
        timePopupWindow.showAtLocation(llPayforAddgoods, Gravity.BOTTOM, 0, 0);

        timePopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(AddGoodsActivity.this, 1f);//0.0-1.0
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

    //===================================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
