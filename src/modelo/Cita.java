package modelo;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class Cita {

	private Integer id;
	private String fecha_cita;
	private String nota;
	private String paciente;
	private String medico;

	public Cita(Integer id, String fecha, String nota, String paciente, String medico) {
		this.id = id;
		this.fecha_cita = fecha;
		this.nota = nota;
		this.paciente = paciente;
		this.medico = medico;
	}
	
	public Cita(String fecha, String nota, String paciente, String medico) {
		this.fecha_cita = fecha;
		this.nota = nota;
		this.paciente = paciente;
		this.medico = medico;
	}

	public Cita() {
		// TODO Auto-generated constructor stub
	}

	// GETTERS
	public String getMedico() {
		return medico;
	}
	public String getPaciente() {
		return paciente;
	}
	public String getFecha_cita() {
		return fecha_cita;
	}
	public Integer getId() {
		return id;
	}
	public String getNota() {
		return nota;
	}
	/*
	@SuppressWarnings("deprecation")
	public String getFechaString() {
		// Choose time zone in which you want to interpret your Date
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
		Date dummy=null;
		cal.setTime(dummy);
		String dia = ((Integer) dummy.getDate()).toString();
		int m = dummy.getMonth() + 1;
		String mes = ((Integer) m).toString();
		int year = cal.get(Calendar.YEAR);
		String anho = ((Integer) year).toString();
		String hora = ((Integer) dummy.getHours()).toString();
		String min = ((Integer) dummy.getMinutes()).toString();
		String f = hora + ":" + min + "  -  " + dia + "/" + mes + "/" + anho;
		return f;
	}
	*/
	// SETTERS
	public void setMedico(String medico) {
		this.medico = medico;
	}
	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}
	public void setFecha_cita(String fecha_cita) {
		this.fecha_cita = fecha_cita;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	
	
}
