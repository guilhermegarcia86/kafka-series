package com.irs.register.register.infra.security.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.irs.register.register.infra.security.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
	
	Optional<User> findByEmail(String email);

}
