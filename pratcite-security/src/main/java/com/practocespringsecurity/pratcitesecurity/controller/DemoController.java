package com.practocespringsecurity.pratcitesecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

	
	//obicni pagevi definisani
	// dakle za / vraca home.html
	// za leaders// vraca leaders.htmls
	// itd...
	@GetMapping("/")
	public String showHome() {
		return "home";

	}
	//add request mappinf for /leaders
	@GetMapping("/leaders")
	public String showLeaders() {
		return "leaders";

	}
	
	@GetMapping("/system")
	public String showSystem() {
		return "system";

	}
	
}
