package cn.itcast.gjp.contrller;
/**
 * 控制器层：
 *  接收MainView层的数据，将数据传递给service层
 *  成员位置，创建service对象
 */

import cn.itcast.gjp.domain.ZhangWu;
import cn.itcast.gjp.service.ZhangWuService;

import java.util.List;

public class ZhangWuController {
    //
    ZhangWuService service = new ZhangWuService();


    /*
     * 定义方法，实现账务添加功能
     * 由视图层调用，传递参数(传递过来的参数不能是5个数据，传递的是一个ZhangWu类型的对象)
     * 本方法调用service层的方法，传递ZhangWu对象，获取到添加后的结果集(添加成功影响的行数，int)
     *
     */
    public void addZhangWu(ZhangWu zw) {
        service.addZhangWu(zw);

    }

    /*
    定义方法，实现查询账务的功能
    由视图层调用,调用service层的方法

     */
    public List<ZhangWu> select(String startDate, String endDate) {

        return service.select(startDate,endDate);
    }


    /*
     * 控制层类定义方法,实现查询所有的账务数据
     * 方法由试图层调用,方法调用service层
     */
    public List<ZhangWu> selectAll() {
        return service.selectAll();
    }



    /*
     * 定义方法，实现删除功能
     * 视图层调用，传递int类型主键
     * 调用service层方法，传递int主键
     */
    public void deleteZhangWu(int zwid) {
        service.deleteZhangWu(zwid);
    }


    /*


     */
    public void editZhangWu(ZhangWu zw){
        service.editZhangWu(zw);

    }
}
