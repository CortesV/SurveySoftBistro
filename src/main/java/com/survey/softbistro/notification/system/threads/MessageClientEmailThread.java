package com.survey.softbistro.notification.system.threads;

import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.survey.softbistro.notification.system.component.entity.RegistrationMessage;
import com.survey.softbistro.notification.system.component.interfacee.ISendingMessage;
import com.survey.softbistro.notification.system.interfacee.ISending;

/**
 * Start thread for sending message about client
 * 
 * @author zviproject
 *
 */
public class MessageClientEmailThread implements Runnable, ISending {

	private Session session;
	private List<RegistrationMessage> messages;
	private int emailIndex;
	private String messageTheme;
	private String messageText;
	private String username;
	private ISendingMessage iSendingMessage;
	private String uuid;

	public MessageClientEmailThread(Session session, List<RegistrationMessage> messages, int emailIndex,
			String messageTheme, String messageText, String username, ISendingMessage iSendingMessage, String uuid) {
		this.session = session;
		this.messages = messages;
		this.emailIndex = emailIndex;
		this.messageTheme = messageTheme;
		this.messageText = messageText;
		this.username = username;
		this.iSendingMessage = iSendingMessage;
		this.uuid = uuid;
	}

	/**
	 * Sending message on email abou registration<br>
	 * then store information in database
	 */
	@Override
	public void sendMessage() {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));

			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(messages.get(emailIndex).getClientEmail()));

			message.setSubject(messageTheme);
			message.setText(messageText);

			Transport.send(message);
			iSendingMessage.insertForConfirmEmail(uuid, messages.get(emailIndex).getClientId());

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		sendMessage();

	}

}