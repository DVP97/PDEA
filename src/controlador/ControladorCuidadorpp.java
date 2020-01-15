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

    @FXML
    private JFXButton btnDatos;

	private static Cuidador cuidadorActual = new Cuidador();

	@Override
	public void initialize(URL location, ResourceBundle reosurces) {

		campoCuidador.setText("Hola " + ControladorCuidadorpp.getCuidadorActual().getNombre() + ",");
	}

	@FXML
	 void pressBtnEjercicios(ActionEvent event) throws IOException {
		try {
			System.out.println("Cargando Rutina ejercicios pacientes...");
			Parent CuidadorEjercicios = FXMLLoader.load(getClass().getResource("/vista/cuidador_ejercicios_paciente.fxml"));
			Stage CuidadorRutinaPaciente = new Stage();
			CuidadorRutinaPaciente.setTitle("Menu Cuidador - Ejercicios del Paciente");
			CuidadorRutinaPaciente.setScene(new Scene(CuidadorEjercicios));
			CuidadorRutinaPaciente.show();
			CuidadorRutinaPaciente.setMinHeight(400);
			CuidadorRutinaPaciente.setMinWidth(800);

			Stage CerrarVentanaCuidador = (Stage) btnEjercicios.getScene().getWindow();
			CerrarVentanaCuidador.close();
		}	
			
		catch(ControladorExcepciones case1){
			ControladorAvisos.setMensajeError("No se pudo abrir la ventana de Paciente.");
			case1.abrirVentanaAvisos();
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
