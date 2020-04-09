package baseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;


public class ConexionBBDD {

	String BBDDName;
	Connection c = null;
	Statement stmt = null;

	public ConexionBBDD(String path) {
		BBDDName = "PDEAnewBBDD.db";
	}

	public void consulta() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:"+BBDDName);
			c.setAutoCommit(false);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM paciente;" );

			while ( rs.next() ) {
				String dni = rs.getString("dni_paciente");
				String nombre = rs.getString("nombre");
				String apellidos  = rs.getString("apellidos");
				String tlf = rs.getString("telefono");
				String med = rs.getString("medico");
				String fechaNaz = rs.getString("fecha_nacimiento");
				
				System.out.println( "DNI = " + dni );
				System.out.println( "NAME = " + nombre );
				System.out.println( "SURNAME = " + apellidos );
				System.out.println( "TELEPHONE = " + tlf );
				System.out.println( "DNI_MEDICO = " + med );
				System.out.println( "=============================");
			}

			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		System.out.println("Consulta terminada");
	}

}
