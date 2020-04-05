package baseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Medico;
import modelo.Paciente;

public class DAOMedicos extends AbstractDAO {

	public DAOMedicos(Connection conexion) {
		super.setConexion(conexion);
	}

	public Medico visualizarMedico(String dni) {
		Medico resultado = null;
		Connection con;
		PreparedStatement stmMedico = null;
		ResultSet rsMedico = null;

		con = super.getConexion();

		String consulta = "select * from medico where dni_medico = ?";

		try {
			stmMedico = con.prepareStatement(consulta);
			stmMedico.setString(1, dni);
			rsMedico = stmMedico.executeQuery();

			if (rsMedico.next()) {
				resultado = new Medico(rsMedico.getString("dni_medico"), rsMedico.getString("nombre"),
						rsMedico.getString("apellidos"), rsMedico.getString("telefono"),
						rsMedico.getString("contrasena"));

			}

		} catch (SQLException e) {
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

	public void insertarMedico(Medico medico) {
		Connection con;
		PreparedStatement stmMedico = null;

		con = super.getConexion();

		String consulta = "insert into medico (dni_medico, nombre, apellidos, telefono, contrasena)"
				+ " values (?,?,?,?,?)";
		try {
			stmMedico = con.prepareStatement(consulta);
			stmMedico.setString(1, medico.getDni());
			stmMedico.setString(2, medico.getNombre());
			stmMedico.setString(3, medico.getApellidos());
			stmMedico.setString(4, medico.getTelefono());
			stmMedico.setString(5, medico.getContrasena());

			con.setAutoCommit(true);
			stmMedico.executeUpdate();
			con.setAutoCommit(false);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				stmMedico.close();
			} catch (SQLException e) {
				System.out.println("Imposible cerrar cursores");
			}
		}

	}

	public void modificarMedico(Medico medico) {
		Connection con;
		PreparedStatement stmMedico = null;

		con = super.getConexion();

		String consulta = "update medico set nombre = ?, apellidos= ?, telefono = ?, contrasena= ? "
				+ "where dni_medico = ?";

		try {
			stmMedico = con.prepareStatement(consulta);
			stmMedico.setString(1, medico.getNombre());
			stmMedico.setString(2, medico.getApellidos());
			stmMedico.setString(3, medico.getTelefono());
			stmMedico.setString(4, medico.getContrasena());
			stmMedico.setString(5, medico.getDni());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				stmMedico.close();
			} catch (SQLException e) {
				System.out.println("Imposible cerrar cursores");
			}
		}
	}

	public void borrarMedico(Medico medico) {
		Connection con;
		PreparedStatement stmMedico = null;

		con = super.getConexion();

		String consulta = "delete from medico where dni_medico = ?";
		try {
			stmMedico = con.prepareStatement(consulta);
			stmMedico.setString(1, medico.getDni());

			con.setAutoCommit(true);
			stmMedico.executeUpdate();
			con.setAutoCommit(false);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				stmMedico.close();
			} catch (SQLException e) {
				System.out.println("Imposible cerrar cursores");
			}
		}
	}

	public ArrayList<Paciente> obtenerPacientesMedico(Medico medico) {
		ArrayList<Paciente> resultado = new ArrayList<Paciente>();
		Paciente pacienteActual = null;
		Connection con;
		PreparedStatement stmMedico = null;
		ResultSet rsPacientes;

		con = super.getConexion();

		String consulta = "select m.* " + "from paciente as p right join medico as m on p.medico = m.dni_medico "
				+ "where m.dni_medico = ?";

		try {
			stmMedico = con.prepareStatement(consulta);
			stmMedico.setString(1, medico.getDni());
			rsPacientes =stmMedico.executeQuery();
			
			while (rsPacientes.next()) {
				pacienteActual = new Paciente(rsPacientes.getString("dni_paciente"), rsPacientes.getString("nombre"), rsPacientes.getString("apellidos"), rsPacientes.getString("telefono"), rsPacientes.getString("contrasena"), rsPacientes.getDate("fecha_nacimiento"), rsPacientes.getString("medico"), rsPacientes.getBoolean("ejerciciosHechos"));
				resultado.add(pacienteActual);
			}
		} catch (SQLException e) {
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

}
