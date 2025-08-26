package com.students.boundary;

import static com.student.utils.TestUtils.boundaryTestFile;
import static com.student.utils.TestUtils.currentTest;
import static com.student.utils.TestUtils.testReport;
import static com.student.utils.TestUtils.yakshaAssert;

import java.io.IOException;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import com.student.entity.Student;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class StudentBoundaryTest {

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
	public void testStudentNameNotNull() throws IOException {
		Student student = new Student();
		student.setName(null); // Null name
		student.setEmail("john.doe@example.com");
		student.setProgram("Computer Science");
		Set<ConstraintViolation<Student>> violations = validator.validate(student);
		yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
	}

	@Test
	public void testStudentNameMaxLength() throws IOException {
		Student student = new Student();
		student.setName("A".repeat(256)); // too long, assuming max = 255
		student.setEmail("john.doe@example.com");
		student.setProgram("Computer Science");
		Set<ConstraintViolation<Student>> violations = validator.validate(student);
		yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
	}

	@Test
	public void testStudentEmailNotNull() throws IOException {
		Student student = new Student();
		student.setName("John Doe");
		student.setEmail(null); // Null email
		student.setProgram("Computer Science");
		Set<ConstraintViolation<Student>> violations = validator.validate(student);
		yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
	}

	@Test
	public void testStudentEmailValidFormat() throws IOException {
		Student student = new Student();
		student.setName("John Doe");
		student.setEmail("john.doe.com"); // Invalid email format
		student.setProgram("Computer Science");
		Set<ConstraintViolation<Student>> violations = validator.validate(student);
		yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
	}

	@Test
	public void testStudentProgramNotBlank() throws IOException {
		Student student = new Student();
		student.setName("John Doe");
		student.setEmail("john.doe@example.com");
		student.setProgram("  "); // Blank program
		Set<ConstraintViolation<Student>> violations = validator.validate(student);
		yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
	}

	@Test
	public void testStudentProgramMinLength() throws IOException {
		Student student = new Student();
		student.setName("John Doe");
		student.setEmail("john.doe@example.com");
		student.setProgram("C"); // too short, assuming min = 2
		Set<ConstraintViolation<Student>> violations = validator.validate(student);
		yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
	}

}
