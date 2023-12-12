package com.microsoft.tutorial.service.impl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.microsoft.tutorial.entity.User;
import com.microsoft.tutorial.repository.UserRepository;
import com.microsoft.tutorial.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
	@Override
	public User registerUser(User user) {
		// TODO Auto-generated method stub
		if(userRepository.existsByEmail(user.getEmail())) {
			throw new RuntimeException("Email Allready Registered");
		}
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		User save = userRepository.save(user);
		return save;
	}

	@Override
	public User updateUser(long id, User usr) {
		User user = getUserById(id);
		user.setPassword(usr.getPassword());
		User updated = userRepository.save(user);
		return updated;
	}

	@Override
	public String deleteUser(long id) {
		User user = getUserById(id);
		userRepository.delete(user);
		return "User Deleted Successfully";
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User getUserById(long id) {
		
		User user = userRepository.findById(id).orElseThrow(()->new RuntimeException("User Not Found"));
		
		return user;
	}

}
