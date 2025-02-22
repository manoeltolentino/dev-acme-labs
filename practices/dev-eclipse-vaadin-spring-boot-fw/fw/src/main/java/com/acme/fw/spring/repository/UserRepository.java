package com.acme.fw.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.acme.fw.spring.model.User;
import com.acme.fw.vaadin.data.SamplePerson;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

	List<User> findByNameStartsWithIgnoreCase(String name);

}
