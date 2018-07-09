package douguoshouyin.douguoshouyin.activity.payfor.shangpinguanli;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import douguoshouyin.douguoshouyin.R;
import douguoshouyin.douguoshouyin.base.BaseActivity;

/**
 * 添加商品中的跳转的商品分类页面
 * 点击item后返回给上一个界面 分类信息 并显示   上个界面的已经输入的东西不能丢失
 */
public class ClassActivity extends BaseActivity {


    @BindView(R.id.rv_payfor_addgoods_class_fenlei)
    RecyclerView rvPayforAddgoodsClassFenlei;

    @Override
    public int intiLayout() {
        return R.layout.module_activity_payfor_addgoods_class;
    }

    @Override
    protected void init() {
        initView();
        initData();
        initBack();
        initTitle("选择分类");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
