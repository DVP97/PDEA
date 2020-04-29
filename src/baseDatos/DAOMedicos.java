package baseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Cita;
import modelo.Medico;
import modelo.Paciente;

public class DAOMedicos extends AbstractDAO {

	//private baseDatos.FachadaBaseDatos fbd = application.Main.getFbd();

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

	////Función que devuelve los pacientes del médico
	public ArrayList<Paciente> obtenerPacientesMedico(Medico medico) {
		
		ArrayList<Paciente> resultado = new ArrayList<Paciente>();
		
		Paciente pacienteActual = null;
		Connection con;
		PreparedStatement stmMedico = null;
		ResultSet rsPacientes;

		con = super.getConexion();

		String consulta = "select p.* " + "from paciente as p right join medico as m on p.medico = m.dni_medico "
				+ "where m.dni_medico = ? ";

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
	
	//Función que devuelve las citas
	public ArrayList<Cita> obtenerCitasMedico (Medico medico){
		ArrayList<Cita> resultado = new ArrayList<Cita>();
		Cita citaActual = null;
		Connection con;
		PreparedStatement stmPaciente = null;
		ResultSet rsCitas;
		
		con = this.getConexion();
		
		String consulta = "select * from cita where medico = ?";
		
		try {
			stmPaciente = con.prepareStatement(consulta);
			stmPaciente.setString(1, medico.getDni());
			rsCitas = stmPaciente.executeQuery();
			
			while (rsCitas.next()) {
				Paciente p = this.visualizarPaciente(rsCitas.getString("paciente"));
				String nombre = p.getNombre() + " " + p.getApellidos();
				citaActual = new Cita(rsCitas.getInt("id"), rsCitas.getString("fecha_cita"), rsCitas.getString("nota"), rsCitas.getString("paciente"), rsCitas.getString("medico"), nombre);
				resultado.add(citaActual);
			}
		}
		catch (SQLException e) {
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

}
