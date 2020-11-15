
package com.trackpets.springboot.web.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private  
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/css/**", "/js/**", "/images/**", "/index").permitAll()
		/*.antMatchers("/addAnimal/**").hasAnyRole("USER")
		.antMatchers("/eliminarAnimal/**").hasAnyRole("ADMIN")
		.antMatchers("/addPersona/**").hasAnyRole("USER")
		.antMatchers("/eliminaPersona/**").hasAnyRole("ADMIN")
		.antMatchers("/addProtectora/**").hasAnyRole("USER")
		.antMatchers("/eliminarProtectora/**").hasAnyRole("ADMIN")*/
		.anyRequest().authenticated()
		.and()
		.formLogin().permitAll()
		.and()
		.logout().permitAll()
		.and().exceptionHandling().accessDeniedPage("/error_403"); 
	}

	@Bean
	public BCryptPasswordEncoder passwordencoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception {
		PasswordEncoder encoder = passwordencoder();
		UserBuilder users = User.builder().passwordEncoder(encoder::encode);
		
		builder.inMemoryAuthentication()
		.withUser(users.username("admin").password("armando").roles("ADMIN", "USER"))
		.withUser(users.username("admin2").password("carlos").roles("ADMIN", "USER"))
		.withUser(users.username("admin3").password("sergio").roles("ADMIN", "USER"))
		.withUser(users.username("pepe").password("12345").roles("USER"));

	}
}