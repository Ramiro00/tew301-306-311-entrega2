package com.tew.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="piso")
public class Piso implements Serializable {

	private int id;
	private int idagente;
	private int precio;
	private String direccion;
	private String ciudad;
	private int ano;
	private int estado;
	private String sestado;
	private String foto;
	private boolean visita;

	public Piso() {
		
	}
	
	public Piso(int id, int idagente, int precio, String direccion, String ciudad, int ano, int estado, String foto) {
		this.id=id;
		this.idagente=idagente;
		this.precio=precio;
		this.direccion=direccion;
		this.ciudad=ciudad;
		this.ano=ano;
		this.estado=estado;
		this.foto = foto;
	}
	
	public boolean isVisita() {
		return visita;
	}

	public void setVisita(boolean visita) {
		this.visita = visita;
	}
	
	@XmlElement
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@XmlElement
	public int getIdagente() {
		return idagente;
	}

	public void setIdagente(int idagente) {
		this.idagente = idagente;
	}
	
	@XmlElement
	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	@XmlElement
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@XmlElement
	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	@XmlElement
	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	@XmlElement
	public int getEstado() {
		return estado;
	}

	public void setEstado(int i) {
		this.estado = i;
	}

	public String getSestado() {
		int i = this.getEstado();
		switch (i) {
		case 1:
			this.sestado = "A Reformar";
			break;
		case 2:
			this.sestado = "Malo";
			break;
		case 3:
			this.sestado = "Medio";
			break;
		case 4:
			this.sestado = "Bueno";
			break;
		case 5:
			this.sestado = "A Estrenar";
			break;

		default:
			this.sestado = "Sin estado";
		}

		return this.sestado;
	}

	public void setSestado(String sestado) {
		this.sestado = sestado;
	}
	
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	@Override
	public String toString() {
		return "Piso [id=" + id + ", idagente=" + idagente + ", precio=" + precio + ", direccion=" + direccion
				+ ", ciudad=" + ciudad + ", ano=" + ano + ", estado=" + estado + ", foto=" + foto + "]";
	}
	
	

}
