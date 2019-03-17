package com.rahulografy.springdemo.restfulwebservices.user;

import javax.validation.constraints.Size;

public class User {

	@Size(min=1, message = "'Id' should have atleast 1 characters")
	private String id;
	
	@Size(min=2, message = "'Name' should have atleast 2 characters")
	private String name;
	
	@Size(min=1, message = "'Address' should have atleast 1 character")
	private String address;

	public User(String id, String name, String address) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", address=" + address + "]";
	}
}