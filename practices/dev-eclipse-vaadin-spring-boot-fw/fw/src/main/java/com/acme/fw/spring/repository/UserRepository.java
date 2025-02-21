package com.acme.fw.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acme.fw.spring.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	List<User> findByNameStartsWithIgnoreCase(String name);

}
