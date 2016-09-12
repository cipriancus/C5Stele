package com.ntt.data.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

public class SaveRequestAwareAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private static Logger logger = LoggerFactory.getLogger(SaveRequestAwareAuthenticationSuccessHandler.class);

	private RequestCache requestCache = new HttpSessionRequestCache();
	
	
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		logger.info("Handle success request");
		
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		
		if (savedRequest == null) {
			if (request.isUserInRole("ROLE_ADMIN")){
				getRedirectStrategy().sendRedirect(request, response, "/pages/admin.xhtml");
			} else {
				getRedirectStrategy().sendRedirect(request, response, "/pages/dashboard.xhtml");
			}
			
			return;
		}
		
		String targetURLParameter = getTargetUrlParameter();
		
		if (isAlwaysUseDefaultTargetUrl() || (targetURLParameter != null && StringUtils.hasText(request.getParameter(targetURLParameter)))) {
			requestCache.removeRequest(request, response);
			super.onAuthenticationSuccess(request, response, authentication);
			
			return;
		}
		
		clearAuthenticationAttributes(request);
		
		String targetURL = savedRequest.getRedirectUrl();
		
		//TODO refine targetURL
		
		getRedirectStrategy().sendRedirect(request, response, targetURL);
		
	}



	public void setRequestCache(RequestCache requestCache) {
		this.requestCache = requestCache;
	}
	
}
