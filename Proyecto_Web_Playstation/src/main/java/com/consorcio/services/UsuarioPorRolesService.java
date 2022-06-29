package com.consorcio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consorcio.dao.JuegosRepository;
import com.consorcio.dao.UsuarioPorRoles;
import com.consorcio.entity.Usuario;

@Service
public class UsuarioPorRolesService {

	@Autowired
	private UsuarioPorRoles repo;
	
	public void guardar(Usuario bean) {
		repo.save(bean);
	}
	public void eliminar (int cod) {
		repo.deleteById(cod);
			}
	public Usuario buscar (int cod) {
		return repo.findById(cod).orElse(null);
	}
	public List<Usuario>listarTodos(){
		return repo.findAll();
		
	}
	
	
}
