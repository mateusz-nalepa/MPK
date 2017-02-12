package com.mpk.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyLong;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mpk.dao.DriverRepository;
import com.mpk.dao.UserRepository;
import com.mpk.entity.Driver;
import com.mpk.entity.User;
import com.mpk.enums.DriverStatus;
import com.mpk.helpers.DriverHelper;

@RunWith(MockitoJUnitRunner.class)
public class DriverServiceTest {

	@Mock
	UserRepository userRepo;
	@Mock
	DriverRepository driverRepo;
	DriverService driverService;
	List<Driver> drivers;
	Driver driver;
	User user;

	@Before
	public void init() {
		driverService = new DriverService(driverRepo, userRepo);
		drivers = addDefaultDrivers();
		driver = addDefaultDriver();
		user = addDefaultUser();
		Mockito.when(driverRepo.findAll()).thenReturn(drivers);
		Mockito.when(driverRepo.findOne(Mockito.anyLong())).thenReturn(driver);
		Mockito.when(userRepo.findOne(Mockito.anyLong())).thenReturn(user);
	}

	private User addDefaultUser() {
		User user = new User();
		user.setUsername("patryk");
		user.setPassword("password");
		user.setFirstName("Patryk");
		user.setLastName("Olesiñski");
		user.setActive(true);
		user.setRole("ROLE_ADMIN");
		user.setId(1L);
		user.setMail("patryk@mail.com");
		user.setPhone("123123123");
		return user;
	}
	
	private Driver addDefaultDriver() {
		Driver driver = new Driver();
		driver.setId(0L);
		driver.setSalary(3200);
		driver.setStatus(DriverStatus.Active);
		driver.setUser(addDefaultUser());
		return driver;
	}

	private List<Driver> addDefaultDrivers() {
		List<Driver> drivers = new ArrayList<Driver>();
		Driver driver = addDefaultDriver();
		drivers.add(driver);
		driver = new Driver();
		driver.setId(1L);
		driver.setSalary(1200);
		driver.setStatus(DriverStatus.Holiday);
		driver.setUser(addDefaultUser());
		drivers.add(driver);
		driver = new Driver();
		driver.setId(2L);
		driver.setSalary(1400);
		driver.setStatus(DriverStatus.Active);
		driver.setUser(new User());
		drivers.add(driver);
		return drivers;
	}

	@Test
	public void shouldGetAllDrivers() {
		List<DriverHelper> result = driverService.getAll();
		assertEquals(3, result.size());
		assertEquals(DriverStatus.Active, result.get(0).getStatus());
		assertNotNull(result.get(0).getUserId());
		assertEquals("patryk",result.get(0).getUsername());
	}
	
	@Test
	public void shouldGetOneDriver() {
		DriverHelper result = driverService.findById(anyLong());
		assertEquals("patryk", result.getUsername());
		assertEquals(DriverStatus.Active, result.getStatus());
		assertNotNull(result.getUserId());
	}
	
	@Test
	public void shouldAddDriver() {
		driver = addDefaultDriver();
		DriverHelper driverHelper = new DriverHelper(driver);
		ResponseEntity<Void> result = driverService.add(driverHelper);
		assertEquals(new ResponseEntity<Void>(HttpStatus.CREATED), result);
	}
}