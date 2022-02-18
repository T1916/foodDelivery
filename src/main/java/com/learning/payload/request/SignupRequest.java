package com.learning.payload.request;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class SignupRequest 
{
		
	@NotBlank
	@Size(min = 3, max = 100)
	private String email;
	
	@NotBlank
	@Size(min = 3, max = 20)
	private String name;
	
	@NotBlank
	@Size(min = 3, max = 20)
	private String password;
	
	@NotBlank
	@Size(min = 3, max = 200)
	private String address;
	
	private Set<String> role;
}
