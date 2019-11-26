package modelo;

import java.util.Calendar;
import java.util.Date;

public class Mensaje {
	private String emisor;
	private String receptor;
	private String mensaje;
	private boolean leido;
	private boolean contestado;
	private boolean borrado;
	private Date fecha;
	
	public Mensaje (String emisor, String receptor,  String mensaje) {
		this.emisor =emisor;
		this.receptor = receptor;
		this.mensaje = mensaje;
		this.leido = false;
		this.contestado = false;
		this.borrado = false;
		this.fecha = Calendar.getInstance().getTime();;
	}
	
	public Mensaje () {
		this.emisor =null;
		this.receptor = null; 
		this.mensaje = null;
		this.leido = false;
		this.contestado = false;
		this.borrado = false;
		this.fecha = new Date();
	}
	
	//GETTERS 

	public String getEmisor() {
		return emisor;
	}
	public String getMensaje() {
		return mensaje;
	}
	public String getReceptor() {
		return receptor;
	}
	public boolean isBorrado() {
		return borrado;
	}
	public boolean isContestado() {
		return contestado;
	}
	public boolean isLeido() {
		return leido;
	}
	public Date getFecha() {
		return fecha;
	}
	
	//SETTERS

	public void setEmisor(String emisor) {
		this.emisor = emisor;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public void setReceptor(String receptor) {
		this.receptor = receptor;
	}
	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}
	public void setContestado(boolean contestado) {
		this.contestado = contestado;
	}
	public void setLeido(boolean leido) {
		this.leido = leido;
	}
	public void setFecha() {
		this.fecha = Calendar.getInstance().getTime();;
	}
}