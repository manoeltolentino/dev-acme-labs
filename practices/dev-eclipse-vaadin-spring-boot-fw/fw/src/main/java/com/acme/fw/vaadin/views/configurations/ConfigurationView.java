package com.acme.fw.vaadin.views.configurations;

import org.vaadin.lineawesome.LineAwesomeIconUrl;

import com.acme.fw.vaadin.views.MainLayout;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.PermitAll;

@Route(value = "configuration", layout = MainLayout.class)
@Menu(order = 5, icon = LineAwesomeIconUrl.TOOLS_SOLID)
@PermitAll
public class ConfigurationView extends VerticalLayout{
	
	H1 title = new H1("Configurações");
	
	Accordion accordion = new Accordion();
	
	public ConfigurationView() {
		add(title);
		
		VerticalLayout securityArea = new VerticalLayout();

		PasswordField currentPassword = new PasswordField("Senha atual");
		PasswordField newPassword = new PasswordField("Nova senha");
		PasswordField confirmPassword = new PasswordField("Confirme a senha");
		
		Button cancelChangePasswordBtn = new Button("Cancela");
		Button confirmChangePasswordBtn = new Button("Confirma");
		
		securityArea.add(currentPassword, newPassword, confirmPassword, cancelChangePasswordBtn, confirmChangePasswordBtn);
		
		accordion.add("Segurança", securityArea);
		
		add(accordion);
	}
}
