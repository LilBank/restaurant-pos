package controller;

import java.util.ArrayList;
import java.util.List;

import application.EMTableView;
import application.Main;
import database.DBManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
	private Button back;
	@FXML
	private Button exit;
	@FXML
	private TextField totalPrice;

	// will be used when generate button is done
	@FXML
	private HBox foodpane;
	@FXML
	private Pane drinkpane;

	private static String tablenumber;

	@FXML
	public void initialize() {
		List<String> texts = DBManager.getButtons("Menu");
		System.out.println("get all names");
		foodpane.setSpacing(5);
		for (String text : texts) {
			Button button = new Button(text);
			button.setPrefSize(150, 150);
			// HBox.setMargin(button, new Insets(5));
			// button.setOnActon(...);
			foodpane.getChildren().add(button);

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
