package com.connect2prog.service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.connect2prog.models.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
@Data
@AllArgsConstructor
@Slf4j
public class UserDetailsImpl implements UserDetails{

	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	public UserDetailsImpl() {}
	private String id;
	private String username;
	private String email;
	@JsonProperty
	private String password;
	
	public static UserDetailsImpl build(UserRole user) {
		List<GrantedAuthority> grantedAuthority= user.getRoles().stream().map(role->new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
		
		List<String> list=grantedAuthority.stream().map(x->x.getAuthority()).collect(Collectors.toList());
		log.info("Granted Aurgority=============================="+ list);
		log.info("USERID::::::::::"+ user.getId());
		log.info("USERNAME::::::::::"+ user.getId());
		log.info("PASSWORD::::::::::"+ user.getId());
		log.info("Granted AUThority::::::::::"+ grantedAuthority);
		
		return new UserDetailsImpl(user.getId(), user.getUserName(), user.getEmail(), user.getPassword(), grantedAuthority);
	}
	private Collection<? extends GrantedAuthority> authorities;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}	
}