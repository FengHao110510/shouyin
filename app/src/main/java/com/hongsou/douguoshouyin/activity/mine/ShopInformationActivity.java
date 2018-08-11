package com.hongsou.douguoshouyin.activity.mine;

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
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.http.ResponseCallback;
import com.hongsou.douguoshouyin.javabean.BaseBean;
import com.hongsou.douguoshouyin.javabean.ShopinforBean;
import com.hongsou.douguoshouyin.javabean.UpImgBean;
import com.hongsou.douguoshouyin.tool.BitmapUtil;
import com.hongsou.douguoshouyin.tool.ToastUtil;
import com.hongsou.douguoshouyin.views.CircleImageView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 门店信息页面
 */
//TODO 添加头像 保存接口  权限没加呢
public class ShopInformationActivity extends BaseActivity {


    @BindView(R.id.iv_mine_shopinfor_camera)
    CircleImageView ivMineShopinforCamera;
    @BindView(R.id.et_mine_shopinfor_shanghubianhao)
    TextView etMineShopinforShanghubianhao;
    @BindView(R.id.et_mine_shopinfor_mendianmingcheng)
    EditText etMineShopinforMendianmingcheng;
    @BindView(R.id.et_mine_shopinfor_mendiandizhi)
    TextView etMineShopinforMendiandizhi;
    @BindView(R.id.et_mine_shopinfor_xiangxidizhi)
    EditText etMineShopinforXiangxidizhi;
    @BindView(R.id.et_mine_shopinfor_phone)
    EditText etMineShopinforPhone;
    @BindView(R.id.rl_mine_shopinfor_yimafu)
    RelativeLayout rlMineShopinforYimafu;
    @BindView(R.id.bt_mine_shopinfor_save)
    Button btMineShopinforSave;
    @BindView(R.id.ll_mine_mendian)
    LinearLayout llMineShopinfor;

    PopupWindow mPopupWindow;

    //头像图片url
    private String pictureUrl;
    //img 文件
    private File file;

    //是否点击保存
    private boolean flag= false;

    @Override
    public int initLayout() {
        return R.layout.module_activity_mine_shopinfor;
    }

    @Override
    protected void init() {
        initView();
        initData();
        initBack();
        initTitle("门店信息");
    }

    @Override
    public void initView() {
        getShopinfor();
    }

    /**
     * @author fenghao
     * @date 2018/7/30 0030 上午 11:21
     * @desc 获取商店信息
     */
    private void getShopinfor() {
        HttpFactory.get().url(ApiConfig.GET_SHOP_INFO).addParams("shopNumber", getShopNumber())
                .build().execute(new ResponseCallback<ShopinforBean>(this) {

            @Override
            public void onResponse(ShopinforBean response, int id) {
                if (response.isSuccess()) {
                    etMineShopinforMendianmingcheng.setText(response.getData().getShopName());
                    etMineShopinforShanghubianhao.setText(response.getData().getShopNumber());
                    etMineShopinforMendiandizhi.setText(response.getData().getAddressInfo());
                    etMineShopinforXiangxidizhi.setText(response.getData().getAddress());
                    etMineShopinforPhone.setText(response.getData().getPhone());
                    Glide.with(ShopInformationActivity.this).load(ApiConfig.IMG_URL+response.getData().getAddressLink())
                            .into(ivMineShopinforCamera);
                    pictureUrl = response.getData().getAddressLink();

                } else {
                    ToastUtil.showToast(response.getMsg());
                }
            }
        });
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.iv_mine_shopinfor_camera, R.id.rl_mine_shopinfor_yimafu, R.id.bt_mine_shopinfor_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_mine_shopinfor_camera:
                Choice();
                break;
            case R.id.rl_mine_shopinfor_yimafu:
                //跳转到桌牌一码付页面
                Intent zhuopaiIntent = new Intent(this, ZhuoPaiActivity.class);
                startActivity(zhuopaiIntent);
                break;
            case R.id.bt_mine_shopinfor_save:
                //保存
                save();
                break;
            default:
                break;
        }
    }

    //弹框选择拍照还是从相册中选取
    private void Choice() {
        showPopWindow();
    }

    //弹框
    private void showPopWindow() {

        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.module_pop_camera, null);

        TextView tv_pop_camera_paizhao_icon = view.findViewById(R.id.tv_pop_camera_paizhao_icon);
        TextView tv_pop_camera_xiangce_icon = view.findViewById(R.id.tv_pop_camera_xiangce_icon);

        setIconFont(new TextView[]{tv_pop_camera_paizhao_icon, tv_pop_camera_xiangce_icon});

        LinearLayout ll_pop_camera_paizhao = view.findViewById(R.id.ll_pop_camera_paizhao);
        LinearLayout ll_pop_camera_xiangce = view.findViewById(R.id.ll_pop_camera_xiangce);

        ll_pop_camera_paizhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
                openCamera();
            }
        });

        ll_pop_camera_xiangce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
                openPhoto();
            }
        });

        int h = this.getWindowManager().getDefaultDisplay().getHeight();
        mPopupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, h / 5);
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
        mPopupWindow.showAtLocation(llMineShopinfor, Gravity.BOTTOM, 0, 0);

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(ShopInformationActivity.this, 1f);//0.0-1.0
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

    /**
     * 打开摄像头拍照
     */
    private void openCamera() {
        //跳转到相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }

    /**
     * 打开相册
     */
    private void openPhoto() {
        //在这里跳转到手机系统相册里面
        Intent intent = new Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);
    }

    //相机回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == Activity.RESULT_OK) {
                    String sdStatus = Environment.getExternalStorageState();

                    if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
                        System.out.println(" ------------- sd card is not avaiable ---------------");
                        return;
                    }
                    String name = "photo.jpg";

                    Bundle bundle = data.getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");

                    File file1 = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/");
                    file1.mkdirs(); //创建文件夹

                    String fileName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + name;

                    FileOutputStream b = null;

                    try {
                        b = new FileOutputStream(fileName);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);
                        Bitmap comp = BitmapUtil.comp(bitmap);
                        file = BitmapUtil.saveBitmapFile(comp,fileName);

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
            case 2:
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
                        Bitmap comp = BitmapUtil.comp(bitmap);
                        file = BitmapUtil.saveBitmapFile(comp,path);

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

    private void showImage(Bitmap bitmap) {
        ivMineShopinforCamera.setImageBitmap(bitmap);
    }

    /**
     * @author fenghao
     * @date 2018/7/19 0019 下午 18:31
     * @desc 上传图片
     */
    private void postImg() {

        showLoadingDialog();
        Log.e(TAG, "postImg: "+file.length() );
        OkHttpUtils.post().addFile("foodImg", file.getName(), file)
                .url(ApiConfig.UPLOAD_IMG).addHeader("Content-Type", "multipart/form-data")
                .build().execute(new StringCallback() {
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
                    pictureUrl = upImgBean.getData().getFoodProductsPicture();
                    toSave();
                    //图片编号
                } else {
                    ToastUtil.showToast("上传图片失败");

                }
            }
        });
    }


    /**
     * 判断用户是否填写信息
     */
    private void save() {
        if (etMineShopinforShanghubianhao.getText().toString().equals("")) {
            ToastUtil.showToast("请输入商户编号");
            return;
        }
        if (etMineShopinforMendianmingcheng.getText().toString().equals("")) {
            ToastUtil.showToast("请输入门店名称");
            return;
        }
        if (etMineShopinforMendiandizhi.getText().toString().equals("")) {
            ToastUtil.showToast("请输入门店地址");
            return;
        }
        if (etMineShopinforXiangxidizhi.getText().toString().equals("")) {
            ToastUtil.showToast("请输入详细地址");
            return;
        }
        if (etMineShopinforPhone.getText().toString().length()!=11){
                ToastUtil.showToast("请输入正确门店电话");
                return;
            }
        if (file != null) {
            postImg();
            Log.e(TAG, "save: asdad   postImg();" );
        }else {
            toSave();
        }
    }

    /**
     * 保存接口
     */
    private void toSave() {
        HttpFactory.post().url(ApiConfig.UPDATE_SHOP_INFO)
                .addParams("shopNumber", getShopNumber())
                .addParams("shopName", etMineShopinforMendianmingcheng.getText().toString())
                .addParams("address", etMineShopinforXiangxidizhi.getText().toString())
                .addParams("phone", etMineShopinforPhone.getText().toString())
                .addParams("addressLink", pictureUrl)
                .build().execute(new ResponseCallback<BaseBean>(this) {

            @Override
            public void onResponse(BaseBean response, int id) {
                if (response.isSuccess()) {
                    ToastUtil.showToast("保存成功");
                    flag = true;
                } else {
                    ToastUtil.showToast(response.getMsg());
                }
            }
        });
    }

    @Override
    public void initBack() {
        TextView finish_back = findViewById(R.id.tv_titlebar_finish_back);
        finish_back.setTypeface(typeface);
        finish_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settoResult();
            }
        });
    }

    @Override
    public void onBackPressed() {
        settoResult();
    }

    private void settoResult() {
        Intent reIntent = new Intent();
        reIntent.putExtra("flag",flag);
        setResult(1,reIntent);
        finishActivity();

    }

    //==================================================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
