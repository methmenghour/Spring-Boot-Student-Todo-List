package com.menghour.student;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentTodoListApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentTodoListApplication.class, args);	
	}

}
