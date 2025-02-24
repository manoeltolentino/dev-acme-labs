package com.example.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.security.models.AppUser;
import com.example.security.repository.AppUserRepository;

@Service
public class AppUserService implements UserDetailsService {
	
	@Autowired
	private AppUserRepository appUserRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		AppUser appUser = this.appUserRepository.findByEmail(email);
		
		if (appUser != null) {
			var springUser = User.withUsername(appUser.getEmail())
					.password(appUser.getPassword())
					.roles(appUser.getRole())
					.build();
			
			return springUser;
		}
		
		return null;
	}

}
