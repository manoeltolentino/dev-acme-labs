package com.acme.spring_boot_crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acme.spring_boot_crud.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
