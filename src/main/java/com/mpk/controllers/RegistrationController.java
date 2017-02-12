package com.mpk.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mpk.helpers.BusLineHelper;
import com.mpk.helpers.UserHelper;
import com.mpk.services.UserService;

@RestController
@RequestMapping("registration")
public class RegistrationController {
	private UserService userService;

	@Autowired
	public RegistrationController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	public ResponseEntity<Void> add(@RequestBody UserHelper userHelper) {
		return userService.add(userHelper);
	}
}