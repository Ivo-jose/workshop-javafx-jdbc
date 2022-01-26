package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentListController implements Initializable {
	
	//Declarando uma dependência do DepartmentService
	private DepartmentService service;
	
	@FXML
	private TableView<Department> tableViewDepartment;
	@FXML
	private TableColumn<Department, Integer> tableColumnId;
	@FXML
	private TableColumn<Department, String> tableColumnName;
	@FXML
	private Button btNew;
	
	private ObservableList<Department> obsList;
	
	
	
	//Método do tratamento de eventos do click do botão
	@FXML
	public void onBtNewAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		createDialogForm("/gui/DepartmentForm.fxml", parentStage);
	}
	
	//Método para injeção de dependência de service sem acoplamento forte
	public void setDepartmentService(DepartmentService service) {
		this.service = service;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		//Chamando método auxiliar
		initializeNodes();
	}

	private void initializeNodes() {
		//Comando para iniciar apropriadamente o comportamentos da colunas da table, (passando por parâmetro id da classe Department)
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		//Comando para iniciar apropriadamente o comportamentos da colunas da table, (passando por parâmetro name da classe Department)
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		//Configurando a tabela para ser redimensionada de acordo com a janela
		
	    //1º· pegando uma referência para o Stage atual
		Stage stage =(Stage) Main.getMainScene().getWindow();
		//2º. Fazendo a tableViewDepartment acompanhar a janela
		tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());
	}

	//Método responsável por acessar o serviço, carregar os departamentos e jogar os departamentos na ObservableList.
	public void updateTableView() {
		if(service == null) {
			throw new IllegalStateException("Service was null");
		}
		//Recuperando os departamentos mockados
		List<Department> list = service.findAll();
		//Carregando list dentro do obsList
		obsList = FXCollections.observableArrayList(list);
		tableViewDepartment.setItems(obsList);
	}
	
	//Método auxiliar para criar a tela do DialogForm
	private void createDialogForm(String absoluteName,Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			
			//Instanciando um novo Stage para que um palco seja carregado na frente de outro palco
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Enter Department data");
			dialogStage.setScene(new Scene(pane));
			//Afirmando que a janela não pode ser redimensionada
			dialogStage.setResizable(false);
			//Chamando o stage pai da janela
			dialogStage.initOwner(parentStage);
			//Dizendo qual o comportamento da janela
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
		}
		catch(IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
}
