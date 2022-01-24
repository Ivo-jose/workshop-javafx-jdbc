package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;


public class Main extends Application {
	
	//Criando atributo para ser usado de referência 
	private static Scene mainScene;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
			ScrollPane scrollPane = loader.load();
			
			//Macete para deixar o ScrollPane a janela
			scrollPane.setFitToHeight(true);
			scrollPane.setFitToWidth(true);
			
		    mainScene = new Scene(scrollPane);
			primaryStage.setScene(mainScene);
			primaryStage.setTitle("Sample JavaFX Application");
			primaryStage.show();
		} 
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//Criando método para pegar o atributo private mainScene
	public static Scene getMainScene() {
		return mainScene;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
