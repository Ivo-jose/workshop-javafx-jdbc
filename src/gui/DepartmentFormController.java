package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DepartmentFormController implements Initializable{

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

}
