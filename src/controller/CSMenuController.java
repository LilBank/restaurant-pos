package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.TabableView;

import application.CSCheckBill;
import application.CSTable;
import application.Main;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Food;

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
	private Button back;
	@FXML
	private Button logout;
	@FXML
	private TextField totalPrice;
	@FXML
	private TableView<Food> table;
	@FXML
	private TableColumn<Food, ?> tableColumn;

	private ListView<Label> foodList;
	private ListProperty<Label> listProperty;
	List<String> nameCollector = new ArrayList<>();

	final ObservableList<Food> data = FXCollections.observableArrayList(new Food("Pizza", 1, 50),
			new Food("Ham", 1, 20));

	@FXML
	public void initialize() {
		createTableColumn();
	}

	public void createTableColumn() {
		TableColumn nameC = new TableColumn("Name");
		nameC.setMinWidth(200);
		nameC.setCellValueFactory(new PropertyValueFactory<Food, String>("name"));
		TableColumn quantityC = new TableColumn("Quantity");
		quantityC.setMinWidth(100);
		quantityC.setCellValueFactory(new PropertyValueFactory<Food, Integer>("quantity"));
		TableColumn priceC = new TableColumn("Price");
		priceC.setMinWidth(100);
		priceC.setCellValueFactory(new PropertyValueFactory<Food, Integer>("price"));
		table.setItems(data);
		table.getColumns().addAll(nameC,quantityC,priceC);
	}
	public void sendButtonHandler() {
		int total = Integer.parseInt(totalPrice.getText());
	}

	/**
	 * Handler for order button. When event receive the CS checkbill scene is shown.
	 * 
	 */
	public void orderButtonHandler(ActionEvent event) {
		ScreenController.switchWindow((Stage) order.getScene().getWindow(), new CSCheckBill());
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
