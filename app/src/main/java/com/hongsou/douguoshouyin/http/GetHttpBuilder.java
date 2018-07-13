package com.hongsou.douguoshouyin.http;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/7/2.
 */

public class GetHttpBuilder {

    protected String url;
    protected Object tag;
    private Map<String, String> params;

    public HttpFactory build() {
        return new HttpFactory(HttpFactory.GET, url, tag, params);
    }

    public GetHttpBuilder url(String url) {
        this.url = url;
        return this;
    }


    public GetHttpBuilder tag(Object tag) {
        this.tag = tag;
        return this;
    }

    public GetHttpBuilder params(Map<String, String> params) {
        this.params = params;
        return this;
    }

    public GetHttpBuilder addParams(String key, String val) {
        if (this.params == null) {
            params = new LinkedHashMap<>();
        }
        params.put(key, val);
        return this;
    }

}
