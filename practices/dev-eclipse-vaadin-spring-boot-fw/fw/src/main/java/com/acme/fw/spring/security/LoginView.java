package com.acme.fw.spring.security;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;

public class LoginView extends VerticalLayout implements BeforeEnterObserver{
	
	private LoginForm loginForm = new LoginForm();
	
	public LoginView() {
		addClassName("login-view");
		setSizeFull();
		
		setJustifyContentMode(JustifyContentMode.CENTER);
		setAlignItems(Alignment.CENTER);
		
		loginForm.setAction("login");
		
		add(new H1("Test application"), loginForm);
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
