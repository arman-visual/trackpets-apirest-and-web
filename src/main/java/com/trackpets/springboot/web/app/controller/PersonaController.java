package com.trackpets.springboot.web.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/persona")
public class PersonaController {

	@GetMapping(value = "/addPersona")
	public String addPersona(ModelMap modelmap) {
		modelmap.addAttribute("titulo", "Alta persona");
		return "formPersona";
	}

}
