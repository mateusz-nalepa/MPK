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
@RequestMapping("user")
public class UserController {
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public List<UserHelper> getAll() {
		return userService.getAll();
	}

	@GetMapping("notDriver")
	public List<UserHelper> getUsersNotDrivers() {
		return userService.getUsersNotDrivers();
	}

	@GetMapping("{userId}")
	public UserHelper getById(@PathVariable Long userId) {
		return userService.findById(userId);
	}

	@PostMapping
	public ResponseEntity<Void> add(@RequestBody UserHelper userHelper) {
		return userService.add(userHelper);
	}

	@PutMapping("{userId}")
	public ResponseEntity<Void> update(@PathVariable Long userId, @RequestBody UserHelper userHelper) {
		userService.update(userId, userHelper);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@DeleteMapping("{userId}")
	public ResponseEntity<Void> delete(@PathVariable Long userId) {
		return userService.delete(userId);
	}
}
