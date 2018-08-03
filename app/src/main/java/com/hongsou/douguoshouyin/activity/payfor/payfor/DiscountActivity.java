package com.hongsou.douguoshouyin.activity.payfor.payfor;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.tool.ToastUtil;

/**
 * 打折 减价页面
 */
public class DiscountActivity extends BaseActivity {


    @BindView(R.id.rl_payfor_discount_zhekou)
    RelativeLayout rlPayforDiscountZhekou;
    @BindView(R.id.rl_payfor_discount_youhui)
    RelativeLayout rlPayforDiscountYouhui;

    Dialog dialog;

    @Override
    public int initLayout() {
        return R.layout.module_activity_payfor_discount;
    }

    @Override
    protected void init() {
        initView();
        initData();
        initBack();
        initTitle("选择优惠");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    //点击弹框
    @OnClick({R.id.rl_payfor_discount_zhekou, R.id.rl_payfor_discount_youhui, R.id.tv_payfor_discount_bushiyongyouhui})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_payfor_discount_zhekou:
                showDiscountDialog(true, "自定义折扣", "9折,请输入90");
                break;
            case R.id.rl_payfor_discount_youhui:
                showDiscountDialog(false, "自定义减价", "请输入减价金额");
                break;
            case R.id.tv_payfor_discount_bushiyongyouhui:
                setResult(500);
                finishActivity();
                break;
            default:
                break;
        }
    }

    /**
     * @param b     判断是折扣还是优惠  true 折扣 false减价
     * @param title 头
     * @param hint  hint
     */
    private void showDiscountDialog(final boolean b, String title, String hint) {
        View view = LayoutInflater.from(this).inflate(R.layout.module_dialog_edit, null);
        Display display = this.getWindowManager().getDefaultDisplay();
        int w = display.getWidth();
        int h = display.getHeight();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(w * 4 / 5, h * 2 / 7);

        dialog = new Dialog(this, R.style.CommonDialog);
        dialog.addContentView(view, params);
        dialog.show();

        TextView tv_dialog_edit_title = view.findViewById(R.id.tv_dialog_edit_title);//头
        final TextView et_dialog_edit_content = view.findViewById(R.id.et_dialog_edit_content);//内容
        TextView tv_dialog_edit_cancle = view.findViewById(R.id.tv_dialog_edit_cancle);//取消
        TextView tv_dialog_edit_yes = view.findViewById(R.id.tv_dialog_edit_yes);//确定

        tv_dialog_edit_title.setText(title);
        et_dialog_edit_content.setHint(hint);
        if (b) {
            et_dialog_edit_content.setInputType(InputType.TYPE_CLASS_NUMBER);
        } else {
            et_dialog_edit_content.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }

        et_dialog_edit_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
//                setNum(et_dialog_edit_content.getText().toString(),et_dialog_edit_content);
                String temp = editable.toString();

                int posDot = temp.indexOf(".");
                if (posDot <= 0) {
                    if (temp.length() > 6) {
                        editable.delete(temp.length() - 1, temp.length());
                    }

                } else {
                    if (temp.length() - posDot - 1 > 2) {
                        editable.delete(posDot + 3, posDot + 4);
                    }
                }

            }
        });

        tv_dialog_edit_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        tv_dialog_edit_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                if (b) {

                    if (et_dialog_edit_content.getText().toString().length() < 3 && et_dialog_edit_content.getText().toString().length() > 0
                            && Integer.valueOf(et_dialog_edit_content.getText().toString()) > 0) {
                        float f = (float) (Integer.valueOf(et_dialog_edit_content.getText().toString())) / 10;
                        intent.putExtra("content", f + "");
                        intent.putExtra("zkorxj", true);  //折扣还是现金优惠 true是折扣
                        setResult(RESULT_OK, intent);
                        dialog.dismiss();
                        finishActivity();
                    } else {
                        ToastUtil.showToast("请设置1到99之间的折扣率");
                    }

                } else {
                    if (!et_dialog_edit_content.getText().toString().equals("") && Float.valueOf(et_dialog_edit_content.getText().toString()) < 1000000.00) {

                        intent.putExtra("content", et_dialog_edit_content.getText().toString());
                        intent.putExtra("zkorxj", false);  //折扣还是现金优惠 true是折扣

                        setResult(RESULT_OK, intent);
                        dialog.dismiss();
                        finishActivity();
                    } else {
                        ToastUtil.showToast("请设置0.01到999999.99之间的减价");
                    }
                }
            }
        });


    }

    //设置消费金额
    private void setNum(String s, TextView et_dialog_edit_content) {

        // 限制最多能输入6位整数
        if (s.toString().contains(".")) {
            if (s.toString().indexOf(".") > 6) {
                s = s.toString().subSequence(0, 6) + s.toString().substring(s.toString().indexOf("."));
                et_dialog_edit_content.setText(s);
            }
        } else {
            if (s.toString().length() > 6) {
                s = (String) s.toString().subSequence(0, 6);
                et_dialog_edit_content.setText(s);
            }
        }
        // 判断小数点后只能输入两位
        if (s.toString().contains(".")) {
            if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                s = (String) s.toString().subSequence(0,
                        s.toString().indexOf(".") + 3);
                et_dialog_edit_content.setText(s);
            }
        }
        //如果第一个数字为0，第二个不为点，就不允许输入
        if (s.toString().startsWith("0") && s.toString().trim().length() > 1) {
            if (!s.toString().substring(1, 2).equals(".")) {
                et_dialog_edit_content.setText(s.subSequence(0, 1));
                return;
            }
        }


        et_dialog_edit_content.setText(s);
    }

    //====================================================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
