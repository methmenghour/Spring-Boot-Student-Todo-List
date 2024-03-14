package com.menghour.student.service;

import com.menghour.student.entity.Student;

public interface StudentService {
	Student create(Student student);
	Student getById(Long id);
}
