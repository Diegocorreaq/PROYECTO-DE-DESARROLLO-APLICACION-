package com.consorcio.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consorcio.dao.UsuarioRepository;
import com.consorcio.entity.Enlace;
import com.consorcio.entity.Usuario;


@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repo;
	public void guardar(Usuario bean) {
		repo.save(bean);
	}
	public Usuario iniciaSesion(String login) {
		return repo.iniciarSesion(login);
	}
	public List<Enlace> listaEnlacesDelUsuario(int codRol){
		
		return repo.traerEnlacesDelUsuario(codRol);
	}
	
}
