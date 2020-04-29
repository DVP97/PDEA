package modelo;

import java.util.Date;

public final class Presion extends datoSensor{
	private Integer dato;
	
	
	public Presion(Date fecha_dato, Integer dato, String paciente) {	
		super(fecha_dato,paciente);
		this.dato = dato;
	}
	
	public Presion() {
		super();	
		this.dato = null;
	}
	
	//GETTERS

	public Integer getDato() {
		return dato;
	}

	
	//SETTERS
	public void setDato(Integer dato) {
		this.dato = dato;
	}
	
}
