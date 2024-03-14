package com.menghour.student.exception;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<?> handleApiException(ApiException e, HttpServletRequest request){		 
		 String error =e.getStatus().getReasonPhrase();
	      HttpStatus status =e.getStatus();	      
	      StandardError err = new StandardError(
	                Instant.now(),
	                status.value(),
	                error,
	                e.getMessage(),
	                request.getRequestURI()
	        );
	      
	      return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> notValid(MethodArgumentNotValidException e, HttpServletRequest request) {
        String error = "Invalid Data";
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                error,
                e.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.toSet())
                        .toString()
                        .replaceAll("\\[*]*", ""),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(err);
    }
	
	
}
