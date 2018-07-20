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
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.adapter.ChooseCategoryAdapter;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.http.ResponseCallback;
import com.hongsou.douguoshouyin.javabean.FoodCategoryBean;
import com.hongsou.douguoshouyin.tool.BitmapUtil;
import com.hongsou.douguoshouyin.tool.ToastUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    //

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
    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.iv_payfor_addtaocan_icon, R.id.tv_payfor_addtaocan_icon,
            R.id.rl_payfor_addtaocan_fenlei,
            R.id.tv_payfor_addtaocan_shifouzaishou,
            R.id.rl_payfor_addtaocan_taocanleixing,
            R.id.tv_payfor_addtaocan_zuhe_addfenzu,
            R.id.tv_payfor_addtaocan_danpin_addshangpin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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
                //选择套餐类型  如果是单品套餐 显示添加单品  组合套餐 显示添加分组   默认显示添加单人套餐
                showPopWindow();
                break;
            case R.id.tv_payfor_addtaocan_zuhe_addfenzu:
                //添加分组按钮  跳转添加分组页面
                Intent taocanfenzuIntent = new Intent(this, TaocanfenzuActivity.class);
                startActivity(taocanfenzuIntent);
                break;
            case R.id.tv_payfor_addtaocan_danpin_addshangpin:
                //添加单品按钮  跳转添加单品页面
                Intent danpinIntent = new Intent(this, AddTaocanFoodsActivity.class);
                startActivity(danpinIntent);
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
     * @author fenghao
     * @date 2018/7/20 0020 上午 11:36
     * @desc 展示分类列表
     */
    private void showCategoryPop() {
        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.module_pop_recyclerview, null);
        RecyclerView rvPopRecyclerviewChooseList = view.findViewById(R.id.rv_pop_recyclerview_choose_list);
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
        if (dataBeanList.size()<1){
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

        //h popwindow的高度
        int h = this.getWindowManager().getDefaultDisplay().getHeight();
        imgPopupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, h / 3);
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

        TextView tvPopAddTaocanleixingDanpinIcon = view.findViewById(R.id.tv_pop_add_taocanleixing_danpin_icon);
        TextView tvPopAddTaocanleixingZuheIcon = view.findViewById(R.id.tv_pop_add_taocanleixing_zuhe_icon);

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
                llPayforAddtaocanDanpinTaocan.setVisibility(View.VISIBLE);
                llPayforAddtaocanZuhe.setVisibility(View.GONE);
                tvPayforAddtaocanLeixing.setText("单品套餐");
                taocanleixingFlag = "1";
                mPopupWindow.dismiss();
            }
        });
        rlPopAddTaocanleixingZuhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llPayforAddtaocanDanpinTaocan.setVisibility(View.GONE);
                llPayforAddtaocanZuhe.setVisibility(View.VISIBLE);
                tvPayforAddtaocanLeixing.setText("组合套餐");
                taocanleixingFlag = "2";
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
