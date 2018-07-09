package douguoshouyin.douguoshouyin.activity.mine;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import douguoshouyin.douguoshouyin.R;
import douguoshouyin.douguoshouyin.base.BaseActivity;
import douguoshouyin.douguoshouyin.http.Apiconfig;
import douguoshouyin.douguoshouyin.http.HttpFactory;
import douguoshouyin.douguoshouyin.tool.ToastUtil;
import okhttp3.Call;

public class ReplacePasswordActivity extends BaseActivity {


    @BindView(R.id.et_mine_replace_oldpassword)
    EditText etMineReplaceOldpassword;
    @BindView(R.id.et_mine_replace_newpassword)
    EditText etMineReplaceNewpassword;
    @BindView(R.id.et_mine_replace_aginpassword)
    EditText etMineReplaceAginpassword;
    @BindView(R.id.bt_mine_replace_over)
    Button btMineReplaceOver;

    @Override
    public int intiLayout() {
        return R.layout.module_activity_mine_replace_password;
    }

    @Override
    protected void init() {
        initView();
        initData();
        initBack();
        initTitle("修改密码");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.bt_mine_replace_over)
    public void onViewClicked() {
        //修改密码
        over();
    }

    /**
     * 判断密码是否填写
     */
    private void over() {
        if (etMineReplaceOldpassword.getText().toString().equals("")) {
            ToastUtil.showToast("请输入原密码");
        } else if (etMineReplaceNewpassword.getText().toString().equals("")) {
            ToastUtil.showToast("请输入新密码");
        } else if (etMineReplaceAginpassword.getText().toString().equals("")) {
            ToastUtil.showToast("请再次输入密码");
        } else {
            toOver();
        }
    }

    /**
     * 修改完成接口
     */
    private void toOver() {
        showLoadingDialog();
        HttpFactory.post().url(Apiconfig.replacePassword).addParams("", "").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoadingDialog();

            }

            @Override
            public void onResponse(String response, int id) {
                dismissLoadingDialog();
            }
        });
    }


    //==========================================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
