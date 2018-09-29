package cn.itcast.webservice.invoking;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;
import cn.itcast.webservice.IWeatherService;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/9/29
 * @description:
 */
public class WeatherServiceInvokingClient {

    @Test
    public void testWeather(){
        //1.生成一个客户端代理工厂
        JaxWsProxyFactoryBean client = new JaxWsProxyFactoryBean();

        //2.设置服务端的访问地址
        client.setAddress("http://localhost:12345/weather");

        //3.设置服务端的接口
        client.setServiceClass(IWeatherService.class);

        //4.创建客户端对象
        IWeatherService iws = (IWeatherService) client.create();

        //5.调用远程的服务器端提供的方法
        String info = iws.getWeatherByCityName("上海");

        System.out.println(info);
    }
}
