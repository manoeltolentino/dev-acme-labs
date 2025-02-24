package com.example.security.views.registration;

import org.vaadin.lineawesome.LineAwesomeIconUrl;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Registration")
@Route("/registration")
@Menu(order = 2, icon = LineAwesomeIconUrl.USER)
public class RegistrationView extends VerticalLayout{
	
	public RegistrationView() {
		RegistrationForm registrationForm = new RegistrationForm();
		
		setHorizontalComponentAlignment(Alignment.CENTER, registrationForm);
		
		add(registrationForm);
		
		RegistrationFormBinder registrationFormBinder = new RegistrationFormBinder(registrationForm);
		registrationFormBinder.addBindingAndValidation();
	}

}
