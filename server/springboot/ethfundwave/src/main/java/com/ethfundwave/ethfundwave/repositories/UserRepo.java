package com.ethfundwave.ethfundwave.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ethfundwave.ethfundwave.models.User;

public interface UserRepo extends MongoRepository<User,String>{
	Optional<User> findByUserName(String username);
	
	Boolean existsByUsername(String username);
	
	Boolean existsByEmail(String email);
	
}
