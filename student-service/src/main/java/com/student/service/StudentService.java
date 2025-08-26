package com.student.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.student.entity.Student;
import com.student.exception.NotFoundException;
import com.student.repo.StudentRepository;

@Service
public class StudentService {

	private final StudentRepository repo;

	public StudentService(StudentRepository repo) {
		this.repo = repo;
	}

	public Student createStudent(Student s) {
		return repo.save(s);
	}

	public Optional<Student> getStudent(Long id) {
		return repo.findById(id);
	}

	public Student updateStudent(Long id, Student updated) {

		Student existing = repo.findById(id)
				.orElseThrow(() -> new NotFoundException("Student with id " + id + " not found"));
		existing.setName(updated.getName());
		existing.setEmail(updated.getEmail());
		existing.setProgram(updated.getProgram());
		return repo.save(existing);
	}
}
