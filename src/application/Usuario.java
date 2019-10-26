package application;

public abstract class Usuario {
	private String dni;
	private String nombre;
	private Integer telefono;
	private String contrasena;
	
	public Usuario (String dni, String nombre, Integer telefono, String contrasena) {
		this.dni= dni;
		this.nombre= nombre;
		this.telefono= telefono;
		this.contrasena= contrasena;
	}
	
	
	//GETTERS
	public String getContrasena() {
		return contrasena;
	}
	
	public String getDni() {
		return dni;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public Integer getTelefono() {
		return telefono;
	}
	
	//SETTERS
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}
}
