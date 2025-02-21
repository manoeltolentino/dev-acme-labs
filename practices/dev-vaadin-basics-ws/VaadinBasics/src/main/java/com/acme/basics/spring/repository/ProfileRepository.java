package com.acme.basics.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acme.basics.spring.model.Profile;



public interface ProfileRepository extends JpaRepository<Profile, Long> {
	
	public Profile findByName(String name);

}
