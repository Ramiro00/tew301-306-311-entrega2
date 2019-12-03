package com.tew.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


public class Cita {
	private int idPiso;
	private int idCliente;
	private long fechaHoraCita;
	private int estado;
	private String direccion;
	private String estadoStr;
	private String fechaHoraCitaStr;

	public int getIdPiso() {
		return idPiso;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public long getFechaHoraCita() {
		return fechaHoraCita;
	}

	public int getEstado() {
		return estado;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getEstadoStr() {
		int i = this.getEstado();
		switch (i) {
		case 1:
			this.estadoStr = "Seleccionado";
			break;
		case 2:
			this.estadoStr = "Citado";
			break;
		case 3:
			this.estadoStr = "Acpetado";
			break;
		default:
			this.estadoStr = "Seleccionadoo";
		}

		return this.estadoStr;
	}

	public String getFechaHoraCitaStr() {
		// Unix seconds
		long unix_seconds = this.getFechaHoraCita();
		// convert seconds to milliseconds
		Date date = new Date(unix_seconds * 1000L);
		// format of the date
		SimpleDateFormat jdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		jdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));
		String java_date = jdf.format(date);
		this.fechaHoraCitaStr = java_date;

/*		long fhcita = this.getFechaHoraCita();
		Calendar fechor = Calendar.getInstance();
		fechor.setTimeInMillis(fhcita);
		String fecha = fechor.get(Calendar.DAY_OF_MONTH) + "/" + fechor.get(Calendar.MONTH) + "/"
				+ fechor.get(Calendar.YEAR) + " " + fechor.get(Calendar.HOUR) + ":" + fechor.get(Calendar.MINUTE);
		this.fechaHoraCitaStr = fecha;
		return fechaHoraCitaStr;*/

		return this.fechaHoraCitaStr;

	}

	public void setIdPiso(int idPiso) {
		this.idPiso = idPiso;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public void setFechaHoraCita(long fechaHoraCita) {
		this.fechaHoraCita = fechaHoraCita;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public void setEstadoStr(String estadoStr) {
		this.estadoStr = estadoStr;
	}

	public void setFechaHoraCitaStr(String fechaHoraCitaStr) {
		this.fechaHoraCitaStr = fechaHoraCitaStr;
	}
}