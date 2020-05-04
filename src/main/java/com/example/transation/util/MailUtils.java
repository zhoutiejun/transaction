package com.example.transation.util;

import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * @Author ：yaxuSong
 * @Description:
 * @Date: 17:42 2018/6/25
 * @Modified by:
 */
public class MailUtils {
    public static JavaMailSenderImpl initJavaMailSender(String sender, String pwd) {

        Properties properties = new Properties();
        properties.setProperty("mail.debug", "true");// 是否显示调试信息(可选)
        properties.setProperty("mail.smtp.starttls.enable", "false");
        properties.setProperty("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.auth", "true");
        properties.put("mail.smtp.timeout", " 25000 ");

        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setJavaMailProperties(properties);
        javaMailSender.setHost("smtp.exmail.qq.com");
        javaMailSender.setUsername(sender); // s根据自己的情况,设置username
        javaMailSender.setPassword(pwd); // 根据自己的情况, 设置password
        javaMailSender.setPort(465);
        javaMailSender.setDefaultEncoding("UTF-8");

        return javaMailSender;
    }
}
