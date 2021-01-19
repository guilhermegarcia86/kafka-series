package com.irs.sender.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Person {
	
	String name;
	String email;

}
