package baseDatos;

import java.sql.DriverManager;
import java.util.ArrayList;

import modelo.Aviso;
import modelo.Cita;
import modelo.Cuidador;
import modelo.Ejercicio;
import modelo.Medico;
import modelo.Mensaje;
import modelo.Paciente;

public class FachadaBaseDatos {
	private java.sql.Connection conexion;
	private DAOCitas daoCitas;
	private DAOCuidadores daoCuidadores;
	private DAOEjercicios daoEjercicios;
	private DAOMedicos daoMedicos;
	private DAOMensajes daoMensajes;
	private DAOPacientes daoPacientes;
	private DAOSensores daoSensores;


	// EN ESTA CLASE SE HACE LA CONEXION CON LA BBDD
	public FachadaBaseDatos() {
		//conexion a BBDD remota
		boolean acceso = false;
		try {
			String USER = "pr_pdea";
		    String PASS = "mochila";
		    
			Class.forName("org.mariadb.jdbc.Driver");

            conexion = DriverManager.getConnection("jdbc:mariadb://2.139.176.212/pr_pdea", USER, PASS);
            daoCitas = new DAOCitas(conexion);
			daoCuidadores = new DAOCuidadores(conexion);
			daoEjercicios = new DAOEjercicios(conexion);
			daoMedicos = new DAOMedicos(conexion);
			daoMensajes = new DAOMensajes(conexion);
			daoPacientes = new DAOPacientes(conexion);	
			daoSensores = new DAOSensores(conexion);
			
			System.out.println("Conexion con BBDD remota establecida");
			acceso =true;
		}
		catch(Exception e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		if(acceso==false) {
			//conexion a BBDD local
			try {
				Class.forName("org.sqlite.JDBC");
				conexion = DriverManager.getConnection("jdbc:sqlite:PDEAnewBBDD.db");
			
				daoCitas = new DAOCitas(conexion);
				daoCuidadores = new DAOCuidadores(conexion);
				daoEjercicios = new DAOEjercicios(conexion);
				daoMedicos = new DAOMedicos(conexion);
				daoMensajes = new DAOMensajes(conexion);
				daoPacientes = new DAOPacientes(conexion);
				
				System.out.println("Conexion con BBDD local");
			}catch ( Exception e ) {
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			}
		}
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

	public void modificarCuidador(Cuidador cuidador) {
		daoCuidadores.modificarCuidador(cuidador);
	}

	public void borrarCuidador(Cuidador cuidador) {
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

	//MENSAJES
	public ArrayList<Mensaje> obtenerMensajesRecibidos (Medico medico){
		return daoMensajes.obtenerMensajesRecibidos(medico);
	}
	
	public ArrayList<Mensaje> obtenerMensajesEnviados (Medico medico){
		return daoMensajes.obtenerMensajesEnviados(medico);
	}
	
	public ArrayList<Mensaje> obtenerMensajesRecibidos (Paciente paciente){
		return daoMensajes.obtenerMensajesRecibidos(paciente);
	}
	
	public ArrayList<Mensaje> obtenerMensajesEnviados (Paciente paciente){
		return daoMensajes.obtenerMensajesEnviados(paciente);
	}
	
	public void enviarMensaje (Mensaje mensaje) {
		daoMensajes.enviarMensaje(mensaje);
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

	public ArrayList<Ejercicio> obtenerEjerciciosPaciente(Paciente paciente) {
		return daoPacientes.obtenerEjerciciosPaciente(paciente);
	}
	
	public ArrayList<Cita> obtenerCitasPaciente (Paciente paciente){
		return daoPacientes.obtenerCitasPaciente(paciente);
	}
	
	public void asignarEjercicioPaciente (Paciente paciente, Ejercicio ejercicio) {
		daoPacientes.asignarEjercicioPaciente(paciente, ejercicio);
	}
	
	public void asignarCuidadorPaciente (Paciente paciente, Cuidador cuidador) {
		daoPacientes.asignarCuidadorPaciente(paciente, cuidador);
	}
	
	public void modificarDuracionEjercicioPaciente (Paciente paciente, Ejercicio ejercicio) {
		daoPacientes.modificarDuracionEjercicioPaciente(paciente, ejercicio);
	}
	
	public void borrarEjercicioPaciente (Paciente paciente, Ejercicio ejercicio) {
		daoPacientes.borrarEjercicioPaciente(paciente, ejercicio);
	}
	
	//SENSORES
	public void getDatosSensor1De(String dni) {
		daoSensores.getDatosSensor1De(dni);
	}
	public ArrayList<Aviso> crearAvisosSensor1(String dni) {
		return daoSensores.crearAvisosSensor1(dni);
	}
	
	public void getDatosSensor2De(String dni) {
		daoSensores.getDatosSensor2De(dni);
	}
	public ArrayList<Aviso> crearAvisosSensor2(String dni) {
		return daoSensores.crearAvisosSensor2(dni);
	}
	
	public void getDatosSensor3De(String dni) {
		daoSensores.getDatosSensor3De(dni);
	}
	public ArrayList<Aviso> crearAvisosSensor3(String dni) {
		return daoSensores.crearAvisosSensor3(dni);
	}
}
