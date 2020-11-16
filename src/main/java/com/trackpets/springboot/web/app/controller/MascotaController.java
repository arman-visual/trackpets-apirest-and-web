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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.trackpets.springboot.web.app.models.entity.Mascota;
import com.trackpets.springboot.web.app.service.IMascotaService;
import com.trackpets.springboot.web.app.service.IProtectoraService;

@Controller
@RequestMapping("/mascota")
@SessionAttributes("mascota")
public class MascotaController {

	@Autowired
	private IMascotaService mascotaService;

	@Autowired
	private IProtectoraService protectoraService;

	@GetMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("titulo", "Lista de mascotas almacenadas");
		model.addAttribute("mascotas", mascotaService.findAll());
		return "listarPet";
	}

	@GetMapping(value = "/addMascota")
	public String addMascota(ModelMap modelmap) {
		Mascota mascota = new Mascota();
		modelmap.put("mascota", mascota);
		modelmap.put("titulo", "Alta mascota");
		modelmap.put("protectoras", protectoraService.findAll());
		modelmap.put("textButton", "Dar de alta");
		return "formPet";
	}

	@PostMapping(value = "/guardar")
	public String guardarMascota(@Validated Mascota mascota, BindingResult result, ModelMap modelmap,
			SessionStatus status) {

		if (result.hasErrors()) {
			modelmap.addAttribute("titulo", "Registro de Empleado");
			return "formPet";
		}
		mascotaService.save(mascota);
		status.setComplete();
		return "redirect:/mascota/listar";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {

		Optional<Mascota> mascota = null;

		if (id > 0) {
			mascota = mascotaService.findById(id);
		} else {
			return "redirect:home";
		}
		model.put("mascota", mascota);
		model.put("titulo", "Editar mascota");
		model.put("protectoras", protectoraService.findAll());
		model.put("textButton", "Actualizar");
		return "formPet";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {

		if (id > 0) {
			mascotaService.deleteById(id);
		}
		return "redirect:/mascota/listar";
	}
}
