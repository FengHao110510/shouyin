package douguoshouyin.douguoshouyin.http;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/7/2.
 */

public class PostHttpBuilder {

    protected String url;
    protected Object tag;
    private Map<String,String> params;

    public HttpFactory build()
    {
        return new HttpFactory(url, tag, params);
    }

    public PostHttpBuilder url(String url)
    {
        this.url = url;
        return  this;
    }


    public PostHttpBuilder tag(Object tag)
    {
        this.tag = tag;
        return  this;
    }

        public PostHttpBuilder params(Map<String, String> params)
    {
        this.params = params;
        return this;
    }

    public PostHttpBuilder addParams(String key, String val)
    {
        if (this.params == null)
        {
            params = new LinkedHashMap<>();
        }
        params.put(key, val);
        return this;
    }

}
