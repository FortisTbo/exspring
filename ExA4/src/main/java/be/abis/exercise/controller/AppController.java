package be.abis.exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import be.abis.exercise.model.Course;
import be.abis.exercise.service.TrainingService;

@Controller
public class AppController {
	

	@Autowired
	TrainingService trainingService;
	
	
	@GetMapping("/")
	public String showTitle(Model model) {
		Course c = trainingService.getCourseRepo().findCourse(7850);

		model.addAttribute("course", c);
		return "course";
	}

}
