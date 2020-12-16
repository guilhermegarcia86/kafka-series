package com.irs.register.register.application.controller.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenDTO {
	
	private String type;
	private String token;

}
