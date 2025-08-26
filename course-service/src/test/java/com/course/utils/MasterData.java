package com.course.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.course.entity.Course;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class MasterData {

	public static Course getCourse() {
		Course course = new Course();
		course.setId(1L);
		course.setName("Mathematics 101");
		course.setCode("MATH101");
		course.setStudentId(1001L);
		return course;
	}

	public static List<Course> getCourseList() {
		List<Course> list = new ArrayList<>();
		list.add(getCourse()); // Adding a mock course
		return list;
	}

	public static Page<Course> getCoursePage(int page, int size) {
		List<Course> courseList = getCourseList();
		PageRequest pageRequest = PageRequest.of(page, size);
		return new PageImpl<>(courseList, pageRequest, courseList.size());
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
