package com.irs.register.register.infra.db.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.irs.register.register.entity.User;


@Component
public class UserRepositoryAdapter {
	
	@Autowired
	private AuthRepository authRepository;

	public User save(User user) {
		//Mapper to User Entity
		return null;
	}

	public Optional<User> findByEmail(String email) {
		//Mapper to User Domain
		return null;
	}
	
	public Optional<User> findById(Integer id){
		return null;
	}

}
