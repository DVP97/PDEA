package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ControladorEjercicios implements Initializable{
	
	@FXML
    private Button btnAnterior;
	
	@FXML
    private Button btnComenzar;
	
	@FXML
    private Button btnSiguiente;
	
	@FXML
	private Label cronometro;

    @FXML
    private Label campoPacienteEj;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		campoPacienteEj.setText("Hola " +ControladorPacientepp.getPacienteActual().getNombre() +",");
	}
	
	

}
