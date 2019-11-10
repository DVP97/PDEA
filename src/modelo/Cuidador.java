package modelo;

public final class Cuidador extends Usuario{
	public Cuidador(String dni, String nombre, String apellidos, Integer telefono, String contrasena) {
		super(dni, nombre, apellidos, telefono, contrasena);
	}
	
	public Cuidador() {
		super();
	}
}
