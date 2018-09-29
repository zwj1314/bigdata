package cn.itcast.jk.shiro;

import cn.itcast.jk.domain.Module;
import cn.itcast.jk.domain.Role;
import cn.itcast.jk.domain.User;
import cn.itcast.jk.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/9/24
 * @description:
 */
public class AuthRealm extends AuthorizingRealm {
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    //授权 当jsp页面中出现shiro标签时，就会执行授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
        System.out.println("授权");
        User user= (User) pc.fromRealm(this.getName()).iterator().next();//根据realm的名字去找对应的realm
        Set<Role> roles = user.getRoles();//对象导航
        List<String> permissions = new ArrayList<>();
        for (Role role:roles){
            //
            Set<Module> modules = role.getModules();//得到每个角色下的模块列表
            for (Module module:modules){
                permissions.add(module.getName());
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissions);//添加用户的模块（权限）

        return info;
    }

    //认证 authenticationToken代表用户在界面输入的用户名和密码
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("认证");
        //1.向下转型
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;

        //2.调用业务方法，实现根据用户名查询
        String hql = "from User where userName=?";
        List<User> list = userService.find(hql, User.class, new String[]{upToken.getUsername()});
        if (list != null && list.size() > 0) {
            User user = list.get(0);
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
            return info;//如果此处返回不为空，则会进入到密码比较器
        }

        return null;
    }
}
