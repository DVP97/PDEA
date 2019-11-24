package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import modelo.Paciente;

public class ControladorPacienteMensajes implements Initializable{

	@FXML
	private BorderPane BorderPaneGlobal;
	
	@FXML
	private JFXButton btnEnviar; 
	
	@FXML
	private JFXButton btnVolver;
	
	@FXML
    private Label campoPaciente;
	
	@FXML
	private Tab tabRecibidos;
	
	@FXML
	private TitledPane structMensajeRecib;
	
	@FXML
	private Label textoMensajeRecib;
	
	@FXML
	private Tab tabEnviados;
	
	@FXML
	private TitledPane structMensajeEnv;
	
	@FXML
	private Label textoMensajeEnv;
	
	@FXML
	private TextArea campoEscritura;
	
	
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
