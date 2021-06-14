package com.connect2prog.conntroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.connect2prog.dto.JWTRequest;
import com.connect2prog.dto.JWTResponse;
import com.connect2prog.jwt.util.JWTUtility;
import com.connect2prog.service.UserService;

//@RestController
public class UserController {

	@Autowired
	private JWTUtility jWTUtility;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	@GetMapping("/getmsg")
	public String getMessage() {
		return "Hello Good Morning!";
	}
	@PostMapping("/authenticate")
	public JWTResponse authenticate(JWTRequest jwtRequest)throws Exception {
		System.out.println("Authentic Reached==============");
		try {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						jwtRequest.getUsername(), 
						jwtRequest.getPassword()));
		}catch(BadCredentialsException exec) {
			//throw new Exception("Bad Credential");
			System.out.println("Error: Bad Credential");
			exec.printStackTrace();
		}
		//Once Authentication is done we need create JWT Token
		final UserDetails userDetails=userService.loadUserByUsername(jwtRequest.getUsername());
		final String token=jWTUtility.generateToken(userDetails);
		//return new JWTResponse(token);
		return null;
	}
}
