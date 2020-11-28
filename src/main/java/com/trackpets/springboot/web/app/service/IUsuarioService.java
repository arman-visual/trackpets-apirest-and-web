package com.trackpets.springboot.web.app.service;

import com.trackpets.springboot.web.app.UserAlreadyExistException;
import com.trackpets.springboot.web.app.models.dao.dto.UsuarioDTO;
import com.trackpets.springboot.web.app.models.entity.Usuario;


public interface IUsuarioService {
	void save(Usuario usuario);

    Usuario findByUsername(String username); 
    
    Usuario registerNewUserAccount(UsuarioDTO accountDto) throws UserAlreadyExistException;
}
