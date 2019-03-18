package com.rahulografy.springdemo.restfulwebservices.user;

import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Model contains user related data")
public class User {

	@ApiModelProperty(notes = "'id' should have atleast 1 character")
	@Size(min = 1, message = "'id' should have atleast 1 character")
	private String id;

	@ApiModelProperty(notes = "'name' should have atleast 2 characters")
	@Size(min = 2, message = "'name' should have atleast 2 characters")
	private String name;

	@ApiModelProperty(notes = "'address' should have atleast 1 character")
	@Size(min = 1, message = "'address' should have atleast 1 character")
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