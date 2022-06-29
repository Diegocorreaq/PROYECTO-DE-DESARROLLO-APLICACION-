package com.consorcio.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.consorcio.entity.Cliente;

import com.consorcio.services.ProveedorService;

@Controller
@RequestMapping("/proveedor")
public class ProveedorController {
	
	@Autowired
	private ProveedorService ProveedorServicio;
	
	
	
	@RequestMapping("/")
	public String index(Model model) {
		//crear atributos
		model.addAttribute("proveedor",ProveedorServicio.listarTodos());
		return "proveedor";
	}
	
	@RequestMapping("/guardar")
	public String guardar(@RequestParam("codigo")int cod,@RequestParam("nombre")String nom,@RequestParam("paterno")String pa,
			@RequestParam("materno")String ma,@RequestParam("sexo")String sexo,@RequestParam("dni")int dni,
			@RequestParam("fechaNacimiento")String fecha,@RequestParam("celular")int cel,@RequestParam("direccion")String dir
			,@RequestParam("distrito")int dis
			,RedirectAttributes redirect) {
		
		try {
			Cliente c= new Cliente();
			c.setNombre(nom);
			c.setPaterno(pa);
			c.setMaterno(ma);
			c.setSexo(sexo);
			c.setDni(dni);
			c.setFechaNacimiento(LocalDate.parse(fecha));
			c.setCelular(cel);
			c.setDireccion(dir);
			c.setDistrito(dis);
			
			
			
			//validar cod
			if(cod==0) {
				ProveedorServicio.guardar(c);
				redirect.addFlashAttribute("MENSAJE", "Proveedor registrado correctamente");
			}else {
				c.setCodigo(cod);
				ProveedorServicio.guardar(c);
					redirect.addFlashAttribute("MENSAJE", "Proveedor Actualizado correctamente");
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			redirect.addFlashAttribute("MENSAJE","Error al guardar Proveedor");
		e.printStackTrace();
		}
		return "redirect:/proveedor/";
		
	}
	
	
	@RequestMapping("/buscar")
	@ResponseBody
	public Cliente buscar (@RequestParam("codigo")int cod) {
		return ProveedorServicio.buscar(cod);
		
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(@RequestParam("codigo")int cod,RedirectAttributes redirect) {
		try {
			ProveedorServicio.eliminar(cod);
			redirect.addFlashAttribute("MENSAJE", "Proveedor eliminado Correctamente");
			
		} catch (Exception e) {
			// TODO: handle exception
			redirect.addFlashAttribute("MENSAJE", "Error en la eliminacion del Proveedor ");
			e.printStackTrace();
		}
		return "redirect:/proveedor/";
	}

}
