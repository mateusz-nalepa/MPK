package com.mpk.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mpk.dao.UserRepository;
import com.mpk.entity.BusLine;
import com.mpk.entity.Route;
import com.mpk.entity.User;
import com.mpk.excepctions.ExceptionFactory;
import com.mpk.helpers.RouteHelper;
import com.mpk.helpers.UserHelper;

@Service
public class UserService {
	private UserRepository userRepo;

	@Autowired
	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	public List<UserHelper> getAll() {
		List<User> users = userRepo.findAll();
		List<UserHelper> userHelpers = new ArrayList<>();
		for (User u : users) {
			u.setPassword(null);
			userHelpers.add(new UserHelper(u));
		}
		return userHelpers;
	}

	public List<UserHelper> getUsersNotDrivers() {
		List<User> users = userRepo.findAll();
		List<UserHelper> userHelpers = new ArrayList<>();
		for (User u : users) {
			u.setPassword(null);
			if (u.getDriver() == null) {
				userHelpers.add(new UserHelper(u));
			}
		}
		return userHelpers;
	}

	public UserHelper findById(Long id) {
		User user = userRepo.findOne(id);
		user.setPassword(null);
		ExceptionFactory.throwNotFoundExceptionIfNull(user, User.class);
		return new UserHelper(user);
	}

	public ResponseEntity<Void> add(UserHelper userHelper) {
		User user = new User();
		user.setUsername(userHelper.getLogin());
		user.setPassword(userHelper.getPassword());
		user.setFirstName(userHelper.getName());
		user.setLastName(userHelper.getSurname());
		user.setMail(userHelper.getEmail());
		user.setPhone(userHelper.getPhone());
		user.setRole("ROLE_USER");
		user.setActive(true);
		if (userRepo.findByMail(user.getMail()) != null) {
			return new ResponseEntity<Void>(HttpStatus.valueOf(405));
		}
		if (userRepo.findByUsername(user.getUsername()) != null) {
			return new ResponseEntity<Void>(HttpStatus.valueOf(406));
		} else {
			userRepo.save(user);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}

	public void update(Long userId, UserHelper userHelper) {
		User user = userRepo.findOne(userId);
		user.setFirstName(userHelper.getName());
		user.setLastName(userHelper.getSurname());
		user.setMail(userHelper.getEmail());
		user.setPhone(userHelper.getPhone());
		user.setUsername(userHelper.getLogin());
		user.setRole(userHelper.getRole());
		userRepo.save(user);
	}

	public ResponseEntity<Void> delete(Long userId) {
		User user = userRepo.findOne(userId);
		if (user.getDriver() == null) {
			user.setActive(false);
			userRepo.save(user);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

}
