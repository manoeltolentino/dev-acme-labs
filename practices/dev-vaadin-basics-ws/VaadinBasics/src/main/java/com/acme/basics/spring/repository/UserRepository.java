package com.acme.basics.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acme.basics.spring.model.User;


public interface UserRepository extends JpaRepository<User, Long> {

}
