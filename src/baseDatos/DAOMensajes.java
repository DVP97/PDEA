package baseDatos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Medico;
import modelo.Mensaje;
import modelo.Paciente;


public class DAOMensajes extends AbstractDAO {
	
	public DAOMensajes(Connection conexion) {
		super.setConexion(conexion);
	}
	
	public ArrayList<Mensaje> obtenerMensajesRecibidos (Medico medico){
		ArrayList<Mensaje> resultado = new ArrayList<Mensaje>();
		Mensaje mensajeActual = null;
		Connection con;
		PreparedStatement stmMedico = null;
		ResultSet rsMensajes;
		
		con = super.getConexion();
		
		String consulta = "select * from mensaje where dni_medico = ? and esMedicoEmisor = 'false' order by fecha desc";
		
		try {
			stmMedico = con.prepareStatement(consulta);
			stmMedico.setString(1, medico.getDni());
			rsMensajes = stmMedico.executeQuery();
			
			while (rsMensajes.next()) {
				mensajeActual = new Mensaje(rsMensajes.getInt("id_mensaje"), rsMensajes.getString("dni_medico"), rsMensajes.getString("dni_paciente"), rsMensajes.getBoolean("esMedicoEmisor"), rsMensajes.getString("asunto"), rsMensajes.getString("mensaje"), rsMensajes.getString("fecha"));
				resultado.add(mensajeActual);
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				stmMedico.close();
			} catch (SQLException e) {
				System.out.println("Imposible cerrar cursores");
			}
		}
		return resultado;
	}
	
	public ArrayList<Mensaje> obtenerMensajesEnviados (Medico medico){
		ArrayList<Mensaje> resultado = new ArrayList<Mensaje>();
		Mensaje mensajeActual = null;
		Connection con;
		PreparedStatement stmMedico = null;
		ResultSet rsMensajes;
		
		con = super.getConexion();
		
		String consulta = "select * from mensaje where dni_medico = ? and esMedicoEmisor = 'true' order by fecha desc";
		
		try {
			stmMedico = con.prepareStatement(consulta);
			stmMedico.setString(1, medico.getDni());
			rsMensajes = stmMedico.executeQuery();
			
			while (rsMensajes.next()) {
				mensajeActual = new Mensaje(rsMensajes.getInt("id_mensaje"), rsMensajes.getString("dni_medico"), rsMensajes.getString("dni_paciente"), rsMensajes.getBoolean("esMedicoEmisor"), rsMensajes.getString("asunto"), rsMensajes.getString("mensaje"), rsMensajes.getString("fecha"));
				resultado.add(mensajeActual);
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				stmMedico.close();
			} catch (SQLException e) {
				System.out.println("Imposible cerrar cursores");
			}
		}
		return resultado;
	}
	
	public ArrayList<Mensaje> obtenerMensajesRecibidos (Paciente paciente){
		ArrayList<Mensaje> resultado = new ArrayList<Mensaje>();
		Mensaje mensajeActual = null;
		Connection con;
		PreparedStatement stmPaciente = null;
		ResultSet rsMensajes;
		
		con = super.getConexion();
		
		String consulta = "select * from mensaje where dni_paciente = ? and esMedicoEmisor = 'true' order by fecha desc";
		
		try {
			stmPaciente = con.prepareStatement(consulta);
			stmPaciente.setString(1, paciente.getDni());
			rsMensajes = stmPaciente.executeQuery();
			
			while (rsMensajes.next()) {
				mensajeActual = new Mensaje(rsMensajes.getInt("id_mensaje"), rsMensajes.getString("dni_medico"), rsMensajes.getString("dni_paciente"), rsMensajes.getBoolean("esMedicoEmisor"), rsMensajes.getString("asunto"), rsMensajes.getString("mensaje"), rsMensajes.getString("fecha"));
				resultado.add(mensajeActual);
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				stmPaciente.close();
			} catch (SQLException e) {
				System.out.println("Imposible cerrar cursores");
			}
		}
		return resultado;
	}
	
	public ArrayList<Mensaje> obtenerMensajesEnviados (Paciente paciente){
		ArrayList<Mensaje> resultado = new ArrayList<Mensaje>();
		Mensaje mensajeActual = null;
		Connection con;
		PreparedStatement stmPaciente = null;
		ResultSet rsMensajes;
		
		con = super.getConexion();
		
		String consulta = "select * from mensaje where dni_paciente = ? and esMedicoEmisor = 'false' order by fecha desc";
		
		try {
			stmPaciente = con.prepareStatement(consulta);
			stmPaciente.setString(1, paciente.getDni());
			rsMensajes = stmPaciente.executeQuery();
			
			while (rsMensajes.next()) {
				mensajeActual = new Mensaje(rsMensajes.getInt("id_mensaje"), rsMensajes.getString("dni_medico"), rsMensajes.getString("dni_paciente"), rsMensajes.getBoolean("esMedicoEmisor"), rsMensajes.getString("asunto"), rsMensajes.getString("mensaje"), rsMensajes.getString("fecha"));
				resultado.add(mensajeActual);
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				stmPaciente.close();
			} catch (SQLException e) {
				System.out.println("Imposible cerrar cursores");
			}
		}
		return resultado;
	}

	public void enviarMensaje(Mensaje mensaje) {
		Connection con;
		PreparedStatement stmMensaje = null;
		
		con = super.getConexion();
		
		String consulta = "insert into mensaje (dni_medico, dni_paciente, esMedicoEmisor, asunto, mensaje, fecha) "
				+ "values (?,?,?,?,?,?)";
		try {
			stmMensaje= con.prepareStatement(consulta);
			stmMensaje.setString(1, mensaje.getDni_medico());
			stmMensaje.setString(2, mensaje.getDni_paciente());
			stmMensaje.setBoolean(3, mensaje.isEsMedicoEmisor());
			stmMensaje.setString(4, mensaje.getAsunto());
			stmMensaje.setString(5, mensaje.getMensaje());
			stmMensaje.setString(6, mensaje.getFechaString());
			
			con.setAutoCommit(true);
			stmMensaje.executeUpdate();
			con.setAutoCommit(false);
			
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				stmMensaje.close();
			} catch (SQLException e) {
				System.out.println("Imposible cerrar cursores");
			}
		}
	}

}
