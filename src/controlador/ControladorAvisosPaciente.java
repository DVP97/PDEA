package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import modelo.Cita;
import modelo.Ejercicio;
import modelo.Paciente;


public class ControladorAvisosPaciente implements Initializable{

    @FXML
    private JFXButton btnAnterior;
    @FXML
    private JFXButton btnNext;
	@FXML
	private ImageView pantallaEj;
	@FXML
	private JFXButton btnVolver;
	@FXML
	private Label campoHechos;
	@FXML
	private Label campoFecha;
	@FXML
	private Label campoPaciente;
	
	private ArrayList<Ejercicio> ejercicios;
	private Integer contador=0;
	private Cita fecha_cita;
	private boolean hechos;

	@Override
	public void initialize(URL location, ResourceBundle reosurces) {
		
		Paciente p = ControladorPacientepp.getPacienteActual();
		campoPaciente.setText("Hola " +p.getNombre()+",");
		

	
		this.ejercicios = lectorJson.getEjercicios(p);
		pantallaEj.setImage(new Image(this.getClass().getResource("/"+this.ejercicios.get(contador).getGif()).toExternalForm()));
		//this.fecha_cita = lectorJson.getCita(fecha_cita);
		//Muestra el valor del booleano
		
        hechos = p.isEjerciciosHechos();

		
		if (hechos) {
			
			campoHechos.setText("Ejercicios acabados.");
			
		} else {
			campoHechos.setText("Ejercicios no acabados.");
		}
		//Muestra fecha de la cita
		fecha_cita = lectorJson.getCita(p.getDni());
		campoFecha.setText(fecha_cita.getFecha().toString());
		
	


	}
	

    @FXML
    void pressBtnAnterior(ActionEvent event) {
    	ejercicioAnterior();
    }
    @FXML
    void pressBtnNext(ActionEvent event) {
    	siguienteEjercicio();
    }

	//Botón volver
	@FXML
	void pressBtnVolver(ActionEvent event) throws IOException {
		try {
			System.out.println("Cargando ventana principal de Paciente...");
			Parent PacienteVentana = FXMLLoader.load(getClass().getResource("/vista/menupaciente.fxml"));
			Stage Pacientepp = new Stage();
			Pacientepp.setTitle("Menu Principal Paciente");
			Pacientepp.setScene(new Scene(PacienteVentana));
			Pacientepp.show();
			Pacientepp.setMinHeight(550);
			Pacientepp.setMinWidth(500);

			System.out.println("Cerrando ventana de Login.");
			Stage CerrarVentanaLogin = (Stage) btnVolver.getScene().getWindow();
			CerrarVentanaLogin.close();
		}	

		catch(ControladorExcepciones case1){
			ControladorAvisos.setMensajeError("No se pudo abrir la ventana de Paciente.");
			case1.abrirVentanaAvisos();
		}
	}
	
	void siguienteEjercicio() {
		
	
		if (contador==ejercicios.size()-1) {
			System.out.println("Este es en el último ejercicio.");
			ControladorAvisos.setMensajeError("Este es en el último ejercicio");
			abrirVentanaAvisos();
		} else {
			
		this.contador = contador+1;
	
		pantallaEj.setImage(new Image(this.getClass().getResource("/"+this.ejercicios.get(contador).getGif()).toExternalForm()));
	} 
	}
	
	void ejercicioAnterior() {
		
		if (contador==0) {
			System.out.println("Primer ejercicio");
			ControladorAvisos.setMensajeError("Primer ejercicio.");
			abrirVentanaAvisos();
		} else {
			this.contador = contador-1;
		pantallaEj.setImage(new Image(this.getClass().getResource("/"+this.ejercicios.get(contador).getGif()).toExternalForm()));
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
}





