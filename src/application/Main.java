package application;



import baseDatos.FachadaBaseDatos;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
<<<<<<< HEAD
	private static FachadaBaseDatos fbd = null;
	@Override
	public void start(Stage primaryStage) {

		fbd = this.getFbd();
		
		
		Paciente p = new Paciente();
		fbd.insertarPaciente(p);
		

=======

	static baseDatos.FachadaBaseDatos fbd; 
	
	@Override
	public void start(Stage primaryStage) {

		fbd = new FachadaBaseDatos();
>>>>>>> 1ca3b64d836e3a58eec70f2c0536b60c75a33ece
		
		try {
			Parent Login = FXMLLoader.load(getClass().getResource("/vista/login.fxml"));
			primaryStage.setTitle("PDEA Login");
			primaryStage.setScene(new Scene(Login));
			primaryStage.show();
			primaryStage.setMinHeight(340);
			primaryStage.setMinWidth(520);
			primaryStage.setMaxHeight(500);
			primaryStage.setMaxWidth(520);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		launch(args);

		
	}
	
<<<<<<< HEAD
	
	public static FachadaBaseDatos getFbd () {
		if(fbd == null) {
			fbd = new FachadaBaseDatos();
			return fbd;
		}else{
			return fbd;
		}
	}
	
	
=======
	public static baseDatos.FachadaBaseDatos getFbd() {
		return fbd;
	}
	public void setFbd(baseDatos.FachadaBaseDatos fbd) {
		Main.fbd = fbd;
	}
>>>>>>> 1ca3b64d836e3a58eec70f2c0536b60c75a33ece
}
