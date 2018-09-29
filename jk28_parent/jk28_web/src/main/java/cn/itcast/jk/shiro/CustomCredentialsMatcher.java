package cn.itcast.jk.shiro;

import cn.itcast.jk.utils.Encrypt;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/9/24
 * @description:
 */
public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {

    /**
     *  密码验证器
     * @param token:代表用户在界面输入的用户名和密码
     * @param info：从数据库中得到的加密数据
     * @return
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        //1.向下转型
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        //2.将原始密码进行加密
        Object pwd = Encrypt.md5(new String(upToken.getPassword()), upToken.getUsername());
        //3.取出数据库中加密的密码
        Object dbPwd = info.getCredentials();

        return this.equals(pwd, dbPwd);
    }
}
