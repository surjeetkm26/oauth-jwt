package com.connect2prog.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@ComponentScan(basePackages = {"com.connect2prog.conntroller","com.connect2prog.config","com.connect2prog.service","com.connect2prog.jwt.util","com.connect2prog.filter"})
public class JwtAuthSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtAuthSpringApplication.class, args);
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
