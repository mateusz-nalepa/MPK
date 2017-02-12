package com.mpk.controllers;

import java.security.Principal;

import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
	@RequestMapping("/principal")
	public Principal getPrincipal(Principal principal){
		return principal;
	}
}
