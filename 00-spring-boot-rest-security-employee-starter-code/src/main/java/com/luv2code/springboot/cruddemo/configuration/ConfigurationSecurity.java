package com.luv2code.springboot.cruddemo.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ConfigurationSecurity {

//	@Bean
//	public InMemoryUserDetailsManager userDetailsManager() {
//		UserDetails john = User.builder().
//								username("nikola").
//								password("{noop}test123")
//								.roles("ADMIN")
//								.build();
//
//		UserDetails mary = User.builder().
//						   		username("mary").
//						   		password("{noop}test123").
//						   		roles("USER").
//						   		build();
//
//		UserDetails susan = User.builder().
//								username("susan").
//								password("{noop}test123").
//								roles("MANAGER").
//								build();
//
//
//		return new InMemoryUserDetailsManager(john,mary,susan);
//	}

	@Bean
	SecurityFilterChain securityFilter(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeHttpRequests(config -> 
		config
			  .requestMatchers(HttpMethod.GET,"/api/employee").hasRole("EMPLOYEE")
			  .requestMatchers(HttpMethod.GET,"/api/employee/**").hasRole("EMPLOYEE")
			  .requestMatchers(HttpMethod.POST,"/api/employee").hasRole("EMPLOYEE")
			  .requestMatchers(HttpMethod.PUT,"/api/employee").hasRole("EMPLOYEE")
			  .requestMatchers(HttpMethod.DELETE,"/api/employee/**").hasRole("EMPLOYEE"));
	
		httpSecurity.csrf(csfr -> csfr.disable());
		return httpSecurity.build();
		
	}
	
	
	@Bean
	 UserDetailsManager userDetailManager(DataSource dataSource) {
		JdbcUserDetailsManager jdbUserDetail = new JdbcUserDetailsManager(dataSource);
		jdbUserDetail.setUsersByUsernameQuery(
				"selecy user_id,pw,active from members where user_id =?");// ? it will be passed as login form from our app
		
		
		
		jdbUserDetail.setAuthoritiesByUsernameQuery(
				"select user_id,roles from role where user_id=?");
		
		return jdbUserDetail;
	}
}
