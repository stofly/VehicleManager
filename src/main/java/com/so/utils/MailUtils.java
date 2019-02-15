package com.so.utils;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtils {
	public static void sendEmail(String email, String subject, String text) throws MessagingException {
		// 1.创建一个程序与邮件服务器会话对象 Session
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.auth", "true");// 设置访问smtp服务器需要认证
		properties.setProperty("mail.transport.protocol", "smtp"); // 设置访问服务器的协议
		Session session = Session.getDefaultInstance(properties);
		// session.setDebug(true); //打开debug功能
		// 2.创建一个Message，它相当于是邮件内容
		Message msg = new MimeMessage(session);
		// 设置发送者
		msg.setFrom(new InternetAddress("15037584397@163.com"));
		msg.setSubject(subject); // 设置邮件主题
		msg.setText(text); // 设置邮件内容
		// 3.发送邮件
		Transport trans = session.getTransport();
		// 连接邮箱smtp服务器;25为默认端口;163邮箱;客户端授权密码（注意，不是登录密码）
		trans.connect("smtp.163.com", 25, "15037584397@163.com", "gaojun161530247");
		// 邮件接收者
		if (email == null)
			email = "15037584397@163.com";
		trans.sendMessage(msg, new Address[] { new InternetAddress(email) }); // 发送邮件
		// 关闭连接
		trans.close();
	}
}