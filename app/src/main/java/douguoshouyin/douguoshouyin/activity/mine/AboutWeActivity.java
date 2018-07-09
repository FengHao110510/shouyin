package douguoshouyin.douguoshouyin.activity.mine;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import douguoshouyin.douguoshouyin.R;
import douguoshouyin.douguoshouyin.base.BaseActivity;
import douguoshouyin.douguoshouyin.http.Apiconfig;
import douguoshouyin.douguoshouyin.http.HttpFactory;
import douguoshouyin.douguoshouyin.tool.Global;
import okhttp3.Call;

public class AboutWeActivity extends BaseActivity {


    @BindView(R.id.tv_mine_aboutwe_version)
    TextView tvMineAboutweVersion;
    @BindView(R.id.tv_mine_aboutwe_check)
    Button tvMineAboutweCheck;

    @Override
    public int intiLayout() {
        return R.layout.module_activity_mine_about_we;
    }

    @Override
    protected void init() {
        initView();
        initData();
        initBack();
        initTitle("关于我们");
    }

    @Override
    public void initView() {

            tvMineAboutweVersion.setText("版本号V "+ Global.getVersionName());

    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.tv_mine_aboutwe_check)
    public void onViewClicked() {
        //检查更新  走接口 TODO
        showLoadingDialog();
        HttpFactory.post().url(Apiconfig.checkVersion).addParams("", "").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoadingDialog();
            }

            @Override
            public void onResponse(String response, int id) {

            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
