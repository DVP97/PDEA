package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.regexp.joni.constants.AnchorType;
import javafx.scene.chart.XYChart.Series;
import modelo.Medico;
import modelo.Paciente;
import modelo.datoSensor1;
import modelo.datoSensor2;
import modelo.datoSensor3;

public class ControladorMedicoSensoresPaciente implements Initializable {

	 @FXML
	 private Label campoMedico;

	 @FXML
	 private Text campoPaciente;
	
	@FXML
	private JFXButton buttonVolver;
	
	@FXML
	private AnchorPane AnchorPaneFrecuencia;
	
	@FXML
	private AnchorPane anchorPaneTension;
	
	@FXML
	private AnchorPane AnchorPaneSaturacion;
    
    private static Paciente pacienteActual = new Paciente();
    
    private static Medico medicoActual = new Medico();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	medicoActual = ControladorMedicopp.getMedicoActual();
    	pacienteActual = ControladorMedicoSubmenuPaciente.getPacienteActual();
    	campoMedico.setText("Hola " +medicoActual.getNombre()+",");
    	campoPaciente.setText(pacienteActual.getNombreCompleto());
    	
    	InicializarGráficaFrecuencia();
    	
    }
  
    @FXML
    void pressBtnVolver(ActionEvent event) throws IOException {
		try {
			System.out.println("Cargando submenu paciente...");
			Parent medicoSubMenuPaciente = FXMLLoader.load(getClass().getResource("/vista/medico_submenu_paciente.fxml"));
			Stage subMenuPaciente = new Stage();
			subMenuPaciente.setTitle("Menu Medico - Seleccion Paciente");
			subMenuPaciente.setScene(new Scene(medicoSubMenuPaciente));
			subMenuPaciente.show();
			subMenuPaciente.setMinHeight(600);
			subMenuPaciente.setMinWidth(800);

			Stage CerrarVentanaSensores = (Stage) buttonVolver.getScene().getWindow();
			CerrarVentanaSensores.close();
		}	
			
		catch(ControladorExcepciones case1){
			ControladorAvisos.setMensajeError("No se pudo abrir la ventana de Submenu.");
			case1.abrirVentanaAvisos();
		}
	}

    void InicializarGráficaFrecuencia(){
    	
    	ArrayList<Integer> arrayListFrecAntes = lectorJson.getSensor1FrecuenciaAntes(pacienteActual.getDni());
    	ArrayList<Integer> arrayListFrecDespues = lectorJson.getSensor1FrecuenciaDespues(pacienteActual.getDni());
    	ArrayList<String> arrayListFechas = lectorJson.getFechaSensor1(pacienteActual.getDni());
    	
    	CategoryAxis xAxis = new CategoryAxis(FXCollections.observableArrayList(arrayListFechas));
    	NumberAxis yAxis = new NumberAxis(0,100,1);
    	
    	LineChart<String,Number> Frecuencias = new LineChart(xAxis,yAxis);

    	Frecuencias.setTitle(" Frecuencia cardiaca");
    	XYChart.Series series = new XYChart.Series<>();
    	for(int i =0; i<arrayListFrecAntes.size();i++) {
    		series.getData().add(new XYChart.Data<>(arrayListFechas.get(i),arrayListFrecAntes.get(i)));
    	}
    	XYChart.Series series2 = new XYChart.Series<>();
    	for(int i =0; i<arrayListFrecDespues.size();i++) {
    		series2.getData().add(new XYChart.Data<>(arrayListFechas.get(i),arrayListFrecDespues.get(i)));
    	}
    	Frecuencias.getData().addAll(series,series2);
    	
    	AnchorPaneFrecuencia.getChildren().add(Frecuencias);
    	AnchorPaneFrecuencia.setTopAnchor(Frecuencias, 0.0);
    	AnchorPaneFrecuencia.setBottomAnchor(Frecuencias, -100.0);
    	AnchorPaneFrecuencia.setLeftAnchor(Frecuencias, 0.0);
    	AnchorPaneFrecuencia.setRightAnchor(Frecuencias, 0.0);
    }
    
}