package be.abis.exercise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.abis.exercise.model.Course;
import be.abis.exercise.repository.CourseRepository;

@Service
public class AbisCourseService implements CourseService {
	
	@Autowired
	CourseRepository courseRep;
	
	@Override
	public List<Course> findAllCourses() {
		return courseRep.findAllCourses();
	}

	@Override
	public Course findCourse(int id) {
		return courseRep.findCourse(id);
	}

	@Override
	public Course findCourse(String shortTitle) {
		return courseRep.findCourse(shortTitle);
	}



}
