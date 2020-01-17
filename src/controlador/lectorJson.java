package controlador;

import java.io.FileReader;
import java.lang.reflect.Type;

import java.util.ArrayList;

import modelo.Cuidador;
import modelo.Medico;
import modelo.Mensaje;
import modelo.Paciente;
import modelo.datoSensor1;
import modelo.datoSensor2;
import modelo.datoSensor3;
import modelo.Ejercicio;
import modelo.Cita;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class lectorJson {
	
	public static ArrayList<Paciente> lectorJsonPacientes() {
		try {
			//guardas en una clase FileReader el nombre del json que quieres leer
			FileReader fr = new FileReader("pacientes.json");
			Gson gson= new Gson();
			//Guardas el tipo de datos que se va a encontrar dentro del json
			//en este caso un arraylist de Paciente
			Type tipoListaPacientes = new TypeToken<ArrayList<Paciente>>(){}.getType();
			//guardas con el fromJson en la variable que vas a devolver, los argumentos siempre son el FileReader y el tipo de datos que se va a encontrar
			ArrayList<Paciente> pacientes = gson.fromJson(fr, tipoListaPacientes);
			//devuelves el arraylist de pacientes
			return pacientes;
		}catch (Exception e) {
			System.out.println("Fallo en: " +e);
		}
		return null;
	}

	//Lo mismo que la anterior
	public static ArrayList<Medico> lectorJsonMedicos() {
		try {
			FileReader fr = new FileReader("medicos.json");
			Gson gson= new Gson();
			Type tipoListaMedico = new TypeToken<ArrayList<Medico>>(){}.getType();
			ArrayList<Medico> medicos = gson.fromJson(fr, tipoListaMedico);
			return medicos;
		}catch (Exception e) {
			System.out.println("Fallo en: " +e);
		}
		return null;
	}
	
	public static ArrayList<Cuidador> lectorJsonCuidadores() {
		try {
			FileReader fr = new FileReader("cuidadores.json");
			Gson gson= new Gson();
			Type tipoListaCuidador = new TypeToken<ArrayList<Cuidador>>(){}.getType();
			ArrayList<Cuidador> cuidadores = gson.fromJson(fr, tipoListaCuidador);
			return cuidadores;
		}catch (Exception e) {
			System.out.println("Fallo en: " +e);
		}
		return null;
	}
	
	public static ArrayList<Mensaje> lectorJsonMensajes() {
		ArrayList<Mensaje> mensajes = new ArrayList<Mensaje>();
		try {
			FileReader fr = new FileReader("mensajes.json");
			Gson gson= new Gson();
			Type tipoListoMensajes = new TypeToken<ArrayList<Mensaje>>(){}.getType();
			mensajes = gson.fromJson(fr, tipoListoMensajes);
		}catch (Exception e) {
			System.out.println("Fallo en: " +e);
		}
		return mensajes;
	}
	
	public static ArrayList<Ejercicio> lectorJsonEjercicios() {
		ArrayList<Ejercicio> ejercicios = new ArrayList<Ejercicio>();
		try {
			FileReader fr = new FileReader("ejercicios.json");
			Gson gson= new Gson();
			Type tipoListaEjercicios = new TypeToken<ArrayList<Ejercicio>>(){}.getType();
			ejercicios = gson.fromJson(fr, tipoListaEjercicios);
		}catch (Exception e) {
			System.out.println("Fallo en: " +e);
		}
		return ejercicios;
	}
	

	
	public static ArrayList<Cita> lectorJsonCitas() {
		ArrayList<Cita> citas = new ArrayList <Cita>();
		
		try {
			FileReader fr = new FileReader("citas.json");
			Gson gson= new Gson();
			Type tipoListaCitas = new TypeToken<ArrayList<Cita>>(){}.getType();
			citas = gson.fromJson(fr, tipoListaCitas);
			//System.out.println("Fallo en: " );
		}catch (Exception e) {
			System.out.println("Fallo en: " +e);
		}
		return citas;
		
	}
	
	public static ArrayList<datoSensor1> lectorJsonSensor1() {
		ArrayList<datoSensor1> datosS1 = new ArrayList <datoSensor1>();
		
		try {
			FileReader fr = new FileReader("sensor1.json");
			Gson gson= new Gson();
			Type tipoListaDatosS1 = new TypeToken<ArrayList<datoSensor1>>(){}.getType();
			datosS1 = gson.fromJson(fr, tipoListaDatosS1);
			
		}catch (Exception e) {
			System.out.println("Fallo en: " +e);
		}
		return datosS1;
		
	}
	
	public static ArrayList<datoSensor2> lectorJsonSensor2() {
		ArrayList<datoSensor2> datosS2 = new ArrayList <datoSensor2>();
		
		try {
			FileReader fr = new FileReader("sensor2.json");
			Gson gson= new Gson();
			Type tipoListaDatosS2 = new TypeToken<ArrayList<datoSensor2>>(){}.getType();
			datosS2 = gson.fromJson(fr, tipoListaDatosS2);
			
		}catch (Exception e) {
			System.out.println("Fallo en: " +e);
		}
		return datosS2;
		
	}
	
	public static ArrayList<datoSensor3> lectorJsonSensor3() {
		ArrayList<datoSensor3> datosS3 = new ArrayList <datoSensor3>();
		
		try {
			FileReader fr = new FileReader("sensor3.json");
			Gson gson= new Gson();
			Type tipoListaDatosS3 = new TypeToken<ArrayList<datoSensor3>>(){}.getType();
			datosS3 = gson.fromJson(fr, tipoListaDatosS3);

		}catch (Exception e) {
			System.out.println("Fallo en: " +e);
		}
		return datosS3;
		
	}
	//METODOS

	//Buscas un paciente por su dni empleando la funcion leer pacientes anterior y lo recorres comparando los dnis

	//getters para variables de sensor2	
	public static ArrayList<Integer> getSensor1FrecuenciaAntes (String dni) {
		ArrayList<Integer> s1 = new ArrayList<Integer>();
		datoSensor1 d = new datoSensor1();
		ArrayList<datoSensor1> datosS1pac = lectorJsonSensor1();
		for (int i = 0; i< datosS1pac.size(); i++) {
			d= datosS1pac.get(i);	
			if(d.getDni().equalsIgnoreCase(dni)) {
				s1.add(d.getFrecuenciaAntes());
			}
		}
		return s1;
	}
	
	public static ArrayList<Integer> getSensor1FrecuenciaDespues (String dni) {
		ArrayList<Integer> s1 = new ArrayList<Integer>();
		datoSensor1 d = new datoSensor1();
		ArrayList<datoSensor1> datosS1pac = lectorJsonSensor1();
		for (int i = 0; i< datosS1pac.size(); i++) {
			d= datosS1pac.get(i);	
			if(d.getDni().equalsIgnoreCase(dni)) {
				s1.add(d.getFrecuenciaDespues());
			}
		}
		return s1;
	}
	
	public static ArrayList<String> getFechaSensor1 (String dni) {
		ArrayList<String> s1 = new ArrayList<String>();
		datoSensor1 d = new datoSensor1();
		ArrayList<datoSensor1> datosS1pac = lectorJsonSensor1();
		for (int i = 0; i< datosS1pac.size(); i++) {
			d= datosS1pac.get(i);	
			if(d.getDni().equalsIgnoreCase(dni)) {
				s1.add(d.getFechaString());
			}
		}
		return s1;
	}
	//getters para variables de sensor2
	public static ArrayList<String> getFechaSensor2 (String dni) {
		ArrayList<String> s1 = new ArrayList<String>();
		datoSensor2 d = new datoSensor2();
		ArrayList<datoSensor2> datosS2pac = lectorJsonSensor2();
		for (int i = 0; i< datosS2pac.size(); i++) {
			d= datosS2pac.get(i);	
			if(d.getDni().equalsIgnoreCase(dni)) {
				s1.add(d.getFechaString());
			}
		}
		return s1;
	}
	
	public static ArrayList<Integer> getDatosSensor2 (String dni) {
		ArrayList<Integer> s1 = new ArrayList<Integer>();
		datoSensor2 d = new datoSensor2();
		ArrayList<datoSensor2> datosS2pac = lectorJsonSensor2();
		for (int i = 0; i< datosS2pac.size(); i++) {
			d= datosS2pac.get(i);	
			System.out.println(d.getDni());
			if(d.getDni().equalsIgnoreCase(dni)) {
				s1.add(d.getDatosMedicos().intValue());
				System.out.println(d.getDatosMedicos().intValue());
			}
		}
		return s1;
	}
	//getters para variables de sensor3
	public static ArrayList<String> getFechaSensor3 (String dni) {
		ArrayList<String> s1 = new ArrayList<String>();
		datoSensor3 d = new datoSensor3();
		ArrayList<datoSensor3> datosS1pac = lectorJsonSensor3();
		for (int i = 0; i< datosS1pac.size(); i++) {
			d= datosS1pac.get(i);	
			if(d.getDni().equalsIgnoreCase(dni)) {
				s1.add(d.getFechaString());
			}
		}
		return s1;
	}
	
	public static ArrayList<Integer> getSistoleAntesSensor3 (String dni) {
		ArrayList<Integer> s1 = new ArrayList<Integer>();
		datoSensor3 d = new datoSensor3();
		ArrayList<datoSensor3> datosS3pac = lectorJsonSensor3();
		for (int i = 0; i< datosS3pac.size(); i++) {
			d= datosS3pac.get(i);	
			if(d.getDni().equalsIgnoreCase(dni)) {
				s1.add(d.getSistoleAntes());
				System.out.println(d.getSistoleAntes());
			}
		}
		return s1;
	}
	
	public static ArrayList<Integer> getDiastoleAntesSensor3 (String dni) {
		ArrayList<Integer> s1 = new ArrayList<Integer>();
		datoSensor3 d = new datoSensor3();
		ArrayList<datoSensor3> datosS1pac = lectorJsonSensor3();
		for (int i = 0; i< datosS1pac.size(); i++) {
			d= datosS1pac.get(i);	
			if(d.getDni().equalsIgnoreCase(dni)) {
				s1.add(d.getDiastoleAntes());
			}
		}
		return s1;
	}

	public static ArrayList<Integer> getSistoleDespuesSensor3 (String dni) {
		ArrayList<Integer> s1 = new ArrayList<Integer>();
		datoSensor3 d = new datoSensor3();
		ArrayList<datoSensor3> datosS1pac = lectorJsonSensor3();
		for (int i = 0; i< datosS1pac.size(); i++) {
			d= datosS1pac.get(i);	
			if(d.getDni().equalsIgnoreCase(dni)) {
				s1.add(d.getSistoleDespues());
			}
		}
		return s1;
	}

	public static ArrayList<Integer> getDiastoleDespuesSensor3 (String dni) {
		ArrayList<Integer> s1 = new ArrayList<Integer>();
		datoSensor3 d = new datoSensor3();
		ArrayList<datoSensor3> datosS1pac = lectorJsonSensor3();
		for (int i = 0; i< datosS1pac.size(); i++) {
			d= datosS1pac.get(i);	
			if(d.getDni().equalsIgnoreCase(dni)) {
				s1.add(d.getDiastoleDespues());
			}
		}
		return s1;
	}
	
	public static Paciente getPaciente (String dni) {
		Paciente p = new Paciente();
		ArrayList<Paciente> pac = lectorJsonPacientes();
		for (int i = 0; i< pac.size(); i++) {
			p = pac.get(i);
			if (p.getDni().equalsIgnoreCase(dni)) {
				return p;
			}
		}
		return null;
	}
	
	
	public static Medico getMedico (String dni) {
		Medico p = new Medico();
		ArrayList<Medico> pac = lectorJsonMedicos();
		for (int i = 0; i< pac.size(); i++) {
			p = pac.get(i);
			if (p.getDni().equalsIgnoreCase(dni)) {
				return p;
			}
		}
		return null;
	}
	
	public static Cuidador getCuidador (String dni) {
		Cuidador p = new Cuidador();
		ArrayList<Cuidador> pac = lectorJsonCuidadores();
		for (int i = 0; i< pac.size(); i++) {
			p = pac.get(i);
			if (p.getDni().equalsIgnoreCase(dni)) {
				return p;
			}
		}
		return null;
	}
	
	public static Ejercicio getEjercicio(Integer id) {
		Ejercicio e = new Ejercicio();
		ArrayList<Ejercicio> pac = lectorJsonEjercicios();
		for (int i = 0; i < pac.size(); i++) {
			e = pac.get(i);
			if (e.getId().equals(id)) {
				return e;
			}
		}
		return null;
	}
	//
	public static Cita getCita (String dni){
		Cita c = new Cita();
		ArrayList<Cita> pac = lectorJsonCitas();
		for (int i = 0; i < pac.size() ; i++) {
			c = pac.get(i);
			if (c.getDni().equals(dni)) {
				return c;
		    }
	    }
		return null;
	}
	
	// El dni que metes es el del emisor
	public static ArrayList<Mensaje> getMensajesEnviadosPor (String dni) {
		ArrayList<Mensaje> mensajesEnviados = new ArrayList<Mensaje>();
		Mensaje m = new Mensaje();
		ArrayList<Mensaje> mensajes = lectorJsonMensajes();
		for (int i = 0; i< mensajes.size(); i++) {
			m = mensajes.get(i);
			if(!m.isBorrado()) {
				if (m.getEmisor().equalsIgnoreCase(dni)) {
					mensajesEnviados.add(m);
				}
			}
		}
		return mensajesEnviados;
	}
	
	// El dni que metes es el del receptor
	public static ArrayList<Mensaje> getMensajesEnviadosA (String dni) {
		ArrayList<Mensaje> mensajesRecibidos = new ArrayList<Mensaje>();
		Mensaje m = new Mensaje();
		ArrayList<Mensaje> mensajes = lectorJsonMensajes();
		for (int i = 0; i< mensajes.size(); i++) {
			m = mensajes.get(i);
			if(!m.isBorrado()) {
				if (m.getReceptor().equalsIgnoreCase(dni)) {
					mensajesRecibidos.add(m);
				}
			}
		}
		return mensajesRecibidos;
	}
	
	public static ArrayList<Mensaje> getMensajesA(String dniPac, String dniMed){
		ArrayList<Mensaje> mensajesA = new ArrayList<Mensaje>();
		Mensaje m = new Mensaje();
		ArrayList<Mensaje> mensajes = lectorJsonMensajes();
		for (int i = 0; i <mensajes.size(); i++) {
			m = mensajes.get(i);
			if(!m.isBorrado()) {
				if(m.getReceptor().equalsIgnoreCase(dniPac)) {
					if(m.getEmisor().equalsIgnoreCase(dniMed)) {
						mensajesA.add(m);
					}
				}
			}
		}
		return mensajesA;
	}
	
	//Devuelve los ejercicios correspondientes al paciente
	public static ArrayList<Ejercicio> getEjercicios (Paciente p) {
		//Array ids de los ejercicios de paciente
		ArrayList<Integer> idsEjercicios = p.getEjercicios();
		//Array donde estan ejercicios a secas
		ArrayList<Ejercicio> pac = lectorJsonEjercicios();
		ArrayList<Ejercicio> ejerciciosDePaciente = new ArrayList<Ejercicio>();
		for (int i = 0; i < idsEjercicios.size(); i++) {
			for(int a=0; a< pac.size();a++) {
				if (idsEjercicios.get(i).equals(pac.get(a).getId())){
					ejerciciosDePaciente.add(pac.get(a));
				}
			}	
		}
	 return ejerciciosDePaciente;	
	}
	
	
	public static ArrayList<String> getNombresCompletosPacientesDe (Medico m){
		ArrayList<String> nombresCompletos= new ArrayList<String>();
		ArrayList<String> dnis = m.getPacientes();
		for (int i = 0; i < dnis.size() ; i++) {
			Paciente p = getPaciente(dnis.get(i));
			String n = p.getNombre() + " " + p.getApellidos();
			nombresCompletos.add(n);
		}
		return nombresCompletos;
	}
	

		
		
	
	
	
}
