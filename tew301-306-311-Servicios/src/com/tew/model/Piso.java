package com.tew.model;

public class Piso {

	private int id;
	private int idagente;
	private int precio;
	private String direccion;
	private String ciudad;
	private int ano;
	private int estado;
	private String sestado;
	private String Foto;
	private boolean visita;

	public boolean isVisita() {
		return visita;
	}

	public void setVisita(boolean visita) {
		this.visita = visita;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdagente() {
		return idagente;
	}

	public void setIdagente(int idagente) {
		this.idagente = idagente;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

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
		return Foto;
	}

	public void setFoto(String foto) {
		Foto = foto;
	}

}
