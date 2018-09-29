package jk28_web;

import org.junit.Test;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/9/25
 * @description:
 */

public class JavaMail01Test {

    @Test
    public void testJavaMail() throws Exception {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.163.com");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(props);//得到session
        session.setDebug(true);//启用debug模式，可以在控制台输出smtp协议应答的过程

        //创建一个MimeMessage格式的邮件
        MimeMessage message = new MimeMessage(session);

        //设置发送者
        Address fromAddress = new InternetAddress("zjian2584105760@163.com");
        message.setFrom(fromAddress);//设置发送的邮件地址

        //设置接收者
        Address toAddress = new InternetAddress("2584105760@qq.com");
        message.setRecipient(Message.RecipientType.TO, toAddress);//设置接收的邮件地址

        //设置邮件的主题
        message.setSubject("zhouboheng");
        //设置邮件的内容
        message.setText("工作顺利");

        //保存邮件
        message.saveChanges();

        //得到发送邮件的火箭
        Transport transport = session.getTransport("smtp");

        //火箭连接到服务器上
        transport.connect("smtp.163.com", "zjian2584105760@163.com", "zhangjian1994");

        //火箭点火
        transport.sendMessage(message, message.getAllRecipients());

        //关闭通道
        transport.close();



    }
}

