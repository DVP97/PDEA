package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.gluonhq.charm.glisten.control.TextField;
import com.jfoenix.controls.JFXButton;

import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;


public class ControladorLogin implements Initializable{
	
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
    private JFXTextField txtInputUsuario;
	
	@FXML
    private  JFXPasswordField txtInputPassword;
	
	@FXML
	private JFXButton buttonAceptar;
	
	@FXML
	private JFXButton buttonCancelar;
	
	lectorJson lector = new lectorJson();
	ArrayList<Paciente> pacientes = lector.devolverPacientes();
	ArrayList<Medico> medicos = lector.devolverMedicos();
	ArrayList<Cuidador> cuidadores = lector.devolverCuidadores();

	
	@Override
	public void initialize(URL location, ResourceBundle reosurces) {
		
		
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
			try {
				ControladorAvisos.setMensajeError("El usuario debe estar compuesto por 8 digitos y una letra.");
				Parent avisos = FXMLLoader.load(getClass().getResource("/interfaz/avisos.fxml"));
				Stage VentanaAvisos = new Stage();
				VentanaAvisos.setTitle("Aviso");
				VentanaAvisos.setScene(new Scene(avisos));
				VentanaAvisos.show();
			}
			catch(Exception a) {
				 a.printStackTrace();
			}
		}
		
	}
	//Acciones ejecutadas al pulsar el Boton Cancelar
	public void pressBtnCancelar(ActionEvent event) {
		System.out.println("Saliendo de la aplicacion. Hasta pronto.");
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
			System.out.println("Usuario valido.");
			return true;
		}
		
		
	}
	
	public class ExcepcionUser extends Exception{
	     
	    private int codigoError;
	    
	    public ExcepcionUser(int codigoError){
	        this.codigoError=codigoError;
	    }
	}
	

	
	public boolean esPaciente() {
		for (int i=0; i< pacientes.size(); i++) {
			Paciente p = pacientes.get(i);
			if(p.getDni().equalsIgnoreCase(txtInputUsuario.getText())) {
				return true;
			}
		}
		return false;	
	}
	
	
	public boolean esMedico() {
		for (int i=0; i< medicos.size(); i++) {
			Medico p = medicos.get(i);
			if(p.getDni().equalsIgnoreCase(txtInputUsuario.getText())) {
				return true;
			}
		}
		return false;	
	}
	
	public boolean esCuidador() {
		for (int i=0; i< cuidadores.size(); i++) {
			Cuidador p = cuidadores.get(i);
			if(p.getDni().equalsIgnoreCase(txtInputUsuario.getText())) {
				return true;
			}
		}
		return false;	
	}

}