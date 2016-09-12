package com.ntt.data.managed.bean.admin;

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
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ntt.data.components.messages.MessageProducer;
import com.ntt.data.components.utils.EmailContent;
import com.ntt.data.managed.bean.FileUploadManagedBean;
import com.ntt.data.model.Picture;
import com.ntt.data.model.User;
import com.ntt.data.service.IPictureService;
import com.ntt.data.service.IRoleService;
import com.ntt.data.service.IUserService;

@ManagedBean
@ViewScoped
@PreAuthorize("hasRole('ROLE_ADMIN')")

public class UsersAdminBean {

	private List<User> users;

	private User selectedUser;

	private User newUser;

	private Picture picture;

	private List<User> filteredUsers;

	private String email;

	private String username;

	public EmailContent getContent() {
		return content;
	}

	public void setContent(EmailContent content) {
		this.content = content;
	}

	private EmailContent content;

	@ManagedProperty(value = "#{mailProducer}")
	private MessageProducer mailProducer;

	public MessageProducer getMailProducer() {
		return mailProducer;
	}

	public void setMailProducer(MessageProducer mailProducer) {
		this.mailProducer = mailProducer;
	}

	@ManagedProperty(value = "#{encoder}")
	private PasswordEncoder encode;

	@ManagedProperty(value = "#{userService}")
	private IUserService userService;

	@ManagedProperty(value = "#{pictureService}")
	private IPictureService pictureService;

	@ManagedProperty(value = "#{fileUploadManagedBean}")
	FileUploadManagedBean fileUploadManagedBean;

	@ManagedProperty(value = "#{roleService}")
	private IRoleService roleService;

	@PostConstruct
	public void init() {
		newUser = new User();
		this.users = userService.getAllEmployees();
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public Boolean rendered() {
		if (selectedUser == null)
			return false;
		return true;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public User getNewUser() {
		return newUser;
	}

	public void setNewUser(User newUser) {
		this.newUser = newUser;
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

	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	public IRoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	public List<User> getFilteredUsers() {
		return filteredUsers;
	}

	public void setFilteredUsers(List<User> filteredUsers) {
		this.filteredUsers = filteredUsers;
	}

	public PasswordEncoder getEncode() {
		return encode;
	}

	public void setEncode(PasswordEncoder encode) {
		this.encode = encode;
	}

	public void save() {
		userService.editInfo(selectedUser.getId(), username, selectedUser.getFirstName(), selectedUser.getLastName(),
				selectedUser.getTitle(), selectedUser.getPhoneNumber(), email, selectedUser.getAgenciesId(),
				selectedUser.getPositionsId(), selectedUser.getPositionsId(), selectedUser.getRolesId(),
				selectedUser.isActive());
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Your edit was successful!", null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void add() {
			newUser.setActive(true);
			newUser.setForgot_uid(UUID.randomUUID().toString());
			newUser.setPassword(encode.encode(newUser.getPassword()));
			userService.saveUser(newUser);
			newUser = userService.getUserByEmail(newUser.getEmail());
			Picture picture = getDefaultPicture(newUser);
			picture.setId_of_user(newUser.getId());
			pictureService.savePicture(picture);

			sendMail();

			this.users = userService.getAllEmployees();

			newUser = new User();

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "User added successfully!", null);
			FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void sendMail() {
		// Sending confirmation mail
		content = new EmailContent();
		content.setTo(newUser.getEmail());
		content.setFrom("no-reply@nttdata.com");
		content.setBody("Welcome to 5 Star Collegue.");
		content.setSubject("Registration successful");

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("uid", this.newUser.getForgot_uid());
		model.put("user", this.newUser.getFirstName() + " " + this.newUser.getLastName());
		model.put("username", this.newUser.getUsername());
		content.setTemplate("velocity/registrationByAdmin.vm");
		content.setModel(model);
		mailProducer.sendMessage(content);

	}

	public Picture renderPicture() {
		return pictureService.getPictureForUser(selectedUser);
	}

	public void savePic(FileUploadEvent e) {
		this.fileUploadManagedBean.fileUploadListener(e);
		if (fileUploadManagedBean.getFile() != null) {
			Picture picture = new Picture();
			picture.setBase64Image(fileUploadManagedBean.getImageContentsAsBase64());
			picture.setId_of_user(selectedUser.getId());
			picture.setTypeOfImage(fileUploadManagedBean.getTypeOfImage());
			if (pictureService.getPictureForUser(selectedUser) != null) {
				pictureService.deletePicture(selectedUser);
			}
			pictureService.savePicture(picture);
			System.out.println("save image");
		} else {
			Picture picture = getDefaultPicture(newUser);
			picture.setId_of_user(newUser.getId());
			pictureService.savePicture(picture);
		}
	}

	public void removePic() {
		if (pictureService.getPictureForUser(selectedUser) != null){
			pictureService.deletePicture(selectedUser);
			
			Picture picture=getDefaultPicture(selectedUser);
			picture.setId_of_user(selectedUser.getId());
			pictureService.savePicture(picture);
		}

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Picture removed", null);
		FacesContext.getCurrentInstance().addMessage(null, message);
		System.out.println("picture removed");

	}

	private Picture getDefaultPicture(User user) {
		BufferedInputStream stream = null;
		picture = new Picture();
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
		return picture;
	}

	public String getEmail() {
		email = selectedUser.getEmail();
		return email;
	}

	public void setEmail(String email) {
		if (userService.emailExists(email, selectedUser.getId()) == false) {
			this.email = email;
		} else {
			FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email already exists", "");
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
		}
	}

	public String getUsername() {
		username = selectedUser.getUsername();
		return username;
	}

	public void setUsername(String username) {
		if (userService.existsUsername(username, selectedUser.getId()) == false) {
			this.username = username;

		} else {
			FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username already exists", "");
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
		}
	}

}
