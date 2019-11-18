package modelo;

import java.util.ArrayList;



public final class Medico extends Usuario{
	
	ArrayList<Paciente> pacientes; 
	public Medico(String dni, String nombre, String apellidos, Integer telefono, String contrasena, ArrayList<Paciente> pacientes) {
		super(dni, nombre, apellidos, telefono, contrasena);
		this.pacientes = pacientes;
	}
	public Medico() {
		super();
		this.pacientes = null;
	}
}
