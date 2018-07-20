package com.hongsou.douguoshouyin.views.keyboard;

import android.content.Context;
import android.os.Build;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.lang.reflect.Method;


public class KeyboardUtil {

    private EditText mEditText;

    private View.OnClickListener mOnClickListener;

    public KeyboardUtil(VirtualKeyboardView keyboardView, EditText editText, View.OnClickListener listener) {
        this.mEditText = editText;
        mOnClickListener = listener;
        keyboardView.setOnKeyboardActionListener(mOnKeyboardActionListener);
    }


    private VirtualKeyboardView.OnKeyboardActionListener mOnKeyboardActionListener = new VirtualKeyboardView.OnKeyboardActionListener() {
        @Override
        public void onKey(int primaryCode, String val, View v) {
            Editable editable = mEditText.getText();
            int start = mEditText.getSelectionStart();
            if (primaryCode == VirtualKeyboardView.KEYCODE_DELETE) {
                // 回退
                if (editable != null && editable.length() > 0) {
                    if (start > 0) {
                        editable.delete(start - 1, start);
                    }
                }
            } else if (primaryCode == VirtualKeyboardView.KEYCODE_CLEAR) {
                // 清空全部
                if (editable != null && editable.length() > 0) {
                    if (start > 0) {
                        editable.delete(0, start);
                    }
                }
            } else if (primaryCode == VirtualKeyboardView.KEYCODE_DOT) {
                if (start > 0) {
                    editable.insert(start, val);
                }
            } else if (primaryCode == VirtualKeyboardView.KEYCODE_SUBMIT) {
                mOnClickListener.onClick(v);
            } else {
                editable.insert(start, val);
            }
        }
    };

    /**
     * 隐藏系统键盘
     *
     * @param editText
     */
    public static void hideSystemSofeKeyboard(Context context, EditText editText) {
        int sdkInt = Build.VERSION.SDK_INT;
        if (sdkInt >= 11) {
            try {
                Class<EditText> cls = EditText.class;
                Method setShowSoftInputOnFocus;
                setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
                setShowSoftInputOnFocus.setAccessible(true);
                setShowSoftInputOnFocus.invoke(editText, false);

            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            editText.setInputType(InputType.TYPE_NULL);
        }
        // 如果软键盘已经显示，则隐藏
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }
}