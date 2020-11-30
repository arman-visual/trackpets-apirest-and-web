package com.trackpets.springboot.web.app.controller;

import java.security.Principal;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
	//@Secured("ROLE_USER")
	/*
	 * @GetMapping("/login") public String login(Model model, Principal principal,
	 * RedirectAttributes flash) {
	 * 
	 * if (principal != null) { flash.addFlashAttribute("info",
	 * "Ya se ha iniciado sesión"); return "redirect:/mascota/home"; } return
	 * "login"; }
	 */
}
