package com.ntt.data.managed.bean;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.ntt.data.components.SessionData;
import com.ntt.data.service.IRoleService;
import com.ntt.data.service.IUserService;

@ManagedBean
@ViewScoped
public class LoginBean {
	private String username;
    
    private String password;
    
    @ManagedProperty(value="#{userService}")
	private IUserService userService;
    
    @ManagedProperty(value="#{roleService}")
	private IRoleService roleService;
    
    @ManagedProperty(value = "#{sessionComponent}")
	private SessionData session;
 
    public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
    
	public SessionData getSession() {
		return session;
	}

	public void setSession(SessionData session) {
		this.session = session;
	}
   
    public String login() throws ServletException, IOException {
//        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
//        FacesMessage message = null;
//        boolean loggedIn = false;
//         
//        if(username != null && password != null && userService.getUserByCredentials(username, password) != null && userService.getUserByCredentials(username, password).isActive()==true) {
//	            loggedIn = true;
//	            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);
//	            session.setLoggedUser(userService.getUserByCredentials(username, password));
//        } else {
//            loggedIn = false;
//            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
//        }
//         
//        FacesContext.getCurrentInstance().addMessage(null, message);
//        
//        
//        if (loggedIn) {
//        	extContext.getFlash().setKeepMessages(true);
//        	if (roleService.isAdmin(session.getLoggedUser().getRolesId())) {
//        		return "admin?faces-redirect=true";
//        	} else {
//        		return "dashboard?faces-redirect=true";
//        	}
//        }
    	
    	ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

		// reset previous security error message
		context.getSessionMap().put("securityErrorMessage", null);

		// redirect to spring security
		RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher("/j_spring_security_check");

		dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());

		FacesContext.getCurrentInstance().responseComplete();

		return null;
    }   
    
    public String logout() throws ServletException, IOException {
//        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "You have been signed out", "");
//        
//        
//        FacesContext.getCurrentInstance().addMessage(null, message);
////        ec.getFlash().setKeepMessages(true);
//        
////        return "index?faces-redirect=true";
//            	
//    
////        ec.invalidateSession();
//        
//        session.setLoggedUser(null);
//        session.setSessionId(null);
//        
   	//redirectto spring security
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

		RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher("/j_spring_security_logout");

		dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());

		FacesContext.getCurrentInstance().responseComplete();

		return null;
    }

	public IRoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}
}