package com.hongsou.douguoshouyin.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

/**
 * Created by Administrator on 2018/4/24.
 */

public class CustomPopupWindow {

    private PopupWindow mPopupWindow;
    private View contentview;
    private Context mContext;
    private Builder mBuilder;

    public final int vertical = 1;
    public final int horizontal = 2;
    private int[] location = new int[2];

    public CustomPopupWindow(Builder builder) {
        mBuilder = builder;
        mContext = builder.context;
        contentview = LayoutInflater.from(mContext).inflate(builder.contentviewid, null);
        mPopupWindow =
                new PopupWindow(contentview, builder.width, builder.height, builder.fouse);

        //需要跟 setBackGroundDrawable 结合
        mPopupWindow.setOutsideTouchable(builder.outsidecancel);
        if (!builder.outsidecancel) {
            // false 说明点击外部区域不关闭弹窗， 此时需要以下代码置空，否则不管用
            mPopupWindow.setBackgroundDrawable(null);
        } else {
            mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        mPopupWindow.setAnimationStyle(builder.animstyle);
    }

    /**
     * 监听PopupWindow的关闭
     * @param dismissListener
     */
    public void setOnDismissListener(PopupWindow.OnDismissListener dismissListener){
        if (mPopupWindow != null){
            mPopupWindow.setOnDismissListener(dismissListener);
        }
    }

    /**
     * popup 消失
     */
    public void dismiss() {
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
        }
    }

    /**
     * popup 是否显示
     */
    public boolean isShowing() {
        if (mPopupWindow != null) {
            return mPopupWindow.isShowing();
        }
        return false;
    }

    /**
     * popup 宽
     */
    public int getWidth() {
        if (mPopupWindow != null) {
            return mPopupWindow.getWidth();
        }
        return 0;
    }

    /**
     * popup 高
     */
    public int getHeight() {
        if (mPopupWindow != null) {
            return mPopupWindow.getHeight();
        }
        return 0;
    }

    /**
     * 根据id获取view
     *
     * @param viewid
     * @return
     */
    public View getItemView(int viewid) {
        if (mPopupWindow != null) {
            return this.contentview.findViewById(viewid);
        }
        return null;
    }

    /**
     * 根据父布局，显示位置
     *
     * @param rootviewid
     * @param gravity
     * @param x
     * @param y
     * @return
     */
    public CustomPopupWindow showAtLocation(int rootviewid, int gravity, int x, int y) {
        if (mPopupWindow != null) {
            View rootview = LayoutInflater.from(mContext).inflate(rootviewid, null);
            mPopupWindow.showAtLocation(rootview, gravity, x, y);
        }
        return this;
    }

    /**
     * 根据id获取view ，并显示在该view的位置
     *
     * @param targetviewId
     * @param gravity
     * @param offx
     * @param offy
     * @return
     */
    public CustomPopupWindow showAsLaction(int targetviewId, int gravity, int offx, int offy) {
        if (mPopupWindow != null) {
            View targetview = LayoutInflater.from(mContext).inflate(targetviewId, null);
            mPopupWindow.showAsDropDown(targetview, gravity, offx, offy);
        }
        return this;
    }

    /**
     * 显示在 targetview 的不同位置
     *
     * @param targetview
     * @param gravity
     * @param offx
     * @param offy
     * @return
     */
    public CustomPopupWindow showAsLaction(View targetview, int gravity, int offx, int offy) {
        if (mPopupWindow != null) {
            mPopupWindow.showAsDropDown(targetview, gravity, offx, offy);
        }
        return this;
    }

    public CustomPopupWindow showAsLaction(View targetview, int gravity, int orientation) {
        targetview.getLocationOnScreen(location);  //获取控件的位置坐标
        int width = ((Activity) mContext).getWindowManager().getDefaultDisplay().getWidth();
        int height = ((Activity) mContext).getWindowManager().getDefaultDisplay().getHeight();
        if (mPopupWindow != null) {
            if (orientation == horizontal) {
                // 左右弹出
                if (mPopupWindow.getWidth() > (width - (location[1] + targetview.getMeasuredWidth()))) {
                    // 右边弹出
                    mPopupWindow.showAtLocation(targetview, Gravity.NO_GRAVITY, location[0] + targetview.getWidth(), location[1]);
                } else {
                    // 左边弹出
                    mPopupWindow.showAtLocation(targetview, Gravity.NO_GRAVITY, location[0] - mPopupWindow.getWidth(), location[1]);
                }
            }else {
                if (location[1] > height / 2 + 100) {  //若是控件的y轴位置大于屏幕高度的一半，向上弹出
                    mPopupWindow.showAtLocation(targetview,
                            gravity, (location[0]), location[1] - targetview.getMeasuredHeight());  //显示指定控件的上方
                } else {
                    mPopupWindow.showAsDropDown(targetview, 0, 0);    //显示指定控件的下方
                }
            }
        }
        return this;
    }

    /**
     * @desc 显示在控件上方
     * @anthor lpc
     * @date: 2018/7/19
     * @param v 控件对象
     */
    public void showUp(View v) {
        //获取需要在其上方显示的控件的位置信息
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        //在控件上方显示
        mPopupWindow.showAtLocation(v, Gravity.NO_GRAVITY,
                (location[0] + v.getWidth() / 2) - mPopupWindow.getWidth() / 2, location[1] - mPopupWindow.getHeight());
    }

    /**
     * 根据id设置焦点监听
     *
     * @param viewid
     * @param listener
     */
    public void setOnFocusListener(int viewid, View.OnFocusChangeListener listener) {
        View view = getItemView(viewid);
        view.setOnFocusChangeListener(listener);
    }


    /**
     * builder 类
     */
    public static class Builder {


        private int contentviewid;
        private int width;
        private int height;
        private boolean fouse;
        private boolean outsidecancel;
        private int animstyle;
        private Context context;

        public Builder setContext(Context context) {
            this.context = context;
            return this;
        }


        public Builder setContentView(int contentviewid) {
            this.contentviewid = contentviewid;
            return this;
        }

        public Builder setwidth(int width) {
            this.width = width;
            return this;
        }

        public Builder setheight(int height) {
            this.height = height;
            return this;
        }

        public Builder setFouse(boolean fouse) {
            this.fouse = fouse;
            return this;
        }

        public Builder setOutSideCancel(boolean outsidecancel) {
            this.outsidecancel = outsidecancel;
            return this;
        }

        public Builder setAnimationStyle(int animstyle) {
            this.animstyle = animstyle;
            return this;
        }

        public CustomPopupWindow builder() {
            return new CustomPopupWindow(this);
        }
    }
}
