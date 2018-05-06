package controller;

import java.util.ArrayList;
import java.util.List;

import database.DBManager;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
	@FXML
	private TableView<String> table;
	/** id Column */
	@FXML
	private TableColumn<String, Integer> id;
	/** name Column */
	@FXML
	private TableColumn<String, String> name;
	/** password Column */
	@FXML
	private TableColumn<String, String> password;
	@FXML
	private Button remove;

	private static List<User> userName = DBManager.getUser();

	@FXML
	public void initialize() {
		System.out.println(userName);
//		ObservableList<User> data = FXCollections.observableList(userID);
//		table.setItems(data);
//		table.getColumns().addAll(id, name, password);
		// id.setCellValueFactory(new PropertyValueFactory<Menu, String>(""));
	}

	/**
	 * Method for handling remove button. When event receive, remove user from the
	 * table and database.
	 */
	public void removeButtonHandler() {

	}
}
