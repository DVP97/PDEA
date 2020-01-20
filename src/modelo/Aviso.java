package modelo;

import controlador.lectorJson;

public class Aviso {
	private String concepto;
	private datoSensor datoSensor;
	private String nombreSensor;
	private String nombrePaciente;
	
	public Aviso (String concepto, datoSensor ds, String nombre) {
		this.concepto = concepto;
		this.datoSensor = ds;
		this.nombreSensor = nombre;
		this.nombrePaciente = lectorJson.getPaciente(datoSensor.getDni()).getNombreCompleto();
	}
	
	public Aviso() {
		this.concepto = null;
		this.datoSensor = null;
		this.nombreSensor = null;
	}
	
	//GETTERS
	public String getConcepto() {
		return concepto;
	}
	public datoSensor getDatoSensor() {
		return datoSensor;
	}
	public String getNombreSensor() {
		return nombreSensor;
	}
	public String getNombrePaciente() {
		return nombrePaciente;
	}
	//SETTERS
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public void setDatoSensor(datoSensor datoSensor) {
		this.datoSensor = datoSensor;
	}
	public void setNombreSensor(String nombreSensor) {
		this.nombreSensor = nombreSensor;
	}
	public void setNombrePaciente() {
		this.nombrePaciente = lectorJson.getPaciente(datoSensor.getDni()).getNombreCompleto();
		
	}
}
