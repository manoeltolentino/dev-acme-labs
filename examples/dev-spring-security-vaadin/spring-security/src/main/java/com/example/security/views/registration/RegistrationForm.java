package com.example.security.views.registration;

import java.util.stream.Stream;

import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;

public class RegistrationForm extends FormLayout{
	
	private H3 title;
	private TextField firstName;
	
	private TextField lastName;
	private EmailField email;
	private PasswordField password;
	private PasswordField confirmPassword;
	private Checkbox allowMarketing;
	
	private Span errorMessageField;
	private Button submitButton;
	
	public RegistrationForm() {
		title = new H3("Signup form");
		firstName = new TextField("First name");
		lastName = new TextField("Last name");
		email = new EmailField("Email");
		
		allowMarketing = new Checkbox("Allow marketing emails?");
		allowMarketing.getStyle().set("margin-top", "10px");
		
		password = new PasswordField("Password");
		confirmPassword = new PasswordField("Confirm password");
		
		setRequiredIndicatorVisible(firstName, lastName, email, password, confirmPassword);
		
		errorMessageField = new Span();
		
		submitButton = new Button("Ok");
		submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		
		add(title, firstName, lastName, email, password, confirmPassword, allowMarketing, errorMessageField, submitButton);
		setMaxWidth("500px");
		
       // Allow the form layout to be responsive.
       // On device widths 0-490px we have one column.
       // Otherwise, we have two columns.
		setResponsiveSteps(
				new ResponsiveStep("0", 1, ResponsiveStep.LabelsPosition.TOP),
				new ResponsiveStep("490px", 2, ResponsiveStep.LabelsPosition.TOP)
				);
		
		// These components always take full width
		setColspan(title, 2);
		setColspan(email, 2);
		setColspan(errorMessageField, 2);
		setColspan(submitButton, 2);
		
	}
	
	public PasswordField getPasswordField() { return password; }
	
	public PasswordField getPasswordConfirmField() { return confirmPassword; }
	
	public Span getErrorMessageField() { return errorMessageField; }
	
	public Button getSubButton() { return submitButton; }
	
	private void setRequiredIndicatorVisible(HasValueAndElement<?, ?>...components) {
		
		Stream.of(components).forEach(comp -> comp.setRequiredIndicatorVisible(true));
		
	}

}
