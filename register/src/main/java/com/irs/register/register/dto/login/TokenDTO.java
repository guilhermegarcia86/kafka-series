package com.irs.register.register.dto.login;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenDTO {
	
	private String type;
	private String token;

}
