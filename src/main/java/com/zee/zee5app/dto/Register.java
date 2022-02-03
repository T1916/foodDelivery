package com.zee.zee5app.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

import javax.naming.InvalidNameException;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.zee.zee5app.exception.InvalidEmailException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidPasswordException;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


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
	public Register(String string, String string2, String string3, String string4, String string5) 
	{
		// TODO Auto-generated constructor stub
	}
	
//	public Register(String id, String firstName, String lastName, String email, String password, String ContactNumber) 
//			throws InvalidIdLengthException, InvalidNameException 
//	{
//		super();
//		this.setId(id);
//		this.setFirstName(firstName);
//		this.setLastName(lastName);
//		this.setEmail = email;
//		this.password = password;
//		this.setContactNumber(ContactNumber);
//	}

	
	// it will consider this column as Primary Key
	// camel naming conventions are converted to snake case
	@Column(name = "regid")	
	@Id
	private String id;
	
	// it should have length of 6.
	// we have to write a code to validate the length
	// then assign the value.
	
	@Size(max=50)
	@NotBlank
	private String firstName;
	
	@Size(max=50)
	@NotBlank
	private String lastName;
	
	@Size(max=50)
	@Email
	private String email;
	
	@Size(max=100)
	@NotBlank
	private String password;
	
	@NotBlank
	private BigDecimal ContactNumber;
	
	
//	public Register()
//	{
//		// explicit default constructor
//		// any kind of customization during the initialization of object
//		// then its better to introduce EDC or PC as per the need.
//		
//	}
	
	

	@Override
	public int compareTo(Register o) 
	{
		// TODO Auto-generated method stub
		return o.id.compareTo(this.getId());
	}
	
	public void setContactNumber(String contactNumber) {
		this.ContactNumber = new BigDecimal(contactNumber);
	}

	
	
	
	// private stuff will be accessible only inside the class.
	
	// setter : is used to set the value for a particular field.
	
	// getter : to get/return the value of a specific field. 
	
	// LOMBOK 
	

}
