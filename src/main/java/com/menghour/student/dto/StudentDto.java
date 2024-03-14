package com.menghour.student.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
	private Long id;
	
	 @NotNull(message = "Student Name is not Null")
	 @NotBlank(message = "Student Name is not Blank")
	private String name;
	 @Pattern(regexp="^\\+?[\\d\\s?]+",message = "invalid mobile number entered ")
	 @Length(min = 1, max = 20)
	private String phoneNumber;
	 
	 @Min(18)
	 @Max(60)
	private Integer age;
}
