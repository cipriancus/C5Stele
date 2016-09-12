package com.ntt.data.components.messages;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.ntt.data.components.utils.EmailContent;

@Component("mailProducer")
public class MessageProducer {

	@Autowired
	@Qualifier("producerTemplate")
	private JmsTemplate jmsTemplate;

	@Autowired
	private Destination destination;

	public void sendMessage(final EmailContent emailContent) {

		System.out.println("Producer sends "  + emailContent);

		jmsTemplate.send(destination, new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				
				return session.createObjectMessage(emailContent);
			}
		});

	}

}