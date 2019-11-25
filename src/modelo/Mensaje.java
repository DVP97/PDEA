package modelo;

public class Mensaje {
	private String emisor;
	private String receptor;
	private String mensaje;
	
	public Mensaje (String emisor, String receptor,  String mensaje) {
		this.emisor =emisor;
		this.receptor = receptor;
		this.mensaje = mensaje;
	}
	
	public Mensaje () {
		this.emisor =null;
		this.receptor = null; 
		this.mensaje = null;
	}
	
	//GETTERS 

	public String getEmisor() {
		return emisor;
	}
	public String getMensaje() {
		return mensaje;
	}
	public String getReceptor() {
		return receptor;
	}
	
	//SETTERS

	public void setEmisor(String emisor) {
		this.emisor = emisor;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public void setReceptor(String receptor) {
		this.receptor = receptor;
	}
}