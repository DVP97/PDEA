package application;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

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
	//no hace falta hacer nada aqu�	
	}
		
	//Acciones ejecutadas al pulsar el Boton Aceptar
	public void pressBtnAceptar(ActionEvent event) {
		
		try {
			comprobarInputUser();
			if (comprobarInputUser() == false) {
				throw new ExcepcionUser(01);
			}
			
		}
		catch(ExcepcionUser loginfailure){
			JOptionPane.showMessageDialog(null, "El usuario debe estar compuesto por 8 digitos y una letra.", "Aviso", JOptionPane.WARNING_MESSAGE);
		}
		
	}
	//Acciones ejecutadas al pulsar el Boton Cancelar
	public void pressBtnCancelar(ActionEvent event) {
		System.out.println("Saliendo de la aplicación. Hasta pronto.");
		System.exit(0);
	}	

	public boolean comprobarInputUser() {		
		// capturar texto  del TextField de usuario y convierte a mayus.
		String inputUser = txtInputUsuario.getText().toUpperCase();
		
		System.out.println("Usuario introducido: "+inputUser);
		
		
		/*
		//comprobar caracter a caracter si es un numero
		for(int i=0; i<inputUser.length()-1; i++){
		 
			System.out.println(inputUser.charAt(i));
		//	Double a = Double.parseDouble(inputUser.charAt(i);
			if (inputUser.charAt(i) == instanceof java.lang.Integer){
				System.out.println("error");
				return false;
			}
		}
		*/
		
		//comprueba la longitud del string inputUser
		if(inputUser.length()!=9) {
			return false;
		}
		else {
			System.out.println("Usuario válido.");
			return true;
		}
		
		
	}
	
	public class ExcepcionUser extends Exception{
	     
	    private int codigoError;
	    
	    public ExcepcionUser(int codigoError){
	        this.codigoError=codigoError;
	    }
	}

}