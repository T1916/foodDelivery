package com.learning.exception.apierror;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.learning.exception.apierror.ApiSubError;
import com.learning.exception.apierror.ApiValidationError;

import lombok.Data;

@Data
public class ApiError 
{
private HttpStatus httpStatus;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timeStamp;
	
	private String message;
	private String debugMessage;
	private List<ApiSubError> subErrors; // to hold validational related errors
	
	private ApiError()
	{
		timeStamp = LocalDateTime.now(); // @ the time of calling the method, that date and time will be provided when the method is called
	}
	
	
	public ApiError(HttpStatus status, String message, Throwable ex)
	{
		this();
		this.httpStatus = status;
		this.message = message;
		this.debugMessage = ex.getLocalizedMessage();
	}
	
	// every field validation in subError
	// create and add it into subError
	
	public ApiError(HttpStatus status) 
	{
		// TODO Auto-generated constructor stub
		this.httpStatus = status;
	}


	private void addSubError(ApiSubError apiSubError)
	{
		if(subErrors == null)
		{
			subErrors = new ArrayList<>();
		}
		subErrors.add(apiSubError);
	}
	
	private void addValidationError(String object, String field, Object rejectedValue, String message)
	{
		addSubError(new ApiValidationError(object, field, rejectedValue, message));
	}
	
	private void addValidationError(String object, String message)
	{
		addSubError(new ApiValidationError(object, message));
	}
	
	private void addValidationError(FieldError fieldError)
	{
		this.addValidationError(fieldError.getObjectName(), fieldError.getField(), fieldError.getRejectedValue(), fieldError.getDefaultMessage());
	}
	
	public void addValidationErrors(List<FieldError> fieldErrors)
	{
		//fieldErrors.forEach(this::addValidationError);
		fieldErrors.forEach(e->this.addValidationError(e));
	}
	
	private void addValidationError(ObjectError objectError)
	{
		this.addValidationError(objectError.getObjectName(), objectError.getDefaultMessage());
	}
	
	public void addValdationError(List<ObjectError> globalErrors) 
	{
		globalErrors.forEach(e->this.addValidationError(e));
		
	}
	
	public void addValidationError(ConstraintViolation<?> cv)
	{
		this.addValidationError(cv.getRootBeanClass().getName(),
				((PathImpl)cv.getPropertyPath()).getLeafNode().asString(), 
				cv.getInvalidValue(), 
				cv.getMessage());
	}
	
	public void addValidationsErrors(Set<ConstraintViolation<?>> constraintViolations)
	{
		constraintViolations.forEach(e->addValidationError(e));
	}

}