package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import modelo.Paciente;


public class ControladorPacientepp implements Initializable {

    @FXML
    private JFXButton btnAvisos;

    @FXML
    private JFXButton btnEjercicios;

    @FXML
    private JFXButton btnMensajes;
    
    @FXML
    private Label campoPaciente;

    private static Paciente pacienteActual = new Paciente();
    
    
    @Override
    public void initialize(URL location, ResourceBundle reosurces) {
    //add controlador para tomar solo el nombre(comprobar si cada caracter es un espacio y cuando lo sea cortar el string ahi).
    	campoPaciente.setText("Hola " +ControladorPacientepp.getPacienteActual().getNombre() +",");
	}
    
    @FXML
    void pressBtnMensajes(ActionEvent event) throws IOException  {
    	try {
        	System.out.println("Cargando ventana de Mensajes...");
    		Parent Mensajeria = FXMLLoader.load(getClass().getResource("/vista/pacientepp_mensajeria.fxml"));
    		Stage MensajeriaPaciente = new Stage();
    		MensajeriaPaciente.setTitle("Mensajeria Paciente");
    		MensajeriaPaciente.setScene(new Scene(Mensajeria));
    		MensajeriaPaciente.show();
    		MensajeriaPaciente.setMinHeight(525);
    		MensajeriaPaciente.setMinWidth(620);
    		
    		Stage CambioVentanaEjs = (Stage) btnEjercicios.getScene().getWindow();
    		CambioVentanaEjs.close();
        	}
        	catch(ControladorExcepciones r){
        		ControladorAvisos.setMensajeError("No se pudo abrir la ventana de Mensajeria para Pacientes.");
        		r.abrirVentanaAvisos();
        	}
    }
    
    @FXML
    
    void pressBtnAvisos(ActionEvent event) throws IOException {
    	try {
    		System.out.println("Cargando ventana de Avisos...");
    		Parent VentanaAvisos = FXMLLoader.load(getClass().getResource("/vista/pacientepp_avisos.fxml"));
    		Stage AvisosPaciente = new Stage();
    		AvisosPaciente.setTitle("Avisos Paciente");
    		AvisosPaciente.setScene(new Scene(VentanaAvisos));
    		AvisosPaciente.show();
    		AvisosPaciente.setMinHeight(525);
    		AvisosPaciente.setMinWidth(620);
    		
    	}catch (ControladorExcepciones r) {
    		ControladorAvisos.setMensajeError("No se pudo abrir la ventana de Avisos para Pacientes.");
    		r.abrirVentanaAvisos();
    	}
    	
    }
    
  //Getters y Setters
    public static Paciente getPacienteActual() {
  		return pacienteActual;
  	}

	public static void setPacienteActual(Paciente PacienteActual) {
  		pacienteActual = PacienteActual;
  	}
	

}
