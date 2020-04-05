package modelo;


public final class Cuidador extends Usuario{
	public Cuidador(String dni, String nombre, String apellidos, String telefono, String contrasena) {
		super(dni, nombre, apellidos, telefono, contrasena);
	}
	
	public Cuidador() {
		super();
	}
	
	//GETTERS
	public String getNombreCompleto() {
		String nombreCompleto =getNombre() + " " + getApellidos();
		return nombreCompleto;
	}
	
	//SETTERS

}
