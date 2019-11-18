package modelo;

public final class Medico extends Usuario{
	public Medico(String dni, String nombre, String apellidos, Integer telefono, String contrasena, String[] pacientes) {
		super(dni, nombre, apellidos, telefono, contrasena, pacientes);
	}
	public Medico() {
		super();
	}
}
