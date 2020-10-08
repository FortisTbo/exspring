package be.abis.exercise.it;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import be.abis.exercise.service.CourseService;

@WebMvcTest
@RunWith(SpringRunner.class)
public class TestCourseController {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	private CourseService courseService;
	
	@Test
	public void testShowCourseHtml() throws Exception {
		
		mockMvc.perform(get("/"))
		.andExpect(status().is2xxSuccessful());
	}
}
