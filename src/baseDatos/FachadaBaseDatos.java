package baseDatos;

import java.util.ArrayList;

import modelo.Cuidador;
import modelo.Ejercicio;
import modelo.Medico;
import modelo.Paciente;

public class FachadaBaseDatos {
	private java.sql.Connection conexion;
	private DAOCuidadores daoCuidadores;
	private DAOMedicos daoMedicos;
	private DAOPacientes daoPacientes;


	// EN ESTA CLASE SE HACE LA CONEXION CON LA BBDD
	public FachadaBaseDatos() {
		daoCuidadores = new DAOCuidadores(conexion);
		daoMedicos = new DAOMedicos(conexion);
		daoPacientes = new DAOPacientes(conexion);
	}
	
	//CUIDADORES
	public Cuidador visualizarCuidador(String dni) {
		return daoCuidadores.visualizarCuidador(dni);
	}

	public void insertarCuidador(Cuidador cuidador) {
		daoCuidadores.insertarCuidador(cuidador);
	}

	public void modificarMedico(Cuidador cuidador) {
		daoCuidadores.modificarCuidador(cuidador);
	}

	public void borrarMedico(Cuidador cuidador) {
		daoCuidadores.borrarCuidador(cuidador);
	}

	public ArrayList<Paciente> obtenerPacientesCuidador (Cuidador cuidador){
		return daoCuidadores.obtenerPacientesCuidador(cuidador);
	}
	
	// MEDICOS
	public Medico visualizarMedico(String dni) {
		return daoMedicos.visualizarMedico(dni);
	}

	public void insertarMedico(Medico medico) {
		daoMedicos.insertarMedico(medico);
	}

	public void modificarMedico(Medico medico) {
		daoMedicos.modificarMedico(medico);
	}

	public void borrarMedico(Medico medico) {
		daoMedicos.borrarMedico(medico);
	}

	public ArrayList<Paciente> obtenerPacientesMedico(Medico medico) {
		return daoMedicos.obtenerPacientesMedico(medico);
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

	public ArrayList<Cuidador> obtenerCuidadoresPaciente(Paciente paciente) {
		return daoPacientes.obtenerCuidadoresPaciente(paciente);
	}

	public ArrayList<Ejercicio> obtenerEjercicios(Paciente paciente) {
		return daoPacientes.obtenerEjercicios(paciente);
	}

}
