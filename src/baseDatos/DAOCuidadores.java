package baseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Cuidador;
import modelo.Paciente;

public class DAOCuidadores extends AbstractDAO {

	public DAOCuidadores(Connection conexion) {
		super.setConexion(conexion);
	}

	public Cuidador visualizarCuidador(String dni) {
		Cuidador resultado = null;
		Connection con;
		PreparedStatement stmCuidador = null;
		ResultSet rsCuidador = null;

		con = super.getConexion();

		String consulta = "select * from cuidador where dni_cuidador = ?";

		try {
			stmCuidador = con.prepareStatement(consulta);
			stmCuidador.setString(1, dni);
			rsCuidador = stmCuidador.executeQuery();

			if (rsCuidador.next()) {
				resultado = new Cuidador(rsCuidador.getString("dni_cuidador"), rsCuidador.getString("nombre"),
						rsCuidador.getString("apellidos"), rsCuidador.getString("telefono"),
						rsCuidador.getString("contrasena"));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				stmCuidador.close();
			} catch (SQLException e) {
				System.out.println("Imposible cerrar cursores");
			}
		}
		return resultado;
	}

	public void insertarCuidador(Cuidador cuidador) {
		Connection con;
		PreparedStatement stmCuidador = null;

		con = super.getConexion();

		String consulta = "insert into cuidador (dni_cuidador, nombre, apellidos, telefono, contrasena)"
				+ " values (?,?,?,?,?)";

		try {
			stmCuidador = con.prepareStatement(consulta);
			stmCuidador.setString(1, cuidador.getDni());
			stmCuidador.setString(2, cuidador.getNombre());
			stmCuidador.setString(3, cuidador.getApellidos());
			stmCuidador.setString(4, cuidador.getTelefono());
			stmCuidador.setString(5, cuidador.getContrasena());

			con.setAutoCommit(true);
			stmCuidador.executeUpdate();
			con.setAutoCommit(false);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				stmCuidador.close();
			} catch (SQLException e) {
				System.out.println("Imposible cerrar cursores");
			}
		}
	}

	public void modificarCuidador(Cuidador cuidador) {
		Connection con;
		PreparedStatement stmCuidador = null;

		con = super.getConexion();

		String consulta = "update cuidador set nombre = ?, apellidos= ?, telefono = ?, contrasena= ? "
				+ "where dni_cuidador = ?";

		try {
			stmCuidador = con.prepareStatement(consulta);
			stmCuidador.setString(1, cuidador.getNombre());
			stmCuidador.setString(2, cuidador.getApellidos());
			stmCuidador.setString(3, cuidador.getTelefono());
			stmCuidador.setString(4, cuidador.getContrasena());
			stmCuidador.setString(5, cuidador.getDni());

			con.setAutoCommit(true);
			stmCuidador.executeUpdate();
			con.setAutoCommit(false);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				stmCuidador.close();
			} catch (SQLException e) {
				System.out.println("Imposible cerrar cursores");
			}
		}
	}

	public void borrarCuidador(Cuidador cuidador) {
		Connection con;
		PreparedStatement stmCuidador = null;

		con = super.getConexion();

		String consulta = "delete from cuidador where dni_cuidador = ?";

		try {
			stmCuidador = con.prepareStatement(consulta);
			stmCuidador.setString(1, cuidador.getDni());

			con.setAutoCommit(true);
			stmCuidador.executeUpdate();
			con.setAutoCommit(false);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				stmCuidador.close();
			} catch (SQLException e) {
				System.out.println("Imposible cerrar cursores");
			}
		}
	}

	public ArrayList<Paciente> obtenerPacientesCuidador(Cuidador cuidador) {
		ArrayList<Paciente> resultado = new ArrayList<Paciente>();
		Paciente pacienteActual = null;
		Connection con;
		PreparedStatement stmCuidador = null;
		ResultSet rsPacientes;

		con = super.getConexion();

		String consulta = "select p.* "
				+ "from paciente as p right join cuidador_de_paciente as cu on p.dni_paciente = cu.paciente "
				+ "where cu.cuidador = ? ";
		try {
			stmCuidador = con.prepareStatement(consulta);
			stmCuidador.setString(1, cuidador.getDni());
			rsPacientes = stmCuidador.executeQuery();

			while (rsPacientes.next()) {
				pacienteActual = new Paciente(rsPacientes.getString("dni_paciente"), rsPacientes.getString("nombre"),
						rsPacientes.getString("apellidos"), rsPacientes.getString("telefono"),
						rsPacientes.getString("contrasena"), rsPacientes.getDate("fecha_nacimiento"),
						rsPacientes.getString("medico"), rsPacientes.getBoolean("ejerciciosHechos"));
				resultado.add(pacienteActual);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				stmCuidador.close();
			} catch (SQLException e) {
				System.out.println("Imposible cerrar cursores");
			}
		}
		return resultado;
	}

}
