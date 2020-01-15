package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import javafx.scene.control.Label;
import javafx.stage.Stage;
import modelo.Cuidador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class ControladorCuidadorpp implements Initializable {

    @FXML
    private Label campoCuidador;

    @FXML
    private JFXComboBox<?> comboPaciente;

    @FXML
    private JFXButton btnEjercicios;

    @FXML
    private JFXButton btnAvisos;


	private static Cuidador cuidadorActual = new Cuidador();

	@Override
	public void initialize(URL location, ResourceBundle reosurces) {

    	Cuidador c = ControladorCuidadorpp.getCuidadorActual();
		campoCuidador.setText("Hola " +c.getNombre()+",");
	}

	@FXML
	 void pressBtnEjercicios(ActionEvent event) throws IOException {
		try {
			System.out.println("Cargando Rutina ejercicios pacientes...");
			Parent CuidadorEjercicios = FXMLLoader.load(getClass().getResource("/vista/cuidadorpp_ejercicios_de_paciente.fxml"));
			Stage CuidadorRutinaPaciente = new Stage();
			CuidadorRutinaPaciente.setTitle("Menu Cuidador - Ejercicios del Paciente");
			CuidadorRutinaPaciente.setScene(new Scene(CuidadorEjercicios));
			CuidadorRutinaPaciente.show();
			CuidadorRutinaPaciente.setMinHeight(400);
			CuidadorRutinaPaciente.setMinWidth(400);

			Stage CerrarVentanaCuidador = (Stage) btnEjercicios.getScene().getWindow();
			CerrarVentanaCuidador.close();
		}	
			
		catch(ControladorExcepciones case1){
			ControladorAvisos.setMensajeError("No se pudo abrir la ventana de Paciente.");
			case1.abrirVentanaAvisos();
		}
	}


    @FXML
    void pressBtnAvisos(ActionEvent event) throws IOException {
    	try {
    		System.out.println("Cargando ventana de Avisos...");
    		Parent Avisos = FXMLLoader.load(getClass().getResource("/vista/cuidadorpp_avisos_de_paciente.fxml"));
    		Stage AvisosPaciente = new Stage();
    		AvisosPaciente.setTitle("Menu Cuidador - Avisos del Paciente");
    		AvisosPaciente.setScene(new Scene(Avisos));
    		AvisosPaciente.show();
    		AvisosPaciente.setMinHeight(325);
    		AvisosPaciente.setMinWidth(600);
    		
    		Stage CambioVentanaAvisos = (Stage) btnAvisos.getScene().getWindow();
    		CambioVentanaAvisos.close();
    	}
    	catch (ControladorExcepciones r) {
    		ControladorAvisos.setMensajeError("No se pudo abrir la ventana de Avisos para Cuidador.");
    		r.abrirVentanaAvisos();
    	}
    }
	// GETTERS
	public static Cuidador getCuidadorActual() {
		return cuidadorActual;
	}

	// SETTERS
	public static void setCuidadorActual(Cuidador CuidadorActual) {
		cuidadorActual = CuidadorActual;
	}
}
