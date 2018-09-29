package cn.itcast.gjp.service;

import cn.itcast.gjp.dao.ZhangWuDao;
import cn.itcast.gjp.domain.ZhangWu;

import java.util.List;

/**
 * service层(业务层)：
 *  接收上一层Controller层的数据，经过计算传递给dao层，操作数据库
 *  调用dao层中的类,类成员位置,创建Dao类的对象

 */
public class ZhangWuService {
    //创建Dao类的对象
    ZhangWuDao dao = new ZhangWuDao();


    /*
     * 定义方法，实现添加账务
     * 是由控制层调用，传递ZhangWu对象
     */
    public void addZhangWu(ZhangWu zw) {
        //调用dao中的方法添加账务
        dao.addZhangWu(zw);

    }
    /*
    定义方法，实现查询所有的账务信息
    由控制层调用
    调用dao层的方法，返回存储账务信息的List集合
     */
    public List<ZhangWu> selectAll() {

        return dao.selectAll();

    }

    public List<ZhangWu> select(String startDate, String endDate) {
        return dao.select(startDate,endDate);
    }


    /*
     * 定义方法，实现删除账务功能
     * 由控制层调用，传递主键id
     * 调用dao层方法，传递主键id
     */
    public void deleteZhangWu(int zwid) {
        dao.deleteZhangWu(zwid);
    }




    public void editZhangWu(ZhangWu zw) {
        dao.editZhangWu(zw);
    }
}
