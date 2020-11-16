package com.trackpets.springboot.web.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.trackpets.springboot.web.app.models.entity.Mascota;
import com.trackpets.springboot.web.app.service.IMascotaService;

@RestController
@RequestMapping("/api/mascotas")
public class MascotaRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MascotaController.class);

	@Autowired
	private IMascotaService mascotaService;

	@GetMapping("/buscar/todos")
	@ResponseStatus(HttpStatus.OK)
	public List<Mascota> findAll() {
		LOGGER.info("Accediendo findAll()...");
		return mascotaService.findAll();
	}

	@GetMapping("/buscar/nombre/{nombre}")
	@ResponseStatus(HttpStatus.OK)
	public List<Mascota> mascotasByNombre(@PathVariable String nombre) {
		return mascotaService.mascotasByNombre(nombre);
	}

	@GetMapping("/buscar/raza/{raza}")
	@ResponseStatus(HttpStatus.OK)
	public List<Mascota> mascotasByRaza(@PathVariable String raza) {
		return mascotaService.mascotasByRaza(raza);
	}

	@GetMapping("/buscar/tamaño/{tamaño}")
	@ResponseStatus(HttpStatus.OK)
	public List<Mascota> mascotasByTamaño(@PathVariable String tamaño) {
		List<Mascota> mascotas = mascotaService.mascotasByTamaño(tamaño);
		return mascotas;
	}

	@GetMapping("/buscar/genero/{genero}")
	@ResponseStatus(HttpStatus.OK)
	public List<Mascota> mascotasByGenero(@PathVariable String genero) {
		List<Mascota> mascotas = mascotaService.mascotasByGenero(genero);
		return mascotas;
	}
	/*
	 * 
	 * @GetMapping("/buscar/{tipo}")
	 * 
	 * @ResponseStatus(HttpStatus.OK) public List<Mascota>
	 * mascotasByEspecie(@PathVariable String especie){ List<Mascota> mascotas =
	 * mascotaService.mascotasByEspecie(especie); return mascotas; }
	 * 
	 * @GetMapping("/buscar/{edad}")
	 * 
	 * @ResponseStatus(HttpStatus.OK) public List<Mascota>
	 * mascotasByEdad(@PathVariable String edad){ List<Mascota> mascotas =
	 * mascotaService.mascotasByEdad(edad); return mascotas; }
	 */
}
