package com.connect2prog.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JWTResponse {
	
	private String jwtToken;
	private String id;
	private String username;
	private String email;
	private List<String> roles;
	/*
	 * public JWTResponse(String jwtToken) { this.jwtToken=jwtToken; }
	 */
}
