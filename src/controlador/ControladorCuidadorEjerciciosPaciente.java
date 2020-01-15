package controlador;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ControladorCuidadorEjerciciosPaciente {
	   @FXML
	    private ImageView pantallaEj;

	    @FXML
	    private Label campoHechos;

	    @FXML
	    private JFXButton btnAnterior;

	    @FXML
	    private JFXButton btnNext;

	    @FXML
	    private JFXButton btnVolver;

	    @FXML
	    void pressBtnAnterior(ActionEvent event) {

	    }

	    @FXML
	    void pressBtnNext(ActionEvent event) {

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
			
		catch(ControladorExcepciones case1){
			ControladorAvisos.setMensajeError("No se pudo abrir el menu de cuidador.");
			case1.abrirVentanaAvisos();
		}
    }

}
