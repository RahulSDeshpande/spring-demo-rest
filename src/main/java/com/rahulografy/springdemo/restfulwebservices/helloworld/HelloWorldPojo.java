package com.rahulografy.springdemo.restfulwebservices.helloworld;

public class HelloWorldPojo {

	private String hello;
	private String world;
	private String pojo;

	public HelloWorldPojo(String hello, String world, String pojo) {
		super();
		this.hello = hello;
		this.world = world;
		this.pojo = pojo;
	}

	public String getHello() {
		return hello;
	}

	public void setHello(String hello) {
		this.hello = hello;
	}

	public String getWorld() {
		return world;
	}

	public void setWorld(String world) {
		this.world = world;
	}

	public String getPojo() {
		return pojo;
	}

	public void setPojo(String pojo) {
		this.pojo = pojo;
	}
}