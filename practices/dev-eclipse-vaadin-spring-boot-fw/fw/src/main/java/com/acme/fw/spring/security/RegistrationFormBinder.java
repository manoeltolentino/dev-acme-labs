package com.acme.fw.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.acme.fw.spring.controller.UserController;
import com.acme.fw.spring.model.User;
import com.acme.fw.spring.service.UserService;
import com.acme.fw.spring.utils.SpringContext;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.ValueContext;

public class RegistrationFormBinder {
	
	private RegistrationForm registrationForm;
	
	@Autowired
	private UserService userService;
	
	//Flag for disabling first run for password validation
	private boolean enablePasswordValidation;
	
	public RegistrationFormBinder(RegistrationForm registrationForm) {
		this.registrationForm = registrationForm;
	}
	
	//Method to add the data binding and validation logics to the registration form
	public void addBindingAndValidation() {
		
		BeanValidationBinder<User> binder = new BeanValidationBinder<>(User.class);
		binder.bindInstanceFields(registrationForm);
		
		binder.forField(registrationForm.getPasswordField()).withValidator(this::passwordValidator).bind("password");
		
		// The second password field is not connected to the Binder, but we want the 
		// binder to re-check the password validator when the field value changes. The easiest way is just to do that manually.
		registrationForm.getPasswordConfirmField().addValueChangeListener(e -> {
			
			// The user has modified the second field, now we can validate and show errors.
	        // See passwordValidator() for how this flag is used.
			enablePasswordValidation = true;
			
			binder.validate();
		});
		
		// Set the label where bean-level error messages go
		binder.setStatusLabel(registrationForm.getErrorMessageField());
		
		registrationForm.getSubButton().addClickListener(e -> {
			try {
				User user = new User();
				
				binder.writeBean(user);
				
				UserService userService2 = SpringContext.getBean(UserService.class);
				
				user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
				
				userService2.createEntity(user);
				
				showSuccess(user);
				
			}
			catch(ValidationException ex) {
				  // validation errors are already visible for each field,
	              // and bean-level errors are shown in the status label.
	              // We could show additional messages here if we want, do logging, etc.
			}
		});
	}
	
	private ValidationResult passwordValidator(String pass1, ValueContext ctx) {
		
		if ((pass1 == null) || pass1.length() < 5) {
			return ValidationResult.error("Password should be at least 5 characters long");
		}
		
		if (!enablePasswordValidation) {
			// user hasn't visited the field yet, so don't validate just yet, but next time.
			enablePasswordValidation = true;
			return ValidationResult.ok();
		}
		
		String pass2 = registrationForm.getPasswordConfirmField().getValue();
		
		if (pass1 != null && pass1.equals(pass2)) {
			return ValidationResult.ok();
		}
		
		return ValidationResult.error("Passwords do not match");
		
	}
	
	//We call this method when form submission has succeeded
	private void showSuccess(User user) {
		Notification notification = Notification.show("Data saved, welcome " + user.getLogin());
		notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
		
		// Here you'd typically redirect the user to another view
	}

}
