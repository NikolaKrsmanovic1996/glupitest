package com.practocespringsecurity.pratcitesecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginControllerSecurity {
	
	
	/*
	 * Ovo ti dolazi /login-plain iz klase DemoSecurityConfig.java
	 * tu si mu custom rekao da ces koristiti ovaj kontroler
	 * 
	 */
	
	@GetMapping("/login-plain")
	public String loginPage() {
		return "login-template/login-plain";
	}
	
	@GetMapping("/access-denied")
	public String accessDenidedPage() {
		return "login-template/access-denied";
	}
}
