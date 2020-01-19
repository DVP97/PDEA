package modelo;


import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;



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
		
		@SuppressWarnings("deprecation")
		public String getFechaString() {
			// Choose time zone in which you want to interpret your Date
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
			cal.setTime(fecha_cita);
			String dia = ((Integer) fecha_cita.getDate()).toString();
			int m = fecha_cita.getMonth() +1;
			String mes = ((Integer) m).toString();
			int year =  cal.get(Calendar.YEAR);
			String anho = ((Integer) year).toString();
			String hora = ((Integer) fecha_cita.getHours()).toString();
			String min = ((Integer) fecha_cita.getMinutes()).toString();
			String f = hora+ ":" + min + "\t-\t" +dia + "/"+ mes + "/"+ anho ;
			return f;
			
		}
	//SETTERS
		
		public void setFecha(Date calend) {
			this.fecha_cita = Calendar.getInstance().getTime();
		}
		public void setDni(String dni) {
			this.dni = dni;
		}
		public void setNota(String nota) {
			this.nota = nota;
		}

}
