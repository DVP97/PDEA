package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Label;
import javafx.stage.Stage;
import modelo.Cuidador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class ControladorCuidadorpp implements Initializable {
	@FXML
	private Button btnAvisos;
	@FXML
	private Button btnEjercicios;
	@FXML
	private Button btnDatos;
	@FXML
	private ComboBox<?> comboPacientes;
	@FXML
	private Label campoCuidador;

	private static Cuidador cuidadorActual = new Cuidador();

	@Override
	public void initialize(URL location, ResourceBundle reosurces) {
		// add controlador para tomar solo el nombre(comprobar si cada caracter es un
		// espacio y cuando lo sea cortar el string ahi).
		campoCuidador.setText("Hola " + ControladorCuidadorpp.getCuidadorActual().getNombre() + ",");
	}

	@FXML
	private void pressBtnEjercicios(ActionEvent event) throws IOException {
		//try {
			System.out.println("Cargando ventana de Ejercicios...");
			// Parent Ejercicios =
			// FXMLLoader.load(getClass().getResource("/vista/pacientepp_ejercicios.fxml"));
			Stage EjerciciosPaciente = new Stage();
			EjerciciosPaciente.setTitle("Ejercicios Paciente");
			// EjerciciosPaciente.setScene(new Scene(Ejercicios));
			EjerciciosPaciente.show();
			EjerciciosPaciente.setMinHeight(620);
			EjerciciosPaciente.setMinWidth(600);

			Stage CambioVentanaEjs = (Stage) btnEjercicios.getScene().getWindow();
			CambioVentanaEjs.close();
			
		//} catch (ControladorExcepciones r) {
		//	ControladorAvisos.setMensajeError("No se pudo abrir la ventana de Ejercicios para Pacientes.");
		//	r.abrirVentanaAvisos();
		//}
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
