package modelo;


public class Aviso {
	private String concepto;
	private datoSensor datoSensor;
	
	public Aviso (String concepto, datoSensor ds) {
		this.concepto = concepto;
		this.datoSensor = ds;
	}
	
	public Aviso() {
		this.concepto = null;
		this.datoSensor = null;
	}
	
	//GETTERS
	public String getConcepto() {
		return concepto;
	}
	public datoSensor getDatoSensor() {
		return datoSensor;
	}
	//SETTERS
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public void setDatoSensor(datoSensor datoSensor) {
		this.datoSensor = datoSensor;
	}
}
