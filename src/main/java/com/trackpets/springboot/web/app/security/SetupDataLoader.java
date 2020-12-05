package com.trackpets.springboot.web.app.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.trackpets.springboot.web.app.models.dao.IPrivilegeDao;
import com.trackpets.springboot.web.app.models.dao.IRoleDao;
import com.trackpets.springboot.web.app.models.dao.IUsuarioDao;
import com.trackpets.springboot.web.app.models.entity.Privilege;
import com.trackpets.springboot.web.app.models.entity.Role;
import com.trackpets.springboot.web.app.models.entity.Usuario;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
	
	boolean alreadySetup = false;
	 
	@Autowired
	private IUsuarioDao userDao;
	@Autowired
	private IRoleDao roleDao;
	@Autowired
	private IPrivilegeDao privilegeDao;
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (alreadySetup)
            return;
        Privilege readPrivilege
          = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege
          = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
        Privilege editPrivilege
        = createPrivilegeIfNotFound("EDIT_PRIVILEGE");
 
        List<Privilege> adminPrivileges = Arrays.asList(
          readPrivilege, writePrivilege, editPrivilege); 
        List<Privilege> protectoraPrivileges = Arrays.asList(
                readPrivilege, editPrivilege);  
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_PROTEC", protectoraPrivileges);
        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));
 
        Role adminRole = roleDao.findByName("ROLE_ADMIN");
        Usuario usuario = new Usuario();
        usuario.setUsername("Admin");
        usuario.setPassword(passwordEncoder.encode("12345"));
        usuario.setEmail("trackpets2020@gmail.com");
        usuario.setRoles(Arrays.asList(adminRole));
        usuario.setEnabled(true);
        userDao.save(usuario);
 
        alreadySetup = true;
		
	}

	 
    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {
 
        Privilege privilege = privilegeDao.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeDao.save(privilege);
        }
        return privilege;
    }
    
    @Transactional
    Role createRoleIfNotFound(
      String name, Collection<Privilege> privileges) {
 
        Role role = roleDao.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleDao.save(role);
        }
        return role;
    }
}
