package com.rahulografy.springdemo.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserVersioningController {

	// Basic Versioning

	@GetMapping("v1/getUserVersioning")
	public UserV1 getUserV1() {
		return new UserV1("Rahul Deshpande");
	}

	@GetMapping("v2/getUserVersioning")
	public UserV2 getUserV2() {
		return new UserV2(new Name("Rahul", "Deshpande"));
	}

	// Versioning using Params

	@GetMapping(value = "getUserVersioningParams", params = "version=1")
	public UserV1 getUserV1Params() {
		return new UserV1("Rahul Deshpande");
	}

	@GetMapping(value = "getUserVersioningParams", params = "version=2")
	public UserV2 getUserV2Params() {
		return new UserV2(new Name("Rahul", "Deshpande"));
	}

	// Versioning using Headers

	@GetMapping(value = "getUserVersioningHeaders", headers = "X-API-VERSION=1")
	public UserV1 getUserV1Headers() {
		return new UserV1("Rahul Deshpande");
	}

	@GetMapping(value = "getUserVersioningHeaders", headers = "X-API-VERSION=2")
	public UserV2 getUserV2Headers() {
		return new UserV2(new Name("Rahul", "Deshpande"));
	}

	// Versioning using Produces

	@GetMapping(value = "getUserVersioningProduces", produces = "application/vnd.company.app-v1+json")
	public UserV1 getUserV1Produces() {
		return new UserV1("Rahul Deshpande");
	}

	@GetMapping(value = "getUserVersioningProduces", produces = "application/vnd.company.app-v2+json")
	public UserV2 getUserV2Produces() {
		return new UserV2(new Name("Rahul", "Deshpande"));
	}
}