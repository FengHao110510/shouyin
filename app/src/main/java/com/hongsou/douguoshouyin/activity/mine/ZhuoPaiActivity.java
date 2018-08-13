package com.hongsou.douguoshouyin.activity.mine;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.tool.BitmapUtil;
import com.hongsou.douguoshouyin.tool.ImageUtils;
import com.hongsou.douguoshouyin.tool.ToastUtil;

/**
 * 桌牌一码付页面页面
 */
public class ZhuoPaiActivity extends BaseActivity {


    @BindView(R.id.iv_mine_mendian_zhuopai_erweima)
    ImageView ivMineMendianZhuopaiErweima;
    @BindView(R.id.bt_mine_mendian_zhuopai_save)
    Button btMineMendianZhuopaiSave;
    Bitmap taikaBitmap;

    @Override
    public int initLayout() {
        return R.layout.module_activity_mine_zhuo_pai;
    }

    @Override
    protected void init() {
        initView();
        initData();
        initBack();
        initTitle("一码付");
    }

    @Override
    public void initView() {

        //台卡桌牌二维码
        taikaBitmaps();

    }

    @Override
    public void initData() {

    }



    //初始化台卡图
    private void taikaBitmaps() {
        Bitmap bitmaperwei = BitmapUtil.create2DCoderBitmap("haha"
                , 200, 200);
        Bitmap bitmapbg = BitmapFactory.decodeResource(this.getResources(), R.drawable.taika_bg);
        taikaBitmap = compositeImages(bitmapbg, bitmaperwei);
        ivMineMendianZhuopaiErweima.setImageBitmap(taikaBitmap);
    }

    /**
     * 合成两个图，src为显示范围，dst为显示内容
     *
     * @param srcBitmap
     * @param dstBitmap
     * @return
     */
    public static Bitmap compositeImages(Bitmap srcBitmap, Bitmap dstBitmap) {

        Bitmap bmp = null;
        //下面这个Bitmap中创建的函数就可以创建一个空的Bitmap
        bmp = Bitmap.createBitmap(srcBitmap.getWidth(), srcBitmap.getHeight(), srcBitmap.getConfig());
        Paint paint = new Paint();
        Canvas canvas = new Canvas(bmp);
        //首先绘制第一张图片，很简单，就是和方法中getDstImage一样
        canvas.drawBitmap(srcBitmap, 0, 0, paint);

        //在绘制第二张图片的时候，我们需要指定一个Xfermode
        //这里采用Multiply模式，这个模式是将两张图片的对应的点的像素相乘
        //，再除以255，然后以新的像素来重新绘制显示合成后的图像
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY));
        canvas.drawBitmap(dstBitmap, new Rect(0, 0, dstBitmap.getWidth(), dstBitmap.getHeight()),
                new Rect(srcBitmap.getWidth() * 1 / 5, srcBitmap.getHeight() * 1 / 3, srcBitmap.getWidth() * 4 / 5, srcBitmap.getHeight() * 4 / 5), paint);

        return bmp;
    }

    @OnClick(R.id.bt_mine_mendian_zhuopai_save)
    public void onViewClicked() {
        //将二维码保存到本地  TODO
//        Bitmap bmp = BitmapFactory.decodeResource(this.getResources(), R.drawable.bg);
        if (ImageUtils.saveImageToGallery(this, taikaBitmap)) {
            ToastUtil.showToast("保存成功");
        } else {
            ToastUtil.showToast("保存失败");
        }


    }
    //====================================================================================================================


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
