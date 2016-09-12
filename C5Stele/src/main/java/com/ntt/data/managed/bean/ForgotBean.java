package com.ntt.data.managed.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import com.ntt.data.components.messages.MessageProducer;
import com.ntt.data.components.utils.EmailContent;
import com.ntt.data.model.User;
import com.ntt.data.service.IUserService;

/**
 * TO DO
 * 
 * @author ciprian.cusmuliuc
 *
 */
@ManagedBean
@RequestScoped
public class ForgotBean {
	private String email;

	@ManagedProperty(value = "#{mailProducer}")
	private MessageProducer mailProducer;

	public MessageProducer getMailProducer() {
		return mailProducer;
	}

	public void setMailProducer(MessageProducer mailProducer) {
		this.mailProducer = mailProducer;
	}

	@ManagedProperty(value = "#{userService}")
	private IUserService userService;

	private EmailContent content;

	public EmailContent getContent() {
		return content;
	}

	public void setContent(EmailContent content) {
		this.content = content;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void sendMail(String email) {
		// Sending confirmation mail
		content = new EmailContent();
		content.setTo(email);
		content.setFrom("no-reply@nttdata.com");
		content.setSubject("Password reset");
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("user", userService.getUserByEmail(email).getFirstName());

		if (userService.getUserByEmail(email) != null) {

			User user = userService.getUserByEmail(email);
			user.setForgot_uid(UUID.randomUUID().toString());
			userService.saveUser(user);

			model.put("forgot_uid", user.getForgot_uid());
			content.setTemplate("velocity/passwordReset.vm");
			content.setModel(model);
			mailProducer.sendMessage(content);
		}
	}

	public String send() {
		RequestContext context = RequestContext.getCurrentInstance();
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		FacesMessage message = null;
		boolean reset = false;

		if (email != null && userService.validateEmail(email, true)) {
			reset = true;
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Reset link has been sent", email);
		} else {
			reset = false;
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Email not registered", "");
		}

		sendMail(email);

		FacesContext.getCurrentInstance().addMessage(null, message);
		context.addCallbackParam("reset", reset);
		ec.getFlash().setKeepMessages(true);

		if (reset) {
			return "index.xhtml";
		}
		return null;
	}
}
