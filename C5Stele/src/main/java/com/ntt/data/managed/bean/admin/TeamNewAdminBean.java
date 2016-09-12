package com.ntt.data.managed.bean.admin;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;
import org.springframework.security.access.prepost.PreAuthorize;

import com.ntt.data.model.Team;
import com.ntt.data.model.User;
import com.ntt.data.service.IAgencyService;
import com.ntt.data.service.IProjectService;
import com.ntt.data.service.ITeamService;
import com.ntt.data.service.IUserService;

@ManagedBean(name = "teamNewAdminBean")
@ViewScoped
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class TeamNewAdminBean {
	private DualListModel<User> allMembers;

	private String projectID;

	private List<User> currentMembers;

	@ManagedProperty(value = "#{teamService}")
	ITeamService teamService;

	@ManagedProperty(value = "#{userService}")
	IUserService userService;

	@ManagedProperty(value = "#{agencyService}")
	IAgencyService agencyService;

	@ManagedProperty(value = "#{projectService}")
	IProjectService projectService;

	private Team newTeam;

	public Team getNewTeam() {
		return newTeam;
	}

	public void setNewTeam(Team newTeam) {
		this.newTeam = newTeam;
	}

	public Team getSelectedTeam() {
		return newTeam;
	}

	public void setSelectedTeam(Team newTeam) {
		this.newTeam = newTeam;
	}

	public ITeamService getTeamService() {
		return teamService;
	}

	public void setTeamService(ITeamService teamService) {
		this.teamService = teamService;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public DualListModel<User> getAllMembers() {
		if (currentMembers == null) {
			currentMembers = new ArrayList<User>();
		}
		allMembers = new DualListModel<User>(userService.getAllActiveEmployees(), currentMembers);
		return allMembers;
	}

	public void setAllMembers(DualListModel<User> allMembers) {
		this.allMembers = allMembers;
	}

	public void onTransfer(TransferEvent event) {
		for (Object item : event.getItems()) {

			int iterator = ((String) item).indexOf("username=") + 9;
			while (((String) item).charAt(iterator) != ',') {
				iterator++;
			}
			String username = ((String) item).substring(((String) item).indexOf("username") + 9, iterator);

			iterator = ((String) item).indexOf("password=") + 9;
			while (((String) item).charAt(iterator) != ',') {
				iterator++;
			}
			String password = ((String) item).substring(((String) item).indexOf("password") + 9, iterator);

			currentMembers.add(userService.getUserByUsername(username));
		}
	}

	public String getProjectID() {
		return projectID;
	}

	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}

	public List<User> getCurrentMembers() {
		return currentMembers;
	}

	public void setCurrentMembers(List<User> currentMembers) {
		this.currentMembers = currentMembers;
	}

	public IAgencyService getAgencyService() {
		return agencyService;
	}

	public void setAgencyService(IAgencyService agencyService) {
		this.agencyService = agencyService;
	}

	public IProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(IProjectService projectService) {
		this.projectService = projectService;
	}

	public void createTeam(String newTeamName) {
		if (newTeamName != ""&&projectID!=null) {

			Team newTeam = new Team();
			newTeam.setName(newTeamName);
			newTeam.setProjectsId(Long.parseLong(projectID));

			for (User iterator : currentMembers) {
				iterator.setAgenciesId(projectService.getProjectById(Long.parseLong(projectID)).getAgenciesId());
			}

			teamService.save(newTeam);

			newTeam = teamService.getTeamByName(newTeam.getName());

			for (User iterator : currentMembers) {
				iterator.setTeamsId(newTeam.getId());
				userService.saveUser(iterator);
			}
			cancel();// pentru clear input
		}else{
			ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot insert empty data","");
			FacesContext.getCurrentInstance().addMessage(null, message);
			extContext.getFlash().setKeepMessages(true);
		}
	}

	public void cancel() {
		this.projectID = null;
		this.currentMembers.clear();
	}
}
