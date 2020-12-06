package com.trackpets.springboot.web.app.controller;

import java.security.Principal;
import java.util.Calendar;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.trackpets.springboot.web.app.UserAlreadyExistException;
import com.trackpets.springboot.web.app.models.dao.dto.UsuarioDTO;
import com.trackpets.springboot.web.app.models.entity.Usuario;
import com.trackpets.springboot.web.app.models.entity.VerificationToken;
import com.trackpets.springboot.web.app.security.OnRegistrationCompleteEvent;
import com.trackpets.springboot.web.app.service.IUsuarioService;

@Controller
public class IndexController {

	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private MessageSource messages;

	@Autowired
	ApplicationEventPublisher eventPublisher;
	
	@GetMapping("/login")
	public String login(Model model, Principal principal, RedirectAttributes flash) {

		if (principal != null) {
			flash.addFlashAttribute("info", "Ya se ha iniciado sesión");
			return "redirect:/mascota/home";
		}
		return "login";
	}
	
    @GetMapping("/registro")
    public String verRegistroForm(final HttpServletRequest request, Model model) {
    	UsuarioDTO usuarioDTO = new UsuarioDTO();
        model.addAttribute("user", usuarioDTO);
        return "registroUsuario";
    }
    
    @PostMapping("/registro/save")
	public ModelAndView RegistroForm(@ModelAttribute("user") @Valid UsuarioDTO userDto, BindingResult bindingResult,
			HttpServletRequest request, Errors errors) {
		try {
			//Validamos formulario por si existe algun error
			if (bindingResult.hasErrors()) {
				throw new Exception();
			}
			Usuario registered = usuarioService.registerNewUserAccount(userDto);
			String appUrl = request.getContextPath();
			eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), appUrl));

		} catch (UserAlreadyExistException uaeEx) {
			ModelAndView mav = new ModelAndView("registroUsuario", "user", userDto);
			mav.addObject("error", "Email ya registrado.");
			return mav;
		} catch (RuntimeException ex) {
			System.out.println(ex);
			return new ModelAndView("emailError", "user", userDto);
		} catch (Exception e) {
			ModelAndView mav = new ModelAndView("registroUsuario", "user", userDto);
			mav.addObject("errorPassword", "Passwords no coinciden.");
			return mav;
		}

		return new ModelAndView("successRegister", "user", userDto);
	}

    @GetMapping("/regitrationConfirm")
    public String confirmRegistration
      (WebRequest request, Model model, @RequestParam("token") String token) {
     
        Locale locale = request.getLocale();
        
        VerificationToken verificationToken = usuarioService.getVerificationToken(token);
        if (verificationToken == null) {
            String message = messages.getMessage("auth.message.invalidToken", null, locale);
            model.addAttribute("message", message);
            return "redirect:/badUser.html?lang=" + locale.getLanguage();
        }
        
        Usuario user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            String messageValue = messages.getMessage("auth.message.expired", null, locale);
            model.addAttribute("message", messageValue);
            return "redirect:/badUser.html?lang=" + locale.getLanguage();
        } 
        
        user.setEnabled(true); 
        usuarioService.saveRegisteredUser(user); 
        return "redirect:/login.html?lang=" + request.getLocale().getLanguage(); 
    }
   
	//@Secured("ROLE_USER")
	@GetMapping(value = { "/home", "/", "" })
	public String index(ModelMap modelmap) {
		modelmap.addAttribute("titulo", "Track Pets Web Oficial");
		return "index";
	}

	//@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@GetMapping(value = "/addAnimal")
	public String addAnimal(ModelMap modelmap) {
		modelmap.addAttribute("titulo", "Alta mascota");
		return "formPet";
	}

	//@Secured({"READ_PRIVILEGE", "WRITE_PRIVILEGE"})
	@GetMapping(value = "/addProtectora")
	public String addProtectora(ModelMap modelmap) {
		modelmap.addAttribute("titulo", "Alta protectora");
		return "formProtectora";
	}

	//@Secured("ROLE_ADMIN")
	@GetMapping(value = "/addPersona")
	public String addPersona(ModelMap modelmap) {
		modelmap.addAttribute("titulo", "Alta persona");
		return "formPersona";
	}
}
