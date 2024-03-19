package com.menghour.student.exception;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
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
	
	
}
