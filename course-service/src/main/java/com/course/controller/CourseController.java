package com.course.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.dto.CourseResponse;
import com.course.entity.Course;
import com.course.service.CourseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
	
	private final CourseService service;

	public CourseController(CourseService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<Course> create(@Valid @RequestBody Course c) {
		return ResponseEntity.ok(service.save(c));
	}

	@GetMapping("/{id}")
	public ResponseEntity<CourseResponse> get(@PathVariable Long id) {
		return ResponseEntity.ok(service.getCourseWithStudent(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Course> update(@PathVariable Long id, @Valid @RequestBody Course c) {
		return ResponseEntity.ok(service.update(id, c));
	}
}
