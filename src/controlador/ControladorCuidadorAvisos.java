package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modelo.Cita;
import modelo.Cuidador;
import modelo.Paciente;

public class ControladorCuidadorAvisos implements Initializable {

	@FXML
	private JFXComboBox<?> campoPacientes;

	@FXML
	private AnchorPane anchorPaneAvisos;

	@FXML
	private Label campoCuidador;
	@FXML
	private Label campoPaciente;

    @FXML
    private Label citaAvisos;
    
	@FXML
	private JFXButton btnVolver;
	@FXML
	private JFXButton btnSensores;

	private Cita fecha_cita;

	private static Paciente pacienteActual = new Paciente();

	//private ObservableList<Aviso> avisos = getAvisos();
	
	private baseDatos.FachadaBaseDatos fbd = application.Main.getFbd();

	@Override
	public void initialize(URL location, ResourceBundle reosurces) {

		Cuidador c = ControladorCuidadorpp.getCuidadorActual();
		campoCuidador.setText("Hola " + c.getNombre() + ",");
		campoPaciente.setText(pacienteActual.getNombreCompleto());

	
		fecha_cita = seleccionarSiguienteCita(pacienteActual);
		citaAvisos.setText(fecha_cita.getFecha_cita());

	}


	@FXML
	void pressBtnVolver(ActionEvent event) throws IOException {
		try {

			Parent CuidadorVentana = FXMLLoader.load(getClass().getResource("/vista/cuidadorpp.fxml"));
			Stage Cuidadorpp = new Stage();
			Cuidadorpp.setTitle("Menu Cuidador");
			Cuidadorpp.setScene(new Scene(CuidadorVentana));
			Cuidadorpp.show();
			Cuidadorpp.setMinHeight(400);
			Cuidadorpp.setMinWidth(800);

			Stage CerrarEjercicios = (Stage) btnVolver.getScene().getWindow();
			CerrarEjercicios.close();
		}

		catch (ControladorExcepciones case1) {
			ControladorAvisos.setMensajeError("No se pudo abrir el menu de cuidador.");
			case1.abrirVentanaAvisos();
		}
	}
	
	@FXML
	void pressBtnSensores(ActionEvent event) throws IOException {
    	try {
    		this.pressBtnS();
    	}catch(ControladorExcepciones e) {
    		ControladorAvisos.setMensajeError("ERROR");
    		e.abrirVentanaAvisos();
    	}
	}

    private void pressBtnS() throws IOException  {
		

     			try {
     				System.out.println("Cargando ventana de Sensores...");

     				Parent Avisos = FXMLLoader.load(getClass().getResource("/vista/cuidador_sensores_de_paciente.fxml"));
     				Stage AvisosPaciente = new Stage();
     				AvisosPaciente.setTitle("Menu Cuidador - Avisos de " + pacienteActual.getNombre());
     				AvisosPaciente.setScene(new Scene(Avisos));
     				AvisosPaciente.show();
     				AvisosPaciente.setMinHeight(650);
     				AvisosPaciente.setMinWidth(700);
     				AvisosPaciente.setMaxWidth(700);
     				AvisosPaciente.setMaxHeight(750);
     				

     				System.out.println("Cerrando ventana avisos del Cuidador");
     				Stage CambioVentanaSensores = (Stage) btnSensores.getScene().getWindow();
     				CambioVentanaSensores.close();
     			} catch (ControladorExcepciones r) {
     				ControladorAvisos.setMensajeError("No se pudo abrir la ventana de Sensores para Cuidador.");
     				r.abrirVentanaAvisos();
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

	// GETTERS
	public static Paciente getPacienteElegido() {
		return pacienteActual;
	}
	// SETTERS
	public static void setPacienteElegido(Paciente PacienteElegido) {
		pacienteActual = PacienteElegido;
	}
	


	public class sortByDate implements Comparator<Cita> {
		@Override
		public int compare(Cita c1, Cita c2) {
			return c1.getFecha_cita().compareTo(c2.getFecha_cita());
			
			
		}
	}

	public Cita seleccionarSiguienteCita(Paciente p) {
		
		ArrayList<Cita> citas;
		citas = fbd.obtenerCitasPaciente(p);
		Collections.sort(citas, new sortByDate());
		return citas.get(0);
	}
}