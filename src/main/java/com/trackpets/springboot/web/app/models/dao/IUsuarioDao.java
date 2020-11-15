package com.trackpets.springboot.web.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.trackpets.springboot.web.app.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long>{
		
	public Usuario findByUSername(String username);
		
	
}
