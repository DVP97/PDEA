package controlador;

import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.stage.Stage;
import modelo.Cuidador;
import modelo.Medico;
import modelo.Paciente;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
	
    @FXML
    private JFXButton buttonRegistrar;
    
    @FXML
    void pressBtnRegistrar(ActionEvent event) {
    	try {
    	System.out.println("Cargando ventana de Registro...");
		ControladorPacientepp.setPacienteActual(this.quePaciente());
		Parent NuevoRegistro = FXMLLoader.load(getClass().getResource("/vista/registro.fxml"));
		Stage Registro = new Stage();
		Registro.setTitle("Registro de Nuevo Usuario");
		Registro.setScene(new Scene(NuevoRegistro));
		Registro.show();
    	}
    	catch(Exception r){
    		ControladorAvisos.setMensajeError("No se pudo abrir la ventana de Registro.");
			abrirVentanaAvisos();
    	}
    }
		
	
	
	// Lectura inicial de los json
	lectorJson lector = new lectorJson();
	ArrayList<Paciente> pacientes = lector.devolverPacientes();
	ArrayList<Medico> medicos = lector.devolverMedicos();
	ArrayList<Cuidador> cuidadores = lector.devolverCuidadores();
	//----------------------------
	
	@Override
	public void initialize(URL location, ResourceBundle reosurces) {
		
	}
	
	
	
	//Acciones ejecutadas al pulsar el Boton Aceptar
	public void pressBtnAceptar() {
		
		try {
			comprobarInputUser();
			if (comprobarInputUser() == false) {
				throw new ExcepcionUser(01);
			}
			
			//comprobar tipo de usuario, usamos switch para optimizar programa
			int usertype=0;
			if (esPaciente()==true) {
				usertype=1;
			}
			if (esCuidador()==true) {
				usertype=2;
			}
			if (esMedico()==true) {
				usertype=3;
			}
			
			switch (usertype) {
					
				case 1:
					try {
						System.out.println("Cargando ventana principal de Paciente...");
						ControladorPacientepp.setPacienteActual(this.quePaciente());
						Parent PacienteVentana = FXMLLoader.load(getClass().getResource("/vista/menupaciente.fxml"));
						Stage Pacientepp = new Stage();
						Pacientepp.setTitle("Menu Principal Paciente");
						Pacientepp.setScene(new Scene(PacienteVentana));
						Pacientepp.show();
						

						System.out.println("Cerrando ventana de Login.");
						Stage CerrarVentanaLogin = (Stage) buttonAceptar.getScene().getWindow();
						CerrarVentanaLogin.close();
						}
						
					
					catch(Exception a){
						ControladorAvisos.setMensajeError("No se pudo abrir la ventana de Paciente.");
						abrirVentanaAvisos();
					}

					break;
					
				case 2:
					try {
						System.out.println("Cargando ventana principal de Cuidador...");
						ControladorCuidadorpp.setCuidadorActual(this.queCuidador());
						Parent CuidadorVentana = FXMLLoader.load(getClass().getResource("/vista/cuidadorpp.fxml"));
						Stage Cuidadorpp = new Stage();
						Cuidadorpp.setTitle("Menu Principal Cuidador");
						Cuidadorpp.setScene(new Scene(CuidadorVentana));
						Cuidadorpp.show();

						System.out.println("Cerrando ventana de Login.");
						Stage CerrarVentanaLogin = (Stage) buttonAceptar.getScene().getWindow();
						CerrarVentanaLogin.close();
						
					}
					catch(Exception a){
						ControladorAvisos.setMensajeError("No se pudo abrir la ventana de Cuidador.");
						abrirVentanaAvisos();
					}

					break;
					
				case 3:
					try {
						System.out.println("Cargando ventana principal de Medico...");
						ControladorMedicopp.setMedicoActual(this.queMedico());
						Parent MedicoVentana = FXMLLoader.load(getClass().getResource("/vista/medicopp.fxml"));
						Stage Medicopp = new Stage();
						Medicopp.setTitle("Menu Principal Medico");
						Medicopp.setScene(new Scene(MedicoVentana));
						Medicopp.show();
						
						System.out.println("Cerrando ventana de Login.");
						Stage CerrarVentanaLogin = (Stage) buttonAceptar.getScene().getWindow();
						CerrarVentanaLogin.close();
						
						}
					catch(Exception a){
						ControladorAvisos.setMensajeError("No se pudo abrir la ventana de Medico.");
						abrirVentanaAvisos();
					}

					break;
					
				default:
					// en caso de que se introduzca un DNI que no se encuentre en la base de datos
					ControladorAvisos.setMensajeError("Datos incorrectos.");
					abrirVentanaAvisos();
					break;
			}	
		}

		catch(ExcepcionUser loginfailure){
			ControladorAvisos.setMensajeError("El usuario debe estar compuesto por 8 digitos y una letra.");
			abrirVentanaAvisos();
		}
		
	}
	
	public void btnAceptarActionPerformed(ActionEvent event) {
		this.pressBtnAceptar();
	}
	
	public void textoClavePressed(KeyEvent event){
		if(event.getCode().equals(KeyCode.ENTER)) {
			this.pressBtnAceptar();
		}
		
	}
	
	//----------------------------------------------
	
	
	// Acciones ejecutadas al pulsar el Boton Cancelar
	public void pressBtnCancelar(ActionEvent event) {
		System.out.println("Saliendo de la aplicacion. Hasta pronto.");
		System.exit(0);
	}	
	//------------------------------------------------
	
	
	// Funciones donde se comprueba que los datos introducidos son correctos
	public boolean comprobarInputUser() {		
		// capturar texto  del TextField de usuario y convierte a mayus.
		String inputUsuario = txtInputUsuario.getText().toUpperCase();
		System.out.println("Usuario introducido: "+inputUsuario);
		System.out.println("Password introducido: "+txtInputPassword.getText());
		
		// comprueba la longitud del string inputUser, que los primeros 8 caracteres son numeros y que el ultimo caracter es una letra
		if(inputUsuario.length()!=9 || comprobarDigitosDNI() == false || Character.isLetter(inputUsuario.charAt(8)) == false ) {
			System.out.println("Usuario no valido.");
			return false;
		}
		

		else {
			System.out.println("Usuario valido.");
			return true;
		}	
	}
	
	// comprobacion de que los primeros 8 caracteres introducidos en el campo de ussuario son digitos 
	public boolean comprobarDigitosDNI() {
		String inputUser = txtInputUsuario.getText().toUpperCase();
		for(int i=0; i<inputUser.length()-1; i++) {
			if(Character.isDigit((inputUser.charAt(i)))==false) {
					return false;
			}
		}
		return true;
	}
	//------------------------------------------------------

	// funciones para comprobar el tipo de usuario
	public boolean esPaciente() {
		for (int i=0; i< pacientes.size(); i++) {
			Paciente p = pacientes.get(i);
			String passwordPaciente = p.getContrasena();
			String passwordEncriptada = getMd5(txtInputPassword.getText());
			if(p.getDni().equalsIgnoreCase(txtInputUsuario.getText()) && passwordPaciente.equals(passwordEncriptada) ) {
				return true;
			}
		}
		return false;	
	}
	

	public boolean esCuidador() {
		for (int i=0; i< cuidadores.size(); i++) {
			Cuidador p = cuidadores.get(i);
			String passwordCuidador = p.getContrasena();
			String passwordEncriptada = getMd5(txtInputPassword.getText());
			if(p.getDni().equalsIgnoreCase(txtInputUsuario.getText()) && passwordCuidador.equals(passwordEncriptada) ){
				return true;
			}
		}
		return false;	
	}
	
	
	public boolean esMedico() {
		for (int i=0; i< medicos.size(); i++) {
			Medico p = medicos.get(i);
			String passwordMedico = p.getContrasena();
			String passwordEncriptada = getMd5(txtInputPassword.getText());
			if(p.getDni().equalsIgnoreCase(txtInputUsuario.getText()) && passwordMedico.equals(passwordEncriptada) ){
				return true;
			}
		}
		return false;	
	}
	
	
	//Funciones para establecer el Usuario en el menu principal
	public Paciente quePaciente() {
		for (int i=0; i< pacientes.size(); i++) {
			Paciente p = pacientes.get(i);
			if(p.getDni().equalsIgnoreCase(txtInputUsuario.getText())) {
				return p;
			}
		}
		return null;	
	}
	
	
	public Medico queMedico() {
		for (int i=0; i< pacientes.size(); i++) {
			Medico p = medicos.get(i);
			if(p.getDni().equalsIgnoreCase(txtInputUsuario.getText())) {
				return p;
			}
		}
		return null;	
	}
	
	
	public Cuidador queCuidador() {
		for (int i=0; i< cuidadores.size(); i++) {
			Cuidador p = cuidadores.get(i);
			if(p.getDni().equalsIgnoreCase(txtInputUsuario.getText())) {
				return p;
			}
		}
		return null;	
	}
	//--------------------------------------------
	
	
	// funcion para declarar excepciones propias
	public class ExcepcionUser extends Exception{
		
		private int codigoError;
	    
	    public ExcepcionUser(int codigoError){
	        this.codigoError=codigoError;
	    }
	}
	//-----------------------------------------
	//funcion hash recibe contrasena y devuelve contrasena encriptado
	public static String getMd5(String input) 
    { 
        try { 
  
            // Static getInstance method is called with hashing MD5 
            MessageDigest md = MessageDigest.getInstance("MD5"); 
  
            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(input.getBytes()); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            
            	
            
            return hashtext; 
        }  
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    } 
  
        
      //-----------------------------------------
        
	//-----------------------------------------
	
	// funcion desde la que se llama a la ventana de avisos
	public void abrirVentanaAvisos() {
		try {
			Parent avisos = FXMLLoader.load(getClass().getResource("../vista/avisos.fxml"));
			Stage VentanaAvisos = new Stage();
			VentanaAvisos.setTitle("Aviso");
			VentanaAvisos.setScene(new Scene(avisos));
			VentanaAvisos.show();
		}
		catch(Exception a) {
			System.out.println("Error");
			 a.printStackTrace();
		}
	}
	//---------------------------------------------------
}