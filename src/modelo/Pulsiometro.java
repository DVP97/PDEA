package modelo;

import java.util.Date;

public final class Pulsiometro extends datoSensor {
	private Integer dato;

	public Pulsiometro(Date fecha, Integer dato, String dni) {
		super(fecha, dni);
		this.dato = dato;

	}

	public Pulsiometro() {
		super();
		this.dato = null;
	}

	// GETTERS
	public Integer getDato() {
		return dato;
	}

	// SETTERS
	public void setDato(Integer dato) {
		this.dato = dato;
	}

}
