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

import com.rahulografy.springdemo.restfulwebservices.exception.PostNotFoundRuntimeException;
import com.rahulografy.springdemo.restfulwebservices.exception.UserNotFoundRuntimeException;

@RestController
public class PostJpaController {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PostRepo postRepo;

	@GetMapping(path = "/jpa/users/{id}/posts")
	public List<Post> getAllPostsForUser(@PathVariable String id) {
		final Optional<User> userOptional = userRepo.findById(id);

		if (!userOptional.isPresent())
			throw new UserNotFoundRuntimeException("User with the id '" + id + "' not found!");

		if (userOptional.get().getPosts().isEmpty())
			throw new PostNotFoundRuntimeException("No post found for the user with the id '" + id + "'!");

		return userOptional.get().getPosts();
	}

	@GetMapping(path = "/jpa/posts")
	public List<Post> getAllPosts() {
		return postRepo.findAll();
	}

	@PostMapping(path = "/jpa/user/{id}/post")
	public ResponseEntity<Object> addPost(@PathVariable String id, @Valid @RequestBody Post post) {

		final Optional<User> userOptional = userRepo.findById(id);

		if (!userOptional.isPresent())
			throw new UserNotFoundRuntimeException("User with the id '" + id + "' not found!");

		User user = userOptional.get();

		post.setUser(user);

		Post postSaved = postRepo.save(post);

		if (postSaved != null) {

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(postSaved.getId()).toUri();
			return ResponseEntity.created(location).build();
		}

		throw new PostNotFoundRuntimeException("Post has already created for the id '" + postSaved.getId() + "'!");
	}

	@GetMapping(path = "/jpa/post/{id}")
	public Post getPost(@PathVariable String id) {
		final Optional<Post> postOptional = postRepo.findById(id);

		if (!postOptional.isPresent())
			throw new PostNotFoundRuntimeException("Post with the id '" + id + "' not found!");

		return postOptional.get();
	}

	@DeleteMapping(path = "/jpa/deletePost/{id}")
	public void deletePost(@PathVariable String id) {
		postRepo.deleteById(id);
	}
}