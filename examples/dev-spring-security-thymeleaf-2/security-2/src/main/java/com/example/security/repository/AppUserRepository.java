package com.example.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.security.models.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Integer>{
	
	public AppUser findByEmail(String email);

}
