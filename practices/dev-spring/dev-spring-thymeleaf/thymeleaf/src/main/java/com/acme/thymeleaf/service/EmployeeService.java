package com.acme.thymeleaf.service;

import java.util.List;

import com.acme.thymeleaf.model.Employee;

public interface EmployeeService {
	
	List<Employee> getAllEmployees();
	
	void saveEmployee(Employee employee);
	
	Employee getEmployeeById(Long id);
	
	void deleteEmployeeById(Long id);

}
