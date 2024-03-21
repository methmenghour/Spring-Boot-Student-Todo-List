package com.menghour.student.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.menghour.student.dto.StudentDto;
import com.menghour.student.entity.Student;

@Mapper
public interface StudentMapper {
	StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);
	 Student studentDtoToStudent(StudentDto studentDto);
	 @Mapping(target = "id", ignore = true)
	 StudentDto studentToStudentDto(Student student);
}