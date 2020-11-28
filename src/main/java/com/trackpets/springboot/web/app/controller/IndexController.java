package com.trackpets.springboot.web.app.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.trackpets.springboot.web.app.UserAlreadyExistException;
import com.trackpets.springboot.web.app.models.dao.dto.UsuarioDTO;
import com.trackpets.springboot.web.app.models.entity.Usuario;
import com.trackpets.springboot.web.app.service.IUsuarioService;

@Controller
public class IndexController {

	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping("/login")
	public String login(Model model, Principal principal, RedirectAttributes flash) {

		if (principal != null) {
			flash.addFlashAttribute("info", "Ya se ha iniciado sesión");
			return "redirect:/mascota/home";
		}
		return "login";
	}
	
    @GetMapping("/registro")
    public String verRegistroForm(WebRequest request, Model model) {
    	UsuarioDTO usuarioDTO = new UsuarioDTO();
        model.addAttribute("user", usuarioDTO);
        return "registroUsuario";
    }
    
    @PostMapping("/registro")
    public ModelAndView RegistroForm(@ModelAttribute("userForm") UsuarioDTO userDto, ModelAndView mav, BindingResult bindingResult, HttpServletRequest request) {
    	try {
            Usuario registered = usuarioService.registerNewUserAccount(userDto);
        } catch (UserAlreadyExistException uaeEx) {
            mav.addObject("message", "An account for that username/email already exists.");
            return mav;
        }
     
        return new ModelAndView("successRegister", "user", userDto);
    }

   
	@Secured("ROLE_USER")
	@GetMapping(value = { "/home", "/", "" })
	public String index(ModelMap modelmap) {
		modelmap.addAttribute("titulo", "Track Pets Web Oficial");
		return "index";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping(value = "/addAnimal")
	public String addAnimal(ModelMap modelmap) {
		modelmap.addAttribute("titulo", "Alta mascota");
		return "formPet";
	}

	@Secured("ROLE_USER")
	@GetMapping(value = "/addProtectora")
	public String addProtectora(ModelMap modelmap) {
		modelmap.addAttribute("titulo", "Alta protectora");
		return "formProtectora";
	}

	@Secured("ROLE_USER")
	@GetMapping(value = "/addPersona")
	public String addPersona(ModelMap modelmap) {
		modelmap.addAttribute("titulo", "Alta persona");
		return "formPersona";
	}
}
