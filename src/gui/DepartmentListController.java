package gui;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;

public class DepartmentListController implements Initializable {
	
	@FXML
	private TableView<Department> tableViewDepartment;
	@FXML
	private TableColumn<Department, Integer> tableColumnId;
	@FXML
	private TableColumn<Department, String> tableColumnName;
	@FXML
	private Button btNew;
	
	//Método do tratamento de eventos do click do botão
	@FXML
	public void onBtNewAction() {
		System.out.println("onBtNewAction");
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

}
