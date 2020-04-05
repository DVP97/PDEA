package modelo;


public final class Medico extends Usuario{
	
	public Medico(String dni, String nombre, String apellidos, String telefono, String contrasena) {
		super(dni, nombre, apellidos, telefono, contrasena);
	}
	public Medico() {
		super();
	}
	
	//GETTERS
	public String getNombreCompleto() {
		String nombreCompleto =getNombre() + " " + getApellidos();
		return nombreCompleto;
	}
}
