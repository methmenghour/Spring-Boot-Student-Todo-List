package com.menghour.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.menghour.student.dto.StudentDto;
import com.menghour.student.entity.Student;
import com.menghour.student.exception.ErrorResponse;
import com.menghour.student.mapper.StudentMapper;
import com.menghour.student.service.StudentService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@RequestMapping("students")
public class StudentController {

	@Autowired
	private final StudentService studentService;
	@PostMapping
	public ResponseEntity<?>create (@RequestBody @Valid StudentDto studentDto){	
		Student  student=StudentMapper.INSTANCE.studentDtoToStudent(studentDto);
	    student = studentService.create(student);
		//return ResponseEntity.ok(StudentMapper.INSTANCE.studentToStudentDto(student));
	    return ResponseEntity.ok(new ErrorResponse(HttpStatus.OK,"succeeded"));
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?>getOneStudent (@PathVariable Long id){	
		return ResponseEntity.ok(studentService.getById(id));
	}
}
