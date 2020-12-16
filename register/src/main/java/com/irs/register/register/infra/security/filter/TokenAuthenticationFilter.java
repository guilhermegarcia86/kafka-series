package com.irs.register.register.infra.security.filter;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.irs.register.register.infra.security.entity.User;
import com.irs.register.register.infra.security.repository.UserRepositoryPort;
import com.irs.register.register.infra.security.service.TokenService;

public class TokenAuthenticationFilter extends OncePerRequestFilter {
	
	private final TokenService tokenService;
	private final UserRepositoryPort repository;
	
	public TokenAuthenticationFilter(TokenService tokenService, UserRepositoryPort repository) {
		this.tokenService = tokenService;
		this.repository = repository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String tokenFromHeader = getTokenFromHeader(request);
		boolean tokenValid = tokenService.isTokenValid(tokenFromHeader);
		if(tokenValid) {
			this.authenticate(tokenFromHeader);
		}

		filterChain.doFilter(request, response);
	}

	private void authenticate(String tokenFromHeader) {
		Integer id = tokenService.getTokenId(tokenFromHeader);
		
		Optional<User> optionalUser = repository.findById(id);
		
		if(optionalUser.isPresent()) {
			
			User user = optionalUser.get();
			
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getPerfis());
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		}
	}

	private String getTokenFromHeader(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		
		return token.substring(7, token.length());
	}

}
