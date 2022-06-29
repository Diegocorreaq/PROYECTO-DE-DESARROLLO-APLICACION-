package com.consorcio.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.consorcio.entity.Usuario;

public interface UsuarioPorRoles  extends JpaRepository<Usuario, Integer>{

}
