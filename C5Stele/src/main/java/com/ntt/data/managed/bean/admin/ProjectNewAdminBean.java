package com.ntt.data.managed.bean.admin;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.springframework.security.access.prepost.PreAuthorize;

import com.ntt.data.model.Project;
import com.ntt.data.service.IProjectService;

@ManagedBean(name = "projectNewAdminBean")
@PreAuthorize("hasRole('ROLE_ADMIN')")

public class ProjectNewAdminBean {

	String agencyID;
	
	@ManagedProperty(value="#{projectService}")
	IProjectService projectService;
	
	public void createProject(String newProjectName,String newProjectDescription){
		if(newProjectName!=""&&newProjectDescription!=""){
			Project newProject=new Project();
			newProject.setDescription(newProjectDescription);
			newProject.setName(newProjectName);
			newProject.setAgenciesId(Long.parseLong(agencyID));
			projectService.save(newProject);
		}else{
			ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot insert empty data","");
			FacesContext.getCurrentInstance().addMessage(null, message);
			extContext.getFlash().setKeepMessages(true);
		}
		
	}
	
	public IProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(IProjectService projectService) {
		this.projectService = projectService;
	}

	public String getAgencyID() {
		return agencyID;
	}


	public void setAgencyID(String agencyID) {
		this.agencyID = agencyID;
	}
}
