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
	
		
		
		/*if (avisos.size() > 0) {
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

		}*/

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
		return pacienteActual;
	}
	// SETTERS
	public static void setPacienteElegido(Paciente PacienteElegido) {
		pacienteActual = PacienteElegido;
	}
	
	/*public ObservableList<Aviso> getAvisos() {
		
		ObservableList<Aviso> avisos = FXCollections.observableArrayList();
		avisos.addAll(lectorJson.crearAvisosSensor1(p.getDni()));
		avisos.addAll(lectorJson.crearAvisosSensor2(p.getDni()));
		avisos.addAll(lectorJson.crearAvisosSensor3(p.getDni()));

		return avisos;
	}*/



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