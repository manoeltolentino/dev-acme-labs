package com.acme.fw.spring.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

import com.acme.fw.vaadin.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.security.AuthenticationContext;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;

@Route(value = "user-logged-view", layout = MainLayout.class)
@Menu(order = 2, icon = LineAwesomeIconUrl.USER_SECRET_SOLID)
@RolesAllowed("ADMIN")
public class UserLoggedView extends VerticalLayout{
	
	public UserLoggedView(AuthenticationContext authenticationContext) {
		add(new H2("Everyone can see this"));
		
		authenticationContext.getAuthenticatedUser(UserDetails.class).ifPresent(user -> {
			boolean isAdmin = user.getAuthorities().stream()
					.anyMatch(grantedAuthority -> "ROLE_ADMIN".equals(grantedAuthority.getAuthority()));
			if (isAdmin) {
				add(new Button("Admin button"));
			}
			else {
				add(new H2("You are not an admin"));
			}
			
			String login = user.getUsername();
			add(login);
		});
	}

}
