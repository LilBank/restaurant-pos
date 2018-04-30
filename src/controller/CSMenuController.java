package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.TabableView;

import application.CSTable;
import application.Main;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * CSMenuController contains method for handling all event receive from the
 * UserInterface.
 * 
 * @author Piyawat & Vichaphol
 *
 */
public class CSMenuController {
	@FXML
	private Button order;
	@FXML
	private Button pay;
	@FXML
	private Button back;
	@FXML
	private Button logout;
	@FXML
	private Label totalPrice;
	@FXML
	private TableColumn<CSTableController, String> table;

	private SimpleStringProperty name = new SimpleStringProperty("");
	private SimpleStringProperty quantity = new SimpleStringProperty("");
	private SimpleStringProperty price = new SimpleStringProperty("");
	private ListView<Label> foodList;
	private ListProperty<Label> listProperty;
	List<String> nameCollector = new ArrayList<>();

	@FXML
	public void initialize() {
//			foodliset.set(FXCollections.observableList(nameCollector);
//		 listItems.itemsProperty().bind(listProperty);
		
	}

	public void sendButtonHandler() {
		int total = Integer.parseInt(totalPrice.getText());
	}

	/**
	 * Handler for back button. When event receive the CS table scene is shown.
	 * 
	 */
	public void backButtonHandler(ActionEvent event) {
		ScreenController.switchWindow((Stage) back.getScene().getWindow(), new CSTable());
	}

	/**
	 * Handler for logout button. When event recieve the Start up scene is shown.
	 * 
	 */
	public void logoutButtonHandler(ActionEvent event) {
		ScreenController.switchWindow((Stage) logout.getScene().getWindow(), new Main());
	}
}
