package com.acme.fw.vaadin.views.crudsample;

import org.vaadin.lineawesome.LineAwesomeIconUrl;

import com.acme.fw.spring.model.User;
import com.acme.fw.spring.repository.UserRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
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
	}
	
	/*
	private void updateUsers(String filterText) {
		if (filterText.isEmpty()) {
			grid.setItems(userRepository.findAll());
		}
		else {
			grid.setItems(userRepository.findBy(null, null))
		}
	}
	*/

}
