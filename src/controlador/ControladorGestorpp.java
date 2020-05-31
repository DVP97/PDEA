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
import javafx.scene.control.Label;
import javafx.stage.Stage;
import modelo.Medico;

public class ControladorGestorpp implements Initializable {

	@FXML
	private Label campoGestor;

	@FXML
	private JFXComboBox<?> comboRol;

	@FXML
	private JFXButton btnRegistrar;

	@FXML
	private JFXButton btnEliminar;

	private static Medico gestor = new Medico();

	@Override
	public void initialize(URL location, ResourceBundle reosurces) {
	
		campoGestor.setText("Hola " + gestor.getNombre() + ",");
	}

	
	@FXML
	void pressBtnEliminar(ActionEvent event) throws IOException {
		try {
			ControladorGestorEliminar.setGestor(gestor);

			Parent eliminar = FXMLLoader.load(getClass().getResource("/vista/gestor_eliminar.fxml"));
			Stage MensajeriaPaciente = new Stage();
    		MensajeriaPaciente.setTitle("Eliminar Usuario");
    		MensajeriaPaciente.setScene(new Scene(eliminar));
    		MensajeriaPaciente.show();
    		MensajeriaPaciente.setMinHeight(318);
    		MensajeriaPaciente.setMinWidth(413);
    		
    		Stage CambioVentanaMensajes = (Stage) btnEliminar.getScene().getWindow();
    		CambioVentanaMensajes.close();

		} catch (ControladorExcepciones r) {
			ControladorAvisos.setMensajeError("No se pudo abrir la ventana de Eliminar Usuario.");
			r.abrirVentanaAvisos();
		}
	}

	// En esta acción se abre la ventana de registro
	@FXML
	void pressBtnRegistrar(ActionEvent event) throws IOException{
		try {
			ControladorGestorRegistro.setGestor(gestor);
			Parent eliminar = FXMLLoader.load(getClass().getResource("/vista/gestor_registrar.fxml"));
			Stage MensajeriaPaciente = new Stage();
    		MensajeriaPaciente.setTitle("Registrar Usuario");
    		MensajeriaPaciente.setScene(new Scene(eliminar));
    		MensajeriaPaciente.show();
    		MensajeriaPaciente.setMinHeight(410);
    		MensajeriaPaciente.setMinWidth(410);
    		
    		Stage CambioVentanaMensajes = (Stage) btnRegistrar.getScene().getWindow();
    		CambioVentanaMensajes.close();
		} catch (ControladorExcepciones r) {
			ControladorAvisos.setMensajeError("No se pudo abrir la ventana de Registrar Usuario.");
			r.abrirVentanaAvisos();
		}
	}

	 //Getters y Setters
    public static Medico getGestor() {
  		return gestor;
  	}

	public static void setGestor(Medico med) {
  		gestor = med;
  	}
}