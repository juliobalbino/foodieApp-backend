package com.foodie.foodieApp.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.foodie.foodieApp.entities.Usuario;

@Service
public interface EmailService {

	void sendAccConfirmationEmail(Usuario obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendAccConfirmationHtmlEmail(Usuario obj);
	
	void sendHtmlEmail(MimeMessage msg);
	
	void sendNewPasswordEmail(Usuario usuario, String newPass);
}
