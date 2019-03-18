package com.rahulografy.springdemo.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@GetMapping(path = "/helloWorldPojo")
	public HelloWorldPojo helloWorldPojo() {
		return new HelloWorldPojo("hello", "world", "pojo");
	}

	@GetMapping(path = "/helloWorldPojo/{name}")
	public HelloWorldPojo helloWorldPojo(@PathVariable String name) {
		return new HelloWorldPojo("hello", "world", name);
	}
}