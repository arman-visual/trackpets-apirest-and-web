package com.trackpets.springboot.web.app.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.trackpets.springboot.web.app.models.entity.Mascota;

public interface IMascotaDao extends JpaRepository<Mascota, Long>{
	

}
