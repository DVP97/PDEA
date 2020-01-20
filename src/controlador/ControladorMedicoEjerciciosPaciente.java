
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import modelo.Ejercicio;
import modelo.Paciente;

public class ControladorMedicoEjerciciosPaciente implements Initializable{

    @FXML
    private JFXComboBox<String> comboPaciente;

    @FXML
    private TitledPane btnEditar1;

    @FXML
    private TitledPane btnEditar2;

    @FXML
    private JFXButton btnEditar3;

    @FXML
    private JFXButton buttonVolver;
    
    @FXML
    private JFXButton btnAnadirEjercicio;
    
    @FXML
    private JFXButton btnEliminarEjercicio;
    
    @FXML
    private AnchorPane anchorPaneEjercicios;
    
    @FXML
    private Accordion accordionEjercicios;
    
    private static Paciente pacienteActual = new Paciente();
    
    private ArrayList<String> nombresEjercicios = getNombresEjercicios();
    
    private ObservableList<String> listaEjerciciosComboBox = FXCollections.observableArrayList(nombresEjercicios);

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	pacienteActual = ControladorMedicoSubmenuPaciente.getPacienteActual();
    	comboPaciente.setItems(listaEjerciciosComboBox);
    	setTitlePanesEjercicios();
    }
    
    private TitledPane getExpanded() {
		ObservableList<TitledPane> panes = accordionEjercicios.getPanes();

		for (int i = 0; i < panes.size(); i++) {
			if (panes.get(i).isExpanded()) {
				return panes.get(i);
			}
		}
		return null;
	}
    
    /*private Integer getIndiceComboBox(String pac) {
		for (int i = 0; i < getNombresEjercicios().size(); i++) {
			if (getNombresEjercicios().get(i).equalsIgnoreCase(pac)) {
				return i;
			}
		}
		return null;
	}*/
   
    
    @FXML
    void pressBtnVolver(ActionEvent event) throws IOException {
		
		try {
			System.out.println("Cargando submenu paciente...");
			Parent medicoSubMenuPaciente = FXMLLoader.load(getClass().getResource("/vista/medico_submenu_paciente.fxml"));
			Stage subMenuPaciente = new Stage();
			subMenuPaciente.setTitle("Menu " +pacienteActual.getNombreCompleto());
			subMenuPaciente.setScene(new Scene(medicoSubMenuPaciente));
			subMenuPaciente.show();
			subMenuPaciente.setMinHeight(600);
			subMenuPaciente.setMinWidth(800);

			Stage CerrarVentanaSensores = (Stage) buttonVolver.getScene().getWindow();
			CerrarVentanaSensores.close();
		}	
			
		catch(ControladorExcepciones case1){
			ControladorAvisos.setMensajeError("No se pudo abrir la ventana de Submenu.");
			case1.abrirVentanaAvisos();
		}
		
	}

    @FXML
    void pressBtnAnadir(ActionEvent event) {

    }

    @FXML
    void pressBtnEliminar(ActionEvent event) {
    	
    	TitledPane tp = getExpanded();
    	
    	if (tp!= null) {
    		Integer id = Integer.parseInt(tp.getId());
    		Ejercicio ejercicio = lectorJson.getEjercicio(id);
    		
    		ArrayList<Ejercicio> ejPac = lectorJson.getEjercicios(pacienteActual);
    		ArrayList<Integer> devuelvo = new ArrayList<Integer>();

    		for (int i = 0 ; i < ejPac.size(); i++) {
    			if (ejPac.get(i).getId() != ejercicio.getId()) {
    				devuelvo.add(ejPac.get(i).getId());
    			}
    		}
    		pacienteActual.setEjercicios(devuelvo);
    		
    		
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

		} catch (Exception a) {
			System.out.println("Error");
		}
	}
    
    private ArrayList<String> getNombresEjercicios(){
    	ArrayList<Ejercicio> ejercicios = lectorJson.getEjercicios(pacienteActual);
    	ArrayList<String> nombres = new ArrayList<String>();
    	for(int i = 0 ; i < ejercicios.size() ; i++) {
    		Ejercicio e = ejercicios.get(i);
    		nombres.add(e.getNombre());
    	}
    	return nombres;
    }
    
    private void setTitlePanesEjercicios() {
    	ArrayList<TitledPane> tpse = new ArrayList<TitledPane>();
    	
    	if(numeroEjerciciosPaciente()>0) {
    		for (int i = 0 ; i < numeroEjerciciosPaciente(); i++) {
    			ArrayList<Ejercicio> ejerciciosPac = lectorJson.getEjercicios(pacienteActual);
    			List<Ejercicio> listEjerciciosPac = new ArrayList<Ejercicio>();
    			listEjerciciosPac.addAll(ejerciciosPac);
    			Ejercicio ejercicioActual = listEjerciciosPac.get(i);
    			
    			Label contenido = new Label(ejercicioActual.getNombre());
				ScrollPane panelContenido = new ScrollPane(contenido);
				contenido.boundsInParentProperty();
    			
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append("Ejercicio ");
				stringBuilder.append(i+1);
				
				
				TitledPane tp = new TitledPane(stringBuilder.toString(), panelContenido) ;
				tp.setId(ejercicioActual.getId().toString());
				tpse.add(i, tp);
    		}
    		
    		accordionEjercicios.setLayoutY(60);
    		accordionEjercicios.setLayoutX(5);
    		accordionEjercicios.getPanes().addAll(tpse);
			AnchorPane.setTopAnchor(accordionEjercicios, Double.valueOf(30));
    	}
    	else {
			Label emptyEnv = new Label("No hay mensajes enviados.");
			emptyEnv.setFont(new Font("Arial", 18));
			emptyEnv.setLayoutY(60);
			emptyEnv.setLayoutX(5);
			anchorPaneEjercicios.getChildren().add(emptyEnv);
			
			AnchorPane.setTopAnchor(emptyEnv, Double.valueOf(40));
		}
    }
    
    private Integer numeroEjerciciosPaciente() {
    	return lectorJson.getEjercicios(pacienteActual).size();
    }
	
}