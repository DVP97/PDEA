package modelo;

import java.util.ArrayList;

public final class Cuidador extends Usuario{
	ArrayList<Paciente> pacientes;
	public Cuidador(String dni, String nombre, String apellidos, Integer telefono, String contrasena, ArrayList<Paciente> pacientes) {
		super(dni, nombre, apellidos, telefono, contrasena);
		this.pacientes = pacientes;
	}
	
	public Cuidador() {
		super();
		this.pacientes = null;
	}
}
