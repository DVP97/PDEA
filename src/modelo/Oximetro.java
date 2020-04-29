package modelo;

import java.util.Date;


public final class Oximetro extends datoSensor{
	private Integer datosMedicos;
	
	
	public Oximetro(Date fecha, String dni, Integer datosMedicos) {
		super(fecha, dni);
		this.datosMedicos = datosMedicos;
	}
	
	public Oximetro() {
		super();
		this.datosMedicos = null;
	}
	//GETTERS
	public Integer getDatosMedicos() {
		return datosMedicos;
	}

	
	//SETTERS
	public void setDatosMedicos(Integer datosMedicos) {
		this.datosMedicos = datosMedicos;
	}

	
}
