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
	
	
}
