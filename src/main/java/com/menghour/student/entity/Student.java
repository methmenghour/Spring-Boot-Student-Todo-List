package com.menghour.student.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tblStudent")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	
	private Long id;
	
	@Column(name = "name")
	private String name;	
	 
	@Column(name = "phonenumber")
	private String phoneNumber;
	
	@Column(name = "age")
	private Integer age;	
}
