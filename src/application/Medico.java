package application;

public final class Medico extends Usuario{
	public Medico(String dni, String nombre, String apellidos, Integer telefono, String contrasena) {
		super(dni, nombre, apellidos, telefono, contrasena);
	}
	public Medico() {
		super();
	}
}
