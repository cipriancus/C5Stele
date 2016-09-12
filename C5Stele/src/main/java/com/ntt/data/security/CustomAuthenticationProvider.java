package com.ntt.data.security;

import java.util.ArrayList;
import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.ntt.data.components.SessionData;
import com.ntt.data.model.User;
import com.ntt.data.service.IRoleService;
import com.ntt.data.service.IUserService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private IUserService userService;

	@Autowired
	private IRoleService roleService;

	@Autowired
	private PasswordEncoder encode;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();

		FacesMessage message = null;

		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			throw new BadCredentialsException("Empty credentials");
		}

		User userByCredentials = userService.getUserByUsername(username);

		if (userByCredentials == null || !userByCredentials.isActive() || !encode.matches(password, userByCredentials.getPassword())) {
			throw new BadCredentialsException("User does not exist!");
		}

		message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SessionData session = (SessionData)((HttpSession)facesContext.getExternalContext().getSession(true)).getAttribute("sessionComponent");
		
		session.setLoggedUser(userByCredentials);
		FacesContext.getCurrentInstance().addMessage(null, message);

		boolean admin = roleService.isAdmin(userByCredentials.getRolesId());

		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		if (admin) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		} else {
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		}

		return new UsernamePasswordAuthenticationToken(username, null, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {

		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
