package com.acme.spring.service.impl;	

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.acme.spring.model.Customer;
import com.acme.spring.repository.CustomerRepository;
import com.acme.spring.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {
	
	private final CustomerRepository customerRepository;

	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public List<Customer> getAllEntities() {
		
		return customerRepository.findAll();
	}

	@Override
	public Customer getEntityById(Long id) {

		return customerRepository.findById(id).get();
		
	}

	@Override
	public Customer createEntity(Customer entity) {

		return customerRepository.save(entity);
		
	}

	@Override
	public Customer updateEntity(Long id, Customer entity) {

		return customerRepository.save(entity);
		
	}

	@Override
	public void deleteEntity(Customer entity) {
		
		customerRepository.delete(entity);
	}

	@Override
	public List<Customer> findByLastNameStartsWithIgnoreCase(String lastName) {

		return null;
		
	}

}
