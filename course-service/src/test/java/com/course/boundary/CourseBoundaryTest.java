package com.course.boundary;

import static com.course.utils.TestUtils.boundaryTestFile;
import static com.course.utils.TestUtils.currentTest;
import static com.course.utils.TestUtils.testReport;
import static com.course.utils.TestUtils.yakshaAssert;

import java.io.IOException;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.course.entity.Course;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class CourseBoundaryTest {

	private static Validator validator;

	@BeforeAll
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testCourseNameNotNull() throws IOException {
		Course course = new Course();
		course.setName(null); // Should be not null
		course.setCode("CS101");
		course.setStudentId(1001L);

		Set<ConstraintViolation<Course>> violations = validator.validate(course);
		yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
	}

	@Test
	public void testCourseNameMaxLength() throws IOException {
		Course course = new Course();
		course.setName("a".repeat(256)); // Too long, assuming max = 255
		course.setCode("CS101");
		course.setStudentId(1001L);

		Set<ConstraintViolation<Course>> violations = validator.validate(course);
		yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
	}
}
