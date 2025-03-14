package com.isocelys.spring.service;

import java.util.List;

import com.isocelys.spring.model.Customer;

public interface CustomerService {
	
	List<Customer> getAllEntities();
	
	Customer getEntityById(Long id);
	
	Customer createEntity(Customer entity);
	
	Customer updateEntity(Long id, Customer entity);
	
	void deleteEntity(Customer entity);
	
	List<Customer> findByLastNameStartsWithIgnoreCase(String lastName);

}
