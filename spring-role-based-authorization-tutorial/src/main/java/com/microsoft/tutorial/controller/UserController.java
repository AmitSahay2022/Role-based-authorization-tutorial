package com.microsoft.tutorial.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microsoft.tutorial.entity.Role;
import com.microsoft.tutorial.entity.User;
import com.microsoft.tutorial.repository.RoleRepository;
import com.microsoft.tutorial.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
	private UserService userService;
	private RoleRepository roleRepository;

	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody User user) {
		Role role = roleRepository.findById(2).get();
		user.getRoles().add(role);
		return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
	}
	@PutMapping("{userId}")
	public ResponseEntity<User> updateUser(@PathVariable long userId,@RequestBody User user){
		return new ResponseEntity<User>(userService.updateUser(userId, user),HttpStatus.ACCEPTED);
	}
	@DeleteMapping("{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable long userId){
		return new ResponseEntity<String>(userService.deleteUser(userId),HttpStatus.OK);
	}
	@GetMapping("{userId}")
	public ResponseEntity<User> getUserDetails(@PathVariable long userId){
	  return new ResponseEntity<User>(userService.getUserById(userId),HttpStatus.OK);
	}
	@GetMapping	
	public ResponseEntity<List<User>> getAllUsers(){
		return new ResponseEntity<List<User>>(userService.getAllUser(),HttpStatus.OK);
	}
}
