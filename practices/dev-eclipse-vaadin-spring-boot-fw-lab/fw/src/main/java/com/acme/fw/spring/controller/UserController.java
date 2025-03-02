package com.acme.fw.spring.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acme.fw.spring.model.User;
import com.acme.fw.spring.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	private final UserService userService;

	public UserController(UserService userService) {
		
		this.userService = userService;

	}
	
	@GetMapping
	public List<User> getAllEntities(){
		
		return this.userService.getAllEntities();
		
	}
	
	@GetMapping("/{id}")
	public User getEntityById(@PathVariable Long id) {
		
		return this.userService.getEntityById(id);
		
	}
	
	@PostMapping
	public User createEntity(@RequestBody User user) {
		
		return this.userService.createEntity(user);
		
	}
	
	@PutMapping("/{id}")
	public User updateEntity(@PathVariable Long id,  @RequestBody User user) {
		
		return this.userService.updateEntity(null, user);
		
	}
	
	@DeleteMapping("/{id}")
	public void deleteEntity(@PathVariable Long id) {
		
		User user = this.userService.get(id).orElse(null);
		
		if (user != null) {
			this.userService.deleteEntity(user);
		}
		
	}
	
	
}
