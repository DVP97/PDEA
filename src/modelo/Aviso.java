package modelo;


public class Aviso {
	private String concepto;
	private datoSensor datoSensor;
	private String nombreSensor;
	
	public Aviso (String concepto, datoSensor ds, String nombre) {
		this.concepto = concepto;
		this.datoSensor = ds;
		this.nombreSensor = nombre;
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
}
