package controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import modelo.Medico;
import modelo.Paciente;
import modelo.Mensaje;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTabPane;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;



public class ControladorMedicopp implements Initializable {


    @FXML
    private Label campoMedico;

    @FXML
    private Label labelRedactar;

    @FXML
    private Label labelBandejaEntrada;

    @FXML
    private Label labelBandejaSalida;

    @FXML
    private TextField campoBusqueda;

    @FXML
    private ListView<String> listaPacientes;

    @FXML
    private TextField campoDNI;

    @FXML
    private TextField campoNombre;

    @FXML
    private TextField campoTelefono;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnEjercicios;

    @FXML
    private AnchorPane anchorPaneRecibidos;

    @FXML
    private Accordion AccordionMensajesRec;

    @FXML
    private AnchorPane anchorPaneEnviados;

    @FXML
    private Accordion AccordionMensajesEnv;

    @FXML
    private TextArea campoRedactar;

    @FXML
    private JFXButton btnConfirmarEnvio;

    @FXML
    private JFXButton btnResponder;

    @FXML
    private JFXTabPane JFXTabPaneMensajeria;

    @FXML
    private JFXComboBox<String> comboBoxElegirDestinatario;


    private ObservableList<String> listaPacientesComboBox = FXCollections.observableArrayList(getNombrePacientes());

    private static Medico medicoActual = new Medico();

    //Metodos

    //Por ahora así, en algun momento filtraremos por paciente ya que el medico accedera directamente a solo un paciente
    @Override
    public void initialize(URL location, ResourceBundle reosurces) {
        JFXTabPaneMensajeria.setTabMinWidth(100);
        JFXTabPaneMensajeria.setTabMinHeight(100);
    	labelRedactar.setRotate(90);
    	labelBandejaEntrada.setRotate(90);
    	labelBandejaSalida.setRotate(90);
    	comboBoxElegirDestinatario.setItems(listaPacientesComboBox);

    	Medico m = ControladorMedicopp.getMedicoActual();
    	campoMedico.setText("Hola " +ControladorMedicopp.getMedicoActual().getNombre()+",");

    	ArrayList<TitledPane> tpsr = new ArrayList<TitledPane>();
    	ArrayList<TitledPane> tpse = new ArrayList<TitledPane>();

    	if(numeroMensajesRecibidos()>0) {
    		for(int i=0; i<numeroMensajesRecibidos(); i++) {
    			ArrayList<Mensaje> mensajeRec = lectorJson.getMensajesEnviadosA(m.getDni());
    			Mensaje mensajeAct = mensajeRec.get(i);
    			Paciente pacEmisor = lectorJson.getPaciente(mensajeAct.getEmisor());
    			Label contenido = new Label(mensajeAct.getMensaje());
				ScrollPane panelContenido = new ScrollPane(contenido);
				contenido.boundsInParentProperty();
				TitledPane tp = new TitledPane("De: " +pacEmisor.getNombre() + " " + pacEmisor.getApellidos(), panelContenido) ;
				tp.setId(pacEmisor.getDni());
				tpsr.add(i, tp);
    		}
    		AccordionMensajesRec.setLayoutY(60);
			AccordionMensajesRec.setLayoutX(5);
			AccordionMensajesRec.getPanes().addAll(tpsr);
			AnchorPane.setTopAnchor(AccordionMensajesRec, Double.valueOf(30));

    	}
    	else {
    		Label emptyRec = new Label("No hay mensajes en la bandeja de entrada.");
			emptyRec.setFont(new Font("Arial", 18));
			emptyRec.setLayoutY(60);
			emptyRec.setLayoutX(5);
			anchorPaneRecibidos.getChildren().add(emptyRec);
			AnchorPane.setTopAnchor(emptyRec, Double.valueOf(40));
    	}


    	if (numeroMensajesEnviados() > 0) {
			//identificar primero tipo de usuario

			for (int i = 0; i < numeroMensajesEnviados(); i++) {
				ArrayList<Mensaje> mensajesEnv  = lectorJson.getMensajesEnviadosPor(m.getDni());
				Mensaje mensajeAct = mensajesEnv.get(i);
				Paciente pacienteReceptor = lectorJson.getPaciente(mensajeAct.getReceptor());
				Label contenido = new Label(mensajeAct.getMensaje());
				ScrollPane panelContenido = new ScrollPane(contenido);
				contenido.minHeight(60);
				contenido.boundsInParentProperty();
				contenido.wrapTextProperty();
				TitledPane tp = new TitledPane("Para: " + pacienteReceptor.getNombre() + " " +pacienteReceptor.getApellidos() , panelContenido) ;
				tp.setId(pacienteReceptor.getDni());
				tpse.add(i, tp);
			}


			AccordionMensajesEnv.setLayoutY(60);
			AccordionMensajesEnv.setLayoutX(5);
			AccordionMensajesEnv.getPanes().addAll(tpse);
			AnchorPane.setTopAnchor(AccordionMensajesEnv, Double.valueOf(30));
		}
		else {
			Label emptyEnv = new Label("No hay mensajes enviados.");
			emptyEnv.setFont(new Font("Arial", 18));
			emptyEnv.setLayoutY(60);
			emptyEnv.setLayoutX(5);
			anchorPaneEnviados.getChildren().add(emptyEnv);

			AnchorPane.setTopAnchor(emptyEnv, Double.valueOf(40));
		}

	}

    @FXML
    void pressBtnConfirmarEnvio(ActionEvent event) {

    	if(campoRedactar.getText().length()>0) {

    		try {
    			String pac = comboBoxElegirDestinatario.getValue();
    			Integer indice = getIndiceComboBox(pac);
    			if (indice!= null) {
	    			String dniPac = medicoActual.getPacientes().get(indice);

	    			enviarMensaje(dniPac);
    			}else {
    				ControladorAvisos.setMensajeError("Por favor, seleccione un paciente.");
					abrirVentanaAvisos();

    			}
    		}
    		catch(Exception a) {
    			ControladorAvisos.setMensajeError("Error enviando el mensaje.");
				abrirVentanaAvisos();
    		}
    	}
    	else{
			ControladorAvisos.setMensajeError("No ha introducido texto alguno en el mensaje que intenta enviar.");
			abrirVentanaAvisos();
		}
    }

    @FXML
    void pressBtnResponder(ActionEvent event) {
    	TitledPane tp = getExpanded();
    	if(tp!=null) {
	    	String dni = tp.getId();
	    	Paciente pac = lectorJson.getPaciente(dni);
	    	
	    	comboBoxElegirDestinatario.setValue(pac.getNombre()+ " "+ pac.getApellidos());
	
	    	JFXTabPaneMensajeria.getSelectionModel().select(2);
    	
    	}else {
    		ControladorAvisos.setMensajeError("Por favor, seleccione un mensaje.");
			abrirVentanaAvisos();
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

    //Getters y Setters
	public static Medico getMedicoActual() {
		return medicoActual;
	}

	public static void setMedicoActual(Medico MedicoActual) {
		medicoActual = MedicoActual;
	}

	public ArrayList<String> getNombrePacientes (){
		ArrayList<String> pacientes = new ArrayList<String>();
		ArrayList<String> pacientesDnis = medicoActual.getPacientes();

		for (int i = 0; i < pacientesDnis.size(); i++) {

			String nombrePac = lectorJson.getPaciente(pacientesDnis.get(i)).getNombre();
			String apellidosPac = lectorJson.getPaciente(pacientesDnis.get(i)).getApellidos();
			String nombreCompleto = nombrePac + " " + apellidosPac;
			pacientes.add(nombreCompleto);
		}
		return pacientes;
	}

	public ArrayList<String> getDnisPacientes (){
		return medicoActual.getPacientes();
	}

	public Integer getIndiceComboBox(String pac) {
		for (int i  = 0 ; i < getNombrePacientes().size(); i++) {
			if (getNombrePacientes().get(i).equalsIgnoreCase(pac)) {
				return i;
			}
		}
		return null;
	}

	public Integer numeroMensajesRecibidos() {
		Medico m = ControladorMedicopp.getMedicoActual();
		return lectorJson.getMensajesEnviadosA(m.getDni()).size();
	}

	public Integer numeroMensajesEnviados() {
		Medico m = ControladorMedicopp.getMedicoActual();
		return lectorJson.getMensajesEnviadosPor(m.getDni()).size();
	}

	public void enviarMensaje(String dniPac) {
    	Mensaje msg = new Mensaje(getMedicoActual().getDni(), dniPac, campoRedactar.getText());
		ArrayList<Mensaje> mensajes = new ArrayList<Mensaje>();
		mensajes= lectorJson.lectorJsonMensajes();
		mensajes.add(msg);
		escritorJson.escribirEnJsonMensajes(mensajes);
		Paciente pacienteReceptor = lectorJson.getPaciente(dniPac);


		Label contenido = new Label(msg.getMensaje());
		ScrollPane panelContenido = new ScrollPane(contenido);
		contenido.boundsInParentProperty();

		TitledPane tp = new TitledPane("Para: " + pacienteReceptor.getNombre()+" "+ pacienteReceptor.getApellidos() , panelContenido) ;
		tp.setId(dniPac);

		AccordionMensajesEnv.getPanes().add(tp);


		ControladorAvisos.setMensajeError("Mensaje Enviado.");
		abrirVentanaAvisos();

		campoRedactar.clear();
    }

	public TitledPane getExpanded () {
    	ObservableList<TitledPane> panes = AccordionMensajesRec.getPanes();
    	
    	for (int i = 0 ; i < panes.size(); i++) {
    		if(panes.get(i).isExpanded()) {
    			return panes.get(i);
    		}
    	}
		return null;
    }
}
