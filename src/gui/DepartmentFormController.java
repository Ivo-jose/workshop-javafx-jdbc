package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import db.DbException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentFormController implements Initializable{
	
	//Criando uma dependência de Department
	private Department entity;
	
	//Criando uma dependência de DepartmentService
	private DepartmentService service;
	
	//Criando uma dependência de DataChangeListener
	private List<DataChangeListener> dataChangeListerners = new ArrayList<>();
	

	@FXML
	private TextField txtId;
	@FXML
	private TextField txtName;
	@FXML
	private Label labelErrorName;
	@FXML
	private Button btSave;
	@FXML
	private Button btCancel;
	
	public void setDepartment(Department entity) {
		this.entity = entity;
	}
	
	public void setDepartmentService(DepartmentService service) {
		this.service = service;
	}
	
	public void subscribeDataChangelistener(DataChangeListener listener) {
		dataChangeListerners.add(listener);
	}
	
	//Métodos dos eventos
	@FXML
	public void onBtSaveAction(ActionEvent event) {
		if(entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		if(service == null) {
			throw new IllegalStateException("Service was null");
		}
		try {
			entity = getFormData();
			service.saveOrUpdate(entity);
			notifyDataChangeListeners();
			Utils.currentStage(event).close();
		}
		catch(DbException e) {
			Alerts.showAlert("Error saving object", null, e.getMessage(), AlertType.ERROR);
		}
		
	}
	
	private void notifyDataChangeListeners() {
		for(DataChangeListener listener : dataChangeListerners) {
			listener.onDataChange();
		}
	}

	private Department getFormData() {
		Department obj = new Department();
		obj.setId(Utils.tryParseToInt(txtId.getId()));
		obj.setName(txtName.getText());
		return obj;
	}

	@FXML
	public void onBtCancelAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNode();
	}
	
	//Função auxiliar
	private void initializeNode() {
		//Setando o TextField para receber somente números inteiros.
		Constraints.setTextFieldInteger(txtId);
		//Setando um limite de caracteres para o TextField 
		Constraints.setTextFieldMaxLength(txtName, 40);
	}

	//Método responsável por pegar os dados contido em entity e popular as caixinhas de texto do formulário
	public void updateFormData() {
		//Tetando (Programação denfensiva)
		if(entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		//Setando o txtID com um valor convertido em String
		txtId.setText(String.valueOf(entity.getId()));
		//Setando o txtName
		txtName.setText(entity.getName());
	}
}
