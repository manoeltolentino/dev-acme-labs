package com.acme.fw.vaadin.views.crudsample;

import com.acme.fw.spring.model.User;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;

public class UserEditor extends Composite<VerticalLayout> {
	
	public interface SaveListener{
		void onSave(User user);
	}
	
	public interface DeleteListener{
		void onDelete(User user);
	}
	
	public interface CancelListener{
		void onCancel();
	}
	
	private User user;
	
	private SaveListener saveListener;
	private DeleteListener deleteListener;
	private CancelListener cancelListener;
	
	private final Binder<User> binder = new BeanValidationBinder<>(User.class);
	
	public void setUser(User user) {
		this.user = user;
		binder.readBean(user);
	}

	public DeleteListener getDeleteListener() {
		return deleteListener;
	}

	public void setDeleteListener(DeleteListener deleteListener) {
		this.deleteListener = deleteListener;
	}

	public CancelListener getCancelListener() {
		return cancelListener;
	}

	public void setCancelListener(CancelListener cancelListener) {
		this.cancelListener = cancelListener;
	}

	public User getUser() {
		return user;
	}

	public Binder<User> getBinder() {
		return binder;
	}
	
	
	public UserEditor() {
		var id = new Text("id");
		var login = new TextField("Login");
		var name = new TextField("Nome");
		
		var save = new Button("Salvar", VaadinIcon.CHECK.create());
		var cancel = new Button("Cancelar");
		var delete = new Button("Delete", VaadinIcon.TRASH.create());
		
//		binder.forField(id).bind("id");
		binder.forField(login).bind("login");
		binder.forField(name).bind("name");
		
		save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		save.addClickListener(e -> saveListener.onSave(user));
		save.addClickShortcut(Key.ENTER);
		
		delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
		delete.addClickListener(e -> deleteListener.onDelete(user));
		
		cancel.addClickListener(e -> cancelListener.onCancel());
		
		getContent().add(id, login, new HorizontalLayout(save, cancel, delete));
		
	}
}
