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
    	
    	InicializarGraficaFrecuencia();
    	InicializarGraficasTension();
  //  	InicializarGráficaSaturacion();
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

    void InicializarGraficaFrecuencia(){
    	
    	ArrayList<Integer> arrayListFrecAntes = lectorJson.getSensor1FrecuenciaAntes(pacienteActual.getDni());
    	ArrayList<Integer> arrayListFrecDespues = lectorJson.getSensor1FrecuenciaDespues(pacienteActual.getDni());
    	ArrayList<String> arrayListFechas = lectorJson.getFechaSensor1(pacienteActual.getDni());
    	
    	CategoryAxis xAxis = new CategoryAxis(FXCollections.observableArrayList(arrayListFechas));
    	NumberAxis yAxis = new NumberAxis(0,100,5);
    	
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

    void InicializarGraficasTension(){
    	
    	ArrayList<Integer> arrayListSistoleAntes = lectorJson.getSistoleAntesSensor3(pacienteActual.getDni());
    	ArrayList<Integer> arrayListSistoleDespues = lectorJson.getSistoleDespuesSensor3(pacienteActual.getDni());    	
    	
    	ArrayList<Integer> arrayListDiastoleAntes = lectorJson.getDiastoleAntesSensor3(pacienteActual.getDni());
    	ArrayList<Integer> arrayListDiastoleDespues = lectorJson.getDiastoleDespuesSensor3(pacienteActual.getDni());
    	
    	ArrayList<String> arrayListFechas3 = lectorJson.getFechaSensor3(pacienteActual.getDni());
    	
    	CategoryAxis xAxis = new CategoryAxis(FXCollections.observableArrayList(arrayListFechas3));
    	NumberAxis yAxis = new NumberAxis(0,180,5);
    	
    	CategoryAxis xAxisDias = new CategoryAxis(FXCollections.observableArrayList(arrayListFechas3));
    	NumberAxis yAxisDias = new NumberAxis(0,180,5);
    	
    	LineChart<String,Number> Sistoles = new LineChart(xAxis,yAxis);

    	LineChart<String,Number> Diastoles = new LineChart(xAxisDias,yAxisDias);
    	
    	Sistoles.setTitle("Sistoles");
    	XYChart.Series series = new XYChart.Series<>();
    	for(int i =0; i<arrayListSistoleAntes.size();i++) {
    		series.getData().add(new XYChart.Data<>(arrayListFechas3.get(i),arrayListSistoleAntes.get(i)));
    	}
    	XYChart.Series series2 = new XYChart.Series<>();
    	for(int i =0; i<arrayListSistoleDespues.size();i++) {
    		series2.getData().add(new XYChart.Data<>(arrayListFechas3.get(i),arrayListSistoleDespues.get(i)));
    	}
    	Sistoles.getData().addAll(series,series2);
    	
    	Diastoles.setTitle("Diastoles");
    	XYChart.Series seriesDiast = new XYChart.Series<>();
    	for(int i =0; i<arrayListDiastoleAntes.size();i++) {
    		seriesDiast.getData().add(new XYChart.Data<>(arrayListFechas3.get(i),arrayListDiastoleAntes.get(i)));
    	}
    	XYChart.Series seriesDiast2 = new XYChart.Series<>();
    	for(int i =0; i<arrayListDiastoleDespues.size();i++) {
    		seriesDiast2.getData().add(new XYChart.Data<>(arrayListFechas3.get(i),arrayListDiastoleDespues.get(i)));
    	}
    	Diastoles.getData().addAll(seriesDiast,seriesDiast2);
    	
    	anchorPaneTension.getChildren().addAll(Sistoles,Diastoles);

    	anchorPaneTension.setTopAnchor(Sistoles, 0.0);
    	anchorPaneTension.setBottomAnchor(Sistoles, 200.0);
    	anchorPaneTension.setLeftAnchor(Sistoles, 0.0);
    	anchorPaneTension.setRightAnchor(Sistoles, 0.0);
    	
    	anchorPaneTension.setBottomAnchor(Diastoles, -100.0);
    	anchorPaneTension.setLeftAnchor(Diastoles, 0.0);
    	anchorPaneTension.setRightAnchor(Diastoles, 0.0);
    }
 
/*
    void InicializarGráficaSaturacion(){
    	
    	ArrayList<Integer> arrayListDatosMedicos = lectorJson.getDatosSensor2(pacienteActual.getDni());
    	
    	//System.out.println(arrayListDatosMedicos);
    	
    	ArrayList<String> arrayListFechas = lectorJson.getFechaSensor2(pacienteActual.getDni());
    	
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
    	
    	AnchorPaneSaturacion.getChildren().add(Frecuencias);
    	AnchorPaneSaturacion.setTopAnchor(Frecuencias, 0.0);
    	AnchorPaneSaturacion.setBottomAnchor(Frecuencias, -100.0);
    	AnchorPaneSaturacion.setLeftAnchor(Frecuencias, 0.0);
    	AnchorPaneSaturacion.setRightAnchor(Frecuencias, 0.0);
    	
    }
    */
}