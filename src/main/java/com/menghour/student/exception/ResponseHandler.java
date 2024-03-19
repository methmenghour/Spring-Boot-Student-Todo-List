package com.menghour.student.exception;

import java.time.Instant;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
	public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object data) {
		Map<String, Object> map = new TreeMap<String, Object>();
	
		map.put("data", data);
		map.put("timestamp",Instant.now());
		map.put("error",status.getReasonPhrase());
		map.put("message", message);
		map.put("status", status.value());
		return new ResponseEntity<>(map, status);
	}
}
