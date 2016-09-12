package com.ntt.data.components.messages;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ntt.data.components.EmailComponent;
import com.ntt.data.components.utils.EmailContent;
import com.ntt.data.components.utils.MailSendException;

public class MessageConsumer implements MessageListener {

	private static Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

	private EmailComponent emailComponent;

	@Override
	public void onMessage(Message message) {
		ActiveMQObjectMessage msg = (ActiveMQObjectMessage) message;
		EmailContent emailContent = null;
		try {
			emailContent = (EmailContent)msg.getObject();
		} catch (JMSException e1) {
			logger.error("Invalid email content ");
		}
		
		if (emailContent != null) {
			try {

				if (emailContent.getTemplate() == null) {
					emailComponent.sendMail(emailContent);
				} else {
					emailComponent.sendTemplateMail(emailContent, emailContent.getTemplate(), emailContent.getModel());
				}
			} catch (MailSendException e) {
				logger.error("Invalid email content ");
			}
		}

	}

	public EmailComponent getEmailComponent() {
		return emailComponent;
	}

	public void setEmailComponent(EmailComponent emailComponent) {
		this.emailComponent = emailComponent;
	}

}