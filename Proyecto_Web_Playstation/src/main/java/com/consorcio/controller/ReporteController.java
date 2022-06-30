package com.consorcio.controller;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.consorcio.entity.Juegos;
import com.consorcio.services.BoletaServices;
import com.consorcio.services.JuegosService;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping("/reporte")
public class ReporteController {
	@Autowired
	private JuegosService juegosService;
	@Autowired
	private BoletaServices boletaServices;
	
	@RequestMapping("/juegos")
	public void medicamentos(HttpServletResponse response) {
		try {
			
			List<Juegos> lista=juegosService.listarTodos();
			File file=ResourceUtils.getFile("classpath:juegosReporte.jrxml");
						JasperReport jasper=JasperCompileManager.compileReport(file.getAbsolutePath());			
			JRBeanCollectionDataSource origen=new JRBeanCollectionDataSource(lista);			//crear reporte
			JasperPrint jasperPrint=JasperFillManager.fillReport(jasper,null,origen);
			
			response.setContentType("application/pdf");
			//
			OutputStream salida=response.getOutputStream();
			//exportar a pdf
			JasperExportManager.exportReportToPdfStream(jasperPrint,salida);		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
