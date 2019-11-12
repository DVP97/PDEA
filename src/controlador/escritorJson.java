package controlador;

import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import modelo.Cuidador;
import modelo.Medico;
import modelo.Paciente;



public class escritorJson {

	lectorJson lector = new lectorJson();
	ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
	ArrayList<Medico> medicos = new ArrayList<Medico>();
	ArrayList<Cuidador> cuidadores = new ArrayList<Cuidador>();
	
	
	public escritorJson() {
		try {
			escribirEnJson("pacientes.json");
			escribirEnJson("medicos.json");
			escribirEnJson("cuidadores.json");
		}catch(Exception e) {
			System.out.println("Fallo en: " +e);
		}
	}



	
	private void escribirEnJson(String sFile) throws java.io.IOException{

		//Aqui deberiamos tener los arrays que vienen del main actualizados y escribirlos
		//pacientes = lector.devolverPacientes();
		//cuidadores = lector.devolverCuidadores();
		//medicos = lector.devolverMedicos();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try (FileWriter writer = new FileWriter (sFile)){
			if (sFile.equalsIgnoreCase("pacientes.json")) {
				gson.toJson(pacientes, writer);
			}else if (sFile.equalsIgnoreCase("cuidadores.json")) {
				gson.toJson(cuidadores, writer);
	        }else if (sFile.equalsIgnoreCase("medicos.json")) {
				gson.toJson(medicos, writer);
	        }
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
