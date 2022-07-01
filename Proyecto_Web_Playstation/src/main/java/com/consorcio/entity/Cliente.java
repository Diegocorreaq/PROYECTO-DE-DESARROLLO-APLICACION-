package com.consorcio.entity;
import java.io.Serializable;
import java.time.LocalDate;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "tb_cliente")

public class Cliente implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_cli")
	private int codigo;
	@Column(name = "nom_cli")
	private String nombre;
	@Column(name = "pat_cli")
	private String paterno;
	@Column(name = "mat_cli")
	private String materno;
	@Column(name = "sex_cli")
	private String sexo;
	@Column(name = "dni_cli")
	private int dni;
	@Column(name = "fec_nac_cli")
	private LocalDate fechaNacimiento;
	@Column(name = "cel_cli")
	private int celular;
	@Column(name = "dir_cli")
	private String direccion;
	

	
	//Relación UNO a MUCHOS "Boleta"
	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<Boleta> listaBoletas;
	
	public Cliente() {
		
		
	}
	
	public Cliente(int codCli) {
		
		codigo= codCli;
	}
	
	

	public int getCodigo() {
		return codigo;
	}


	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getPaterno() {
		return paterno;
	}


	public void setPaterno(String paterno) {
		this.paterno = paterno;
	}


	public String getMaterno() {
		return materno;
	}


	public void setMaterno(String materno) {
		this.materno = materno;
	}


	public String getSexo() {
		return sexo;
	}


	public void setSexo(String sexo) {
		this.sexo = sexo;
	}


	public int getDni() {
		return dni;
	}


	public void setDni(int dni) {
		this.dni = dni;
	}


	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	public int getCelular() {
		return celular;
	}


	public void setCelular(int celular) {
		this.celular = celular;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}



	public List<Boleta> getListaBoletas() {
		return listaBoletas;
	}


	public void setListaBoletas(List<Boleta> listaBoletas) {
		this.listaBoletas = listaBoletas;
	}
	
	
	
}

