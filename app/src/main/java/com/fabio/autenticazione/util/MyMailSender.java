package com.fabio.autenticazione.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MyMailSender {
    
    private JavaMailSender emailSender;

    @Value("${spring.mail.username}")
	private String username;

    public MyMailSender(JavaMailSender javaMailSender) {
        this.emailSender = javaMailSender;
    }

    public void sendSimpleMail(String to,String subject,String text) {
        var mail = new SimpleMailMessage();
        
        mail.setFrom("perellifabio@gmail.com");
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setText(text);
        emailSender.send(mail);
    
    }

    public void printUsername(){
        System.out.println("Username: "+username);
    }
}
