package com.hostel.advice;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.id.IdentifierGenerationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hostel.exception.HostelEmptyException;
import com.hostel.exception.UserNotFoundException;

@RestControllerAdvice
public class HostelExceptionHandler extends Exception{

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> inValidDataException(MethodArgumentNotValidException ex) {
		Map<String, String> exceptionMap = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error ->{
			exceptionMap.put(error.getField(), error.getDefaultMessage());
		});
		return exceptionMap;
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public Map<String, String> inValidUserException(UserNotFoundException ex){
		Map<String, String> exceptionMap = new HashMap<>();
		exceptionMap.put("ErrorMessgae", ex.getMessage());
		return exceptionMap;
	}
	
	@ExceptionHandler(IdentifierGenerationException.class)
	public Map<String, String> identifierGenerationException(IdentifierGenerationException ex) {
		Map<String, String> exceptionMap = new HashMap<>();
		exceptionMap.put("errorMessage", ex.getMessage());
		return exceptionMap;
	}
	
	@ExceptionHandler(HostelEmptyException.class)
	public Map<String, String> noHostelExistsExption(HostelEmptyException ex){
		Map<String, String> exceptionMap = new HashMap<>();
		exceptionMap.put("errorMessage", ex.getMessage());
		return exceptionMap;
	}
}
