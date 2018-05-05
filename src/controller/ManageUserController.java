package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;

/**
 * ManagerUserController contains method for handling all event receive from the
 * UserInterface.
 * 
 * @author Piyawat & Vichaphol
 *
 */
public class ManageUserController {
	/** id Column */
	@FXML
	private TableColumn<Integer, ?> id;
	/** name Column */
	@FXML
	private TableColumn<String, ?> name;
	/** password Column */
	@FXML
	private TableColumn<String, ?> password;
	@FXML
	private Button remove;

	/**
	 * Method for handling remove button. When event receive, remove user from the
	 * table and database.
	 */
	public void removeButtonHandler() {
		
	}
}
