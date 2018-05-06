package controller;

import java.util.ArrayList;
import java.util.List;

import database.DBManager;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Menu;
import model.User;

/**
 * ManagerUserController contains method for handling all event receive from the
 * UserInterface.
 * 
 * @author Piyawat & Vichaphol
 *
 */
public class ManageUserController {

	// /** id Column */
	// @FXML
	// private TableColumn<User, Integer> id;
	// /** name Column */
	// @FXML
	// private TableColumn<User, String> name;
	// /** password Column */
	// @FXML
	// private TableColumn<User, String> password;
	@FXML
	private ListView<User> listView;
	@FXML
	ListProperty<User> listProperty = new SimpleListProperty<>();
	@FXML
	private Button remove;

	private static List<User> userName = DBManager.getUser();

	@FXML
	public void initialize() {
		// ObservableList<User> data = FXCollections.observableList(userName);
		// table.setItems(data);
		// table.getColumns().addAll(id, name, password);
		listProperty.set(FXCollections.observableArrayList(userName));
		listView.itemsProperty().bind(listProperty);
	}

	/**
	 * Method for handling remove button. When event receive, remove user from the
	 * table and database.
	 */
	public void removeButtonHandler() {
		
	}
}
