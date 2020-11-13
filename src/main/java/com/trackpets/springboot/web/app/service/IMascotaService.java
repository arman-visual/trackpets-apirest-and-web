package com.trackpets.springboot.web.app.service;

import java.util.List;
import java.util.Optional;

import com.trackpets.springboot.web.app.models.entity.Mascota;

public interface IMascotaService {
	
	public List<Mascota>findAll();
	
	public Optional<Mascota> findById(Long id);
	
	public void deleteById(Long id);
	
	public Mascota save(Mascota mascota);
	
	public List<Mascota> mascotasByGenero(String genero);
	
	public List<Mascota> mascotasByNombre(String nombre);
	
	public List<Mascota> mascotasByRaza(String raza);
	
	public List<Mascota> mascotasByTamaño(String tamaño);
	
	public List<Mascota> mascotasByEspecie(String especie);
	
	public List<Mascota> mascotasByEdad(String edad);

}
