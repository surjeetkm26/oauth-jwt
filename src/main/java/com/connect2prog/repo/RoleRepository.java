package com.connect2prog.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.connect2prog.models.ERole;
import com.connect2prog.models.Role;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
	@Query(value = "{name : ?0}")
	Optional<Role> findByName(ERole name);
}
