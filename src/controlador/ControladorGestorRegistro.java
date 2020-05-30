package controlador;

import java.io.IOException;
import java.math.BigInteger;

import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TimeZone;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.sun.xml.internal.ws.org.objectweb.asm.Label;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Cuidador;
import modelo.Medico;
import modelo.Paciente;
import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ControladorGestorRegistro implements Initializable {

    @FXML
    private TextField textoDNI;

    @FXML
    private TextField textoNombre;

    @FXML
    private PasswordField textoContrasena;

    @FXML
    private PasswordField textoContrasena2;

    @FXML
    private JFXComboBox<String> comboRol;

    @FXML
    private TextField textoApellidos;

    @FXML
    private JFXButton btnCancelar;

    @FXML
    private TextField textoTelefono;

    @FXML
    private TextField textoFechaNac;

    @FXML
    private JFXButton btnAceptar;
    
    @FXML
    private JFXDatePicker campoFecha;
    
    @FXML
    private Label campoGestor;


    private static Medico gestor = new Medico();

    private ObservableList<String> dbTypeList = FXCollections.observableArrayList("Paciente","Cuidador","Medico");

    private baseDatos.FachadaBaseDatos fbd = application.Main.getFbd();
    

	@Override
	public void initialize(URL location, ResourceBundle reosurces) {
		campoGestor.setText("Hola " + gestor.getNombre() + ",");
		comboRol.setItems(dbTypeList);
		
	}

    @FXML
    void pulsarBtnAceptar_reg(ActionEvent event) {
    try {

    		String FechaN[] = campoFecha.getValue().toString().split("-");
    		List<String> Fecha = Arrays.asList(FechaN);
    		int dia = Integer.parseInt(Fecha.get(0));
    		int mes = Integer.parseInt(Fecha.get(1));
    		int anho = Integer.parseInt(Fecha.get(2));
    		// guardar dia y hora de la cita en un Date
    		@SuppressWarnings("deprecation")
    		Date calend = new Date(anho, mes, dia);
		
	    	//Comprobacion de que coinciden las contrasenas
	    	String pswrd = textoContrasena.getText();
	    	String pswrdSecond = textoContrasena2.getText();
	    	String passwordEncriptada = getMd5(pswrd);
	    	//Declaraciones
	    	String nombre = textoNombre.getText(), apellidos = textoApellidos.getText(), dni = textoDNI.getText(),
	    			tlfn = textoTelefono.getText();
	    	boolean prueba = false;
	    	prueba = comprobarDigitosDNI();
	
	    	//Comprobacion de el resto de campos
	    	int roltype=0;
	
			if (comboRol.getValue().equals ("Paciente")) {
				roltype=1;
			}
			if (comboRol.getValue().equals ("Cuidador")) {
				roltype=2;
			}
			if (comboRol.getValue().equals ("Medico")) {
				roltype=3;
			}
			if (roltype==0) {
				abrirVentanaAvisos();
			}
	
			else {
				
				System.out.println("Usuario seleccionado: " +comboRol.getValue());
				
				if(dni.length() !=9 | nombre.length()<1 | apellidos.length()<1 | tlfn.length()!=9 | pswrd.length()<1 ) {
					ControladorAvisos.setMensajeError("Por favor revise los datos introducidos.");
					abrirVentanaAvisos();	
				}

				if ( pswrd.length()>0 && pswrd.length()<4 | !pswrd.equals(pswrdSecond) ){
		    		try {
		    			ControladorAvisos.setMensajeError("Ambas contraseñas deben coincidir y tener un minimo de 4 caracteres.");
		    			abrirVentanaAvisos();
	
		    		}
		    		catch(Exception a) {
		    			System.out.println("Error");
		    			 a.printStackTrace();
		    		}	
		    	}  

				System.out.println(prueba);
				if (!prueba ) {
							ControladorAvisos.setMensajeError("Por favor compruebe que el dni es correcto.");
			    			abrirVentanaAvisos();
			    		
				} else {
						System.out.println(passwordEncriptada);
						try {
							switch (roltype) {
	
								case 1:
									try {
	
										Paciente newPaciente = new Paciente();
										newPaciente.setDni(dni);
										newPaciente.setNombre(nombre);
										newPaciente.setApellidos(apellidos);
										newPaciente.setFecha_nacimiento(getFechaString(calend));
										//newPaciente.setFecha_nacimiento(fechaNacimientoParseada);
										newPaciente.setTelefono(tlfn);
										newPaciente.setContrasena(passwordEncriptada);
										System.out.println("Registrando usuario Paciente");
										
										fbd.insertarPaciente(newPaciente);
										ControladorAvisos.setMensajeError("Usuario Registrado.");
										abrirVentanaAvisos();
										Stage  CerrarRegistro= (Stage) btnAceptar.getScene().getWindow();
										CerrarRegistro.close();
										
									}
									catch(Exception a) {
										ControladorAvisos.setMensajeError("Error registrando paciente, por favor revise los datos introducidos.");
										abrirVentanaAvisos();
									}
									break;
	
								case 2:
									try {
	
										Cuidador newCuidador = new Cuidador();
										newCuidador.setDni(textoDNI.getText());
										newCuidador.setNombre(textoNombre.getText());
										newCuidador.setApellidos(textoApellidos.getText());
	
	
										newCuidador.setTelefono(textoTelefono.getText());
										newCuidador.setContrasena(passwordEncriptada);
	
										fbd.insertarCuidador(newCuidador);
	
										ControladorAvisos.setMensajeError("Usuario Registrado.");
										abrirVentanaAvisos();
										Stage  CerrarRegistro= (Stage) btnAceptar.getScene().getWindow();
										CerrarRegistro.close();
									}
									catch(Exception a) {
										ControladorAvisos.setMensajeError("Error registrando cuidador, por favor revise los datos introducidos.");
										abrirVentanaAvisos();
									}
									break;
	
								case 3:
									try {
	
	
										Medico newMedico = new Medico();
										newMedico.setDni(textoDNI.getText());
										newMedico.setNombre(textoNombre.getText());
										newMedico.setApellidos(textoApellidos.getText());
	
	
										newMedico.setTelefono(textoTelefono.getText());
										newMedico.setContrasena(passwordEncriptada);
	
										fbd.insertarMedico(newMedico);
	
										ControladorAvisos.setMensajeError("Usuario Registrado.");
										abrirVentanaAvisos();
										Stage  CerrarRegistro= (Stage) btnAceptar.getScene().getWindow();
										CerrarRegistro.close();
									}
									catch(Exception a) {
										ControladorAvisos.setMensajeError("Error registrando medico, por favor revise los datos introducidos.");
										abrirVentanaAvisos();
									}
									break;
	
								default:
									ControladorAvisos.setMensajeError("Elija un Rol.");
									abrirVentanaAvisos();
							}
						}
	
						catch(Exception a) {
							ControladorAvisos.setMensajeError("Elija un Rol ");
							abrirVentanaAvisos();
						}
					}
				}
			
    	}
    	catch(Exception a) {
    		ControladorAvisos.setMensajeError("Complete todos lo campos pedidos porfavor.");
    		abrirVentanaAvisos();
    	}
    }

    @FXML
    void pulsarBtnCancelar_reg(ActionEvent event)throws IOException {

		try {
			ControladorGestorpp.setGestor(gestor);

			Parent medicoSubMenuPaciente = FXMLLoader.load(getClass().getResource("/vista/gestorpp.fxml"));
			Stage subMenuPaciente = new Stage();
			subMenuPaciente.setTitle("Menu " + gestor.getNombreCompleto());
			subMenuPaciente.setScene(new Scene(medicoSubMenuPaciente));
			subMenuPaciente.show();
			subMenuPaciente.setMinHeight(600);
			subMenuPaciente.setMinWidth(800);

			Stage CerrarVentanaSensores = (Stage) btnCancelar.getScene().getWindow();
			CerrarVentanaSensores.close();
		}

		catch (ControladorExcepciones case1) {
			ControladorAvisos.setMensajeError("No se pudo abrir la ventana de Submenu.");
			case1.abrirVentanaAvisos();
		}
    }

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
		catch(Exception a) {
			System.out.println("Error");
		}
	}
    
    public boolean comprobarDigitosDNI() {
		
    	String inputUser = textoDNI.getText().toUpperCase();
    	int in = inputUser.length(); 
    	String letra = dniReal(inputUser);
		
		for(int i=0; i<=in-2; i++) {
			if(Character.isDigit((inputUser.charAt(i)))==false) {
				ControladorAvisos.setMensajeError("El usuario debe estar compuesto por 8 digitos y una letra.");
				abrirVentanaAvisos();
				return false;
			}	
		}	
		if (!inputUser.endsWith(letra)) {
			ControladorAvisos.setMensajeError("El dni introducido no es válido, introduzca uno real. ");
			abrirVentanaAvisos();
			return false;
		}
		return true;	
	}
    
    //Comprobar que el DNI es real
    public String dniReal (String comprobacionLetra) {
    	
    	String dniLetra = null;
    	String[] letra = {"T","R","W","A","G","M","Y","F","P","D","X","B","N","J","Z","S","Q","V","H","L","C","K","E"};
    	int comprobacionNum = Integer.parseInt(comprobacionLetra.substring(0, 8));
    	int resto = comprobacionNum % 23;
    	dniLetra = letra [resto];
    	
    	
    	return dniLetra;
    }
      //-----------------------------------------



    @SuppressWarnings("deprecation")
	public String getFechaString(Date dummy) {
		// Choose time zone in which you want to interpret your Date
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));

		cal.setTime(dummy);
		String dia = ((Integer) dummy.getDate()).toString();
		int m = dummy.getMonth() + 1;
		String mes = ((Integer) m).toString();
		int year = cal.get(Calendar.YEAR);
		String anho = ((Integer) year).toString();
		String hora = ((Integer) dummy.getHours()).toString();
		String min = ((Integer) dummy.getMinutes()).toString();
		String f = hora + ":" + min + "  -  " + dia + "/" + mes + "/" + anho;
		return f;
	}

	 //Getters y Setters
    public static Medico getGestor() {
  		return gestor;
  	}

	public static void setGestor(Medico med) {
  		gestor = med;
  	}
}
