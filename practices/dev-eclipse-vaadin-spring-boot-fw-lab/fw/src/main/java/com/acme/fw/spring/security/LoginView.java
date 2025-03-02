package com.acme.fw.spring.security;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("login")
@PageTitle("Login")
@AnonymousAllowed
public class LoginView extends VerticalLayout implements BeforeEnterObserver{
	
	private LoginForm loginForm = new LoginForm();
	
	private final SecurityService securityService;
	
	public LoginView(SecurityService securityService) {
		
		this.securityService = securityService;
		
		addClassName("login-view");
		setSizeFull();
		
		setJustifyContentMode(JustifyContentMode.CENTER);
		setAlignItems(Alignment.CENTER);
		
		loginForm.setAction("login");
		
		loginForm.addLoginListener(event -> {
			
			String username = event.getUsername();
			
			try {
				securityService.login(username);
			}
			catch(Exception e) {
				
				//TODO >> Mesmo com erro, está indo para o home. Impedir continuação.
				
				Notification notification = Notification.show("Erro ao salvar login: " + e.getMessage());
				notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
				
				loginForm.setError(true);
				
			}
				
		});
		
		
		add(new H1("Spring Vaadin Framework"), loginForm);
		
	}
	
	public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
		
		if (beforeEnterEvent.getLocation()
				.getQueryParameters()
				.getParameters()
				.containsKey("error")) {
			loginForm.setError(true);
		}
		
	}

}
