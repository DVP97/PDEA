package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import modelo.Cita;
import modelo.Medico;
import modelo.Paciente;

public class controladorCitarPaciente implements Initializable{

    @FXML
    private JFXButton buttonVolver;

    @FXML
    private Label campoMedico;

    @FXML
    private JFXDatePicker campoFecha;

    @FXML
    private JFXTimePicker campoHora;

    @FXML
    private JFXTextField nombrePaciente;

    @FXML
    private JFXTextField notaCita;

    @FXML
    private JFXButton buttonCrearCita;

    private static Paciente pacienteActual = new Paciente();
    
	private static Medico medicoActual = new Medico();
    
	private ArrayList<String> nombresPacientes = lectorJson.getNombresCompletosPacientesDe(medicoActual);
	    
	private ObservableList<String> listaPacientesComboBox = FXCollections.observableArrayList(nombresPacientes);
	    
	@Override
	public void initialize(URL location, ResourceBundle reosurces) {
		pacienteActual = ControladorMedicoSubmenuPaciente.getPacienteActual();
		System.out.println(pacienteActual.getNombreCompleto());
		nombrePaciente.setText(pacienteActual.getNombreCompleto());
	}

    @FXML
    void pressBtnCrearCita(ActionEvent event) {
    	ArrayList<Cita> addCita = lectorJson.lectorJsonCitas();
		Cita nCita = new Cita();
		
		Paciente p = pacienteActual;
		// asociar mediante dni del paciente
		nCita.setDni(p.getDni());
		// fecha de la cita
		String FechaN[] = campoFecha.getValue().toString().split("-");
		List<String> Fecha = Arrays.asList(FechaN);
		int dia = Integer.parseInt(Fecha.get(0));
		int mes = Integer.parseInt(Fecha.get(1));
		int anho = Integer.parseInt(Fecha.get(2));

		// hora de la cita
		String HoraN[] = campoHora.getValue().toString().split(":");
		List<String> HoraCita = Arrays.asList(HoraN);
		int hora = Integer.parseInt(HoraCita.get(0));
		int mins = Integer.parseInt(HoraCita.get(1));

		// guardar dia y hora de la cita en un Date
		Date calend = new Date(anho, mes, dia, hora, mins);
		nCita.setFecha(calend);

		// aniadir comentario del medico
		nCita.setNota(notaCita.getText());

		// aniadir nCita al Arraylist
		addCita.add(nCita);

		// escribir en el json de citas el Arraylist modificado
		escritorJson.escribirEnJsonCitas(addCita);

		// aviso al usuario de que se ha creado la cita correctamente
		ControladorAvisos.setMensajeError("Se ha a�adido correctamente una nueva cita.");
		abrirVentanaAvisos();
		// limpiar los campos del tab de Citas
		nombrePaciente.setText(null);
		notaCita.clear();
		campoFecha.setValue(null);
		campoHora.setValue(null);
    }
    @FXML
    void asdf(ActionEvent event){
		Stage cerrarCrearCita = (Stage) buttonVolver.getScene().getWindow();
		cerrarCrearCita.close();

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
}

