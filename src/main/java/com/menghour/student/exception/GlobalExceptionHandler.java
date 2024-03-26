package com.menghour.student.exception;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.servlet.ServletException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	/*
	 Class ResourceNotFoundException 
	 example :
		 studentRepository.findById(id)
		 .orElseThrow(()-> new ResourceNotFoundException("student",id));
		  
	 */
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<?> handleApiException(ApiException e){		 
		  return  ResponseHandler.generateResponse(e.getMessage(),e.getStatus(), "");

	}

	/*
	Class MethodArgumentNotValidException 
	 example:
		  in class StudentDto
		  @NotNull(message = "Student Name is not Null")
		 @NotBlank(message = "Student Name is not Blank")
		 private String name;
		 
		 in Controller
		 
		 @PostMapping
		public ResponseEntity<?>create (@RequestBody @Valid StudentDto studentDto){	
			Student  student=StudentMapper.INSTANCE.studentDtoToStudent(studentDto);
		    student = studentService.create(student);
		    return ResponseHandler.generateResponse("Successfully created data!", HttpStatus.OK, student);
	}
	  
	 */
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
	/*
	 Class ServletException
	 
	 public class NoResourceFoundException extends ServletException implements ErrorResponse.
	  Raised when ResourceHttpRequestHandler can not find a resource
	 */
	
	@ExceptionHandler(ServletException.class)
    public ResponseEntity<Object> NoResourceFoundException(ServletException ex) {
        return  ResponseHandler.generateResponse(ex.getMessage(),HttpStatus.NOT_FOUND, "");
    }
	/*
	 Class MethodArgumentTypeMismatchException
	 
	     public class MethodArgumentTypeMismatchException extends TypeMismatchException. 
	     A TypeMismatchException raised while resolving a controller method argument.
	      Provides access to the target MethodParameter   
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> NoResourceFoundException(MethodArgumentTypeMismatchException ex) {
		System.out.println(ex);
        return  ResponseHandler.generateResponse(ex.getMessage(),HttpStatus.BAD_REQUEST, "");
    }
	 
}
