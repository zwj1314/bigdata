package cn.itcast.jk.action.sysadmin;

import cn.itcast.jk.action.BaseAction;
import cn.itcast.jk.domain.Module;
import cn.itcast.jk.service.DeptService;
import cn.itcast.jk.service.ModuleService;
import cn.itcast.jk.utils.Page;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/9/21
 * @description:
 */
public class ModuleAction extends BaseAction implements ModelDriven<Module> {

    private Module model = new Module();

    @Override
    public Module getModel() {
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

    //注入ModuleService和DeptService
    private ModuleService moduleService;

    public void setModuleService(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    private DeptService deptService;

    public void setDeptService(DeptService deptService){
        this.deptService = deptService;
    }


    /**
     * 分页查询
     */
    public String list() throws Exception {
        page = moduleService.findPage("from Module", page, Module.class, null);
        //设置分页的url
        page.setUrl("moduleAction_list");

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
        Module module = moduleService.get(Module.class, model.getId());

        //2.放入栈顶
        super.push(module);

        return "toview";
    }

    /**
     * 新增
     */
    public String tocreate(){

        //跳转页面
        return "tocreate";
    }

    /**
     * 保存
     * <s:select name="parent.id"
     * <input type="text" name="moduleName" Value=""/>
     * model对象接收
     */
    public String insert(){
        //1.调用业务方法实现保存
        moduleService.saveOrUpdate(model);

        //跳页面
        return "alist";
    }

    /**
     * 进入修改页面
     */
    public String toupdate(){
        //1.根据选中的id得到一个对象
        Module module = moduleService.get(Module.class, model.getId());
        //2.将对象放入值栈中
        super.push(module);

        //5.跳转页面
        return "toupdate";
    }

    /**
     * 修改
     */
    public String update(){
        //调用业务
        Module module = moduleService.get(Module.class, model.getId());//根据id，得到一个数据库中保存的对象
        //设置修改的属性
        module.setName(model.getName());
        module.setLayerNum(model.getLayerNum());
        module.setCpermission(model.getCpermission());
        module.setCurl(model.getCurl());
        module.setCtype(model.getCtype());
        module.setState(model.getState());
        module.setBelong(model.getBelong());
        module.setCwhich(model.getCwhich());
        module.setRemark(model.getRemark());
        module.setOrderNo(model.getOrderNo());

        //保存
        moduleService.saveOrUpdate(module);
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
        moduleService.delete(Module.class, ids);
        return "alist";
    }

}
