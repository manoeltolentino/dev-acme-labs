package com.acme.fw.spring.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinServletRequest;

@Component
public class SecurityService {
	
	private static final String LOGOUT_SUCCESS_URL = "/";
	
	public UserDetails getAuthenticateUser() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		if (authentication != null) {
			Object principal = securityContext.getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				return (UserDetails)securityContext.getAuthentication().getPrincipal();
			}
		}
		
		//Anonymous or no authentication.
		return null;
	}
	
	public void logout() {
		UI.getCurrent().getPage().setLocation(LOGOUT_SUCCESS_URL);
		SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
		securityContextLogoutHandler.logout(VaadinServletRequest.getCurrent(), null, null);
	}

}
