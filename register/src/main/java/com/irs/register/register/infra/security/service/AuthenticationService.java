package com.irs.register.register.infra.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.irs.register.register.infra.security.entity.User;
import com.irs.register.register.infra.security.repository.UserRepositoryAdapter;

@Service
public class AuthenticationService implements UserDetailsService{
	
	@Autowired
	private UserRepositoryAdapter repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optional = repository.findByEmail(username);
		
		if(optional.isPresent()) {
			return optional.get();
		}
		
		throw new UsernameNotFoundException("User not found");
	}	

}
