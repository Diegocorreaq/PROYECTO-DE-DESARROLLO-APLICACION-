package com.consorcio.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.consorcio.entity.Juegos;
import com.consorcio.entity.TipoJuego;
import com.consorcio.services.JuegosService;
import com.consorcio.services.TipoJuegoService;

@Controller
@RequestMapping("/juegos")
public class JuegosController {
	
	@Autowired
	private JuegosService juegoServicio;
	@Autowired
	private TipoJuegoService juegoTipoServicio;
	
	
	
	@RequestMapping("/")
	public String index(Model model) {
		//crear atributos
		model.addAttribute("juegos",juegoServicio.listarTodos());
		model.addAttribute("tipojuego",juegoTipoServicio.listarTodos());
		return "juegos";
	}

	
	@RequestMapping("/guardar")
	public String guardar(@RequestParam("codigo")int cod,@RequestParam("nombre")String nom,@RequestParam("descripcion")String des,
			@RequestParam("stock")int stock,@RequestParam("precio")double pre,
			@RequestParam("fecha")String fecha,@RequestParam("tipo")int tipo
			,RedirectAttributes redirect) {
		
		try {
			Juegos j= new Juegos();
			j.setNombre(nom);
			j.setDescripcion(des);
			j.setStock(stock);
			j.setPrecio(pre);
			j.setFecha(LocalDate.parse(fecha));
			//
			TipoJuego tm= new TipoJuego();
			tm.setCodigoTipo(tipo);
			j.setTipo(tm);
			
			//validar cod
			if(cod==0) {
				juegoServicio.guardar(j);
				redirect.addFlashAttribute("MENSAJE", "Juego registrado correctamente");
			}else {
				j.setCodigo(cod);
				juegoServicio.guardar(j);
					redirect.addFlashAttribute("MENSAJE", "Juego Actualizado correctamente");
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			redirect.addFlashAttribute("MENSAJE","Error al guardar juego");
		e.printStackTrace();
		}
		return "redirect:/juegos/";
		
	}
	
	
	
	@RequestMapping("/buscar")
	@ResponseBody
	public Juegos buscar (@RequestParam("codigo")int cod) {
		return juegoServicio.buscar(cod);
		
	}
	
	
	@RequestMapping("/eliminar")
	public String eliminar(@RequestParam("codigo")int cod,RedirectAttributes redirect) {
		try {
			juegoServicio.eliminar(cod);
			redirect.addFlashAttribute("MENSAJE", "Juego eliminado Correctamente");
			
		} catch (Exception e) {
			// TODO: handle exception
			redirect.addFlashAttribute("MENSAJE", "Error en la eliminacion del juego ");
			e.printStackTrace();
		}
		return "redirect:/juegos/";
	}

	
	
	
}
