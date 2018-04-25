package controller;

import application.CSTable;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CSMenuController {
	@FXML
	private Button back;

	/**
	 * Handler for back button. When event receive the CS table scene is shown.
	 * 
	 * @param event
	 */
	public void backButtonHandler(ActionEvent event) {
		ScreenController.switchWindow((Stage) back.getScene().getWindow(), new CSTable());
	}
}
