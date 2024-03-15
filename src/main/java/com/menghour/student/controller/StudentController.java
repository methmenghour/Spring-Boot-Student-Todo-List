package com.menghour.student.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.menghour.student.dto.PageDto;
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
		return ResponseEntity.ok(student);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?>getOneStudent(@PathVariable Long id){	
		return ResponseEntity.ok(studentService.getById(id));
	}
	@PutMapping("{id}")
	public ResponseEntity<?>  update(@PathVariable("id") Long studentId, @RequestBody @Valid  StudentDto studentDto){
		Student  student=StudentMapper.INSTANCE.studentDtoToStudent(studentDto);
	    student = studentService.update(studentId,student);
		return ResponseEntity.ok(student);
	}
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long studentId){
		studentService.delete(studentId);
		return ResponseEntity.ok(new ErrorResponse(HttpStatus.OK,"successful"));
	}

	
	@GetMapping 
	public ResponseEntity<?> getStudents(){ 
		return ResponseEntity.ok(studentService.getStudents()); 
	}
	 
	@GetMapping("v2/")
	public ResponseEntity<?> getStudentsBySpecification(@RequestParam Map<String, String> params){
		return ResponseEntity.ok(studentService.getBrandsBySpecification(params));
	}
 
	@GetMapping("v3/")
	public ResponseEntity<?> getStudentsBySpecAndPage(@RequestParam Map<String, String> params){
		
		Page<Student> pageStudent = studentService.getBrandsBySpecificationAndPagination(params);
		PageDto pageDto=new PageDto(pageStudent);
			
		return ResponseEntity.ok(pageDto);
		}
}
