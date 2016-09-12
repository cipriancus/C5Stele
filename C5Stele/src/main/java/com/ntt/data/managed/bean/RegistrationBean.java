package com.ntt.data.managed.bean;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FlowEvent;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ntt.data.components.messages.MessageProducer;
import com.ntt.data.components.utils.EmailContent;
import com.ntt.data.model.Agency;
import com.ntt.data.model.Picture;
import com.ntt.data.model.Position;
import com.ntt.data.model.User;
import com.ntt.data.service.IAgencyService;
import com.ntt.data.service.IPictureService;
import com.ntt.data.service.IPositionService;
import com.ntt.data.service.IUserService;

@ManagedBean
@ViewScoped
public class RegistrationBean {

	private User user = new User();
	private Picture picture;
	private String email;
	private String valid = "true";
	private String username;

	@ManagedProperty(value = "#{userService}")
	private IUserService userService;

	@ManagedProperty(value = "#{pictureService}")
	private IPictureService pictureService;

	@ManagedProperty(value = "#{fileUploadManagedBean}")
	FileUploadManagedBean fileUploadManagedBean;

	@ManagedProperty(value = "#{mailProducer}")
	private MessageProducer mailProducer;

	public MessageProducer getMailProducer() {
		return mailProducer;
	}

	public void setMailProducer(MessageProducer mailProducer) {
		this.mailProducer = mailProducer;
	}

	@ManagedProperty(value = "#{agencyService}")
	private IAgencyService agencyService;

	@ManagedProperty(value = "#{positionService}")
	private IPositionService positionService;

	@ManagedProperty(value = "#{encoder}")
	private PasswordEncoder encode;

	private List<Agency> agencies;

	private List<Position> positions;

	private EmailContent content;

	private boolean skip;

	@PostConstruct
	public void init() {
		this.agencies = agencyService.getAll();
		this.positions = positionService.getAll();
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	public IPositionService getPositionService() {
		return positionService;
	}

	public void setPositionService(IPositionService positionService) {
		this.positionService = positionService;
	}

	public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	public IAgencyService getAgencyService() {
		return agencyService;
	}

	public void setAgencyService(IAgencyService agencyService) {
		this.agencyService = agencyService;
	}

	public List<Agency> getAgencies() {
		return agencies;
	}

	public void setAgencies(List<Agency> agencies) {
		this.agencies = agencies;
	}

	// For registration
	public String save() {
		fileUploadManagedBean.fileUploadSimple();
		user.setActive(false);
		user.setUid(UUID.randomUUID().toString());
		user.setRolesId(2L);
		user.setEmail(email);
		user.setUsername(username);
		user.setPassword(encode.encode(user.getPassword()));
		userService.saveUser(this.user);
		user = userService.getUserByUsername(user.getUsername());

		if (fileUploadManagedBean.getFile() != null) {
			picture = new Picture();
			picture.setBase64Image(fileUploadManagedBean.getImageContentsAsBase64());
			picture.setId_of_user(user.getId());
			picture.setTypeOfImage(fileUploadManagedBean.getTypeOfImage());
			pictureService.savePicture(picture);
		} else {
			picture = getPicture();
			picture.setId_of_user(user.getId());
			pictureService.savePicture(picture);
		}

		return chooseMessage(true);
	}

	// For reactivation
	public String reactivate(String email) {
		this.email=email;
		return chooseMessage(false);
	}

	public void sendMail() {
		// Sending confirmation mail
		content = new EmailContent();
		content.setTo(user.getEmail());
		content.setFrom("no-reply@nttdata.com");
		content.setBody("Welcome to 5 Star Collegue.");
		content.setSubject("Registration successful");

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("uid", this.user.getUid());
		model.put("user", this.user.getFirstName() + " " + this.user.getLastName());
		content.setTemplate("velocity/registrationTemplate.vm");
		content.setModel(model);
		mailProducer.sendMessage(content);

	}

	public String chooseMessage(boolean firstRegistration) {

		FacesMessage message = null;
		boolean reset = false;
		FacesMessage successMessage = new FacesMessage("Your confirmation mail will be sent shortly");

		if (email != null && userService.validateEmail(email, false)) {
			this.setUser(userService.getUserByEmail(email));
			sendMail();
			reset = true;
			message = successMessage;
		} else if (user.getEmail() != null && userService.validateEmail(user.getEmail(), false)) {
			this.setUser(userService.getUserByEmail(user.getEmail()));
			sendMail();
			reset = true;
			message = successMessage;
		} else {
			reset = false;
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Email not registered or already active","");
		}

		if (firstRegistration) {
			return showMessage(successMessage, true);
		}
		return showMessage(message, reset);
	}

	public String showMessage(FacesMessage msg, boolean goodToSend) {
		FacesContext.getCurrentInstance().addMessage(null, msg);

		if (goodToSend) {
			return "index.xhtml";
		}
		return null;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (userService.emailExists(email) == false) {
			this.email = email;

			this.valid="true";
			}else{
				this.valid="false";
				FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Email already exists", "");
				FacesContext.getCurrentInstance().addMessage(null, errorMessage);
			}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public String onFlowProcess(FlowEvent event) {
		if (skip) {
			skip = false; // reset in case user goes back
			return "confirm";
		} else {
			return event.getNewStep();
		}
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public Picture getPicture() {
		BufferedInputStream stream = null;
		picture = new Picture();
		if (fileUploadManagedBean.getFile() != null) {
			picture.setBase64Image(fileUploadManagedBean.getImageContentsAsBase64());
			picture.setId_of_user(user.getId());
			picture.setTypeOfImage(fileUploadManagedBean.getTypeOfImage());
		} else {
			try {
				if (user.getTitle() != null) {
					if (user.getTitle().equals("Mr"))
						stream = new BufferedInputStream(
								getClass().getClassLoader().getResourceAsStream("images/man_picture.jpg"));
					else
						stream = new BufferedInputStream(
								getClass().getClassLoader().getResourceAsStream("images/woman_picture.jpg"));
				} else {
					stream = new BufferedInputStream(
							getClass().getClassLoader().getResourceAsStream("images/man_picture.jpg"));
				}

				byte[] pictureByte = IOUtils.toByteArray(stream);
				picture.setBase64Image(Base64.getEncoder().encodeToString(pictureByte));
				picture.setId_of_user(user.getId());
				picture.setTypeOfImage("image/jpg");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	public EmailContent getContent() {
		return content;
	}

	public void setContent(EmailContent content) {
		this.content = content;
	}

	public IPictureService getPictureService() {
		return pictureService;
	}

	public void setPictureService(IPictureService pictureService) {
		this.pictureService = pictureService;
	}

	public FileUploadManagedBean getFileUploadManagedBean() {
		return fileUploadManagedBean;
	}

	public void setFileUploadManagedBean(FileUploadManagedBean fileUploadManagedBean) {
		this.fileUploadManagedBean = fileUploadManagedBean;
	}

	public PasswordEncoder getEncode() {
		return encode;
	}

	public void setEncode(PasswordEncoder encode) {
		this.encode = encode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		if (userService.existsUsername(username)== false) {
			this.username = username;
			this.valid="true";
		
			}else{
				this.valid="false";
				FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Username already exists", "");
				FacesContext.getCurrentInstance().addMessage(null, errorMessage);
			}
	}

}