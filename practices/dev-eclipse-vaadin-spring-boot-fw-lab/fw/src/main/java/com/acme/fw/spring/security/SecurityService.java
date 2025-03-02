package com.acme.fw.spring.security;

import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.acme.fw.spring.exceptions.AcmeException;
import com.acme.fw.spring.model.User;
import com.acme.fw.spring.service.UserService;
import com.vaadin.flow.spring.security.AuthenticationContext;

@Component
public class SecurityService {
	
	private final AuthenticationContext authenticationContext;
	
	private final UserService userService;
	
	public SecurityService(AuthenticationContext authenticationContext, UserService userService) {
		
		this.authenticationContext = authenticationContext;
		
		this.userService = userService;
		
	}
	
	public UserDetails getAuthenticatedUser() {
		
		return authenticationContext.getAuthenticatedUser(UserDetails.class).get();
		
	}
	
	public void logout() {
		
		authenticationContext.logout();
		
	}
	
	public void login(String username) throws AcmeException{
		
		User user = userService.getEntityByLogin(username);
		
		if (user == null) {
			
			throw new AcmeException("Usuário não encontrado: " + username);
			
		}
		
		user.setLastAccess(new Date());
		
		userService.save(user);
		
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
