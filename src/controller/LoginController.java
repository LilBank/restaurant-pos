package controller;

import application.CSTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LoginController {
	@FXML
	private Button login;

	public void loginButtonHandler(ActionEvent event) {
		ScreenController.switchWindow((Stage) login.getScene().getWindow(), new CSTable());
	}
}
