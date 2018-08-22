package com.hongsou.douguoshouyin.views.keyboard;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hongsou.douguoshouyin.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 虚拟键盘
 */
public class VirtualKeyboardView extends LinearLayout {

    Context context;
    @BindView(R.id.keyboard_1)
    TextView mKeyboard1;
    @BindView(R.id.keyboard_2)
    TextView mKeyboard2;
    @BindView(R.id.keyboard_3)
    TextView mKeyboard3;
    @BindView(R.id.keyboard_4)
    TextView mKeyboard4;
    @BindView(R.id.keyboard_5)
    TextView mKeyboard5;
    @BindView(R.id.keyboard_6)
    TextView mKeyboard6;
    @BindView(R.id.keyboard_delete)
    TextView mKeyboardDelete;
    @BindView(R.id.keyboard_7)
    TextView mKeyboard7;
    @BindView(R.id.keyboard_8)
    TextView mKeyboard8;
    @BindView(R.id.keyboard_9)
    TextView mKeyboard9;
    @BindView(R.id.keyboard_0)
    TextView mKeyboard0;
    @BindView(R.id.keyboard_clean)
    TextView mKeyboardClean;
    @BindView(R.id.keyboard_dot)
    TextView mKeyboardDot;
    @BindView(R.id.keyboard_00)
    TextView mKeyboard00;
    @BindView(R.id.keyboard_submit)
    TextView mKeyboardSubmit;


    public static final int KEYCODE_CLEAR = -1;
    public static final int KEYCODE_DELETE = -2;
    public static final int KEYCODE_SUBMIT = -3;
    public static final int KEYCODE_DOT = -4;
    public static final int KEYCODE_00 = -5;


    private OnKeyboardActionListener mOnKeyboardActionListener;


    public interface OnKeyboardActionListener {
        void onKey(int primaryCode, String val, View v);
    }

    public VirtualKeyboardView(Context context) {
        this(context, null);
    }

    public VirtualKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        View view = View.inflate(context, R.layout.module_layout_virtual_keyboard, null);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        ButterKnife.bind(this, view);
        //必须要，不然不显示控件
        addView(view);
        mKeyboardDelete.setTypeface(Typeface.createFromAsset(context.getAssets(), "iconfont.ttf"));
    }

    public void setOnKeyboardActionListener(OnKeyboardActionListener onKeyboardActionListener) {
        mOnKeyboardActionListener = onKeyboardActionListener;
    }

    @OnClick({R.id.keyboard_1, R.id.keyboard_2, R.id.keyboard_3, R.id.keyboard_4, R.id.keyboard_5, R.id.keyboard_6,
            R.id.keyboard_7, R.id.keyboard_8, R.id.keyboard_9, R.id.keyboard_0, R.id.keyboard_dot, R.id.keyboard_00,
            R.id.keyboard_clean,  R.id.keyboard_delete, R.id.keyboard_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.keyboard_1:
                mOnKeyboardActionListener.onKey(1, mKeyboard1.getText().toString(), mKeyboardSubmit);
                break;
            case R.id.keyboard_2:
                mOnKeyboardActionListener.onKey(2, mKeyboard2.getText().toString(), mKeyboardSubmit);
                break;
            case R.id.keyboard_3:
                mOnKeyboardActionListener.onKey(3, mKeyboard3.getText().toString(), mKeyboardSubmit);
                break;
            case R.id.keyboard_4:
                mOnKeyboardActionListener.onKey(4, mKeyboard4.getText().toString(), mKeyboardSubmit);
                break;
            case R.id.keyboard_5:
                mOnKeyboardActionListener.onKey(5, mKeyboard5.getText().toString(), mKeyboardSubmit);
                break;
            case R.id.keyboard_6:
                mOnKeyboardActionListener.onKey(6, mKeyboard6.getText().toString(), mKeyboardSubmit);
                break;
            case R.id.keyboard_7:
                mOnKeyboardActionListener.onKey(7, mKeyboard7.getText().toString(), mKeyboardSubmit);
                break;
            case R.id.keyboard_8:
                mOnKeyboardActionListener.onKey(8, mKeyboard8.getText().toString(), mKeyboardSubmit);
                break;
            case R.id.keyboard_9:
                mOnKeyboardActionListener.onKey(9, mKeyboard9.getText().toString(), mKeyboardSubmit);
                break;
            case R.id.keyboard_0:
                mOnKeyboardActionListener.onKey(0, mKeyboard0.getText().toString(), mKeyboardSubmit);
                break;
            case R.id.keyboard_dot:
                mOnKeyboardActionListener.onKey(KEYCODE_DOT, mKeyboardDot.getText().toString(), mKeyboardSubmit);
                break;
            case R.id.keyboard_00:
                mOnKeyboardActionListener.onKey(KEYCODE_00, mKeyboard00.getText().toString(), mKeyboardSubmit);
                break;
            case R.id.keyboard_delete:
                mOnKeyboardActionListener.onKey(KEYCODE_DELETE, mKeyboardDelete.getText().toString(), mKeyboardSubmit);
                break;
            case R.id.keyboard_clean:
                mOnKeyboardActionListener.onKey(KEYCODE_CLEAR, mKeyboardClean.getText().toString(), mKeyboardSubmit);
                break;
            case R.id.keyboard_submit:
                mOnKeyboardActionListener.onKey(KEYCODE_SUBMIT, mKeyboardSubmit.getText().toString(), mKeyboardSubmit);
                break;
            default:
                break;
        }
    }
}
