package modelo;

import java.util.Date;

public final class Presion extends datoSensor{
	private Integer sistoleAntes;
	private Integer diastoleAntes;
	private Integer sistoleDespues;
	private Integer diastoleDespues;
	
	
	public Presion(Date fecha, String dni, Integer sistoleAntes, Integer diastoleAntes, Integer sistoleDespues, Integer diastoleDespues) {
		super(fecha, dni);
		this.sistoleAntes = sistoleAntes;
		this.diastoleAntes = diastoleAntes;
		this.diastoleDespues = diastoleDespues;
		this.sistoleDespues = sistoleDespues;
	}
	
	public Presion() {
		super();
		this.sistoleAntes = null;
		this.diastoleAntes = null;
		this.sistoleDespues = null;
		this.diastoleDespues = null;
	}
	
	//GETTERS
	public Integer getDiastoleAntes() {
		return diastoleAntes;
	}
	public Integer getDiastoleDespues() {
		return diastoleDespues;
	}

	public Integer getSistoleAntes() {
		return sistoleAntes;
	}
	public Integer getSistoleDespues() {
		return sistoleDespues;
	}

	
	//SETTERS
	public void setDiastoleAntes(Integer diastoleAntes) {
		this.diastoleAntes = diastoleAntes;
	}
	public void setDiastoleDespues(Integer diastoleDespues) {
		this.diastoleDespues = diastoleDespues;
	}

	public void setSistoleAntes(Integer sistoleAntes) {
		this.sistoleAntes = sistoleAntes;
	}
	public void setSistoleDespues(Integer sistoleDespues) {
		this.sistoleDespues = sistoleDespues;
	}
	
	
}
