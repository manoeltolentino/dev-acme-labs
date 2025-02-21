package com.acme.fw.spring.service;

import java.util.List;

import com.acme.fw.spring.model.User;

public interface UserService {
	
	public List<User> getAllEntities();
	
	public User getEntityById(Long id);
	
	public User createEntity(User user);
	
	public User updateEntity(Long id, User user);
	
	public void deleteEntity(Long id);

}
