package controller;

import java.util.ArrayList;
import java.util.List;

import application.Tableview;
import database.DBManager;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import model.User;
import util.ScreenController;

/**
 * ManagerUserController contains method for handling all event receive from the
 * UserInterface.
 * 
 * @author Piyawat & Vichaphol
 *
 */
public class ManageUserController {
	@FXML
	private ListView<String> listView;
	@FXML
	private Button remove;
	@FXML
	private Button back;

	ListProperty<String> listProperty = new SimpleListProperty<>();
	DBManager dbm = DBManager.getInstance();
	List<User> listname = dbm.getDBUser();

	@FXML
	public void initialize() {
		List<String> name = new ArrayList<>();
		listname.forEach(x -> name.add(x.getUsername()));
		listProperty.set(FXCollections.observableArrayList(name));
		listView.itemsProperty().bind(listProperty);
	}

	/**
	 * Method for handling remove button. When event receive, remove user from the
	 * table and database.
	 */
	public void removeButtonHandler(MouseEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure removing this user? ", ButtonType.OK);
		alert.showAndWait().ifPresent(response -> {
			if (response == ButtonType.OK) {
				listProperty.remove(listView.getSelectionModel().getSelectedItem());
			}
		});

	}

	/**
	 * Handler for back button. When event receive the CS table scene is shown.
	 * 
	 */
	public void backButtonHandler(ActionEvent event) {
		ScreenController.switchWindow((Stage) back.getScene().getWindow(), new Tableview());
	}
}
