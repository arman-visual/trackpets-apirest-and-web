package com.trackpets.springboot.web.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.trackpets.springboot.web.app.service.JpaUserDetailsService;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;


@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JpaUserDetailsService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/css/**", "/js/**", "/images/**", "/home").permitAll()
				/*
				 * .antMatchers("/mascota/addMascota/**").hasAnyRole("USER")
				 * .antMatchers("/mascota/editar/**").hasAnyRole("ADMIN")
				 * .antMatchers("/persona/addPersona/**").hasAnyRole("USER")
				 * .antMatchers("/protectora/addProtectora/**").hasAnyRole("USER")
				 * .antMatchers("/protectora/editar/**").hasAnyRole("ADMIN")
				 */
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/login").permitAll()
		.and().logout().permitAll(); 
	}
	
	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {

			build.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder);
	}
}

