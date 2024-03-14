package com.menghour.student.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.menghour.student.entity.Student;
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
		Optional<Student> studentByid = studentRepository.findById(id);
		System.out.println("Student ======="+studentByid);
		if(studentByid.isPresent()) {
			return studentByid.get();
			
		}
		throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"student with id="+id+" not found");
	}
	
	

}
