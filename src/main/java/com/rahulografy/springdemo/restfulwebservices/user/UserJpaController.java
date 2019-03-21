package com.rahulografy.springdemo.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rahulografy.springdemo.restfulwebservices.exception.UserNotFoundRuntimeException;

@RestController
public class UserJpaController {

	@Autowired
	private UserRepo userRepo;

	@PostMapping(path = "/jpa/user")
	public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {

		final User userCreated = userRepo.save(user);

		if (userCreated != null) {

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(userCreated.getId()).toUri();
			return ResponseEntity.created(location).build();
		}

		throw new UserNotFoundRuntimeException("User's profile is already created for the id '" + user.getId() + "'!");
	}

	@GetMapping(path = "/jpa/users")
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@GetMapping(path = "/jpa/user/{id}")
	public User getUser(@PathVariable String id) {
		final Optional<User> user = userRepo.findById(id);

		if (!user.isPresent())
			throw new UserNotFoundRuntimeException("User with the id '" + id + "' not found!");

		return user.get();
	}

	@DeleteMapping(path = "/jpa/deleteUser/{id}")
	public void deleteUser(@PathVariable String id) {
		userRepo.deleteById(id);
	}

	@GetMapping(path = "/jpa/users/{id}/posts")
	public List<Post> getAllPostsForUser(@PathVariable String id) {
		final Optional<User> userOptional = userRepo.findById(id);

		if (!userOptional.isPresent())
			throw new UserNotFoundRuntimeException("User with the id '" + id + "' not found!");

		if (userOptional.get().getPosts().isEmpty())
			throw new UserNotFoundRuntimeException("No post found for the user with the id '" + id + "'!");

		return userOptional.get().getPosts();
	}
}