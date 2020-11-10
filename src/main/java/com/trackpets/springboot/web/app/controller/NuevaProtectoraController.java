package com.trackpets.springboot.web.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller



public class NuevaProtectoraController {
	@GetMapping("/nuevaProtectora")
	public String form(Model model) {
		model.addAttribute("titulo", "Nueva protectora trackpets");
		return "nuevaProtectora";
		
	}
	
	
	
	@PostMapping("/nuevaProtectora")
	public String procesarFormulario(Model model, @RequestParam String email, @RequestParam String password, @RequestParam String nombre,@RequestParam String telefono,@RequestParam String direccion) {
		
		
		model.addAttribute("titulo", "Resultado registro usuarios");
		model.addAttribute("email", email);
		model.addAttribute("nombre", nombre);
		model.addAttribute("telefono", telefono);
		model.addAttribute("direccion",direccion);
		model.addAttribute("password",password);
		
		return "datosNuevaProtectora";
	}
	
	
	
}
	
	

