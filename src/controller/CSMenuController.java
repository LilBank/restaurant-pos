package controller;

import javafx.beans.property.ListProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
	private Button send;
	@FXML
	private Button pay;
	@FXML
	private Label totalPrice;
	@FXML
	private TableView<String> table;
	@FXML
	private ListView<Label> foodList;
	@FXML
	private ListProperty<Label> listProperty;

	public void sendButtonHandler() {
		int total = Integer.parseInt(totalPrice.getText());
	}
}
