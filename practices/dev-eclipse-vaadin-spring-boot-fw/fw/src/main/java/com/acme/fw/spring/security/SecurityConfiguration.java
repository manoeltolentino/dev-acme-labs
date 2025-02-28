package com.acme.fw.spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.vaadin.flow.spring.security.VaadinWebSecurity;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends VaadinWebSecurity {
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth -> 
        	auth.requestMatchers(
        			AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/images/*.png")).permitAll());

        super.configure(http); 

        setLoginView(http, LoginView.class); 
    }
	
    @Override
    public void configure(WebSecurity web) throws Exception {
        // Customize your WebSecurity configuration.
        super.configure(web);
    }
    
    @Bean
    public UserDetailsManager userDetailsService() {
    	
        UserDetails user = User.builder()
        		.username("user")
        		.password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW") //password: password
        		.roles("USER")
        		.build();

        UserDetails admin = User.builder()
        		.username("admin")
        		.password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW") //password: password
        		.roles("USER", "ADMIN")
        		.build();
        
        return new InMemoryUserDetailsManager(user, admin);

        
    }

}
