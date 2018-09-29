package cn.itcast.publisher;

import cn.itcast.webservice.impl.WeatherServiceImpl;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/9/29
 * @description:
 */
public class PublisherTest {

    public static void main(String[] args) {
        //1.创建一个工厂类
        JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();

        //2.设置服务的地址
        factory.setAddress("http://localhost:12345/weather");

        //3.设置背后工作的服务类
        factory.setServiceBean(new WeatherServiceImpl());

        //4.创建webservice服务
        factory.create();
    }
}
