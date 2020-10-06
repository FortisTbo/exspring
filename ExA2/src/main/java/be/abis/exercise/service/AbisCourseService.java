package be.abis.exercise.service;

import java.util.List;

import org.springframework.stereotype.Service;

import be.abis.exercise.model.Course;
import be.abis.exercise.repository.MemoryCourseRepository;

@Service
public class AbisCourseService implements CourseService {
	
	MemoryCourseRepository memCouRep = new MemoryCourseRepository();
	
	@Override
	public List<Course> findAllCourses() {
		return memCouRep.findAllCourses();
	}

	@Override
	public Course findCourse(int id) {
		return memCouRep.findCourse(id);
	}

	@Override
	public Course findCourse(String shortTitle) {
		return memCouRep.findCourse(shortTitle);
	}



}
