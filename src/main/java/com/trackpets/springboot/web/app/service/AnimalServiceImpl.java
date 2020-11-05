package com.trackpets.springboot.web.app.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.trackpets.springboot.web.app.models.dao.IAnimalesDao;
import com.trackpets.springboot.web.app.models.entity.Animal;

public class AnimalServiceImpl implements IAnimalService{

	@Autowired
	private EntityManager em;
	@Autowired
	private IAnimalesDao animalDAO;
	
	@Override
	public List<Animal> findAll() {
		return (List<Animal>) animalDAO.findAll();
	}

}
