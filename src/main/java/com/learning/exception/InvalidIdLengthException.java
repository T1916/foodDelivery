package com.learning.exception;

import lombok.ToString;

@ToString(callSuper = true)
public class InvalidIdLengthException extends Exception 
{
	// as a User Defined exception.

	public InvalidIdLengthException(String message) 
	{
		super(message);
		// TODO Auto-generated constructor stub
	}

//	@Override
//	public String toString() 
//	{
//		return "IdNotFoundException [toString()=" + super.toString() + "]";
//	}
	
	

}
