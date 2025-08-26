package com.course.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Course name is required")
	@Size(min = 3, max = 255, message = "Course name must be between 3 and 255 characters")
	private String name;

	@NotBlank(message = "Course code is required")
	@Size(min = 2, max = 20, message = "Course code must be between 2 and 20 characters")
	@Column(unique = true, nullable = false)
	private String code;

	@NotNull(message = "Student ID must not be null")
	private Long studentId;

	public Course() {
	}

	public Course(Long id, String name, String code, Long studentId) {
		this.id = id;
		this.name = name;
		this.code = code;
		this.studentId = studentId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
}
