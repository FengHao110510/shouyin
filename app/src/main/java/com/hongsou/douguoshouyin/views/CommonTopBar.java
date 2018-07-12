package com.hongsou.douguoshouyin.views;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hongsou.douguoshouyin.R;


/**
 * Created by franky814 on 2016/2/20.
 * 通用顶部菜单栏
 */
public class CommonTopBar extends FrameLayout implements View.OnClickListener {

    private View rootView;
    private RelativeLayout rl_layout;
    private LinearLayout ll_right;
    private Context mContext;
    private TextView tv_left;
    private TextView tv_center;
    private TextView tv_right;
    private TextView tv_right_icon;
    private View v_bottomline;

    public interface ClickCallBack {
        void onClick(View v);
    }

    public CommonTopBar(Context context) {
        this(context, null);
    }

    public CommonTopBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommonTopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView(context);
        initAttr(attrs);
    }

    private void initAttr(AttributeSet attrs) {
        TypedArray ta = mContext.obtainStyledAttributes(attrs, R.styleable.CommonTopBar);
        //设置左右中文字是否显示
        setLeftTextVisibility(ta.getInt(R.styleable.CommonTopBar_left_text_visibility, 0));
        setRightTextVisibility(ta.getInt(R.styleable.CommonTopBar_right_text_visibility, 0));
        setCenterTextVisibility(ta.getInt(R.styleable.CommonTopBar_center_text_visibility, 0));
        //设置左右中的文字
        setLeftText(ta.getString(R.styleable.CommonTopBar_left_text));
        setRightText(ta.getString(R.styleable.CommonTopBar_right_text));
        setCenterText(ta.getString(R.styleable.CommonTopBar_center_text));
        //设置左右中文字的颜色
        setLeftTextColor(ta.getResourceId(R.styleable.CommonTopBar_left_text_color, R.color.color_title));
        setRightTextColor(ta.getResourceId(R.styleable.CommonTopBar_right_text_color, R.color.color_title));
        setCenterTextColor(ta.getResourceId(R.styleable.CommonTopBar_center_text_color, R.color.color_title));
        //设置右边的图标的内容分（TypeFace方式）
        setRightIcon(ta.getString(R.styleable.CommonTopBar_right_icon));
        //设置右边的图像是否显示
        setRightIconVisibility(ta.getInt(R.styleable.CommonTopBar_right_img_visibility, 0));
        //设置下部的分割线是否显示
        setBottomLineVisibility(ta.getInt(R.styleable.CommonTopBar_bottom_line_visibility, 0));
        // 设置右边整体内容是否显示
        setRightVisibility(ta.getInt(R.styleable.CommonTopBar_right_view_visibility, 0));
        //设置背景颜色，默认白色
        setLayoutBackground(ta.getResourceId(R.styleable.CommonTopBar_background_color, R.color.color_base_yellow));
        ta.recycle();
    }

    private void initView(Context context) {
        mContext = context;
        rootView = LayoutInflater.from(context).inflate(R.layout.common_top_bar_item, this);
        tv_center = (TextView) rootView.findViewById(R.id.tv_center);

        rl_layout = (RelativeLayout) rootView.findViewById(R.id.rl_layout);
        ll_right = (LinearLayout) rootView.findViewById(R.id.ll_right);
        tv_left = (TextView) rootView.findViewById(R.id.tv_left);
        tv_right_icon = (TextView) rootView.findViewById(R.id.tv_right_icon);
        tv_right = (TextView) rootView.findViewById(R.id.tv_right);
        v_bottomline = rootView.findViewById(R.id.v_bottom_line);

        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "iconfont.ttf");
        tv_left.setTypeface(typeface);
        tv_right_icon.setTypeface(typeface);

        setLeftViewClickListener(new ClickCallBack() {
            @Override
            public void onClick(View v) {
                if (mContext instanceof Activity) {
                    ((Activity) mContext).finish();
                }
            }
        });
    }

    /**
     * 设置控件高度
     */
    public void setLayoutHeight(int height) {
        ViewGroup.LayoutParams params = rl_layout.getLayoutParams();
        params.height = height;
        rl_layout.setLayoutParams(params);
    }

    /**
     * 设置背景：主要是背景颜色
     */
    public void setLayoutBackground(int resid) {
        rl_layout.setBackgroundResource(resid);
    }

    /**
     * 设置左侧文字内容
     */
    public void setLeftText(String str) {
        tv_left.setText(str);
    }

    /**
     * 设置左侧文字颜色
     */
    public void setLeftTextColor(int color) {
        tv_left.setTextColor(color);
    }

    /**
     * 设置左侧文字的显示与隐藏
     */
    public void setLeftTextVisibility(int visibility) {
        tv_left.setVisibility(visibility);
    }

    /**
     * 设置中部文字内容
     */
    public void setCenterText(String str) {
        tv_center.setText(str);
    }

    /**
     * 设置中部文字颜色
     */
    public void setCenterTextColor(int color) {
        tv_center.setTextColor(getResources().getColor(color));
    }

    /**
     * 设置中部文字的显示与隐藏
     */
    public void setCenterTextVisibility(int visibility) {
        tv_center.setVisibility(visibility);
    }


    /**
     * 设置右侧文字内容
     */
    public void setRightText(String str) {
        tv_right.setText(str);
    }

    /**
     * 设置右侧文字颜色
     */
    public void setRightTextColor(int color) {
        tv_right.setTextColor(color);
        tv_right_icon.setTextColor(color);
    }

    /**
     * 设置右侧文字的显示与隐藏
     */
    public void setRightTextVisibility(int visibility) {
        tv_right.setVisibility(visibility);
    }

    /**
     * 设置右侧图片src方式
     */
    public void setRightIcon(String src) {
        tv_right_icon.setText(src);
    }

    /**
     * 设置右侧图片的显示与隐藏
     */
    public void setRightIconVisibility(int visibility) {
        tv_right_icon.setVisibility(visibility);
    }

    /**
     * 设置右侧内容是否显示
     * @param visibility
     */
    public void setRightVisibility(int visibility){
        ll_right.setVisibility(visibility);
    }

    /**
     * 设置下部分割线的显示与隐藏
     */
    public void setBottomLineVisibility(int visibility) {
        v_bottomline.setVisibility(visibility);
    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_left:
                if (mContext != null && mContext instanceof Activity) {
                    ((Activity) mContext).finish();
                }
                break;

        }
    }


    public void setRightViewClickListener(final ClickCallBack rtc) {
        ll_right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rtc != null) {
                    rtc.onClick(v);
                }
            }
        });
    }

    public void setLeftViewClickListener(final ClickCallBack rtc) {
        tv_left.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rtc != null) {
                    rtc.onClick(v);
                }
            }
        });
    }
}
