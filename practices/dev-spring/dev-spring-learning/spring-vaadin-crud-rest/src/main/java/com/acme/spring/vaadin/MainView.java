package com.acme.spring.vaadin;

import org.springframework.util.StringUtils;

import com.acme.spring.model.Customer;
import com.acme.spring.repository.CustomerRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout{
	
	private static final long serialVersionUID = 1L;
	
	private final CustomerRepository repository;
	private final CustomerEditor customerEditor;
	
	final Grid<Customer> grid;
	final TextField filter;
	private final Button addNewButton;

	public MainView(CustomerRepository repository, CustomerEditor customerEditor) {
		
		this.repository = repository;
		this.customerEditor = customerEditor;
		
		this.grid = new Grid<>(Customer.class);
		this.filter = new TextField();
		this.addNewButton = new Button("New customer", VaadinIcon.PLUS.create());
		
		//build layout
		HorizontalLayout actions = new HorizontalLayout(filter, addNewButton);
		add(actions, grid, customerEditor);
		
		grid.setHeight("300px");
		grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);
		
		filter.setPlaceholder("Filter by last name");
		
		//Hook logic to components
		
		//Replace listing with filtered content when user changes filter
		filter.setValueChangeMode(ValueChangeMode.LAZY);
		filter.addValueChangeListener(e -> listCustomers(e.getValue()));
		
		//Connect selected Customer to editor or hide if none is selected
		grid.asSingleSelect().addValueChangeListener(e -> customerEditor.editCustomer(e.getValue()));
		
		// Instantiate and edit new Customer the new button is clicked
		addNewButton.addClickListener(e -> customerEditor.editCustomer(new Customer("", "")));
		
		// Listen changes made by the editor, refresh data from backend
		customerEditor.setChangeHandler(() -> {
			customerEditor.setVisible(false);
			listCustomers(filter.getValue());
		});
		
		// Initialize listing
		listCustomers(null);
		
	}
	
	private void listCustomers(String filterText) {
		
		if (StringUtils.hasText(filterText)) {
			this.grid.setItems(this.repository.findByLastNameStartsWithIgnoreCase(filterText));
		}
		else {
			this.grid.setItems(this.repository.findAll());
		}
	}

}
