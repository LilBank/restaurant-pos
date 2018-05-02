package controller;

import application.Login;
import application.MGMenu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * MGTableController class contains method for handling event from the
 * manager-tableview ui. Those methods can also switch to other manager window
 * and view all the tables.
 * 
 * @author Piyawat & Vichapol
 *
 */
public class MGTableController {
	@FXML
	private Button image;
	@FXML
	private Button logout;
	@FXML
	Alert alert;
	
	// will be used when generate button is done
	@FXML
	private Pane pane;
	@FXML
	private Button temp;

	@FXML
	public void intialize() {

	}

	/**
	 * Handler for image button. When event receive the edit menu scene is
	 * shown.
	 * 
	 */
	public void imageButtonHandler(ActionEvent event) {
		ScreenController.switchWindow((Stage) image.getScene().getWindow(), new MGMenu());
	}

	/**
	 * Handler for logout button. When event receive the login scene is shown.
	 * 
	 */
	public void logoutButtonHandler(ActionEvent event) {
		alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to log out ?", ButtonType.OK);
		alert.showAndWait().ifPresent(response -> {
			if (response == ButtonType.OK) {
				ScreenController.switchWindow((Stage) logout.getScene().getWindow(), new Login());
			}
		});
	}
}
