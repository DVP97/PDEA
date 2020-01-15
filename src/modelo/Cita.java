package modelo;


import java.util.Calendar;
import java.util.Date;



public class Cita {

		private Date fecha_cita;
		private String nota;
		private String dni;
		
		public Cita(String dni, Date fecha_cita, String nota) {
			this.dni = dni;
			this.fecha_cita = fecha_cita;
			this.nota = nota;
			
		}
		
		public Cita () {
			this.dni = null;
			this.fecha_cita = new Date();;
			this.nota = null;
		}
		
	//GETTERS
		
		public Date getFecha() {
			return fecha_cita;
		}
		public String getDni() {
			return dni;
		}
		public String getNota() {
			return nota;
		}
		
	//SETTERS
		
		public void setFecha() {
			this.fecha_cita = Calendar.getInstance().getTime();
		}
		public void setDni(String dni) {
			this.dni = dni;
		}
		public void setNota(String nota) {
			this.nota = nota;
		}

}
