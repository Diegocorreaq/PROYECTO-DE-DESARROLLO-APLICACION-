package com.consorcio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.consorcio.entity.Enlace;
import com.consorcio.entity.Usuario;
import com.consorcio.services.UsuarioService;

//crear un atributo con tipo sesion



@SessionAttributes({"ENLACES","DATOS","CODIGOUSUARIO"})
@Controller
public class UsuarioController {
	@Autowired UsuarioService usuarioService;
	
	
	
	
	@RequestMapping("/login")
	public String index() {
		return "sesion";
		
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
