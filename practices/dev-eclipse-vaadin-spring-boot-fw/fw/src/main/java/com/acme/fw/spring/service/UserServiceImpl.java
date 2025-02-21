package com.acme.fw.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.acme.fw.spring.model.User;
import com.acme.fw.spring.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		
		this.userRepository = userRepository;
		
	}

	@Override
	public List<User> getAllEntities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getEntityById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User createEntity(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateEntity(Long id, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteEntity(Long id) {
		// TODO Auto-generated method stub

	}

}
