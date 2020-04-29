package modelo;

import java.util.Date;

public final class Pulsiometro extends datoSensor{
	private Integer frecuenciaAntes;
	private Integer frecuenciaDespues;
	
	
	public Pulsiometro(Date fecha, Integer frecuenciaAntes, Integer frecuenciaDespues,  String dni) {
		super(fecha, dni);
		this.frecuenciaAntes = frecuenciaAntes;
		this.frecuenciaDespues = frecuenciaDespues;


	}
	
	public Pulsiometro() {
		super();
		this.frecuenciaAntes= null;
		this.frecuenciaDespues= null;
	}
	
	//GETTERS

	public Integer getFrecuenciaAntes() {
		return frecuenciaAntes;
	}
	public Integer getFrecuenciaDespues() {
		return frecuenciaDespues;
	}
	
	//SETTERS
	public void setFrecuenciaDespues(Integer frecuenciaDespues) {
		this.frecuenciaDespues = frecuenciaDespues;
	}
	public void setFrecuenciaAntes(Integer frecuenciaAntes) {
		this.frecuenciaAntes = frecuenciaAntes;
	}

}
