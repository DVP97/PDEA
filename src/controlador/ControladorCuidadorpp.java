package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import modelo.Cuidador;
import modelo.Paciente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	private JFXComboBox<String> comboPaciente;

	@FXML
	private JFXButton btnEjercicios;

	@FXML
	private JFXButton btnAvisos;

	private ArrayList<String> nombresPacientes = lectorJson.getNombresCompletosPacientesDeCuidador(cuidadorActual);

	private ObservableList<String> listaPacientesComboBox = FXCollections.observableArrayList(nombresPacientes);

	private static Cuidador cuidadorActual = ControladorCuidadorpp.getCuidadorActual();

	private static Paciente pacienteElegido = new Paciente();

	@Override
	public void initialize(URL location, ResourceBundle reosurces) {

		Cuidador c = ControladorCuidadorpp.getCuidadorActual();
		campoCuidador.setText("Hola " + c.getNombre() + ",");
		comboPaciente.setItems(listaPacientesComboBox);
		comboPaciente.arm();
	}

	@FXML
	void pressBtnEjercicios(ActionEvent event) throws IOException {
    	String pacienteBuscado = comboPaciente.getValue();

    	pacienteElegido = coincidencia(pacienteBuscado);
		
    	if (pacienteElegido != null) {
			try {
				System.out.println("Cargando Rutina ejercicios pacientes...");
				ControladorCuidadorEjerciciosPaciente.setPacienteElegido(pacienteElegido);
				Parent CuidadorEjercicios = FXMLLoader.load(getClass().getResource("/vista/cuidadorpp_ejercicios_de_paciente.fxml"));
				Stage CuidadorRutinaPaciente = new Stage();
				CuidadorRutinaPaciente.setTitle("Menu Cuidador - Ejercicios de " + pacienteElegido.getNombre());
				CuidadorRutinaPaciente.setScene(new Scene(CuidadorEjercicios));
				CuidadorRutinaPaciente.show();
				CuidadorRutinaPaciente.setMinHeight(600);
				CuidadorRutinaPaciente.setMinWidth(800);

				Stage CerrarVentanaCuidador = (Stage) btnEjercicios.getScene().getWindow();
				CerrarVentanaCuidador.close();
			}

			catch (ControladorExcepciones case1) {
				ControladorAvisos.setMensajeError("No se pudo abrir la ventana de Paciente.");
				case1.abrirVentanaAvisos();
			}
		} else {
			System.out.println("Paciente no seleccionado.");
		}
	}
	
 

	@FXML
	void pressBtnAvisos(ActionEvent event) throws IOException {
    	String pacienteBuscado = comboPaciente.getValue();

    	pacienteElegido = coincidencia(pacienteBuscado);
		
    	if (pacienteElegido != null) {
			try {
				System.out.println("Cargando ventana de Avisos...");
				ControladorCuidadorAvisos.setPacienteElegido(pacienteElegido);
				Parent Avisos = FXMLLoader.load(getClass().getResource("/vista/cuidadorpp_avisos_de_paciente.fxml"));
				Stage AvisosPaciente = new Stage();
				AvisosPaciente.setTitle("Menu Cuidador - Avisos de " + pacienteElegido.getNombre());
				AvisosPaciente.setScene(new Scene(Avisos));
				AvisosPaciente.show();
				AvisosPaciente.setMinHeight(800);
				AvisosPaciente.setMinWidth(1200);

				Stage CambioVentanaAvisos = (Stage) btnAvisos.getScene().getWindow();
				CambioVentanaAvisos.close();
			} catch (ControladorExcepciones r) {
				ControladorAvisos.setMensajeError("No se pudo abrir la ventana de Avisos para Cuidador.");
				r.abrirVentanaAvisos();
			}
		}
	}

	@FXML
	void comprobarInput(KeyEvent event) throws Exception {
		comboPaciente.arm();
		// comparar el nombre introducido con los pacientes asignados al medico, para
		// sugerir posibles coincidencias de forma dinamica

		ArrayList<String> nombresPacientes = lectorJson.getNombresCompletosPacientesDeCuidador(cuidadorActual);
		ArrayList<String> sugerencias = new ArrayList<String>();

		boolean sugerenciasEncontradas;
		if (comboPaciente.getValue() != null) {
			for (int i = 0; i < cuidadorActual.getPacientes().size(); i++) {
				// bucle que va comparando el input con el nombre de cada paciente
				int longitud = 0;

				for (int a = 0; a < comboPaciente.getValue().length(); a++) {
					// bucle que va comparando los char del input con los char del nombre de
					// paciente
					if (comboPaciente.getValue().toLowerCase().charAt(a) == nombresPacientes.get(i).toLowerCase().charAt(a)) {
						longitud++;
					} else {
						break;
					}
				}
				if (longitud == comboPaciente.getValue().length()) {
					// add nombre en posicion i a un nuevo arraylist que se pasa al comboBox con la
					// observableList listaSugerencias
					sugerencias.add(nombresPacientes.get(i));
					sugerenciasEncontradas = true;
				}
			}

			if (sugerenciasEncontradas = true) {
				comboPaciente.getItems().clear();
				ObservableList<String> listaSugerencias = FXCollections.observableArrayList(sugerencias);
				comboPaciente.setItems(listaSugerencias);
			}
		} else {
			// por defecto se muestra la lista entera de pacientes
			ObservableList<String> listaPacientesComboBox = FXCollections.observableArrayList(nombresPacientes);
			comboPaciente.setItems(listaPacientesComboBox);
		}
		comboPaciente.autosize();
		comboPaciente.show();
	}

	private Paciente coincidencia(String pacienteBuscado) {
		for (int i = 0; i < cuidadorActual.getPacientes().size(); i++) {
			ArrayList<String> nombresPacientes = lectorJson.getNombresCompletosPacientesDeCuidador(cuidadorActual);
			if (nombresPacientes.get(i).equalsIgnoreCase(pacienteBuscado)) {
				Paciente p = lectorJson.getPaciente(cuidadorActual.getPacientes().get(i));
				return p;
			}
		}
		return null;
	}

    
   
    
	// GETTERS
	public static Cuidador getCuidadorActual() {
		return cuidadorActual;
	}
	public static Paciente getPacienteElegido() {
		return pacienteElegido;
	}

	// SETTERS
	public static void setCuidadorActual(Cuidador CuidadorActual) {
		cuidadorActual = CuidadorActual;
	}
	public static void setPacienteElegido(Paciente pacienteElegido) {
		ControladorCuidadorpp.pacienteElegido = pacienteElegido;
	}
}
