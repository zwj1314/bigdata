package cn.itcast.jk.action.sysadmin;

import cn.itcast.jk.action.BaseAction;
import cn.itcast.jk.domain.Dept;
import cn.itcast.jk.domain.Role;
import cn.itcast.jk.domain.User;
import cn.itcast.jk.service.DeptService;
import cn.itcast.jk.service.RoleService;
import cn.itcast.jk.service.UserService;
import cn.itcast.jk.utils.Page;
import com.opensymphony.xwork2.ModelDriven;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/9/21
 * @description:部门管理的action
 */
public class UserAction extends BaseAction implements ModelDriven<User> {

    private User model = new User();

    @Override
    public User getModel() {
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

    //注入UserService和DeptService和RoleService
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private DeptService deptService;

    public void setDeptService(DeptService deptService) {
        this.deptService = deptService;
    }

    private RoleService roleService;

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * 分页查询
     */
    public String list() throws Exception {
        page = userService.findPage("from User", page, User.class, null);
        //设置分页的url
        page.setUrl("userAction_list");

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
    public String toview() {
        //1.调用业务方法，根据id得到对象
        User user = userService.get(User.class, model.getId());

        //2.放入栈顶
        super.push(user);

        return "toview";
    }

    /**
     * 新增
     */
    public String tocreate() {
        //调用业务方法
        List<Dept> deptList = deptService.find("from Dept where state = 1", Dept.class, null);
        //将查询的结果放入值栈中
        super.put("deptList", deptList);

        List<User> userList = userService.find("from User where state = 1", User.class, null);
        super.put("userList", userList);
        //跳转页面
        return "tocreate";
    }

    /**
     * 保存
     * <s:select name="parent.id"
     * <input type="text" name="userName" Value=""/>
     * model对象接收
     */
    public String insert() {
        //1.调用业务方法实现保存
        userService.saveOrUpdate(model);

        //跳页面
        return "alist";
    }

    /**
     * 进入修改页面
     */
    public String toupdate() {
        //1.根据选中的id得到一个对象
        User user = userService.get(User.class, model.getId());
        //2.将对象放入值栈中
        super.push(user);
        //3.查询父部门
        List<Dept> deptList = deptService.find("from Dept where state = 1", Dept.class, null);
        //4.将查询的结果放入值栈中
        super.put("deptList", deptList);
        //5.跳转页面
        return "toupdate";
    }

    /**
     * 修改
     */
    public String update() {
        //调用业务
        User user = userService.get(User.class, model.getId());//根据id，得到一个数据库中保存的对象
        //设置修改的属性
        user.setDept(model.getDept());
        user.setUserName(model.getUserName());
        user.setState(model.getState());

        //保存
        userService.saveOrUpdate(user);
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
        userService.delete(User.class, ids);
        return "alist";
    }


    /*
    进入角色分配页面
     */
    public String torole() {

        //1.根据id得到对象
        User obj = userService.get(User.class, model.getId());
        //2.将对象保存到值栈中
        super.push(obj);
        //3.调用角色的业务方法，得到角色列表
        List<Role> roleList = roleService.find("from Role", Role.class, null);
        //4.将roleList放入值栈中
        super.put("roleList", roleList);
        //5.得到当前用户的角色列表
        Set<Role> roleSet = obj.getRoles();
        StringBuffer sb = new StringBuffer();
        for (Role role : roleSet) {
            sb.append(role.getName()).append(",");
        }

        //6.当前用户的角色字符串放入值栈中
        super.put("roleStr", sb.toString());
        return "torole";
    }

    /*
    保存角色
     */
    public String role() {

        //1.根据用户的id得到对象
        User obj = userService.get(User.class, model.getId());

        //2.勾选了哪些角色，只要遍历roleIds就行了
        HashSet<Role> roles = new HashSet<>();
        for (String id : roleIds) {
            Role role = roleService.get(Role.class, id);
            roles.add(role);
        }//roles即为当前选中的列表

        //3.设置用户与角色列表之间的关系
        obj.setRoles(roles);

        //4.保存到数据库表中
        userService.saveOrUpdate(obj);

        //5.跳转页面
        return "alist";
    }

    private String[] roleIds;//定义字符数组，保存角色列表

    public void setRoleIds(String[] roleIds) {
        this.roleIds = roleIds;
    }
}
