package com.course.functional;

import static com.course.utils.MasterData.asJsonString;
import static com.course.utils.TestUtils.businessTestFile;
import static com.course.utils.TestUtils.currentTest;
import static com.course.utils.TestUtils.testReport;
import static com.course.utils.TestUtils.yakshaAssert;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.course.controller.CourseController;
import com.course.dto.CourseResponse;
import com.course.dto.StudentDTO;
import com.course.entity.Course;
import com.course.service.CourseService;

@WebMvcTest(CourseController.class)
@AutoConfigureMockMvc
public class CourseControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CourseService courseService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testCreateCourse() throws Exception {
		Course course = new Course();
		course.setId(1L);
		course.setName("Mathematics 101");
		course.setCode("MATH101");
		course.setStudentId(1001L);

		when(courseService.save(any(Course.class))).thenReturn(course);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/courses").content(asJsonString(course))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(asJsonString(course)) ? "true" : "false"),
				businessTestFile);
	}

	@Test
	public void testGetCourseWithStudent() throws Exception {
		Long courseId = 1L;

		// Mock Course data
		Course course = new Course();
		course.setId(courseId);
		course.setName("Mathematics 101");
		course.setCode("MATH101");
		course.setStudentId(1001L);

		// Mock StudentDTO (fetched using studentId)
		StudentDTO student = new StudentDTO();
		student.setId(1001L);
		student.setName("John Doe"); // Assuming you fetch student details here.

		// Mock CourseResponse
		CourseResponse courseResponse = new CourseResponse();
		courseResponse.setCourse(course);
		courseResponse.setStudent(student); // Attach the mocked student data

		// Mock the service to return the CourseResponse
		when(courseService.getCourseWithStudent(eq(courseId))).thenReturn(courseResponse);

		// Perform GET request
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/courses/" + courseId)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(asJsonString(courseResponse)) ? "true"
						: "false"),
				businessTestFile);
	}

	@Test
	public void testUpdateCourse() throws Exception {
		Course updatedCourse = new Course();
		updatedCourse.setId(1L);
		updatedCourse.setName("Advanced Mathematics");
		updatedCourse.setCode("MATH201");
		updatedCourse.setStudentId(1002L);

		when(courseService.update(eq(1L), any(Course.class))).thenReturn(updatedCourse);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/courses/1")
				.content(asJsonString(updatedCourse)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(asJsonString(updatedCourse)) ? "true"
						: "false"),
				businessTestFile);
	}
}
