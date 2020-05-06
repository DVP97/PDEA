package modelo;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class Mensaje {
	private Integer id_mensaje;
	private String asunto;
	private String dni_paciente;
	private String dni_medico;
	private String mensaje;
	private boolean esMedicoEmisor;
	private String fecha;

	public Mensaje(Integer id_mensaje, String dni_medico, String dni_paciente, boolean esMedicoEmisor, String asunto,
			String mensaje) {
		this.id_mensaje = id_mensaje;
		this.dni_medico = dni_medico;
		this.dni_paciente = dni_paciente;
		this.esMedicoEmisor = false;
		this.asunto = asunto;
		this.mensaje = mensaje;
		this.fecha = getFechaString(Calendar.getInstance().getTime());
	}
	
	public Mensaje(Integer id_mensaje, String dni_medico, String dni_paciente, boolean esMedicoEmisor, String asunto,
			String mensaje, String fecha) {
		this.id_mensaje = id_mensaje;
		this.dni_medico = dni_medico;
		this.dni_paciente = dni_paciente;
		this.esMedicoEmisor = false;
		this.asunto = asunto;
		this.mensaje = mensaje;
		this.fecha = fecha;
	}

	public Mensaje(String dni_medico, String dni_paciente, boolean esMedicoEmisor, String asunto,
			String mensaje, String fecha) {
		this.dni_medico = dni_medico;
		this.dni_paciente = dni_paciente;
		this.esMedicoEmisor = false;
		this.asunto = asunto;
		this.mensaje = mensaje;
		this.fecha = getFechaString(Calendar.getInstance().getTime());
	}
	
	public Mensaje() {
		this.asunto = null;
		this.dni_medico = null;
		this.dni_paciente = null;
		this.esMedicoEmisor = false;
		this.asunto = null;
		this.mensaje = null;
		this.fecha = null;
	}

	// GETTERS
	public String getAsunto() {
		return asunto;
	}
	public String getDni_medico() {
		return dni_medico;
	}
	public String getDni_paciente() {
		return dni_paciente;
	}
	public String getFecha() {
		return fecha;
	}
	public Integer getId_mensaje() {
		return id_mensaje;
	}
	public String getMensaje() {
		return mensaje;
	}
	public boolean isEsMedicoEmisor() {
		return esMedicoEmisor;
	}

	
	// SETTERS
	public void setDni_medico(String dni_medico) {
		this.dni_medico = dni_medico;
	}
	public void setDni_paciente(String dni_paciente) {
		this.dni_paciente = dni_paciente;
	}
	public void setEsMedicoEmisor(boolean esMedicoEmisor) {
		this.esMedicoEmisor = esMedicoEmisor;
	}
	public void setId_mensaje(Integer id_mensaje) {
		this.id_mensaje = id_mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public void setFecha() {
		this.fecha = getFechaString(Calendar.getInstance().getTime());
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	
    public String getFechaString(Date dummy) {
		// Choose time zone in which you want to interpret your Date
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));

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

}