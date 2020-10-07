package be.abis.exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import be.abis.exercise.model.Person;
import be.abis.exercise.service.TrainingService;

@Controller
public class AppController {
	

	@Autowired
	TrainingService trainingService;
	String nameSave;
	
	
	@GetMapping("/")
	public String showLoginPage(Model model) {
		Person p = new Person ();
		
		model.addAttribute("person", p);
		return "login";
	}
	
	@PostMapping("/WelcomForm")
	public String submitLoginForm(Model model, Person p) {
		Person findP = trainingService.findByEmailPerson(p.getEmailAddress());
		
		String name = nameSave;
		
		if  (findP != null) {
			name = findP.getFirstName();
			nameSave = name;
		} 

		model.addAttribute("name", name);
		return "welcom";
	}
	
	@GetMapping("/searchForCourses")
	public String showSearchForCoursesPage(Model model) {

		return "searchForCourses";
	}

}
