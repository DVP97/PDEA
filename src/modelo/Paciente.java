package modelo;

public final class Paciente extends Usuario{
	private String fecha_nacimiento;
	String medico;
	boolean ejerciciosHechos;
	String cuandoHechos;

	public Paciente(String dni, String nombre, String apellidos, String telefono, String contrasena, String fecha_nacimiento, String medico, boolean ejerciciosHechos) {
		super(dni, nombre, apellidos, telefono, contrasena);
		this.fecha_nacimiento= fecha_nacimiento;
		this.medico = medico;
		this.ejerciciosHechos = ejerciciosHechos;
	}
	
	public Paciente(String dni, String nombre, String apellidos, String telefono, String contrasena, String fecha_nacimiento, String medico, boolean ejerciciosHechos, String cuandoHechos) {
		super(dni, nombre, apellidos, telefono, contrasena);
		this.fecha_nacimiento= fecha_nacimiento;
		this.medico = medico;
		this.ejerciciosHechos = ejerciciosHechos;
		this.cuandoHechos = cuandoHechos;
	}
	
	public Paciente () {
		super();
		this.fecha_nacimiento= null;
		this.medico = null;
		this.ejerciciosHechos=false;
	}
	
	//GETTERS
	/*@SuppressWarnings("deprecation")
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
		
	}*/
	
	public String getFecha_nacimiento() {
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
	
	public String getCuandoHechos() {
		return cuandoHechos;
	}
	
	//SETTERS
	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento= fecha_nacimiento;
	}

	public void setMedico (String medico) {
		this.medico = medico;
	}
	
	public void setEjerciciosHechos(boolean ejerciciosHechos) {
		this.ejerciciosHechos = ejerciciosHechos;
	}
	
	public void setCuandoHechos(String cuandoHechos) {
		this.cuandoHechos = cuandoHechos;
	}
	
	//METODOS


		
}

