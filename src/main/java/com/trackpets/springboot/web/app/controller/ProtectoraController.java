package com.trackpets.springboot.web.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.trackpets.springboot.web.app.models.entity.Protectora;
import com.trackpets.springboot.web.app.service.IProtectoraService;

@Controller
@RequestMapping("/protectora")
@SessionAttributes("protectora")
public class ProtectoraController {

	@Autowired
	private IProtectoraService protectoraService;
	
	@GetMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("titulo", "Lista de protectoras");
		model.addAttribute("protectoras", protectoraService.findAll());
		return "listProtectoras";
	}

	@GetMapping(value = "/addProtectora")
	public String addProtectora(ModelMap modelmap) {
		Protectora protectora = new Protectora();
		modelmap.addAttribute("protectora", protectora);
		modelmap.addAttribute("titulo", "Alta protectora");
		modelmap.addAttribute("textButton", "Dar alta protectora");
		return "formProtectora";
	}

	@PostMapping(value = "/guardar")
	public String guardarProtectora(@Validated Protectora protectora, BindingResult result, ModelMap modelmap,
			SessionStatus status) {
		if (result.hasErrors()) {
			modelmap.addAttribute("titulo", "Alta de protectora");
			return "formProtectora";
		}
		protectoraService.save(protectora);
		status.setComplete();
		return "redirect:/protectora/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {

		Protectora protectora = null;

		if (id > 0) {
			protectora = protectoraService.findById(id);
		} else {
			return "redirect:home";
		}
		model.put("protectora", protectora);
		model.put("titulo", "Editar protectora");
		model.put("textButton", "Actualizar");
		return "formProtectora";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {

		if (id > 0) {
			protectoraService.deleteById(id);
		}
		return "redirect:/protectora/listar";

	}
}
