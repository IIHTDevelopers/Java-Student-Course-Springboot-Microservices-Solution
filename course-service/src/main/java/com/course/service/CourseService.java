package com.course.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.course.dto.CourseResponse;
import com.course.dto.StudentDTO;
import com.course.entity.Course;
import com.course.exception.NotFoundException;
import com.course.repo.CourseRepository;

@Service
public class CourseService {
	
	private final CourseRepository repo;
	private final RestTemplate restTemplate;

	@Value("${student.service.url:http://student-service}")
	private String studentServiceBase;

	public CourseService(CourseRepository repo, RestTemplate restTemplate) {
		this.repo = repo;
		this.restTemplate = restTemplate;
	}

	public Course save(Course c) {
		return repo.save(c);
	}

	public Optional<Course> get(Long id) {
		return repo.findById(id);
	}

	public Course update(Long id, Course updated) {
		if (!repo.existsById(id))
			throw new NotFoundException("Course with id " + id + " not found");
		updated.setId(id);
		return repo.save(updated);
	}

	public CourseResponse getCourseWithStudent(Long id) {
		Course c = repo.findById(id).orElseThrow(() -> new NotFoundException("Course with id " + id + " not found"));
		String url = studentServiceBase + "/api/students/" + c.getStudentId();
		try {
			ResponseEntity<StudentDTO> resp = restTemplate.getForEntity(url, StudentDTO.class);
			StudentDTO s = resp.getBody();
			if (s == null)
				throw new NotFoundException("Student with id " + c.getStudentId() + " not found");
			return new CourseResponse(c, s);
		} catch (HttpClientErrorException.NotFound ex) {
			throw new NotFoundException("Student with id " + c.getStudentId() + " not found");
		}
	}
}
