package com.ntt.data.managed.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.ntt.data.components.SessionData;
import com.ntt.data.components.messages.MessageProducer;
import com.ntt.data.components.utils.EmailContent;
import com.ntt.data.model.ContactInformation;
import com.ntt.data.model.User;
import com.ntt.data.service.IContactService;
import com.ntt.data.service.IUserService;

@ManagedBean(name = "contactBean")
@ViewScoped
public class ContactBean {

	private FacesContext context;

	private ResourceBundle text;

	@ManagedProperty(value = "#{sessionComponent}")
	private SessionData session;

	@ManagedProperty(value = "#{mailProducer}")
	private MessageProducer mailProducer;

	@ManagedProperty(value = "#{userService}")
	private IUserService userService;

	@ManagedProperty(value = "#{contactService}")
	private IContactService contactService;

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public MessageProducer getMailProducer() {
		return mailProducer;
	}

	public void setMailProducer(MessageProducer mailProducer) {
		this.mailProducer = mailProducer;
	}

	/**
	 * folosit pentru numele din formular
	 */
	private String name;
	/**
	 * folosit pentru email-ul din formular
	 */
	private String email;
	/**
	 * folosit pentru mesajul din formular
	 */
	private String message;

	public String getName() {
		if (session != null) {
			if (session.isLogged() == true) {
				User loggedUser = session.getLoggedUser();
				this.name = loggedUser.getFirstName() + " " + loggedUser.getLastName();
			}
		}
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		if (session != null) {
			if (session.isLogged() == true) {
				User loggedUser = session.getLoggedUser();
				this.email = loggedUser.getEmail();
			}
		}
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ContactBean() {
		context = FacesContext.getCurrentInstance();
		text = context.getApplication().evaluateExpressionGet(context, "#{msg}", ResourceBundle.class);
	}

	public SessionData getSession() {
		return session;
	}

	public void setSession(SessionData session) {
		this.session = session;
	}

	public List<ContactInformation> getData() {
		return contactService.getAllContacts();
	}

	public String sendMessage() {
		if (message != null & email != null) {
			if (this.name.equals("")) {
				if (userService.validateEmail(email, true) == true)
					this.name = userService.getUserByEmail(this.email).getFirstName();
				else
					this.name = "a customer";
			}
			EmailContent email = new EmailContent();
			email.setSubject("Contact message from " + name);
			email.setBody(message);
			email.setFrom(this.email);
			email.setTo("cipriancus@gmail.com");// email ul aplicatiei

			/*
			 * Se trimite mail ul de la client la admin
			 */
			mailProducer.sendMessage(email);

			/*
			 * Se trimite mail ul catre client pentru a sti ca va fi contactat
			 */
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("user", name);

			email = new EmailContent();
			email.setSubject("Contact message sent");
			email.setFrom("cipriancus@gmail.com");
			email.setTo(this.email);
			model.put("linkFAQ", "http://10.227.96.28:8080/C5Stele");
			email.setTemplate("velocity/contactTemplate.vm");
			email.setModel(model);
			mailProducer.sendMessage(email);

		}

		FacesMessage msg = new FacesMessage("Email sent successful", "You will be contacted as soon as possible");
		FacesContext.getCurrentInstance().addMessage(null, msg);

		if (session.isLogged() == true)
			return "dashboard";
		else
			return "index";
	}

	public IContactService getContactService() {
		return contactService;
	}

	public void setContactService(IContactService contactService) {
		this.contactService = contactService;
	}

}
