package com.rahulografy.springdemo.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rahulografy.springdemo.restfulwebservices.exception.UserNotFoundRuntimeException;

@RestController
public class UserJpaController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private UserDao userDao;

	@PostMapping(path = "/jpa/user")
	public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {

		// final User userCreated = userDao.createUser(user);
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
		// return userDao.getUsers();
		return userRepo.findAll();
	}

	@GetMapping(path = "/jpa/user/{id}")
	public Resource<User> getUser(@PathVariable String id) {
		// final User user = userDao.getUser(id);
		final Optional<User> user = userRepo.findById(id);

		if (!user.isPresent())
			throw new UserNotFoundRuntimeException("User with the id '" + id + "' not found!");

		Resource<User> resource = new Resource<User>(user.get());
		resource.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).addUser(null))
				.withRel("Add a User"));
		resource.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getAllUsers())
				.withRel("Get All Users"));
		resource.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getUser(null))
				.withRel("Get User by 'ID'"));
		// resource.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).deleteUser(null)).withRel("Delete
		// a User by 'ID'"));

		return resource;
	}

	@DeleteMapping(path = "/jpa/deleteUser/{id}")
	public void deleteUser(@PathVariable String id) {
		// return userDao.deleteUser(id);
		userRepo.deleteById(id);
	}

	@GetMapping(path = "/jpa/goodMorning")
	public String goodMorning(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
		return messageSource.getMessage("good.morning", null, LocaleContextHolder.getLocale());
	}

	@GetMapping(path = "/jpa/goodMorningGeneric")
	public String goodMorning() {
		return messageSource.getMessage("good.morning", null, LocaleContextHolder.getLocale());
	}
}