package com.trackpets.springboot.web.app.service;

import java.util.List;

import com.trackpets.springboot.web.app.models.entity.Protectora;

public interface IProtectoraService {

	public List<Protectora>findAll();
	
	public void save(Protectora protectora);
	
	public void deleteById(Long id);
	
	public Protectora findById(Long id);
	
}
