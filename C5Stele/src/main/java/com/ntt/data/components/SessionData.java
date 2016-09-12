package com.ntt.data.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntt.data.model.User;
import com.ntt.data.service.IUserService;

/**
 * create a component to store all session data
 * 
 * @author bernard.ciurariu
 *
 */
@Component(value="sessionComponent")
@Scope(value = "session")
public class SessionData {
	
	private static Logger logger = LoggerFactory.getLogger(SessionData.class); 

	private String sessionId;

	private User loggedUser;
	
	private String forgot_UID;
	
	@Autowired
	IUserService userService;

	public String getForgot_UID() {
		return forgot_UID;
	}

	public void setForgot_UID(String forgot_UID) {
		this.forgot_UID = forgot_UID;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public User getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}

	public boolean isLogged() {
		
		boolean isLogged = loggedUser != null ? true : false;
		
		logger.debug("User is logged: {}", isLogged);
		
		return isLogged;
	}
	
	public boolean isAdmin(){
		return loggedUser.getRolesId()==1? true:false;
	}

}
