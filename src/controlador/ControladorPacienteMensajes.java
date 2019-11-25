package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import modelo.Paciente;

public class ControladorPacienteMensajes implements Initializable{

	
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

	@FXML
	void pressBtnEnviar(ActionEvent event) {
		
	}
	
	@FXML
	void pressBtnVolver(ActionEvent event) throws IOException {
		try {
			System.out.println("Cargando ventana principal de Paciente...");
			Parent PacienteVentana = FXMLLoader.load(getClass().getResource("/vista/menupaciente.fxml"));
			Stage Pacientepp = new Stage();
			Pacientepp.setTitle("Menu Principal Paciente");
			Pacientepp.setScene(new Scene(PacienteVentana));
			Pacientepp.show();
			Pacientepp.setMinHeight(550);
			Pacientepp.setMinWidth(500);

			System.out.println("Cerrando ventana de Login.");
			Stage CerrarVentanaLogin = (Stage) btnVolver.getScene().getWindow();
			CerrarVentanaLogin.close();
		}	
		
		catch(ControladorExcepciones case1){
			ControladorAvisos.setMensajeError("No se pudo abrir la ventana de Paciente.");
			case1.abrirVentanaAvisos();
		}
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
