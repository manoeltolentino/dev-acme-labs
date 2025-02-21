package com.acme.spring_boot_crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acme.spring_boot_crud.model.User;
import com.acme.spring_boot_crud.service.UserService;

@RestController
@RequestMapping("/api/entities")
public class UserController {
	
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping
	public List<User> getAllEntities(){
		return userService.getAllEntities();
	}
	
	@GetMapping("/{id}")
	public User getEntityById(@PathVariable Long id) {
		return userService.getEntityById(id);
	}
	
	@PostMapping
	public User createEntity(@RequestBody User entity) {
		return userService.createEntity(entity);
	}
	
	@PutMapping("/{id}")
	public User updateEntity(@PathVariable Long id, @RequestBody User entity) {
		
		return userService.updateEntity(id, entity);
		
	}
	
	@DeleteMapping("/{id}")
	public void deleteEntity(@PathVariable Long id) {
		
		userService.deleteEntity(id);
		
	}
	

}
