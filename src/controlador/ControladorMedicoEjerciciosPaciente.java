
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;
import modelo.Paciente;

public class ControladorMedicoEjerciciosPaciente implements Initializable{

    @FXML
    private JFXComboBox<?> comboPaciente;

    @FXML
    private TitledPane btnEditar1;

    @FXML
    private TitledPane btnEditar2;

    @FXML
    private JFXButton btnEditar3;

    @FXML
    private JFXButton buttonVolver;
    
    private static Paciente pacienteActual = new Paciente();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	pacienteActual = ControladorMedicoSubmenuPaciente.getPacienteActual();
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
	
}