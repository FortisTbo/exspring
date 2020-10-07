package be.abis.exercise.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import be.abis.exercise.model.Person;
import be.abis.exercise.service.TrainingService;

@Controller
public class AppController {
	

	@Autowired
	TrainingService trainingService;
	Person personSave;
	
	
	@GetMapping("/")
	public String showLoginPage(Model model) {
		Person p = new Person ();
		
		model.addAttribute("person", p);
		return "login";
	}
	
	@PostMapping("/WelcomForm")
	public String submitLoginForm(Model model, Person p) {
		Person findP = trainingService.findByEmailPerson(p.getEmailAddress());
		
		if  (findP != null) {
			personSave = findP;
		}

		model.addAttribute("name", personSave.getFirstName());
		return "welcom";
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
	public String submitChangePassword(Model model, Person p) throws IOException {
		trainingService.changePassword(personSave, p.getPassword());
		
		model.addAttribute("name", personSave.getFirstName());
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
	public String submitAddPerson(Model model, Person p) throws IOException {
		List<Person> allPersons = trainingService.allPersons();
		p.setPersonId(allPersons.size()+1);
		trainingService.addPerson(p);
		
		return "personAdministration";
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
	
	
}
