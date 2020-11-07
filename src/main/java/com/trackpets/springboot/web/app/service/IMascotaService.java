package com.trackpets.springboot.web.app.service;

import java.util.List;
import java.util.Optional;

import com.trackpets.springboot.web.app.models.entity.Mascota;

public interface IMascotaService {
	
	public List<Mascota>findAll();
	
	public Optional<Mascota> findById(Long id);
	
	public void deleteById(Long id);
	
	public Mascota save(Mascota mascota);
	
	//public Animal empleadoByDocumento(String documento);
	
	//public List<Animal> empleadosByProvincia(String provincia);
	
	//public List<Animal> empleadosByApellido(String apellido);

}
