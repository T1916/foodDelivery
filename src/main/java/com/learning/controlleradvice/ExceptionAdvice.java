package com.learning.controlleradvice;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.learning.exception.AlreadyExistsException;
import com.learning.exception.IdNotFoundException;

@RestControllerAdvice
public class ExceptionAdvice 
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

}
