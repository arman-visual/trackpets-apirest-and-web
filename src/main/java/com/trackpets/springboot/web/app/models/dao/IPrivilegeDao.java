package com.trackpets.springboot.web.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.trackpets.springboot.web.app.models.entity.Privilege;

public interface IPrivilegeDao extends CrudRepository<Privilege, Long> {

	Privilege findByName(String name);

	@Override
	void delete(Privilege privilege);

}
