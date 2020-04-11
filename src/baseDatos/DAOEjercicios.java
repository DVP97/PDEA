package baseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Ejercicio;

public class DAOEjercicios extends AbstractDAO{
	
	public DAOEjercicios (Connection con) {
		super.setConexion(con);
	}
	
	public Ejercicio visualizarEjercicio(Integer id) {
		Ejercicio resultado = null;
		Connection con;
		PreparedStatement stmEjercicio = null;
		ResultSet rsEjercicio = null;
		
		con = super.getConexion();
		
		String consulta = "select * from ejercicio where id_ejercicio = ?";
		
		try {
			stmEjercicio = con.prepareStatement(consulta);
			stmEjercicio.setInt(1, id);
			rsEjercicio = stmEjercicio.executeQuery();
			
			if(rsEjercicio.next()) {
				resultado = new Ejercicio(rsEjercicio.getInt("id_ejercicio"), rsEjercicio.getString("nombre"), rsEjercicio.getString("gif"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				stmEjercicio.close();
			} catch (SQLException e) {
				System.out.println("Imposible cerrar cursores");
			}
		}
		return resultado;
	}

	
}
