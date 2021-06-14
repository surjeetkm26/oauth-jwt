package com.connect2prog.service;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.connect2prog.models.UserRole;
import com.connect2prog.repo.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("LoadByUsername==============================");
		String name=userRepository.findByUserName(username).get().getUserName();
		log.info(name);
		UserRole user=userRepository.findByUserName(username).orElseThrow(()-> new UsernameNotFoundException("User Not Found"+username));
		log.info("USERNAME============================="+user.getUserName());
		log.info("USER ROLES================================================");
		user.getRoles().stream().forEach(System.out::print);
		
		return new UserDetailsImpl().build(user);
	}
}
