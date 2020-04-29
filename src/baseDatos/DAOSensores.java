package baseDatos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Aviso;
import modelo.Cita;
import modelo.Cuidador;
import modelo.Ejercicio;
import modelo.Paciente;
import modelo.datoSensor1;
import modelo.Medico;
import modelo.Mensaje;
import modelo.datoSensor2;
import modelo.datoSensor3;

public class DAOSensores extends AbstractDAO{
	
	private baseDatos.FachadaBaseDatos fbd = application.Main.getFbd();
	
	public DAOSensores(Connection conexion) {
		super.setConexion(conexion);
	}

	public datoSensor1 visualizarDatosSensor1(String dni) {
		datoSensor1 datoS1 = null;
		Connection con;
		PreparedStatement stmPaciente = null;
		ResultSet rsPaciente = null;

		con = super.getConexion();

		String consulta = "select * from sensor1 where paciente = ?";
		
		try {
			stmPaciente = con.prepareStatement(consulta);
			stmPaciente.setString(1, dni);
			rsPaciente = stmPaciente.executeQuery();

			if (rsPaciente.next()) {
				datoS1 = new datoSensor1(
						rsPaciente.getDate("fecha_dato"), 
						rsPaciente.getInt("frecuenciaAntes"),
						rsPaciente.getInt("frecuenciaDespues"), 
						rsPaciente.getString("paciente"));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				stmPaciente.close();
			} catch (SQLException e) {
				System.out.println("Imposible cerrar cursores");
			}
		}
		
		return datoS1; 
	}
	
	public ArrayList<datoSensor1> getDatosSensor1De(String dni){
		ArrayList<datoSensor1> s1 = new ArrayList<datoSensor1>();
		datoSensor1 datoS1 = null;
		Connection con;
		PreparedStatement stmPaciente = null;
		ResultSet rsPaciente = null;

		con = super.getConexion();

		String consulta = "select * from sensor1 where paciente = ?";
		
		try {
			stmPaciente = con.prepareStatement(consulta);
			stmPaciente.setString(1, dni);
			rsPaciente = stmPaciente.executeQuery();

			if (rsPaciente.next()) {
				datoS1 = new datoSensor1(
						rsPaciente.getDate("fecha_dato"), 
						rsPaciente.getInt("frecuenciaAntes"),
						rsPaciente.getInt("frecuenciaDespues"), 
						rsPaciente.getString("paciente"));
				s1.add(datoS1);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				stmPaciente.close();
			} catch (SQLException e) {
				System.out.println("Imposible cerrar cursores");
			}
		}
		return s1; 
	}
	
	public ArrayList<Aviso> crearAvisosSensor1(String dni){
		ArrayList<Aviso> avisos = new ArrayList<Aviso>();
		ArrayList<datoSensor1> sensor1 = getDatosSensor1De(dni);
		for (int i =0 ; i<sensor1.size();i++) {
			datoSensor1 s1 = sensor1.get(i);
			int a = s1.getFrecuenciaAntes().compareTo(50);
			int b = s1.getFrecuenciaAntes().compareTo(100);
			int c = s1.getFrecuenciaDespues().compareTo(75);
			int d = s1.getFrecuenciaDespues().compareTo(136);
			if(a<0) {
				Aviso aviso = new Aviso();
				aviso.setConcepto("La frecuencia cardiaca antes de hacer el ejercicio es demasiado baja.");
				aviso.setDatoSensor(s1);
				aviso.setNombreSensor("Sensor 1");
				aviso.setNombrePaciente();
				avisos.add(aviso);
			}if(b>0) {
				Aviso aviso = new Aviso();
				aviso.setConcepto("La frecuencia cardiaca antes de hacer el ejercicio es demasiado alta.");
				aviso.setDatoSensor(s1);
				aviso.setNombrePaciente();

				aviso.setNombreSensor("Sensor 1");

				avisos.add(aviso);
			}if(c<0) {
				Aviso aviso = new Aviso();
				aviso.setConcepto("La frecuencia cardiaca despues de hacer el ejercicio es demasiado baja.");
				aviso.setDatoSensor(s1);
				aviso.setNombrePaciente();

				aviso.setNombreSensor("Sensor 1");

				avisos.add(aviso);
			}
			if(d<0) {
				Aviso aviso = new Aviso();
				aviso.setConcepto("La frecuencia cardiaca despues de hacer el ejercicio es demasiado alta.");
				aviso.setDatoSensor(s1);
				aviso.setNombrePaciente();

				aviso.setNombreSensor("Sensor 1");

				avisos.add(aviso);
			}
		}
		return avisos;
	}
}