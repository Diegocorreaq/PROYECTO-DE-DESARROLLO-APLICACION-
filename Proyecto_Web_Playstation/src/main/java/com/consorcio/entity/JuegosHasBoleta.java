package com.consorcio.entity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;




@Entity
@Table(name = "tb_Juegos_has_boleta")
public class JuegosHasBoleta implements Serializable{
	@EmbeddedId
	private JuegosHasBoletaPK pk;
	
	
	//Relación MUCHOS  a UNO "juegos"
	@ManyToOne
	@JoinColumn(name="cod_juegos",referencedColumnName = "cod_juegos",insertable = false,updatable =false)
	private Juegos juegos;//ASOCI.
	
	//Relación MUCHOS  a UNO "Boleta"
	@ManyToOne
	@JoinColumn(name="num_bol",referencedColumnName = "num_bol",insertable = false,updatable =false)
	private Boleta boleta;//ASOCI.
	
	@Column(name = "pre")
	private double precio;
	
	@Column(name = "can")
	private int cantidad;

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public JuegosHasBoletaPK getPk() {
		return pk;
	}

	public void setPk(JuegosHasBoletaPK pk) {
		this.pk = pk;
	}

	public Juegos getJuegos() {
		return juegos;
	}

	public void setJuegos(Juegos juegos) {
		this.juegos = juegos;
	}

	public Boleta getBoleta() {
		return boleta;
	}

	public void setBoleta(Boleta boleta) {
		this.boleta = boleta;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	
	
	
	
}
