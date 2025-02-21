package com.acme.spring_boot_crud.service;

import java.util.List;

import com.acme.spring_boot_crud.model.User;

public interface UserService {
	
	List<User> getAllEntities();
	
	User getEntityById(Long id);
	
	User createEntity(User entity);
	
	User updateEntity(Long id, User entity);
	
	void deleteEntity(Long id);

}
