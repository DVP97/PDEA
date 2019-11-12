package application;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import modelo.Cuidador;
import modelo.Medico;
import modelo.Paciente;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class Main extends Application {

	private static ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
	private static ArrayList<Medico> medicos= new ArrayList<Medico>();
	private static ArrayList<Cuidador> cuidadores = new ArrayList<Cuidador>();

	
	@Override
	public void start(Stage primaryStage) {

		
		try {
			Parent Login = FXMLLoader.load(getClass().getResource("/vista/login.fxml"));
			primaryStage.setTitle("PDEA Login");
			primaryStage.setScene(new Scene(Login));
			primaryStage.show();
			primaryStage.setMinHeight(340);
			primaryStage.setMinWidth(520);
			primaryStage.setMaxHeight(500);
			primaryStage.setMaxWidth(520);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		launch(args);
		
	}
	
	//GETTERS
	public static ArrayList<Paciente> getPacientes() {
		return pacientes;
	}
	public static ArrayList<Cuidador> getCuidadores() {
		return cuidadores;
	}
	public static ArrayList<Medico> getMedicos() {
		return medicos;
	}
	
	//SETTERS
	public static void setCuidadores(ArrayList<Cuidador> cuidadores) {
		Main.cuidadores = cuidadores;
	}
	public static void setMedicos(ArrayList<Medico> medicos) {
		Main.medicos = medicos;
	}
	public static void setPacientes(ArrayList<Paciente> pacientes) {
		Main.pacientes = pacientes;
	}
	
}
