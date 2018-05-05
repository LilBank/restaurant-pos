package controller;

import java.util.List;

import application.TableView;
import application.CheckBill;
import application.Main;
import database.DBManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import util.ScreenController;

/**
 * OrderController(manager/employee) contains method for handling all event
 * receive from the UserInterface. Contains method for viewing and ordering
 * customer orders. (With help form TA for spacing nodes)
 * 
 * @author Piyawat & Vichaphol & P'Jacky
 *
 */
public class OrderViewController {
	@FXML
	private Button order;
	@FXML
	private Button back;
	@FXML
	private Button exit;
	@FXML
	private Button checkBill;
	@FXML
	private TextField totalPrice;
	@FXML
	private FlowPane foodpane;
	@FXML
	private FlowPane drinkpane;

	private static String tablenumber;

	// for single instantiation
	private static List<String> foodname;
	private static List<String> drinkname;

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
		ScreenController.switchWindow((Stage) back.getScene().getWindow(), new TableView());
	}

	/**
	 * Handler for back button. When event receive the CS table scene is shown.
	 * 
	 */
	public void checkBillButtonHandler(ActionEvent event) {
		ScreenController.switchWindow((Stage) back.getScene().getWindow(), new CheckBill(null));
	}

	/**
	 * Handler for logout button. When event receive the Start up scene is
	 * shown.
	 * 
	 */
	public void exitButtonHandler(ActionEvent event) {
		ScreenController.switchWindow((Stage) exit.getScene().getWindow(), new Main());
	}

	/**
	 * Static method for scene before opening this scene to get the button text
	 * and set as table number.
	 * 
	 * @param buttonText
	 */
	public static void setTable(String buttonText) {
		tablenumber = buttonText;
	}

	/**
	 * Static method for scene before opening this scene to get list of menu
	 * names and set the List<String> attribute above.
	 * 
	 * @param List
	 *            of menu names List<String>
	 */
	public static void setMenu(List<String> foodnames, List<String> drinknames) {
		foodname = foodnames;
		drinkname = drinknames;
	}

	// during in test
	public int getTable() {
		return Integer.parseInt(tablenumber);
	}

}
