package com.trackpets.springboot.web.app.service;

import java.util.List;
import java.util.Optional;

import com.trackpets.springboot.web.app.models.entity.Protectora;

public interface IProtectoraService {

	public List<Protectora>findAll();
	
	public Protectora save(Protectora protectora);
	
	public void deleteById(Long id);
	
	public Optional<Protectora> findById(Long id);
}
