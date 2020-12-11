package com.irs.register.register.application.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irs.register.register.infra.security.service.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenDTO> auth(@RequestBody @Validated LoginDTO loginDTO){
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.getUser(), loginDTO.getPass());
		
		Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		
		String token = tokenService.generateToken(authentication);
		
		return ResponseEntity.ok(TokenDTO.builder().type("Bearer").token(token).build());
		
	}

}
