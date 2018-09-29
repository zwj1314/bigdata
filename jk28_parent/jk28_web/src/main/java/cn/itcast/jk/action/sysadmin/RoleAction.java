package cn.itcast.jk.action.sysadmin;

import cn.itcast.jk.action.BaseAction;
import cn.itcast.jk.domain.Module;
import cn.itcast.jk.domain.Role;
import cn.itcast.jk.exception.SysException;
import cn.itcast.jk.service.DeptService;
import cn.itcast.jk.service.ModuleService;
import cn.itcast.jk.service.RoleService;
import cn.itcast.jk.utils.Page;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/9/21
 * @description:部门管理的action
 */
public class RoleAction extends BaseAction implements ModelDriven<Role> {

    private Role model = new Role();

    @Override
    public Role getModel() {
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

    //注入RoleService和DeptService和ModuleService
    private RoleService roleService;

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    private DeptService deptService;

    public void setDeptService(DeptService deptService) {
        this.deptService = deptService;
    }

    private ModuleService moduleService;

    public void setModuleService(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    /**
     * 分页查询
     */
    public String list() throws Exception {
        page = roleService.findPage("from Role", page, Role.class, null);
        //设置分页的url
        page.setUrl("roleAction_list");

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
    public String toview() throws Exception {
        try {
            //1.调用业务方法，根据id得到对象
            Role role = roleService.get(Role.class, model.getId());

            //2.放入栈顶
            super.push(role);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SysException("对不起，请先勾选再操作！！！");
        }

        return "toview";
    }

    /**
     * 新增
     */
    public String tocreate() {

        //跳转页面
        return "tocreate";
    }

    /**
     * 保存
     * <s:select name="parent.id"
     * <input type="text" name="roleName" Value=""/>
     * model对象接收
     */
    public String insert() {
        //1.调用业务方法实现保存
        roleService.saveOrUpdate(model);

        //跳页面
        return "alist";
    }

    /**
     * 进入修改页面
     */
    public String toupdate() {
        //1.根据选中的id得到一个对象
        Role role = roleService.get(Role.class, model.getId());
        //2.将对象放入值栈中
        super.push(role);

        //5.跳转页面
        return "toupdate";
    }

    /**
     * 修改
     */
    public String update() {
        //调用业务
        Role role = roleService.get(Role.class, model.getId());//根据id，得到一个数据库中保存的对象
        //设置修改的属性
        role.setName(model.getName());
        role.setRemark(model.getRemark());


        //保存
        roleService.saveOrUpdate(role);
        return "alist";
    }

    /**
     * 删除
     * <input type="checkbox" name="id" value="100"/>
     * <input type="checkbox" name="id" value="3d00290a-1af0-4c28-853e-29fbf96a2722"/>
     * ....
     * model
     * id:String类型
     * 具有同名框的一组值如何封装数据？\
     * 如果服务器端是String类型(可以正常提取)：
     * 100，3d00290a-1af0-4c28-853e-29fbf96a2722
     * <p>
     * id:Integer,float,double,date类型 id=100, id=200, id=300
     * id=300(只能提取最后一个值)
     * Integer[] ids;
     */
    public String delete() {
        String ids[] = model.getId().split(",");

        //调用业务方法,实现批量删除
        roleService.delete(Role.class, ids);
        return "alist";
    }

    /*
    进入模块分配页面
     */
    public String tomodule() {
        //1.根据角色id，得到角色对象
        Role obj = roleService.get(Role.class, model.getId());

        //2.jsp页面中使用，放入值栈中
        super.push(obj);

        return "tomodule";
    }

    /**
     *
     */
    public void roleModuleJsonStr() throws IOException {
        //1.根据角色id，得到角色对象
        Role obj = roleService.get(Role.class, model.getId());

        //2.通过对象导航方式，加载出当前角色的模块列表
        Set<Module> moduleSet = obj.getModules();

        //3.加载出全部的模块列表
        List<Module> moduleList = moduleService.find("from Module", Module.class, null);
        int size = moduleList.size();

        //4.组织json串
        StringBuilder sb = new StringBuilder();//非线程安全
        sb.append("[");
        for (Module module : moduleList) {
            size--;
            sb.append("{\"id\":\"").append(module.getId());
            sb.append("\",\"pId\":\"").append(module.getParentId());
            sb.append("\",\"name\":\"").append(module.getName());
            sb.append("\",\"checker\":\"");

            if (moduleSet.contains(module)) {
                sb.append("true");
            } else {
                sb.append("false");
            }
            sb.append("\"}");

            if (size>0){
                sb.append(",");
            }
        }
        sb.append("]");

        //5.得到response对象
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");

        //6.使用response对象输出json串
        response.getWriter().write(sb.toString());

    }

    /*
    保存当前角色的模块列表
    <input type="hidden" name="id" value="${id}"/>
	<input type="hidden" id="moduleIds" name="moduleIds" value="" />
     */
    public String module(){
        //1.对应的角色是谁？
        Role role = roleService.get(Role.class, model.getId());

        //2.选中了哪些模块
        String[] ids = moduleIds.split(",");
        //加载选中的这些模块
        Set<Module> moduleSet = new HashSet<>();
        if (ids!=null && ids.length>0){
            for (String id:ids){
                moduleSet.add(moduleService.get(Module.class, id));//将选中的模块添加到模块列表中
            }
        }

        //3.实现角色分配新的模块
        role.setModules(moduleSet);

        //4.保存结果
        roleService.saveOrUpdate(role);

        //5.跳转页面
        return "alist";
    }

    private String moduleIds;
    public void setModuleIds(String moduleIds) {
        this.moduleIds = moduleIds;
    }
}
