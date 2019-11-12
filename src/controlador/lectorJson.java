package controlador;

import java.io.FileReader;
import java.lang.reflect.Type;

import java.util.ArrayList;



import application.Main;
import modelo.Cuidador;
import modelo.Medico;
import modelo.Paciente;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class lectorJson {


	public lectorJson() {
		
		try {
			leerJsonPartes("pacientes.json");
			leerJsonPartes("cuidadores.json");
			leerJsonPartes("medicos.json");
		}catch(Exception e) {
			System.out.println("Fallo en: "+e);
		}
	}

	
	private void leerJsonPartes(String sFile) throws java.io.IOException {
        FileReader fr = new FileReader(sFile);
        Gson gson= new Gson();
        if (sFile.equalsIgnoreCase("pacientes.json")) {
			Type tipoListaPacientes = new TypeToken<ArrayList<Paciente>>(){}.getType();
			ArrayList<Paciente> pacientes = gson.fromJson(fr, tipoListaPacientes);
			Main.setPacientes(pacientes);
        }
        else if (sFile.equalsIgnoreCase("cuidadores.json")) {
        	Type tipoListaCuidadores = new TypeToken<ArrayList<Cuidador>>(){}.getType();
			ArrayList<Cuidador> cuidadores = gson.fromJson(fr, tipoListaCuidadores);
			Main.setCuidadores(cuidadores);
        }else if (sFile.equalsIgnoreCase("medicos.json")) {
        	Type tipoListaMedicos = new TypeToken<ArrayList<Medico>>(){}.getType();
			ArrayList<Medico> medicos = gson.fromJson(fr, tipoListaMedicos);
			Main.setMedicos(medicos);
        }
    }
	
}
