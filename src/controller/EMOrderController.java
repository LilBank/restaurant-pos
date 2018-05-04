package controller;

import java.util.List;

import application.EMTableView;
import application.Main;
import database.DBManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import util.ScreenController;

/**
 * EMOrderController(employee) contains method for handling all event receive
 * from the UserInterface. Contains method for viewing and ordering customer
 * orders. (With help form TA for spacing nodes)
 * 
 * @author Piyawat & Vichaphol & P' Jacky
 *
 */
public class EMOrderController {
	@FXML
	private Button order;
	@FXML
	private Button back;
	@FXML
	private Button exit;
	@FXML
	private TextField totalPrice;
	@FXML
	private FlowPane foodpane;
	@FXML
	private FlowPane drinkpane;

	private static String tablenumber;
	private static List<String> foodname = DBManager.getFoodname("Foods");
	private static List<String> drinkname = DBManager.getFoodname("Drinks");

	@FXML
	public void initialize() {
		// adding buttons to foodpane
		System.out.println(tablenumber);
		for (String text : foodname) {
			Button button = new Button(text);
			button.setPrefSize(100, 100);
			button.setWrapText(true);
			button.setTextAlignment(TextAlignment.CENTER);
			foodpane.getChildren().add(button);
		}
		for (String text : drinkname) {
			Button button = new Button(text);
			button.setPrefSize(100, 100);
			button.setWrapText(true);
			button.setTextAlignment(TextAlignment.CENTER);
			drinkpane.getChildren().add(button);
		}
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
