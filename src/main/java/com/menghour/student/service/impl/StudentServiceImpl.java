package com.menghour.student.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.menghour.student.entity.Student;
import com.menghour.student.exception.ResourceNotFoundException;
import com.menghour.student.repository.StudentRepository;
import com.menghour.student.service.StudentService;
import com.menghour.student.service.util.PageUtil;
import com.menghour.student.spec.StudentFilter;
import com.menghour.student.spec.StudentSpec;

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
	@Override
	public Student update(Long id, Student studentUpdate) {
		Student student= getById(id);
		student.setName(studentUpdate.getName());
		student.setAge(studentUpdate.getAge());
		student.setPhoneNumber(studentUpdate.getPhoneNumber());
		return studentRepository.save(student);
	}
	@Override
	public void delete(Long id) {
		Student student=getById(id);
		studentRepository.deleteById(student.getId());
	}
	@Override
	public List<Student> getStudents() {	
		return studentRepository.findAll();
	}
	@Override
	public List<Student> getBrandsBySpecification(Map<String, String> params) {
        StudentFilter studentFilter = new StudentFilter();
		
        if(params.containsKey("id")) {
			String id = params.get("id");
			studentFilter.setId(Long.parseLong(id));
		}
		if(params.containsKey("name")) {
			String name = params.get("name");
			studentFilter.setName(name);
		}
		if(params.containsKey("age")) {
			String age = params.get("age");
			studentFilter.setAge(Integer.parseInt(age));
		}
		if(params.containsKey("phoneNumber")) {
			String phoneNumber = params.get("phoneNumber");
			studentFilter.setPhoneNumber(phoneNumber);
		}
		StudentSpec studentSpec  = new StudentSpec(studentFilter);

		return studentRepository.findAll(studentSpec);
	}
	@Override
	public Page<Student> getBrandsBySpecificationAndPagination(Map<String, String> params) {
        
		StudentFilter studentFilter = new StudentFilter();
		
        if(params.containsKey("id")) {
			String id = params.get("id");
			studentFilter.setId(Long.parseLong(id));
		}
		if(params.containsKey("name")) {
			String name = params.get("name");
			studentFilter.setName(name);
		}
		if(params.containsKey("age")) {
			String age = params.get("age");
			studentFilter.setAge(Integer.parseInt(age));
		}
		if(params.containsKey("phoneNumber")) {
			String phoneNumber = params.get("phoneNumber");
			studentFilter.setPhoneNumber(phoneNumber);
		}
		
		int pageLimit = PageUtil.DEFAULT_PAGE_SIZE_LIMIT;	
		if(params.containsKey(PageUtil.PAGE_SIZE_LIMIT)) {
			pageLimit = Integer.parseInt(params.get(PageUtil.PAGE_SIZE_LIMIT));
		}
		
		int pageNumber = PageUtil.DEFAULT_PAGE_NUMBER;
		if(params.containsKey(PageUtil.PAGE_NUMBER)) {
			pageNumber = Integer.parseInt(params.get(PageUtil.PAGE_NUMBER));
		}
		
		StudentSpec studentSpec  = new StudentSpec(studentFilter);
		
		Pageable pageable = PageUtil.getPageable(pageNumber, pageLimit);

		Page<Student> studentPage = studentRepository.findAll(studentSpec,pageable);
		
		return studentPage;
	}
	

}
