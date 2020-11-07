package com.trackpets.springboot.web.app.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.avisual.web.app.models.entity.Empleado;
import com.trackpets.springboot.web.app.models.entity.Mascota;
import com.trackpets.springboot.web.app.service.IMascotaService;

@Controller
@RequestMapping("/animal")
public class MascotaController {
	
	@Autowired
	private IMascotaService mascotaService;

	@GetMapping("/lista")
	public String listar(Model model) {
		model.addAttribute("titulo", "Lista de empleados");
		model.addAttribute("mascotas", mascotaService.findAll());
		return "listaMascotas";
	}
	
	@GetMapping(value = "/addAnimal")
	public String addMascota(ModelMap modelmap) {
		Mascota mascota = new Mascota();
		modelmap.put("mascota", mascota);
		modelmap.put("titulo", "Alta mascota");
		modelmap.put("textButton", "Dar de alta");
		return "formPet";
	}
	
	@GetMapping(value = "/guardar")
	public String guardarMascota(@Validated Mascota mascota, BindingResult result, ModelMap modelmap) {
		if (result.hasErrors()) {
			modelmap.addAttribute("titulo", "Registro de Empleado");
			return "formUser";
		}
		mascotaService.save(mascota);
//		Redireccion al path /home
		return "redirect:home";
	}
	
	@GetMapping("/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {

		Optional<Mascota> mascota = null;

		if (id > 0) {
			mascota = mascotaService.findById(id);
		} else {
			return "redirect:home";
		}
		model.put("mascota", mascota);
		model.put("titulo", "Editar mascota");
		model.put("textButton", "actualizar");
		return "formPet";
	}

	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {

		if (id > 0) {
			mascotaService.deleteById(id);
		}
		return "redirect:/animal/listaMascotas";

	}
}
