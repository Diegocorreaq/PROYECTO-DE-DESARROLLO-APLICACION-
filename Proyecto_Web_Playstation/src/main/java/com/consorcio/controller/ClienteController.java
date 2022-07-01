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
import com.consorcio.services.ClienteService;
@Controller
@RequestMapping("/Cliente")
public class ClienteController {
	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping("/")
	public String index(Model model) {
		//crear atributos
		model.addAttribute("clientes",clienteService.listarTodos());
		return "cliente";
	}
	@RequestMapping("/guardar")
	public String guardar(@RequestParam("codigo")int cod,@RequestParam("nombre")String nom,@RequestParam("paterno")String pat,
			@RequestParam("materno")String mater,@RequestParam("sexo")String sexo,@RequestParam("dni")int dni,
			@RequestParam("fechaNac")String fecha,@RequestParam("celular")int cel,@RequestParam("direccion")String dir
			,RedirectAttributes redirect) {
		
		try {
			Cliente j= new Cliente();
			j.setNombre(nom);
			j.setPaterno(pat);
			j.setMaterno(mater);
			j.setSexo(sexo);
			j.setDni(dni);
			j.setFechaNacimiento(LocalDate.parse(fecha));
			j.setCelular(cel);
			j.setDireccion(dir);
			//
			
			
			//validar cod
			if(cod==0) {
				clienteService.guardar(j);
				redirect.addFlashAttribute("MENSAJE", "Juego registrado correctamente");
			}else {
				j.setCodigo(cod);
				clienteService.guardar(j);
					redirect.addFlashAttribute("MENSAJE", "Juego Actualizado correctamente");
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			redirect.addFlashAttribute("MENSAJE","Error al guardar juego");
		e.printStackTrace();
		}
		return "redirect:/Cliente/";
		
	}
	@RequestMapping("/buscar")
	@ResponseBody
	public Cliente buscar (@RequestParam("codigo")int cod) {
		return clienteService.buscar(cod);
		
	}
	@RequestMapping("/eliminar")
	public String eliminar(@RequestParam("codigo")int cod,RedirectAttributes redirect) {
		try {
			clienteService.eliminar(cod);
			redirect.addFlashAttribute("MENSAJE", "Juego eliminado Correctamente");
			
		} catch (Exception e) {
			// TODO: handle exception
			redirect.addFlashAttribute("MENSAJE", "Error en la eliminacion del juego ");
			e.printStackTrace();
		}
		return "redirect:/Cliente/";
	}


}
