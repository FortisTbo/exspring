package be.abis.exercise.it;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import static org.hamcrest.Matchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import be.abis.exercise.service.CourseService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCoursService {
	
	@Autowired
	CourseService courseService;
	

	
	@Test
	public void checkCourseTitleWhenCoursId7900 () {
		assertEquals ("Workshop SQL",courseService.findCourse(7900).getShortTitle());
	}
	
	@Test
	public void checkPriceOfCourse7900IsHigherThan400() {
		assertThat(courseService.findCourse(7900).getPricePerDay(), greaterThan(400.0));
	}

	
}
