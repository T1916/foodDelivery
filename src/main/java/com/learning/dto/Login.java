package com.learning.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;



@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "login", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
//property = "username")

public class Login 
{

	public Login() {
		// TODO Auto-generated constructor stub
	}
	public Login(String email, String password)
	{
		this.email = email;
		this.password = password;
	}

	@Id
	private Integer id;
	
	@Size(max=50)
	@Email
	private String email;
	
	@Size(max=100)
	@NotBlank
	private String password;
	
	
	@OneToOne
	@JoinColumn(name = "regId")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Register register;

}
