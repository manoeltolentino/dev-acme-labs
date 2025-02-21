package com.isocelys.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isocelys.spring.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

	List<Customer> findByLastNameStartsWithIgnoreCase(String lastName);
	
}
