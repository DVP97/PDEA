package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import modelo.Medico;


public class ControladorMedicoSelectorPaciente implements Initializable{

    @FXML
    private Label campoMedico;

    @FXML
    private JFXTextField inputBuscarPaciente;

    @FXML
    private JFXButton btnBuscarPaciente;

    @FXML
    private JFXButton btnMenuGeneral;
    
    
    //Metodos
    
    @Override
    public void initialize(URL location, ResourceBundle reosurces) {
    	Medico m = ControladorMedicopp.getMedicoActual();
    	campoMedico.setText("Hola " +ControladorMedicopp.getMedicoActual().getNombre()+",");

    }

    @FXML
    void comprobarInput(KeyEvent event) {
    	//comparar el nombre introducido con los pacientes asignados al medico, para sugerir posibles coincidencias de forma dinamica 
    }

    @FXML
    void pressBtnBuscarPaciente(ActionEvent event) {
    	//comparar el nombre introducido con los pacientes asignados al medico
    	
    	// imprimir mensaje de aviso en caso de no encontrar coincidencia alguna

    		ControladorAvisos.setMensajeError("No se ha encontrado el paciente introducido.");
    		abrirVentanaAvisos();
    }

    @FXML
    void pressBtnMenuGeneral(ActionEvent event) throws IOException{
    	//abre la ventana general de médico
    	try {
			System.out.println("Cargando ventana principal de Medico...");
			Parent MedicoVentana = FXMLLoader.load(getClass().getResource("/vista/medicopp.fxml"));
			Stage Medicopp = new Stage();
			Medicopp.setTitle("Menu Principal Medico");
			Medicopp.setScene(new Scene(MedicoVentana));
			Medicopp.show();
			Medicopp.setMinHeight(600);
			Medicopp.setMinWidth(800);
			
			System.out.println("Cerrando ventana de Login.");
			Stage CerrarVentanaLogin = (Stage) btnMenuGeneral.getScene().getWindow();
			CerrarVentanaLogin.close();
			
		}
		
		catch(ControladorExcepciones case3){
			ControladorAvisos.setMensajeError("No se pudo abrir la ventana de Medico.");
			case3.abrirVentanaAvisos();
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

		}
		catch(Exception a) {
			System.out.println("Error");
		}
	}
}
