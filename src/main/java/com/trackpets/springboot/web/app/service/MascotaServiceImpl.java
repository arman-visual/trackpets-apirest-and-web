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
		List<Mascota> mascotas = em.createQuery(cr).getResultList();
		if (mascotas.equals(null) || mascotas.isEmpty()) {
			LOGGER.error("No se han encontrado mascotas con el nombre ".concat(nombre));
			throw new NoResultException("No se han encontrado mascotas con el nombre '".concat(nombre).concat("'"));
		} else {
			LOGGER.info("Se han encontrado mascotas con el nombre '".concat(nombre).concat("'"));
			List<Mascota> mascotaFiltradas = mascotas.stream().filter(x -> x.isEstado()).collect(Collectors.toList());
			LOGGER.info("Filtrando mascotas solo disponibles... '".concat(nombre).concat("'"));
			return mascotaFiltradas;
		}
	}

	@Override
	public List<Mascota> mascotasByRaza(String raza) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Mascota> cr = cb.createQuery(Mascota.class);
		Root<Mascota> root = cr.from(Mascota.class);
		cr.select(root).where(cb.like(root.get("raza"), "%" + raza + "%"));
		List<Mascota> mascotas = em.createQuery(cr).getResultList();
		if (mascotas.equals(null) || mascotas.isEmpty()) {
			LOGGER.error("No se han encontrado mascotas de raza ".concat(raza));
			throw new NoResultException("No se han encontrado mascotas con raza '".concat(raza).concat("'"));
		} else {
			LOGGER.info("Se han encontrado mascotas con raza '".concat(raza).concat("'"));
			List<Mascota> mascotaFiltradas = mascotas.stream().filter(x -> x.isEstado()).collect(Collectors.toList());
			LOGGER.info("Filtrando mascotas solo disponibles... '".concat(raza).concat("'"));
			return mascotaFiltradas;
		}
	}

	@Override
	public List<Mascota> mascotasByTamaño(String tamaño) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Mascota> cr = cb.createQuery(Mascota.class);
		Root<Mascota> root = cr.from(Mascota.class);
		cr.select(root).where(cb.like(root.get("tamaño"), "%" + tamaño + "%"));
		List<Mascota> mascotas = em.createQuery(cr).getResultList();
		if (mascotas.equals(null) || mascotas.isEmpty()) {
			LOGGER.error("No se han encontrado mascotas de tamaño ".concat(tamaño));
			throw new NoResultException("No se han encontrado mascotas de tamaño '".concat(tamaño).concat("'"));
		} else {
			LOGGER.info("Se han encontrado mascotas de tamaño '".concat(tamaño).concat("'"));
			List<Mascota> mascotaFiltradas = mascotas.stream().filter(x -> x.isEstado()).collect(Collectors.toList());
			LOGGER.info("Filtrando mascotas solo disponibles... '".concat(tamaño).concat("'"));
			return mascotaFiltradas;
		}
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
		List<Mascota> mascotas = em.createQuery(cr).getResultList();
		if (mascotas.equals(null) || mascotas.isEmpty()) {
			LOGGER.error("No se han encontrado mascotas de genero ".concat(genero));
			throw new NoResultException("No se han encontrado mascotas de genero '".concat(genero).concat("'"));
		} else {
			LOGGER.info("Se han encontrado mascotas de genero '".concat(genero).concat("'"));
			List<Mascota> mascotaFiltradas = mascotas.stream().filter(x -> x.isEstado()).collect(Collectors.toList());
			LOGGER.info("Filtrando mascotas solo disponibles... '".concat(genero).concat("'"));
			return mascotaFiltradas;
		}
	}

}
