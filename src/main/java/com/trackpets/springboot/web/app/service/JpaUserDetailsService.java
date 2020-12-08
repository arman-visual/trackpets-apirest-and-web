package com.trackpets.springboot.web.app.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trackpets.springboot.web.app.models.dao.IUsuarioDao;
import com.trackpets.springboot.web.app.models.entity.Privilege;
import com.trackpets.springboot.web.app.models.entity.Role;
import com.trackpets.springboot.web.app.models.entity.Usuario;

@Service("userDetailsService")
@Transactional
public class JpaUserDetailsService implements UserDetailsService {

	@Autowired
	private IUsuarioDao usuarioDao;

	private Logger logger = LoggerFactory.getLogger(JpaUserDetailsService.class);

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		try {
			Usuario user = usuarioDao.findByEmail(email);
			if (user == null) {
				throw new UsernameNotFoundException("No se ha encontrado usuario con email: " + email);
			}

			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
					user.isEnabled(), true, true, true, getAuthorities(user.getRoles()));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {

		return getGrantedAuthorities(getPrivileges(roles));
	}

	private List<String> getPrivileges(Collection<Role> roles) {

		List<String> privileges = new ArrayList<>();
		List<Privilege> collection = new ArrayList<>();
		for (Role role : roles) {
			collection.addAll(role.getPrivileges());
		}
		for (Privilege item : collection) {
			privileges.add(item.getName());
		}
		return privileges;
	}

	private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (String privilege : privileges) {
			authorities.add(new SimpleGrantedAuthority(privilege));
		}
		return authorities;
	}
}
