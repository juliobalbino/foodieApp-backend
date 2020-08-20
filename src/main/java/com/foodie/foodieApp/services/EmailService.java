package com.foodie.foodieApp.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.foodie.foodieApp.entities.Usuario;

public interface EmailService {

	void sendAccConfirmationEmail(Usuario obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendAccConfirmationHtmlEmail(Usuario obj);
	
	void sendHtmlEmail(MimeMessage msg);
}
