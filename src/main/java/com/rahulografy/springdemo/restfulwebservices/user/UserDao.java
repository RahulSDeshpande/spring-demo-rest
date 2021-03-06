package com.rahulografy.springdemo.restfulwebservices.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDao {

	private static List<User> listUsers = new ArrayList<>();

	static {
		listUsers.add(new User("100", "RD0", "Pune0"));
		listUsers.add(new User("101", "RD1", "Pune1"));
		listUsers.add(new User("102", "RD2", "Pune2"));
		listUsers.add(new User("103", "RD3", "Pune3"));
	}

	public List<User> getUsers() {
		return listUsers;
	}

	public User createUser(User userNew) {

		if (userNew == null)
			return null;

		for (User user : listUsers) {

			if (user.getId().equals(userNew.getId()))
				return null;
		}

		listUsers.add(userNew);
		return userNew;
	}

	public User createUser(String id, String name, String address) {

		if (id == null || name == null || address == null)
			return null;

		for (User user : listUsers) {

			if (user.getId().equals(id))
				return null;
		}

		listUsers.add(new User(id, name, address));
		return listUsers.get(listUsers.size() - 1);
	}

	public User getUser(String id) {

		for (User user : listUsers) {

			if (user.getId().equals(id))
				return user;
		}

		return null;
	}

	public boolean deleteUser(String id) {

		for (User user : listUsers) {

			if (user.getId().equals(id)) {
				listUsers.remove(user);
				return true;
			}
		}
		return false;
	}
}