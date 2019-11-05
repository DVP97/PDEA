package application;

import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.google.gson.JsonParser;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;





public class lectorJson {


	JsonArray arraypacientes = new JsonArray();
	JsonArray arraycuidadores = new JsonArray();
	JsonArray arraymedicos = new JsonArray();

	private ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
	private ArrayList<Medico> medicos= new ArrayList<Medico>();
	private ArrayList<Cuidador> cuidadores = new ArrayList<Cuidador>();

	
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
        JsonParser parser = new JsonParser();
        FileReader fr = new FileReader(sFile);
        JsonElement datos = parser.parse(fr);
        if (sFile.equalsIgnoreCase("pacientes.json")) {
        	arraypacientes = datos.getAsJsonArray();
        }
        else if (sFile.equalsIgnoreCase("cuidadores.json")) {
        	arraycuidadores = datos.getAsJsonArray();
        }else if (sFile.equalsIgnoreCase("medicos.json")) {
        	arraymedicos = datos.getAsJsonArray();
        }
        convertir();
    }
	
	private void convertir ()  {
		Paciente pac;
		Cuidador cui;
		Medico med;
		
		if (arraypacientes != null) {
            java.util.Iterator<JsonElement> iter = arraypacientes.iterator();
            while(iter.hasNext()) {
            	pac= new Paciente();
            	JsonElement elemento = iter.next();
            	JsonObject objeto = elemento.getAsJsonObject();
            	java.util.Set<java.util.Map.Entry<String,JsonElement>> entradas = objeto.entrySet();
            	java.util.Iterator<java.util.Map.Entry<String,JsonElement>> iter2 = entradas.iterator();
                while (iter2.hasNext()) {
                    java.util.Map.Entry<String,JsonElement> entrada = iter2.next();
                    String key = entrada.getKey();
                    if (key.equalsIgnoreCase("dni")) {
                    	pac.setDni(entrada.getValue().getAsString());
                    }
                    else if (key.equalsIgnoreCase("nombre")) {
                    	pac.setNombre(entrada.getValue().getAsString());
                    }
                    else if (key.equalsIgnoreCase("telefono")) {
                    	pac.setTelefono(entrada.getValue().getAsInt());
                    }
                    else if (key.equalsIgnoreCase("contrasena")) {
                    	pac.setContrasena(entrada.getValue().getAsString());;
                    }
                    else if (key.equalsIgnoreCase("fecha_nacimiento")) {
                    	String valorS = entrada.getValue().getAsString();                    	
                    	Date valorD;
						try {
							valorD = new SimpleDateFormat("dd/MM/yyyy").parse(valorS);
	                    	pac.setFecha_nacimiento(valorD);
						} catch (ParseException e) {
							e.printStackTrace();
						}
                    } 
                }
                pacientes.add(pac);
            }
		}
		
		
		if (arraycuidadores!= null) {
			java.util.Iterator<JsonElement> iter = arraycuidadores.iterator();
			while(iter.hasNext()) {
				cui= new Cuidador();
				JsonElement elemento = iter.next();
				JsonObject objeto = elemento.getAsJsonObject();
				java.util.Set<java.util.Map.Entry<String, JsonElement>> entradas= objeto.entrySet();
				java.util.Iterator<java.util.Map.Entry<String, JsonElement>> iter2 = entradas.iterator();
				while(iter2.hasNext()) {
					java.util.Map.Entry<String,JsonElement> entrada = iter2.next();
                    String key = entrada.getKey();
                    if (key.equalsIgnoreCase("dni")) {
                    	cui.setDni(entrada.getValue().getAsString());
                    }
                    else if (key.equalsIgnoreCase("nombre")) {
                    	cui.setNombre(entrada.getValue().getAsString());
                    }
                    else if (key.equalsIgnoreCase("telefono")) {
                    	cui.setTelefono(entrada.getValue().getAsInt());
                    }
                    else if (key.equalsIgnoreCase("contrasena")) {
                    	cui.setContrasena(entrada.getValue().getAsString());;
                    }
				}
				cuidadores.add(cui);
			}
		}
		
		if (arraymedicos!= null) {
			java.util.Iterator<JsonElement> iter = arraymedicos.iterator();
			while(iter.hasNext()) {
				med= new Medico();
				JsonElement elemento = iter.next();
				JsonObject objeto = elemento.getAsJsonObject();
				java.util.Set<java.util.Map.Entry<String, JsonElement>> entradas= objeto.entrySet();
				java.util.Iterator<java.util.Map.Entry<String, JsonElement>> iter2 = entradas.iterator();
				while(iter2.hasNext()) {
					java.util.Map.Entry<String,JsonElement> entrada = iter2.next();
                    String key = entrada.getKey();
                    if (key.equalsIgnoreCase("dni")) {
                    	med.setDni(entrada.getValue().getAsString());
                    }
                    else if (key.equalsIgnoreCase("nombre")) {
                    	med.setNombre(entrada.getValue().getAsString());
                    }
                    else if (key.equalsIgnoreCase("telefono")) {
                    	med.setTelefono(entrada.getValue().getAsInt());
                    }
                    else if (key.equalsIgnoreCase("contrasena")) {
                    	med.setContrasena(entrada.getValue().getAsString());;
                    }
				}
				medicos.add(med);
			}
		}
		
	}
	
	public ArrayList<Paciente> devolverPacientes(){
		return pacientes;
	}
	
	public ArrayList<Cuidador> devolverCuidadores(){
		return cuidadores;
	}
	public ArrayList<Medico> devolverMedicos(){
		return medicos;
	}
}
