package baseDatos;

import java.util.ArrayList;

import modelo.Cuidador;
import modelo.Paciente;

public class FachadaBaseDatos {
	private java.sql.Connection conexion;
	private DAOPacientes daoPacientes;

	//EN ESTA CLASE SE HACE LA CONEXION CON LA BBDD
	public FachadaBaseDatos() {		
        daoPacientes = new DAOPacientes(conexion);
	}
	
	//PACIENTES
	public Paciente visualizarPaciente (String dni) {
		return daoPacientes.visualizarPaciente(dni);
	}
	public void insertarPaciente (Paciente paciente) {
		daoPacientes.insertarPaciente(paciente);
	}
	public void modificarPaciente (Paciente paciente) {
		daoPacientes.modificarPaciente(paciente);
	}
	public void borrarPaciente (Paciente paciente) {
		daoPacientes.borrarPaciente(paciente);
	}
	public ArrayList<Cuidador> obtenerCuidadores (Paciente paciente){
		return daoPacientes.obtenerCuidadores(paciente);
	}
}
