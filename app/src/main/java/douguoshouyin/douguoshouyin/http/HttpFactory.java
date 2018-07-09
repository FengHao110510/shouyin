package douguoshouyin.douguoshouyin.http;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Map;

import douguoshouyin.douguoshouyin.R;
import douguoshouyin.douguoshouyin.base.BaseActivity;
import douguoshouyin.douguoshouyin.base.BaseFragment;
import douguoshouyin.douguoshouyin.tool.ToastUtil;

import okhttp3.Call;

/**
 * Created by Administrator on 2018/7/2. http
 */

public class HttpFactory {

    private  String url;
    private  Object tag;
    private  Map<String, String> params;


    public HttpFactory(String url, Object tag, Map<String, String> params) {
        this.url = url;
        this.tag = tag;
        this.params = params;
    }

    public static PostHttpBuilder post(){
        return new PostHttpBuilder();
    }

    public static PostHttpBuilder get(){
        return new PostHttpBuilder();
    }

    public void execute(final StringCallback stringCallBack) {
        if (NetWorkStateUtils.isNetConnected()){
            OkHttpUtils.post()
                    .url(url)
                    .params(params)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtil.showToast(R.string.http_error);
                    stringCallBack.onError(call, e, id);
                }

                @Override
                public void onResponse(String response, int id) {
                    stringCallBack.onResponse(response, id);
                }
            });
        }else {
            BaseActivity.dismissLoadingDialog();
            BaseFragment.dismissLoadingDialog();
            ToastUtil.showToast("网络未连接，请检查网络");
        }
    }
}
