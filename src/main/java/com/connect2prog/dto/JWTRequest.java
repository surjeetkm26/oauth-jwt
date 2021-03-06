package com.connect2prog.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JWTRequest {

	private String username;
	private String password;
	private String email;
	private Set<String> strRoles;
}
