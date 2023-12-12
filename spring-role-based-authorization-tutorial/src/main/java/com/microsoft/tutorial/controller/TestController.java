package com.microsoft.tutorial.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {
	@GetMapping("/admin")
	public ResponseEntity<String> adminDashboard(){
		return new ResponseEntity<String>("Admin Dashboard",HttpStatus.OK);
	}
	@GetMapping("/user")
	public ResponseEntity<String> userDashboard(){
		return new ResponseEntity<String>("User Dashboard",HttpStatus.OK);
	}
	@GetMapping("/any")
	public ResponseEntity<String> anyDashboard(){
		return new ResponseEntity<String>("any Dashboard",HttpStatus.OK);
	}

}
