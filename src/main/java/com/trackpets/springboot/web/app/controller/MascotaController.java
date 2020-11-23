package com.trackpets.springboot.web.app.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

	private static final Logger LOGGER = LoggerFactory.getLogger(MascotaController.class);
	
	@Autowired
	private IMascotaService mascotaService;

	@Autowired
	private IProtectoraService protectoraService;

	@GetMapping("/home")
	public String home(Model model) {
		List<Mascota> mascotas = new ArrayList<Mascota>();
		model.addAttribute("titulo", "Buscar mascotas");
		model.addAttribute("mascotas", mascotas);
		return "buscar";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Model model,
			@Param("palabraClave")String palabraClave, @Param("filtro")String filtro) {		
			List<Mascota> mascotas = new ArrayList<Mascota>();
			model.addAttribute("titulo", "Buscar mascotas");
			if(filtro.equals(null)||filtro.equals("")) {
				LOGGER.info("--filtro: ".concat(filtro) + " --".concat("findAll()"));
				model.addAttribute("mascotas", mascotaService.findAll());
			}else if (filtro.equals("Nombre")) {
				mascotas = mascotaService.mascotasByNombre(palabraClave);
				LOGGER.info("--filtro: ".concat(filtro) + " --".concat("mascotasByNombre(".concat(palabraClave).concat(")")));
				model.addAttribute("mascotas", mascotas);
			} else if (filtro.equals("Genero")) {
				mascotas = mascotaService.mascotasByGenero(palabraClave);
				LOGGER.info("--filtro: ".concat(filtro) + " --".concat("mascotasByGenero(".concat(palabraClave).concat(")")));
				model.addAttribute("mascotas", mascotas);
			} else if (filtro.equals("Raza")) {
				mascotas = mascotaService.mascotasByRaza(palabraClave);
				LOGGER.info("--filtro: ".concat(filtro) + " --".concat("mascotasByRaza(".concat(palabraClave).concat(")")));
				model.addAttribute("mascotas", mascotas);
			} else if (filtro.equals("Tamaño")) {
				mascotas = mascotaService.mascotasByTamaño(palabraClave);
				LOGGER.info("--filtro: ".concat(filtro) + " --".concat("mascotasByTamaño(".concat(palabraClave).concat(")")));
				model.addAttribute("mascotas", mascotas);
			} else if (filtro.equals("Edad")) {
				//TODO Falta por desarrollar
				mascotas= mascotaService.mascotasByEdad(palabraClave);
				LOGGER.info("--filtro: ".concat(filtro) + " --".concat("mascotasByEdad(".concat(palabraClave).concat(")")));
				model.addAttribute("mascotas", mascotas);
			}
			return "buscar";
	}

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
			modelmap.addAttribute("titulo", "Registro de Mascota");
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
	
	@ModelAttribute("filtros")
	public List<String> filtros(){
		return Arrays.asList("Nombre","Raza", "Edad", "Tamaño", "Genero");
	}
}
