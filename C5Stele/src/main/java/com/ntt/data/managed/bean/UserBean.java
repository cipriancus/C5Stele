package com.ntt.data.managed.bean;

import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ntt.data.components.SessionData;
import com.ntt.data.model.User;
import com.ntt.data.service.IRoleService;
import com.ntt.data.service.IUserService;

@ManagedBean(name = "userMB")
@SessionScoped
public class UserBean {

	private static Logger logger = LoggerFactory.getLogger(UserBean.class);

	@ManagedProperty(value = "#{userService}")
	private IUserService userService;

	@ManagedProperty(value = "#{sessionComponent}")
	private SessionData session;

	@ManagedProperty(value = "#{roleService}")
	private IRoleService roleService;

	private User user;

	private String locale;
	
	private Locale localeBean;

	public UserBean() {
		
	}
	
	@PostConstruct
	void init() {
		localeBean = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
		FacesContext.getCurrentInstance().getViewRoot().setLocale(localeBean);
	}

	public User getLoggedUser() {

		logger.info("Get the user for dfds INFO level");

		if (user == null) {
			user = userService.getUserById(1L);
			logger.warn("The use is unknown");
		}

		logger.info("Get the user is {}", user);

		return user;
	}

	/**
	 * 
	 * @param locale
	 */
	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getLocale() {
		return locale;
	}

	public String updateLocale() {
		if (locale != null) {
			String[] tokens = locale.split("_");

			if (tokens.length == 2) {
				localeBean = new Locale(tokens[0], tokens[1]);
				FacesContext.getCurrentInstance().getViewRoot().setLocale(localeBean);
			}

		}

		return "";
	}

	public Locale getLocaleBean() {
		return localeBean;
	}

	public void setLocaleBean(Locale localeBean) {
		this.localeBean = localeBean;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public SessionData getSession() {
		return session;
	}

	public void setSession(SessionData session) {
		this.session = session;
	}

	public boolean isLoggedIn() {
		return session.isLogged();
	}

	public IRoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	public String home() {
		if (session.getLoggedUser() == null) {
			return "/pages/index.xhtml";
		}

		if (roleService.isAdmin(session.getLoggedUser().getRolesId())) {
			return "admin.xhtml";
		}
		return "dashboard.xhtml";
	}

}
