package com.acme.fw.spring.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.vaadin.flow.spring.security.AuthenticationContext;

@Component
public class SecurityService {
	
	private final AuthenticationContext authenticationContext;
	
	public SecurityService(AuthenticationContext authenticationContext) {
		
		this.authenticationContext = authenticationContext;
		
	}
	
	public UserDetails getAuthenticatedUser() {
		
		return authenticationContext.getAuthenticatedUser(UserDetails.class).get();
		
	}
	
	public void logout() {
		
		authenticationContext.logout();
		
	}
	
	
	/*
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
	
	*/

}
