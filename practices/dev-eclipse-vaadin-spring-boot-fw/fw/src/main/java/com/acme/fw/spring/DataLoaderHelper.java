package com.acme.fw.spring;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.acme.fw.spring.model.Profile;
import com.acme.fw.spring.model.User;
import com.acme.fw.spring.repository.ProfileRepository;
import com.acme.fw.spring.repository.UserRepository;

@Component
public class DataLoaderHelper implements CommandLineRunner{
	
	private final UserRepository userRepository;
	private final ProfileRepository profileRepository;
	
	public DataLoaderHelper(UserRepository userRepository, ProfileRepository profileRepository) {
		
		this.userRepository = userRepository;
		this.profileRepository = profileRepository;
		
	}
	
	@Override
	public void run(String... args) throws Exception{
		
		profileRepository.save(new Profile("Administrator"));
		profileRepository.save(new Profile("User"));
		
		System.out.println(profileRepository.findAll());
		
		Profile administratorProfile = profileRepository.findByName("Administrator");
		Profile userProfile = profileRepository.findByName("User");

		User user = new User("mtolentino", "Manoel Tolentino");
		user.setProfiles(Set.of(administratorProfile, userProfile));
		
		userRepository.save(user);
		
		System.out.println(userRepository.findAll());
	}

}
