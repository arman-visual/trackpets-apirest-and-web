package com.trackpets.springboot.web.app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trackpets.springboot.web.app.models.dao.IMascotaDao;
import com.trackpets.springboot.web.app.models.entity.Mascota;

@Service
public class MascotaServiceImpl implements IMascotaService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MascotaServiceImpl.class);

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

	@Override
	public List<Mascota> mascotasByNombre(String nombre) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Mascota> cr = cb.createQuery(Mascota.class);
		Root<Mascota> root = cr.from(Mascota.class);
		cr.select(root).where(cb.like(root.get("nombre"), "%" + nombre + "%"));
		List<Mascota> mascotas = null;
		try {
			mascotas = em.createQuery(cr).getResultList();
			if (!mascotas.isEmpty()) {
				LOGGER.info("Se han encontrado mascotas con el nombre '".concat(nombre).concat("'"));
				mascotas = mascotas.stream().filter(x -> x.isEstado()).collect(Collectors.toList());
				LOGGER.info("Filtrando mascotas solo disponibles... '".concat(nombre).concat("'"));
			} else {
				LOGGER.error("No se han encontrado mascotas con el nombre '".concat(nombre).concat("'"));
			}
		} catch (NoResultException nre) {
			LOGGER.debug("Error :" + nre);
		}
		return mascotas;
	}

	@Override
	public List<Mascota> mascotasByRaza(String raza) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Mascota> cr = cb.createQuery(Mascota.class);
		Root<Mascota> root = cr.from(Mascota.class);
		cr.select(root).where(cb.like(root.get("raza"), "%" + raza + "%"));
		List<Mascota> mascotas = null;
		try {
			mascotas = em.createQuery(cr).getResultList();
			if (!mascotas.isEmpty()) {
			LOGGER.info("Se han encontrado mascotas con raza '".concat(raza).concat("'"));
			mascotas = mascotas.stream().filter(x -> x.isEstado()).collect(Collectors.toList());
			LOGGER.info("Filtrando mascotas solo disponibles... '".concat(raza).concat("'"));
			}else {
				LOGGER.error("No se han encontrado mascotas con el nombre de raza '".concat(raza).concat("'"));
			}
		} catch (Exception e) {
			LOGGER.error("Error: " + e);
		}
		return mascotas;
	}

	@Override
	public List<Mascota> mascotasByTamaño(String tamanyo) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Mascota> cr = cb.createQuery(Mascota.class);
		Root<Mascota> root = cr.from(Mascota.class);
		cr.select(root).where(cb.like(root.get("tamanyo"), "%" + tamanyo + "%"));
		List<Mascota> mascotas = null;
		try {
			mascotas = em.createQuery(cr).getResultList();
			if (!mascotas.isEmpty()) {
			LOGGER.info("Se han encontrado mascotas de tamaño '".concat(tamanyo).concat("'"));
			mascotas = mascotas.stream().filter(x -> x.isEstado()).collect(Collectors.toList());
			LOGGER.info("Filtrando mascotas solo disponibles... '".concat(tamanyo).concat("'"));
			}else {
				LOGGER.error("No se han encontrado mascotas de tamaño ".concat(tamanyo));
			}
		} catch (Exception e) {
			LOGGER.error("Error: " + e);
		}
		return mascotas;
	}

	@Override
	public List<Mascota> mascotasByEspecie(String especie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Mascota> mascotasByEdad(String edad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Mascota> mascotasByGenero(String genero) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Mascota> cr = cb.createQuery(Mascota.class);
		Root<Mascota> root = cr.from(Mascota.class);
		cr.select(root).where(cb.like(root.get("genero"), "%" + genero + "%"));
		List<Mascota> mascotas = null;
		try {
			mascotas = em.createQuery(cr).getResultList();
			if (!mascotas.isEmpty()) {
			LOGGER.info("Se han encontrado mascotas de genero '".concat(genero).concat("'"));
			mascotas = mascotas.stream().filter(x -> x.isEstado()).collect(Collectors.toList());
			LOGGER.info("Filtrando mascotas solo disponibles... '".concat(genero).concat("'"));
			}else {
				LOGGER.error("No se han encontrado mascotas de genero ".concat(genero));
			}
		} catch (Exception e) {
			LOGGER.error("Error: " + e);
		}
		return mascotas;
	}

}
