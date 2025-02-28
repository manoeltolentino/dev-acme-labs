package com.acme.fw.spring.security;


import org.vaadin.lineawesome.LineAwesomeIconUrl;

import com.acme.fw.vaadin.views.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Layout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.PermitAll;

@PageTitle("Registration")
@Route(value = "/registration", layout = MainLayout.class)
@Menu(order = 2, icon = LineAwesomeIconUrl.USER)
@PermitAll
public class RegistrationView extends VerticalLayout{
	
	public RegistrationView() {
		RegistrationForm registrationForm = new RegistrationForm();
		
		setHorizontalComponentAlignment(Alignment.CENTER, registrationForm);
		
		add(registrationForm);
		
		RegistrationFormBinder registrationFormBinder = new RegistrationFormBinder(registrationForm);
		registrationFormBinder.addBindingAndValidation();
	}

}
