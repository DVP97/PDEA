package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTabPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import modelo.Medico;
import modelo.Paciente;

public class ControladorMedicoSubmenuPaciente {

    @FXML
    private Label campoMedico;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnEjercicios;

    @FXML
    private JFXTabPane JFXTabPaneMensajeria;

    @FXML
    private Tab tabRecibidos;

    @FXML
    private AnchorPane anchorPaneRecibidos;

    @FXML
    private JFXButton btnResponder;

    @FXML
    private Accordion AccordionMensajesRec;

    @FXML
    private Label labelBandejaEntrada;

    @FXML
    private Tab tabEnviados;

    @FXML
    private AnchorPane anchorPaneEnviados;

    @FXML
    private Accordion AccordionMensajesEnv;

    @FXML
    private Label labelBandejaSalida;

    @FXML
    private TextArea campoRedactar;

    @FXML
    private JFXButton btnConfirmarEnvio;

    @FXML
    private JFXComboBox<?> comboBoxElegirDestinatario;

    @FXML
    private Label labelRedactar;

    private static Paciente pacienteActual;
    
    private static Medico medicoActual = ControladorMedicopp.getMedicoActual();
    
    public void initialize(URL location, ResourceBundle reosurces) {
    	campoMedico.setText("Hola " + medicoActual.getNombre() +",");
    }
    
    @FXML
    void pressBtnConfirmarEnvio(ActionEvent event) {

    }

    @FXML
    void pressBtnResponder(ActionEvent event) {

    }
    
    public Paciente getPacienteActual() {
		return pacienteActual;
	}
    public static void setPacienteActual(Paciente p) {
		pacienteActual = p;
	}
    
    public Medico getMedicoActual() {
    	return medicoActual;
    }
    public static void setMedicoActual(Medico m) {
    	medicoActual = m;
    }

}