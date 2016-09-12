package com.ntt.data.managed.bean.admin;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.springframework.security.access.prepost.PreAuthorize;

import com.ntt.data.components.SessionData;
import com.ntt.data.model.Project;
import com.ntt.data.model.Team;
import com.ntt.data.service.IProjectService;
import com.ntt.data.service.ITeamService;

@ManagedBean(name = "projectEditAdminBean")
@ViewScoped
@PreAuthorize("hasRole('ROLE_ADMIN')")

public class ProjectEditAdminBean {

	@ManagedProperty(value="#{projectService}")
	IProjectService projectService;
	
	@ManagedProperty(value="#{sessionComponent}")
	SessionData sessionData;
	
	public ITeamService getTeamService() {
		return teamService;
	}

	public void setTeamService(ITeamService teamService) {
		this.teamService = teamService;
	}

	List<Project> projects;

	Project selectedProject;
	
	@ManagedProperty(value = "#{teamService}")
	ITeamService teamService;
	
	
	public void deleteProject(){
		
		for(Team iterator:teamService.getTeamsForProjectID(selectedProject.getId())){
			iterator.setProjectsId(null);
			teamService.save(iterator);
		}
		projectService.delete(selectedProject);
		
		
	}
	
	public void saveProject(String projectName, String projectDescription){
		selectedProject.setName(projectName);
		selectedProject.setDescription(projectDescription);
		selectedProject.setAgenciesId(selectedProject.getAgenciesId());
		projectService.save(selectedProject);
	}

	
	public void cancel(){
		
	}
	

	public SessionData getSessionData() {
		return sessionData;
	}

	public void setSessionData(SessionData sessionData) {
		this.sessionData = sessionData;
	}

	
	public IProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(IProjectService projectService) {
		this.projectService = projectService;
	}

	public Project getSelectedProject() {
		return selectedProject;
	}

	public void setSelectedProject(Project selectedProject) {
		this.selectedProject = selectedProject;
	}

	public List<Project> getProjects() {
		projects=projectService.getAll();
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
}
