package com.trackpets.springboot.web.app.service;

import com.trackpets.springboot.web.app.UserAlreadyExistException;
import com.trackpets.springboot.web.app.models.dao.dto.UsuarioDTO;
import com.trackpets.springboot.web.app.models.entity.Usuario;
import com.trackpets.springboot.web.app.models.entity.VerificationToken;

public interface IUsuarioService {
	void save(Usuario usuario);

    Usuario findByUsername(String username); 
    
    Usuario registerNewUserAccount(UsuarioDTO accountDto) throws UserAlreadyExistException;
    
    Usuario findUserByEmail(String email);
    
    Usuario getUser(String verificationToken);
    
    void saveRegisteredUser(Usuario user);
 
    void createVerificationToken(Usuario user, String token);
 
    VerificationToken getVerificationToken(String VerificationToken);
}
