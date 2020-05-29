package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.TimeZone;

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
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public class ControladorCuidadorEjerciciosPaciente implements Initializable {

	@FXML
	private ImageView pantallaEj;

	@FXML
	private Label campoHechos;
	
	@FXML
	private Label campoDuracion;

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
    private static Paciente pacienteActual = new Paciente();
	
    
    private baseDatos.FachadaBaseDatos fbd = application.Main.getFbd();

	
	@Override
	public void initialize(URL location, ResourceBundle reosurces) {


		Cuidador c = ControladorCuidadorpp.getCuidadorActual();
		campoCuidador.setText("Hola " + c.getNombre() + ",");
		campoPaciente.setText(pacienteActual.getNombreCompleto());

		// Aquí falta la chica del desplegable

		hechos = hizoEjerciciosHoy();


		if (hechos) {

			campoHechos.setText("Ejercicios acabados.");

		} else {
			campoHechos.setText("Ejercicios no acabados.");
		}

		this.ejercicios = fbd.obtenerEjerciciosPaciente(pacienteActual);
		if (ejercicios.size() != 0) {
			pantallaEj.setImage(new Image(this.getClass().getResource("/"+this.ejercicios.get(contador).getGif()).toExternalForm()));
			campoDuracion.setText("00:" +this.ejercicios.get(contador).getDuracion().toString());
		}else {
			Label emptyEnv = new Label("No hay ejercicios asignados.");
			emptyEnv.setFont(new Font("Arial", 18));
			emptyEnv.setLayoutY(60);
			emptyEnv.setLayoutX(5);
			//anchorGrande.getChildren().add(emptyEnv);
			AnchorPane.setTopAnchor(emptyEnv, Double.valueOf(40));
		}

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
			System.out.println("Este es en el ultimo ejercicio.");
			ControladorAvisos.setMensajeError("Este es en el ultimo ejercicio");
			abrirVentanaAvisos();
		} else {

			this.contador = contador + 1;

			pantallaEj.setImage(new Image(
					this.getClass().getResource("/" + this.ejercicios.get(contador).getGif()).toExternalForm()));
			campoDuracion.setText("00:" +this.ejercicios.get(contador).getDuracion().toString());
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
			campoDuracion.setText("00:" + this.ejercicios.get(contador).getDuracion().toString());
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
		return pacienteActual;
	}
    
    //SETTERS
    public static void setPacienteElegido(Paciente PacienteElegido) {
		pacienteActual = PacienteElegido;
	}
    
    private boolean hizoEjerciciosHoy() {
    	boolean hechos = pacienteActual.isEjerciciosHechos();
		if (hechos == true) {
			String fechaHoy = getFechaString(Calendar.getInstance().getTime());
			String fechaHizo = pacienteActual.getCuandoHechos();

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
