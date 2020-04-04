package baseDatos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Paciente;

public class DAOPacientes extends AbstractDAO {
	public DAOPacientes(Connection conexion) {
		super.setConexion(conexion);
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
						rsPaciente.getString("contrasena"), rsPaciente.getDate("fecha_nacimiento"),
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

	public void insertarPaciente(Paciente paciente) {
		Connection con;
		PreparedStatement stmPaciente = null;

		con = super.getConexion();

		String consulta = "insert into paciente (dni_paciente, nombre, apellidos, telefono, contraseña, fecha_nacimiento, medico, ejerciciosHechos)"
				+ " values (?,?,?,?,?,?,?,?)";

		try {
			stmPaciente = con.prepareStatement(consulta);
			stmPaciente.setString(1, paciente.getDni());
			stmPaciente.setString(2, paciente.getNombre());
			stmPaciente.setString(3, paciente.getApellidos());
			stmPaciente.setString(4, paciente.getTelefono());
			stmPaciente.setString(5, paciente.getContrasena());
			stmPaciente.setDate(6, (Date) paciente.getFecha_nacimiento());
			stmPaciente.setString(7, paciente.getMedico());
			stmPaciente.setBoolean(8, paciente.isEjerciciosHechos());

			con.setAutoCommit(true);
			stmPaciente.executeUpdate();
			con.setAutoCommit(false);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				stmPaciente.close();
			} catch (SQLException e) {
				System.out.println("Imposible cerrar cursores");
			}
		}

	}

	public void modificarPaciente(Paciente paciente) {
		Connection con;
		PreparedStatement stmPaciente = null;

		con = super.getConexion();

		String consulta = "update paciente set nombre = ?, apellidos= ?, telefono = ?, contraseña= ?, fecha_nacimiento= ?, medico= ?, ejerciciosHechos= ? "
				+ "where dni_paciente = ?";

		try {
			stmPaciente = con.prepareStatement(consulta);
			stmPaciente.setString(1, paciente.getNombre());
			stmPaciente.setString(2, paciente.getApellidos());
			stmPaciente.setString(3, paciente.getTelefono());
			stmPaciente.setString(4, paciente.getContrasena());
			stmPaciente.setDate(5, (Date) paciente.getFecha_nacimiento());
			stmPaciente.setString(6, paciente.getMedico());
			stmPaciente.setBoolean(7, paciente.isEjerciciosHechos());
			stmPaciente.setString(8, paciente.getDni());

			con.setAutoCommit(true);
			stmPaciente.executeUpdate();
			con.setAutoCommit(false);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				stmPaciente.close();
			} catch (SQLException e) {
				System.out.println("Imposible cerrar cursores");
			}
		}

	}

	public void borrarPaciente(Paciente paciente) {
		Connection con;
		PreparedStatement stmPaciente = null;

		con = super.getConexion();

		try {
			stmPaciente = con.prepareStatement("delete from paciente " + "where dni_paciente=?");
			stmPaciente.setString(1, paciente.getDni());

			con.setAutoCommit(true);
			stmPaciente.executeUpdate();
			con.setAutoCommit(false);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				stmPaciente.close();
			} catch (SQLException e) {
				System.out.println("Imposible cerrar cursores");
			}
		}
	}

}
