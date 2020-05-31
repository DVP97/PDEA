package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.TimeZone;

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
import modelo.Pulsiometro;
import modelo.Oximetro;
import modelo.Presion;

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
    
    private baseDatos.FachadaBaseDatos fbd = application.Main.getFbd();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	medicoActual = ControladorMedicopp.getMedicoActual();
    	pacienteActual = ControladorMedicoSubmenuPaciente.getPacienteActual();
    	campoMedico.setText("Hola " +medicoActual.getNombre()+",");
    	campoPaciente.setText(pacienteActual.getNombreCompleto());
    	
    	InicializarGraficaFrecuencia();
    	InicializarGraficaPresion();
    	InicializarGraficaSaturacion();
    }
  
    @FXML
    void pressBtnVolver(ActionEvent event) throws IOException {
		try {
			System.out.println("Cargando submenu paciente...");
			Parent medicoSubMenuPaciente = FXMLLoader.load(getClass().getResource("/vista/medico_submenu_paciente.fxml"));
			Stage subMenuPaciente = new Stage();
			subMenuPaciente.setTitle("Menu " +pacienteActual.getNombreCompleto());
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
    	ArrayList<Pulsiometro> arraypulsiometro = fbd.getDatosSensor1De((pacienteActual.getDni()));
    	ArrayList<Integer> arrayListFrecAntes = new ArrayList<Integer>();
    	for(int i =0; i < arraypulsiometro.size(); i++){
    		Pulsiometro p = arraypulsiometro.get(i);
    		arrayListFrecAntes.add(p.getDato());
    	}
    	ArrayList<String> arrayListFechas = new ArrayList<String>();
    	for(int i =0; i < arraypulsiometro.size(); i++){
    		Pulsiometro p = arraypulsiometro.get(i);
    		arrayListFechas.add(getFechaString(p.getFecha()));
    	}
    	
    	CategoryAxis xAxis = new CategoryAxis(FXCollections.observableArrayList(arrayListFechas));
    	NumberAxis yAxis = new NumberAxis(0,120,5);
    	
    	LineChart<String,Number> Frecuencias = new LineChart(xAxis,yAxis);

    	Frecuencias.setTitle(" Frecuencia cardiaca");
    	XYChart.Series series = new XYChart.Series<>();
    	for(int i =0; i<arrayListFrecAntes.size();i++) {
    		series.getData().add(new XYChart.Data<>(arrayListFechas.get(i),arrayListFrecAntes.get(i)));
    	}
    	Frecuencias.getData().addAll(series);
    	
    	AnchorPaneFrecuencia.getChildren().add(Frecuencias);
    	AnchorPaneFrecuencia.setTopAnchor(Frecuencias, 0.0);
    	AnchorPaneFrecuencia.setBottomAnchor(Frecuencias, -35.0);
    	AnchorPaneFrecuencia.setLeftAnchor(Frecuencias, 0.0);
    	AnchorPaneFrecuencia.setRightAnchor(Frecuencias, 0.0);
    	
    }

    void InicializarGraficaPresion(){
    	ArrayList<Presion> arraypresion = fbd.getDatosSensor3De((pacienteActual.getDni()));
    	ArrayList<Integer> arrayListPresion = new ArrayList<Integer>();
    	for(int i =0; i < arraypresion.size(); i++){
    		Presion p = arraypresion.get(i);
    		arrayListPresion.add(p.getDato());
    	}
    	
    	ArrayList<String> arrayListFechas3 = new ArrayList<String>();
    	for(int i =0; i < arraypresion.size(); i++){
    		Presion p = arraypresion.get(i);
    		arrayListFechas3.add(getFechaString(p.getFecha()));
    	}
    	CategoryAxis xAxis = new CategoryAxis(FXCollections.observableArrayList(arrayListFechas3));
    	NumberAxis yAxis = new NumberAxis(0,180,5);
    	
    	LineChart<String,Number> Sistoles = new LineChart(xAxis,yAxis);
    	
    	Sistoles.setTitle("Presion");
    	XYChart.Series series = new XYChart.Series<>();
    	for(int i =0; i<arrayListPresion.size();i++) {
    		series.getData().add(new XYChart.Data<>(arrayListFechas3.get(i),arrayListPresion.get(i)));
    	}
    	Sistoles.getData().addAll(series);
    	
    	anchorPaneTension.getChildren().addAll(Sistoles);

    	anchorPaneTension.setTopAnchor(Sistoles, 0.0);
    	anchorPaneTension.setBottomAnchor(Sistoles, -35.0);
    	anchorPaneTension.setLeftAnchor(Sistoles, 0.0);
    	anchorPaneTension.setRightAnchor(Sistoles, 0.0);
    }

	void InicializarGraficaSaturacion(){
    	 ArrayList<Oximetro> arrayoximetro = fbd.getDatosSensor2De((pacienteActual.getDni()));
    	ArrayList<Integer> arrayListDatosMedicos = new ArrayList<Integer>();
    	for(int i =0; i < arrayoximetro.size(); i++){
    		Oximetro p = arrayoximetro.get(i);
    		arrayListDatosMedicos.add(p.getDatosMedicos());
    	}
    	ArrayList<String> arrayListFechas2 = new ArrayList<String>();
    	for(int i =0; i < arrayoximetro.size(); i++){
    		Oximetro p = arrayoximetro.get(i);
    		arrayListFechas2.add(getFechaString(p.getFecha()));
    	}
    	CategoryAxis xAxis = new CategoryAxis(FXCollections.observableArrayList(arrayListFechas2));
    	NumberAxis yAxis = new NumberAxis(0,100,1);
 	
    	LineChart<String,Number> Frecuencias = new LineChart(xAxis,yAxis);

    	Frecuencias.setTitle(" Saturacion O2");
    	XYChart.Series series = new XYChart.Series<>();
    	for(int i =0; i<arrayListDatosMedicos.size();i++) {
    		series.getData().add(new XYChart.Data<>(arrayListFechas2.get(i),arrayListDatosMedicos.get(i)));
    	}
    	
    	Frecuencias.getData().add(series);
    	
    	AnchorPaneSaturacion.getChildren().add(Frecuencias);
    	AnchorPaneSaturacion.setTopAnchor(Frecuencias, 0.0);
    	AnchorPaneSaturacion.setBottomAnchor(Frecuencias, -35.0);
    	AnchorPaneSaturacion.setLeftAnchor(Frecuencias, 0.0);
    	AnchorPaneSaturacion.setRightAnchor(Frecuencias, 0.0);
    	
    }
    
 	public String getFechaString(Date dummy) {
		// Choose time zone in which you want to interpret your Date
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));

		cal.setTime(dummy);
		String dia = ((Integer) dummy.getDate()).toString();
		int m = dummy.getMonth() + 1;
		String mes = ((Integer) m).toString();
		int year = cal.get(Calendar.YEAR);
		String anho = ((Integer) year).toString();
		//String hora = ((Integer) dummy.getHours()).toString();
		//String min = ((Integer) dummy.getMinutes()).toString();
		String f = dia + "/" + mes + "/" + anho;
		return f;
	}
}