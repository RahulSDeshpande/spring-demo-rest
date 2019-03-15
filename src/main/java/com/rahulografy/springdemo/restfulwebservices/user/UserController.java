package com.rahulografy.springdemo.restfulwebservices.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private UserDao userDao;

	@PostMapping(path = "/user")
	public boolean addUser(@RequestBody User user) {
		return userDao.createUser(user);
	}

	@GetMapping(path = "/users")
	public List<User> getAllUsers() {
		return userDao.getUsers();
	}

	@GetMapping(path = "/user/{id}")
	public User getUser(@PathVariable String id) {
		return userDao.getUser(id);
	}

	@GetMapping(path = "/deleteUser/{id}")
	public boolean deleteUser(@PathVariable String id) {
		return userDao.deleteUser(id);
	}
}