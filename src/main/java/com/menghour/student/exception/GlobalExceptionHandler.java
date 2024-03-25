package com.menghour.student.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import jakarta.servlet.http.HttpServletRequest;

//@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<?> handleApiException(ApiException e){		 
		  return  ResponseHandler.generateResponse(e.getMessage(),e.getStatus(), "");

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> notValid(MethodArgumentNotValidException e) {
	   String message = e.getBindingResult()
                         .getFieldErrors()
                         .stream()
                         .map(FieldError::getDefaultMessage)
                         .collect(Collectors.toSet())
                         .toString()
                         .replaceAll("\\[*]*", "");
	   
	   return  ResponseHandler.generateResponse(message,HttpStatus.UNPROCESSABLE_ENTITY, "");

    }
	
//	@ExceptionHandler(ResourceNotFoundException.class)
//	    public ResponseEntity<?> handleNoHandlerFoundException(
//	            NoHandlerFoundException ex, HttpServletRequest httpServletRequest) {
//		 return  ResponseHandler.generateResponse(ex.getMessage(),HttpStatus.NOT_FOUND, "");
//	    }
		
	@ExceptionHandler(Exception.class)
    public ResponseEntity<Object> NoResourceFoundException(Exception ex) {
        return  ResponseHandler.generateResponse(ex.getMessage(),HttpStatus.NOT_FOUND, "");
    }
	/*
	 @ExceptionHandler({NoHandlerFoundException.class})
	    public ResponseEntity<?> handleNoHandlerFoundException(
	            NoHandlerFoundException ex, HttpServletRequest httpServletRequest) {
		 return  ResponseHandler.generateResponse(ex.getMessage(),HttpStatus.NOT_FOUND, "");
	    }
	*/
	
}
