package controlador;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;

public class ControladorCuidadorEjerciciosPaciente {

    @FXML
    private JFXComboBox<?> comboPaciente;

    @FXML
    private TitledPane btnEditar1;

    @FXML
    private JFXButton buttonVolver;

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

			Stage CerrarEjercicios = (Stage) buttonVolver.getScene().getWindow();
			CerrarEjercicios.close();
		}	
			
		catch(ControladorExcepciones case1){
			ControladorAvisos.setMensajeError("No se pudo abrir el menu de cuidador.");
			case1.abrirVentanaAvisos();
		}
    }

}
