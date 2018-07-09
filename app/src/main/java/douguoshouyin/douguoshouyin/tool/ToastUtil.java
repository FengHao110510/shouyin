package douguoshouyin.douguoshouyin.tool;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import douguoshouyin.douguoshouyin.R;
import douguoshouyin.douguoshouyin.base.BaseApplication;


/**
 * 文件描述：new！！com.example.administrator.myapplication.app.tool
 * 作者：fh
 * 创建时间：2018/6/13
 * 更改时间：2018/6/13
 * 版本号：1
 * 用途：toast工具类 可以在子线程直接调用
 */


public class ToastUtil {
    private static ToastUtil.MyToast toast;

    public static Handler mHandler = new Handler(Looper.getMainLooper());

    public static void showToast(Object text) {
        showToast(text, Toast.LENGTH_SHORT);
    }

    //网络连接出错专用
    public static void showToast() {
        showToast("网络连接出错", Toast.LENGTH_SHORT);
    }

    public static void showToast(final Object text, final int duration) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            show(text, duration);
        } else {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    show(text, duration);
                }
            });
        }
    }

    private static void show(Object text, int duration) {
        if (null == text) {
            return;
        }
        if (toast != null) {
            toast.clear();
        }
        if (text instanceof String) {
            toast = ToastUtil.MyToast.makeText(BaseApplication.getApplication(), text.toString(), duration);
        } else if (text instanceof Integer) {
            int id = Integer.parseInt(text.toString());
            toast = ToastUtil.MyToast.makeText(BaseApplication.getApplication(), id, duration);
        }
        toast.show();
    }

    public static class MyToast {
        private Toast mToast;

        private MyToast(Context context, CharSequence text, int duration) {
            View v = LayoutInflater.from(context).inflate(R.layout.module_toast_layout, null);
            TextView textView = (TextView) v.findViewById(R.id.textView1);
            textView.setText(text);
            mToast = new Toast(context);
            mToast.setDuration(duration);
            mToast.setGravity(Gravity.CENTER, 0, 0);
            mToast.setView(v);
        }

        public static MyToast makeText(Context context, Object text, int duration) {
            String str = null;
            if (text instanceof CharSequence) {
                str = (String) text;
            } else if (text instanceof Integer) {
                str = context.getString((Integer) text);
            }
            return new MyToast(context, str, duration);
        }

        public void show() {
            if (mToast != null) {
                mToast.show();
            }
        }

        public void clear() {
            if (mToast != null) {
                mToast.cancel();
                mToast = null;
            }
        }

        public void setGravity(int gravity, int xOffset, int yOffset) {
            if (mToast != null) {
                mToast.setGravity(gravity, xOffset, yOffset);
            }
        }
    }
}
