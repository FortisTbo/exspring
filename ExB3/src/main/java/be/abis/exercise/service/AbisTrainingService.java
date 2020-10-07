package be.abis.exercise.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.abis.exercise.exception.EnrollException;
import be.abis.exercise.model.Course;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.CourseRepository;
import be.abis.exercise.repository.PersonRepository;

@Service
public class AbisTrainingService implements TrainingService {
	
	@Autowired
	PersonRepository personRepo;
	
	@Autowired
	CourseRepository courseRepo;

	public CourseRepository getCourseRepo() {
		return courseRepo;
	}

	public void setCourseRepo(CourseRepository courseRepo) {
		this.courseRepo = courseRepo;
	}

	@Override
	public Person findPerson(int id) {
		return personRepo.findPerson(id);
	}

	@Override
	public void addPerson(Person p) throws IOException {
		personRepo.addPerson(p);
	}

	@Override
	public void deletePerson(int id) {
		personRepo.deletePerson(id);

	}

	@Override
	public void changePassword(Person p, String newPswd) throws IOException {
		personRepo.changePassword(p, newPswd);

	}

	@Override
	public List<Course> showFollowedCourses(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enrollForSession(Person person, Course course, LocalDate date) throws EnrollException {
		// TODO Auto-generated method stub

	}
	
	@Override
	public Person findByEmailPerson(String emailAddress) {
		ArrayList<Person> allPersons = personRepo.getAllPersons();
		
		for (Person p : allPersons) {
			if (p.getEmailAddress().equals(emailAddress)) {
				return p;
			}
		}
		return null;
	}
	
	@Override
	public List<Person> allPersons() {
		ArrayList<Person> allPersons = personRepo.getAllPersons();
		
		return allPersons;
	}
}
