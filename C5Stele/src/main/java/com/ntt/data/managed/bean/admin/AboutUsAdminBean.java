package com.ntt.data.managed.bean.admin;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.springframework.security.access.prepost.PreAuthorize;

import com.ntt.data.components.SessionData;
import com.ntt.data.model.AboutTab;
import com.ntt.data.service.IAboutService;

@ManagedBean(name = "aboutUsAdminBean")
@SessionScoped
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AboutUsAdminBean {

	@ManagedProperty(value = "#{aboutService}")
	IAboutService aboutService;
	
	@ManagedProperty(value = "#{sessionComponent}")
	SessionData session;
	
	private Long id;
	private String title;
	private String text;
	
	public IAboutService getAboutService() {
		return aboutService;
	}

	public void setAboutService(IAboutService aboutService) {
		this.aboutService = aboutService;
	}

	public SessionData getSession() {
		return session;
	}

	public void setSession(SessionData session) {
		this.session = session;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if(title!="")
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		if(text!="")
		this.text = text;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void postNewAboutUs() {
		if (title != null && text != null) {
			AboutTab aboutTab = new AboutTab(title, text);
			aboutService.saveAboutTab(aboutTab);
			
	        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "The new About Us tab has been inserted successfully","");
			FacesContext.getCurrentInstance().addMessage(null, message);
			extContext.getFlash().setKeepMessages(true);

		}else{
			    ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot insert empty data","");
				FacesContext.getCurrentInstance().addMessage(null, message);
				extContext.getFlash().setKeepMessages(true);
		}
	}

	public void updateAboutUs(Long id,String title, String text) {
		AboutTab aboutTab=new AboutTab();
		aboutTab.setId(id);
		aboutTab.setName(title);
		aboutTab.setText(text);
		aboutService.saveAboutTab(aboutTab);
		
		ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "The existing About Us tab has been updated successfully","");
		FacesContext.getCurrentInstance().addMessage(null, message);
		extContext.getFlash().setKeepMessages(true);
	}
	
	public void deleteAboutUs(Long id) {
		AboutTab aboutTab=aboutService.getAboutTabById(id);
		aboutService.deleteAboutTab(aboutTab);
		
		ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "The existing About Us tab has been deleted successfully","");
		FacesContext.getCurrentInstance().addMessage(null, message);
		extContext.getFlash().setKeepMessages(true);
	}
}
