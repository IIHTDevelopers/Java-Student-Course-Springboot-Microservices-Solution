package com.student.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.entity.Student;
import com.student.exception.NotFoundException;
import com.student.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/students")
public class StudentController {

	private final StudentService service;

	public StudentController(StudentService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<Student> create(@Valid @RequestBody Student s) {
		return ResponseEntity.ok(service.createStudent(s));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Student> get(@PathVariable Long id) {
		return service.getStudent(id).map(ResponseEntity::ok)
				.orElseThrow(() -> new NotFoundException("Student with id " + id + " not found"));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Student> update(@PathVariable Long id, @Valid @RequestBody Student s) {
		return ResponseEntity.ok(service.updateStudent(id, s));
	}
}
