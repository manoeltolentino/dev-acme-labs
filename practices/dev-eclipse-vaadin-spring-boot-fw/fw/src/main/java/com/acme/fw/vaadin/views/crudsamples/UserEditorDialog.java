package com.acme.fw.vaadin.views.crudsamples;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.acme.fw.spring.model.Profile;
import com.acme.fw.spring.repository.ProfileRepository;
import com.acme.fw.spring.utils.SpringContext;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class UserEditorDialog extends Dialog{
	
	private final ProfileRepository profileRepository = SpringContext.getBean(ProfileRepository.class);
	
	public UserEditorDialog() {
		
		setHeaderTitle("Perfis do usuário");
		setWidth("500px");

		Button closeBtn = new Button("Fechar", e -> this.close());
		
		Grid<Profile> grid = new Grid<>(Profile.class, false);
		grid.addColumn(Profile::getName);
		grid.setWidthFull();
		grid.setHeight("100px");
		
		List<Profile> profileList = this.profileRepository.findAll();
		grid.setItems(profileList);
		
		var verticalLayout = new VerticalLayout();
		verticalLayout.add(grid, closeBtn);
		
		add(verticalLayout);

	}
	
	

}
