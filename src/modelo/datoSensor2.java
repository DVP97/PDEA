package modelo;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import controlador.lectorJson;

public class datoSensor2 {
	private String dni;
	private Integer datosMedicos;
	private Date fecha;
	
	
	public datoSensor2(String dni, Integer datosMedicos) {
		this.datosMedicos = datosMedicos;
		this.dni=dni;
		this.fecha =  Calendar.getInstance().getTime();
	}
	
	public datoSensor2() {
		this.dni = null;
		this.datosMedicos = null;
		this.fecha = Calendar.getInstance().getTime();
	}
	//GETTERS
	public Integer getDatosMedicos() {
		return datosMedicos;
	}
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
	
	//SETTERS
	public void setDatosMedicos(Integer datosMedicos) {
		this.datosMedicos = datosMedicos;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
}
