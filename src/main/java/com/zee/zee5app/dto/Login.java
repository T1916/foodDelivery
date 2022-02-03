package com.zee.zee5app.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "login", uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
public class Login 
{
	public Login()
	{
		
	}
//	public Login(String username, String password, String id)
//	{
//		super();
//		this.setUsername(username);
//		this.setPassword(password);
//		this.setId(id);
//	}
	
	@NotBlank
	private String username;
	@NotBlank
	private String password;
	@Id
	private String id;
	private ROLE role;
}
