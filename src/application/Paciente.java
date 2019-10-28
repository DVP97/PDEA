package application;

import java.util.Date;

public final class Paciente extends Usuario{
	private Date fecha_nacimiento;
	public Paciente(String dni, String nombre, Integer telefono, String contrasena, Date fecha_nacimiento) {
		super(dni, nombre, telefono, contrasena);
		this.fecha_nacimiento= fecha_nacimiento;
	}
	
	public Paciente () {
		super();
		this.fecha_nacimiento= null;
	}
	
	//GETTERS
	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}
	
	//SETTERS
	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento= fecha_nacimiento;
	}

	//METODOS


		
}

