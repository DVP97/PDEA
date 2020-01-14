package modelo;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class datoSensor1 {
	private Integer frecuenciaAntes;
	private Integer frecuenciaDespues;
	private Date fecha;
	private String dni;
	
	
	public datoSensor1(Integer frecuenciaAntes, Integer frecuenciaDespues,  String dni) {
		this.frecuenciaAntes = frecuenciaAntes;
		this.frecuenciaDespues = frecuenciaDespues;
		this.fecha = Calendar.getInstance().getTime();;
		this.dni = dni;
	}
	
	public datoSensor1() {
		this.frecuenciaAntes= null;
		this.frecuenciaDespues= null;
		this.fecha= new Date();
		this.dni = null;
	}
	
	//GETTERS
	public String getDni() {
		return dni;
	}
	public Date getFecha() {
		return fecha;
	}
	
	@SuppressWarnings("deprecation")
	public String getFechaString() {
		// Choose time zone in which you want to interpret your Date
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
		cal.setTime(fecha);
		String dia = ((Integer) fecha.getDate()).toString();
		int m = fecha.getMonth() +1;
		String mes = ((Integer) m).toString();
		int year =  cal.get(Calendar.YEAR);
		String anho = ((Integer) year).toString();
		String hora = ((Integer) fecha.getHours()).toString();
		String min = ((Integer) fecha.getMinutes()).toString();
		String f = hora+ ":" + min + "\t-\t" +dia + "/"+ mes + "/"+ anho ;
		return f;
		
	}
	
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
	public void setDni(String dni) {
		this.dni = dni;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}
