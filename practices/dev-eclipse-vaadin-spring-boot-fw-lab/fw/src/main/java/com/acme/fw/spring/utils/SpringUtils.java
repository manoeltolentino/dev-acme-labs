package com.acme.fw.spring.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SpringUtils implements ApplicationContextAware{
	
	private static ApplicationContext applicationContext;
	
	public static <T extends Object> T getBean(Class<T> beanClass) {
		return applicationContext.getBean(beanClass);
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException{
		setContext(applicationContext);
	}
	
	private static synchronized void setContext(ApplicationContext applicationContext) {
		SpringUtils.applicationContext = applicationContext;
	}
	
	public static String encodeBCryptPassword(String rawPassword) {

		BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder();
		
		return encoder.encode(rawPassword);
		
	}
	
	public static boolean matchBCryptPassword(String rawPassword, String encodedPassword) {
		
		BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder();
		
		return encoder.matches(rawPassword, encodedPassword);
				
	}	

}
