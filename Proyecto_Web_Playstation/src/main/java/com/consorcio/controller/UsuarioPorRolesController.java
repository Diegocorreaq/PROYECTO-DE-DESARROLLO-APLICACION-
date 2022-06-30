package com.consorcio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.consorcio.entity.Rol;
import com.consorcio.entity.Usuario;
import com.consorcio.services.RolService;
import com.consorcio.services.UsuarioPorRolesService;


@Controller
@RequestMapping("/UsuarioPorRoles")
public class UsuarioPorRolesController {
	
	@Autowired
	private UsuarioPorRolesService usuarioPorRolesService;
	@Autowired
	private RolService rolService;
	
	
	@RequestMapping("/")
	public String index(Model model) {
		//crear atributos
		model.addAttribute("usuarioPorRoles",usuarioPorRolesService.listarTodos());
		model.addAttribute("roles",rolService.listarTodos());
		return "UsuarioPorRoles";
	}
	
	@RequestMapping("/editar")
	public String guardar(@RequestParam("codigo")int cod,@RequestParam("login")String log,@RequestParam("clave")String cla,
			@RequestParam("nombre")String nom,@RequestParam("apellido")String ape
			,@RequestParam("rol")int rol,RedirectAttributes redirect) {
		
		try {
			Usuario u= new Usuario();
		u.setLogin(log);
		u.setClave(cla);
		u.setNombre(nom);
		u.setApellido(ape);
		//
		Rol ro= new Rol();
		ro.setCodigo(rol);
		u.setRol(ro);
			
			
			
			//validar cod
			if(cod==0) {
				usuarioPorRolesService.guardar(u);
				redirect.addFlashAttribute("MENSAJE", "Usuario registrado correctamente");
			}else {
				u.setCodigo(cod);
				usuarioPorRolesService.guardar(u);
					redirect.addFlashAttribute("MENSAJE", "Usuario Actualizado correctamente");
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			redirect.addFlashAttribute("MENSAJE","Error al guardar Proveedor");
		e.printStackTrace();
		}
		return "redirect:/UsuarioPorRoles/";
	
	}
	@RequestMapping("/buscar")
	@ResponseBody
	public Usuario buscar (@RequestParam("codigo")int cod) {
		return usuarioPorRolesService.buscar(cod);
		
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(@RequestParam("codigo")int cod,RedirectAttributes redirect) {
		try {
			usuarioPorRolesService.eliminar(cod);
			redirect.addFlashAttribute("MENSAJE", "Usuario eliminado Correctamente");
			
		} catch (Exception e) {
			// TODO: handle exception
			redirect.addFlashAttribute("MENSAJE", "Error en la eliminacion del Usuario ");
			e.printStackTrace();
		}
		return "redirect:/UsuarioPorRoles/";
	}
	
	
}
