package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.TabableView;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
	private Label totalPrice;
	@FXML
	private TableView table;

	private SimpleStringProperty id = new SimpleStringProperty("");
	private SimpleStringProperty name = new SimpleStringProperty("");
	private SimpleStringProperty quantity = new SimpleStringProperty("");
	private SimpleStringProperty price = new SimpleStringProperty("");
	private ListView<Label> foodList;
	private ListProperty<Label> listProperty;
	List<String> nameCollector = new ArrayList<>();

	@FXML
	public void initialize() {
//		id.set(FXCollections.observableList(nameCollector);
//		listItems.itemsProperty().bind(listProperty);
	}

	public void sendButtonHandler() {
		int total = Integer.parseInt(totalPrice.getText());
	}
}
