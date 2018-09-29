package cn.itcast.gjp.tools;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;

/**
 *
 * 获取数据库连接的工具类
 * 实现连接池，dhcp连接池
 *
 */
public class JDBCUtils {

    //创建BasicDataSource对象，连接池
    private static BasicDataSource datasource = new BasicDataSource();
    //静态代码块，实现必要的参数设置
    static {
        datasource.setDriverClassName("com.mysql.jdbc.Driver");
        datasource.setUrl("jdbc://mysql:localhost:3306/gjp");
        datasource.setUsername("root");
        datasource.setPassword("1234");
        datasource.setMaxActive(10);
        datasource.setMaxIdle(5);
        datasource.setMinIdle(2);
        datasource.setInitialSize(10);
    }

    //返回datasource对象,供外界调用
    public static DataSource getDataSource(){
        return datasource;
    }

}
