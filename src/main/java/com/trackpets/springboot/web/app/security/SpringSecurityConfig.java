package com.trackpets.springboot.web.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.trackpets.springboot.web.app.service.JpaUserDetailsService;


@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
@ComponentScan({ "com.trackpets.springboot.web.app" })
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter implements ApplicationContextAware {
	
	
	@Autowired
	private JpaUserDetailsService userDetailsService;	 
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/resource/**","/static/**", "/css/**", "/js/**", "/images/**", "/home","/registro","/registro/save").permitAll()
				/*
				 * .antMatchers("/mascota/addMascota/**").hasAnyRole("USER")
				 * .antMatchers("/mascota/editar/**").hasAnyRole("ADMIN")
				 * .antMatchers("/persona/addPersona/**").hasAnyRole("USER")
				 * .antMatchers("/protectora/addProtectora/**").hasAnyRole("USER")
				 * .antMatchers("/protectora/editar/**").hasAnyRole("ADMIN")
				 */
		.antMatchers("/mascota/addMascota/**").hasAuthority("EDIT_PRIVILEGE")
		.antMatchers("/mascota/editar/**").hasAuthority("EDIT_PRIVILEGE")
		.antMatchers("/mascota/listar").hasAuthority("READ_PRIVILEGE")
		.antMatchers("/mascota/home").permitAll()
		.antMatchers("/protectora/addProtectora/**").hasAnyAuthority("WRITE_PRIVILEGE")
		.antMatchers("/protectora/editar/**").hasAnyAuthority("WRITE_PRIVILEGE")
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.failureUrl("/login.html?error=true")
		.loginPage("/login").permitAll()
		.and().logout().permitAll(); 
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	
}

