package com.ntt.data.components;

import java.util.Map;
import java.util.Map.Entry;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.ntt.data.components.utils.EmailContent;
import com.ntt.data.components.utils.MailSendException;


@Component("emailComponent")
public class EmailComponent{

	private static Logger logger = LoggerFactory.getLogger(EmailComponent.class);

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private VelocityEngine velocityEngine;
	
	public void sendMail(EmailContent content) throws MailSendException {
		logger.info("Send mail for " + content);

		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(mimeMessage, true);
			helper.setTo(content.getTo());
			helper.setFrom(content.getFrom());
			helper.setSubject(content.getSubject());
			helper.setText(content.getBody());
			addInLineResources(helper, content.getResources());
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			throw new MailSendException("Error sending the mail", e);
		}
	}
	
	public void sendTemplateMail(EmailContent content, String template, Map<String, Object> templateModel) throws MailSendException {
		logger.info("Send mail for " + content);

		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(mimeMessage, true);
			helper.setTo(content.getTo());
			helper.setFrom(content.getFrom());
			helper.setSubject(content.getSubject());
			helper.setText(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, template, "UTF-8", templateModel));
			addInLineResources(helper, content.getResources());
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			throw new MailSendException("Error sending the mail", e);
		}
	}

	private void addInLineResources(MimeMessageHelper helper, Map<String, Resource> resources)
			throws MessagingException {

		if (resources == null) {
			return;
		}

		for (Entry<String, Resource> r : resources.entrySet()) {
			helper.addInline(r.getKey(), r.getValue());
		}
	}

}
