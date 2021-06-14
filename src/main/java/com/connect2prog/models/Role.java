package com.connect2prog.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Document("roles")
@Data
@AllArgsConstructor
public class Role {
	@Id
	private String id;
	private ERole name;
	public Role() {}
	public Role(ERole name) {
		this.name=name;
	}
}
