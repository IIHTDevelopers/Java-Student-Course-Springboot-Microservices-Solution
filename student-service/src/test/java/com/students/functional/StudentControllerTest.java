package com.students.functional;

import static com.student.utils.MasterData.asJsonString;
import static com.student.utils.MasterData.getStudent;
import static com.student.utils.TestUtils.businessTestFile;
import static com.student.utils.TestUtils.currentTest;
import static com.student.utils.TestUtils.testReport;
import static com.student.utils.TestUtils.yakshaAssert;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.student.StudentServiceApplication;
import com.student.controller.StudentController;
import com.student.entity.Student;
import com.student.service.StudentService;

@WebMvcTest(controllers = StudentController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = StudentServiceApplication.class)
public class StudentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StudentService studentService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testCreateStudent() throws Exception {
		Student student = getStudent();
		student.setId(1L);

		when(studentService.createStudent(any(Student.class))).thenReturn(student);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/students").content(asJsonString(student))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(asJsonString(student)) ? "true" : "false"),
				businessTestFile);
	}

	@Test
	public void testGetStudent() throws Exception {
		Student student = getStudent();
		student.setId(1L);

		when(studentService.getStudent(1L)).thenReturn(java.util.Optional.of(student));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/students/1")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(asJsonString(student)) ? "true" : "false"),
				businessTestFile);
	}

	@Test
    public void testGetStudentNotFound() throws Exception {
        when(studentService.getStudent(99L)).thenReturn(java.util.Optional.empty());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/students/99")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        yakshaAssert(currentTest(),
                (result.getResponse().getStatus() == 404 ? "true" : "false"),
                businessTestFile);
    }

	@Test
	public void testUpdateStudent() throws Exception {
		Student updatedStudent = getStudent();
		updatedStudent.setId(1L);
		updatedStudent.setName("Updated Name");

		when(studentService.updateStudent(eq(1L), any(Student.class))).thenReturn(updatedStudent);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/students/1")
				.content(asJsonString(updatedStudent)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(asJsonString(updatedStudent)) ? "true"
						: "false"),
				businessTestFile);
	}
}
