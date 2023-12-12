package com.microsoft.tutorial.security;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.microsoft.tutorial.entity.Role;
import com.microsoft.tutorial.entity.User;
import com.microsoft.tutorial.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		 User user = userRepository.findByEmail(username).orElseThrow(()->new RuntimeException("User Not Found"));
		Set<Role> roles = user.getRoles();
		Set<SimpleGrantedAuthority> authorities = roles.stream().map(r->new SimpleGrantedAuthority(r.getName())).collect(Collectors.toSet());
		return new org.springframework.security.core.userdetails.User(username,user.getPassword(),authorities);
	}
}
