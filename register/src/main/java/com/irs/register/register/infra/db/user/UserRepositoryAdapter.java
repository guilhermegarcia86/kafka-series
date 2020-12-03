package com.irs.register.register.infra.db.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.irs.register.register.domain.user.User;
import com.irs.register.register.domain.user.UserRepository;

@Component
public class UserRepositoryAdapter implements UserRepository {
	
	@Autowired
	private MongoRepository mongoRepository;

	@Override
	public User save(User user) {
		//Mapper to User Entity
		return null;
	}

	@Override
	public User findByEmail(String email) {
		//Mapper to User Domain
		return null;
	}

}
