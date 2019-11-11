package controlador;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class ControladorRegistro {

    @FXML
    private TextField textoDNI;

    @FXML
    private TextField textoNombre;

    @FXML
    private PasswordField textoContrasena;

    @FXML
    private PasswordField textoContrasena2;

    @FXML
    private ComboBox<?> comboRol;

    @FXML
    private TextField textoApellidos;

    @FXML
    private JFXButton btnCancelar;

    @FXML
    private JFXButton btnAceptar;
    
    @FXML
    void elegirRol(ActionEvent event) {

    }

    @FXML
    void pulsarBtnAceptar_reg(ActionEvent event) {

    }

    @FXML
    void pulsarBtnCancelar_reg(ActionEvent event) {

    }

    //Getters y Setters
	public TextField getTextoDNI() {
		return textoDNI;
	}

	public void setTextoDNI(TextField textoDNI) {
		this.textoDNI = textoDNI;
	}

	public TextField getTextoNombre() {
		return textoNombre;
	}

	public void setTextoNombre(TextField textoNombre) {
		this.textoNombre = textoNombre;
	}

	public PasswordField getTextoContrasena() {
		return textoContrasena;
	}

	public void setTextoContrasena(PasswordField textoContrasena) {
		this.textoContrasena = textoContrasena;
	}

	public PasswordField getTextoContrasena2() {
		return textoContrasena2;
	}

	public void setTextoContrasena2(PasswordField textoContrasena2) {
		this.textoContrasena2 = textoContrasena2;
	}

	public ComboBox<?> getComboRol() {
		return comboRol;
	}

	public void setComboRol(ComboBox<?> comboRol) {
		this.comboRol = comboRol;
	}

	public TextField getTextoApellidos() {
		return textoApellidos;
	}

	public void setTextoApellidos(TextField textoApellidos) {
		this.textoApellidos = textoApellidos;
	}
    
    
}
