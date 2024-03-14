package com.menghour.student.exception;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

//	@ExceptionHandler(ApiException.class)
//	public ResponseEntity<?> handleApiException(ApiException e){
//		 ErrorResponse errorResponse=new ErrorResponse(e.getStatus(),e.getMessage());
//		  return ResponseEntity
//				  .status(e.getStatus())
//				  .body(errorResponse);
//	}
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<?> handleApiException(ApiException e, HttpServletRequest request){
		// ErrorResponse errorResponse=new ErrorResponse(e.getStatus(),e.getMessage());	
		 
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
//	 @ExceptionHandler(Exception.class)
//	    public ResponseEntity<String> handleException(Exception e) {
//	        return new ResponseEntity<>("An unexpected error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//	    }
	
	/*
	 * @ExceptionHandler(ConstraintViolationException.class) public void
	 * handleConstraintViolationException(ConstraintViolationException exception,
	 * ServletWebRequest webRequest) throws IOException {
	 * webRequest.getResponse().sendError(HttpStatus.BAD_REQUEST.value(),
	 * exception.getMessage()); }
	 * 
	 * @ExceptionHandler(MethodArgumentNotValidException.class) public
	 * ResponseEntity<ErrorDto> handleException(MethodArgumentNotValidException ex)
	 * {
	 * 
	 * ErrorDto dto = new ErrorDto(HttpStatus.BAD_REQUEST, "Validation error");
	 * 
	 * dto.setDetailedMessages(ex.getBindingResult().getAllErrors().stream()
	 * .map(err -> err.unwrap(ConstraintViolation.class)) .map(err ->
	 * String.format("%s : %s", err.getPropertyPath(), err.getMessage()))
	 * .collect(Collectors.toList()));
	 * 
	 * return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
	 * 
	 * }
	 * 
	 * @Data public static class ErrorDto {
	 * 
	 * private final int status; private final String error; private final String
	 * message; private List<String> detailedMessages;
	 * 
	 * public ErrorDto(HttpStatus httpStatus, String message) { status =
	 * httpStatus.value(); error = httpStatus.getReasonPhrase(); this.message =
	 * message; }
	 * 
	 * }
	 */
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
