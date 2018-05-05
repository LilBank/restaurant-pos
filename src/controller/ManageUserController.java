package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

/**
 * ManagerUserController contains method for handling all event receive from the
 * UserInterface.
 * 
 * @author Piyawat & Vichaphol
 *
 */
public class ManageUserController {
	@FXML
	private TableColumn<Integer, ?> id;
	@FXML
	private TableColumn<String, ?> name;
	@FXML
	private TableColumn<String, ?> password;
}
