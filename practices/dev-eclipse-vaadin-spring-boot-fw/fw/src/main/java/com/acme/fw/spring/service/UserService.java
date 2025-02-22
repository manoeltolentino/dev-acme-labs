package com.acme.fw.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.acme.fw.spring.model.User;

public interface UserService {
	
	public List<User> getAllEntities();
	
	public User getEntityById(Long id);
	
    public Optional<User> get(Long id);
	
	public User createEntity(User user);
	
	public User updateEntity(Long id, User user);
	
	public void deleteEntity(Long id);
	
    public User save(User entity);
	
    public Page<User> list(Pageable pageable);
    
    public Page<User> list(Pageable pageable, Specification<User> filter);

}
