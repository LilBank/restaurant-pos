package controller;

import application.CheckBill;
import application.EMTableView;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Food;

/**
 * EMOrderController(employee) contains method for handling all event receive
 * from the UserInterface. Contains method for viewing and ordering customer
 * orders.
 * 
 * @author Piyawat & Vichaphol
 *
 */
public class EMOrderController {
	@FXML
	private Button order;
	@FXML
	private Button checkBill;
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

	// will be used when generate button is done
	@FXML
	private Pane foodpane;
	@FXML
	private Pane drinkpane;
	@FXML
	private Button temp;

	// final ObservableList<Food> data = FXCollections.observableArrayList(new
	// Food("Pizza", 1, 50),
	// new Food("Ham", 1, 20));

	private static String tablenumber;

	@FXML
	public void initialize() {
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
		table.getColumns().addAll(nameC, quantityC, priceC);
	}

	// during in test
	public void checkBillButtonHandler(ActionEvent event) {
		 ScreenController.switchWindow((Stage) checkBill.getScene().getWindow(),
		 new CheckBill(totalPrice.getText()));
	}

	/**
	 * Handler for back button. When event receive the CS table scene is shown.
	 * 
	 */
	public void backButtonHandler(ActionEvent event) {
		ScreenController.switchWindow((Stage) back.getScene().getWindow(), new EMTableView());
	}

	/**
	 * Handler for logout button. When event receive the Start up scene is
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
