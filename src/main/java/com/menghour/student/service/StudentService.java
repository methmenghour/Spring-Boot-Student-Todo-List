package com.menghour.student.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.menghour.student.entity.Student;

public interface StudentService {
	Student create(Student student);
	Student getById(Long id);
	Student update(Long id, Student studentUpdate);
	void  delete(Long id);

	List<Student> getStudents();
	List<Student> getBrandsBySpecification(Map<String, String> params);
	Page<Student> getBrandsBySpecificationAndPagination(Map<String, String> params);
	

}
