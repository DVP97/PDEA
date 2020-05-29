package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.TimeZone;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modelo.Cita;
import modelo.Cuidador;
import modelo.Paciente;

public class ControladorCuidadorAvisos implements Initializable {

	@FXML
	private JFXComboBox<?> campoPacientes;

	@FXML
	private AnchorPane anchorPaneAvisos;

	@FXML
	private Label campoCuidador;
	@FXML
	private Label campoPaciente;

    @FXML
    private Label citaAvisos;
    
	@FXML
	private JFXButton btnVolver;
	@FXML
	private JFXButton btnSensores;

	private Cita fecha_cita;

	private static Paciente pacienteActual = new Paciente();

	//private ObservableList<Aviso> avisos = getAvisos();
	
	private baseDatos.FachadaBaseDatos fbd = application.Main.getFbd();

	@Override
	public void initialize(URL location, ResourceBundle reosurces) {

		Cuidador c = ControladorCuidadorpp.getCuidadorActual();
		campoCuidador.setText("Hola " + c.getNombre() + ",");
		campoPaciente.setText(pacienteActual.getNombreCompleto());

	
		fecha_cita = seleccionarSiguienteCita(pacienteActual);
		citaAvisos.setText(fecha_cita.getFecha_cita());

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
	
	@FXML
	void pressBtnSensores(ActionEvent event) throws IOException {
    	try {
    		this.pressBtnS();
    	}catch(ControladorExcepciones e) {
    		ControladorAvisos.setMensajeError("ERROR");
    		e.abrirVentanaAvisos();
    	}
	}

    private void pressBtnS() throws IOException  {
		

     			try {
     				System.out.println("Cargando ventana de Sensores...");

     				Parent Avisos = FXMLLoader.load(getClass().getResource("/vista/cuidador_sensores_de_paciente.fxml"));
     				Stage AvisosPaciente = new Stage();
     				AvisosPaciente.setTitle("Menu Cuidador - Avisos de " + pacienteActual.getNombre());
     				AvisosPaciente.setScene(new Scene(Avisos));
     				AvisosPaciente.show();
     				AvisosPaciente.setMinHeight(650);
     				AvisosPaciente.setMinWidth(700);
     				AvisosPaciente.setMaxWidth(700);
     				AvisosPaciente.setMaxHeight(750);
     				

     				System.out.println("Cerrando ventana avisos del Cuidador");
     				Stage CambioVentanaSensores = (Stage) btnSensores.getScene().getWindow();
     				CambioVentanaSensores.close();
     			} catch (ControladorExcepciones r) {
     				ControladorAvisos.setMensajeError("No se pudo abrir la ventana de Sensores para Cuidador.");
     				r.abrirVentanaAvisos();
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

	// GETTERS
	public static Paciente getPacienteElegido() {
		return pacienteActual;
	}
	// SETTERS
	public static void setPacienteElegido(Paciente PacienteElegido) {
		pacienteActual = PacienteElegido;
	}
	


	public class sortByDate implements Comparator<Cita> {
		@Override
		public int compare(Cita c1, Cita c2) {
			return c1.getFecha_cita().compareTo(c2.getFecha_cita());
			
			
		}
	}

	public Cita seleccionarSiguienteCita(Paciente p) {
		Cita c = new Cita();
		ArrayList<Cita> citas = borrarCitasPasadas();
		if (citas.size() != 0) {
			c = citas.get(0);
			citaAvisos.setText(c.getFecha_cita());
		} else {
			citaAvisos.setText("No tiene citas pendientes.");
		}
		return c;
	}
	
	private ArrayList<Cita> borrarCitasPasadas() {
		ArrayList<Cita> citas = fbd.obtenerCitasPaciente(pacienteActual);
		Collections.sort(citas, new sortByDate());
		ArrayList<Cita> resultado = new ArrayList<Cita>();
		if (citas.size() != 0) {
			Collections.sort(citas, new sortByDate());
			for (int i = 0; i < citas.size(); i++) {
				String fechaHoy = getFechaString(Calendar.getInstance().getTime());
				boolean b = compararFechas(fechaHoy, citas.get(i).getFecha_cita(), 0);
				if (b == true) {
					resultado.add(citas.get(i));
				}
			}
		}
		return resultado;
	}
	
	@SuppressWarnings("deprecation")
	private String getFechaString(Date dummy) {
		// Choose time zone in which you want to interpret your Date
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));

		cal.setTime(dummy);
		Integer dia =  ((Integer) dummy.getDate());
		String dias;
		if (dia < 10) {
			dias = "0" + dia.toString();
		} else {
			dias = dia.toString();
		}
		Integer m = (Integer)dummy.getMonth() + 1;
		String mess;
		if (m < 10) {
			mess = "0" + m.toString();
		} else {
			mess = m.toString();
		}
		int year = cal.get(Calendar.YEAR);
		String anho = ((Integer) year).toString();
		Integer hora =  ((Integer) dummy.getHours());
		String horas;
		if (hora < 10) {
			horas = "0" + hora.toString();
		} else {
			horas = hora.toString();
		}
		Integer min = (Integer) dummy.getMinutes();
		String mins;
		if (min < 10) {
			mins = "0" + min.toString();
		} else {
			mins = min.toString();
		}
		String f = horas + ":" + mins + "-" + dias + "/" + mess + "/" + anho;
		return f;
	}
	
	@SuppressWarnings("deprecation")
	private boolean compararFechas(String fechaHoy, String fechaHizo, int b) {
		// HOY
		String partsHoy[] = fechaHoy.split("-");
		String partshoraHoy = partsHoy[0];
		String partsdiaHoy = partsHoy[1];

		// FECHA HORA:MINUTOS
		String partshoraHoy1[] = partshoraHoy.split(":");
		Integer horaHoy = Integer.parseInt(partshoraHoy1[0]);
		Integer minutosHoy = Integer.parseInt(partshoraHoy1[1]);

		// FECHA DIA/MES/AÑO
		String partshoraHoy2[] = partsdiaHoy.split("/");
		Integer diaHoy = Integer.parseInt(partshoraHoy2[0]);
		Integer mesHoy = Integer.parseInt(partshoraHoy2[1]);
		Integer anoHoy = Integer.parseInt(partshoraHoy2[2]);

		// Construimos el date
		Date dateHoy = new Date((anoHoy - 1900), mesHoy, diaHoy, horaHoy, minutosHoy);

		// HIZO EJERCICIOS
		String partsHizo[] = fechaHizo.split("-");
		String partshoraHizo = partsHizo[0];
		String partsdiaHizo = partsHizo[1];
		// FECHA HORA:MINUTOS
		String partshoraHizo1[] = partshoraHizo.split(":");
		Integer horaHizo = Integer.parseInt(partshoraHizo1[0]);
		Integer minutosHizo = Integer.parseInt(partshoraHizo1[1]);

		// FECHA DIA/MES/AÑO
		String partshoraHizo2[] = partsdiaHizo.split("/");
		Integer diaHizo = Integer.parseInt(partshoraHizo2[0]);
		Integer mesHizo = Integer.parseInt(partshoraHizo2[1]);
		Integer anoHizo = Integer.parseInt(partshoraHizo2[2]);

		// Construimos el date
		Date dateHizo = new Date((anoHizo - 1900), mesHizo, diaHizo, horaHizo, minutosHizo);

		// Calendar auxiliar (sumamos 24 horas a la fecha de cuando hizo los ejercicios)
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateHizo);

		cal.add(Calendar.DAY_OF_MONTH, b);

		Date dateHizoSumada = cal.getTime();

		int a = comparar(dateHoy, dateHizoSumada);
		if (a > 0) {
			return false;
		} else if (a < 0) {
			return true;
		} else if (a == 0) {
			return true;
		}
		return false;
	}
	
	private int comparar(Date hoy, Date hizo) {
		if (hoy.compareTo(hizo) > 0) {
			return 1;
		} else if (hoy.compareTo(hizo) < 0) {
			return -1;
		} else if (hoy.compareTo(hizo) == 0) {
			return 0;
		}
		return 0;
	}
}