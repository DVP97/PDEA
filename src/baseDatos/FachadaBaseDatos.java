package baseDatos;

import java.util.ArrayList;

import modelo.Cuidador;
import modelo.Ejercicio;
import modelo.Medico;
import modelo.Paciente;

public class FachadaBaseDatos {
	private java.sql.Connection conexion;
	private DAOPacientes daoPacientes;
	private DAOMedicos daoMedicos;

	// EN ESTA CLASE SE HACE LA CONEXION CON LA BBDD
	public FachadaBaseDatos() {
		daoPacientes = new DAOPacientes(conexion);
		daoMedicos = new DAOMedicos(conexion);
	}

	// PACIENTES
	public Paciente visualizarPaciente(String dni) {
		return daoPacientes.visualizarPaciente(dni);
	}

	public void insertarPaciente(Paciente paciente) {
		daoPacientes.insertarPaciente(paciente);
	}

	public void modificarPaciente(Paciente paciente) {
		daoPacientes.modificarPaciente(paciente);
	}

	public void borrarPaciente(Paciente paciente) {
		daoPacientes.borrarPaciente(paciente);
	}

	public ArrayList<Cuidador> obtenerCuidadores(Paciente paciente) {
		return daoPacientes.obtenerCuidadores(paciente);
	}
	
	public ArrayList<Ejercicio> obtenerEjercicios (Paciente paciente){
		return daoPacientes.obtenerEjercicios(paciente);
	}

	// MEDICOS
	public Medico visualizarMedico(String dni) {
		return daoMedicos.visualizarMedico(dni);
	}

	public void insertarMedico(Medico medico) {
		daoMedicos.insertarMedico(medico);
	}
	
	public void modificarMedico (Medico medico) {
		daoMedicos.modificarMedico(medico);
	}
	
	public void borrarMedico(Medico medico) {
		daoMedicos.borrarMedico(medico);
	}
	
	public ArrayList<Paciente> obtenerPacientesMedico(Medico medico){
		return daoMedicos.obtenerPacientesMedico(medico);
	}
	
}
