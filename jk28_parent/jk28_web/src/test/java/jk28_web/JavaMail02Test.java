package jk28_web;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/9/25
 * @description:
 */
public class JavaMail02Test {

    @Test
    public void testJavaMail() throws Exception {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext-mail.xml");

        SimpleMailMessage message = (SimpleMailMessage) ac.getBean("mailMessage");//加载简单邮件对象
        JavaMailSender sender = (JavaMailSender) ac.getBean("mailSender");//得到邮件的发送对象，用于发送邮件

        //设置简单邮件对象的属性
        message.setSubject("spring");
        message.setText("hello");
        message.setTo("2584105760@qq.com");

        //发送邮件
        sender.send(message);




    }
}
