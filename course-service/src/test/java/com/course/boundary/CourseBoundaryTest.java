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
	public void testCourseNameNotBlank() throws IOException {
		Course course = new Course();
		course.setName(" "); // Blank string
		course.setCode("CS101");
		course.setStudentId(1001L);

		Set<ConstraintViolation<Course>> violations = validator.validate(course);
		yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
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
	public void testCourseNameMinLength() throws IOException {
		Course course = new Course();
		course.setName("ab"); // Too short, assuming min = 3
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

	@Test
	public void testCourseCodeNotBlank() throws IOException {
		Course course = new Course();
		course.setName("Introduction to CS");
		course.setCode(" "); // Blank string
		course.setStudentId(1001L);

		Set<ConstraintViolation<Course>> violations = validator.validate(course);
		yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
	}

	@Test
	public void testCourseCodeNotNull() throws IOException {
		Course course = new Course();
		course.setName("Introduction to CS");
		course.setCode(null); // Should not be null
		course.setStudentId(1001L);

		Set<ConstraintViolation<Course>> violations = validator.validate(course);
		yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
	}

	@Test
	public void testCourseCodeMinLength() throws IOException {
		Course course = new Course();
		course.setName("Introduction to CS");
		course.setCode("A"); // Too short, assuming min = 2
		course.setStudentId(1001L);

		Set<ConstraintViolation<Course>> violations = validator.validate(course);
		yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
	}

	@Test
	public void testCourseCodeMaxLength() throws IOException {
		Course course = new Course();
		course.setName("Introduction to CS");
		course.setCode("A".repeat(21)); // Too long, assuming max = 20
		course.setStudentId(1001L);

		Set<ConstraintViolation<Course>> violations = validator.validate(course);
		yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
	}

	@Test
	public void testStudentIdNotNull() throws IOException {
		Course course = new Course();
		course.setName("Introduction to CS");
		course.setCode("CS101");
		course.setStudentId(null); // Should not be null

		Set<ConstraintViolation<Course>> violations = validator.validate(course);
		yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
	}
}
