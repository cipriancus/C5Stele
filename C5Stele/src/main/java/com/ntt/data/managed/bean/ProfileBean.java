package com.ntt.data.managed.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ntt.data.components.SessionData;
import com.ntt.data.model.Agency;
import com.ntt.data.model.Picture;
import com.ntt.data.model.Position;
import com.ntt.data.model.Team;
import com.ntt.data.model.User;
import com.ntt.data.service.IAgencyService;
import com.ntt.data.service.IPictureService;
import com.ntt.data.service.IPositionService;
import com.ntt.data.service.IRoleService;
import com.ntt.data.service.ITeamService;
import com.ntt.data.service.IUserService;

@ManagedBean
@ViewScoped
@PreAuthorize("hasRole('ROLE_ADMIN')")

public class ProfileBean {

	private User user;
	
	@Autowired
	private PasswordEncoder encode;

	@ManagedProperty(value = "#{userService}")
	private IUserService userService;

	@ManagedProperty(value = "#{pictureService}")
	private IPictureService pictureService;

	@ManagedProperty(value = "#{fileUploadManagedBean}")
	FileUploadManagedBean fileUploadManagedBean;

	@ManagedProperty(value = "#{agencyService}")
	private IAgencyService agencyService;

	@ManagedProperty(value = "#{positionService}")
	private IPositionService positionService;

	@ManagedProperty(value = "#{teamService}")
	private ITeamService teamService;
	
	@ManagedProperty(value = "#{roleService}")
	private IRoleService roleService;

	@ManagedProperty(value = "#{sessionComponent}")
	private SessionData session;

	private List<Agency> agencies;

	private List<Position> positions;

	private List<Team> teams;

	private String newPassword;

	private String oldPassword;
	
	private String email;
	
	private String valid="true";

	@PostConstruct
	public void init() {
		if (!session.isLogged()) {
			FacesContext fc = FacesContext.getCurrentInstance();
			NavigationHandler nh = fc.getApplication().getNavigationHandler();
			nh.handleNavigation(fc, null, "index?faces-redirect=true");
		} else {
			this.agencies = agencyService.getAll();
			this.positions = positionService.getAll();
			this.teams = teamService.getAll();
			this.user = userService.getUserById(session.getLoggedUser().getId());
			if (pictureService.getPictureForUser(user) != null) {
				this.fileUploadManagedBean.setTypeOfImage(user.getPicture().getTypeOfImage());
				this.fileUploadManagedBean.setImageContentsAsBase64(user.getPicture().getBase64Image());
			}
		}
	}

	public void savePass() {
		if (userService.changePassword(user.getId(), newPassword,oldPassword)==true) {	
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "The new password has been saved","");
			FacesContext.getCurrentInstance().addMessage(null, message);
			session.setLoggedUser(user);
		}else{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error, current password ","");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		
		
	}

	public void save() {
		userService.editInfo(user.getId(), user.getFirstName(), user.getLastName(), user.getTitle(),
				user.getPhoneNumber(), user.getEmail(), user.getAgenciesId(), user.getPositionsId(), user.getTeamsId());
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "The new informations have been saved","");
		FacesContext.getCurrentInstance().addMessage(null, message);
		session.setLoggedUser(userService.getUserById(user.getId()));

	}

	public void savePic(FileUploadEvent e) {
		this.fileUploadManagedBean.fileUploadListener(e);
		if (fileUploadManagedBean.getFile() != null) {
			Picture picture = new Picture();
			picture.setBase64Image(fileUploadManagedBean.getImageContentsAsBase64());
			picture.setId_of_user(user.getId());
			picture.setTypeOfImage(fileUploadManagedBean.getTypeOfImage());
			if (pictureService.getPictureForUser(user) != null) {
				pictureService.deletePicture(user);
			}
			pictureService.savePicture(picture);
			System.out.println("save image");
		} else {
			System.out.println("no image");
		}
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
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

	public IAgencyService getAgencyService() {
		return agencyService;
	}

	public void setAgencyService(IAgencyService agencyService) {
		this.agencyService = agencyService;
	}

	public IPositionService getPositionService() {
		return positionService;
	}

	public void setPositionService(IPositionService positionService) {
		this.positionService = positionService;
	}

	public List<Agency> getAgencies() {
		return agencies;
	}

	public void setAgencies(List<Agency> agencies) {
		this.agencies = agencies;
	}

	public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	public ITeamService getTeamService() {
		return teamService;
	}

	public void setTeamService(ITeamService teamService) {
		this.teamService = teamService;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public SessionData getSession() {
		return session;
	}

	public void setSession(SessionData session) {
		this.session = session;
	}

	public IRoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	public String getEmail() {
		email=user.getEmail();
		return email;
	}

	public void setEmail(String email) {
		if (userService.emailExists(email,user.getId()) == false) {
			this.email = email;

			this.setValid("true");
			}else{
				this.setValid("false");
				FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Email already exists", "");
				FacesContext.getCurrentInstance().addMessage(null, errorMessage);
			}
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}
}
