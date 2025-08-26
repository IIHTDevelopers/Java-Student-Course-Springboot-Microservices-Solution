package com.student.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.student.entity.Student;

public class MasterData {

	public static Student getStudent() {
		Student student = new Student();
		student.setId(1L);
		student.setName("John Doe");
		student.setEmail("john.doe@example.com");
		student.setProgram("Computer Science");
		return student;
	}

	public static List<Student> getStudentList() {
		List<Student> list = new ArrayList<>();

		Student student = new Student();
		student.setId(1L);
		student.setName("John Doe");
		student.setEmail("john.doe@example.com");
		student.setProgram("Computer Science");

		list.add(student);
		return list;
	}

	public static Page<Student> getStudentPage(int page, int size) {
		List<Student> studentList = getStudentList();
		PageRequest pageRequest = PageRequest.of(page, size);
		return new PageImpl<>(studentList, pageRequest, studentList.size());
	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			final String jsonContent = mapper.writeValueAsString(obj);

			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String randomStringWithSize(int size) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < size; i++) {
			s.append("A");
		}
		return s.toString();
	}
}
