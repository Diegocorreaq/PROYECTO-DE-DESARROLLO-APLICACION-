package com.consorcio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consorcio.dao.TipoJuegoRepository;
import com.consorcio.entity.TipoJuego;

@Service
public class TipoJuegoService {
	
	@Autowired
	private TipoJuegoRepository repo;
	
	public void guardar(TipoJuego bean) {
		repo.save(bean);
		
	}
	public void eliminar (int cod) {
		repo.deleteById(cod);
		
	}
	public TipoJuego buscar(int cod)
	{
		return repo.findById(cod).orElse(null);
		
	}
	public List<TipoJuego> listarTodos(){
		return repo.findAll();
			}
	

}
