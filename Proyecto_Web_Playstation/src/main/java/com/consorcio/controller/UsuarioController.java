package com.consorcio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.consorcio.entity.Enlace;
import com.consorcio.entity.Rol;
import com.consorcio.entity.Usuario;
import com.consorcio.services.UsuarioService;

//crear un atributo con tipo sesion



@SessionAttributes({"ENLACES","DATOS","CODIGOUSUARIO"})
@Controller
@RequestMapping("/login")
public class UsuarioController {
	@Autowired UsuarioService usuarioService;
	
	 @Autowired PasswordEncoder passwordEncoder;
	
	
	
	@RequestMapping("/")
	public String index() {
		return "sesion";
		
	}
	@RequestMapping("/nuevoUsu")
	public String nuevoUsuario() {
		return "nuevoUsu";
		
	}
	@RequestMapping("/guardar")
	public String guardar(@RequestParam("codigo")int cod,@RequestParam("nombre")String nom,@RequestParam("apellido")String ape,
			@RequestParam("nomusu")String nomusu,@RequestParam("contraseña")String pass
			,RedirectAttributes redirect) {
		
		try {
			Usuario u= new Usuario();
			u.setNombre(nom);
			u.setApellido(ape);
			u.setLogin(nomusu);
			u.setClave(passwordEncoder.encode(pass));
			//
			Rol r= new Rol();
			r.setCodigo(1);
			u.setRol(r);
			
			//validar cod
			if(cod==0) {
				usuarioService.guardar(u);
				redirect.addFlashAttribute("MENSAJE", "Usuario registrado correctamente");
			}else {
				u.setCodigo(cod);
				usuarioService.guardar(u);
					redirect.addFlashAttribute("MENSAJE", "Juego Actualizado correctamente");
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			redirect.addFlashAttribute("MENSAJE","Error al guardar juego");
		e.printStackTrace();
		}
		return "redirect:/login/";
		
	}
	
	@RequestMapping("/intranet")
	public String intranet(Authentication auth, Model model) {
		//obtener nombre de usuario que inicio sesion
		String nomUsuario=auth.getName();
		//invocar al metodo iniciaSesion
		Usuario u= usuarioService.iniciaSesion(nomUsuario);
		//invocar al metodo listaEnlacesDelUsuario
		
		List<Enlace>lista= usuarioService.listaEnlacesDelUsuario(u.getRol().getCodigo());
		//crear un atributo con el valor del arreglo lista
		model.addAttribute("CODIGOUSUARIO", u.getCodigo());
		model.addAttribute("ENLACES", lista);
		model.addAttribute("DATOS", u.getApellido()+" "+ u.getNombre());
		return "intranet";
		
	}
	
}
