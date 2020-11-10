package com.trackpets.springboot.web.app.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trackpets.springboot.web.app.models.dao.IProtectoraDao;
import com.trackpets.springboot.web.app.models.entity.Protectora;

@Service
public class ProtectoraServiceImpl implements IProtectoraService{

	@Autowired
	private EntityManager em;
	@Autowired
	private IProtectoraDao protectoraDAO;
	
	@Override
	@Transactional(readOnly = true)
	public List<Protectora> findAll() {
		return (List<Protectora>)protectoraDAO.findAll();
	}

	@Override
	@Transactional
	public Protectora save(Protectora protectora) {
		return protectoraDAO.save(protectora);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		protectoraDAO.deleteById(id);
	}

	@Override
	@Transactional
	public Optional<Protectora> findById(Long id) {
		return protectoraDAO.findById(id);
	}

}
