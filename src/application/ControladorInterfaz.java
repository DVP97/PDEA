package application;

import java.net.URL;
import java.util.ResourceBundle;
import com.gluonhq.charm.glisten.control.TextField;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;


public class ControladorInterfaz implements Initializable{
	
	@FXML
	private Button myButton;
	
	@FXML
	private Label txtBienvenida;
	
	@FXML
	private Separator separatorTop;
	
	@FXML
	private ImageView logo;
	
	@FXML
	private GridPane gridPanelLogin;
	
	@FXML
    private TextField txtInputUsuario;
	
	@FXML
    private TextField txtInputPassword;
	
	@FXML
	private JFXButton buttonAceptar;
	
	@FXML
	private JFXButton buttonCancelar;
	
	
	@Override
	public void initialize(URL location, ResourceBundle reosurces) {
	//no hace falta hacer nada aquí	
	}
		
	public void aceptado(ActionEvent event) {
		System.out.println("Bienvenido a PDEA.");
		
		
	}
}
