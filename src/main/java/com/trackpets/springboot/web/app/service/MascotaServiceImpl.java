package com.trackpets.springboot.web.app.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trackpets.springboot.web.app.models.dao.IMascotaDao;
import com.trackpets.springboot.web.app.models.entity.Mascota;

@Service
public class MascotaServiceImpl implements IMascotaService{

	@Autowired
	private EntityManager em;
	@Autowired
	private IMascotaDao animalDAO;
	
	@Override
	@Transactional(readOnly = true)
	public List<Mascota> findAll() {
		return (List<Mascota>) animalDAO.findAll();
	}

	@Override
	@Transactional
	public Mascota save(Mascota animal) {
		return animalDAO.save(animal);
	}

	@Override
	public void deleteById(Long id) {
		animalDAO.deleteById(id);
	}

	@Override
	public Optional<Mascota> findById(Long id) {
		return animalDAO.findById(id);
	}

}
