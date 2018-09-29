package cn.itcast.jk.job;

import java.util.Date;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/9/28
 * @description:定义了一个任务类
 */
public class MailJob {

    public void send() throws Exception{
        System.out.println("任务执行了..."+new Date());
    }
}
