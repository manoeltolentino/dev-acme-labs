package com.acme.fw.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
	

    public Optional<User> get(Long id) {
        return this.userRepository.findById(id);
    }
    
    public User getEntityByLogin(String login) {
    	
    	return this.userRepository.findByLogin(login);
    	
    }

	@Override
	public User createEntity(User user) {
		
		return this.userRepository.save(user);
		
	}

	@Override
	public User updateEntity(Long id, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteEntity(User user) {
		
		this.userRepository.delete(user);

	}
	
    public User save(User entity) {
        return this.userRepository.save(entity);
    }	
	
    public Page<User> list(Pageable pageable) {
        return this.userRepository.findAll(pageable);
    }

    public Page<User> list(Pageable pageable, Specification<User> filter) {
        return this.userRepository.findAll(filter, pageable);
    }	

}
