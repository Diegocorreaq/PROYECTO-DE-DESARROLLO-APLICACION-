package com.consorcio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consorcio.dao.JuegosRepository;
import com.consorcio.entity.Juegos;

@Service
public class JuegosService {

	@Autowired
	private JuegosRepository repo;
	
	public void guardar(Juegos bean) {
		repo.save(bean);
	}
	public void eliminar (int cod) {
		repo.deleteById(cod);
			}
	public Juegos buscar (int cod) {
		return repo.findById(cod).orElse(null);
	}
	public List<Juegos>listarTodos(){
		return repo.findAll();
		
	}
	
}
