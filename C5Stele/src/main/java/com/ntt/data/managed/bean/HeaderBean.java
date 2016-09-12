package com.ntt.data.managed.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.ntt.data.components.SessionData;
import com.ntt.data.service.IRoleService;

@ManagedBean
@ViewScoped
public class HeaderBean {

	@ManagedProperty(value = "#{sessionComponent}")
	private SessionData session;
	
	@ManagedProperty(value = "#{roleService}")
	private IRoleService roleService;
	
	public boolean isNotLoggedIn() {
		return !(session.getLoggedUser() != null);
	}
	

	public SessionData getSession() {
		return session;
	}

	public void setSession(SessionData session) {
		this.session = session;
	}
	
	public String writeOutcome() {
		if (isNotLoggedIn()) {
			return "index.xhtml";
		}
		if (roleService.isAdmin(session.getLoggedUser().getRolesId())) {
			return "admin.xhtml";
		}
		return "dashboard.xhtml";
	}

	public IRoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}
}
