package com.trackpets.springboot.web.app.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.trackpets.springboot.web.app.models.entity.Role;

public interface IRoleDao extends JpaRepository<Role, Long>{
    Role findByName(String name);

    @Override
    void delete(Role role);

}
