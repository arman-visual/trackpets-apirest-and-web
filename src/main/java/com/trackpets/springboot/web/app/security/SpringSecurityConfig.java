package com.trackpets.springboot.web.app.security;

import java.util.Locale;
import java.util.Properties;

import javax.mail.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import com.trackpets.springboot.web.app.service.JpaUserDetailsService;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;


@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
	
	@Autowired
	private JpaUserDetailsService userDetailsService;	 
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/*
	 * @Bean public LocaleResolver localeResolver() { SessionLocaleResolver slr =
	 * new SessionLocaleResolver(); slr.setDefaultLocale(Locale.ENGLISH); return
	 * slr; }
	 */

    @Bean
    public LocaleResolver localeResolver() {
        final CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(new Locale("es"));
        return cookieLocaleResolver;
    }
    
    @Bean
    @Description("Spring Message Resolver")
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/css/**", "/js/**", "/images/**", "/home","/registro","/registro/save").permitAll()
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

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	
}

