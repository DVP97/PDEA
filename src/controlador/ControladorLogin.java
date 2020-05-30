package controlador;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.scene.layout.BorderPane;

public class ControladorLogin implements Initializable {

	@FXML
	private BorderPane BorderPaneGlobal;
	@FXML
	private GridPane gridPanelLogin;
	@FXML
	private ImageView logo;
	@FXML
	private JFXButton buttonAceptar;
	@FXML
	private JFXButton buttonCancelar;
	@FXML
	private JFXButton buttonRegistrar;
	@FXML
	private JFXPasswordField txtInputPassword;
	@FXML
	private JFXTextField txtInputUsuario;
	@FXML
	private Label txtBienvenida;
	@FXML
	private Separator separatorTop;

	private baseDatos.FachadaBaseDatos fbd = application.Main.getFbd();

	@Override
	public void initialize(URL location, ResourceBundle reosurces) {

	}

	// BOTONES
	@FXML
	void pressBtnRegistrar(ActionEvent event) {
		try {
			System.out.println("Cargando ventana de Registro...");
			Parent NuevoRegistro = FXMLLoader.load(getClass().getResource("/vista/registro.fxml"));
			Stage Registro = new Stage();
			Registro.setTitle("Registro de Nuevo Usuario");
			Registro.setScene(new Scene(NuevoRegistro));
			Registro.show();
			Registro.setMinHeight(400);
			Registro.setMinWidth(500);
		} catch (Exception r) {
			ControladorAvisos.setMensajeError("No se pudo abrir la ventana de Registro.");

		}
	}

	public void btnAceptarActionPerformed(ActionEvent event) throws IOException {
		try {
			this.pressBtnAceptar();
		} catch (ControladorExcepciones e) {
			ControladorAvisos.setMensajeError("El usuario debe estar compuesto por 8 digitos y una letra.");
			e.abrirVentanaAvisos();
		}
	}

	public void pressBtnCancelar(ActionEvent event) {
		System.out.println("Saliendo de la aplicacion. Hasta pronto.");
		System.exit(0);
	}

	// PRESS ENTER
	public void textoClavePressed(KeyEvent event) throws IOException {
		if (event.getCode().equals(KeyCode.ENTER)) {
			try {
				this.pressBtnAceptar();
			} catch (ControladorExcepciones e) {
				ControladorAvisos.setMensajeError("Datos incorrectos.");
				e.abrirVentanaAvisos();
			}
		}

	}

	// METODOS
	public void pressBtnAceptar() throws IOException {
		try {

			// comprobar tipo de usuario, usamos switch para optimizar programa
			int usertype = 0;
			if (esPaciente()) {
				usertype = 1;
			}
			if (esCuidador()) {
				usertype = 2;
			}
			if (esMedico()) {
				usertype = 3;
			}if(esGestor()) {
				usertype = 4; 
			}

			switch (usertype) {

			case 1:
				try {
					System.out.println("Cargando ventana principal de Paciente...");
					Paciente p = fbd.visualizarPaciente(txtInputUsuario.getText().toUpperCase());
					ControladorPacientepp.setPacienteActual(p);
					Parent PacienteVentana = FXMLLoader.load(getClass().getResource("/vista/menupaciente.fxml"));
					Stage Pacientepp = new Stage();
					Pacientepp.setTitle("Menu Principal Paciente");
					Pacientepp.setScene(new Scene(PacienteVentana));
					Pacientepp.show();
					Pacientepp.setMinHeight(620);
					Pacientepp.setMinWidth(600);

					System.out.println("Cerrando ventana de Login.");
					Stage CerrarVentanaLogin = (Stage) buttonAceptar.getScene().getWindow();
					CerrarVentanaLogin.close();
				}

				catch (ControladorExcepciones case1) {
					ControladorAvisos.setMensajeError("No se pudo abrir la ventana de Paciente.");
					case1.abrirVentanaAvisos();
				}
				break;

			case 2:
				try {
					System.out.println("Cargando ventana principal de Cuidador...");
					Cuidador c = fbd.visualizarCuidador(txtInputUsuario.getText().toUpperCase());
					ControladorCuidadorpp.setCuidadorActual(c);
					Parent CuidadorVentana = FXMLLoader.load(getClass().getResource("/vista/cuidadorpp.fxml"));
					Stage Cuidadorpp = new Stage();
					Cuidadorpp.setTitle("Menu Principal Cuidador");
					Cuidadorpp.setScene(new Scene(CuidadorVentana));
					Cuidadorpp.show();
					Cuidadorpp.setMinHeight(360);
					Cuidadorpp.setMinWidth(500);

					System.out.println("Cerrando ventana de Login.");
					Stage CerrarVentanaLogin = (Stage) buttonAceptar.getScene().getWindow();
					CerrarVentanaLogin.close();
				}

				catch (ControladorExcepciones case2) {
					ControladorAvisos.setMensajeError("No se pudo abrir la ventana de Cuidador.");
					case2.abrirVentanaAvisos();
				}
				break;

			case 3:
				try {
					System.out.println("Cargando ventana principal de Medico...");
					Medico m = fbd.visualizarMedico(txtInputUsuario.getText().toUpperCase());
					ControladorMedicoSelectorPaciente.setMedicoActual(m);
					Parent MedicoVentana = FXMLLoader
							.load(getClass().getResource("/vista/medico_selector_paciente.fxml"));
					Stage Medicopp = new Stage();
					Medicopp.setTitle("Menu Medico - Seleccion Paciente");
					Medicopp.setScene(new Scene(MedicoVentana));
					Medicopp.show();
					Medicopp.setMinHeight(400);
					Medicopp.setMinWidth(800);

					System.out.println("Cerrando ventana de Login.");
					Stage CerrarVentanaLogin = (Stage) buttonAceptar.getScene().getWindow();
					CerrarVentanaLogin.close();

				}

				catch (ControladorExcepciones case3) {
					ControladorAvisos.setMensajeError("No se pudo abrir la ventana de Medico.");
					case3.abrirVentanaAvisos();
				}
				break;

			case 4:
				try {
					System.out.println("Cargando ventana principal de Gestor...");
					Medico m = fbd.visualizarMedico(txtInputUsuario.getText().toUpperCase());
					ControladorGestorpp.setGestor(m);;
					Parent GestorVentana = FXMLLoader.load(getClass().getResource("/vista/gestorpp.fxml"));
					Stage Gestorpp = new Stage();
					Gestorpp.setTitle("Menu Gestor");
					Gestorpp.setScene(new Scene(GestorVentana));
					Gestorpp.show();
					Gestorpp.setMinHeight(500);
					Gestorpp.setMinWidth(700);

					System.out.println("Cerrando ventana de Login.");
					Stage CerrarVentanaLogin = (Stage) buttonAceptar.getScene().getWindow();
					CerrarVentanaLogin.close();

				}

				catch (ControladorExcepciones case4) {
					ControladorAvisos.setMensajeError("No se pudo abrir la ventana de Medico.");
					case4.abrirVentanaAvisos();
				}
				break;
			default:
				// en caso de que se introduzca un DNI que no se encuentre en la base de datos
				ControladorAvisos.setMensajeError("Datos incorrectos.");
				throw new ControladorExcepciones();

			}
		}

		catch (ControladorExcepciones loginfailure) {
			ControladorAvisos.setMensajeError("Datos incorrectos.");
			loginfailure.abrirVentanaAvisos();
		}

	}

	public boolean esPaciente() {
		if (fbd.visualizarPaciente((txtInputUsuario.getText().toUpperCase())) == null) {

			return false;
		} else {
			Paciente p = fbd.visualizarPaciente((txtInputUsuario.getText().toUpperCase()));
			String contra = getMd5(txtInputPassword.getText());

			if (p.getContrasena().equalsIgnoreCase(contra)) {
				return true;
			}
		}
		return false;
	}

	public boolean esCuidador() {
		if (fbd.visualizarCuidador((txtInputUsuario.getText().toUpperCase())) == null) {
			return false;
		} else {
			Cuidador c = fbd.visualizarCuidador((txtInputUsuario.getText().toUpperCase()));
			String contra = getMd5(txtInputPassword.getText());
			if (c.getContrasena().equalsIgnoreCase(contra)) {
				return true;
			}
		}
		return false;
	}

	public boolean esMedico() {
		if (fbd.visualizarMedico(txtInputUsuario.getText().toUpperCase()) == null) {
			return false;
		} else {
			Medico m = fbd.visualizarMedico((txtInputUsuario.getText().toUpperCase()));

			String contra = getMd5(txtInputPassword.getText());
			if (m.getContrasena().equalsIgnoreCase(contra)) {
				@SuppressWarnings("unused")
				boolean a = m.isGestor();
				if (a == false) {
					return true;
				}
			}
		}
		return false;
	}
	public boolean esGestor() {
		if (fbd.visualizarMedico(txtInputUsuario.getText().toUpperCase()) == null) {
			return false;
		} else {
			Medico m = fbd.visualizarMedico((txtInputUsuario.getText().toUpperCase()));

			String contra = getMd5(txtInputPassword.getText());
			if (m.getContrasena().equalsIgnoreCase(contra)) {
				@SuppressWarnings("unused")
				boolean a = m.isGestor();
				if (a == true) {
					return true;
				}
			}
		}
		return false;
	}
	

	// funcion hash recibe contrasena y devuelve contrasena encriptado
	public static String getMd5(String input) {
		try {

			// Static getInstance method is called with hashing MD5
			MessageDigest md = MessageDigest.getInstance("MD5");

			// digest() method is called to calculate message digest
			// of an input digest() return array of byte
			byte[] messageDigest = md.digest(input.getBytes());

			// Convert byte array into signum representation
			BigInteger no = new BigInteger(1, messageDigest);

			// Convert message digest into hex value
			String hashtext = no.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}

			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

}