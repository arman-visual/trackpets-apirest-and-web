package com.trackpets.springboot.web.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NoticiasController {
	
	
@GetMapping(value = "/noticias")
public String noticias() {
	return "noticias";
	
}
}
