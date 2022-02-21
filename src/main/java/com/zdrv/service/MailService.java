package com.zdrv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	@Autowired
	private MailSender sender;

	public void sendMail(String moji) {
		SimpleMailMessage msg = new SimpleMailMessage();

		msg.setFrom("test@mail.com");
		msg.setTo("b.miyazaki.zaqxsw@gmail.com");
		msg.setSubject("お問い合わせが届きました");//タイトルの設定
		msg.setText(moji); //本文の設定

		this.sender.send(msg);
	}
}
