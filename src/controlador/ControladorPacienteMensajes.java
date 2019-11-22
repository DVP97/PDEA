package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import modelo.Paciente;

public class ControladorPacienteMensajes implements Initializable{

	
	
	@FXML
	private JFXButton btnEnviar; 
	
	@FXML
    private Label campoPaciente;
	
	private static Paciente pacienteActual = new Paciente();
	
	
	 @Override
	 public void initialize(URL location, ResourceBundle reosurces) {
		 campoPaciente.setText("Hola " +ControladorPacientepp.getPacienteActual().getNombre()+",");
	}
	 
	 
	 //GETTER
	 public static Paciente getPacienteActual() {
		return pacienteActual;
	}
	 
	 //SETTER
	 public static void setPacienteActual(Paciente pacienteActual) {
		 ControladorPacienteMensajes.pacienteActual = pacienteActual;
	}
}
