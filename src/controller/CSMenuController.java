package controller;

import application.CSCheckBill;
import application.CSTable;
import application.Main;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
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
public class CSMenuController extends MGMenuController {
	@FXML
	private Button order;
	@FXML
	private Button back;
	@FXML
	private Button exit;
	@FXML
	private TextField totalPrice;
	@FXML
	private TableView<Food> table;
	@FXML
	private TableColumn<Food, ?> tableColumn;
	@FXML
	private ListView<Label> foodList;
	@FXML
	private ListView<Label> drinkList;
	/** Combined with food list view */
	@FXML
	ListProperty<Label> foodListProperty = new SimpleListProperty<>();

	final ObservableList<Food> data = FXCollections.observableArrayList(new Food("Pizza", 1, 50),
			new Food("Ham", 1, 20));

	private static String tablenumber;

	@FXML
	public void initialize() {
		foodList.setVisible(false);
		drinkList.setVisible(false);
		foodListProperty.set(FXCollections.observableArrayList(getImage()));
		foodList.itemsProperty().bind(foodListProperty);
		createTableColumn();
	}

	/**
	 * Add column to the table with the set data.
	 * 
	 */
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
		table.getColumns().addAll(nameC, quantityC, priceC);
	}

	/**
	 * Handler for food button. When pressed, the food view will be shown.
	 * 
	 */
	public void foodButtonHandler(ActionEvent event) {
		foodList.setVisible(true);
		foodListProperty.set(FXCollections.observableArrayList(getImage()));
	}

	/**
	 * Handler for drink button. When pressed, the drink view will be shown.
	 * 
	 */
	public void drinkButtonHandler(ActionEvent event) {
		drinkList.setVisible(true);
	}

	/**
	 * Handler for order button. When event receive the CS checkbill scene is
	 * shown.
	 * 
	 */
	public void orderButtonHandler(ActionEvent event) {
		ScreenController.switchWindow((Stage) order.getScene().getWindow(), new CSCheckBill(totalPrice.getText()));
	}

	/**
	 * Handler for back button. When event receive the CS table scene is shown.
	 * 
	 */
	public void backButtonHandler(ActionEvent event) {
		ScreenController.switchWindow((Stage) back.getScene().getWindow(), new CSTable());
	}

	/**
	 * Handler for logout button. When event recieve the Start up scene is
	 * shown.
	 * 
	 */
	public void exitButtonHandler(ActionEvent event) {
		ScreenController.switchWindow((Stage) exit.getScene().getWindow(), new Main());
	}

	// during in test
	public static void setTable(String arg) {
		tablenumber = arg;
	}

	// during in test
	public int getTable() {
		return Integer.parseInt(tablenumber);
	}
}
