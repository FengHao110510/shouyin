package douguoshouyin.douguoshouyin.activity.mine;

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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import douguoshouyin.douguoshouyin.R;
import douguoshouyin.douguoshouyin.base.BaseActivity;
import douguoshouyin.douguoshouyin.http.Apiconfig;
import douguoshouyin.douguoshouyin.http.HttpFactory;
import douguoshouyin.douguoshouyin.tool.CircleImageView;
import douguoshouyin.douguoshouyin.tool.ToastUtil;
import okhttp3.Call;

/**
 * 门店信息页面
 */
//TODO 添加头像 保存接口  权限没加呢
public class MenDianActivity extends BaseActivity {


    @BindView(R.id.et_mine_mendian_shanghubianhao)
    EditText etMineMendianShanghubianhao;
    @BindView(R.id.et_mine_mendian_mendianmingcheng)
    EditText etMineMendianMendianmingcheng;
    @BindView(R.id.et_mine_mendian_mendiandizhi)
    EditText etMineMendianMendiandizhi;
    @BindView(R.id.et_mine_mendian_xiangxidizhi)
    EditText etMineMendianXiangxidizhi;
    @BindView(R.id.et_mine_mendian_phone)
    EditText etMineMendianPhone;
    @BindView(R.id.rl_mine_mendian_yimafu)
    RelativeLayout rlMineMendianYimafu;
    @BindView(R.id.bt_mine_mendian_save)
    Button btMineMendianSave;
    @BindView(R.id.iv_mine_mendian_camera)
    CircleImageView ivMineMendianCamera;
    @BindView(R.id.ll_mine_mendian)
    LinearLayout llMineMendian;
    private Object ZPerweima;
    PopupWindow mPopupWindow;

    @Override
    public int intiLayout() {
        return R.layout.module_activity_mine_mendian;
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

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.iv_mine_mendian_camera, R.id.rl_mine_mendian_yimafu, R.id.bt_mine_mendian_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_mine_mendian_camera:
                Choice();
                break;
            case R.id.rl_mine_mendian_yimafu:
                //跳转到桌牌一码付页面
                Intent zhuopaiIntent = new Intent(this, ZhuoPaiActivity.class);
                startActivity(zhuopaiIntent);
                break;
            case R.id.bt_mine_mendian_save:
                //保存
                save();
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
                ToastUtil.showToast("拍照");
                openCamera();
            }
        });

        ll_pop_camera_xiangce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
                openPhoto();
                ToastUtil.showToast("相册");
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
        mPopupWindow.showAtLocation(llMineMendian, Gravity.BOTTOM, 0, 0);

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(MenDianActivity.this, 1f);//0.0-1.0
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
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
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

                    File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/");
                    file.mkdirs(); //创建文件夹

                    String fileName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + name;

                    FileOutputStream b = null;

                    try {
                        b = new FileOutputStream(fileName);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);
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
                        showImage(bitmap);
                    } catch (Exception e) {
                        // TODO Auto-generatedcatch block
                        e.printStackTrace();
                    }


                }
                break;
        }


    }

    private void showImage(Bitmap bitmap) {
        ivMineMendianCamera.setImageBitmap(bitmap);

    }


    /**
     * 判断用户是否填写信息
     */
    private void save() {
        if (etMineMendianShanghubianhao.getText().toString().equals("")) {
            ToastUtil.showToast("请输入商户编号");
        } else if (etMineMendianMendianmingcheng.getText().toString().equals("")) {
            ToastUtil.showToast("请输入门店名称");
        } else if (etMineMendianMendiandizhi.getText().toString().equals("")) {
            ToastUtil.showToast("请输入门店地址");
        } else if (etMineMendianXiangxidizhi.getText().toString().equals("")) {
            ToastUtil.showToast("请输入详细地址");
        } else if (etMineMendianPhone.getText().toString().equals("")) {
            ToastUtil.showToast("请输入门店电话");
        } else {
            toSave();
        }
    }

    /**
     * 保存接口
     */
    private void toSave() {
        showLoadingDialog();
        HttpFactory.post().url(Apiconfig.mendianSave).addParams("", "").build().execute(new StringCallback() {
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

    //==================================================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
