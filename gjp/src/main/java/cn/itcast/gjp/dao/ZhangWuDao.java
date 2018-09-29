package cn.itcast.gjp.dao;

import cn.itcast.gjp.domain.ZhangWu;
import cn.itcast.gjp.tools.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ZhangWuDao {

    private QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

    /*
     * 定义方法，实现添加账务功能
     * 由业务层调用，传递ZhangWu对象
     * 将ZhangWu对象中的数据，添加到数据库
     */
    public void addZhangWu(ZhangWu zw) {

        //添加账务的sql,参数用占位符表示，防止sql注入
        String sql = "insert into gjp_zhangwu (flname, money, zhanghu, createtime, description) values (?,?,?,?,?)";

        //定义数组，接收service层传递过来的参数
        Object[] params = {zw.getFlname(),zw.getMoney(),zw.getZhanghu(),zw.getCreatetime(),zw.getDecsription()};

        //调用qr对象中的方法update执行添加
        try {
            qr.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    /*
    定义方法，实现查询所有的数据记录
    由业务层调用，没有参数传递
    结果集返回所有的账务数据，存储到list集合中，并返回
     */
    public List<ZhangWu> selectAll() {

        //sql
        String sql = "select * from gjp_zhangwu ";

        //定义集合，存储查询的结果集
        List<ZhangWu> list = null;
        try {
            list = qr.query(sql,new BeanListHandler<>(ZhangWu.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;



    }

    public List<ZhangWu> select(String startDate, String endDate) {

        //查询的sql
        String sql = "select * from gjp_zhangwu where createtime between ? and ?";
        //定义数组，存储参数
        Object[] params = {startDate,endDate};
        //
        List<ZhangWu> list = null;
        try {
            list = qr.query(sql,new BeanListHandler<>(ZhangWu.class),params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    /*
    定义函数，删除指定的zwid的账务信息
    接收MainView传入过来的zwid

    */
    public void deleteZhangWu(int zwid) {
        try {
            //删除信息的sql
            String sql = "delect * from gjp_zhangwu where zwid = ?";
            //
            qr.update(sql);

        }catch (SQLException e){
            System.out.println(e);
            throw new RuntimeException("删除账务失败");

        }
    }

    public void editZhangWu(ZhangWu zw) {
        try{
            String sql = "UPDATE gjp_zhangwu SET flname=?,money=?,zhanghu=?,createtime=?,description=? WHERE zwid=?";

            //定义数组，获取用户输入的账务信息
            Object[] params = {zw.getFlname(),zw.getMoney(),zw.getZhanghu(),zw.getCreatetime(),zw.getDecsription(),zw.getZwid()};
            qr.update(sql,params);

        }catch (SQLException e){
            System.out.println(e);
            throw new RuntimeException("编辑账务信息失败");
        }


    }
}
