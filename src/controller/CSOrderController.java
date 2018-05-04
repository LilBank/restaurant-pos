package controller;

import java.util.List;

import application.CSTable;
import application.Main;
import database.DBManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
//import model.Food;
import util.ScreenController;

/**
 * CSMenuController(customer) contains method for handling all event receive
 * from the UserInterface. Contains method for customer to call for bill check
 * and order dishes.
 * 
 * @author Piyawat & Vichaphol
 *
 */
public class CSOrderController {
	@FXML
	private Button order;
	@FXML
	private Button back;
	@FXML
	private Button exit;
	@FXML
	private TextField totalPrice;

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
		ScreenController.switchWindow((Stage) back.getScene().getWindow(), new CSTable());
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
