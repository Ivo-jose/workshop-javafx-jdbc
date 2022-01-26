package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.DepartmentService;

public class MainViewController implements Initializable{

	@FXML
	private MenuItem menuItemSeller;
	@FXML
	private MenuItem menuItemDepartment;
	@FXML
	private MenuItem menuItemAbout;
	
	@FXML
	public void onMenuItemSellerAction() {
		System.out.println("onMenuItemSellerAction");
	}
	
	@FXML
	public void onMenuItemDepartmentAction() {
		//Pedindo para o método loadView ler a tela de DepartmentList.fxml
		loadView("/gui/DepartmentList.fxml",(DepartmentListController controller) -> {
			controller.setDepartmentService(new DepartmentService());
			controller.updateTableView();
		});
	}
	

	@FXML
	public void onMenuItemAboutAction() {
		//Pedindo para o método loadView ler a tela de About.fxml
		loadView("/gui/About.fxml", x -> {});
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}
	
	
	//Criando um função para abrir outra tela(Pode ser usado em mais de uma tela)
	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newvBox = loader.load();
			//Mostrando a view about na view principal
			Scene mainScene = Main.getMainScene();
			//Pegando uma referência para o ScrollPane (MainView)
			VBox mainVBox = (VBox)((ScrollPane) mainScene.getRoot()).getContent();
			//Guardando uma referência para o menu
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			//Adicionando os filhos
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newvBox.getChildren());
			
			T controller = loader.getController();
			initializingAction.accept(controller);
			} 
		catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
}
