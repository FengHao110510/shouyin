package com.hongsou.douguoshouyin.db.helper;

import com.google.gson.Gson;
import com.hongsou.douguoshouyin.db.FoodZuheTaocanXQ;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



/**
 * Created by Administrator on 2018/4/2.
 * TODO:    数据类型转换
 */
public class GroupFoodConverter implements PropertyConverter<List<FoodZuheTaocanXQ>, String> {

    /**
     * 将字符串转换为实体类对象
     *
     * @param databaseValue
     * @return
     */
    @Override
    public List<FoodZuheTaocanXQ> convertToEntityProperty(String databaseValue) {
        if (databaseValue == null) {
            return null;
        }
        List<String> list_str = Arrays.asList(databaseValue.split("!"));
        List<FoodZuheTaocanXQ> list_transport = new ArrayList<>();
        for (String s : list_str) {
            list_transport.add((FoodZuheTaocanXQ) new Gson().fromJson(s, FoodZuheTaocanXQ.class));
        }
        return list_transport;
    }

    /**
     * 实体类对象转换为String
     *
     * @param arrays
     * @return
     */
    @Override
    public String convertToDatabaseValue(List<FoodZuheTaocanXQ> arrays) {
        if (arrays == null) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            for (FoodZuheTaocanXQ array : arrays) {
                String str = new Gson().toJson(array);
                sb.append(str);
                sb.append("!");
            }
            return sb.toString();

        }
    }


}
