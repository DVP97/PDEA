package baseDatos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Cita;
import modelo.Cuidador;
import modelo.Ejercicio;
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

	public void insertarPaciente(Paciente paciente) {
		Connection con;
		PreparedStatement stmPaciente = null;

		con = super.getConexion();

		String consulta = "insert into paciente (dni_paciente, nombre, apellidos, telefono, contrasena, fecha_nacimiento, medico, ejerciciosHechos)"
				+ " values (?,?,?,?,?,?,?,?)";

		try {
			stmPaciente = con.prepareStatement(consulta);
			stmPaciente.setString(1, paciente.getDni());
			stmPaciente.setString(2, paciente.getNombre());
			stmPaciente.setString(3, paciente.getApellidos());
			stmPaciente.setString(4, paciente.getTelefono());
			stmPaciente.setString(5, paciente.getContrasena());
			stmPaciente.setString(6, paciente.getFecha_nacimiento());
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

		String consulta = "update paciente set nombre = ?, apellidos= ?, telefono = ?, contrasena= ?, fecha_nacimiento= ?, medico= ?, ejerciciosHechos= ? "
				+ "where dni_paciente = ?";

		try {
			stmPaciente = con.prepareStatement(consulta);
			stmPaciente.setString(1, paciente.getNombre());
			stmPaciente.setString(2, paciente.getApellidos());
			stmPaciente.setString(3, paciente.getTelefono());
			stmPaciente.setString(4, paciente.getContrasena());
			stmPaciente.setString(5, paciente.getFecha_nacimiento());
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

		String consulta = "delete from paciente where dni_paciente=?";
		
		try {
			stmPaciente = con.prepareStatement(consulta);
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

	public ArrayList<Cuidador> obtenerCuidadoresPaciente (Paciente paciente){
		ArrayList<Cuidador> resultado = new ArrayList<Cuidador>();
		Cuidador cuidadorActual = null;
		Connection con ; 
		PreparedStatement stmPaciente = null;
		ResultSet rsCuidadores;
		
		con= super.getConexion();
		
		String consulta = "select c.* "
				+ "from cuidador as c right join cuidador_de_paciente as cu on c.dni_cuidador = cu.cuidador "
				+ "where cu.paciente = ? ";
		try {
			stmPaciente = con.prepareStatement(consulta);
			stmPaciente.setString(1, paciente.getDni());
			rsCuidadores = stmPaciente.executeQuery();
			
			while (rsCuidadores.next()) {
				cuidadorActual = new Cuidador(rsCuidadores.getString("dni_cuidador"), rsCuidadores.getString("nombre"), rsCuidadores.getString("apellidos"), rsCuidadores.getString("telefono"), rsCuidadores.getString("contrasena"));
				resultado.add(cuidadorActual);
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

	public ArrayList<Ejercicio> obtenerEjerciciosPaciente (Paciente paciente){
		ArrayList<Ejercicio> resultado = new ArrayList<Ejercicio>();
		Ejercicio ejercicioActual = null;
		Connection con ; 
		PreparedStatement stmPaciente = null;
		ResultSet rsEjercicios;
		
		con= this.getConexion();
		
		String consulta = "select e.*, ep.duracion "
				+ "from ejercicio as e right join ejercicio_de_paciente as ep on e.id_ejercicio = ep.ejercicio "
				+ "where ep.paciente = ? ";
		try {
			stmPaciente = con.prepareStatement(consulta);
			stmPaciente.setString(1, paciente.getDni());
			rsEjercicios = stmPaciente.executeQuery();
			
			while (rsEjercicios.next()) {
				ejercicioActual = new Ejercicio(rsEjercicios.getInt("id_ejercicio"), rsEjercicios.getString("nombre"), rsEjercicios.getInt("duracion"), rsEjercicios.getString("gif"));
				resultado.add(ejercicioActual);
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

	public ArrayList<Cita> obtenerCitasPaciente (Paciente paciente){
		ArrayList<Cita> resultado = new ArrayList<Cita>();
		Cita citaActual = null;
		Connection con;
		PreparedStatement stmPaciente = null;
		ResultSet rsCitas;
		
		con = this.getConexion();
		
		String consulta = "select * from cita where paciente = ?";
		
		try {
			stmPaciente = con.prepareStatement(consulta);
			stmPaciente.setString(1, paciente.getDni());
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
	
	public void asignarEjercicioPaciente (Paciente paciente, Ejercicio ejercicio) {
		Connection con;
		PreparedStatement stmPaciente = null;
		
		con = super.getConexion();
		
		String consulta = "insert into ejercicio_de_paciente (paciente, ejercicio, duracion) "
				+"values (?, ?, ?)";
		
		try {
			stmPaciente = con.prepareStatement(consulta);
			stmPaciente.setString(1, paciente.getDni());
			stmPaciente.setInt(2, ejercicio.getId());
			stmPaciente.setInt(3, ejercicio.getDuracion());
		
			con.setAutoCommit(true);
			stmPaciente.executeUpdate();
			con.setAutoCommit(false);
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
	}
	
	public void asignarCuidadorPaciente (Paciente paciente, Cuidador cuidador) {
		Connection con;
		PreparedStatement stmPaciente = null;
		
		con = super.getConexion();
		
		String consulta = "insert into cuidador_de_paciente (paciente, cuidador) "
				+"values (?, ?)";
		
		try {
			stmPaciente = con.prepareStatement(consulta);
			stmPaciente.setString(1, paciente.getDni());
			stmPaciente.setString(2, cuidador.getDni());
		
			con.setAutoCommit(true);
			stmPaciente.executeUpdate();
			con.setAutoCommit(false);
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
	}
	
	public void modificarDuracionEjercicioPaciente (Paciente paciente, Ejercicio ejercicio) {
		Connection con;
		PreparedStatement stmPaciente = null;
		
		con = super.getConexion();
				
		String consulta = "update ejercicio_de_paciente set duracion = ? "
				+"where paciente = ? and ejercicio = ?";
		
		try {
			stmPaciente = con.prepareStatement(consulta);
			stmPaciente.setInt(1, ejercicio.getDuracion());
			stmPaciente.setString(2, paciente.getDni());
			stmPaciente.setInt(3, ejercicio.getId());
		
			con.setAutoCommit(true);
			stmPaciente.executeUpdate();
			con.setAutoCommit(false);
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
	}
	
	public void borrarEjercicioPaciente (Paciente paciente, Ejercicio ejercicio) {
		Connection con;
		PreparedStatement stmPaciente = null;
		
		con = super.getConexion();
				
		String consulta = "delete from ejercicio_de_paciente where paciente = ? and ejercicio = ?";
		
		try {
			stmPaciente = con.prepareStatement(consulta);
			stmPaciente.setString(1, paciente.getDni());
			stmPaciente.setInt(2, ejercicio.getId());
		
			con.setAutoCommit(true);
			stmPaciente.executeUpdate();
			con.setAutoCommit(false);
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
		
	}
	
	
}
