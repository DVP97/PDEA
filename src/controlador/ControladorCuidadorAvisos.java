package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import modelo.Aviso;
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
	private JFXButton btnVolver;

	private Cita fecha_cita;

	private static Paciente pacienteElegido = new Paciente();

	private ObservableList<Aviso> avisos = getAvisos();

	@Override
	public void initialize(URL location, ResourceBundle reosurces) {

		Cuidador c = ControladorCuidadorpp.getCuidadorActual();
		campoCuidador.setText("Hola " + c.getNombre() + ",");

		campoPaciente.setText(pacienteElegido.getNombreCompleto());

		// Recoge fecha de la cita
		fecha_cita = lectorJson.getCita(pacienteElegido.getDni());

	if (avisos.size() > 0) {
			TableView<Aviso> table = new TableView<>();
			// Primera columna
			TableColumn<Aviso, String> columnaSensor = new TableColumn<>("Sensor");
			columnaSensor.setMinWidth(200);
			columnaSensor.setCellValueFactory(new PropertyValueFactory<>("nombreSensor"));

			// Segunda columna
			TableColumn<Aviso, String> columnaConcepto = new TableColumn<>("Concepto");
			columnaConcepto.setMinWidth(600);
			columnaConcepto.setCellValueFactory(new PropertyValueFactory<>("concepto"));

			table = new TableView<>();
			table.setLayoutX(5);
			table.setLayoutY(60);
			table.setItems(avisos);
			
			anchorPaneAvisos.getChildren().add(table);
			anchorPaneAvisos.setTopAnchor(table, 0.0);
		
		}
		else {
			Label tableEmpty = new Label ("Todos los datos proporcionados por los sensores estan bien.");
			
			tableEmpty.setFont(new Font("Arial", 10));
			tableEmpty.setLayoutY(60);
			tableEmpty.setLayoutX(5);
			anchorPaneAvisos.getChildren().add(tableEmpty);
		
		}
		
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
		return pacienteElegido;
	}

	public ObservableList<Aviso> getAvisos() {
		ObservableList<Aviso> avisos = FXCollections.observableArrayList();
		avisos.addAll(lectorJson.crearAvisosSensor1(pacienteElegido.getDni()));
		avisos.addAll(lectorJson.crearAvisosSensor2(pacienteElegido.getDni()));
		avisos.addAll(lectorJson.crearAvisosSensor3(pacienteElegido.getDni()));

		return avisos;
	}

	// SETTERS
	public static void setPacienteElegido(Paciente PacienteElegido) {
		pacienteElegido = PacienteElegido;
	}
}