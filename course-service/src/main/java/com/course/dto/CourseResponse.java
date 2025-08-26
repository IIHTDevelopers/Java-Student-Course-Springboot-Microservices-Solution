package com.course.dto;

import com.course.entity.Course;

public class CourseResponse {
	
	private Course course;
	private StudentDTO student;

	public CourseResponse() {
	}

	public CourseResponse(Course c, StudentDTO s) {
		this.course = c;
		this.student = s;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course c) {
		this.course = c;
	}

	public StudentDTO getStudent() {
		return student;
	}

	public void setStudent(StudentDTO s) {
		this.student = s;
	}
}
