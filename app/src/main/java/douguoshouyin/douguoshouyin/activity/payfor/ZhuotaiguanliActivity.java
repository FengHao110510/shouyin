package douguoshouyin.douguoshouyin.activity.payfor;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import douguoshouyin.douguoshouyin.R;
import douguoshouyin.douguoshouyin.base.BaseActivity;

/**
 * 桌台管理页面
 */
public class ZhuotaiguanliActivity extends BaseActivity {


    @BindView(R.id.tv_payfor_zhuotaiguanli_add)
    TextView tvPayforZhuotaiguanliAdd;
    @BindView(R.id.tv_payfor_zhuotaiguanli_delet)
    TextView tvPayforZhuotaiguanliDelet;

    Dialog dialog;

    @Override
    public int intiLayout() {
        return R.layout.module_activity_payfor_zhuotaiguanli;
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

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.tv_payfor_zhuotaiguanli_add, R.id.tv_payfor_zhuotaiguanli_delet})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_payfor_zhuotaiguanli_add:
                //弹框批量添加
                showAddzhuotaiDialog();
                break;
            case R.id.tv_payfor_zhuotaiguanli_delet:
                //批量删除
                break;
        }
    }

    //弹框批量添加

    private void showAddzhuotaiDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.module_dialog_add_zhuotai, null);

        EditText et_dialog_zhuotaiguige = view.findViewById(R.id.et_dialog_zhuotaiguige);//规格
        EditText et_dialog_zhuotaishuliang = view.findViewById(R.id.et_dialog_zhuotaishuliang);//数量
        TextView tv_dialog_addzhuotai_cancle = view.findViewById(R.id.tv_dialog_addzhuotai_cancle);//
        TextView tv_dialog_addzhuotai_yes = view.findViewById(R.id.tv_dialog_addzhuotai_yes);//

        tv_dialog_addzhuotai_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });
        tv_dialog_addzhuotai_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //成功后走接口上传
                dialog.dismiss();
            }
        });

        dialog = new Dialog(this, R.style.CommonDialog);

        Display display = getWindowManager().getDefaultDisplay();
        int w = display.getWidth();
        int h = display.getHeight();

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(w * 4 / 5, h * 1 / 4);
        dialog.addContentView(view, params);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }

    //===============================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
