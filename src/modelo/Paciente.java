package modelo;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public final class Paciente extends Usuario{
	private Date fecha_nacimiento;
	String medico;
	boolean ejerciciosHechos;

	public Paciente(String dni, String nombre, String apellidos, String telefono, String contrasena, Date fecha_nacimiento, String medico, boolean ejerciciosHechos) {
		super(dni, nombre, apellidos, telefono, contrasena);
		this.fecha_nacimiento= fecha_nacimiento;
		this.medico = medico;
		this.ejerciciosHechos = ejerciciosHechos;
	}
	
	public Paciente () {
		super();
		this.fecha_nacimiento= null;
		this.medico = null;
		this.ejerciciosHechos=false;
	}
	
	
	//GETTERS
	@SuppressWarnings("deprecation")
	public String getFechaNacimientoString() {
		// Choose time zone in which you want to interpret your Date
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
		cal.setTime(fecha_nacimiento);
		String dia = ((Integer) fecha_nacimiento.getDate()).toString();
		int m = fecha_nacimiento.getMonth() +1;
		String mes = ((Integer) m).toString();
		int year =  cal.get(Calendar.YEAR);
		String anho = ((Integer) year).toString();
		
		String f = dia + "/"+ mes + "/"+ anho ;
		return f;
		
	}
	
	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public String getMedico() {
		return medico;
	}

	public String getNombreCompleto() {
		String n = this.getNombre()+ " " + this.getApellidos();
		return n;
	}
	public boolean isEjerciciosHechos() {
		return ejerciciosHechos;
	}
	
	//SETTERS
	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento= fecha_nacimiento;
	}

	public void setMedico (String medico) {
		this.medico = medico;
	}
	
	public void setEjerciciosHechos(boolean ejerciciosHechos) {
		this.ejerciciosHechos = ejerciciosHechos;
	}
	
	
	//METODOS


		
}

