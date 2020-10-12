package be.abis.exercise.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import be.abis.exercise.model.Course;
import be.abis.exercise.model.Person;
import be.abis.exercise.service.CourseService;
import be.abis.exercise.service.TrainingService;

@Controller
public class AppController {
	

	@Autowired
	TrainingService trainingService;
	
	@Autowired
	CourseService courseService;
	
	Person personSave;
	
	
	@GetMapping("/")
	public String showLoginPage(Model model) {
		Person p = new Person ();
		
		model.addAttribute("person", p);
		return "login";
	}
	
	@PostMapping("/WelcomeForm")
	public String submitLoginForm(Model model, Person p, BindingResult bindingResult) {
		Person findP = trainingService.findByEmailPerson(p.getEmailAddress());
		
		if  (findP != null) {
			personSave = findP;
		} else {
			bindingResult.reject("incorrectLogin", "Incorrect login");
			return "login";
		}

		model.addAttribute("name", personSave.getFirstName());
		return "welcome";
	}
	
	@GetMapping("/searchForCourses")
	public String showSearchForCoursesPage(Model model) {

		return "searchForCourses";
	}
	
	@GetMapping("/personAdministration")
	public String showPersonAdministrationPage(Model model) {

		return "personAdministration";
	}
	
	@GetMapping("/changePassword")
	public String showChangePasswordPage(Model model) {
		Person p = new Person ();
		
		model.addAttribute("person", p);
		return "changePassword";
	}
		
	@PostMapping("/changePassword")
	public String submitChangePassword(Model model, Person p)  {
		try {
			trainingService.changePassword(personSave, p.getPassword());
		} catch (IOException e) {
			return "error";
		}
		
		return "changePasswordConfirmation";
	}
	
	@GetMapping("/searchPersons")
	public String showSearchPersonsPage(Model model) {
		List<Person> allPersons = trainingService.allPersons();
		
		model.addAttribute("persons", allPersons);
		return "searchPersons";
	}
	
	@GetMapping("/addPerson")
	public String submitAddPerson(Model model) {
		Person p = new Person ();
		
		model.addAttribute("person", p);
		return "addPerson";
	}
	
	@PostMapping("/addPerson")
	public String submitAddPerson(Model model, @Valid Person p, BindingResult bindingResult)  {
		if (bindingResult.hasErrors()) {
            return "addPerson";
        }
		
		p.setPersonId(trainingService.findMaxId()+1);
		try {
			trainingService.addPerson(p);
		} catch (IOException e) {
			return "error";
		}
		
		model.addAttribute("person", p);
		return "displayPerson";
	}
	
	@GetMapping("/displayPerson")
	public String showPerson(Model model, Person p) {
		model.addAttribute("person", p);
		return "displayPerson";
	}
	
	@GetMapping("/removePerson")
	public String showRemovePerson(@RequestParam(required=false) String id, Model model) {
		System.out.println("ID : " + id);
		if  (id != null) {
			trainingService.deletePerson(Integer.parseInt(id));
		}
		
		List<Person> allPersons = trainingService.allPersons();
		
		model.addAttribute("persons", allPersons);
		return "removePerson";
	}
	
	
	
	@GetMapping("/searchPersonById")
	public String showSearchPersonByIdPage(Model model) {
		Person p = new Person ();
		
		model.addAttribute("person", p);
		return "searchPersonById";
	}
		
	@PostMapping("/searchPersonById")
	public String submitSearchPersonById(Model model, Person p) {
	
		Person pFind = trainingService.findPerson(p.getPersonId());
		if  (pFind == null) {
			model.addAttribute("person", new Person ());
		} else {
			model.addAttribute("person", pFind);
		}
		return "searchPersonById";
		
	}
	
	@GetMapping("/searchCourses")
	public String showSearchCoursesPage(Model model) {
		List<Course> allCourses = courseService.findAllCourses();
		
		model.addAttribute("courses", allCourses);
		return "searchCourses";
	}
	
	@GetMapping("/searchCourseById")
	public String showSearchCourseByIdPage(Model model) {
		Course c = new Course ();
		
		model.addAttribute("course", c);
		return "searchCourseById";
	}
		
	@PostMapping("/searchCourseById")
	public String submitSearchCourseById(Model model, Course c) {
	
		Course cFind = null;
		try {
			cFind = courseService.findCourse(Integer.parseInt(c.getCourseId()));
		} catch (Exception e) {
			// TODO: handle exception
		}
		if  (cFind == null) {
			model.addAttribute("course", new Course ());
		} else {
			model.addAttribute("course", cFind);
		}
		return "searchCourseById";
		
	}
	
	@GetMapping("/searchCourseByShortTitle")
	public String showSearchCourseByShortTitlePage(Model model) {
		Course c = new Course ();
		
		model.addAttribute("course", c);
		return "searchCourseByShortTitle";
	}
		
	@PostMapping("/searchCourseByShortTitle")
	public String submitSearchCourseByShortTitle(Model model, Course c) {
	
		Course cFind = courseService.findCourse(c.getShortTitle());
		if  (cFind == null) {
			model.addAttribute("course", new Course ());
		} else {
			model.addAttribute("course", cFind);
		}
		return "searchCourseByShortTitle";
		
	}
	
}
