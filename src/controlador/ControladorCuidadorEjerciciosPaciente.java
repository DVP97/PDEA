package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modelo.Cuidador;
import modelo.Ejercicio;
import modelo.Paciente;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ControladorCuidadorEjerciciosPaciente implements Initializable {

	@FXML
	private ImageView pantallaEj;

	@FXML
	private Label campoHechos;

	@FXML
	private JFXButton btnAnterior;

	@FXML
	private JFXButton btnNext;

	@FXML
	private JFXButton btnVolver;

	@FXML
	private Label campoCuidador;
	
	@FXML
	private Label campoPaciente;

	private ArrayList<Ejercicio> ejercicios;
	private Integer contador = 0;
	private boolean hechos;
    private static Paciente pacienteElegido = new Paciente();

	
	@Override
	public void initialize(URL location, ResourceBundle reosurces) {

		Cuidador c = ControladorCuidadorpp.getCuidadorActual();
		campoCuidador.setText("Hola " + c.getNombre() + ",");
		campoPaciente.setText(pacienteElegido.getNombreCompleto());

		// Aquí falta la chica del desplegable
		Paciente p = ControladorPacientepp.getPacienteActual();
		hechos = p.isEjerciciosHechos();

		if (hechos) {

			campoHechos.setText("Ejercicios acabados.");

		} else {
			campoHechos.setText("Ejercicios no acabados.");
		}

		this.ejercicios = lectorJson.getEjercicios(p);
		// pantallaEj.setImage(new
		// Image(this.getClass().getResource("/"+this.ejercicios.get(contador).getGif()).toExternalForm()));

	}

	@FXML
	void pressBtnAnterior(ActionEvent event) {
		ejercicioAnterior();
	}

	@FXML
	void pressBtnNext(ActionEvent event) {
		siguienteEjercicio();
	}

	@FXML
	void pressBtnVolver(ActionEvent event) throws IOException {
		try {

			Parent CuidadorVentana = FXMLLoader.load(getClass().getResource("/vista/cuidadorpp.fxml"));
			Stage Cuidadorpp = new Stage();
			Cuidadorpp.setTitle("Menu Cuidador");
			Cuidadorpp.setScene(new Scene(CuidadorVentana));
			Cuidadorpp.show();
			Cuidadorpp.setMinHeight(400);
			Cuidadorpp.setMinWidth(800);

			Stage CerrarEjercicios = (Stage) btnVolver.getScene().getWindow();
			CerrarEjercicios.close();
		}

		catch (ControladorExcepciones case1) {
			ControladorAvisos.setMensajeError("No se pudo abrir el menu de cuidador.");
			case1.abrirVentanaAvisos();
		}
	}

	// MÉTODOS

	void siguienteEjercicio() {

		if (contador == ejercicios.size() - 1) {
			System.out.println("Este es en el último ejercicio.");
			ControladorAvisos.setMensajeError("Este es en el último ejercicio");
			abrirVentanaAvisos();
		} else {

			this.contador = contador + 1;

			pantallaEj.setImage(new Image(
					this.getClass().getResource("/" + this.ejercicios.get(contador).getGif()).toExternalForm()));
		}
	}

	void ejercicioAnterior() {

		if (contador == 0) {
			System.out.println("Primer ejercicio");
			ControladorAvisos.setMensajeError("Primer ejercicio.");
			abrirVentanaAvisos();
		} else {
			this.contador = contador - 1;
			pantallaEj.setImage(new Image(
					this.getClass().getResource("/" + this.ejercicios.get(contador).getGif()).toExternalForm()));

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

		} catch (Exception a) {
			System.out.println("Error");
		}
	}
	
	//GETTERS
    public static Paciente getPacienteElegido() {
		return pacienteElegido;
	}
    
    //SETTERS
    public static void setPacienteElegido(Paciente PacienteElegido) {
		pacienteElegido = PacienteElegido;
	}
}
