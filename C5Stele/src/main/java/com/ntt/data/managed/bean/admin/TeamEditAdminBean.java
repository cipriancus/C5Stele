package com.ntt.data.managed.bean.admin;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;
import org.springframework.security.access.prepost.PreAuthorize;

import com.ntt.data.model.Team;
import com.ntt.data.model.User;
import com.ntt.data.service.IProjectService;
import com.ntt.data.service.ITeamService;
import com.ntt.data.service.IUserService;

@ManagedBean(name = "teamEditAdminBean")
@ViewScoped
@PreAuthorize("hasRole('ROLE_ADMIN')")

public class TeamEditAdminBean {

	private DualListModel<User> allMembers;
	private List<User> currentMembers;

	@ManagedProperty(value = "#{teamService}")
	ITeamService teamService;

	@ManagedProperty(value = "#{userService}")
	IUserService userService;

	@ManagedProperty(value = "#{projectService}")
	IProjectService projectService;

	private Team selectedTeam;

	private String projectID;

	public Team getSelectedTeam() {
		return selectedTeam;
	}

	public void setSelectedTeam(Team selectedTeam) {
		this.selectedTeam = selectedTeam;
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
		currentMembers = new ArrayList<User>();
		currentMembers.addAll(selectedTeam.getMembers());

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

	public IProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(IProjectService projectService) {
		this.projectService = projectService;
	}

	public void saveTeam(String projectName) {
		selectedTeam.setName(projectName);
		selectedTeam.setProjectsId(Long.parseLong(projectID));

		for (User iterator : currentMembers) {
			iterator.setAgenciesId(projectService.getProjectById(Long.parseLong(projectID)).getAgenciesId());
		}

		for (User iterator : currentMembers) {
			iterator.setTeamsId(selectedTeam.getId());
			userService.saveUser(iterator);
		}

		teamService.save(selectedTeam);
	}

	public void deleteTeam() {
		Team editTeam = teamService.getTeamById((selectedTeam.getId()));

		for (User iterator : currentMembers) {
			iterator.setTeamsId(null);
			userService.saveUser(iterator);
		}

		editTeam.setMembers(currentMembers);
		teamService.delete(editTeam);
	}
}
