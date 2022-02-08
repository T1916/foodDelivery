package com.learning.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.naming.InvalidNameException;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.learning.exception.InvalidEmailException;
import com.learning.exception.InvalidIdLengthException;
import com.learning.exception.InvalidPasswordException;
import com.learning.utils.CustomListSerializer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Setter
@Getter
@ToString
//@EqualsAndHashCode
//@NoArgsConstructor
@Entity // entity class is used for ORM 
// we can customize the table name
@AllArgsConstructor
@Table(name = "reg")
public class Register implements Comparable<Register> 
{
	public Register()
	{
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Size(max=50)
	@Email
	private String email;
	
	@Size(max=150)
	@NotBlank
	private String name;
	
	@Size(max=100)
	@NotBlank
	private String password;
	
	@Size(max=200)
	@NotBlank
	private String address;

	@Override
	public int compareTo(Register o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
//	@OneToOne(mappedBy = "register", cascade = CascadeType.ALL)
//	private Login login;
//	

}
