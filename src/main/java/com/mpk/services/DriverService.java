package com.mpk.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mpk.dao.DriverRepository;
import com.mpk.dao.UserRepository;
import com.mpk.entity.Driver;
import com.mpk.entity.User;
import com.mpk.enums.DriverStatus;
import com.mpk.excepctions.ExceptionFactory;
import com.mpk.helpers.DriverHelper;

@Service
public class DriverService {
	private DriverRepository driverRepo;
	private UserRepository userRepo;

	@Autowired
	public DriverService(DriverRepository driverRepo, UserRepository userRepo) {
		this.driverRepo = driverRepo;
		this.userRepo = userRepo;
	}

	public List<DriverHelper> getAll() {
		List<Driver> drivers = driverRepo.findAll();
		List<DriverHelper> driverHelpers = new ArrayList<>();
		for (Driver d : drivers) {
			driverHelpers.add(new DriverHelper(d));
		}
		return driverHelpers;
	}

	public DriverHelper findById(Long id) {
		Driver driver = driverRepo.findOne(id);
		ExceptionFactory.throwNotFoundExceptionIfNull(driver, Driver.class);
		return new DriverHelper(driver);
	}

	public ResponseEntity<Void> add(DriverHelper driverHelper) {
		Driver driver = new Driver();
		User user = userRepo.findOne(driverHelper.getUserId());
		driver.setSalary(driverHelper.getSalary());
		driver.setStatus(DriverStatus.Active);
		driverRepo.save(driver);
		driver.setUser(user);
		user.setDriver(driver);
		userRepo.save(user);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	public void update(Long driverId, DriverHelper driverHelper) {
		Driver driver = driverRepo.findOne(driverId);
		driver.setSalary(driverHelper.getSalary());
		driver.setStatus(driverHelper.getStatus());
		driverRepo.save(driver);
	}

	public ResponseEntity<Void> delete(Long driverId) {
		Driver driver = driverRepo.findOne(driverId);
		User user = userRepo.findOne(driver.getUser().getId());
		if(driver.getWorkSchedules().isEmpty()){
			user.setDriver(null);
			userRepo.save(user);
			driver.setUser(null);
			driver.setWorkSchedules(null);
			driverRepo.delete(driver);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
		}
		
	}
}
