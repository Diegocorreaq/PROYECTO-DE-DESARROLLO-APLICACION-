package com.consorcio.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.consorcio.entity.Boleta;
import com.consorcio.entity.Cliente;
import com.consorcio.entity.Detalle;
import com.consorcio.entity.Juegos;
import com.consorcio.entity.JuegosHasBoleta;
import com.consorcio.entity.Usuario;
import com.consorcio.services.BoletaServices;
import com.consorcio.services.ClienteService;
import com.consorcio.services.JuegosService;

@Controller
@RequestMapping("/ventas")
public class VentaController {
	
	@Autowired
	private JuegosService juegoServicio;
	
	@Autowired
	private ClienteService cliService;
	
	@Autowired
	private BoletaServices bolServicio;
	
	
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("juegos",juegoServicio.listarTodos());
		model.addAttribute("boletas",bolServicio.listarTodos());
		return "boleta";
		
	}
	
	@RequestMapping("/ConsultaCliente")
	@ResponseBody 
	public List<Cliente> consultaCliente(@RequestParam("apellido") String ape){
		return cliService.listarClientePorApellido(ape+"%");
	}
	
	@RequestMapping("/adicionar")
	@ResponseBody
	public List<Detalle> adicionar(@RequestParam("codigo")int cod,@RequestParam("nombre")String nom,@RequestParam("cantidad")int can,@RequestParam("precio")double precio,HttpSession session) {
		//declarar arreglo de objetos de la clase detalle
		List<Detalle>lista;
		//validad si existe el atributo "detalle" dentro de session
		if(session.getAttribute("detalle")==null) //no existe atributo detalle dentro de sesion
			lista= new ArrayList<Detalle>();
			else
				//recuperar atributo "detalle" y guardarlo en el arreglo "lista"
				lista=(List<Detalle>) session.getAttribute("detalle");
			//crear objeto de la clase Detalle
		
				Detalle det= new Detalle();
				//Asignar valor a los atributos del objeto "det"
				det.setCodigo(cod);
				det.setNombre(nom);
				det.setCantidad(can);
				det.setPrecio(precio);
			//adicionar objeto "det" dentro del arreglo "lista"
				lista.add(det);
			
			
			//crear atributo detalle
			session.setAttribute("detalle", lista);
			
		return lista;
	
	}
	
	@RequestMapping("/eliminar")
	@ResponseBody
	public List<Detalle> eliminar(@RequestParam("codigo")int cod,HttpSession session) {

		//recuperar atributo "detalle" y guardarlo en el arreglo "lista"
		List<Detalle>lista=(List<Detalle>) session.getAttribute("detalle");
		//bucle
		for(Detalle det:lista) {
			if(det.getCodigo()==cod) {
				lista.remove(det);
				break;
			}
		}
		return lista;
	}
	
	@RequestMapping("/grabar")
	public String grabar(@RequestParam("codigoCliente") int codCli,@RequestParam("fecha") String fec,HttpSession session,
			@SessionAttribute("CODIGOUSUARIO")int codUsuario,
			 @RequestParam("total") double totalPagar,RedirectAttributes redirect)
	{
		try {
			Boleta bol= new Boleta();
			bol.setFechaEmision(LocalDate.parse(fec));
			bol.setCliente(new Cliente(codCli));
			bol.setUsuario(new Usuario(codUsuario));
			bol.setMonto(totalPagar);
			List<Detalle> lista=(List<Detalle>) session.getAttribute("detalle");
			//Crear un arreglko de la clase juego
			List<JuegosHasBoleta> data= new ArrayList<JuegosHasBoleta>();
			//bucle
			for(Detalle d:lista) {
				JuegosHasBoleta jhb= new JuegosHasBoleta();
				jhb.setJuegos(new Juegos(d.getCodigo()));
				jhb.setCantidad(d.getCantidad());
				jhb.setPrecio(d.getPrecio());
				data.add(jhb);
				
			}
			bol.setListaJuegosHasBoleta(data);
			bolServicio.grabarBoleta(bol);
			//limpiar detalle
			lista.clear();
			session.setAttribute("detalle", lista);
			//
			redirect.addFlashAttribute("MENSAJE","Boleta registrada");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			redirect.addFlashAttribute("MENSAJE","Error en la boleta");
		}
		
		return "redirect:/ventas/";
		
		
	}
	
}
