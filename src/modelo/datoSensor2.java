package modelo;

import java.util.Date;


public final class datoSensor2 extends datoSensor{
	private Integer datosMedicos;
	
	
	public datoSensor2(Date fecha, String dni, Integer datosMedicos) {
		super(fecha, dni);
		this.datosMedicos = datosMedicos;
	}
	
	public datoSensor2() {
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
