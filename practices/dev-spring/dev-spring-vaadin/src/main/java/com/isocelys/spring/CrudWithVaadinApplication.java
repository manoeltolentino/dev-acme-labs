package com.isocelys.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.isocelys.spring.model.Customer;
import com.isocelys.spring.repository.CustomerRepository;

@SpringBootApplication(scanBasePackages = {"com.isocelys.spring.repository"})
public class CrudWithVaadinApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(CrudWithVaadinApplication.class);
	
	public static void main(String[] args) {
		
		SpringApplication.run(CrudWithVaadinApplication.class);
		
	}
	
	@Bean
	public CommandLineRunner loadData(CustomerRepository repository) {
		
		return (args) -> {
			
			repository.save(new Customer("Jack", "Bauer"));
			repository.save(new Customer("Chloe", "O'Brian"));
			repository.save(new Customer("Kim", "Bauer"));
			repository.save(new Customer("David", "Palmer"));
			repository.save(new Customer("Michelle", "Dessler"));
			
			logger.info("Customers found with findAll():");
			logger.info("-------------------------------");
			
			for (Customer customer: repository.findAll()) {
				
				logger.info(customer.toString());
				
			}
			logger.info("");
			
			//fetch customers by last name
			logger.info("Customer found with findByLastNameStartsWithIgnoreCase('Bauer'):");
			logger.info("-------------------------------");
			
			for (Customer customer: repository.findByLastNameStartsWithIgnoreCase("Bauer")) {
				
				logger.info(customer.toString());
				
			}
			logger.info("");
			
		};
		
	}

}
