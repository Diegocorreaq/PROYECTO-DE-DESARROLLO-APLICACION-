package com.consorcio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consorcio.dao.ClienteRepository;
import com.consorcio.entity.Cliente;

@Service
public class ClienteService {
	
	
	@Autowired
	private ClienteRepository repo;
	
	public List<Cliente>listarClientePorApellido(String ape){
		return repo.listAllByApellido(ape);
		
	}

	public void guardar(Cliente bean) {
		repo.save(bean);
	}
	public void eliminar (int cod) {
		repo.deleteById(cod);
			}
	public Cliente buscar (int cod) {
		return repo.findById(cod).orElse(null);
	}
	public List<Cliente>listarTodos(){
		return repo.findAll();
		
	}
	
	
}
