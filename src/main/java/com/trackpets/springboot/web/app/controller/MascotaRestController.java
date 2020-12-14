package com.trackpets.springboot.web.app.controller;

import java.util.List;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import com.trackpets.springboot.web.app.models.entity.Mascota;
import com.trackpets.springboot.web.app.service.IMascotaService;

@Secured("ROLE_USER")
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
		List<Mascota> mascotas = mascotaService.mascotasByNombre(nombre);
		if(mascotas.isEmpty()) {
			throw new NoResultException("No se han encontrado mascotas con raza '".concat(nombre).concat("'"));
		} else return mascotas;
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
}
