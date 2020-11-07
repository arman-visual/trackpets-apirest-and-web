package com.trackpets.springboot.web.app.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.trackpets.springboot.web.app.models.entity.Animal;

public interface IAnimalesDao extends JpaRepository<Animal, Long>{
	

}
