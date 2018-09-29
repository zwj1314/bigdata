package cn.itcast.webservice.impl;

import cn.itcast.webservice.IWeatherService;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/9/29
 * @description:
 */
public class WeatherServiceImpl implements IWeatherService {

    @Override
    public String getWeatherByCityName(String cityName) {
        if ("北京" .equals(cityName)){
            return "晴";
        } else if ("上海".equals(cityName)){
            return "晴";
        } else
            return "查无此地";
    }
}
