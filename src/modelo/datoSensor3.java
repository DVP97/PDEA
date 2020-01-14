package modelo;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class datoSensor3 {
	private String dni;
	private Integer sistoleAntes;
	private Integer diastoleAntes;
	private Integer sistoleDespues;
	private Integer diastoleDespues;
	private Date fecha;
	
	
	public datoSensor3(String dni, Integer sistoleAntes, Integer diastoleAntes, Integer sistoleDespues, Integer diastoleDespues) {
		this.dni = dni;
		this.sistoleAntes = sistoleAntes;
		this.diastoleAntes = diastoleAntes;
		this.diastoleDespues = diastoleDespues;
		this.sistoleDespues = sistoleDespues;
		this.fecha = Calendar.getInstance().getTime();
	}
	
	public datoSensor3() {
		this.dni = null;
		this.sistoleAntes = null;
		this.diastoleAntes = null;
		this.sistoleDespues = null;
		this.diastoleDespues = null;
		this.fecha = new Date();
	}
	
	//GETTERS
	public Integer getDiastoleAntes() {
		return diastoleAntes;
	}
	public Integer getDiastoleDespues() {
		return diastoleDespues;
	}
	public String getDni() {
		return dni;
	}
	public Date getFecha() {
		return fecha;
	}
	public Integer getSistoleAntes() {
		return sistoleAntes;
	}
	public Integer getSistoleDespues() {
		return sistoleDespues;
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
	public void setDiastoleAntes(Integer diastoleAntes) {
		this.diastoleAntes = diastoleAntes;
	}
	public void setDiastoleDespues(Integer diastoleDespues) {
		this.diastoleDespues = diastoleDespues;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public void setSistoleAntes(Integer sistoleAntes) {
		this.sistoleAntes = sistoleAntes;
	}
	public void setSistoleDespues(Integer sistoleDespues) {
		this.sistoleDespues = sistoleDespues;
	}
	
	
}
