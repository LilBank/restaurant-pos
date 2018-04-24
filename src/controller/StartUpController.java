package controller;

import application.CSTable;
import application.Login;
import application.SignUp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * StartUpController contains method for handling all event receive from the
 * UserInterface.
 * 
 * @author Piyawat & Vichapol
 *
 */
public class StartUpController {
	@FXML
	private Button login;
	@FXML
	private Button signUp;
	@FXML
	private Button csMode;

	public void loginButtonHandler(ActionEvent event) {
		ScreenController.switchWindow((Stage) login.getScene().getWindow(), new Login());
	}

	public void signUpButtonHandler(ActionEvent event) {
		ScreenController.switchWindow((Stage) signUp.getScene().getWindow(), new SignUp());
	}

	public void csModeButtonHandler(ActionEvent event) {
		ScreenController.switchWindow((Stage) csMode.getScene().getWindow(), new CSTable());
	}
}
