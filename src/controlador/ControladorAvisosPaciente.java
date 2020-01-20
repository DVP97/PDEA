package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;

import org.omg.CORBA.OMGVMCID;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import modelo.Cita;
import modelo.Paciente;

public class ControladorAvisosPaciente implements Initializable {

	@FXML
	private JFXButton btnAnterior;
	@FXML
	private JFXButton btnNext;
	@FXML
	private JFXButton btnVolver;
	@FXML
	private Label campoHechos;
	@FXML
	private Label campoFecha;
	@FXML
	private Label campoPaciente;

	private Cita fecha_cita;
	private boolean hechos;

	@Override
	public void initialize(URL location, ResourceBundle reosurces) {

		Paciente p = ControladorPacientepp.getPacienteActual();
		campoPaciente.setText("Hola " + p.getNombre() + ",");

		// Muestra el valor del booleano

		hechos = p.isEjerciciosHechos();

		if (hechos) {

			campoHechos.setText("Ejercicios acabados.");

		} else {
			campoHechos.setText("Ejercicios no acabados.");
		}
		// Muestra fecha de la cita
		Cita prox = seleccionarSiguienteCita(p);
		campoFecha.setText(prox.getFechaString());

	}

	// Botón volver
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

		catch (ControladorExcepciones case1) {
			ControladorAvisos.setMensajeError("No se pudo abrir la ventana de Paciente.");
			case1.abrirVentanaAvisos();
		}
	}

	public void abrirVentanaAvisos() {
		try {
			Parent avisos = FXMLLoader.load(getClass().getResource("../vista/avisos.fxml"));
			Stage VentanaAvisos = new Stage();
			VentanaAvisos.setTitle("Aviso");
			VentanaAvisos.setScene(new Scene(avisos));
			VentanaAvisos.show();
			VentanaAvisos.setMinHeight(200);
			VentanaAvisos.setMinWidth(500);
			VentanaAvisos.setMaxHeight(200);
			VentanaAvisos.setMaxWidth(600);

		} catch (Exception a) {
			System.out.println("Error");
		}
	}
	
	public class sortByDate implements Comparator<Cita>{
		@Override
		public int compare (Cita c1, Cita c2) {
			return c1.getFecha().compareTo(c2.getFecha());
		}
	}
	
	public Cita seleccionarSiguienteCita(Paciente p) {
		ArrayList<Cita> citas = lectorJson.getCitasPaciente(p.getDni());
		Collections.sort(citas, new sortByDate());
		return citas.get(0);
	}
}
