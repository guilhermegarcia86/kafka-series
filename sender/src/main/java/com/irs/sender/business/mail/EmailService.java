package com.irs.sender.business.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.irs.sender.domain.Person;
import com.irs.sender.infra.mail.Email;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailService implements Email{
	
	@Autowired
    private JavaMailSender emailSender;

	@Override
	public void sendMessage(Person person) {
		
		try {
			
			log.info("Try to send email for : " + person.getEmail());
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("noreply@irs.com");
            message.setTo(person.getEmail());
            message.setSubject("Confirmação de recebimento");
            message.setText(person.getName() + " seus dados foram recebidos com sucesso");

            emailSender.send(message);
            
        } catch (MailException ex) {
        	log.error("Error to send email", ex);
        }
		
	}

}
