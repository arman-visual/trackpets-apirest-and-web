package com.trackpets.springboot.web.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/animal")
public class AnimalController {

	@GetMapping(value = "/addAnimal")
	public String addAnimal(ModelMap modelmap) {
		modelmap.addAttribute("titulo", "Alta animal");
		return "formPet";
	}
}
