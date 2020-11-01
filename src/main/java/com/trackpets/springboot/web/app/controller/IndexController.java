package com.trackpets.springboot.web.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	/**
	 * Nos lleva al index de nuestra web
	 * 
	 * @return vista html indicada
	 */
	@GetMapping(value = {"/","/index","/home"})
	public String index(ModelMap model) {
		model.addAttribute("titulo", "Track Pets Web Oficial");
		return "index";
	}
}
