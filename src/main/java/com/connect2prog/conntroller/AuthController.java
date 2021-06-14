package com.connect2prog.conntroller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.connect2prog.dto.JWTRequest;
import com.connect2prog.dto.JWTResponse;
import com.connect2prog.jwt.util.JWTUtility;
import com.connect2prog.model.MessageResponse;
import com.connect2prog.models.ERole;
import com.connect2prog.models.Role;
import com.connect2prog.models.UserRole;
import com.connect2prog.repo.RoleRepository;
import com.connect2prog.repo.UserRepository;
import com.connect2prog.service.UserService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JWTUtility jwtUtils;
	@Autowired
	private UserService userService;
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser( @RequestBody JWTRequest jwtRequest) {
		log.info("User Sign In ========================");

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		final UserDetails userDetails=userService.loadUserByUsername(jwtRequest.getUsername());
		final String token=jwtUtils.generateToken(userDetails);
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		JWTResponse jwtResponse=new JWTResponse();
		jwtResponse.setJwtToken(token);
		return ResponseEntity.ok(jwtResponse);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody JWTRequest signUpRequest) {
		log.info("User Sign UP========================");
		/*
		 * boolean
		 * usernames=userRepository.existByUsername(signUpRequest.getUsername());
		 * log.info("Username exist:"+ usernames); if (usernames) { return
		 * ResponseEntity .badRequest() .body(new
		 * MessageResponse("Error: Username is already taken!")); }
		 */


		// Create new user's account
	    String encriptedPasswd=passwordEncoder.encode(signUpRequest.getPassword());
	    System.out.println("Password encoded=========================="+ encriptedPasswd);
		UserRole user1=new UserRole();
		user1.setUserName(signUpRequest.getUsername());
		user1.setEmail(signUpRequest.getEmail());
		user1.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
		log.info("UserName::::::::::::::::==========:"+ signUpRequest.getUsername());
		Set<String> strRoles = signUpRequest.getStrRoles();
		Set<Role> roles = new HashSet<>();
		
		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER).get();
			if(userRole==null) {
				throw new RuntimeException("Error: Role is not found==========================================.");
			}else {
				roles.add(userRole);	
			}
			//Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					//.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			
		} else {
			log.info("ELse Part==========================================");
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_ANONYMOUS)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user1.setRoles(roles);
		userRepository.save(user1);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
