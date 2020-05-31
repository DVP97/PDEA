package baseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Aviso;
import modelo.Pulsiometro;
import modelo.Oximetro;
import modelo.Presion;

public class DAOSensores extends AbstractDAO{
		
	public DAOSensores(Connection conexion) {
		super.setConexion(conexion);
	}
	//SENSOR1 pulsiometro
	public ArrayList<Pulsiometro> getDatosSensor1De(String dni){
		ArrayList<Pulsiometro> s1 = new ArrayList<Pulsiometro>();
		Pulsiometro datoS1 = null;
		Connection con;
		PreparedStatement stmPaciente = null;
		ResultSet rsPaciente = null;

		con = super.getConexion();

		String consulta = "select * from pulsiometro where paciente = ?";
		
		try {
			stmPaciente = con.prepareStatement(consulta);
			stmPaciente.setString(1, dni);
			rsPaciente = stmPaciente.executeQuery();

			while (rsPaciente.next()) {
				datoS1 = new Pulsiometro(
						rsPaciente.getDate("fecha_dato"), 
						rsPaciente.getInt("dato"),
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
		ArrayList<Pulsiometro> sensor1 = getDatosSensor1De(dni);
		for (int i =0 ; i<sensor1.size();i++) {
			Pulsiometro s1 = sensor1.get(i);
			int a = s1.getDato().compareTo(50);
			int b = s1.getDato().compareTo(150);
			
			if(a<0) {
				Aviso aviso = new Aviso();
				aviso.setConcepto("La frecuencia cardiaca es demasiado baja.");
				aviso.setDatoSensor(s1);
				aviso.setNombreSensor("Pulsiometro");
				aviso.setNombrePaciente();
				avisos.add(aviso);
			}if(b>0) {
				Aviso aviso = new Aviso();
				aviso.setConcepto("La frecuencia cardiaca es demasiado alta.");
				aviso.setDatoSensor(s1);
				aviso.setNombrePaciente();

				aviso.setNombreSensor("Pulsiometro");

				avisos.add(aviso);
			}
		}
		return avisos;
	}
	
	//SENSOR2 OXIMETRO
	public ArrayList<Oximetro> getDatosSensor2De(String dni){
		ArrayList<Oximetro> s2 = new ArrayList<Oximetro>();
		Oximetro datoS2 = null;
		Connection con;
		PreparedStatement stmPaciente = null;
		ResultSet rsPaciente = null;

		con = super.getConexion();

		String consulta = "select * from oximetro where paciente = ?";
		
		try {
			stmPaciente = con.prepareStatement(consulta);
			stmPaciente.setString(1, dni);
			rsPaciente = stmPaciente.executeQuery();

			while (rsPaciente.next()) {
				datoS2 = new Oximetro(
						rsPaciente.getDate("fecha_dato"), 
						rsPaciente.getString("paciente"),
						rsPaciente.getInt("datoMedico"));
				s2.add(datoS2);
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
		return s2; 
	}
	
	public ArrayList<Aviso> crearAvisosSensor2(String dni){
		ArrayList<Aviso> avisos = new ArrayList<Aviso>();
		ArrayList<Oximetro> sensor2 = getDatosSensor2De(dni);
		for (int i =0 ; i<sensor2.size();i++) {
			Oximetro s2 = sensor2.get(i);
			int a = s2.getDatosMedicos().compareTo(85);
			if(a<0) {
				Aviso aviso = new Aviso();
				aviso.setConcepto("La saturacion de oxigeno en sangre es demasiado baja.");
				aviso.setDatoSensor(s2);
				aviso.setNombrePaciente();

				aviso.setNombreSensor("Oximetro");

				avisos.add(aviso);
			}
		}
		return avisos;
	}
	
	//SENSOR3 PRESION
	public ArrayList<Presion> getDatosSensor3De(String dni){
		ArrayList<Presion> s3 = new ArrayList<Presion>();
		Presion datoS3 = null;
		Connection con;
		PreparedStatement stmPaciente = null;
		ResultSet rsPaciente = null;

		con = super.getConexion();

		String consulta = "select * from presion where paciente = ?";
		
		try {
			stmPaciente = con.prepareStatement(consulta);
			stmPaciente.setString(1, dni);
			rsPaciente = stmPaciente.executeQuery();

			while (rsPaciente.next()) {
				datoS3 = new Presion(
						rsPaciente.getDate("fecha_dato"), 
						rsPaciente.getInt("dato"),
						rsPaciente.getString("paciente"));
				s3.add(datoS3);
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
		return s3; 
	}
	
	public  ArrayList<Aviso> crearAvisosSensor3(String dni){
		ArrayList<Aviso> avisos = new ArrayList<Aviso>();
		ArrayList<Presion> sensor3 = getDatosSensor3De(dni);
		for (int i =0 ; i<sensor3.size();i++) {
			Presion s3 = sensor3.get(i);
			int a = s3.getDato().compareTo(180);

			if(a>0) {
				Aviso aviso = new Aviso();
				aviso.setConcepto("Lleva mucho tiempo parado.");
				aviso.setDatoSensor(s3);
				aviso.setNombrePaciente();

				aviso.setNombreSensor("Sensor 3");

				avisos.add(aviso);
			}
		}
		return avisos;
	}
}