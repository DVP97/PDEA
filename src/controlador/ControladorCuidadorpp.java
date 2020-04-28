package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import modelo.Cuidador;
import modelo.Paciente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class ControladorCuidadorpp implements Initializable {

	@FXML
	private Label campoCuidador;

	@FXML
	private JFXComboBox<String> buscarPacCB;

	@FXML
	private JFXButton btnEjercicios;

	@FXML
	private JFXButton btnAvisos;

	private ArrayList<String> nombresPacientes = getNombresPacientes();
	private ObservableList<String> listaPacientesComboBox = FXCollections.observableArrayList(nombresPacientes);
	

	private static Cuidador cuidadorActual = new Cuidador();

	private baseDatos.FachadaBaseDatos fbd = application.Main.getFbd();
	int numeroPacientes = fbd.obtenerPacientesCuidador(cuidadorActual).size();

	@Override
	public void initialize(URL location, ResourceBundle reosurces) {

		campoCuidador.setText("Hola " + cuidadorActual.getNombre()+ ",");
		buscarPacCB.setItems(listaPacientesComboBox);
		buscarPacCB.arm();
	}
	
	@FXML
	void comprobarInput(KeyEvent event) throws Exception {
		//buscarPacCB.arm();
		// comparar el nombre introducido con los pacientes asignados al cuidador, para
		// sugerir posibles coincidencias de forma dinamica

		ArrayList<String> sugerencias = new ArrayList<String>();

		int longitud = 0;
		int numeroPacientes = fbd.obtenerPacientesCuidador(cuidadorActual).size();
		boolean sugerenciasEncontradas;
		
		if (buscarPacCB.getValue() != null) {
			for (int i = 0; i < numeroPacientes; i++) {
				
				// bucle que va comparando el input con el nombre de cada paciente
				
				for (int a = 0; a < buscarPacCB.getValue().length(); a++) {
					// bucle que va comparando los char del input con los char del nombre de
					// paciente
					if (buscarPacCB.getValue().toLowerCase().charAt(a) == nombresPacientes.get(i).toLowerCase().charAt(a)) {
						longitud++;
					} else {
						break;
					}
				}
				if (longitud == buscarPacCB.getValue().length()) {
					// add nombre en posicion i a un nuevo arraylist que se pasa al comboBox con la
					// observableList listaSugerencias
					sugerencias.add(nombresPacientes.get(i));
					sugerenciasEncontradas = true;
				}
			}

			if (sugerenciasEncontradas = true) {
				buscarPacCB.getItems().clear();
				ObservableList<String> listaSugerencias = FXCollections.observableArrayList(sugerencias);
				buscarPacCB.setItems(listaSugerencias);
			}
		} else {
			// por defecto se muestra la lista entera de pacientes
			ObservableList<String> listaPacientesComboBox = FXCollections.observableArrayList(nombresPacientes);
			buscarPacCB.setItems(listaPacientesComboBox);
		}
		buscarPacCB.autosize();
		buscarPacCB.show();
		buscarPacCB.arm();
	}


	@FXML
	void pressBtnEjercicios(ActionEvent event) throws IOException {
    	try {
    		this.pressBtnE();
    	}catch(ControladorExcepciones e) {
    		ControladorAvisos.setMensajeError("El usuario seleccionado no es correcto.");
    		e.abrirVentanaAvisos();
    	}
	}	
	
	private void pressBtnE() throws IOException  {
    	
		String pacienteBuscado = buscarPacCB.getValue();
    	Paciente pacienteElegido = coincidencia(pacienteBuscado);
		
    	if (coincidencia(pacienteBuscado) != null) {
			try {
				System.out.println("Cargando Rutina ejercicios pacientes...");
				ControladorCuidadorEjerciciosPaciente.setPacienteElegido(pacienteElegido);
				Parent CuidadorEjercicios = FXMLLoader.load(getClass().getResource("/vista/cuidadorpp_ejercicios_de_paciente.fxml"));
				Stage CuidadorRutinaPaciente = new Stage();
				CuidadorRutinaPaciente.setTitle("Menu Cuidador - Ejercicios de " + pacienteElegido.getNombre());
				CuidadorRutinaPaciente.setScene(new Scene(CuidadorEjercicios));
				CuidadorRutinaPaciente.show();
				CuidadorRutinaPaciente.setMinHeight(600);
				CuidadorRutinaPaciente.setMinWidth(800);

				System.out.println("Cerrando ventana principal de Cuidador...");
				Stage CerrarVentanaCuidador = (Stage) btnEjercicios.getScene().getWindow();
				CerrarVentanaCuidador.close();
			}

			catch (ControladorExcepciones case1) {
				ControladorAvisos.setMensajeError("No se pudo abrir la ventana de Paciente.");
				case1.abrirVentanaAvisos();
			}
		} else {
			ControladorAvisos.setMensajeError("No se ha encontrado el paciente introducido.");
	       	abrirVentanaAvisos();
		}
		
	}
	
	@FXML
	void pressBtnAvisos(ActionEvent event) throws IOException {}
    	/*try {
    		this.pressBtnA();
    	}catch(ControladorExcepciones e) {
    		ControladorAvisos.setMensajeError("El usuario seleccionado no es correcto.");
    		e.abrirVentanaAvisos();
	}
	}
	/*
    private void pressBtnA() throws IOException  {
    		
       String pacienteBuscado = buscarPacCB.getValue();
       Paciente pacienteElegido = coincidencia(pacienteBuscado);
    		
        	if (coincidencia (pacienteBuscado) != null) {
    			try {
    				System.out.println("Cargando ventana de Avisos...");
    				ControladorCuidadorAvisos.setPacienteElegido(pacienteElegido);
    				Parent Avisos = FXMLLoader.load(getClass().getResource("/vista/cuidadorpp_avisos_de_paciente.fxml"));
    				Stage AvisosPaciente = new Stage();
    				AvisosPaciente.setTitle("Menu Cuidador - Avisos de " + pacienteElegido.getNombre());
    				AvisosPaciente.setScene(new Scene(Avisos));
    				AvisosPaciente.show();
    				AvisosPaciente.setMinHeight(800);
    				AvisosPaciente.setMinWidth(1200);

    				System.out.println("Cerrando ventana principal del Cuidador");
    				Stage CambioVentanaAvisos = (Stage) btnAvisos.getScene().getWindow();
    				CambioVentanaAvisos.close();
    			} catch (ControladorExcepciones r) {
    				ControladorAvisos.setMensajeError("No se pudo abrir la ventana de Avisos para Cuidador.");
    				r.abrirVentanaAvisos();
    			}
    		} else {
    			ControladorAvisos.setMensajeError("No se ha encontrado el paciente introducido.");
    	       	abrirVentanaAvisos();
    		}
    	}
	*/
    private Paciente coincidencia(String pacienteBuscado) {

		for (int i = 0; i < numeroPacientes; i++) {
			//ArrayList<String> nombresPacientes2 = fbd.obtenerPacientesCuidador(cuidadorActual);
			ArrayList<String> nombresPacientes = lectorJson.getNombresCompletosPacientesDeCuidador(cuidadorActual);
			
			if (nombresPacientes.get(i).equalsIgnoreCase(pacienteBuscado)) {
			
				Paciente p = fbd.obtenerPacientesCuidador(cuidadorActual).get(i);
				return p;
			}
		}
		return null;
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
    

	
	public ArrayList <String> getNombresPacientes () {
		
		ArrayList <String> nombres = new ArrayList <String>();
		ArrayList <Paciente> pacientes = fbd.obtenerPacientesCuidador(cuidadorActual);
		
		for (int i=0; i < pacientes.size(); i++) {
			
			nombres.add(pacientes.get(i).getNombreCompleto());
			
		}
		
		return nombres;
		
	}

	// GETTERS
	public static Cuidador getCuidadorActual() {
		return cuidadorActual;
	}
	// SETTERS
	public static void setCuidadorActual(Cuidador CuidadorActual) {
		cuidadorActual = CuidadorActual;
	}

}

