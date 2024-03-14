package com.menghour.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.menghour.student.entity.Student;
import com.menghour.student.exception.ApiException;
import com.menghour.student.exception.ResourceNotFoundException;
import com.menghour.student.repository.StudentRepository;
import com.menghour.student.service.StudentService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

	@Autowired
	private final StudentRepository studentRepository;
	@Override
	public Student create(Student student) {
		return studentRepository.save(student);
	}
	@Override
	public Student getById(Long id) {
		return studentRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("student",id));
	}

}
