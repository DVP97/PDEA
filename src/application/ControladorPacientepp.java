package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.gluonhq.charm.glisten.control.TextField;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


public class ControladorPacientepp implements Initializable {

    @FXML
    private JFXButton btnAvisos;

    @FXML
    private JFXButton btnEjercicios;

    @FXML
    private JFXButton btnMensajes;
    
    @FXML
    private TextField campoPaciente;

    private static Paciente pacienteActual = new Paciente();
    
    
    @Override
    public void initialize(URL location, ResourceBundle reosurces) {
    //añadir controlador para tomar solo el nombre(comprobar si cada caracter es un espacio y cuando lo sea cortar el string ahí).
    campoPaciente.setText("Hola " +ControladorPacientepp.getPacienteActual().getNombre()+",");
    
	}
    
  //Getters y Setters
    public static Paciente getPacienteActual() {
  		return pacienteActual;
  	}

	public static void setPacienteActual(Paciente PacienteActual) {
  		pacienteActual = PacienteActual;
  	}
}
