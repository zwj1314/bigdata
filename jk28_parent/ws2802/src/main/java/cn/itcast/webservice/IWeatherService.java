package cn.itcast.webservice;

import javax.jws.WebService;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/9/29
 * @description:
 */
@WebService //作为一个webservice的标志
public interface IWeatherService {

    public String getWeatherByCityName(String cityName);
}
