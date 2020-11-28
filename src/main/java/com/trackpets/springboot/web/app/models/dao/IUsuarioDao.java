package com.trackpets.springboot.web.app.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trackpets.springboot.web.app.models.entity.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario, Long>{
		
	public Usuario findByUsername(String username);
		
    public Usuario findByEmail(String email);

    @Override
    void delete(Usuario user);
}
