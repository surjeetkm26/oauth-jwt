package com.connect2prog.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("LOADING Username====================");
		//Logic to get user from database
		//but here let's create a dummy object here for username
		//new ArrayList()---> List of roles
		return new User("admin","password",new ArrayList<>());
	}
}
