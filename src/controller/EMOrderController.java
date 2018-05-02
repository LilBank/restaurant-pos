package controller;

import application.CheckBill;
import application.EMTableView;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
<<<<<<< HEAD
	public void orderButtonHandler(ActionEvent event) {

=======
	public void checkBillButtonHandler(ActionEvent event) {
		 ScreenController.switchWindow((Stage) checkBill.getScene().getWindow(),
		 new CheckBill(totalPrice.getText()));
>>>>>>> branch 'master' of https://github.com/bankkeez/projectx-restaurantPOS.git
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
