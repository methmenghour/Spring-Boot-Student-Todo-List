package com.menghour.student.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ApiException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5794502172001356952L;

	public ResourceNotFoundException(String resourceName, Long id) {
		super(HttpStatus.NOT_FOUND, String.format("%s With id = %d not found",resourceName,id ));
	}

}
