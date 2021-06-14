package com.connect2prog.models;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.annotations.NotThreadSafe;

import lombok.Data;
@Data
@Document("users")
public class UserRole {

	@Id
	private String id;
	private String userName;
	private String password;
	private String email;
	@DBRef
	private Set<Role> roles=new HashSet<>();
}
