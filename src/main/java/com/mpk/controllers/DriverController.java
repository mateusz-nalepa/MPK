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

import com.mpk.helpers.DriverHelper;
import com.mpk.services.DriverService;

@RestController
@RequestMapping("driver")
public class DriverController {
	private DriverService driverService;

	@Autowired
	public DriverController(DriverService driverService) {
		this.driverService = driverService;
	}

	@GetMapping
	public List<DriverHelper> getAll() {
		return driverService.getAll();
	}

	@GetMapping("{driverId}")
	public DriverHelper getById(@PathVariable Long driverId) {
		return driverService.findById(driverId);
	}

	@PostMapping
	public ResponseEntity<Void> add(@RequestBody DriverHelper driver) {
		return driverService.add(driver);
	}

	@PutMapping("{driverId}")
	public ResponseEntity<Void> update(@PathVariable Long driverId, @RequestBody DriverHelper driverHelper) {
		driverService.update(driverId, driverHelper);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@DeleteMapping("{driverId}")
	public ResponseEntity<Void> delete(@PathVariable Long driverId) {
		return driverService.delete(driverId);
	}
}
