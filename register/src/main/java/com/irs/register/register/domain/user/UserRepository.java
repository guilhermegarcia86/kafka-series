package com.irs.register.register.domain.user;

import java.util.Optional;

public interface UserRepository {
	
	User save(User user);
	
	Optional<User> findByEmail(String email);

}
