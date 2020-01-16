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
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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
    private LineChart<?, ?> LineChartFrec;

    @FXML
    private CategoryAxis LineChartFrec_X;

    @FXML
    private NumberAxis LineChartFrec_Y;

    @FXML
    private LineChart<?, ?> LineChartTension;

    @FXML
    private CategoryAxis LineChartTension_X;

    @FXML
    private NumberAxis LineChartTension_Y;

    @FXML
    private LineChart<?, ?> LineChartSatur;

    @FXML
    private CategoryAxis LineChartSatur_X;

    @FXML
    private NumberAxis LineChartSatur_Y;
    
    private static Paciente pacienteActual = new Paciente();
    
    private static Medico medicoActual = new Medico();
    
    private static datoSensor1 sensor1Actual = new datoSensor1();
    
    
    private static datoSensor2 sensor2Actual = new datoSensor2();
    
    private static datoSensor3 sensor3Actual = new datoSensor3();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	System.out.println(medicoActual.getNombre());
    	System.out.println(pacienteActual.getNombre());
    	campoMedico.setText("Hola " +ControladorMedicopp.getMedicoActual().getNombre()+",");
    	campoPaciente.setText(ControladorMedicoSubmenuPaciente.getPacienteActual().getNombreCompleto());
    	
    	sensor1Actual = lectorJson.getSensor1(ControladorMedicoSubmenuPaciente.getPacienteActual().getDni());
        
    	/*
        ArrayList<Integer> dataS1 = sensor1Actual.getFrecuenciaAntes();
       
        ObservableList<Integer> selectedDataS1 = FXCollections.observableArrayList(dataS1);
       */
    	
    	sensor2Actual = lectorJson.getSensor2(ControladorMedicoSubmenuPaciente.getPacienteActual().getDni());
    	sensor3Actual = lectorJson.getSensor3(ControladorMedicoSubmenuPaciente.getPacienteActual().getDni());
    	
    	LineChartFrec.getData();
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

}