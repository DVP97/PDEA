package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;

public class ControladorMedicopp implements Initializable {

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

    @Override
    public void initialize(URL location, ResourceBundle reosurces) {
	
	}
}