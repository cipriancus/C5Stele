package com.ntt.data.managed.bean.admin;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.security.access.prepost.PreAuthorize;

import com.ntt.data.components.SessionData;
import com.ntt.data.model.User;
import com.ntt.data.service.IRoleService;

@ManagedBean(name = "adminPageViewBean")
@SessionScoped
@PreAuthorize("hasRole('ROLE_ADMIN')")

public class AdminPageViewBean {

	private String aboutUs = "false";
	private String contact = "false";
	private String faq = "false";
	private String news = "false";
	private String teams = "false";
	private String users = "false";
	private String periods = "true";
	private String projects = "false";

	public String getProjects() {
		return projects;
	}

	public void setProjects(String projects) {
		this.projects = projects;
	}

	@ManagedProperty(value = "#{sessionComponent}")
	private SessionData session;

	@ManagedProperty(value = "#{roleService}")
	private IRoleService roleService;

	public String getTeams() {
		return teams;
	}

	public void setTeams(String createTeams) {
		this.teams = createTeams;
	}

	public String getUsers() {
		return users;
	}

	public void setUsers(String editUsers) {
		this.users = editUsers;
	}

	public String getAboutUs() {
		return aboutUs;
	}

	public void setAboutUs(String aboutUs) {
		this.aboutUs = aboutUs;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getFaq() {
		return faq;
	}

	public void setFaq(String faq) {
		this.faq = faq;
	}

	public String getNews() {
		return news;
	}

	public void setNews(String news) {
		this.news = news;
	}

	public String getPeriods() {
		return periods;
	}

	public void setPeriods(String periods) {
		this.periods = periods;
	}

	public void makeContactVisible() {
		aboutUs = "false";
		contact = "true";
		faq = "false";
		news = "false";
		teams = "false";
		users = "false";
		periods = "false";
		projects = "false";
	}

	public void makeAboutVisible() {
		aboutUs = "true";
		contact = "false";
		faq = "false";
		news = "false";
		teams = "false";
		users = "false";
		periods = "false";
		projects = "false";
	}

	public void makeFaqVisible() {
		aboutUs = "false";
		contact = "false";
		faq = "true";
		news = "false";
		teams = "false";
		users = "false";
		periods = "false";
		projects = "false";
	}

	public void makeNewsVisible() {
		aboutUs = "false";
		contact = "false";
		faq = "false";
		news = "true";
		teams = "false";
		users = "false";
		periods = "false";
		projects = "false";
	}

	public void makeTeamsVisible() {
		aboutUs = "false";
		contact = "false";
		faq = "false";
		news = "false";
		teams = "true";
		users = "false";
		periods = "false";
		projects = "false";
	}

	public void makeUsersVisible() {
		aboutUs = "false";
		contact = "false";
		faq = "false";
		news = "false";
		teams = "false";
		users = "true";
		periods = "false";
		projects = "false";
	}

	public void makePeriodsVisible() {
		aboutUs = "false";
		contact = "false";
		faq = "false";
		news = "false";
		teams = "false";
		users = "false";
		periods = "true";
		projects = "false";
	}
	
	public void makeProjectsVisible() {
		aboutUs = "false";
		contact = "false";
		faq = "false";
		news = "false";
		teams = "false";
		users = "false";
		periods = "false";
		projects = "true";
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
}
