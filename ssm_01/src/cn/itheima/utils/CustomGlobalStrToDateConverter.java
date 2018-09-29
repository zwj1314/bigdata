package cn.itheima.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/31
 * @description:
 *  S:source
 *  T:target
 */
public class CustomGlobalStrToDateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String source) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(source);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
