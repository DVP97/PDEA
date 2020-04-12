package baseDatos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Cita;

public class DAOCitas extends AbstractDAO{
	
	public DAOCitas (Connection conexion) {
		super.setConexion(conexion);
	}
	
	public Cita visualizarCita (Integer id) {
		Cita resultado= null;
		Connection con;
		PreparedStatement stmCita = null;
		ResultSet rsCita = null;
		
		con = super.getConexion();
		
		String consulta = "select * from cita where id = ? ";
		
		try {
			stmCita = con.prepareStatement(consulta);
			stmCita.setInt(1, id);
			rsCita = stmCita.executeQuery();
			
			if (rsCita.next()) {
				resultado = new Cita(rsCita.getInt("id"), rsCita.getDate("fecha_cita"), rsCita.getString("nota"), rsCita.getString("dni_paciente"), rsCita.getString("dni_medico"));
			}
			
		}catch (SQLException e) {
				System.out.println(e.getMessage());
			} finally {
				try {
					stmCita.close();
				} catch (SQLException e) {
					System.out.println("Imposible cerrar cursores");
				}
			}
			return resultado;
	}
	
	public void insertarCita (Cita cita) {
		Connection con;
		PreparedStatement stmCita = null;
		
		con = super.getConexion();
		
		String consulta = "insert into cita (fecha_cita, nota, paciente, medico)"
				+ " values (?,?,?,?)";
		
		try {
			stmCita = con.prepareStatement(consulta);
			stmCita.setDate(1, (Date) cita.getFecha_cita());
			stmCita.setString(2, cita.getNota());
			stmCita.setString(3, cita.getPaciente());
			stmCita.setString(4, cita.getMedico());
			
			con.setAutoCommit(true);
			stmCita.executeUpdate();
			con.setAutoCommit(false);
			
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				stmCita.close();
			} catch (SQLException e) {
				System.out.println("Imposible cerrar cursores");
			}
		}
	}

	public void modificarCita (Cita cita) {
		Connection con;
		PreparedStatement stmCita = null;
		
		con = super.getConexion();
		
		String consulta = "update cita set fecha_cita = ?, nota = ? "
				+"where id = ?";
		
		try {
			stmCita = con.prepareStatement(consulta);
			stmCita.setDate(1, (Date) cita.getFecha_cita());
			stmCita.setString(2, cita.getNota());
			stmCita.setInt(3, cita.getId());
			
			con.setAutoCommit(true);
			stmCita.executeUpdate();
			con.setAutoCommit(false);
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				stmCita.close();
			} catch (SQLException e) {
				System.out.println("Imposible cerrar cursores");
			}
		}
	}

	public void borrarCita (Cita cita) {
		Connection con;
		PreparedStatement stmCita = null;
		
		con = super.getConexion();
		
		String consulta = "delte from cita where id = ?";
		
		try {
			stmCita= con.prepareStatement(consulta);
			stmCita.setInt(1, cita.getId());
			
			con.setAutoCommit(true);
			stmCita.executeUpdate();
			con.setAutoCommit(false);
			
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				stmCita.close();
			} catch (SQLException e) {
				System.out.println("Imposible cerrar cursores");
			}
		}
	}

}
