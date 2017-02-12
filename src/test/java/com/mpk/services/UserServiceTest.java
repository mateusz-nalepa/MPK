package com.mpk.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

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

import com.mpk.dao.UserRepository;
import com.mpk.entity.Driver;
import com.mpk.entity.User;
import com.mpk.helpers.UserHelper;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@Mock
	UserRepository userRepo;
	UserService userService;
	User user;
	List<User> users;

	@Before
	public void init() {
		userService = new UserService(userRepo);
		users = addDefaultUsers();
		user = addDefaultUser();
		Mockito.when(userRepo.findAll()).thenReturn(users);
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

	private List<User> addDefaultUsers() {
		List<User> users = new ArrayList<User>();
		User user = addDefaultUser();
		users.add(user);
		user = new User();
		user.setUsername("mateusz");
		user.setPassword("password");
		user.setFirstName("Mateusz");
		user.setLastName("Nalepa");
		user.setActive(true);
		user.setRole("ROLE_USER");
		user.setId(2L);
		user.setMail("mateusz@mail.com");
		user.setPhone("123123123");
		user.setDriver(new Driver());
		users.add(user);
		user = new User();
		user.setUsername("dawid");
		user.setPassword("password");
		user.setFirstName("Dawid");
		user.setLastName("Kij");
		user.setActive(false);
		user.setRole("ROLE_USER");
		user.setId(3L);
		user.setMail("dawid@mail.com");
		user.setPhone("123123123");
		users.add(user);
		return users;
	}

	@Test
	public void shouldGetAllUsers() {
		List<UserHelper> result = userService.getAll();
		assertEquals(3, result.size());
		assertEquals("patryk", result.get(0).getLogin());
		assertEquals("Patryk", result.get(0).getName());
		assertEquals("Olesiñski", result.get(0).getSurname());
		assertEquals("patryk@mail.com", result.get(0).getEmail());
		assertEquals(true, result.get(0).getActive());
		assertEquals("123123123", result.get(0).getPhone());
		assertEquals("ROLE_ADMIN", result.get(0).getRole());
		assertNull(result.get(0).getPassword());
	}

	@Test
	public void shouldGetAllUsersNotDrivers() {
		List<UserHelper> result = userService.getUsersNotDrivers();
		assertEquals(2, result.size());
		assertEquals("patryk", result.get(0).getLogin());
		assertEquals("Patryk", result.get(0).getName());
		assertEquals("Olesiñski", result.get(0).getSurname());
		assertEquals("patryk@mail.com", result.get(0).getEmail());
		assertEquals(true, result.get(0).getActive());
		assertEquals("123123123", result.get(0).getPhone());
		assertEquals("ROLE_ADMIN", result.get(0).getRole());
		assertNull(result.get(0).getPassword());
	}

	@Test
	public void shouldGetOneUserById() {
		UserHelper result = userService.findById(Mockito.anyLong());
		assertEquals("patryk", result.getLogin());
		assertEquals("Patryk", result.getName());
		assertEquals("Olesiñski", result.getSurname());
		assertEquals("patryk@mail.com", result.getEmail());
		assertEquals(true, result.getActive());
		assertEquals("123123123", result.getPhone());
		assertEquals("ROLE_ADMIN", result.getRole());
		assertNull(result.getPassword());
	}

	@Test
	public void shouldAddUser() {
		user = addDefaultUser();
		UserHelper userHelper = new UserHelper(user);
		when(userRepo.findByUsername(anyString())).thenReturn(null);
		when(userRepo.findByMail(anyString())).thenReturn(null);
		ResponseEntity<Void> result = userService.add(userHelper);
		assertEquals(new ResponseEntity<Void>(HttpStatus.CREATED), result);
	}

	@Test
	public void shouldReturn406() {
		user = addDefaultUser();
		UserHelper userHelper = new UserHelper(user);
		when(userRepo.findByUsername(anyString())).thenReturn(new User());
		when(userRepo.findByMail(anyString())).thenReturn(null);
		ResponseEntity<Void> result = userService.add(userHelper);
		assertEquals(new ResponseEntity<Void>(HttpStatus.valueOf(406)), result);
	}

	@Test
	public void shouldReturn405() {
		user = addDefaultUser();
		UserHelper userHelper = new UserHelper(user);
		when(userRepo.findByUsername(anyString())).thenReturn(null);
		when(userRepo.findByMail(anyString())).thenReturn(new User());
		ResponseEntity<Void> result = userService.add(userHelper);
		assertEquals(new ResponseEntity<Void>(HttpStatus.valueOf(405)), result);
	}
}
