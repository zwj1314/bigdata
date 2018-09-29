package cn.itheima.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/9/1
 * @description:
 */
public class Interceptor1 implements HandlerInterceptor {

    //返回布尔值：如果返回true则放行，返回false则被拦截住
    //执行时机:controller方法没有被执行,ModelAndView没有被返回
    //使用场景: 权限验证
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    //执行时机:Controller方法已经执行,ModelAndView没有返回
    //使用场景: 可以在此方法中设置全局的数据处理业务
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    //执行时机:controller已经执行,modelAndview已经返回
    //使用场景: 记录操作日志,记录登录用户的ip,时间等.
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
