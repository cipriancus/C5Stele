package com.ntt.data.managed.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ntt.data.components.SessionData;
import com.ntt.data.model.User;
import com.ntt.data.service.IUserService;

@ManagedBean(name = "changePasswordBean")
public class ChangePasswordBean {
	@ManagedProperty(value = "#{sessionComponent}")
	private SessionData session;

	@ManagedProperty(value = "#{userService}")
	private IUserService userService;
	
	@ManagedProperty(value = "#{encoder}")
	private PasswordEncoder encode;

	String password;

	public SessionData getSession() {
		return session;
	}

	public void setSession(SessionData session) {
		this.session = session;
	}

	public IUserService getUserService() {
		return userService;
	}

	public PasswordEncoder getEncode() {
		return encode;
	}

	public void setEncode(PasswordEncoder encode) {
		this.encode = encode;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String change(){
		if(password!=null && session.getForgot_UID()!=null){
			User user=userService.getUserByForgotUID(session.getForgot_UID());
			user.setPassword(encode.encode(password));
			user.setForgot_uid(null);
			userService.saveUser(user);
		}
		return "index";
	}
}
