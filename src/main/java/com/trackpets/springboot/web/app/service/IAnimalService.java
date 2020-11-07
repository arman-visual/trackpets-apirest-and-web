package com.trackpets.springboot.web.app.service;

import java.util.List;
import java.util.Optional;

import com.trackpets.springboot.web.app.models.entity.Animal;

public interface IAnimalService {
	
	public List<Animal>findAll();
	
	//public Optional<Animal> findById(Long id);
	
	//public void deleteById(Long id);
	
	//public Animal save(Animal empleado);
	
	//public Animal empleadoByDocumento(String documento);
	
	//public List<Animal> empleadosByProvincia(String provincia);
	
	//public List<Animal> empleadosByApellido(String apellido);

}
