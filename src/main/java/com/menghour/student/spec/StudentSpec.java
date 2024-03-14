package com.menghour.student.spec;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.jpa.domain.Specification;

import com.menghour.student.entity.Student;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;

@Data
public class StudentSpec implements Specification<Student> {
	private final StudentFilter studentFilter;
	@Override
	public Predicate toPredicate(Root<Student> student,CriteriaQuery<?> query, CriteriaBuilder cb) {
	List<Predicate> predicates = new ArrayList<>();
	
	if(Objects.nonNull(studentFilter.getId())) { 
		Predicate id = student.get("id").in(studentFilter.getId());
		predicates.add(id);
	}
	if(Objects.nonNull(studentFilter.getName())) { 
		Predicate name = cb.like(cb.upper(student.get("name")), "%"+studentFilter.getName().toUpperCase() + "%");
		predicates.add(name);

	}
	if(Objects.nonNull(studentFilter.getAge())) { 
		Predicate age = student.get("age").in(studentFilter.getAge());
		predicates.add(age);
	}
	if(Objects.nonNull(studentFilter.getPhoneNumber())) { 
		Predicate phoneNumber = cb.like(student.get("phoneNumber"),"%"+studentFilter.getPhoneNumber()+ "%");
		predicates.add(phoneNumber);

	}
	return cb.and(predicates.toArray(Predicate[]::new));
	}

}
