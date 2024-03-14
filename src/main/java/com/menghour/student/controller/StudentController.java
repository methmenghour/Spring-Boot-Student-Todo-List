package com.menghour.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.menghour.student.entity.Student;
import com.menghour.student.service.StudentService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@RequestMapping("students")
public class StudentController {

	@Autowired
	private final StudentService studentService;
	
	@PostMapping
	public ResponseEntity<?>create (@RequestBody Student student){	
		return ResponseEntity.ok(studentService.create(student));
	}
	@GetMapping("{id}")
	public ResponseEntity<?>getOneStudent (@PathVariable Long id){	
		return ResponseEntity.ok(studentService.getById(id));
	}
}
