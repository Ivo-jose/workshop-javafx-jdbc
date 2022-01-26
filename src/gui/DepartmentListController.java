package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
	public void onBtNewAction() {
		System.out.println("onBtNewAction");
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
}
