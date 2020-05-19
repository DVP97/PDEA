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

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import modelo.Cita;
import modelo.Paciente;

public class ControladorAvisosPaciente implements Initializable {

	@FXML
	private JFXButton btnAnterior;

	@FXML
	private JFXButton btnNext;
	@FXML
	private JFXButton btnVolver;
	@FXML
	private Label campoHechos;
	@FXML
	private Label campoFecha;
	@FXML
	private Label campoPaciente;

	private static Cita fecha_cita;
	private baseDatos.FachadaBaseDatos fbd = application.Main.getFbd();
	private Paciente p = ControladorPacientepp.getPacienteActual();

	@Override
	public void initialize(URL location, ResourceBundle reosurces) {

		campoPaciente.setText("Hola " + p.getNombre() + ",");

		// Muestra el valor del booleano

		boolean hechos = hizoEjerciciosHoy();

		if (hechos) {

			campoHechos.setText("Ejercicios acabados.");

		} else {
			campoHechos.setText("Ejercicios no acabados.");
		}
		// Muestra fecha de la cita
		fecha_cita = seleccionarSiguienteCita();

	}

	// Botón volver
	@FXML
	void pressBtnVolver(ActionEvent event) throws IOException {
		try {
			Parent PacienteVentana = FXMLLoader.load(getClass().getResource("/vista/menupaciente.fxml"));
			Stage Pacientepp = new Stage();
			Pacientepp.setTitle("Menu Principal Paciente");
			Pacientepp.setScene(new Scene(PacienteVentana));
			Pacientepp.show();
			Pacientepp.setMinHeight(550);
			Pacientepp.setMinWidth(500);

			Stage CerrarVentanaLogin = (Stage) btnVolver.getScene().getWindow();
			CerrarVentanaLogin.close();
		}

		catch (ControladorExcepciones case1) {
			ControladorAvisos.setMensajeError("No se pudo abrir la ventana de Paciente.");
			case1.abrirVentanaAvisos();
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

	public class sortByDate implements Comparator<Cita> {
		@Override
		public int compare(Cita c1, Cita c2) {

			return c1.getFecha_cita().compareTo(c2.getFecha_cita());
		}
	}

	private Cita seleccionarSiguienteCita() {
		Cita c = new Cita();
		ArrayList<Cita> citas = borrarCitasPasadas();
		if (citas != null) {
			c = citas.get(0);
			campoFecha.setText(c.getFecha_cita());
		} else {
			campoFecha.setText("No tiene citas pendientes.");
		}
		return c;
	}

	private boolean hizoEjerciciosHoy() {
		boolean hechos = p.isEjerciciosHechos();
		if (hechos == true) {
			String fechaHoy = getFechaString(Calendar.getInstance().getTime());
			String fechaHizo = p.getCuandoHechos();

			boolean asdf = compararFechas(fechaHoy, fechaHizo, 1);
			if (asdf == true) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
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

	// GETTERS
	public static Cita getCita() {
		return fecha_cita;
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

	// SETTERS
	public static void setCuidadorActual(Cita fechaCita) {
		fecha_cita = fechaCita;
	}

	private ArrayList<Cita> borrarCitasPasadas() {
		ArrayList<Cita> citas = fbd.obtenerCitasPaciente(p);
		Collections.sort(citas, new sortByDate());
		ArrayList<Cita> resultado = new ArrayList<Cita>();
		if (citas != null) {
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
