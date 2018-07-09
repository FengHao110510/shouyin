package douguoshouyin.douguoshouyin.views;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import douguoshouyin.douguoshouyin.R;
import douguoshouyin.douguoshouyin.tool.ToastUtil;


/**
 * 文件描述：new！！com.example.administrator.myapplication.app.views
 * 作者：fh
 * 创建时间：2018/6/13
 * 更改时间：2018/6/13
 * 版本号：1
 * 用途：
 */


public class LoadingDialog extends Dialog {

    private ProgressBar progressBar;
    private TextView tv_msg;

    public LoadingDialog(@NonNull Context context) {
        super(context, R.style.transparent_dialog);
        createLoadingDialog(context);
    }

    protected LoadingDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected LoadingDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    private void createLoadingDialog(final Context context) {
        //得到加载的view
        View v = View.inflate(getContext(), R.layout.module_alertdialog, null);
        //加载布局
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);
        progressBar = (ProgressBar) v.findViewById(R.id.pb_Circle);
        tv_msg = (TextView) v.findViewById(R.id.tv_msg);


        //设置不可通过点击外面区域取消
        setCanceledOnTouchOutside(false);
        setOnCancelListener(new OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                ToastUtil.showToast("加载取消");
            }
        });

        // 设置布局，设为全屏
        setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));



    }

    // 设置加载信息
    public void setMessage(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            tv_msg.setText(msg);
        }

    }
}
