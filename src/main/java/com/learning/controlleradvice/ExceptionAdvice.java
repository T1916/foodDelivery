package com.learning.controlleradvice;

import java.util.HashMap;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.learning.exception.AlreadyExistsException;
import com.learning.exception.IdNotFoundException;
import com.learning.exception.NameNotFoundException;
import com.learning.exception.apierror.ApiError;

@RestControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler
{
	// this class should be used when any user defined exception is called throughtout 
	// all the controller
	
	@ExceptionHandler(AlreadyExistsException.class)
	public ResponseEntity<?> alreadyRecordExistsExceptionHandler(AlreadyExistsException e)
	{
		HashMap<String, String> map = new HashMap<>();
		map.put("message", "Record already exists" + e.getMessage());
		return ResponseEntity.badRequest().body(map);
		
	}
	
	@ExceptionHandler(NameNotFoundException.class)
	public ResponseEntity<?> NameNotFoundExceptionHandler(NameNotFoundException e)
	{
		HashMap<String, String> map = new HashMap<>();
		map.put("message", "Sorry food not found" + e.getMessage());
		return ResponseEntity.badRequest().body(map);
		
	}
	
	
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<?> exceptionHandler(Exception e)
//	{
//		HashMap<String, String> map = new HashMap<>();
//		map.put("message", "unknown Exception" + e.getMessage());
//		return ResponseEntity.badRequest().body(map);
//		
//	}
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<?> idNotFoundExceptionHandler(IdNotFoundException e)
	{
		HashMap<String, String> map = new HashMap<>();
		map.put("message", "" + e.getMessage());
		return ResponseEntity.badRequest().body(map);
		
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) 
	{
		// TODO Auto-generated method stub
		
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMessage("Validation Error");
		apiError.addValidationErrors(ex.getBindingResult().getFieldErrors()); // field wise errors
		apiError.addValdationError(ex.getBindingResult().getGlobalErrors());
		return buildResponseEntity(apiError);
	}
	
	private ResponseEntity<Object> buildResponseEntity(ApiError apiError)
	{
		// to get which RE object ===> if I want to make any changes into our existing object then in every return we have to do the change or not?
		// instead of that if we will use buildRE method ===> Ease of maintenance
		return new ResponseEntity<>(apiError, apiError.getHttpStatus());
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handleConstraintViolation() 
	{
		return null;
	}

}
