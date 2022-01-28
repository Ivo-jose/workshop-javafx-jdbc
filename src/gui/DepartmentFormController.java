package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;

public class DepartmentFormController implements Initializable{
	
	//Criando uma dependência de Department
	private Department entity;

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
	
	
	//Métodos dos eventos
	@FXML
	public void onBtSaveAction() {
		System.out.println("Save");
	}
	
	@FXML
	public void onBtCancelAction() {
		System.out.println("Cancel");
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
