package controller;

import application.MGTableView;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * MGOrderController(manager) contains method for handling all event receive
 * from the UserInterface. Contains method for viewing and ordering customer
 * orders with permission to remove customer's order.
 * 
 * @author Piyawat & Vichaphol
 *
 */
public class MGOrderController {
	@FXML
	private Button order;
	@FXML
	private Button back;
	@FXML
	private Button exit;
	@FXML
	private TextField totalPrice;

	// will be used when generate button is done
	@FXML
	private Pane foodpane;
	@FXML
	private Pane drinkpane;
	@FXML
	private Button temp;
	
	private static String tablenumber;

	@FXML
	public void initialize() {

	}

	// during in test
	public void orderButtonHandler(ActionEvent event) {

	}

	/**
	 * Handler for back button. When event receive the CS table scene is shown.
	 * 
	 */
	public void backButtonHandler(ActionEvent event) {
		ScreenController.switchWindow((Stage) back.getScene().getWindow(), new MGTableView());
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
