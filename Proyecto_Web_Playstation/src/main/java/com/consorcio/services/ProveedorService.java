package com.consorcio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.consorcio.dao.ProveedorRepository;
import com.consorcio.entity.Cliente;

@Service
public class ProveedorService {
	
	@Autowired
	private ProveedorRepository repoPro;
	
	
	public void guardar(Cliente bean) {
		repoPro.save(bean);
	}
	public void eliminar (int cod) {
		repoPro.deleteById(cod);
			}
	public Cliente buscar (int cod) {
		return repoPro.findById(cod).orElse(null);
	}
	public List<Cliente>listarTodos(){
		return repoPro.findAll();
		
	}

}
