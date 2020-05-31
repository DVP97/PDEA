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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Cuidador;
import modelo.Medico;
import modelo.Paciente;

public class ControladorGestorEliminar implements Initializable {

	@FXML
	private Label campoGestor;

	@FXML
	private TextField textoDNI;

	@FXML
	private JFXComboBox<?> comboRol;

	@FXML
	private JFXButton btnVolver;

	@FXML
	private JFXButton btnEliminar;

	private static Medico gestor = new Medico();

	private baseDatos.FachadaBaseDatos fbd = application.Main.getFbd();

	@Override
	public void initialize(URL location, ResourceBundle reosurces) {
		campoGestor.setText("Hola " + gestor.getNombre() + ",");
	}

	@FXML
	void pulsarBtnEliminar(ActionEvent event) throws IOException {
		try {
			int a = tipoUsuario();
			switch (a) {
			case 1:
				Paciente paciente = fbd.visualizarPaciente(textoDNI.getText());
				fbd.borrarPaciente(paciente);
				ControladorAvisos.setMensajeError("Paciente eliminado.");
				abrirVentanaAvisos();
				break;
			case 2: 
				Medico medico = fbd.visualizarMedico(textoDNI.getText());
				fbd.borrarMedico(medico);
				ControladorAvisos.setMensajeError("Medico eliminado.");
				abrirVentanaAvisos();
				break;
			case 3:
				Cuidador cuidador = fbd.visualizarCuidador(textoDNI.getText());
				fbd.borrarCuidador(cuidador);
				ControladorAvisos.setMensajeError("Cuidador eliminado.");
				abrirVentanaAvisos();
				break;
				
			default:
				ControladorAvisos.setMensajeError("Error eliminando usuario.");
				throw new ControladorExcepciones();
			}
			textoDNI.clear();

		} catch (ControladorExcepciones loginfailure) {
			ControladorAvisos.setMensajeError("Error eliminando al usuario.");
			loginfailure.abrirVentanaAvisos();
			
		}
	}

	@FXML
	void pulsarBtnVolver(ActionEvent event) throws IOException {

		try {
			ControladorGestorpp.setGestor(gestor);

			Parent medicoSubMenuPaciente = FXMLLoader.load(getClass().getResource("/vista/gestorpp.fxml"));
			Stage subMenuPaciente = new Stage();
			subMenuPaciente.setTitle("Menu " + gestor.getNombreCompleto());
			subMenuPaciente.setScene(new Scene(medicoSubMenuPaciente));
			subMenuPaciente.show();
			subMenuPaciente.setMinHeight(318);
			subMenuPaciente.setMinWidth(413);

			Stage CerrarVentanaSensores = (Stage) btnVolver.getScene().getWindow();
			CerrarVentanaSensores.close();
		}

		catch (ControladorExcepciones case1) {
			ControladorAvisos.setMensajeError("No se pudo abrir la ventana de Submenu.");
			case1.abrirVentanaAvisos();
		}
	}

	// Getters y Setters
	public static Medico getGestor() {
		return gestor;
	}

	public static void setGestor(Medico med) {
		gestor = med;
	}

	public int tipoUsuario() {

		String dni = textoDNI.getText();
		Paciente p = fbd.visualizarPaciente(dni);
		Medico m = fbd.visualizarMedico(dni);
		Cuidador c = fbd.visualizarCuidador(dni);
		if (p != null) {
			return 1;
		} else if (m != null) {
			return 2;
		} else if (c != null) {
			return 3;
		} else {
			return 0;
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
		catch(IOException a) {
			System.out.println("Error");
			 a.printStackTrace();
		}
	}
}