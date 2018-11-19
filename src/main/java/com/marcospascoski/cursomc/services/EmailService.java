package com.marcospascoski.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.marcospascoski.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
