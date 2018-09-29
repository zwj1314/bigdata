package com.itheima.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/28
 * @description:
 */
public class FastJsonUtil {
    /**
     * 将对象转成json串
     */
    public static String toJSONString (Object object){
        //DisableCircularReferenceDetect来禁止循环引用
        return JSON.toJSONString(object, SerializerFeature.DisableCircularReferenceDetect);
    }

    //输出json
    public static void write_json(HttpServletResponse response, String jsonString){
        response.setContentType("application/json;utf-8");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().print(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
