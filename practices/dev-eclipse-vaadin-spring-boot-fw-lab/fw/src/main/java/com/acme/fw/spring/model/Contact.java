package com.acme.fw.spring.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Contact {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String contactType;
	
	@OneToMany(mappedBy = "contact")
	private Set<ContactEmail> contactEmails;
	
	@OneToMany(mappedBy = "contact")
	private Set<ContactAddress> contactAddresses;
	
	@ManyToOne
	@JoinColumn(name = "ID_USER", nullable = false)
	private User user;
	
	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Set<ContactAddress> getContactAddresses() {
		return contactAddresses;
	}

	public void setContactAddresses(Set<ContactAddress> contactAddresses) {
		this.contactAddresses = contactAddresses;
	}

	public Set<ContactEmail> getContactEmails() {
		return contactEmails;
	}

	public void setContactEmails(Set<ContactEmail> contactEmails) {
		this.contactEmails = contactEmails;
	}

	public Contact(String contactType) {
		super();
		this.contactType = contactType;
	}

	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}
	
	

}
