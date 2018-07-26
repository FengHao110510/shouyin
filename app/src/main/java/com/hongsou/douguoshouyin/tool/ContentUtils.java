package com.hongsou.douguoshouyin.tool;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lpc
 *         <p>
 * @copyright 鸿搜网络公司 版权所有
 * <p>
 * @date 2018/7/26
 * <p>
 * @desc
 */

public class ContentUtils {

    public static String mapToString(HashMap<String, Object> data) {
        StringBuffer buffer = new StringBuffer();
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            buffer.append(entry.getKey()).append("@_@").append(entry.getValue()).append(">_<");
        }
        return buffer.toString();
    }

    public static Map stringToMap(String val){
        Map<String, Object> map = new HashMap<>();
        try {
            if (val != null && val.length() > 0){
                String[] split = val.split(">_<");
                if (split != null && split.length > 0){
                    for (String s : split) {
                        String[] split1 = s.split("@_@");
                        if (split1 != null && split1.length > 0){
                            map.put(split1[0], split1[1]);
                        }
                    }
                }
            }
        }catch (Exception e){
            System.out.println("content转换异常 ：" + e.toString());
        }
        return map;
    }
}
