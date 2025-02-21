package com.isocelys.spring.vaadin;

import com.isocelys.spring.model.Customer;
import com.isocelys.spring.repository.CustomerRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout{
	
	private static final long serialVersionUID = 1L;
	private final CustomerRepository repository;
	final Grid<Customer> grid;	

	public MainView(CustomerRepository repository) {
		
		add(new Button("Click me", e -> Notification.show("Hello, Spring + Vaadin user")));
		
		this.repository = repository;
		this.grid = new Grid<>(Customer.class);
		add(this.grid);
		listCustomers();
		
	}
	
	private void listCustomers() {
		this.grid.setItems(this.repository.findAll());
	}

}
