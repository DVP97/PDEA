package controlador;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import modelo.Cita;
import modelo.Cuidador;
import modelo.Paciente;

public class ControladorCuidadorAvisos {

    @FXML
    private JFXComboBox<?> campoPacientes;

    @FXML
    private TableColumn<?, ?> columnaFecha;

    @FXML
    private TableColumn<?, ?> columnaConcepto;
    
	@FXML
	private Label campoCuidador;
	@FXML
	private Label campoPaciente;
    @FXML
    private JFXButton btnVolver;
    private Cita fecha_cita;
   
    
    public void initialize(URL location, ResourceBundle reosurces) {
    	
    	Cuidador c = ControladorCuidadorpp.getCuidadorActual();
		campoCuidador.setText("Hola " +c.getNombre()+",");

		Paciente p = ControladorPacientepp.getPacienteActual();
		campoPaciente.setText(p.getNombreCompleto());
	
		//Recoge fecha de la cita
		fecha_cita = lectorJson.getCita(p.getDni());
		
    
    }	

	

    @FXML
    void pressBtnVolver(ActionEvent event)  throws IOException {
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
			
		catch(ControladorExcepciones case1){
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

		}
		catch(Exception a) {
			System.out.println("Error");
		}
    }
}