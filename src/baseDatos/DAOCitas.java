package baseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Cita;
import modelo.Paciente;

public class DAOCitas extends AbstractDAO{
	
	//private baseDatos.FachadaBaseDatos fbd = application.Main.getFbd();

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
				Paciente p = this.visualizarPaciente(rsCita.getString("paciente"));
				String nombre = p.getNombre() + " " + p.getApellidos();
				resultado = new Cita(rsCita.getInt("id"), rsCita.getString("fecha_cita"), rsCita.getString("nota"), rsCita.getString("paciente"), rsCita.getString("dni_medico"), nombre);
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
		
		String consulta = "insert into cita (id, fecha_cita, nota, paciente, medico)"
				+ " values (?,?,?,?,?)";
		
		try {
			stmCita = con.prepareStatement(consulta);
			stmCita.setInt(1, 0);
			stmCita.setString(2, cita.getFecha_cita());
			stmCita.setString(3, cita.getNota());
			stmCita.setString(4, cita.getPaciente());
			stmCita.setString(5, cita.getMedico());
			
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
			stmCita.setString(1, cita.getFecha_cita());
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
	
	public Paciente visualizarPaciente(String dni) {
		Paciente resultado = null;
		Connection con;
		PreparedStatement stmPaciente = null;
		ResultSet rsPaciente = null;

		con = super.getConexion();

		String consulta = "select * from paciente where dni_paciente = ?";

		try {
			stmPaciente = con.prepareStatement(consulta);
			stmPaciente.setString(1, dni);
			rsPaciente = stmPaciente.executeQuery();

			if (rsPaciente.next()) {
				
				resultado = new Paciente(rsPaciente.getString("dni_paciente"), rsPaciente.getString("nombre"),
						rsPaciente.getString("apellidos"), rsPaciente.getString("telefono"),
						rsPaciente.getString("contrasena"), rsPaciente.getString("fecha_nacimiento"),
						rsPaciente.getString("medico"), rsPaciente.getBoolean("ejerciciosHechos"));
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
		return resultado;
	}

}
