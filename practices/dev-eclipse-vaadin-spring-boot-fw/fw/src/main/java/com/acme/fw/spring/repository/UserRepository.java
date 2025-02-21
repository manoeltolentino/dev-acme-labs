package com.acme.fw.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acme.fw.spring.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
