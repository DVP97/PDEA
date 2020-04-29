package controlador;

import java.awt.Font;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modelo.Cuidador;
import modelo.Paciente;
import modelo.Aviso;

public class ControladorCuidadorSensores implements Initializable {

	@FXML
	private AnchorPane anchorPaneAvisos;

	@FXML
	private Label campoCuidador;
	@FXML
	private Label campoPaciente;

    
	@FXML
	private JFXButton btnVolver;
	@FXML
	private JFXButton btnSensores;

	private static Paciente p = new Paciente();

	private ObservableList<Aviso> avisos = getAvisos();
	
	private baseDatos.FachadaBaseDatos fbd = application.Main.getFbd();

	@Override
	public void initialize(URL location, ResourceBundle reosurces) {

		Cuidador c = ControladorCuidadorpp.getCuidadorActual();
		campoCuidador.setText("Hola " + c.getNombre() + ",");
		campoPaciente.setText(p.getNombreCompleto());

	
		
		
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

			table.setLayoutX(5);
			table.setLayoutY(60);
			table.setItems(avisos);
			table.getColumns().addAll(columnaConcepto, columnaSensor);
			anchorPaneAvisos.getChildren().add(table);
			anchorPaneAvisos.setLeftAnchor(table, 0.0);
			anchorPaneAvisos.setRightAnchor(table, 0.0);
			anchorPaneAvisos.setTopAnchor(table, 0.0);
			anchorPaneAvisos.setBottomAnchor(table, 20.0);

		} else {
			Label tableEmpty = new Label("Todos los datos proporcionados por los sensores estan bien.");

			tableEmpty.setFont(new Font("Arial", 15));

			anchorPaneAvisos.getChildren().add(tableEmpty);
			anchorPaneAvisos.setLeftAnchor(tableEmpty, 0.0);
			anchorPaneAvisos.setRightAnchor(tableEmpty, 0.0);
			anchorPaneAvisos.setTopAnchor(tableEmpty, 0.0);
			anchorPaneAvisos.setBottomAnchor(tableEmpty, 80.0);
			tableEmpty.setAlignment(Pos.CENTER);

		}

	}


	@FXML
	void pressBtnVolver(ActionEvent event) throws IOException {
		try {

			Parent CuidadorVentana = FXMLLoader.load(getClass().getResource("/vista/cuidador_avisos_de_paciente.fxml"));
			Stage Cuidadorpp = new Stage();
			Cuidadorpp.setTitle("Menu Avisos Cuidador");
			Cuidadorpp.setScene(new Scene(CuidadorVentana));
			Cuidadorpp.show();
			Cuidadorpp.setMinHeight(400);
			Cuidadorpp.setMinWidth(616);

			Stage CerrarSensores = (Stage) btnVolver.getScene().getWindow();
			CerrarSensores.close();
		}

		catch (ControladorExcepciones case1) {
			ControladorAvisos.setMensajeError("No se pudo abrir el menu de avisos.");
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
		return p;
	}
	// SETTERS
	public static void setPacienteElegido(Paciente Pacient) {
		p = Pacient;
	}
	
	public ObservableList<Aviso> getAvisos() {
		
		ObservableList<Aviso> avisos = FXCollections.observableArrayList();
		avisos.addAll(lectorJson.crearAvisosSensor1(p.getDni()));
		avisos.addAll(lectorJson.crearAvisosSensor2(p.getDni()));
		avisos.addAll(lectorJson.crearAvisosSensor3(p.getDni()));

		return avisos;
	}


}