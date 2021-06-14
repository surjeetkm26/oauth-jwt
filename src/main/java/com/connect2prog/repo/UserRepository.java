package com.connect2prog.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.connect2prog.models.UserRole;
@Repository
public interface UserRepository extends MongoRepository<UserRole, Integer>{
	@Query(value = "{userName : ?0}")
	Optional<UserRole> findByUserName(String userName);
	@Query(value = "{userName : ?0}")
	Boolean existByUsername(String username);
	@Query(value = "{email : ?0}")
	Boolean existByEmail(String email);
}
