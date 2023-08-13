package com.ethfundwave.ethfundwave.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ethfundwave.ethfundwave.models.ERole;
import com.ethfundwave.ethfundwave.models.Role;

public interface RoleRepo extends MongoRepository<Role,String>{
	Optional<Role> findByName(ERole name);
}
