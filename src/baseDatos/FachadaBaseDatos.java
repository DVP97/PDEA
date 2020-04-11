package baseDatos;

import java.util.ArrayList;

import modelo.Cita;
import modelo.Cuidador;
import modelo.Ejercicio;
import modelo.Medico;
import modelo.Paciente;

public class FachadaBaseDatos {
	private java.sql.Connection conexion;
	private DAOCitas daoCitas;
	private DAOCuidadores daoCuidadores;
	private DAOEjercicios daoEjercicios;
	private DAOMedicos daoMedicos;
	private DAOPacientes daoPacientes;


	// EN ESTA CLASE SE HACE LA CONEXION CON LA BBDD
	public FachadaBaseDatos() {
		daoCitas = new DAOCitas(conexion);
		daoCuidadores = new DAOCuidadores(conexion);
		daoEjercicios = new DAOEjercicios(conexion);
		daoMedicos = new DAOMedicos(conexion);
		daoPacientes = new DAOPacientes(conexion);
	}
	
	//CITAS
	public Cita visualizarCita(Integer id) {
		return daoCitas.visualizarCita(id);
	}
	
	public void insertarCita(Cita cita) {
		daoCitas.insertarCita(cita);
	}
	
	public void modificarCita (Cita cita) {
		daoCitas.modificarCita(cita);
	}
	
	public void borrarCita (Cita cita) {
		daoCitas.borrarCita(cita);
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
	
	//EJERCICIOS
	public Ejercicio visualizarEjercicio(Integer id) {
		return daoEjercicios.visualizarEjercicio(id);
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
	
	public ArrayList<Cita> obtenerCitasMedico (Medico medico){
		return daoMedicos.obtenerCitasMedico(medico);
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
	
	public ArrayList<Cita> obtenerCitasPaciente (Paciente paciente){
		return daoPacientes.obtenerCitasPaciente(paciente);
	}

}
