package com.acme.spring_boot_crud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acme.spring_boot_crud.model.User;
import com.acme.spring_boot_crud.repository.UserRepository;
import com.acme.spring_boot_crud.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<User> getAllEntities() {

		return userRepository.findAll();
		
	}

	@Override
	public User getEntityById(Long id) {
		
		User user = userRepository.findById(id).get(); 
		
		return user;
		
	}

	@Override
	public User createEntity(User entity) {
		
		return userRepository.save(entity);
		
	}

	@Override
	public User updateEntity(Long id, User entity) {
		
		if (userRepository.existsById(id)) {
			entity.setId(id);
			return userRepository.save(entity);
		}
		
		return null;
	}

	@Override
	public void deleteEntity(Long id) {
		
		userRepository.deleteById(id);

	}

}
