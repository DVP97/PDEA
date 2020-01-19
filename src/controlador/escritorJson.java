
package controlador;

import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import modelo.Cita;
import modelo.Cuidador;
import modelo.Medico;
import modelo.Mensaje;
import modelo.Paciente;



public class escritorJson {



	public static void escribirEnJsonPacientes(ArrayList<Paciente> pacientes) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try (FileWriter writer = new FileWriter ("pacientes.json")){
			gson.toJson(pacientes, writer);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void escribirEnJsonMedicos(ArrayList<Medico> medicos) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try (FileWriter writer = new FileWriter ("medicos.json")){
			gson.toJson(medicos, writer);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void escribirEnJsonCuidadores(ArrayList<Cuidador> cuidadores) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try (FileWriter writer = new FileWriter ("cuidadores.json")){
			gson.toJson(cuidadores, writer);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void escribirEnJsonMensajes(ArrayList<Mensaje> mensajes) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try (FileWriter writer = new FileWriter ("mensajes.json")){
			gson.toJson(mensajes, writer);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void escribirEnJsonCitas(ArrayList<Cita> citas) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try (FileWriter writer = new FileWriter ("citas.json")){
			gson.toJson(citas, writer);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void modificarPaciente (Paciente paciente) {
		ArrayList<Paciente> pacientes = lectorJson.lectorJsonPacientes();
		for (int i = 0 ; i < pacientes.size(); i++) {
			Paciente pac = pacientes.get(i);
			if (pac.getDni().equalsIgnoreCase(paciente.getDni())) {
				pac.setApellidos(paciente.getApellidos());
				pac.setContrasena(paciente.getContrasena());
				pac.setCuidadores(paciente.getCuidadores());
				pac.setEjercicios(paciente.getEjercicios());
				pac.setFecha_nacimiento(paciente.getFecha_nacimiento());
				pac.setNombre(paciente.getNombre());
				pac.setTelefono(paciente.getTelefono());
				pac.setEjerciciosHechos(paciente.isEjerciciosHechos());
			}
		}
		escribirEnJsonPacientes(pacientes);
	}

	
	
}
