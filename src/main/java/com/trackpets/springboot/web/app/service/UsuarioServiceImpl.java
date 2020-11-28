package com.trackpets.springboot.web.app.service;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.trackpets.springboot.web.app.UserAlreadyExistException;
import com.trackpets.springboot.web.app.models.dao.IRoleDao;
import com.trackpets.springboot.web.app.models.dao.IUsuarioDao;
import com.trackpets.springboot.web.app.models.dao.dto.UsuarioDTO;
import com.trackpets.springboot.web.app.models.entity.Role;
import com.trackpets.springboot.web.app.models.entity.Usuario;


@Service
@Transactional
public class UsuarioServiceImpl implements IUsuarioService {


	@Autowired
	private IRoleDao roleDao;
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Autowired(required = true)
	private PasswordEncoder passwordEncoder;

	@Override
	public void save(Usuario usuario) {
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		List<Role> rolesUsuario = roleDao.findAll();

		usuario.setRoles(rolesUsuario);
		usuarioDao.save(usuario);

	}

	@Override
	public Usuario findByUsername(String username) {
		return usuarioDao.findByUsername(username);
	}

	@Override
	public Usuario registerNewUserAccount(UsuarioDTO accountDto) throws UserAlreadyExistException {

		if (emailExists(accountDto.getEmail())) {
			throw new UserAlreadyExistException(
					"There is an account with that email address: " + accountDto.getEmail());
		}
		final Usuario usuario = new Usuario();

		usuario.setUsername(accountDto.getUsername());
		usuario.setPassword(passwordEncoder.encode(accountDto.getPassword()));
		usuario.setEmail(accountDto.getEmail());
		usuario.setRoles(Arrays.asList(roleDao.findByName("ROLE_USER")));
		return usuarioDao.save(usuario);
	}

	private boolean emailExists(final String email) {
		return usuarioDao.findByEmail(email) != null;
	}

}
