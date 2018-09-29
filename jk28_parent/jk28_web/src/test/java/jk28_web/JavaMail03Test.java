package jk28_web;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/9/25
 * @description:
 */
public class JavaMail03Test {

    @Test
    public void testJavaMail() throws Exception {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext-mail.xml");

        JavaMailSender sender = (JavaMailSender) ac.getBean("mailSender");//得到邮件的发送对象，用于发送邮件

        //发送一个允许带图片，同时带附件的邮件
        MimeMessage message = sender.createMimeMessage();

        //为了更好的操作MimeMeassage对象，借用一个工具类操作它
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        //通过工具类设置主题，内容，图片，附件



        //发送邮件
        sender.send(message);




    }
}
