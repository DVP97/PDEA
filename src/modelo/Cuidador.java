package modelo;

public final class Cuidador extends Usuario{
	public Cuidador(String dni, String nombre, String apellidos, Integer telefono, String contrasena, String[] pacientes) {
		super(dni, nombre, apellidos, telefono, contrasena, pacientes);
	}
	
	public Cuidador() {
		super();
	}
}
