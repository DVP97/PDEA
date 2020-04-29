package controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TimeZone;

import com.jfoenix.controls.JFXButton;
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
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import modelo.Cita;
import modelo.Medico;
import modelo.Paciente;

public class ControladorCitarPaciente implements Initializable{

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
	
	private baseDatos.FachadaBaseDatos fbd = application.Main.getFbd();
    
	private ArrayList<Paciente> nombresPacientes = fbd.obtenerPacientesMedico(medicoActual);
	    
	private ObservableList<Paciente> listaPacientesComboBox = FXCollections.observableArrayList(nombresPacientes);
	
	
	    
	@Override
	public void initialize(URL location, ResourceBundle reosurces) {
		pacienteActual = ControladorMedicoSubmenuPaciente.getPacienteActual();
		medicoActual = ControladorMedicoSelectorPaciente.getMedicoActual();
		System.out.println(pacienteActual.getNombreCompleto());
		nombrePaciente.setText(pacienteActual.getNombreCompleto());
		campoMedico.setText("Hola " + medicoActual.getNombre()+",");	
	}

    @FXML
    void pressBtnCrearCita(ActionEvent event) {
  
		Cita nCita = new Cita();
		
		Paciente p = pacienteActual;
		// asociar mediante dni del paciente
		
		nCita.setPaciente(p.getDni());
		//nCita.setNombrePaciente();
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
		nCita.setFecha_cita(getFechaString(calend));
		

		// aniadir comentario del medico
		nCita.setNota(notaCita.getText());

		// introducir medico actual 
		nCita.setMedico(medicoActual.getDni());
		// escribir en la bbdd la cita 
		fbd.insertarCita(nCita);

		// aviso al usuario de que se ha creado la cita correctamente
		ControladorAvisos.setMensajeError("Se ha creado correctamente una nueva cita.");
		abrirVentanaAvisos();
		// limpiar los campos del tab de Citas
		nombrePaciente.setText(null);
		notaCita.clear();
		campoFecha.setValue(null);
		campoHora.setValue(null);
    }
    @FXML
    void pressBtnCerrar(ActionEvent event){
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
}

