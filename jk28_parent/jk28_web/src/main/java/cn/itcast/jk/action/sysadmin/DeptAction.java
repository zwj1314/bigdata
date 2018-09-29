package cn.itcast.jk.action.sysadmin;

import cn.itcast.jk.action.BaseAction;
import cn.itcast.jk.domain.Dept;
import cn.itcast.jk.service.DeptService;
import cn.itcast.jk.utils.Page;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import java.util.List;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/9/21
 * @description:部门管理的action
 */
public class DeptAction extends BaseAction implements ModelDriven<Dept> {

    private Dept model = new Dept();

    @Override
    public Dept getModel() {
        return model;
    }

    //分页查询
    private Page page = new Page();

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    //注入DeptService
    private DeptService deptService;

    public void setDeptService(DeptService deptService) {
        this.deptService = deptService;
    }

    /**
     * 分页查询
     */
    public String list() throws Exception {
        page = deptService.findPage("from Dept", page, Dept.class, null);
        //设置分页的url
        page.setUrl("deptAction_list");

        //将page对象压入栈顶
        super.push(page);
        return "list";
    }

    /*
    查看
    id=
    model对象
        id属性：
     */
    public String toview(){
        //1.调用业务方法，根据id得到对象
        Dept dept = deptService.get(Dept.class, model.getId());

        //2.放入栈顶
        super.push(dept);

        return "toview";
    }

    /**
     * 新增
     */
    public String tocreate(){
        //调用业务方法
        List<Dept> list = deptService.find("from Dept where state = 1", Dept.class, null);
        //将查询的结果放入值栈中
        super.put("deptList", list);
        //跳转页面
        return "tocreate";
    }

    /**
     * 保存
     * <s:select name="parent.id"
     * <input type="text" name="deptName" Value=""/>
     * model对象接收
     */
    public String insert(){
        //1.调用业务方法实现保存
        deptService.saveOrUpdate(model);

        //跳页面
        return "alist";
    }

    /**
     * 进入修改页面
     */
    public String toupdate(){
        //1.根据选中的id得到一个对象
        Dept dept = deptService.get(Dept.class, model.getId());
        //2.将对象放入值栈中
        super.push(dept);
        //3.调用业务方法
        List<Dept> list = deptService.find("from Dept where state = 1", Dept.class, null);
        //4.将查询的结果放入值栈中
        super.put("deptList", list);
        //5.跳转页面
        return "toupdate";
    }

    /**
     * 修改
     */
    public String update(){
        //调用业务
        Dept dept = deptService.get(Dept.class, model.getId());//根据id，得到一个数据库中保存的对象
        //设置修改的属性
        dept.setParent(model.getParent());
        dept.setDeptName(model.getDeptName());
        //保存
        deptService.saveOrUpdate(dept);
        return "alist";
    }

    /**
     * 删除
     * <input type="checkbox" name="id" value="100"/>
     * <input type="checkbox" name="id" value="3d00290a-1af0-4c28-853e-29fbf96a2722"/>
     *  ....
     * model
     *      id:String类型
     *      具有同名框的一组值如何封装数据？\
     *      如果服务器端是String类型(可以正常提取)：
     *              100，3d00290a-1af0-4c28-853e-29fbf96a2722
     *
     *      id:Integer,float,double,date类型 id=100, id=200, id=300
     *              id=300(只能提取最后一个值)
     *              Integer[] ids;
     *
     */
    public String delete() {
        String ids[] = model.getId().split(",");

        //调用业务方法,实现批量删除
        deptService.delete(Dept.class, ids);
        return "alist";
    }

}
