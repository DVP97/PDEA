package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import modelo.Medico;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.scene.control.Accordion;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;


public class ControladorMedicopp implements Initializable {


    @FXML
    private Label campoMedico;

    @FXML
    private TextField campoBusqueda;

    @FXML
    private ListView<?> listaPacientes;

    @FXML
    private TextField campoDNI;

    @FXML
    private TextField campoNombre;

    @FXML
    private TextField campoTelefono;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnEjercicios;

    @FXML
    private AnchorPane anchorPaneRecibidos;

    @FXML
    private Accordion AccordionMensajesRec;

    @FXML
    private TextArea campoRedactar;

    @FXML
    private JFXButton btnConfirmarEnvio;

    @FXML
    private JFXComboBox<?> comboBoxElegirDestinatario;

    
    private static Medico medicoActual = new Medico();
    
    @Override
    public void initialize(URL location, ResourceBundle reosurces) {
    	 campoMedico.setText("Hola " +ControladorMedicopp.getMedicoActual().getNombre()+",");
    	 
	}
    
    @FXML
    void pressBtnConfirmarEnvio(ActionEvent event) {

    }
    
    //Getters y Setters
	public static Medico getMedicoActual() {
		return medicoActual;
	}

	public static void setMedicoActual(Medico MedicoActual) {
		medicoActual = MedicoActual;
	}
 
}