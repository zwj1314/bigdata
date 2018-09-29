package com.itheima.demo1;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/22
 * @description:注解方式的切面类
 */
@Aspect
public class MyAspectAnno {

    /*
    通知类型：@Before前置通知（切入点的表达式）
     */
//    @Before(value = "execution(public * com.itheima.demo1.CustomerDaoImpl.save())")
    @Before(value = "MyAspectAnno.fun()")
    public void log() {
        System.out.println("记录日志...");
    }


    /*
    最终通知：方法执行成功或者异常，都会执行
     */
//    @After(value = "execution(public * com.itheima.demo1.CustomerDaoImpl.save())")
    @After(value = "MyAspectAnno.fun()")
    public void after() {
        System.out.println("最终通知...");
    }

    /*
    before和after都会写相同的切入点，代码重复
    自己定义切入点
     */
    @Pointcut(value = "execution(public * com.itheima.demo1.CustomerDaoImpl.save())")
    public void fun() {

    }

    /*
    环绕通知
     */
    @Around(value = "MyAspectAnno.fun()")
    public void around(ProceedingJoinPoint joinPoint) {
        System.out.println("环绕通知1...");
        try {
            //让目标对象的方法执行
            joinPoint.proceed();

        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("环绕通知2...");
    }
}
