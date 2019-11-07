package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class ControladorCuidadorpp implements Initializable {

	 	@FXML
	    private ComboBox<?> comboPacientes;

	    @FXML
	    private Button btnAvisos;

	    @FXML
	    private Button btnEjercicios;

	    @FXML
	    private Button btnDatos;
	    
	  private static  Cuidador cuidadorActual = new Cuidador();
	    
	    @Override
	    public void initialize(URL location, ResourceBundle reosurces) {
		
		}
	    //Getters y Setters
	    
		public static Cuidador getCuidadorActual() {
			return cuidadorActual;
		}

		public static void setCuidadorActual(Cuidador cuidadorActual) {
			cuidadorActual = cuidadorActual;
		}
	    
	    
}
