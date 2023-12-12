package com.microsoft.tutorial.service;

import java.util.List;

import com.microsoft.tutorial.entity.User;

public interface UserService {
	User registerUser(User user);

	User updateUser(long id, User user);

	String deleteUser(long id);

	List<User> getAllUser();

	User getUserById(long id);
}
