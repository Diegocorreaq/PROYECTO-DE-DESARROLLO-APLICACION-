package com.consorcio.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consorcio.dao.BoletaRepository;
import com.consorcio.dao.JuegosHasBoletaRepository;
import com.consorcio.entity.Boleta;
import com.consorcio.entity.JuegosHasBoleta;
import com.consorcio.entity.JuegosHasBoletaPK;

@Service
public class BoletaServices {
	
	@Autowired
	private BoletaRepository repo;
	
	@Autowired
	private JuegosHasBoletaRepository repoDetalle;
	
	@Transactional
	public void grabarBoleta(Boleta boleta) {
		
		try {
			//grabar boleta "cabecera"
			repo.save(boleta);
			//grabar detalle
			//bucle
			for(JuegosHasBoleta mhb:boleta.getListaJuegosHasBoleta()) {
				//crear llave
				JuegosHasBoletaPK pk= new JuegosHasBoletaPK();
				pk.setNumeroBoleta(boleta.getNumeroBoleta());
				pk.setCodigoJuegos(mhb.getJuegos().getCodigo());
				mhb.setPk(pk);
				repoDetalle.save(mhb);
				
				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}
	public Boleta findBoletaPorNumero(int num) {
		return repo.buscarBoletaPorNumero(num);
	}
	public List<JuegosHasBoleta> findDetalleBoletaPorNumero(int num){
		return repo.buscarDetalleBoletaPorNumero(num);
	}
	
	

}
