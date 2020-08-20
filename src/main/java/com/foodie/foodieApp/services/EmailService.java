package com.foodie.foodieApp.services;

import org.springframework.mail.SimpleMailMessage;

import com.foodie.foodieApp.entities.Usuario;

public interface EmailService {

	void sendAccConfirmationEmail(Usuario obj);
	
	void sendEmail(SimpleMailMessage msg);
}
