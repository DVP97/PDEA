package modelo;


public final class Medico extends Usuario{
	
	boolean isGestor;
	
	public Medico(String dni, String nombre, String apellidos, String telefono, String contrasena, boolean isGestor) {
		super(dni, nombre, apellidos, telefono, contrasena);
		this.isGestor = isGestor;
	}
	public Medico() {
		super();
		this.isGestor = false;
	}
	
	//GETTERS
	public String getNombreCompleto() {
		String nombreCompleto =getNombre() + " " + getApellidos();
		return nombreCompleto;
	}
	
	public boolean isGestor() {
		return isGestor;
	}
	
	//SETTERS
	public void setGestor(boolean isGestor) {
		this.isGestor = isGestor;
	}
}
