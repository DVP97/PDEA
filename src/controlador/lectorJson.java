package controlador;

import java.io.FileReader;
import java.lang.reflect.Type;

import java.util.ArrayList;

import modelo.Cuidador;
import modelo.Medico;
import modelo.Mensaje;
import modelo.Paciente;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class lectorJson {
	
	
	
	public static ArrayList<Paciente> lectorJsonPacientes() {
		try {
			FileReader fr = new FileReader("pacientes.json");
			Gson gson= new Gson();
			Type tipoListaPacientes = new TypeToken<ArrayList<Paciente>>(){}.getType();
			ArrayList<Paciente> pacientes = gson.fromJson(fr, tipoListaPacientes);
			return pacientes;
		}catch (Exception e) {
			System.out.println("Fallo en: " +e);
		}
		return null;
	}

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
	
	
	//METODOS
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
	
	public static ArrayList<Mensaje> getMensajesEnviadosPor (String dni) {
		ArrayList<Mensaje> mensajesEnviados = new ArrayList<Mensaje>();
		Mensaje m = new Mensaje();
		ArrayList<Mensaje> mensajes = lectorJsonMensajes();
		for (int i = 0; i< mensajes.size(); i++) {
			m = mensajes.get(i);
			if(m.isBorrado()) {
				if (m.getEmisor().equalsIgnoreCase(dni)) {
					mensajesEnviados.add(m);
				}
			}
		}
		return mensajesEnviados;
	}
	
	

	public static ArrayList<Mensaje> getMensajesEnviadosA (String dni) {
		ArrayList<Mensaje> mensajesRecibidos = new ArrayList<Mensaje>();
		Mensaje m = new Mensaje();
		ArrayList<Mensaje> mensajes = lectorJsonMensajes();
		for (int i = 0; i< mensajes.size(); i++) {
			m = mensajes.get(i);
			if(m.isBorrado()) {
				if (m.getReceptor().equalsIgnoreCase(dni)) {
					mensajesRecibidos.add(m);
				}
			}
		}
		return mensajesRecibidos;
	}
	
}
