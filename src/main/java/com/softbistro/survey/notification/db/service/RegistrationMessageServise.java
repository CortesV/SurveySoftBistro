package com.softbistro.survey.notification.db.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Function;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.softbistro.survey.client.manage.components.interfaces.IClient;
import com.softbistro.survey.daemons.notification.system.component.entity.Notification;
import com.softbistro.survey.daemons.notification.system.component.interfaces.ISendingMessage;
import com.softbistro.survey.notification.db.interfacee.ICreateMessage;

/**
 * For creating and sending message that will contain information about new user
 * for confirm registration
 * 
 * @author alex_alokhin, zviproject
 *
 */
@Service
@Scope("prototype")
public class RegistrationMessageServise implements ICreateMessage {
	private Logger log = LogManager.getLogger(getClass());

	@Autowired
	private ISendingMessage iSendingMessage;

	@Autowired
	private IClient iClient;

	/**
	 * Data about account that will sending messages
	 */
	@Value("${client.mail.username}")
	private String username;

	@Value("${client.text.for.sending.url}")
	private String url;

	/**
	 * Sending message to database
	 */
	@Override
	public void send() {
		List<String> emails = iClient.getEmailOfNewClients();
		emails.stream().forEach(email -> {
			Notification notification = new Notification(username,email,generateThemeForMessage(),generateTextForMessage(email));

			iSendingMessage.insertIntoNotification(notification);
			log.info(String.format("Registration email: %s", email));
		});
	}

	/**
	 * Generate text for message
	 * 
	 * @param email,
	 *            uuid
	 */
	@Override
	public String generateTextForMessage(String email) {
		String urlForVote = url + UUID.randomUUID().toString();

		String textMessage = String.format(
				"Registration new account with email \"%s\" \n" + "For confirm click on URL : %s", email, urlForVote);
		return textMessage;
	}

	/**
	 * Generate theme of message
	 */
	@Override
	public String generateThemeForMessage() {
		return String.format("Registration");
	}

	
	
	
}
