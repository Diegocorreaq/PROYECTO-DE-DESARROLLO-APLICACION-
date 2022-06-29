package com.consorcio.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_Juegos")
public class Juegos {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name="cod_juegos")
	private int codigo;
	@Column(name="nom_juegos")
	private String nombre;
	@Column(name="des_juegos")
	private String descripcion;
	@Column(name="sto_juegos")
	private int stock;
	@Column(name="pre_juegos")
	private double precio;
	@Column(name="fec_fab_juegos")
	private LocalDate fecha;
	
	
	//relacion de muchos "JUEGOS" a UNO "TipoJuego"
	@ManyToOne
	@JoinColumn(name="cod_tipo")
	private TipoJuego tipo; //asociacion

	
	public  Juegos() {
		
	}
	public  Juegos(int codJue) {
		codigo=codJue;
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


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}


	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}


	public LocalDate getFecha() {
		return fecha;
	}


	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}


	public TipoJuego getTipo() {
		return tipo;
	}


	public void setTipo(TipoJuego tipo) {
		this.tipo = tipo;
	}
	
	
	
	
	
	
	

}
