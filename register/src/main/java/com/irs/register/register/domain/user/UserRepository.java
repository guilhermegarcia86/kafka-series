package com.irs.register.register.domain.user;

public interface UserRepository {
	
	User save(User user);
	
	User findByEmail(String email);

}
