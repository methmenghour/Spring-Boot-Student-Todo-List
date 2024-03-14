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
		
		Connection connection=null;
		try {
			String url="jdbc:sqlserver://localhost:1433;encrypt=true;trustServerCertificate=true;databaseName=Student";
			String user="sa";
			String pass="Menghour007";
			connection=DriverManager.getConnection(url,user,pass);
			if(connection!=null) {
				DatabaseMetaData metadata=(DatabaseMetaData) connection.getMetaData();
				System.out.println("Product Name :"+metadata.getDatabaseProductName());
				System.out.println("Product Version :"+metadata.getDatabaseProductVersion());

			}
		}catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			try {
				if(connection==null&connection.isClosed()) {
					connection.close();
				}
			}catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		
	}

}
