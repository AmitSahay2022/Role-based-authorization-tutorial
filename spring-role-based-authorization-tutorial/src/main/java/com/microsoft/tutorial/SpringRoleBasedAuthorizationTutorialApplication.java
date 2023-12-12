package com.microsoft.tutorial;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.microsoft.tutorial.entity.Role;
import com.microsoft.tutorial.entity.User;
import com.microsoft.tutorial.repository.RoleRepository;
import com.microsoft.tutorial.repository.UserRepository;
import com.microsoft.tutorial.service.UserService;

@SpringBootApplication
public class SpringRoleBasedAuthorizationTutorialApplication implements CommandLineRunner {
	@Autowired
    private RoleRepository roleRepository;
	@Autowired
	private UserService userService;
	@Override
	public void run(String... args) throws Exception {
		//As soon as Application Starts all the Role will be saved inside DB
		if(roleRepository.count()<=0) {
		List<Role> roles=new ArrayList<>();
		roles.add(new Role("Admin", "Manage Everything"));
		roles.add(new Role("User", "Normal user"));
		roleRepository.saveAll(roles);
		}
		//Now Create Administrator User
		try {
		User user=new User("amit2022@gmail.com", "test1234");
		Role role = roleRepository.findById(1).get();
		user.getRoles().add(role);
		userService.registerUser(user);
		}catch(RuntimeException e) {
			System.out.println(e.getLocalizedMessage());
		}
		
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringRoleBasedAuthorizationTutorialApplication.class, args);
	}

}
