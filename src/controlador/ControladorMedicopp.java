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
import javafx.scene.control.ComboBox;
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
    private TextArea campoRedactar;

    @FXML
    private JFXButton btnConfirmarEnvio;

    @FXML
    private JFXComboBox<String> comboBoxElegirDestinatario;

    private ObservableList<String> listaPacientesComboBox = FXCollections.observableArrayList(medicoActual.getPacientes());
    
    private static Medico medicoActual = new Medico();
   
    //Metodos
    
    //adaptar el código para poder filtrar mensajes por usuario? o solo enviar a pacientes?
    
    @Override
    public void initialize(URL location, ResourceBundle reosurces) {
    	
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
				TitledPane tp = new TitledPane("De: " +pacEmisor.getNombre()  , panelContenido) ;
						
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
    	/*
    	//falta por diseñar la pestaña de mensajes enviados
    	
    	if (numeroMensajesEnviados() > 0) {
			//identificar primero tipo de usuario
			 * 
			for (int i = 0; i < numeroMensajesEnviados(); i++) {
				ArrayList<Mensaje> mensajesEnv  = lectorJson.getMensajesEnviadosPor(p.getDni());
				Mensaje mensajeAct = mensajesEnv.get(i);
				Medico medReceptor = lectorJson.getMedico(mensajeAct.getReceptor());
				Label contenido = new Label(mensajeAct.getMensaje());
				ScrollPane panelContenido = new ScrollPane(contenido);
				contenido.boundsInParentProperty();
				TitledPane tp = new TitledPane("Para: " + medReceptor.getNombre() , panelContenido) ;
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
		*/
	}
    
    @FXML
    void pressBtnConfirmarEnvio(ActionEvent event) {
    	if(campoRedactar.getText().length()>0) {
    		
    		try {
    			String pac = comboBoxElegirDestinatario.getValue();
				Mensaje msg = new Mensaje(getMedicoActual().getDni(), pac, campoRedactar.getText());
				ArrayList<Mensaje> mensajes = new ArrayList<Mensaje>();
				mensajes= lectorJson.lectorJsonMensajes();
				mensajes.add(msg);
				escritorJson.escribirEnJsonMensajes(mensajes);
			
				/*
				//adaptar codigo para agregar mensaje enviado a la bandeja de Enviados
	
				Medico m = ControladorMedicopp.getMedicoActual();
				
				Label contenido = new Label(msg.getMensaje());
				ScrollPane panelContenido = new ScrollPane(contenido);
				contenido.boundsInParentProperty();
				Medico med = lectorJson.getMedico(medPac);
				TitledPane tp = new TitledPane("Para: " + med.getNombre() , panelContenido) ;
				
	
				AccordionMensajesEnv.getPanes().add(tp);
				*/
    			/*
    			if(esPaciente()) {
    				 
    			}
    			if(esCuidador()) {
    				String pac = ControladorPacientepp.getPacienteActual().getDni();
    				Mensaje msg = new Mensaje(getMedicoActual().getDni(), pac, campoRedactar.getText());
    				ArrayList<Mensaje> mensajes = new ArrayList<Mensaje>();
    				mensajes= lectorJson.lectorJsonMensajes();
    				mensajes.add(msg);
    				escritorJson.escribirEnJsonMensajes(mensajes);
    			
    			/*
    			//adaptar codigo para agregar mensaje enviado a la bandeja de Enviados

    			Medico m = ControladorMedicopp.getMedicoActual();
    			
    			Label contenido = new Label(msg.getMensaje());
				ScrollPane panelContenido = new ScrollPane(contenido);
				contenido.boundsInParentProperty();
				Medico med = lectorJson.getMedico(medPac);
				TitledPane tp = new TitledPane("Para: " + med.getNombre() , panelContenido) ;
								

				AccordionMensajesEnv.getPanes().add(tp);
    			 
    			}
				*/
    			ControladorAvisos.setMensajeError("Mensaje Enviado.");
				abrirVentanaAvisos();
				
    			campoRedactar.clear();
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
    
    public Integer numeroMensajesRecibidos() {
		Medico m = ControladorMedicopp.getMedicoActual();
		return lectorJson.getMensajesEnviadosA(m.getDni()).size();
	}
	
	public Integer numeroMensajesEnviados() {
		Medico m = ControladorMedicopp.getMedicoActual();
		return lectorJson.getMensajesEnviadosPor(m.getDni()).size();
	}
	//funciones para identificar usuario al que se manda el mensaje
	//los datos se leen desde el buscador/comboBox de la pestaña mensajes
	/*
	public boolean esPaciente() {
		if(lectorJson.getPaciente(txtInputUsuario.getText().toUpperCase()) == null) {
			return false;
		}else {
			return true;
		}
	}
	

	public boolean esCuidador() {
		if(lectorJson.getCuidador(txtInputUsuario.getText().toUpperCase()) == null) {
			return false;
		}else {
			return true;
		}
	}
	*/
    //Getters y Setters
	public static Medico getMedicoActual() {
		return medicoActual;
	}

	public static void setMedicoActual(Medico MedicoActual) {
		medicoActual = MedicoActual;
	}
 
}