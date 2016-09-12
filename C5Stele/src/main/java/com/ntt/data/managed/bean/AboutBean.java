package com.ntt.data.managed.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.ntt.data.model.AboutTab;
import com.ntt.data.service.IAboutService;

@ManagedBean(name = "aboutBean")
@RequestScoped
public class AboutBean {

	@ManagedProperty(value="#{aboutService}")
	IAboutService aboutService;
	
	public IAboutService getAboutService() {
		return aboutService;
	}

	public void setAboutService(IAboutService aboutService) {
		this.aboutService = aboutService;
	}

	public List<AboutTab> getData() {
	return aboutService.getAllAbout();
	}
}
