package com.irs.register.register.infra.security.repository;

import java.util.Optional;

import com.irs.register.register.infra.security.entity.User;

public interface UserRepositoryPort {
	
	Optional<User> findById(Integer id);
	
	Optional<User> findByEmail(String email);
	
	User save(User user);

}
