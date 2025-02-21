package com.acme.fw.vaadin.views.crudsamples;

import org.vaadin.lineawesome.LineAwesomeIconUrl;

import com.acme.fw.spring.model.User;
import com.acme.fw.spring.repository.UserRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Usuários")
@Route("user-browser")
@Menu(order = 5, icon = LineAwesomeIconUrl.USER)
public class UserView extends VerticalLayout {
	
	private final UserRepository userRepository;
	
	private final TextField filter;
	private final Grid<User> grid;
	private final UserEditor editor;
	
	public UserView(UserRepository userRepository) {
		this.userRepository = userRepository;
		
		var addButton = new Button("Novo", VaadinIcon.PLUS.create());
		filter = new TextField();
		grid = new Grid<>(User.class);
		editor = new UserEditor();
		
		var actionsLayout = new HorizontalLayout(filter, addButton);
		add(actionsLayout, grid, editor);
		
		configureEditor();
		
		addButton.addClickListener(e -> editUser(new User()));
		
		filter.setPlaceholder("Filro pelo nome");
		filter.setValueChangeMode(ValueChangeMode.EAGER);
		filter.addValueChangeListener(e -> updateUsers(e.getValue()));
		
		grid.setHeight("200px");
		grid.asSingleSelect().addValueChangeListener(e -> editUser(e.getValue()));
		
		grid.addComponentColumn(e -> new Button(getProfilesCount(e))).setHeader("Profiles");
		
		updateUsers("");
		
		var dialog = new Button("Dialog", e -> {
			UserEditorDialog userEditorDialog = new UserEditorDialog();
			userEditorDialog.open();
		});
		
		add(dialog);
	}
	
	private String getProfilesCount(User user) {
		
		try {
			return String.valueOf(user.getProfiles().toArray().length);
		}
		catch(Exception e) {
			return "0";
		}
		
	}
	
	private void configureEditor() {
		editor.setVisible(false);
		
		editor.setSaveListener(user -> {
			var saved = userRepository.save(user);
			updateUsers(filter.getValue());
			editor.setUser(null);
			grid.asSingleSelect().setValue(saved);
		});
		
		editor.setDeleteListener(user -> {
			userRepository.delete(user);
			updateUsers(filter.getValue());
			editUser(null);
		});
		
		editor.setCancelListener(() -> {
			editUser(null);
		});
	}
	
	private void updateUsers(String filterText) {
		if (filterText.isEmpty()) {
			grid.setItems(userRepository.findAll());
		}
		else {
			grid.setItems(userRepository.findByNameStartsWithIgnoreCase(filterText));
		}
	}
	
	private void editUser(User user) {
		editor.setUser(user);
		
		if (user != null) {
			editor.setVisible(true);
		}
		else {
			grid.asSingleSelect().setValue(null);
			editor.setVisible(false);
		}
	}

}
