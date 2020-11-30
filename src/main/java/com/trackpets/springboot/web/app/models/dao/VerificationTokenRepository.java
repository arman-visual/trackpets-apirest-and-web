package com.trackpets.springboot.web.app.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trackpets.springboot.web.app.models.entity.Usuario;
import com.trackpets.springboot.web.app.models.entity.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

	VerificationToken findByToken(String token);
	 
    VerificationToken findByUser(Usuario user);
}
