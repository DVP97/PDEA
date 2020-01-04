package modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public final class Paciente extends Usuario{
	private Date fecha_nacimiento;
	private ArrayList<Integer> ejercicios;
	ArrayList<String> cuidadores; 
	String medico;
	
	public Paciente(String dni, String nombre, String apellidos, Integer telefono, String contrasena, Date fecha_nacimiento, ArrayList<String> cuidadores, String medico, ArrayList<Integer> ejercicios) {
		super(dni, nombre, apellidos, telefono, contrasena);
		this.fecha_nacimiento= fecha_nacimiento;
		this.cuidadores = cuidadores;
		this.medico = medico;
		this.ejercicios = ejercicios;
		
	}
	
	public Paciente () {
		super();
		this.fecha_nacimiento= null;
		this.medico = null;
		this.cuidadores = new ArrayList<String>();
		this.ejercicios = new ArrayList<Integer>();
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
	public ArrayList<String> getCuidadores(){
		return cuidadores;
	}
	public String getMedico() {
		return medico;
	}

	public ArrayList<Integer> getEjercicios() {
		return ejercicios;
	}
	
	public String getNombreCompleto() {
		String n = this.getNombre()+ " " + this.getApellidos();
		return n;
	}

	
	//SETTERS
	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento= fecha_nacimiento;
	}

	public void setCuidadores (ArrayList<String> cuidadores) {
		this.cuidadores = cuidadores;
	}
	
	public void setMedico (String medico) {
		this.medico = medico;
	}
	
	public void setEjercicios(ArrayList<Integer> ejercicios) {
		this.ejercicios = ejercicios;
	}
	//METODOS


		
}

